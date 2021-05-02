package io.swisschain.services;

import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;

public interface ValidatorRequestApiService {
  void create(
      String tenantId,
      String validationRequestId,
      String validatorId,
      String message,
      String key,
      String nonce)
      throws OperationFailedException, OperationExhaustedException;

  void confirm(String validatorId, String validationRequestId)
      throws OperationFailedException, OperationExhaustedException;
}
