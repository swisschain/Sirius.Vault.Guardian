package io.swisschain.http_handlers.responses.settings;

import java.util.List;

public class PublicKeys {

  private final String settings;
  private final String guardian;
  private final String customer;
  private List<TenantKey> tenants;

  public PublicKeys(String settings, String guardian, String customer, List<TenantKey> tenants) {
    this.settings = settings;
    this.guardian = guardian;
    this.customer = customer;
    this.tenants = tenants;
  }

  public String getSettings() {
    return settings;
  }

  public String getGuardian() {
    return guardian;
  }

  public String getCustomer() {
    return customer;
  }

  public List<TenantKey> getTenants() {
    return tenants;
  }
}
