package io.swisschain.config.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import fi.iki.elonen.NanoHTTPD;
import io.swisschain.config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class ConfigUploadServer extends NanoHTTPD {
  private static final Logger logger = LogManager.getLogger();

  private final ObjectMapper mapper = new ObjectMapper();
  private final BlockingQueue<Config> configQueue;

  public ConfigUploadServer(int port, BlockingQueue<Config> configQueue) {
    super(port);
    this.configQueue = configQueue;
  }

  @Override
  public Response serve(IHTTPSession session) {
    final String uri = session.getUri();
    final String command = uri.substring(uri.indexOf("/") + 1);

    if (session.getMethod() != Method.POST) {
      logger.error("Invalid method: " + session.getMethod().name());
      return newFixedLengthResponse("{'success':false,'msg':'Invalid method.'}");
    }

    final String msg;
    if ("setConfig".equals(command)) {
      try {
        final var config = mapper.readValue(session.getInputStream(), Config.class);
        configQueue.put(config);
        return newFixedLengthResponse("{'success':true}");
      } catch (IOException | InterruptedException e) {
        logger.error("Unable to load config : " + e.getMessage(), e);
        return newFixedLengthResponse(
            String.format("{'success':false,'msg':'%s'}", e.getMessage()));
      }
    } else {
      logger.error("Invalid request: " + command);
      msg = "{'success':false,'msg':'Unknown command.'}";
    }

    return newFixedLengthResponse(msg);
  }
}
