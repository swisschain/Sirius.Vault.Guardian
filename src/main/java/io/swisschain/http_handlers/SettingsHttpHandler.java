package io.swisschain.http_handlers;

import com.google.common.net.HttpHeaders;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import io.swisschain.http_handlers.responses.settings.*;
import io.swisschain.services.JsonSerializer;
import io.swisschain.services.SettingsKeysService;
import io.swisschain.services.SettingsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class SettingsHttpHandler implements HttpHandler {

  private final Logger logger = LogManager.getLogger();

  private final SettingsService settingsService;
  private final SettingsKeysService settingsKeysService;
  private final JsonSerializer jsonSerializer;

  public SettingsHttpHandler(
      SettingsService settingsService,
      SettingsKeysService settingsKeysService,
      JsonSerializer jsonSerializer) {
    this.settingsService = settingsService;
    this.settingsKeysService = settingsKeysService;
    this.jsonSerializer = jsonSerializer;
  }

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    exchange.getResponseHeaders().add(HttpHeaders.CONTENT_TYPE, "application/json");
    if ("GET".equals(exchange.getRequestMethod())) {
      final var bytes = handleGet().getBytes();
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, bytes.length);
      exchange.getResponseBody().write(bytes);
    } else if ("POST".equals(exchange.getRequestMethod())) {
      handlePost(exchange.getRequestBody());
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_NO_CONTENT, -1);
    }
  }

  private String handleGet() {
    var appConfig = settingsService.get();

    SettingsResponse response;

    if (appConfig == null) {
      response =
          new SettingsResponse(
              null, null, null, new PublicKeys(settingsKeysService.getPublic(), null, null, null));
    } else {
      var tenantKeys = new ArrayList<TenantKey>();
      for (var tenantKey : appConfig.keys.tenants) {
        tenantKeys.add(new TenantKey(tenantKey.tenantId, tenantKey.publicKey));
      }
      response =
          new SettingsResponse(
              appConfig.name,
              new TasksSettings(
                  appConfig.tasks.validationRequestsPeriodInSeconds,
                  appConfig.tasks.validationRequestsPeriodInSeconds),
              new RuleEngineSettings(
                  appConfig.clients.odmApi.enable ? "IBM ODM" : "Simple Rule Engine"),
              new PublicKeys(
                  settingsKeysService.getPublic(),
                  appConfig.keys.guardian.publicKey,
                  appConfig.keys.customer.publicKey,
                  tenantKeys));
    }

    String result;
    try {
      result = jsonSerializer.serialize(response);
    } catch (Exception exception) {
      logger.error("An error occurred while processing get settings request.", exception);
      result = "{}";
    }
    return result;
  }

  private void handlePost(InputStream inputStream) {
    try {
      var secureSettings = jsonSerializer.deserialize(inputStream, SecureSettingsRequest.class);
      settingsService.set(
          secureSettings.getKey(), secureSettings.getNonce(), secureSettings.getSettings());
    } catch (Exception exception) {
      logger.error("An error occurred while processing set settings request.", exception);
    }
  }
}
