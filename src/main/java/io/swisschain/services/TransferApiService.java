package io.swisschain.services;

import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;
import io.swisschain.domain.validation_requests.transfers.TransferValidationRequest;

import java.util.List;

public interface TransferApiService {
  List<TransferValidationRequest> get()
      throws OperationFailedException, OperationExhaustedException;

  void confirm(TransferValidationRequest validationRequest)
      throws OperationFailedException, OperationExhaustedException;

  void reject(TransferValidationRequest validationRequest)
      throws OperationFailedException, OperationExhaustedException;
}
