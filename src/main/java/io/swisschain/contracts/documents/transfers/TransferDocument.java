package io.swisschain.contracts.documents.transfers;

import io.swisschain.contracts.documents.Document;
import io.swisschain.contracts.documents.DocumentStatus;
import io.swisschain.contracts.documents.ValidatorResolution;
import io.swisschain.contracts.transfers.Transfer;

import java.time.Instant;
import java.util.List;

/** Represents final signed document created by Guardian for the transfer validation request. */
public class TransferDocument extends Document {
  private Transfer transfer;

  public TransferDocument() {
    super();
  }

  public TransferDocument(
      long validationRequestId,
      String tenantId,
      Transfer transfer,
      List<ValidatorResolution> validatorResolutions,
      List<String> requestedValidators,
      DocumentStatus status,
      String rejectReasonMessage,
      Instant timestamp) {
    super(
        validationRequestId,
        tenantId,
        validatorResolutions,
        requestedValidators,
        status,
        rejectReasonMessage,
        timestamp);
    this.transfer = transfer;
  }

  /** Gets the transfer details that have been validated. */
  public Transfer getTransfer() {
    return transfer;
  }

  /** Sets the transfer details that have been validated. */
  public void setTransfer(Transfer transfer) {
    this.transfer = transfer;
  }
}
