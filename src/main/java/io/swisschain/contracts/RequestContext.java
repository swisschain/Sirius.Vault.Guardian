package io.swisschain.contracts;

import java.time.Instant;

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

  public boolean equal(RequestContext requestContext) {
    return requestContext != null
        && compare(getUserId(), requestContext.getUserId())
        && compare(getApiKeyId(), requestContext.getApiKeyId())
        && compare(getIp(), requestContext.getIp())
        && compare(getTimestamp(), requestContext.getTimestamp());
  }

  private boolean compare(String a, String b) {
    if (a == null && b == null) {
      return true;
    }

    if (a == null || b == null) {
      return false;
    }

    return a.equals(b);
  }

  private boolean compare(Instant a, Instant b) {
    if (a == null && b == null) {
      return true;
    }

    if (a == null || b == null) {
      return false;
    }

    return a.equals(b);
  }
}
