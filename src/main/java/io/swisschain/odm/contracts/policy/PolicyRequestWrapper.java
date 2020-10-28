package io.swisschain.odm.contracts.policy;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swisschain.odm.contracts.selector.PolicySelectorRequest;

public class PolicyRequestWrapper {
  @JsonProperty("__DecisionID__")
  public String decisionId;

  @JsonProperty("transferValidationRequest")
  public PolicyRequest request;

  public PolicyRequestWrapper() {}

  public PolicyRequestWrapper(String decisionId, PolicyRequest request) {
    this.decisionId = decisionId;
    this.request = request;
  }
}
