package io.swisschain.services;

import io.swisschain.domain.validators.ValidatorApproval;
import io.swisschain.domain.validators.ValidatorRequest;
import io.swisschain.sirius.guardianApi.GuardianApiClient;
import io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class ValidatorApprovalApiServiceImp implements ValidatorApprovalApiService {
  private static final Logger logger = LogManager.getLogger();
  private final GuardianApiClient guardianApiClient;

  public ValidatorApprovalApiServiceImp(GuardianApiClient guardianApiClient) {
    this.guardianApiClient = guardianApiClient;
  }

  @Override
  public List<ValidatorApproval> get() {
    var request = ValidatorApprovalRequests.GetApprovalResultsRequest.newBuilder().build();
    var response = guardianApiClient.getTransactions().getApprovalResults(request);
    return response.getPayloadList().stream()
        .map(
            item ->
                new ValidatorApproval(
                    item.getValidatorId(),
                    Long.parseLong(item.getTransferSigningRequestId()),
                    item.getResolutionDocumentEncBase64(),
                    item.getSignature(),
                    "deviceInfo", // TODO: set device info
                    "ip"))
        .collect(Collectors.toList());
  }

  @Override
  public void confirm(String validatorId, long transferValidationRequestId) {
    var request =
        ValidatorApprovalRequests.AcknowledgeResultRequest.newBuilder()
            .setTransferSigningRequestId(Long.toString(transferValidationRequestId))
            .setValidatorId(validatorId)
            .build();

    var response = guardianApiClient.getTransactions().acknowledgeResult(request);

    // TODO: handle response errors
  }

  @Override
  public void create(
      String tenantId, long transferValidationRequestId, ValidatorRequest validatorRequest) {
    var requestBuilder =
        ValidatorApprovalRequests.CreateApprovalRequestRequest.newBuilder()
            .setRequestId(
                String.format(
                    "Guardian:Create:%d-%s",
                    transferValidationRequestId, validatorRequest.getValidatorId()))
            .setTenantId(tenantId)
            .setTransferSigningRequestId(Long.toString(transferValidationRequestId));

    var validatorRequestBuilder =
        ValidatorApprovalRequests.CreateApprovalRequestRequest.ValidatorRequest.newBuilder()
            .setValidatorId(validatorRequest.getValidatorId())
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
