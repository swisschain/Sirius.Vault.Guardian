package io.swisschain.domain.validators;

public class ValidatorResponse {
  private String validatorId;
  private String validationRequestId;
  private String message;
  private String deviceInfo;
  private String ip;

  public ValidatorResponse() {}

  public ValidatorResponse(
      String validatorId,
      String validationRequestId,
      String message,
      String deviceInfo,
      String ip) {
    this.validatorId = validatorId;
    this.validationRequestId = validationRequestId;
    this.message = message;
    this.deviceInfo = deviceInfo;
    this.ip = ip;
  }

  public String getValidatorId() {
    return validatorId;
  }

  public void setValidatorId(String validatorId) {
    this.validatorId = validatorId;
  }

  public String getValidationRequestId() {
    return validationRequestId;
  }

  public void setValidationRequestId(String validationRequestId) {
    this.validationRequestId = validationRequestId;
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
}
