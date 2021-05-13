package io.swisschain.contracts.smart_contracts.deployment;

import io.swisschain.contracts.common.BrokerAccount;

import java.util.Objects;

/** Represents a deployer information. */
public class SmartContractDeployer {
  private String address;
  private BrokerAccount brokerAccount;

  public SmartContractDeployer() {}

  public SmartContractDeployer(String address, BrokerAccount brokerAccount) {
    this.address = address;
    this.brokerAccount = brokerAccount;
  }

  /** Gets the broker account address. */
  public String getAddress() {
    return address;
  }

  /** Sets the broker account address. */
  public void setAddress(String address) {
    this.address = address;
  }

  /** Gets the broker account. */
  public BrokerAccount getBrokerAccount() {
    return brokerAccount;
  }

  /** Sets the broker account. */
  public void setBrokerAccount(BrokerAccount brokerAccount) {
    this.brokerAccount = brokerAccount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    var that = (SmartContractDeployer) o;
    return address.equals(that.address) && brokerAccount.equals(that.brokerAccount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, brokerAccount);
  }
}
