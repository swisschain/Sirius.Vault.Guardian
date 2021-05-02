package io.swisschain.contracts.common;

import java.util.Objects;

/** Represents an asset. */
public class Asset {
  private long id;
  private String symbol;
  private String address;

  public Asset() {}

  public Asset(long id, String symbol, String address) {
    this.id = id;
    this.symbol = symbol;
    this.address = address;
  }

  /** Gets the identifier. */
  public long getId() {
    return id;
  }

  /** Sets the identifier. */
  public void setId(long id) {
    this.id = id;
  }

  /** Gets the symbol. */
  public String getSymbol() {
    return symbol;
  }

  /** Sets the symbol. */
  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  /** Gets the blockchain address. */
  public String getAddress() {
    return address;
  }

  /** Sets the blockchain address. */
  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Asset asset = (Asset) o;
    return id == asset.id
        && Objects.equals(symbol, asset.symbol)
        && Objects.equals(address, asset.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, symbol, address);
  }
}
