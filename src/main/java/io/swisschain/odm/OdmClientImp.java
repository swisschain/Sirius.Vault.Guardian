package io.swisschain.odm;

import io.swisschain.contracts.documents.ValidatorResolution;
import io.swisschain.odm.models.DecisionResponse;
import io.swisschain.odm.models.RequestWrapper;
import io.swisschain.services.JsonSerializer;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;

public abstract class OdmClientImp<T> implements OdmClient<T> {
  private static final Logger logger = LogManager.getLogger();

  private final String url;
  protected final JsonSerializer jsonSerializer;

  public OdmClientImp(String url, JsonSerializer jsonSerializer) {
    if (url == null || url.isEmpty()) {
      throw new IllegalArgumentException("Url required.");
    }
    this.url = url;
    this.jsonSerializer = jsonSerializer;
  }

  public DecisionResponse getDecision(T data, List<ValidatorResolution> validatorResolutions)
      throws Exception {
    final var json = jsonSerializer.serialize(wrap(data, validatorResolutions));

    final var client = new OkHttpClient();
    final var request =
        new Request.Builder()
            .url(url)
            .method("POST", RequestBody.create(json, MediaType.parse("application/json")))
            .build();

    final var response = client.newCall(request).execute();

    if (response.body() == null) {
      logger.error(String.format("Unable to get policy resolution. %s", response.toString()));
      return null;
    }

    return getResponse(Objects.requireNonNull(response.body()).string());
  }

  protected abstract RequestWrapper wrap(T data, List<ValidatorResolution> validatorResolutions);

  protected abstract DecisionResponse getResponse(String data) throws Exception;
}
