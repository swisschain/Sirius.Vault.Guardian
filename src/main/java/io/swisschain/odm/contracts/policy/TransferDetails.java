package io.swisschain.odm.contracts.policy;

import java.math.BigDecimal;

public class TransferDetails {
  public long operationId;
  public Blockchain blockchain;
  public Asset asset;
  public SourceAddress sourceAddress;
  public DestinationAddress destinationAddress;
  public BigDecimal amount;
  public BigDecimal feeLimit;

  public TransferDetails() {}

  public TransferDetails(
      long operationId,
      Blockchain blockchain,
      Asset asset,
      SourceAddress sourceAddress,
      DestinationAddress destinationAddress,
      BigDecimal amount,
      BigDecimal feeLimit) {
    this.operationId = operationId;
    this.blockchain = blockchain;
    this.asset = asset;
    this.sourceAddress = sourceAddress;
    this.destinationAddress = destinationAddress;
    this.amount = amount;
    this.feeLimit = feeLimit;
  }
}
