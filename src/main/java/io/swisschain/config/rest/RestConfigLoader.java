package io.swisschain.config.rest;

import io.swisschain.config.AppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class RestConfigLoader {
  private static final Logger logger = LogManager.getLogger();

  public static AppConfig loadConfig() {
    logger.info("Starting http server to upload config");
    try {
      final var queue = new ArrayBlockingQueue<AppConfig>(1);
      final var httpServer = new ConfigUploadServer(8070, queue);
      httpServer.start();
      AppConfig appConfig;
      while ((appConfig = queue.poll(1, TimeUnit.MINUTES)) == null) {
        logger.info("Waiting for config upload");
      }
      httpServer.stop();
      return appConfig;
    } catch (IOException | InterruptedException e) {
      logger.error("Unable to load config file due to " + e.getMessage(), e);
    }
    return null;
  }
}
