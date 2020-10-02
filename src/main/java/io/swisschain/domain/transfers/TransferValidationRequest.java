package io.swisschain.domain.transfers;

import java.time.Instant;

public class TransferValidationRequest {
  private long id;
  private TransferDetails details;
  private ApprovalContext approvalContext;
  private String customerSignature;
  private String siriusSignature;
  private TransferValidationRequestStatus status;
  private Instant createdAt;
  private Instant updatedAt;

  public TransferValidationRequest() {}

  public TransferValidationRequest(
      long id,
      TransferDetails details,
      ApprovalContext approvalContext,
      String customerSignature,
      String siriusSignature,
      TransferValidationRequestStatus status,
      Instant createdAt,
      Instant updatedAt) {
    this.id = id;
    this.details = details;
    this.approvalContext = approvalContext;
    this.customerSignature = customerSignature;
    this.siriusSignature = siriusSignature;
    this.status = status;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public static TransferValidationRequest create(
      long id, TransferDetails details, String customerSignature, String siriusSignature) {
    var createdAt = Instant.now();
    return new TransferValidationRequest(
        id,
        details,
        new ApprovalContext(),
        customerSignature,
        siriusSignature,
        TransferValidationRequestStatus.Pending,
        createdAt,
        createdAt);
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public TransferDetails getDetails() {
    return details;
  }

  public void setDetails(TransferDetails details) {
    this.details = details;
  }

  public ApprovalContext getApprovalContext() {
    return approvalContext;
  }

  public void setApprovalContext(ApprovalContext approvalContext) {
    this.approvalContext = approvalContext;
  }

  public String getCustomerSignature() {
    return customerSignature;
  }

  public void setCustomerSignature(String customerSignature) {
    this.customerSignature = customerSignature;
  }

  public String getSiriusSignature() {
    return siriusSignature;
  }

  public void setSiriusSignature(String siriusSignature) {
    this.siriusSignature = siriusSignature;
  }

  public TransferValidationRequestStatus getStatus() {
    return status;
  }

  public void setStatus(TransferValidationRequestStatus status) {
    this.status = status;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Instant updatedAt) {
    this.updatedAt = updatedAt;
  }

  public void validate(){
    status = TransferValidationRequestStatus.Processing;
  }

  public void approve(){
    status = TransferValidationRequestStatus.Approved;
  }

  public void reject(){
    status = TransferValidationRequestStatus.Rejected;
  }
}
