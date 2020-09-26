package io.swisschain.config.rest;

import io.swisschain.config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class RestConfigLoader {
  private static final Logger logger = LogManager.getLogger();

  public static Config loadConfig() {
    logger.info("Starting http server to upload config");
    try {
      final var queue = new ArrayBlockingQueue<Config>(1);
      final var httpServer = new ConfigUploadServer(8070, queue);
      httpServer.start();
      Config config;
      while ((config = queue.poll(1, TimeUnit.MINUTES)) == null) {
        logger.info("Waiting for config upload");
      }
      httpServer.stop();
      return config;
    } catch (IOException | InterruptedException e) {
      logger.error("Unable to load config file due to " + e.getMessage(), e);
    }
    return null;
  }
}
