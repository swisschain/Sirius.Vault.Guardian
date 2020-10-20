package io.swisschain.contracts;

import java.util.Objects;

/** Represents the transfer detailed information and initial client request. */
public class TransferContext {
  private String document;
  private String signature;
  private String accountReferenceId;
  private String withdrawalReferenceId;
  private String component;
  private String operationType;
  private String sourceGroup;
  private String destinationGroup;
  private RequestContext requestContext;

  public TransferContext() {}

  public TransferContext(
      String document,
      String signature,
      String accountReferenceId,
      String withdrawalReferenceId,
      String component,
      String operationType,
      String sourceGroup,
      String destinationGroup,
      RequestContext requestContext) {
    this.document = document;
    this.signature = signature;
    this.accountReferenceId = accountReferenceId;
    this.withdrawalReferenceId = withdrawalReferenceId;
    this.component = component;
    this.operationType = operationType;
    this.sourceGroup = sourceGroup;
    this.destinationGroup = destinationGroup;
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

  /** Gets the client signature for document. */
  public String getSignature() {
    return signature;
  }

  /** Sets the client signature for document. */
  public void setSignature(String signature) {
    this.signature = signature;
  }

  /** Gets the account reference identifier in external system. */
  public String getAccountReferenceId() {
    return accountReferenceId;
  }

  /** Sets the account reference identifier in external system. */
  public void setAccountReferenceId(String accountReferenceId) {
    this.accountReferenceId = accountReferenceId;
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

  /** Gets the Sirius source wallet group. */
  public String getSourceGroup() {
    return sourceGroup;
  }

  /** Sets the Sirius source wallet group. */
  public void setSourceGroup(String sourceGroup) {
    this.sourceGroup = sourceGroup;
  }

  /** Gets the Sirius destination wallet group. */
  public String getDestinationGroup() {
    return destinationGroup;
  }

  /** Sets the Sirius destination wallet group. */
  public void setDestinationGroup(String destinationGroup) {
    this.destinationGroup = destinationGroup;
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
        && Objects.equals(signature, that.signature)
        && Objects.equals(accountReferenceId, that.accountReferenceId)
        && Objects.equals(withdrawalReferenceId, that.withdrawalReferenceId)
        && component.equals(that.component)
        && operationType.equals(that.operationType)
        && Objects.equals(sourceGroup, that.sourceGroup)
        && Objects.equals(destinationGroup, that.destinationGroup)
        && Objects.equals(requestContext, that.requestContext);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        document,
        signature,
        accountReferenceId,
        withdrawalReferenceId,
        component,
        operationType,
        sourceGroup,
        destinationGroup,
        requestContext);
  }
}
