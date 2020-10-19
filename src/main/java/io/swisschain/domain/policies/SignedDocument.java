package io.swisschain.domain.policies;

public class SignedDocument {
  private final String document;
  private final String signature;
  private final String rejectReasonMessage;

  public SignedDocument(String document, String signature) {
    this.document = document;
    this.signature = signature;
    this.rejectReasonMessage = null;
  }

  public SignedDocument(String document, String signature, String rejectReasonMessage) {
    this.document = document;
    this.signature = signature;
    this.rejectReasonMessage = rejectReasonMessage;
  }

  public String getDocument() {
    return document;
  }

  public String getSignature() {
    return signature;
  }

  public String getRejectReasonMessage() {
    return rejectReasonMessage;
  }
}
