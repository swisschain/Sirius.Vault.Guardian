package io.swisschain.http_handlers.responses.settings;

public class RuleEngineSettings {
  private String type;

  public RuleEngineSettings(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
