package io.swisschain.config;

import io.swisschain.config.clients.GuardianApiConfig;
import io.swisschain.config.clients.VaultApiConfig;
import io.swisschain.config.db.DbConfig;
import io.swisschain.config.policy.OdmConfig;
import io.swisschain.config.tasks.TasksConfig;

public class AppConfig {
  public String instanceName;
  public String privateKey;
  public DbConfig db;
  public VaultApiConfig vaultApi;
  public GuardianApiConfig guardianApi;
  public TasksConfig tasks;
  public OdmConfig odm;
}
