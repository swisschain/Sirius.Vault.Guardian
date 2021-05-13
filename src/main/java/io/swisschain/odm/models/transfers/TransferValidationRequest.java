package io.swisschain.odm.models.transfers;

import io.swisschain.contracts.documents.ValidatorResolution;
import io.swisschain.contracts.transfers.Transfer;

import java.util.List;

public class TransferValidationRequest {
  public Transfer transfer;
  public List<ValidatorResolution> validatorResolutions;

  public TransferValidationRequest() {}

  public TransferValidationRequest(
      Transfer transfer, List<ValidatorResolution> validatorResolutions) {
    this.transfer = transfer;
    this.validatorResolutions = validatorResolutions;
  }
}
