package io.swisschain.odm.contracts.policy;

public class ValidationResult {
  public String validatedBy;
  public ValidatorResolution resolution;

  public ValidationResult() {
  }

  public ValidationResult(String validatedBy, ValidatorResolution resolution) {
    this.validatedBy = validatedBy;
    this.resolution = resolution;
  }
}
