package io.swisschain.repositories.validatorResponses;

import io.swisschain.contracts.Resolution;
import io.swisschain.repositories.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public class ValidatorResponseEntity extends Entity {
  private String validatorId;
  private long transferValidationRequestId;
  private String document;
  private String signature;
  private Resolution resolution;
  private String resolutionMessage;
  private String deviceInfo;
  private String ip;
  private Instant createdAt;

  public String getValidatorId() {
    return validatorId;
  }

  public void setValidatorId(String validatorId) {
    this.validatorId = validatorId;
  }

  public long getTransferValidationRequestId() {
    return transferValidationRequestId;
  }

  public void setTransferValidationRequestId(long transferValidationRequestId) {
    this.transferValidationRequestId = transferValidationRequestId;
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

  public Resolution getResolution() {
    return resolution;
  }

  public void setResolution(Resolution resolution) {
    this.resolution = resolution;
  }

  public String getResolutionMessage() {
    return resolutionMessage;
  }

  public void setResolutionMessage(String resolutionMessage) {
    this.resolutionMessage = resolutionMessage;
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

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public void map(ResultSet resultSet) throws SQLException {
    this.validatorId = resultSet.getString("validator_id");
    this.transferValidationRequestId = resultSet.getLong("transfer_validation_request_id");
    this.document = resultSet.getString("document");
    this.signature = resultSet.getString("signature");
    this.resolution = Resolution.valueOf(resultSet.getString("resolution"));
    this.resolutionMessage = resultSet.getString("resolution_message");
    this.deviceInfo = resultSet.getString("device_info");
    this.ip = resultSet.getString("ip");
    this.createdAt = resultSet.getTimestamp("created_at").toInstant();
  }
}
