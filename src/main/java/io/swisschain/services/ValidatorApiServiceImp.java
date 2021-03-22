package io.swisschain.services;

import io.swisschain.domain.validators.Validator;
import io.swisschain.sirius.guardianApi.GuardianApiClient;
import io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class ValidatorApiServiceImp implements ValidatorApiService {
  private static final Logger logger = LogManager.getLogger();
  private final GuardianApiClient guardianApiClient;

  public ValidatorApiServiceImp(GuardianApiClient guardianApiClient) {
    this.guardianApiClient = guardianApiClient;
  }

  @Override
  public List<Validator> get(String tenantId) {
    var request =
        ValidatorApprovalRequests.ActiveValidatorsRequest.newBuilder()
            .setTenantId(tenantId)
            .build();
    var response = guardianApiClient.getTransactions().getActiveValidators(request);

    // TODO: handle response errors

    return response.getActiveValidatorsRequestList().stream()
        .map(item -> new Validator(item.getValidatorId(), item.getValidatorPublicKeyPem()))
        .collect(Collectors.toList());
  }

  @Override
  public Validator getById(String tenantId, String validatorId) {
    return get(tenantId).stream()
        .filter(validator -> validator.getId().equals(validatorId))
        .findFirst()
        .orElse(null);
  }
}
