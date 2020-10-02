package io.swisschain.config.loaders;

import io.swisschain.config.AppConfig;
import io.swisschain.config.rest.RestConfigLoader;

public class ConfigLoader {
  public static AppConfig loadConfig() {
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
