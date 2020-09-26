package io.swisschain.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class LocalConfigLoader {
  private static final String DEFAULT_LOCAL_FILE_NAME = "settings.json";
  private static final Logger logger = LogManager.getLogger();

  public static Config loadConfig(String url) {
    final var mapper = new ObjectMapper();
    try {
      if (url.equals("local")) {
        logger.info("Loading default config from resources");
        final var inputStream =
            LocalConfigLoader.class.getClassLoader().getResourceAsStream(DEFAULT_LOCAL_FILE_NAME);
        return mapper.readValue(inputStream, Config.class);
      } else {
        logger.info("Loading local config from " + url);
        final var inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(url)));
        return mapper.readValue(inputStream, Config.class);
      }
    } catch (IOException e) {
      logger.error("Unable to load local config file due to " + e.getMessage(), e);
    }
    return null;
  }
}
