package io.swisschain.services;

import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;
import io.swisschain.domain.transfers.TransferValidationRequest;

import java.util.List;

public interface TransferApiService {
  List<TransferValidationRequest> get()
      throws OperationFailedException, OperationExhaustedException;

  void confirm(TransferValidationRequest transferValidationRequest)
      throws OperationFailedException, OperationExhaustedException;

  void reject(TransferValidationRequest transferValidationRequest)
      throws OperationFailedException, OperationExhaustedException;
}
