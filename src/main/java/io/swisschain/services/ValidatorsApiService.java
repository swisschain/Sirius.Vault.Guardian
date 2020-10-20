package io.swisschain.services;

import io.grpc.StatusRuntimeException;
import io.swisschain.domain.validators.ValidatorApproval;
import io.swisschain.domain.validators.Validator;
import io.swisschain.domain.validators.ValidatorRequest;
import io.swisschain.sirius.guardianApi.GuardianApiClient;
import io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ValidatorsApiService {
  private static final Logger logger = LogManager.getLogger();
  private final GuardianApiClient guardianApiClient;

  public ValidatorsApiService(GuardianApiClient guardianApiClient) {
    this.guardianApiClient = guardianApiClient;
  }

  public List<ValidatorApproval> getApprovals() {
    try {
      var request = ValidatorApprovalRequests.GetApprovalResultsRequest.newBuilder().build();
      var response = guardianApiClient.getTransactions().getApprovalResults(request);

      var validatorApprovals = new ArrayList<ValidatorApproval>();

      for (var validatorApproval : response.getPayloadList()) {
        validatorApprovals.add(
            new ValidatorApproval(
                validatorApproval.getValidatorId(),
                Long.parseLong(validatorApproval.getTransferSigningRequestId()),
                validatorApproval.getResolutionDocumentEncBase64(),
                validatorApproval.getSignature(),
                "deviceInfo", // TODO: set device info
                "ip"));  // TODO: set ip
      }

      return validatorApprovals;
    } catch (StatusRuntimeException statusRuntimeException) {
      logger.warn(
          String.format(
              "Unable to get list of approval results due to %s network status",
              statusRuntimeException.getStatus().getCode().name()));
      return null;
    }
  }

  public List<Validator> getValidators() {
    var request = ValidatorApprovalRequests.ActiveValidatorsRequest.newBuilder().build();
    var response = guardianApiClient.getTransactions().getActiveValidators(request);

    // TODO: handle response errors

    var validators = new ArrayList<Validator>();

    for (var item : response.getActiveValidatorsRequestList()) {
      validators.add(new Validator(item.getValidatorId(), item.getValidatorPublicKeyPem()));
    }

    return validators;
  }

  public Validator getValidatorById(String validatorId) {
    var validators = getValidators();

    for (var validator : validators) {
      if (validator.getId().equals(validatorId)) {
        return validator;
      }
    }

    return null;
  }

  public void confirm(String validatorId, long transferValidationRequestId) {
    var request =
        ValidatorApprovalRequests.AcknowledgeResultRequest.newBuilder()
            .setTransferSigningRequestId(Long.toString(transferValidationRequestId))
            .setValidatorId(validatorId)
            .build();

    var response = guardianApiClient.getTransactions().acknowledgeResult(request);

    // TODO: handle response errors
  }

  public void sendApprovalRequest(
      long transferValidationRequestId, ValidatorRequest validatorRequest) {
    var requestBuilder =
        ValidatorApprovalRequests.CreateApprovalRequestRequest.newBuilder()
            .setRequestId(
                String.format(
                    "Guardian:Create:%d-%s",
                    transferValidationRequestId, validatorRequest.getValidatorId()))
            .setTransferSigningRequestId(Long.toString(transferValidationRequestId));

    var validatorRequestBuilder =
        ValidatorApprovalRequests.CreateApprovalRequestRequest.ValidatorRequest.newBuilder()
            .setValidaditorId(validatorRequest.getValidatorId())
            .setTransactionDetailsEncBase64(validatorRequest.getEncryptedMessage())
            .setSecretEncBase64(validatorRequest.getEncryptedKey())
            .setIvNonce(validatorRequest.getNonce());
    requestBuilder.addValidatorRequests(validatorRequestBuilder);

    var response =
        guardianApiClient.getTransactions().createApprovalRequest(requestBuilder.build());

    // TODO: handle response errors

    logger.info(
        String.format(
            "Approval requests sent. Id: %d; Validators: %s",
            transferValidationRequestId, validatorRequest.getValidatorId()));
  }
}
