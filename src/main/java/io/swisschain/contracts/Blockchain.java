package io.swisschain.contracts;

import io.swisschain.primitives.NetworkType;

import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Blockchain that = (Blockchain) o;
    return id.equals(that.id)
        && protocolId.equals(that.protocolId)
        && networkType == that.networkType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, protocolId, networkType);
  }
}
