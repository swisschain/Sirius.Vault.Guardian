package io.swisschain.config;

import java.util.List;

public class Config {
  public String instanceName;
  public DbConfig db;
  public VaultApiConfig vaultApi;
  public GuardianApiConfig guardianApi;
  public List<KeyKeeperConfig> keyKeepers;
  public List<PolicyConfig> policies;
}
