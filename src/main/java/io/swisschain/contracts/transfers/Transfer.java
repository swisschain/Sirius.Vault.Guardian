package io.swisschain.contracts.transfers;

import io.swisschain.contracts.common.Blockchain;
import io.swisschain.contracts.common.Unit;

import java.util.Objects;

/** Represents the transfer details and original context of the initial client request. */
public class Transfer {
  private long id;
  private Blockchain blockchain;
  private TransferSource source;
  private TransferDestination destination;
  private Unit value;
  private Unit fee;
  private TransferContext context;

  public Transfer() {}

  public Transfer(
      long id,
      Blockchain blockchain,
      TransferSource source,
      TransferDestination destination,
      Unit value,
      Unit fee,
      TransferContext context) {
    this.id = id;
    this.blockchain = blockchain;
    this.source = source;
    this.destination = destination;
    this.value = value;
    this.fee = fee;
    this.context = context;
  }

  /** Gets the identifier in Sirius. */
  public long getId() {
    return id;
  }

  /** Sets the identifier in Sirius. */
  public void setId(long id) {
    this.id = id;
  }

  /** Gets the blockchain details. */
  public Blockchain getBlockchain() {
    return blockchain;
  }

  /** Sets the blockchain details. */
  public void setBlockchain(Blockchain blockchain) {
    this.blockchain = blockchain;
  }

  public TransferSource getSource() {
    return source;
  }

  public void setSource(TransferSource source) {
    this.source = source;
  }

  public TransferDestination getDestination() {
    return destination;
  }

  public void setDestination(TransferDestination destination) {
    this.destination = destination;
  }

  public Unit getValue() {
    return value;
  }

  public void setValue(Unit value) {
    this.value = value;
  }

  public Unit getFee() {
    return fee;
  }

  public void setFee(Unit fee) {
    this.fee = fee;
  }

  /** Gets the transfer detailed information and initial client request. */
  public TransferContext getContext() {
    return context;
  }

  /** Sets the transfer detailed information and initial client request. */
  public void setContext(TransferContext context) {
    this.context = context;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Transfer transfer = (Transfer) o;
    return id == transfer.id
        && Objects.equals(blockchain, transfer.blockchain)
        && Objects.equals(source, transfer.source)
        && Objects.equals(destination, transfer.destination)
        && Objects.equals(value, transfer.value)
        && Objects.equals(fee, transfer.fee)
        && Objects.equals(context, transfer.context);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, blockchain, source, destination, value, fee, context);
  }
}
