package io.swisschain.http_handlers;

import com.google.common.net.HttpHeaders;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import io.swisschain.common.AppVersion;
import io.swisschain.http_handlers.responses.is_alive.IsAliveResponse;
import io.swisschain.services.JsonSerializer;

import java.io.IOException;
import java.net.HttpURLConnection;

public class IsAliveHttpHandler implements HttpHandler {

  private final JsonSerializer jsonSerializer;

  public IsAliveHttpHandler(JsonSerializer jsonSerializer) {
    this.jsonSerializer = jsonSerializer;
  }

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
      result = jsonSerializer.serialize(response);
    } catch (Exception exception) {
      exception.printStackTrace();
      result = "";
    }
    return result;
  }
}
