package io.swisschain.contracts.common;

import java.math.BigDecimal;
import java.util.Objects;

/** Represents an asset amount. */
public class Unit {
  private Asset asset;
  private BigDecimal amount;

  public Unit() {}

  public Unit(Asset asset, BigDecimal amount) {
    this.asset = asset;
    this.amount = amount;
  }

  /** Gets the asset. */
  public Asset getAsset() {
    return asset;
  }

  /** Sets the asset. */
  public void setAsset(Asset asset) {
    this.asset = asset;
  }

  /** Gets the amount. */
  public BigDecimal getAmount() {
    return amount;
  }

  /** Sets the amount. */
  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Unit unit = (Unit) o;
    return asset.equals(unit.asset) && amount.equals(unit.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(asset, amount);
  }
}
