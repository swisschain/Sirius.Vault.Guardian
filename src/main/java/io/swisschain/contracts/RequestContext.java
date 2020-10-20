package io.swisschain.contracts;

import java.time.Instant;
import java.util.Objects;

public class RequestContext {
  private String userId;
  private String apiKeyId;
  private String ip;
  private Instant timestamp;

  public  RequestContext() {}

  public RequestContext(String userId, String apiKeyId, String ip, Instant timestamp) {
    this.userId = userId;
    this.apiKeyId = apiKeyId;
    this.ip = ip;
    this.timestamp = timestamp;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getApiKeyId() {
    return apiKeyId;
  }

  public void setApiKeyId(String apiKeyId) {
    this.apiKeyId = apiKeyId;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public Instant getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Instant timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RequestContext that = (RequestContext) o;
    return Objects.equals(userId, that.userId)
        && Objects.equals(apiKeyId, that.apiKeyId)
        && Objects.equals(ip, that.ip)
        && Objects.equals(timestamp, that.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, apiKeyId, ip, timestamp);
  }
}
