package io.swisschain.domain.validation_requests.transfers;

import io.swisschain.contracts.transfers.Transfer;
import io.swisschain.domain.validation_requests.ValidationRequest;
import io.swisschain.domain.validation_requests.ValidationRequestStatus;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;

public class TransferValidationRequest extends ValidationRequest {
  private Transfer transfer;

  public TransferValidationRequest() {
    super();
  }

  public TransferValidationRequest(
      long id,
      String tenantId,
      long vaultId,
      Transfer transfer,
      ValidationRequestStatus status,
      String document,
      String signature,
      String rejectReasonMessage,
      Instant createdAt,
      Instant updatedAt) {
    super(
        id,
        tenantId,
        vaultId,
        status,
        document,
        signature,
        rejectReasonMessage,
        createdAt,
        updatedAt);
    this.transfer = transfer;
  }

  @NotNull
  public static TransferValidationRequest create(
      long id, String tenantId, long vaultId, Transfer transfer) {
    var createdAt = Instant.now();
    return new TransferValidationRequest(
        id,
        tenantId,
        vaultId,
        transfer,
        ValidationRequestStatus.New,
        null,
        null,
        null,
        createdAt,
        createdAt);
  }

  public Transfer getTransfer() {
    return transfer;
  }

  public void setTransfer(Transfer transfer) {
    this.transfer = transfer;
  }
}
