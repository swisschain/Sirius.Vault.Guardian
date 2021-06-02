package io.swisschain.domain.validation_requests;

import java.time.Instant;

public abstract class ValidationRequest {
  private long id;
  private String tenantId;
  private long vaultId;
  private ValidationRequestStatus status;
  private String document;
  private String signature;
  private String rejectReasonMessage;
  private Instant createdAt;
  private Instant updatedAt;

  public ValidationRequest() {}

  public ValidationRequest(
      long id,
      String tenantId,
      long vaultId,
      ValidationRequestStatus status,
      String document,
      String signature,
      String rejectReasonMessage,
      Instant createdAt,
      Instant updatedAt) {
    this.id = id;
    this.tenantId = tenantId;
    this.vaultId = vaultId;
    this.status = status;
    this.document = document;
    this.signature = signature;
    this.rejectReasonMessage = rejectReasonMessage;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTenantId() {
    return tenantId;
  }

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  public long getVaultId() {
    return vaultId;
  }

  public void setVaultId(long vaultId) {
    this.vaultId = vaultId;
  }

  public ValidationRequestStatus getStatus() {
    return status;
  }

  public void setStatus(ValidationRequestStatus status) {
    this.status = status;
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

  public String getRejectReasonMessage() {
    return rejectReasonMessage;
  }

  public void setRejectReasonMessage(String rejectReasonMessage) {
    this.rejectReasonMessage = rejectReasonMessage;
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

  public void processing() {
    status = ValidationRequestStatus.Processing;
    updatedAt = Instant.now();
  }

  public void approve() {
    setStatus(ValidationRequestStatus.Approved);
    setUpdatedAt(Instant.now());
  }

  public void reject(String rejectReasonMessage) {
    setStatus(ValidationRequestStatus.Rejected);
    setRejectReasonMessage(rejectReasonMessage);
    setUpdatedAt(Instant.now());
  }

  public void updateDocument(String document, String signature) {
    setDocument(document);
    setSignature(signature);
  }
}
