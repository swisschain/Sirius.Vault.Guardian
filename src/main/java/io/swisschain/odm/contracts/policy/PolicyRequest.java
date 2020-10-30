package io.swisschain.odm.contracts.policy;

import java.util.List;

public class PolicyRequest {
  public TransferDetails transferDetails;
  public List<ValidationResult> validatedBy;

  public PolicyRequest() {}

  public PolicyRequest(TransferDetails transferDetails, List<ValidationResult> validatedBy) {
    this.transferDetails = transferDetails;
    this.validatedBy = validatedBy;
  }
}
