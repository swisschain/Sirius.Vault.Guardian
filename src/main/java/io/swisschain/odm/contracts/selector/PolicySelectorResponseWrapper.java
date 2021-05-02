package io.swisschain.odm.contracts.selector;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PolicySelectorResponseWrapper {
  @JsonProperty("__DecisionID__")
  public String decisionId;

  @JsonProperty("selectPolicyResponse")
  public PolicySelectorResponse response;
}
