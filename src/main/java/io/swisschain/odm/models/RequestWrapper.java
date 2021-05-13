package io.swisschain.odm.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class RequestWrapper {
  @JsonProperty("__DecisionID__")
  public String decisionId;

  public RequestWrapper() {}

  public RequestWrapper(String decisionId) {
    this.decisionId = decisionId;
  }
}
