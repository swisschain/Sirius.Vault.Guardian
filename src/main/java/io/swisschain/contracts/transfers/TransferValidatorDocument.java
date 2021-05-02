package io.swisschain.contracts.transfers;

import io.swisschain.contracts.common.Resolution;
import io.swisschain.contracts.common.ValidatorDocument;

/** Represents validator transfer validation document. */
public class TransferValidatorDocument extends ValidatorDocument {
  private Transfer transfer;

  public TransferValidatorDocument() {
    super();
  }

  public TransferValidatorDocument(
      Resolution resolution, String resolutionMessage, Transfer transfer) {
    super(resolution, resolutionMessage);
    this.transfer = transfer;
  }

  /** Gets transfer details. */
  public Transfer getTransfer() {
    return transfer;
  }

  /** Sets transfer details. */
  public void setTransfer(Transfer transfer) {
    this.transfer = transfer;
  }
}
