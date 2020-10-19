package io.swisschain.repositories.entities;

import io.swisschain.domain.transfers.TransferValidationRequestStatus;
import io.swisschain.repositories.common.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public class TransferValidationRequestEntity extends Entity {
  private long id;
  private String tenantId;
  private String transferDetails;
  private String document;
  private String signature;
  private String rejectReasonMessage;
  private TransferValidationRequestStatus status;
  private Instant createdAt;
  private Instant updatedAt;

  public long getId() {
    return id;
  }

  public String getTenantId() {
    return tenantId;
  }

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  public String getTransferDetails() {
    return transferDetails;
  }

  public TransferValidationRequestStatus getStatus() {
    return status;
  }

  public String getDocument() {
    return document;
  }

  public String getSignature() {
    return signature;
  }

  public String getRejectReasonMessage() {
    return rejectReasonMessage;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  @Override
  public void map(ResultSet resultSet) throws SQLException {
    this.id = resultSet.getLong("id");
    this.tenantId = resultSet.getString("tenant_id");
    this.transferDetails = resultSet.getString("transfer_details");
    this.status = TransferValidationRequestStatus.valueOf(resultSet.getString("status"));
    this.document = resultSet.getString("document");
    this.signature = resultSet.getString("signature");
    this.rejectReasonMessage = resultSet.getString("reject_reason_message");
    this.createdAt = resultSet.getTimestamp("created_at").toInstant();
    this.updatedAt = resultSet.getTimestamp("updated_at").toInstant();
  }
}
