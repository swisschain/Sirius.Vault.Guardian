package io.swisschain.config;

import io.swisschain.config.clients.GuardianApiConfig;
import io.swisschain.config.clients.VaultApiConfig;
import io.swisschain.config.db.DbConfig;

public class AppConfig {
  public String instanceName;
  public String privateKey;
  public DbConfig db;
  public VaultApiConfig vaultApi;
  public GuardianApiConfig guardianApi;
}
