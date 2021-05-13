package io.swisschain.repositories.smart_contract_invocation_validation_requests;

import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;
import io.swisschain.domain.validation_requests.smart_contracts.SmartContractInvocationValidationRequest;

import java.sql.SQLException;

public interface SmartContractInvocationValidationRequestRepository {

  SmartContractInvocationValidationRequest getById(long smartContractInvocationValidationRequestId)
      throws SQLException, OperationFailedException, OperationExhaustedException;

  void insert(SmartContractInvocationValidationRequest smartContractInvocationValidationRequest)
      throws Exception;

  void update(SmartContractInvocationValidationRequest smartContractInvocationValidationRequest)
      throws Exception;
}
