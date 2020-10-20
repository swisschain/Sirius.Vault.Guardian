package io.swisschain.domain.validators;

import java.time.Instant;

public class ValidatorRequest {
  private String validatorId;
  private long transferValidationRequestId;
  private String encryptedMessage;
  private String encryptedKey;
  private String key;
  private String nonce;
  private Instant createdAt;

  public ValidatorRequest() {}

  public ValidatorRequest(
      String validatorId,
      long transferValidationRequestId,
      String encryptedMessage,
      String encryptedKey,
      String key,
      String nonce,
      Instant createdAt) {
    this.validatorId = validatorId;
    this.transferValidationRequestId = transferValidationRequestId;
    this.encryptedMessage = encryptedMessage;
    this.encryptedKey = encryptedKey;
    this.key = key;
    this.nonce = nonce;
    this.createdAt = createdAt;
  }

  public static ValidatorRequest create(
      String validatorId,
      long transferValidationRequestId,
      String encryptedMessage,
      String encryptedKey,
      String key,
      String nonce) {
    return new ValidatorRequest(
        validatorId,
        transferValidationRequestId,
        encryptedMessage,
        encryptedKey,
        key,
        nonce,
        Instant.now());
  }

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
}
