package io.swisschain.contracts.common;

import java.util.Objects;

/** Represents an account. */
public class Account {
  private long id;
  private String referenceId;
  private User user;

  public Account() {}

  public Account(long id, String referenceId, User user) {
    this.id = id;
    this.referenceId = referenceId;
    this.user = user;
  }

  /** Gets the identifier. */
  public long getId() {
    return id;
  }

  /** Sets the identifier. */
  public void setId(long id) {
    this.id = id;
  }

  /** Gets the reference identifier in external system. */
  public String getReferenceId() {
    return referenceId;
  }

  /** Sets the reference identifier in external system. */
  public void setReferenceId(String referenceId) {
    this.referenceId = referenceId;
  }

  /** Gets the user associated with account. */
  public User getUser() {
    return user;
  }

  /** Sets the user associated with account. */
  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Account account = (Account) o;
    return id == account.id
        && Objects.equals(referenceId, account.referenceId)
        && Objects.equals(user, account.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, referenceId, user);
  }
}
