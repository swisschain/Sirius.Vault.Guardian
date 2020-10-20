package io.swisschain.contracts;

import java.time.Instant;
import java.util.List;

/** Represents final signed document created by Guardian for the transfer validation request. */
public class Document {
  private long transferValidationRequestId;
  private String tenantId;
  private TransferDetails transferDetails;
  private List<ValidatorResolution> validatorResolutions;
  private List<String> requestedValidators;
  private DocumentStatus status;
  private String rejectReasonMessage; // Technical problem: exception.getMessage() || Rejected by validators
  private Instant timestamp;

  public Document() {}

  public Document(
      long transferValidationRequestId,
      String tenantId,
      TransferDetails transferDetails,
      List<ValidatorResolution> validatorResolutions,
      List<String> requestedValidators,
      DocumentStatus status,
      String rejectReasonMessage,
      Instant timestamp) {
    this.transferValidationRequestId = transferValidationRequestId;
    this.tenantId = tenantId;
    this.transferDetails = transferDetails;
    this.validatorResolutions = validatorResolutions;
    this.requestedValidators = requestedValidators;
    this.status = status;
    this.rejectReasonMessage = rejectReasonMessage;
    this.timestamp = timestamp;
  }

  /** Gets the original transfer validation request identifier. */
  public long getTransferValidationRequestId() {
    return transferValidationRequestId;
  }

  /** Sets the original transfer validation request identifier. */
  public void setTransferValidationRequestId(long transferValidationRequestId) {
    this.transferValidationRequestId = transferValidationRequestId;
  }

  /** Gets the tenant identifier that owns transfer validation request. */
  public String getTenantId() {
    return tenantId;
  }

  /** Sets the tenant identifier that owns transfer validation request. */
  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  /** Gets the transfer details that have been validated. */
  public TransferDetails getTransferDetails() {
    return transferDetails;
  }

  /** Sets the transfer details that have been validated. */
  public void setTransferDetails(TransferDetails transferDetails) {
    this.transferDetails = transferDetails;
  }

  /** Gets the list of validator resolutions. */
  public List<ValidatorResolution> getValidatorResolutions() {
    return validatorResolutions;
  }

  /** Sets the list of validator resolutions. */
  public void setValidatorResolutions(List<ValidatorResolution> validatorResolutions) {
    this.validatorResolutions = validatorResolutions;
  }

  /** Gets the list of validators identifiers that have been requested to validate transfer. */
  public List<String> getRequestedValidators() {
    return requestedValidators;
  }

  /** Sets the list of validators identifiers that have been requested to validate transfer. */
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
