package io.swisschain.domain.messages;

public class ClientContext {
    private String userId;
    private String apiKeyId;
    private String accountReferenceId;
    private String withdrawalReferenceId;
    private String ip;
    private String timestamp;

    public ClientContext() {}

    public ClientContext(
            String userId,
            String apiKeyId,
            String accountReferenceId,
            String withdrawalReferenceId,
            String ip,
            String timestamp) {
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
