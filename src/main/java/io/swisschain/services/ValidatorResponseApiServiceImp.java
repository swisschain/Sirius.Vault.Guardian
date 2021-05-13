package io.swisschain.services;

import io.swisschain.domain.validators.ValidatorResponse;
import io.swisschain.sirius.guardianApi.GuardianApiClient;
import io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class ValidatorResponseApiServiceImp implements ValidatorResponseApiService {
  private static final Logger logger = LogManager.getLogger();
  private final GuardianApiClient guardianApiClient;

  public ValidatorResponseApiServiceImp(GuardianApiClient guardianApiClient) {
    this.guardianApiClient = guardianApiClient;
  }

  @Override
  public List<ValidatorResponse> get() {
    var request = ValidatorApprovalRequests.GetApprovalResultsRequest.newBuilder().build();
    var response = guardianApiClient.getTransactions().getApprovalResults(request);
    return response.getPayloadList().stream()
        .map(
            item ->
                new ValidatorResponse(
                    item.getValidatorId(),
                    item.getValidationRequestId(),
                    item.getMessage(),
                    item.getDeviceInfo(),
                    item.getIp()))
        .collect(Collectors.toList());
  }
}
