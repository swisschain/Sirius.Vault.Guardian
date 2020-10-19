package io.swisschain.domain.transfers;

public enum TransferValidationRequestStatus {
  New,
  Processing,
  Approved,
  Rejected,
  Cancelled
}
