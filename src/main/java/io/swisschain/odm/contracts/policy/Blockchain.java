package io.swisschain.odm.contracts.policy;

import io.swisschain.primitives.NetworkType;

public class Blockchain {
  public String id;
  public String protocolId;
  public NetworkType networkType;

  public Blockchain() {}

  public Blockchain(String id, String protocolId, NetworkType networkType) {
    this.id = id;
    this.protocolId = protocolId;
    this.networkType = networkType;
  }
}
