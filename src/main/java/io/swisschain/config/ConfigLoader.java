package io.swisschain.config;

import io.swisschain.config.rest.RestConfigLoader;

public class ConfigLoader {
  public static Config loadConfig() {
    final var configLocation = System.getenv("VAULT_CONFIG");
    if (configLocation == null || configLocation.isEmpty() || configLocation.equals("secure")) {
      return RestConfigLoader.loadConfig();
    }
    if (configLocation.startsWith("http")) {
      return RemoteConfigLoader.loadConfig(configLocation);
    } else {
      return LocalConfigLoader.loadConfig(configLocation);
    }
  }
}
