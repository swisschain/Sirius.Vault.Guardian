package io.swisschain.contracts.smart_contracts.invocation;

import io.swisschain.contracts.common.RequestContext;

import java.util.Objects;

/** Represents a smart contract invocation context. */
public class SmartContractInvocationContext {
  private String document;
  private String documentVersion;
  private String signature;
  private String smartContractName;
  private String codeHash;
  private String component;
  private String referenceId;
  private RequestContext requestContext;

  public SmartContractInvocationContext() {}

  public SmartContractInvocationContext(
      String document,
      String documentVersion,
      String signature,
      String smartContractName,
      String codeHash,
      String component,
      String referenceId,
      RequestContext requestContext) {
    this.document = document;
    this.documentVersion = documentVersion;
    this.signature = signature;
    this.smartContractName = smartContractName;
    this.codeHash = codeHash;
    this.component = component;
    this.referenceId = referenceId;
    this.requestContext = requestContext;
  }

  /** Gets the initial document in JSON format signed by initiator. */
  public String getDocument() {
    return document;
  }

  /** Sets the initial document in JSON format signed by initiator. */
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

  /** Gets the initial document signature. */
  public String getSignature() {
    return signature;
  }

  /** Sets the initial document signature. */
  public void setSignature(String signature) {
    this.signature = signature;
  }

  /** Gets the smart contract name. */
  public String getSmartContractName() {
    return smartContractName;
  }

  /** Sets the smart contract name. */
  public void setSmartContractName(String smartContractName) {
    this.smartContractName = smartContractName;
  }

  /** Gets the smart contract code SHA256 hash. */
  public String getCodeHash() {
    return codeHash;
  }

  /** Sets the smart contract code SHA256 hash. */
  public void setCodeHash(String codeHash) {
    this.codeHash = codeHash;
  }

  /** Gets the Sirius component that was originated smart contract deployment. */
  public String getComponent() {
    return component;
  }

  /** Sets the Sirius component that was originated smart contract deployment. */
  public void setComponent(String component) {
    this.component = component;
  }

  /** Gets the smart contract reference identifier in external system. */
  public String getReferenceId() {
    return referenceId;
  }

  /** Sets the smart contract reference identifier in external system. */
  public void setReferenceId(String referenceId) {
    this.referenceId = referenceId;
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
    SmartContractInvocationContext that = (SmartContractInvocationContext) o;
    return Objects.equals(document, that.document)
        && Objects.equals(documentVersion, that.documentVersion)
        && Objects.equals(signature, that.signature)
        && Objects.equals(smartContractName, that.smartContractName)
        && Objects.equals(codeHash, that.codeHash)
        && Objects.equals(component, that.component)
        && Objects.equals(referenceId, that.referenceId)
        && Objects.equals(requestContext, that.requestContext);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        document,
        documentVersion,
        signature,
        smartContractName,
        codeHash,
        component,
        referenceId,
        requestContext);
  }
}
