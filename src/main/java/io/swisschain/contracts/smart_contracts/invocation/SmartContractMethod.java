package io.swisschain.contracts.smart_contracts.invocation;

import java.util.Objects;

/** Represents a smart contract method. */
public class SmartContractMethod {
  private String name;
  private String address;

  public SmartContractMethod() {}

  public SmartContractMethod(String name, String address) {
    this.name = name;
    this.address = address;
  }

  /** Gets the name. */
  public String getName() {
    return name;
  }

  /** Sets the name. */
  public void setName(String name) {
    this.name = name;
  }

  /** Gets the address. */
  public String getAddress() {
    return address;
  }

  /** Sets the address. */
  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    var that = (SmartContractMethod) o;
    return name.equals(that.name) && Objects.equals(address, that.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, address);
  }
}
