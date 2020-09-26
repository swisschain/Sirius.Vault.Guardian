package io.swisschain.domain.approvals;

public class ValidatorApproval {
  private String validatorId;
  private long transferApprovalRequestId;
  private ValidatorApprovalStatus status;
  private String message;
  private String signature;

  public ValidatorApproval() {}

  public ValidatorApproval(
      String validatorId,
      long transferApprovalRequestId,
      ValidatorApprovalStatus status,
      String message,
      String signature) {
    this.validatorId = validatorId;
    this.transferApprovalRequestId = transferApprovalRequestId;
    this.status = status;
    this.message = message;
    this.signature = signature;
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

  public ValidatorApprovalStatus getStatus() {
    return status;
  }

  public void setStatus(ValidatorApprovalStatus status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }
}
