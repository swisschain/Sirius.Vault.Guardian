package io.swisschain.odm.models.transfers;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swisschain.odm.models.DecisionResponse;
import io.swisschain.odm.models.ResponseWrapper;

public class TransferValidationResponseWrapper extends ResponseWrapper {
  @JsonProperty("transfer_decision_response")
  public DecisionResponse transferValidationRequest;
}
