package io.swisschain.contracts.common;

import java.time.Instant;

/** Represents validator resolution details and original document signed be validator. */
public class ValidatorResolution {
  private String validatorId;
  private String document;
  private String signature;
  private Resolution resolution;
  private String resolutionMessage;
  private String deviceInfo;
  private String ip;
  private Instant timestamp;

  public ValidatorResolution() {}

  public ValidatorResolution(
      String validatorId,
      String document,
      String signature,
      Resolution resolution,
      String resolutionMessage,
      String deviceInfo,
      String ip,
      Instant timestamp) {
    this.validatorId = validatorId;
    this.document = document;
    this.signature = signature;
    this.resolution = resolution;
    this.resolutionMessage = resolutionMessage;
    this.deviceInfo = deviceInfo;
    this.ip = ip;
    this.timestamp = timestamp;
  }

  /** Gets the validator identifier. */
  public String getValidatorId() {
    return validatorId;
  }

  /** Sets the validator identifier. */
  public void setValidatorId(String validatorId) {
    this.validatorId = validatorId;
  }

  /** Gets the original document signed by validator. This document presented in JSON format. */
  public String getDocument() {
    return document;
  }

  /** Sets the original document signed be validator. This document presented in JSON format. */
  public void setDocument(String document) {
    this.document = document;
  }

  /** Gets the validator signature for document. */
  public String getSignature() {
    return signature;
  }

  /** Sets the validator signature for document. */
  public void setSignature(String signature) {
    this.signature = signature;
  }

  /** Gets the validator resolution for transfer. */
  public Resolution getResolution() {
    return resolution;
  }

  /** Sets the validator resolution for transfer. */
  public void setResolution(Resolution resolution) {
    this.resolution = resolution;
  }

  /** Gets the validator resolution comment for transfer. */
  public String getResolutionMessage() {
    return resolutionMessage;
  }

  /** Sets the validator resolution comment for transfer. */
  public void setResolutionMessage(String resolutionMessage) {
    this.resolutionMessage = resolutionMessage;
  }

  /** Gets the validator device information that could include OS version and etc. */
  public String getDeviceInfo() {
    return deviceInfo;
  }

  /** Sets the validator device information that could include OS version and etc. */
  public void setDeviceInfo(String deviceInfo) {
    this.deviceInfo = deviceInfo;
  }

  /** Gets the IP address that validator device had when responding for validation request. */
  public String getIp() {
    return ip;
  }

  /** Sets the IP address that validator device had when responding for validation request. */
  public void setIp(String ip) {
    this.ip = ip;
  }

  /** Gets the date and time when validation response received. */
  public Instant getTimestamp() {
    return timestamp;
  }

  /** Sets the date and time when validation response received. */
  public void setTimestamp(Instant timestamp) {
    this.timestamp = timestamp;
  }
}
