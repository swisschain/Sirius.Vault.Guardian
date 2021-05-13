package io.swisschain.repositories.validator_requests;

import io.swisschain.contracts.documents.Resolution;
import io.swisschain.domain.validators.ValidatorRequestStatus;
import io.swisschain.domain.validators.ValidatorRequestType;
import io.swisschain.repositories.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public class ValidatorRequestEntity extends Entity {
  private String id;
  private String validatorId;
  private String tenantId;
  private String message;
  private String key;
  private String nonce;
  private ValidatorRequestStatus status;
  private ValidatorRequestType type;
  private long validationRequestId;
  private String document;
  private String signature;
  private Resolution resolution;
  private String resolutionMessage;
  private String deviceInfo;
  private String ip;
  private Instant createdAt;
  private Instant updatedAt;

  public String getId() {
    return id;
  }

  public String getValidatorId() {
    return validatorId;
  }

  public String getTenantId() {
    return tenantId;
  }

  public String getMessage() {
    return message;
  }

  public String getKey() {
    return key;
  }

  public String getNonce() {
    return nonce;
  }

  public ValidatorRequestStatus getStatus() {
    return status;
  }

  public ValidatorRequestType getType() {
    return type;
  }

  public long getValidationRequestId() {
    return validationRequestId;
  }

  public String getDocument() {
    return document;
  }

  public String getSignature() {
    return signature;
  }

  public Resolution getResolution() {
    return resolution;
  }

  public String getResolutionMessage() {
    return resolutionMessage;
  }

  public String getDeviceInfo() {
    return deviceInfo;
  }

  public String getIp() {
    return ip;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  @Override
  public void map(ResultSet resultSet) throws SQLException {
    this.id = resultSet.getString("id");
    this.validatorId = resultSet.getString("validator_id");
    this.tenantId = resultSet.getString("tenant_id");
    this.message = resultSet.getString("message");
    this.key = resultSet.getString("key");
    this.nonce = resultSet.getString("nonce");
    this.status = ValidatorRequestStatus.valueOf(resultSet.getString("status"));
    this.type = ValidatorRequestType.valueOf(resultSet.getString("type"));
    this.validationRequestId = resultSet.getLong("validation_request_id");
    this.document = resultSet.getString("document");
    this.signature = resultSet.getString("signature");
    var resolution = resultSet.getString("resolution");
    this.resolution = resolution != null ? Resolution.valueOf(resolution) : null;
    this.resolutionMessage = resultSet.getString("resolution_message");
    this.deviceInfo = resultSet.getString("device_info");
    this.ip = resultSet.getString("ip");
    this.createdAt = resultSet.getTimestamp("created_at").toInstant();
    this.updatedAt = resultSet.getTimestamp("updated_at").toInstant();
  }
}
