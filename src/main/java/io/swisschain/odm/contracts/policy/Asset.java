package io.swisschain.odm.contracts.policy;

public class Asset {
  public long id;
  public String symbol;
  public String address;

  public Asset() {
  }

  public Asset(long id, String symbol, String address) {
    this.id = id;
    this.symbol = symbol;
    this.address = address;
  }
}
