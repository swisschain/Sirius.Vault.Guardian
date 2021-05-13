package io.swisschain.services;

import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;
import io.swisschain.domain.validation_requests.smart_contracts.SmartContractInvocationValidationRequest;

import java.util.List;

public interface SmartContractInvocationApiService {
  List<SmartContractInvocationValidationRequest> get()
      throws OperationFailedException, OperationExhaustedException;

  void confirm(SmartContractInvocationValidationRequest validationRequest)
      throws OperationFailedException, OperationExhaustedException;

  void reject(SmartContractInvocationValidationRequest validationRequest)
      throws OperationFailedException, OperationExhaustedException;
}
