package io.swisschain.repositories.validatorResponses;

import io.swisschain.domain.validators.ValidatorResponse;

import java.util.List;

public interface ValidatorResponseRepository {
  List<ValidatorResponse> getByTransferValidationRequestId(long transferValidationRequestId)
      throws Exception;

  void insert(ValidatorResponse validatorResponse) throws Exception;
}
