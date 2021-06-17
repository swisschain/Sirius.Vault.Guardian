package io.swisschain.http_handlers.responses.settings;

public class SettingsResponse {
  private String name;
  private TasksSettings tasks;
  private RuleEngineSettings ruleEngine;
  private PublicKeys keys;

  public SettingsResponse(
      String name, TasksSettings tasks, RuleEngineSettings ruleEngine, PublicKeys keys) {
    this.name = name;
    this.tasks = tasks;
    this.ruleEngine = ruleEngine;
    this.keys = keys;
  }

  public String getName() {
    return name;
  }

  public TasksSettings getTasks() {
    return tasks;
  }

  public RuleEngineSettings getRuleEngine() {
    return ruleEngine;
  }

  public PublicKeys getKeys() {
    return keys;
  }
}
