package io.swisschain.domain.document;

public class SignatureValidationResult {
  private final boolean isValid;
  private final String reason;

  private SignatureValidationResult(boolean isValid, String reason) {
    this.isValid = isValid;
    this.reason = reason;
  }

  public boolean isValid() {
    return isValid;
  }

  public String getReason() {
    return reason;
  }

  public static SignatureValidationResult CreateValid() {
    return new SignatureValidationResult(true, null);
  }

  public static SignatureValidationResult CreateInvalid(String reason) {
    return new SignatureValidationResult(false, reason);
  }
}
