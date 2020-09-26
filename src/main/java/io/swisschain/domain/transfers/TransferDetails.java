package io.swisschain.domain.transfers;

import java.math.BigDecimal;

public class TransferDetails {
  private long operationId;
  private Blockchain blockchain;
  private Asset asset;
  private SourceAddress sourceAddress;
  private DestinationAddress destinationAddress;
  private BigDecimal amount;
  private BigDecimal feeLimit;
  private ClientContext clientContext;

  public TransferDetails() {}

  public TransferDetails(
      long operationId,
      Blockchain blockchain,
      Asset asset,
      SourceAddress sourceAddress,
      DestinationAddress destinationAddress,
      BigDecimal amount,
      BigDecimal feeLimit,
      ClientContext clientContext) {
    this.operationId = operationId;
    this.blockchain = blockchain;
    this.asset = asset;
    this.sourceAddress = sourceAddress;
    this.destinationAddress = destinationAddress;
    this.amount = amount;
    this.feeLimit = feeLimit;
    this.clientContext = clientContext;
  }

  public long getOperationId() {
    return operationId;
  }

  public void setOperationId(long operationId) {
    this.operationId = operationId;
  }

  public Blockchain getBlockchain() {
    return blockchain;
  }

  public void setBlockchain(Blockchain blockchain) {
    this.blockchain = blockchain;
  }

  public Asset getAsset() {
    return asset;
  }

  public void setAsset(Asset asset) {
    this.asset = asset;
  }

  public SourceAddress getSourceAddress() {
    return sourceAddress;
  }

  public void setSourceAddress(SourceAddress sourceAddress) {
    this.sourceAddress = sourceAddress;
  }

  public DestinationAddress getDestinationAddress() {
    return destinationAddress;
  }

  public void setDestinationAddress(DestinationAddress destinationAddress) {
    this.destinationAddress = destinationAddress;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public BigDecimal getFeeLimit() {
    return feeLimit;
  }

  public void setFeeLimit(BigDecimal feeLimit) {
    this.feeLimit = feeLimit;
  }

  public ClientContext getClientContext() {
    return clientContext;
  }

  public void setClientContext(ClientContext clientContext) {
    this.clientContext = clientContext;
  }
}
