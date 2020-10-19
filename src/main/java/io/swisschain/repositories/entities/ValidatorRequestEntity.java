package io.swisschain.repositories.entities;

import io.swisschain.repositories.common.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public class ValidatorRequestEntity extends Entity {
  private String validatorId;
  private long transferValidationRequestId;
  private String encryptedMessage;
  private String encryptedKey;
  private String key;
  private String nonce;
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

  public String getEncryptedMessage() {
    return encryptedMessage;
  }

  public void setEncryptedMessage(String encryptedMessage) {
    this.encryptedMessage = encryptedMessage;
  }

  public String getEncryptedKey() {
    return encryptedKey;
  }

  public void setEncryptedKey(String encryptedKey) {
    this.encryptedKey = encryptedKey;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getNonce() {
    return nonce;
  }

  public void setNonce(String nonce) {
    this.nonce = nonce;
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
    this.encryptedMessage = resultSet.getString("encrypted_message");
    this.encryptedKey = resultSet.getString("encrypted_key");
    this.key = resultSet.getString("key");
    this.nonce = resultSet.getString("nonce");
    this.createdAt = resultSet.getTimestamp("created_at").toInstant();
  }
}
