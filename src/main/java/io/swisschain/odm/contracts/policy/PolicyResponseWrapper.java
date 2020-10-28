package io.swisschain.odm.contracts.policy;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PolicyResponseWrapper {
  @JsonProperty("__DecisionID__")
  public String decisionId;

  public PolicyResponse response;
}
