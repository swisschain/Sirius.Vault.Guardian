package io.swisschain.domain.validators;

import io.swisschain.contracts.Resolution;

import java.time.Instant;

public class ValidatorResponse {
  private String validatorId;
  private long transferValidationRequestId;
  private String document;
  private String signature;
  private Resolution resolution;
  private String resolutionMessage;
  private String deviceInfo;
  private String ip;
  private Instant createdAt;

  public ValidatorResponse() {}

  public ValidatorResponse(
      String validatorId,
      long transferValidationRequestId,
      String document,
      String signature,
      Resolution resolution,
      String resolutionMessage,
      String deviceInfo,
      String ip,
      Instant createdAt) {
    this.validatorId = validatorId;
    this.transferValidationRequestId = transferValidationRequestId;
    this.document = document;
    this.signature = signature;
    this.resolution = resolution;
    this.resolutionMessage = resolutionMessage;
    this.deviceInfo = deviceInfo;
    this.ip = ip;
    this.createdAt = createdAt;
  }

  public static ValidatorResponse create(
      String validatorId,
      long transferValidationRequestId,
      String document,
      String signature,
      Resolution resolution,
      String resolutionMessage,
      String deviceInfo,
      String ip) {
    return new ValidatorResponse(
        validatorId,
        transferValidationRequestId,
        document,
        signature,
        resolution,
        resolutionMessage,
        deviceInfo,
        ip,
        Instant.now());
  }

  public String getValidatorId() {
    return validatorId;
  }

  public void setValidatorId(String validatorId) {
    this.validatorId = validatorId;
  }

  public long getTransferValidationRequestId() {
    return transferValidationRequestId;
  }

  public void setTransferValidationRequestId(long transferValidationRequestId) {
    this.transferValidationRequestId = transferValidationRequestId;
  }

  public String getDocument() {
    return document;
  }

  public void setDocument(String document) {
    this.document = document;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
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

  public String getDeviceInfo() {
    return deviceInfo;
  }

  public void setDeviceInfo(String deviceInfo) {
    this.deviceInfo = deviceInfo;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }
}
