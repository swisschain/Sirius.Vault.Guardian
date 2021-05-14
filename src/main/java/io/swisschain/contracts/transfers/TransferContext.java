package io.swisschain.contracts.transfers;

import io.swisschain.contracts.common.RequestContext;

import java.util.Objects;

/** Represents the transfer detailed information and initial client request. */
public class TransferContext {
  private String document;
  private String documentVersion;
  private String signature;
  private String withdrawalReferenceId;
  private String component;
  private String operationType;
  private RequestContext requestContext;

  public TransferContext() {}

  public TransferContext(
      String document,
      String documentVersion,
      String signature,
      String withdrawalReferenceId,
      String component,
      String operationType,
      RequestContext requestContext) {
    this.document = document;
    this.documentVersion = documentVersion;
    this.signature = signature;
    this.withdrawalReferenceId = withdrawalReferenceId;
    this.component = component;
    this.operationType = operationType;
    this.requestContext = requestContext;
  }

  /** Gets the original initial document signed by client */
  public String getDocument() {
    return document;
  }

  /** Sets the original initial document signed by client */
  public void setDocument(String document) {
    this.document = document;
  }

  /** Gets the initial document version. */
  public String getDocumentVersion() {
    return documentVersion;
  }

  /** Sets the initial document version. */
  public void setDocumentVersion(String documentVersion) {
    this.documentVersion = documentVersion;
  }

  /** Gets the client signature for document. */
  public String getSignature() {
    return signature;
  }

  /** Sets the client signature for document. */
  public void setSignature(String signature) {
    this.signature = signature;
  }

  /** Gets the withdrawal reference identifier in external system. */
  public String getWithdrawalReferenceId() {
    return withdrawalReferenceId;
  }

  /** Sets the withdrawal reference identifier in external system. */
  public void setWithdrawalReferenceId(String withdrawalReferenceId) {
    this.withdrawalReferenceId = withdrawalReferenceId;
  }

  /** Gets the Sirius component that was originated transfer. */
  public String getComponent() {
    return component;
  }

  /** Sets the Sirius component that was originated transfer. */
  public void setComponent(String component) {
    this.component = component;
  }

  /** Gets the Sirius operation type. */
  public String getOperationType() {
    return operationType;
  }

  /** Sets the Sirius operation type. */
  public void setOperationType(String operationType) {
    this.operationType = operationType;
  }

  /** Gets the client request details. */
  public RequestContext getRequestContext() {
    return requestContext;
  }

  /** Sets the client request details. */
  public void setRequestContext(RequestContext requestContext) {
    this.requestContext = requestContext;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TransferContext that = (TransferContext) o;
    return Objects.equals(document, that.document)
        && Objects.equals(documentVersion, that.documentVersion)
        && Objects.equals(signature, that.signature)
        && Objects.equals(withdrawalReferenceId, that.withdrawalReferenceId)
        && Objects.equals(component, that.component)
        && Objects.equals(operationType, that.operationType)
        && Objects.equals(requestContext, that.requestContext);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        document,
        documentVersion,
        signature,
        withdrawalReferenceId,
        component,
        operationType,
        requestContext);
  }
}
