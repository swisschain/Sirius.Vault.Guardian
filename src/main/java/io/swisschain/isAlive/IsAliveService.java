package io.swisschain.isAlive;

import com.sun.net.httpserver.HttpServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;

public class IsAliveService extends Thread {
  private static final Logger logger = LogManager.getLogger();
  private static final String PATH = "/api/isalive";

  private final int port;

  public IsAliveService(int port) {
    this.port = port;
  }

  @Override
  public void run() {
    logger.info(String.format("Starting isAlive server: port: %d, path: %s", port, PATH));
    try {
      final var server = HttpServer.create();
      server.bind(new InetSocketAddress(port), 0);
      server.createContext(PATH, new IsAliveHandler());
      server.start();
      logger.info(String.format("Started isAlive server: port: %d, path: %s", port, PATH));
    } catch (IOException e) {
      logger.info(
          String.format(
              "Unable to start isAlive server: port: %d, path: %s. Reason: %s",
              port, PATH, e.getMessage()),
          e);
    }
  }
}
