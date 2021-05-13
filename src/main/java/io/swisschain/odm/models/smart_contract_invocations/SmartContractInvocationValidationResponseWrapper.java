package io.swisschain.odm.models.smart_contract_invocations;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swisschain.odm.models.DecisionResponse;
import io.swisschain.odm.models.ResponseWrapper;

public class SmartContractInvocationValidationResponseWrapper extends ResponseWrapper {
  @JsonProperty("smart_contract_invocation_decision_response")
  public DecisionResponse smartContractInvocationDecisionResponse;
}
