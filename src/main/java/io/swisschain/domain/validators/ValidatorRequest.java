package io.swisschain.domain.validators;

import io.swisschain.contracts.documents.Resolution;

import java.time.Instant;
import java.util.UUID;

public class ValidatorRequest {
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

  public ValidatorRequest() {}

  public ValidatorRequest(
      String id,
      String validatorId,
      String tenantId,
      String message,
      String key,
      String nonce,
      ValidatorRequestStatus status,
      ValidatorRequestType type,
      long validationRequestId,
      String document,
      String signature,
      Resolution resolution,
      String resolutionMessage,
      String deviceInfo,
      String ip,
      Instant createdAt,
      Instant updatedAt) {
    this.id = id;
    this.validatorId = validatorId;
    this.tenantId = tenantId;
    this.message = message;
    this.key = key;
    this.nonce = nonce;
    this.status = status;
    this.type = type;
    this.validationRequestId = validationRequestId;
    this.document = document;
    this.signature = signature;
    this.resolution = resolution;
    this.resolutionMessage = resolutionMessage;
    this.deviceInfo = deviceInfo;
    this.ip = ip;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getValidatorId() {
    return validatorId;
  }

  public void setValidatorId(String validatorId) {
    this.validatorId = validatorId;
  }

  public String getTenantId() {
    return tenantId;
  }

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
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

  public ValidatorRequestStatus getStatus() {
    return status;
  }

  public void setStatus(ValidatorRequestStatus status) {
    this.status = status;
  }

  public ValidatorRequestType getType() {
    return type;
  }

  public void setType(ValidatorRequestType type) {
    this.type = type;
  }

  public long getValidationRequestId() {
    return validationRequestId;
  }

  public void setValidationRequestId(long validationRequestId) {
    this.validationRequestId = validationRequestId;
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

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Instant updatedAt) {
    this.updatedAt = updatedAt;
  }

  public void complete(
      String document,
      String signature,
      Resolution resolution,
      String resolutionMessage,
      String deviceInfo,
      String ip) {
    this.status = ValidatorRequestStatus.Completed;
    this.document = document;
    this.signature = signature;
    this.resolution = resolution;
    this.resolutionMessage = resolutionMessage;
    this.deviceInfo = deviceInfo;
    this.ip = ip;
    this.updatedAt = Instant.now();
  }

  public static ValidatorRequest create(
      String validatorId,
      String tenantId,
      String message,
      String key,
      String nonce,
      ValidatorRequestType type,
      long validationRequestId) {
    var createdAt = Instant.now();
    return new ValidatorRequest(
        UUID.randomUUID().toString(),
        validatorId,
        tenantId,
        message,
        key,
        nonce,
        ValidatorRequestStatus.New,
        type,
        validationRequestId,
        null,
        null,
        null,
        null,
        null,
        null,
        createdAt,
        createdAt);
  }
}
