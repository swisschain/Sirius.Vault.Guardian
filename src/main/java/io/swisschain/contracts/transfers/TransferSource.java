package io.swisschain.contracts.transfers;

import io.swisschain.contracts.common.Account;
import io.swisschain.contracts.common.BrokerAccount;

import java.util.Objects;

/** Represents the transfer source details. */
public class TransferSource {
  private String address;
  private BrokerAccount brokerAccount;
  private Account account;

  public TransferSource() {}

  public TransferSource(String address, BrokerAccount brokerAccount, Account account) {
    this.address = address;
    this.brokerAccount = brokerAccount;
    this.account = account;
  }

  /** Gets the address. */
  public String getAddress() {
    return address;
  }

  /** Sets the address. */
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

  /** Gets the account. */
  public Account getAccount() {
    return account;
  }

  /** Sets the account. */
  public void setAccount(Account account) {
    this.account = account;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TransferSource that = (TransferSource) o;
    return address.equals(that.address)
        && Objects.equals(brokerAccount, that.brokerAccount)
        && Objects.equals(account, that.account);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, brokerAccount, account);
  }
}
