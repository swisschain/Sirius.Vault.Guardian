package io.swisschain.http_handlers.responses.settings;

public class TenantKey {
  private String tenantId;
  private String publicKey;

  public TenantKey(String tenantId, String publicKey) {
    this.tenantId = tenantId;
    this.publicKey = publicKey;
  }

  public String getTenantId() {
    return tenantId;
  }

  public String getPublicKey() {
    return publicKey;
  }
}
