package io.swisschain.services;

import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;
import io.swisschain.domain.validators.Validator;

import java.util.List;

public interface ValidatorApiService {
  List<Validator> get(String tenantId) throws OperationFailedException, OperationExhaustedException;

  Validator getById(String tenantId, String validatorId)
      throws OperationFailedException, OperationExhaustedException;
}
