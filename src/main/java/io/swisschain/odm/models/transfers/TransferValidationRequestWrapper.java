package io.swisschain.odm.models.transfers;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swisschain.odm.models.RequestWrapper;

public class TransferValidationRequestWrapper extends RequestWrapper {
  @JsonProperty("transfer_validation_request")
  public TransferValidationRequest transferValidationRequest;

  public TransferValidationRequestWrapper() {}

  public TransferValidationRequestWrapper(
      String decisionId, TransferValidationRequest transferValidationRequest) {
    super(decisionId);
    this.transferValidationRequest = transferValidationRequest;
  }
}
