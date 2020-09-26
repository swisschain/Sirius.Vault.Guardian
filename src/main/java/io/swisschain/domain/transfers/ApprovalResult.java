package io.swisschain.domain.transfers;

import java.time.Instant;

public class ApprovalResult {
  private String validatorId;
  private String message;
  private String deviceInfo;
  private String ip;
  private String signature;
  private Instant timestamp;

  public ApprovalResult() {}

  public ApprovalResult(
      String validatorId,
      String message,
      String deviceInfo,
      String ip,
      String signature,
      Instant timestamp) {
    this.validatorId = validatorId;
    this.message = message;
    this.deviceInfo = deviceInfo;
    this.ip = ip;
    this.signature = signature;
    this.timestamp = timestamp;
  }

  public String getValidatorId() {
    return validatorId;
  }

  public void setValidatorId(String validatorId) {
    this.validatorId = validatorId;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
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

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  public Instant getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Instant timestamp) {
    this.timestamp = timestamp;
  }
}
