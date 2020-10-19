package io.swisschain.contracts;

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

  public boolean equal(Asset asset) {
    return asset != null
        && getId() == asset.getId()
        && compare(getSymbol(), asset.getSymbol())
        && compare(getAddress(), asset.getAddress());
  }

  private boolean compare(String a, String b) {
    if (a == null && b == null) {
      return true;
    }

    if (a == null || b == null) {
      return false;
    }

    return a.equals(b);
  }
}
