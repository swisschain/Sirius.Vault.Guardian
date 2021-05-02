package io.swisschain.contracts.common;

import java.time.Instant;
import java.util.Objects;

/** Represents an initial operation request. */
public class RequestContext {
  private String userId;
  private String apiKeyId;
  private String ip;
  private Instant timestamp;

  public RequestContext() {}

  public RequestContext(String userId, String apiKeyId, String ip, Instant timestamp) {
    this.userId = userId;
    this.apiKeyId = apiKeyId;
    this.ip = ip;
    this.timestamp = timestamp;
  }

  /** Gets the user identifier if request initiated by user, otherwise null. */
  public String getUserId() {
    return userId;
  }

  /** Sets the user identifier. */
  public void setUserId(String userId) {
    this.userId = userId;
  }

  /** Gets the API key identifier if request initiated thought API, otherwise null. */
  public String getApiKeyId() {
    return apiKeyId;
  }

  /** Sets the API key identifier. */
  public void setApiKeyId(String apiKeyId) {
    this.apiKeyId = apiKeyId;
  }

  /** Gets the IP address the request initiated from. */
  public String getIp() {
    return ip;
  }

  /** Sets the IP address. */
  public void setIp(String ip) {
    this.ip = ip;
  }

  /** Gets the date and time when request received. */
  public Instant getTimestamp() {
    return timestamp;
  }

  /** Sets the date and time when request received. */
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
