package io.swisschain.services;

import io.swisschain.domain.exceptions.OperationException;
import io.swisschain.domain.validators.Validator;
import io.swisschain.sirius.guardianApi.GuardianApiClient;
import io.swisschain.sirius.guardianApi.generated.validators.ValidatorsOuterClass;

import java.util.List;
import java.util.stream.Collectors;

public class ValidatorApiServiceImp implements ValidatorApiService {
  private final GuardianApiClient guardianApiClient;

  public ValidatorApiServiceImp(GuardianApiClient guardianApiClient) {
    this.guardianApiClient = guardianApiClient;
  }

  @Override
  public List<Validator> get(String tenantId) throws OperationException {
    var request =
        ValidatorsOuterClass.GetValidatorsRequest.newBuilder().setTenantId(tenantId).build();
    var response = guardianApiClient.getValidators().get(request);

    if (response.getBodyCase() == ValidatorsOuterClass.GetValidatorsResponse.BodyCase.ERROR) {
      throw new OperationException(
          String.format(
              "Can not get validators. Error: %s %s",
              response.getError().getErrorCode().name(), response.getError().getErrorMessage()));
    }

    return response.getResponse().getValidatorsList().stream()
        .map(item -> new Validator(item.getId(), item.getPublicKey()))
        .collect(Collectors.toList());
  }

  @Override
  public Validator getById(String tenantId, String validatorId) throws OperationException {
    return get(tenantId).stream()
        .filter(validator -> validator.getId().equals(validatorId))
        .findFirst()
        .orElse(null);
  }
}
