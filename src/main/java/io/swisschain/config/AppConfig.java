package io.swisschain.config;

import io.swisschain.config.clients.GuardianApiConfig;
import io.swisschain.config.clients.VaultApiConfig;
import io.swisschain.config.db.DbConfig;
import io.swisschain.config.validators.ValidatorConfig;

import java.util.List;

public class AppConfig {
  public String instanceName;
  public DbConfig db;
  public VaultApiConfig vaultApi;
  public GuardianApiConfig guardianApi;
  public List<ValidatorConfig> validators;
}
