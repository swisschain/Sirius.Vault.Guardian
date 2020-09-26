package io.swisschain.domain.transfers;

import java.time.Instant;

public class ClientContext {
  private String userId;
  private String apiKeyId;
  private String accountReferenceId;
  private String withdrawalReferenceId;
  private String ip;
  private Instant timestamp;

  public ClientContext() {}

  public ClientContext(
      String userId,
      String apiKeyId,
      String accountReferenceId,
      String withdrawalReferenceId,
      String ip,
      Instant timestamp) {
    this.userId = userId;
    this.apiKeyId = apiKeyId;
    this.accountReferenceId = accountReferenceId;
    this.withdrawalReferenceId = withdrawalReferenceId;
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

  public String getAccountReferenceId() {
    return accountReferenceId;
  }

  public void setAccountReferenceId(String accountReferenceId) {
    this.accountReferenceId = accountReferenceId;
  }

  public String getWithdrawalReferenceId() {
    return withdrawalReferenceId;
  }

  public void setWithdrawalReferenceId(String withdrawalReferenceId) {
    this.withdrawalReferenceId = withdrawalReferenceId;
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
}
