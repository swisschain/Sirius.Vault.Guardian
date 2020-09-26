package io.swisschain.isAlive;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import io.swisschain.utils.AppVersion;

import java.io.IOException;
import java.net.HttpURLConnection;

public class IsAliveHandler implements HttpHandler {

  private final ObjectMapper mapper = new ObjectMapper();

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    exchange.getResponseHeaders().add(HttpHeaders.CONTENT_TYPE, "application/json");
    final var bytes = getResponse().getBytes();
    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, bytes.length);
    exchange.getResponseBody().write(bytes);
  }

  public String getResponse() {
    IsAliveResponse response = new IsAliveResponse();
    response.setName(AppVersion.NAME);
    response.setVersion(AppVersion.VERSION);
    response.setHostname(AppVersion.HOSTNAME);
    response.setStartedAt(AppVersion.STARTED_AT);
    String result;
    try {
      result = mapper.writeValueAsString(response);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      result = "";
    }
    return result;
  }
}
