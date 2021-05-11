package io.swisschain.services;

import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;
import io.swisschain.domain.validation_requests.smart_contracts.SmartContractDeploymentValidationRequest;

import java.util.List;

public interface SmartContractDeploymentApiService {
  List<SmartContractDeploymentValidationRequest> get()
      throws OperationFailedException, OperationExhaustedException;

  void confirm(SmartContractDeploymentValidationRequest validationRequest)
      throws OperationFailedException, OperationExhaustedException;

  void reject(SmartContractDeploymentValidationRequest validationRequest)
      throws OperationFailedException, OperationExhaustedException;
}
