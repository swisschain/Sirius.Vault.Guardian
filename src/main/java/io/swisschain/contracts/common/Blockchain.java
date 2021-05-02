package io.swisschain.contracts.common;

import io.swisschain.domain.primitives.NetworkType;

import java.util.Objects;

/** Represents a blockchain. */
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

  /** Gets the identifier. */
  public String getId() {
    return id;
  }

  /** Sets the identifier. */
  public void setId(String id) {
    this.id = id;
  }

  /** Gets the protocol identifier. */
  public String getProtocolId() {
    return protocolId;
  }

  /** Sets the protocol identifier. */
  public void setProtocolId(String protocolId) {
    this.protocolId = protocolId;
  }

  /** Gets the network type. */
  public NetworkType getNetworkType() {
    return networkType;
  }

  /** Sets the network type. */
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
