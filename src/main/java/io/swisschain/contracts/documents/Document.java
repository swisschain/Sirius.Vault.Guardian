package io.swisschain.contracts.documents;

import java.time.Instant;
import java.util.List;

/** Represents final signed document created by Guardian. */
public abstract class Document {
  private long validationRequestId;
  private String tenantId;
  private List<ValidatorResolution> validatorResolutions;
  private List<String> requestedValidators;
  private DocumentStatus status;
  private String rejectReasonMessage;
  private Instant timestamp;

  protected Document() {}

  protected Document(
      long validationRequestId,
      String tenantId,
      List<ValidatorResolution> validatorResolutions,
      List<String> requestedValidators,
      DocumentStatus status,
      String rejectReasonMessage,
      Instant timestamp) {
    this.validationRequestId = validationRequestId;
    this.tenantId = tenantId;
    this.validatorResolutions = validatorResolutions;
    this.requestedValidators = requestedValidators;
    this.status = status;
    this.rejectReasonMessage = rejectReasonMessage;
    this.timestamp = timestamp;
  }

  /** Gets the original validation request identifier. */
  public long getValidationRequestId() {
    return validationRequestId;
  }

  /** Sets the original validation request identifier. */
  public void setValidationRequestId(long validationRequestId) {
    this.validationRequestId = validationRequestId;
  }

  /** Gets the tenant identifier that owns validation request. */
  public String getTenantId() {
    return tenantId;
  }

  /** Sets the tenant identifier that owns validation request. */
  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  /** Gets the list of validator resolutions. */
  public List<ValidatorResolution> getValidatorResolutions() {
    return validatorResolutions;
  }

  /** Sets the list of validator resolutions. */
  public void setValidatorResolutions(List<ValidatorResolution> validatorResolutions) {
    this.validatorResolutions = validatorResolutions;
  }

  /** Gets the list of validators identifiers that have been requested to validate request. */
  public List<String> getRequestedValidators() {
    return requestedValidators;
  }

  /** Sets the list of validators identifiers that have been requested to validate request. */
  public void setRequestedValidators(List<String> requestedValidators) {
    this.requestedValidators = requestedValidators;
  }

  /** Gets the final document status based on rule engine decision. */
  public DocumentStatus getStatus() {
    return status;
  }

  /** Sets the final document status based on rule engine decision. */
  public void setStatus(DocumentStatus status) {
    this.status = status;
  }

  /** Gets the rejection reason details. */
  public String getRejectReasonMessage() {
    return rejectReasonMessage;
  }

  /** Sets the rejection reason details. */
  public void setRejectReasonMessage(String rejectReasonMessage) {
    this.rejectReasonMessage = rejectReasonMessage;
  }

  /** Gets the date and time of documents. */
  public Instant getTimestamp() {
    return timestamp;
  }

  /** Sets the date and time of documents. */
  public void setTimestamp(Instant timestamp) {
    this.timestamp = timestamp;
  }
}
