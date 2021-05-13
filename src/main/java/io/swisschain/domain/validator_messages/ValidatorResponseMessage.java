package io.swisschain.domain.validator_messages;

public class ValidatorResponseMessage {
  private MessageType type;
  private String document;
  private String signature;

  public ValidatorResponseMessage() {}

  public ValidatorResponseMessage(MessageType type, String document, String signature) {
    this.type = type;
    this.document = document;
    this.signature = signature;
  }

  public MessageType getType() {
    return type;
  }

  public void setType(MessageType type) {
    this.type = type;
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
}
