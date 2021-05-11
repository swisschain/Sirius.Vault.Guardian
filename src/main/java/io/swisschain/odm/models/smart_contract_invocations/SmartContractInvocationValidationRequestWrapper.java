package io.swisschain.odm.models.smart_contract_invocations;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swisschain.odm.models.RequestWrapper;

public class SmartContractInvocationValidationRequestWrapper extends RequestWrapper {
  @JsonProperty("smart_contract_invocation_validation_request")
  public SmartContractInvocationValidationRequest smartContractInvocationValidationRequest;

  public SmartContractInvocationValidationRequestWrapper() {}

  public SmartContractInvocationValidationRequestWrapper(
      String decisionId,
      SmartContractInvocationValidationRequest smartContractInvocationValidationRequest) {
    super(decisionId);
    this.smartContractInvocationValidationRequest = smartContractInvocationValidationRequest;
  }
}
