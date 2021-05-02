package io.swisschain.odm.contracts.selector;

import io.swisschain.domain.primitives.NetworkType;

public class PolicySelectorRequest {
  public String tenantId;
  public String brokerId;
  public String accountId;
  public String blockchainId;
  public NetworkType networkType;
  public String address;

  public PolicySelectorRequest() {}

  public PolicySelectorRequest(
      String tenantId,
      String brokerId,
      String accountId,
      String blockchainId,
      NetworkType networkType,
      String address) {
    this.tenantId = tenantId;
    this.brokerId = brokerId;
    this.accountId = accountId;
    this.blockchainId = blockchainId;
    this.networkType = networkType;
    this.address = address;
  }
}
