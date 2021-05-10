package io.swisschain.odm.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class ResponseWrapper {
  @JsonProperty("__DecisionID__")
  public String decisionId;
}
