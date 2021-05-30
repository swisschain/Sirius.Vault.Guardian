package io.swisschain.services;

import io.swisschain.domain.exceptions.OperationException;
import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;
import io.swisschain.domain.validators.ValidatorResponse;

import java.util.List;

public interface ValidatorResponseApiService {
  List<ValidatorResponse> get()
      throws OperationFailedException, OperationExhaustedException, OperationException;
}
