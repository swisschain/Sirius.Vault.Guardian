package io.swisschain.services;

import io.swisschain.contracts.smart_contracts.deployment.SmartContractDeployer;
import io.swisschain.contracts.smart_contracts.deployment.SmartContractDeployment;
import io.swisschain.contracts.smart_contracts.deployment.SmartContractDeploymentContext;
import io.swisschain.domain.validation_requests.smart_contract_deployments.SmartContractDeploymentValidationRequest;
import io.swisschain.sirius.vaultApi.VaultApiClient;
import io.swisschain.sirius.vaultApi.generated.common.Common;
import io.swisschain.sirius.vaultApi.generated.smart_contract_deployment_validation_requests.SmartContractDeploymentValidationRequestsOuterClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SmartContractDeploymentApiServiceImp implements SmartContractDeploymentApiService {
  private final Logger logger = LogManager.getLogger();
  private final VaultApiClient vaultApiClient;
  private final String hostProcessId;

  public SmartContractDeploymentApiServiceImp(VaultApiClient vaultApiClient, String hostProcessId) {
    this.vaultApiClient = vaultApiClient;
    this.hostProcessId = hostProcessId;
  }

  @Override
  public List<SmartContractDeploymentValidationRequest> get() {
    var request =
        SmartContractDeploymentValidationRequestsOuterClass
            .GetSmartContractDeploymentValidationRequestsRequest.newBuilder()
            .build();
    var response = vaultApiClient.getSmartContractDeploymentValidationRequests().get(request);

    if (response.getBodyCase()
        == SmartContractDeploymentValidationRequestsOuterClass
            .GetSmartContractDeploymentValidationRequestsResponse.BodyCase.ERROR) {
      logger.error(
          String.format(
              "An error occurred while getting smart contract deployment validation requests. %s %s",
              response.getError().getErrorCode().name(), response.getError().getErrorMessage()));
      return new ArrayList<>();
    }

    return response.getResponse().getRequestsList().stream()
        .map(this::map)
        .collect(Collectors.toList());
  }

  @Override
  public void confirm(SmartContractDeploymentValidationRequest validationRequest) {
    var conformationRequest =
        SmartContractDeploymentValidationRequestsOuterClass
            .ConfirmSmartContractDeploymentValidationRequestRequest.newBuilder()
            .setRequestId(
                String.format(
                    "Guardian:SmartContractDeploymentValidationRequest:%d",
                    validationRequest.getId()))
            .setValidationRequestId(validationRequest.getId())
            .setDocument(validationRequest.getDocument())
            .setSignature(validationRequest.getSignature())
            .setHostProcessId(hostProcessId)
            .build();

    var response =
        vaultApiClient.getSmartContractDeploymentValidationRequests().confirm(conformationRequest);

    if (response.getBodyCase()
        == SmartContractDeploymentValidationRequestsOuterClass
            .ConfirmSmartContractDeploymentValidationRequestResponse.BodyCase.ERROR) {
      var message =
          String.format(
              "It is not possible to confirm smart contract deployment validation request %d. %s %s",
              validationRequest.getId(),
              response.getError().getErrorCode().name(),
              response.getError().getErrorMessage());
      if (response.getError().getErrorCode()
          == SmartContractDeploymentValidationRequestsOuterClass
              .ConfirmSmartContractDeploymentValidationRequestErrorResponseBody.ErrorCode
              .INVALID_STATE) {
        logger.warn(message);
      } else {
        logger.error(message);
      }
    } else {
      logger.info(
          String.format(
              "Smart contract deployment validation request %d confirmed.",
              validationRequest.getId()));
    }
  }

  @Override
  public void reject(SmartContractDeploymentValidationRequest validationRequest) {
    var rejectRequestBuilder =
        SmartContractDeploymentValidationRequestsOuterClass
            .RejectSmartContractDeploymentValidationRequestRequest.newBuilder()
            .setRequestId(
                String.format(
                    "Guardian:SmartContractDeploymentValidationRequest:%d",
                    validationRequest.getId()))
            .setValidationRequestId(validationRequest.getId())
            .setRejectionReason(
                SmartContractDeploymentValidationRequestsOuterClass
                    .RejectSmartContractDeploymentValidationRequestRequest.RejectionReason
                    .REJECTED_BY_POLICY)
            .setDocument(validationRequest.getDocument())
            .setSignature(validationRequest.getSignature())
            .setHostProcessId(hostProcessId);

    if (validationRequest.getRejectReasonMessage() != null) {
      rejectRequestBuilder.setRejectionReasonMessage(validationRequest.getRejectReasonMessage());
    }

    var rejectRequest = rejectRequestBuilder.build();
    var response =
        vaultApiClient.getSmartContractDeploymentValidationRequests().reject(rejectRequest);

    if (response.getBodyCase()
        == SmartContractDeploymentValidationRequestsOuterClass
            .RejectSmartContractDeploymentValidationRequestResponse.BodyCase.ERROR) {
      var message =
          String.format(
              "It is not possible to reject smart contract deployment validation request %d. %s %s",
              validationRequest.getId(),
              response.getError().getErrorCode().name(),
              response.getError().getErrorMessage());
      if (response.getError().getErrorCode()
          == SmartContractDeploymentValidationRequestsOuterClass
              .RejectSmartContractDeploymentValidationRequestErrorResponseBody.ErrorCode
              .INVALID_STATE) {
        logger.warn(message);
      } else {
        logger.error(message);
      }
    } else {
      logger.info(
          String.format(
              "Smart contract deployment validation request %d rejected.",
              validationRequest.getId()));
    }
  }

  private SmartContractDeploymentValidationRequest map(
      SmartContractDeploymentValidationRequestsOuterClass.SmartContractDeploymentValidationRequest
          validationRequest) {
    return SmartContractDeploymentValidationRequest.create(
        validationRequest.getId(),
        validationRequest.getTenantId(),
        map(validationRequest.getSmartContractDeployment()));
  }

  private SmartContractDeployment map(
      SmartContractDeploymentValidationRequestsOuterClass.SmartContractDeployment
          smartContractDeployment) {
    return new SmartContractDeployment(
        smartContractDeployment.getId(),
        Mapper.map(smartContractDeployment.getBlockchain()),
        map(smartContractDeployment.getDeployer()),
        Mapper.map(smartContractDeployment.getArgumentsList()),
        Mapper.map(smartContractDeployment.getPayment()),
        Mapper.map(smartContractDeployment.getFee()),
        map(smartContractDeployment.getContext()));
  }

  private SmartContractDeployer map(
      SmartContractDeploymentValidationRequestsOuterClass.SmartContractDeployer
          smartContractDeployer) {
    return new SmartContractDeployer(
        smartContractDeployer.getAddress(), Mapper.map(smartContractDeployer.getBrokerAccount()));
  }

  private SmartContractDeploymentContext map(
      SmartContractDeploymentValidationRequestsOuterClass.SmartContractDeploymentContext
          smartContractDeploymentContext) {
    return new SmartContractDeploymentContext(
        smartContractDeploymentContext.hasDocument()
            ? smartContractDeploymentContext.getDocument().getValue()
            : null,
        smartContractDeploymentContext.hasDocumentVersion()
            ? smartContractDeploymentContext.getDocumentVersion().getValue()
            : null,
        smartContractDeploymentContext.hasSignature()
            ? smartContractDeploymentContext.getSignature().getValue()
            : null,
        smartContractDeploymentContext.getSmartContractName(),
        smartContractDeploymentContext.getCodeHash(),
        smartContractDeploymentContext.getComponent(),
        smartContractDeploymentContext.hasReferenceId()
            ? smartContractDeploymentContext.getReferenceId().getValue()
            : null,
        Mapper.map(smartContractDeploymentContext.getRequestContext()));
  }
}
