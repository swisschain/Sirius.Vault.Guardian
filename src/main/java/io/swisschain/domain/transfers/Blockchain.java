package io.swisschain.domain.transfers;

import io.swisschain.primitives.NetworkType;

public class Blockchain {
  private String id;
  private String protocolId;
  private NetworkType networkType;

  public Blockchain() {}

  public Blockchain(String id, String protocolId, NetworkType networkType) {
    this.id = id;
    this.protocolId = protocolId;
    this.networkType = networkType;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getProtocolId() {
    return protocolId;
  }

  public void setProtocolId(String protocolId) {
    this.protocolId = protocolId;
  }

  public NetworkType getNetworkType() {
    return networkType;
  }

  public void setNetworkType(NetworkType networkType) {
    this.networkType = networkType;
  }
}
