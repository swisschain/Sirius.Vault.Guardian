package io.swisschain.odm.contracts.policy;

public class SourceAddress {
  public String address;
  public String name;
  public String group;

  public SourceAddress() {}

  public SourceAddress(String address, String name, String group) {
    this.address = address;
    this.name = name;
    this.group = group;
  }
}
