package io.swisschain.odm;

import io.swisschain.odm.contracts.policy.*;
import io.swisschain.odm.contracts.selector.PolicySelectorRequest;
import io.swisschain.odm.contracts.selector.PolicySelectorRequestWrapper;
import io.swisschain.odm.contracts.selector.PolicySelectorResponse;
import io.swisschain.odm.contracts.selector.PolicySelectorResponseWrapper;
import io.swisschain.primitives.NetworkType;
import io.swisschain.primitives.TagType;
import io.swisschain.services.JsonSerializer;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

public class OdmClient {
  private static final Logger logger = LogManager.getLogger();

  private final String baseUrl;
  private final String policySelectorUrl;
  private final JsonSerializer jsonSerializer;

  public OdmClient(String baseUrl, String policySelectorPath, JsonSerializer jsonSerializer) {
    this.jsonSerializer = jsonSerializer;
    if (baseUrl == null || baseUrl.isEmpty()) {
      throw new IllegalArgumentException("Base url required.");
    }

    if (baseUrl.charAt(baseUrl.length() - 1) == '/') {
      this.baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
    } else {
      this.baseUrl = baseUrl;
    }

    if (policySelectorPath.charAt(0) == '/') {
      this.policySelectorUrl = combine(this.baseUrl, policySelectorPath.substring(1));
    } else {
      this.policySelectorUrl = combine(this.baseUrl, policySelectorPath);
    }
  }

  private static String combine(String baseUrl, String relativeUrl) {
    if (relativeUrl == null || relativeUrl.isEmpty()) {
      return baseUrl;
    }

    if (relativeUrl.charAt(0) == '/') {
      return baseUrl + relativeUrl;
    }

    return baseUrl + "/" + relativeUrl;
  }

  public PolicySelectorResponse getPolicy(
      PolicySelectorRequest policySelectorRequest, String requestId) throws Exception {

    final var json =
        jsonSerializer.serialize(
            new PolicySelectorRequestWrapper(requestId, policySelectorRequest));

    final var client = new OkHttpClient();
    final var request =
        new Request.Builder()
            .url(policySelectorUrl)
            .method("POST", RequestBody.create(json, MediaType.parse("application/json")))
            .build();

    final var response = client.newCall(request).execute();

    if (response.body() == null) {
      logger.error(String.format("Unable to get policy. %s", response.toString()));
      return null;
    }

    var result = jsonSerializer.deserialize(
        Objects.requireNonNull(response.body()).string(), PolicySelectorResponseWrapper.class);

    return result.response;
  }

  public PolicyResponse getResolution(
      PolicyRequest policyRequest,
      String requestId,
      String policyService,
      String policyServiceVersion,
      String policy,
      String policyVersion)
      throws Exception {
    final var json = jsonSerializer.serialize(new PolicyRequestWrapper(requestId, policyRequest));

    var relativeUrl = policyService;

    if (policyServiceVersion != null && !policyServiceVersion.isEmpty()) {
      relativeUrl = relativeUrl + "/" + policyServiceVersion;
    }

    relativeUrl = relativeUrl + "/" + policy;

    if (policyVersion != null && !policyVersion.isEmpty()) {
      relativeUrl = relativeUrl + "/" + policyVersion;
    }

    final var client = new OkHttpClient();
    final var request =
        new Request.Builder()
            .url(combine(baseUrl, relativeUrl))
            .method("POST", RequestBody.create(json, MediaType.parse("application/json")))
            .build();

    final var response = client.newCall(request).execute();

    if (response.body() == null) {
      logger.error(String.format("Unable to get policy resolution. %s", response.toString()));
      return null;
    }

    var result = jsonSerializer.deserialize(
        Objects.requireNonNull(response.body()).string(), PolicyResponseWrapper.class);

    return result.response;
  }
}
