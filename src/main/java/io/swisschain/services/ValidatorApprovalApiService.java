package io.swisschain.services;

import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;
import io.swisschain.domain.validators.ValidatorApproval;
import io.swisschain.domain.validators.ValidatorRequest;

import java.util.List;

public interface ValidatorApprovalApiService {
  List<ValidatorApproval> get() throws OperationFailedException, OperationExhaustedException;

  void confirm(String validatorId, long transferValidationRequestId)
      throws OperationFailedException, OperationExhaustedException;

  void create(String tenantId, long transferValidationRequestId, ValidatorRequest validatorRequest)
      throws OperationFailedException, OperationExhaustedException;
}
