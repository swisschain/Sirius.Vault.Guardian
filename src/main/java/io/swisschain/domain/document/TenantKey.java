package io.swisschain.domain.document;

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

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  public String getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }
}
