package io.swisschain.domain.validators;

public class ValidatorApproval {
  private String validatorId;
  private long transferApprovalRequestId;
  private String document;
  private String signature;
  private String deviceInfo;
  private String ip;

  public ValidatorApproval() {}

  public ValidatorApproval(
      String validatorId,
      long transferApprovalRequestId,
      String document,
      String signature,
      String deviceInfo,
      String ip) {
    this.validatorId = validatorId;
    this.transferApprovalRequestId = transferApprovalRequestId;
    this.document = document;
    this.signature = signature;
    this.deviceInfo = deviceInfo;
    this.ip = ip;
  }

  public String getValidatorId() {
    return validatorId;
  }

  public void setValidatorId(String validatorId) {
    this.validatorId = validatorId;
  }

  public long getTransferApprovalRequestId() {
    return transferApprovalRequestId;
  }

  public void setTransferApprovalRequestId(long transferApprovalRequestId) {
    this.transferApprovalRequestId = transferApprovalRequestId;
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
}
