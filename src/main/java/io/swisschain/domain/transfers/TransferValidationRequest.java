package io.swisschain.domain.transfers;

import io.swisschain.contracts.*;

import java.math.BigDecimal;
import java.time.Instant;

public class TransferValidationRequest {
  private long id;
  private String tenantId;
  private TransferDetails transferDetails;
  private TransferValidationRequestStatus status;
  private String document;
  private String signature;
  private String rejectReasonMessage;
  private Instant createdAt;
  private Instant updatedAt;

  public TransferValidationRequest() {}

  public TransferValidationRequest(
      long id,
      String tenantId,
      TransferDetails transferDetails,
      TransferValidationRequestStatus status,
      String document,
      String signature,
      String rejectReasonMessage,
      Instant createdAt,
      Instant updatedAt) {
    this.id = id;
    this.tenantId = tenantId;
    this.transferDetails = transferDetails;
    this.status = status;
    this.document = document;
    this.signature = signature;
    this.rejectReasonMessage = rejectReasonMessage;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public static TransferValidationRequest create(
      long id,
      String tenantId,
      TransferDetails transferDetails) {
    var createdAt = Instant.now();
    return new TransferValidationRequest(
        id,
        tenantId,
        transferDetails,
        TransferValidationRequestStatus.New,
        null,
        null,
        null,
        createdAt,
        createdAt);
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

  public TransferDetails getTransferDetails() {
    return transferDetails;
  }

  public void setTransferDetails(TransferDetails transferDetails) {
    this.transferDetails = transferDetails;
  }

  public TransferValidationRequestStatus getStatus() {
    return status;
  }

  public void setStatus(TransferValidationRequestStatus status) {
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
    status = TransferValidationRequestStatus.Processing;
    updatedAt = Instant.now();
  }

  public void approve(String document, String signature) {
    setStatus(TransferValidationRequestStatus.Approved);
    setDocument(document);
    setSignature(signature);
    setUpdatedAt(Instant.now());
  }

  public void reject(String document, String signature, String rejectReasonMessage) {
    setStatus(TransferValidationRequestStatus.Rejected);
    setDocument(document);
    setSignature(signature);
    setRejectReasonMessage(rejectReasonMessage);
    setUpdatedAt(Instant.now());
  }
}
