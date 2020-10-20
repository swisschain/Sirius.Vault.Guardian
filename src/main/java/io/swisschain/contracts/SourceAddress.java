package io.swisschain.contracts;

import java.util.Objects;

public class SourceAddress {
  private String address;
  private String name;
  private String group;

  public SourceAddress() {}

  public SourceAddress(String address, String name, String group) {
    this.address = address;
    this.name = name;
    this.group = group;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SourceAddress that = (SourceAddress) o;
    return address.equals(that.address)
        && Objects.equals(name, that.name)
        && Objects.equals(group, that.group);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, name, group);
  }
}
