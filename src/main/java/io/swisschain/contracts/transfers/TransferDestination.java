package io.swisschain.contracts.transfers;

import io.swisschain.contracts.common.Account;
import io.swisschain.contracts.common.BrokerAccount;
import io.swisschain.domain.primitives.TagType;

import java.util.Objects;

/** Represents the transfer destination details. */
public class TransferDestination {
  private String address;
  private String tag;
  private TagType tagType;
  private BrokerAccount brokerAccount;
  private Account account;

  public TransferDestination() {}

  public TransferDestination(
      String address, String tag, TagType tagType, BrokerAccount brokerAccount, Account account) {
    this.address = address;
    this.tag = tag;
    this.tagType = tagType;
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

  /** Gets the tag. */
  public String getTag() {
    return tag;
  }

  /** Sets the tag. */
  public void setTag(String tag) {
    this.tag = tag;
  }

  /** Gets the tag type. */
  public TagType getTagType() {
    return tagType;
  }

  /** Sets the tag type. */
  public void setTagType(TagType tagType) {
    this.tagType = tagType;
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
    TransferDestination that = (TransferDestination) o;
    return address.equals(that.address)
        && Objects.equals(tag, that.tag)
        && tagType == that.tagType
        && Objects.equals(brokerAccount, that.brokerAccount)
        && Objects.equals(account, that.account);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, tag, tagType, brokerAccount, account);
  }
}
