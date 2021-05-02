package io.swisschain.contracts.common;

import java.util.Objects;

/** Represents an user. */
public class User {
  private long id;
  private String nativeId;

  public User() {}

  public User(long id, String nativeId) {
    this.id = id;
    this.nativeId = nativeId;
  }

  /** Gets the identifier. */
  public long getId() {
    return id;
  }

  /** Sets the identifier. */
  public void setId(long id) {
    this.id = id;
  }

  /** Gets the identifier in external system. */
  public String getNativeId() {
    return nativeId;
  }

  /** Sets the identifier in external system. */
  public void setNativeId(String nativeId) {
    this.nativeId = nativeId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return id == user.id && Objects.equals(nativeId, user.nativeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nativeId);
  }
}
