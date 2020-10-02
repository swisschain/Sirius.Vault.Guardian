package io.swisschain.domain.transfers;

import java.util.List;

public class ApprovalContext {
  private List<ApprovalResult> approvedBy;
  private List<ApprovalResult> rejectedBy;
  private List<ApprovalResult> skippedBy;
  private List<String> validators;

  public ApprovalContext() {
  }

  public ApprovalContext(
          List<ApprovalResult> approvedBy,
          List<ApprovalResult> rejectedBy,
          List<ApprovalResult> skippedBy,
          List<String> validators) {
    this.approvedBy = approvedBy;
    this.rejectedBy = rejectedBy;
    this.skippedBy = skippedBy;
    this.validators = validators;
  }

  public List<ApprovalResult> getApprovedBy() {
    return approvedBy;
  }

  public void setApprovedBy(List<ApprovalResult> approvedBy) {
    this.approvedBy = approvedBy;
  }

  public List<ApprovalResult> getRejectedBy() {
    return rejectedBy;
  }

  public void setRejectedBy(List<ApprovalResult> rejectedBy) {
    this.rejectedBy = rejectedBy;
  }

  public List<ApprovalResult> getSkippedBy() {
    return skippedBy;
  }

  public void setSkippedBy(List<ApprovalResult> skippedBy) {
    this.skippedBy = skippedBy;
  }

  public List<String> getValidators() {
    return validators;
  }

  public void setValidators(List<String> validators) {
    this.validators = validators;
  }
}
