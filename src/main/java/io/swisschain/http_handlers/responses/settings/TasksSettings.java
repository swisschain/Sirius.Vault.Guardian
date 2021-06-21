package io.swisschain.http_handlers.responses.settings;

public class TasksSettings {
  private int validationRequestsPeriodInSeconds;
  private int validatorResponsesPeriodInSeconds;

  public TasksSettings(
      int validationRequestsPeriodInSeconds, int validatorResponsesPeriodInSeconds) {
    this.validationRequestsPeriodInSeconds = validationRequestsPeriodInSeconds;
    this.validatorResponsesPeriodInSeconds = validatorResponsesPeriodInSeconds;
  }

  public int getValidationRequestsPeriodInSeconds() {
    return validationRequestsPeriodInSeconds;
  }

  public int getValidatorResponsesPeriodInSeconds() {
    return validatorResponsesPeriodInSeconds;
  }
}
