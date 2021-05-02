package io.swisschain.repositories.validator_requests;

import io.swisschain.domain.validators.ValidatorRequest;
import io.swisschain.domain.validators.ValidatorRequestType;

import java.util.List;

public interface ValidatorRequestRepository {

  ValidatorRequest getById(String validatorRequestId) throws Exception;

  List<ValidatorRequest> getByValidationRequestId(
      long validationRequestId, ValidatorRequestType requestType) throws Exception;

  void insert(ValidatorRequest validatorRequest) throws Exception;

  void update(ValidatorRequest validatorRequest) throws Exception;
}
