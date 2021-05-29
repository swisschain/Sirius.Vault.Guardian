package io.swisschain.services;

import io.swisschain.domain.exceptions.OperationException;
import io.swisschain.domain.validators.ValidatorResponse;
import io.swisschain.sirius.guardianApi.GuardianApiClient;
import io.swisschain.sirius.guardianApi.generated.validation_results.ValidationResultsOuterClass;

import java.util.List;
import java.util.stream.Collectors;

public class ValidatorResponseApiServiceImp implements ValidatorResponseApiService {
  private final GuardianApiClient guardianApiClient;

  public ValidatorResponseApiServiceImp(GuardianApiClient guardianApiClient) {
    this.guardianApiClient = guardianApiClient;
  }

  @Override
  public List<ValidatorResponse> get() throws OperationException {
    var request = ValidationResultsOuterClass.GetValidationResultsRequest.newBuilder().build();

    var response = guardianApiClient.getValidationResults().get(request);

    if (response.getBodyCase()
        == ValidationResultsOuterClass.GetValidationResultsResponse.BodyCase.ERROR) {
      throw new OperationException(
          String.format(
              "Can not get validator responses. Error: %s %s",
              response.getError().getErrorCode().name(), response.getError().getErrorMessage()));
    }

    return response.getResponse().getValidationResultsList().stream()
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
