package io.swisschain.config.loaders;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swisschain.config.AppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class RemoteConfigLoader {
  private static final Logger logger = LogManager.getLogger();

  public static AppConfig loadConfig(String url) {
    final var mapper = new ObjectMapper();
    try {
      logger.info("Loading remote config from " + url);
      final var inputStream =
          new BufferedReader(new InputStreamReader(new URL(url).openConnection().getInputStream()));
      return mapper.readValue(inputStream, AppConfig.class);
    } catch (IOException e) {
      logger.error("Unable to load remote config file due to " + e.getMessage(), e);
    }
    return null;
  }
}
