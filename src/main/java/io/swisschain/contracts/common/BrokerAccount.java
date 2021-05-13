package io.swisschain.contracts.common;

import java.util.Objects;

/** Represents a broker account. */
public class BrokerAccount {
  private long id;
  private String name;

  public BrokerAccount() {}

  public BrokerAccount(long id, String name) {
    this.id = id;
    this.name = name;
  }

  /** Gets the identifier. */
  public long getId() {
    return id;
  }

  /** Sets the identifier. */
  public void setId(long id) {
    this.id = id;
  }

  /** Gets the name. */
  public String getName() {
    return name;
  }

  /** Sets the name. */
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BrokerAccount that = (BrokerAccount) o;
    return id == that.id && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}
