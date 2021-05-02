package io.swisschain.odm.contracts.selector;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PolicySelectorRequestWrapper {

  @JsonProperty("__DecisionID__")
  public String decisionId;

  @JsonProperty("selectPolicyRequest")
  public PolicySelectorRequest request;

  public PolicySelectorRequestWrapper() {}

  public PolicySelectorRequestWrapper(String decisionId, PolicySelectorRequest request) {
    this.decisionId = decisionId;
    this.request = request;
  }
}
