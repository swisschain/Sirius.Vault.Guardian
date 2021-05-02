package io.swisschain.contracts.common;

/** Represents validator document. */
public abstract class ValidatorDocument {
  private Resolution resolution;
  private String resolutionMessage;

  public ValidatorDocument() {}

  public ValidatorDocument(Resolution resolution, String resolutionMessage) {
    this.resolution = resolution;
    this.resolutionMessage = resolutionMessage;
  }

  /** Gets the validator resolution. */
  public Resolution getResolution() {
    return resolution;
  }

  /** Sets the validator resolution. */
  public void setResolution(Resolution resolution) {
    this.resolution = resolution;
  }

  /** Gets the validator resolution message. */
  public String getResolutionMessage() {
    return resolutionMessage;
  }

  /** Sets the validator resolution message. */
  public void setResolutionMessage(String resolutionMessage) {
    this.resolutionMessage = resolutionMessage;
  }
}
