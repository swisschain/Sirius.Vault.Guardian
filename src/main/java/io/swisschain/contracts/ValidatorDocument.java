package io.swisschain.contracts;

public class ValidatorDocument {
  private Resolution resolution;
  private String resolutionMessage;
  private TransferDetails transferDetails;

  public ValidatorDocument() {}

  public ValidatorDocument(
      Resolution resolution, String resolutionMessage, TransferDetails transferDetails) {
    this.resolution = resolution;
    this.resolutionMessage = resolutionMessage;
    this.transferDetails = transferDetails;
  }

  public Resolution getResolution() {
    return resolution;
  }

  public void setResolution(Resolution resolution) {
    this.resolution = resolution;
  }

  public String getResolutionMessage() {
    return resolutionMessage;
  }

  public void setResolutionMessage(String resolutionMessage) {
    this.resolutionMessage = resolutionMessage;
  }

  public TransferDetails getTransferDetails() {
    return transferDetails;
  }

  public void setTransferDetail(TransferDetails transferDetails) {
    this.transferDetails = transferDetails;
  }
}
