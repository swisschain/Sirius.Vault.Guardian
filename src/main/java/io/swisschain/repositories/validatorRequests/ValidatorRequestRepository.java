package io.swisschain.repositories.validatorRequests;

import io.swisschain.domain.validators.ValidatorRequest;
import java.util.List;

public interface ValidatorRequestRepository {

  List<ValidatorRequest> getByTransferValidationRequestId(long transferValidationRequestId)
      throws Exception;

  ValidatorRequest get(String validatorId, long transferValidationRequestId) throws Exception;

  void insert(ValidatorRequest validatorRequest) throws Exception;
}
