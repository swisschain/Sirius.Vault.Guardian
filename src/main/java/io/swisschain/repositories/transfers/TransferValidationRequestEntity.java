package io.swisschain.repositories.transfers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swisschain.domain.transfers.TransferValidationRequestStatus;
import io.swisschain.repositories.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public class TransferValidationRequestEntity extends Entity {
  private final ObjectMapper mapper = new ObjectMapper();

  private long id;
  private String details;
  private String approvalContext;
  private String customerSignature;
  private String siriusSignature;
  private TransferValidationRequestStatus status;
  private Instant createdAt;
  private Instant updatedAt;

  public long getId() {
    return id;
  }

  public String getDetails() {
    return details;
  }

  public String getApprovalContext() {
    return approvalContext;
  }

  public String getCustomerSignature() {
    return customerSignature;
  }

  public String getSiriusSignature() {
    return siriusSignature;
  }

  public TransferValidationRequestStatus getStatus() {
    return status;
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
    this.details = resultSet.getString("details");
    this.approvalContext = resultSet.getString("approval_context");
    this.customerSignature = resultSet.getString("customer_signature");
    this.siriusSignature = resultSet.getString("sirius_signature");
    this.status =
        Enum.valueOf(TransferValidationRequestStatus.class, resultSet.getString("status"));
    this.createdAt = resultSet.getTimestamp("created_at").toInstant();
    this.updatedAt = resultSet.getTimestamp("updated_at").toInstant();
  }
}
