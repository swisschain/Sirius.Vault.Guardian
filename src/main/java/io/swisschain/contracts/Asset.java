package io.swisschain.contracts;

import java.util.Objects;

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

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Asset asset = (Asset) o;
    return id == asset.id && Objects.equals(symbol, asset.symbol) && address.equals(asset.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, symbol, address);
  }
}
