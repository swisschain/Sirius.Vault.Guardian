package io.swisschain.services;

import io.swisschain.contracts.smart_contracts.invocation.SmartContractInvocation;
import io.swisschain.contracts.smart_contracts.invocation.SmartContractInvocationContext;
import io.swisschain.contracts.smart_contracts.invocation.SmartContractInvoker;
import io.swisschain.contracts.smart_contracts.invocation.SmartContractMethod;
import io.swisschain.domain.validation_requests.smart_contracts.SmartContractInvocationValidationRequest;
import io.swisschain.sirius.vaultApi.VaultApiClient;
import io.swisschain.sirius.vaultApi.generated.smart_contract_invocation_validation_requests.SmartContractInvocationValidationRequestsOuterClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SmartContractInvocationApiServiceImp implements SmartContractInvocationApiService {
  private final Logger logger = LogManager.getLogger();
  private final VaultApiClient vaultApiClient;
  private final String hostProcessId;

  public SmartContractInvocationApiServiceImp(VaultApiClient vaultApiClient, String hostProcessId) {
    this.vaultApiClient = vaultApiClient;
    this.hostProcessId = hostProcessId;
  }

  @Override
  public List<SmartContractInvocationValidationRequest> get() {
    var request =
        SmartContractInvocationValidationRequestsOuterClass
            .GetSmartContractInvocationValidationRequestsRequest.newBuilder()
            .build();
    var response = vaultApiClient.getSmartContractInvocationValidationRequests().get(request);

    if (response.getBodyCase()
        == SmartContractInvocationValidationRequestsOuterClass
            .GetSmartContractInvocationValidationRequestsResponse.BodyCase.ERROR) {
      logger.error(
          String.format(
              "An error occurred while getting smart contract invocation validation requests. %s %s",
              response.getError().getErrorCode().name(), response.getError().getErrorMessage()));
      return new ArrayList<>();
    }

    return response.getResponse().getRequestsList().stream()
        .map(this::map)
        .collect(Collectors.toList());
  }

  @Override
  public void confirm(SmartContractInvocationValidationRequest validationRequest) {
    var conformationRequest =
        SmartContractInvocationValidationRequestsOuterClass
            .ConfirmSmartContractInvocationValidationRequestRequest.newBuilder()
            .setRequestId(
                String.format(
                    "Guardian:SmartContractInvocationValidationRequest:%d",
                    validationRequest.getId()))
            .setValidationRequestId(validationRequest.getId())
            .setDocument(validationRequest.getDocument())
            .setSignature(validationRequest.getSignature())
            .setHostProcessId(hostProcessId)
            .build();

    var response =
        vaultApiClient.getSmartContractInvocationValidationRequests().confirm(conformationRequest);

    if (response.getBodyCase()
        == SmartContractInvocationValidationRequestsOuterClass
            .ConfirmSmartContractInvocationValidationRequestResponse.BodyCase.ERROR) {
      var message =
          String.format(
              "It is not possible to confirm smart contract invocation validation request %d. %s %s",
              validationRequest.getId(),
              response.getError().getErrorCode().name(),
              response.getError().getErrorMessage());
      if (response.getError().getErrorCode()
          == SmartContractInvocationValidationRequestsOuterClass
              .ConfirmSmartContractInvocationValidationRequestErrorResponseBody.ErrorCode
              .INVALID_STATE) {
        logger.warn(message);
      } else {
        logger.error(message);
      }
    } else {
      logger.info(
          String.format(
              "Smart contract invocation validation request %d confirmed.",
              validationRequest.getId()));
    }
  }

  @Override
  public void reject(SmartContractInvocationValidationRequest validationRequest) {
    var rejectRequestBuilder =
        SmartContractInvocationValidationRequestsOuterClass
            .RejectSmartContractInvocationValidationRequestRequest.newBuilder()
            .setRequestId(
                String.format(
                    "Guardian:SmartContractInvocationValidationRequest:%d",
                    validationRequest.getId()))
            .setValidationRequestId(validationRequest.getId())
            .setRejectionReason(
                SmartContractInvocationValidationRequestsOuterClass
                    .RejectSmartContractInvocationValidationRequestRequest.RejectionReason
                    .REJECTED_BY_POLICY)
            .setDocument(validationRequest.getDocument())
            .setSignature(validationRequest.getSignature())
            .setHostProcessId(hostProcessId);

    if (validationRequest.getRejectReasonMessage() != null) {
      rejectRequestBuilder.setRejectionReasonMessage(validationRequest.getRejectReasonMessage());
    }

    var rejectRequest = rejectRequestBuilder.build();
    var response =
        vaultApiClient.getSmartContractInvocationValidationRequests().reject(rejectRequest);

    if (response.getBodyCase()
        == SmartContractInvocationValidationRequestsOuterClass
            .RejectSmartContractInvocationValidationRequestResponse.BodyCase.ERROR) {
      var message =
          String.format(
              "It is not possible to reject smart contract invocation validation request %d. %s %s",
              validationRequest.getId(),
              response.getError().getErrorCode().name(),
              response.getError().getErrorMessage());
      if (response.getError().getErrorCode()
          == SmartContractInvocationValidationRequestsOuterClass
              .RejectSmartContractInvocationValidationRequestErrorResponseBody.ErrorCode
              .INVALID_STATE) {
        logger.warn(message);
      } else {
        logger.error(message);
      }
    } else {
      logger.info(
          String.format(
              "Smart contract invocation validation request %d rejected.",
              validationRequest.getId()));
    }
  }

  private SmartContractInvocationValidationRequest map(
      SmartContractInvocationValidationRequestsOuterClass.SmartContractInvocationValidationRequest
          validationRequest) {
    return SmartContractInvocationValidationRequest.create(
        validationRequest.getId(),
        validationRequest.getTenantId(),
        map(validationRequest.getSmartContractInvocation()));
  }

  private SmartContractInvocation map(
      SmartContractInvocationValidationRequestsOuterClass.SmartContractInvocation
          smartContractInvocation) {
    return new SmartContractInvocation(
        smartContractInvocation.getId(),
        Mapper.map(smartContractInvocation.getBlockchain()),
        map(smartContractInvocation.getInvoker()),
        new SmartContractMethod(
            smartContractInvocation.getMethodName(),
            smartContractInvocation.hasMethodAddress()
                ? smartContractInvocation.getMethodAddress().getValue()
                : null),
        smartContractInvocation.getSmartContractAddress(),
        Mapper.map(smartContractInvocation.getArgumentsList()),
        Mapper.map(smartContractInvocation.getPayment()),
        Mapper.map(smartContractInvocation.getFee()),
        map(smartContractInvocation.getContext()));
  }

  private SmartContractInvoker map(
      SmartContractInvocationValidationRequestsOuterClass.SmartContractInvoker
          smartContractInvoker) {
    return new SmartContractInvoker(
        smartContractInvoker.getAddress(), Mapper.map(smartContractInvoker.getBrokerAccount()));
  }

  private SmartContractInvocationContext map(
      SmartContractInvocationValidationRequestsOuterClass.SmartContractInvocationContext
          smartContractInvocationContext) {
    return new SmartContractInvocationContext(
        smartContractInvocationContext.hasDocument()
            ? smartContractInvocationContext.getDocument().getValue()
            : null,
        smartContractInvocationContext.hasDocumentVersion()
            ? smartContractInvocationContext.getDocumentVersion().getValue()
            : null,
        smartContractInvocationContext.hasSignature()
            ? smartContractInvocationContext.getSignature().getValue()
            : null,
        smartContractInvocationContext.getSmartContractName(),
        smartContractInvocationContext.getCodeHash(),
        smartContractInvocationContext.getComponent(),
        smartContractInvocationContext.hasReferenceId()
            ? smartContractInvocationContext.getReferenceId().getValue()
            : null,
        Mapper.map(smartContractInvocationContext.getRequestContext()));
  }
}
