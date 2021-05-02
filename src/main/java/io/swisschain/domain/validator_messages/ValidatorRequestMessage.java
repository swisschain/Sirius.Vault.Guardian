package io.swisschain.domain.validator_messages;

public class ValidatorRequestMessage {
  private MessageType type;
  private String message;

  public ValidatorRequestMessage() {}

  public ValidatorRequestMessage(MessageType type, String message) {
    this.type = type;
    this.message = message;
  }

  public MessageType getType() {
    return type;
  }

  public void setType(MessageType type) {
    this.type = type;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
