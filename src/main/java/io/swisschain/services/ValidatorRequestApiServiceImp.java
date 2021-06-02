package io.swisschain.services;

import io.swisschain.domain.exceptions.OperationException;
import io.swisschain.sirius.guardianApi.GuardianApiClient;
import io.swisschain.sirius.guardianApi.generated.validation_requests.ValidationRequestsOuterClass;

public class ValidatorRequestApiServiceImp implements ValidatorRequestApiService {
  private final GuardianApiClient guardianApiClient;

  public ValidatorRequestApiServiceImp(GuardianApiClient guardianApiClient) {
    this.guardianApiClient = guardianApiClient;
  }

  @Override
  public void create(
      String tenantId,
      long vaultId,
      String validationRequestId,
      String validatorId,
      String message,
      String key,
      String nonce)
      throws OperationException {
    var request =
        ValidationRequestsOuterClass.CreateValidationRequestRequest.newBuilder()
            .setIdempotencyId(
                String.format(
                    "Guardian:CreateValidatorRequest:%s-%s", validationRequestId, validatorId))
            .setTenantId(tenantId)
            .setVaultId(vaultId)
            .setValidationRequestId(validationRequestId)
            .setValidatorId(validatorId)
            .setMessage(message)
            .setKey(key)
            .setNonce(nonce)
            .build();

    var response = guardianApiClient.getValidationRequests().create(request);

    if (response.getBodyCase()
        == ValidationRequestsOuterClass.CreateValidationRequestResponse.BodyCase.ERROR) {
      throw new OperationException(
          String.format(
              "Can not get validators. Error: %s %s",
              response.getError().getErrorCode().name(), response.getError().getErrorMessage()));
    }
  }

  @Override
  public void confirm(String validatorId, String validationRequestId) throws OperationException {
    var request =
        ValidationRequestsOuterClass.ConfirmValidationRequestRequest.newBuilder()
            .setIdempotencyId(
                String.format(
                    "Guardian:ConfirmValidatorRequest:%s-%s", validationRequestId, validatorId))
            .setValidationRequestId(validationRequestId)
            .setValidatorId(validatorId)
            .build();

    var response = guardianApiClient.getValidationRequests().confirm(request);

    if (response.getBodyCase()
        == ValidationRequestsOuterClass.ConfirmValidationRequestResponse.BodyCase.ERROR) {
      throw new OperationException(
          String.format(
              "Can not get validators. Error: %s %s",
              response.getError().getErrorCode().name(), response.getError().getErrorMessage()));
    }
  }
}
