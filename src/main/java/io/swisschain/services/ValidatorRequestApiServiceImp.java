package io.swisschain.services;

import io.swisschain.sirius.guardianApi.GuardianApiClient;
import io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ValidatorRequestApiServiceImp implements ValidatorRequestApiService {
  private static final Logger logger = LogManager.getLogger();
  private final GuardianApiClient guardianApiClient;

  public ValidatorRequestApiServiceImp(GuardianApiClient guardianApiClient) {
    this.guardianApiClient = guardianApiClient;
  }

  @Override
  public void confirm(String validatorId, String validationRequestId) {
    var request =
        ValidatorApprovalRequests.AcknowledgeResultRequest.newBuilder()
            .setTransferSigningRequestId(validationRequestId)
            .setValidatorId(validatorId)
            .build();

    var response = guardianApiClient.getTransactions().acknowledgeResult(request);

    // TODO: handle response errors
  }

  @Override
  public void create(
      String tenantId,
      String validationRequestId,
      String validatorId,
      String message,
      String key,
      String nonce) {
    var request =
        ValidatorApprovalRequests.CreateApprovalRequestRequest.newBuilder()
            .setRequestId(String.format("Guardian:Create:%s-%s", validationRequestId, validatorId))
            .setTenantId(tenantId)
            .setValidationRequestId(validationRequestId)
            .setValidatorId(validatorId)
            .setMessage(message)
            .setKey(key)
            .setNonce(nonce)
            .build();

    var response = guardianApiClient.getTransactions().createApprovalRequest(request);

    // TODO: handle response errors

    logger.info(
        String.format(
            "Approval requests sent. Id: %s; Validator: %s", validationRequestId, validatorId));
  }
}
