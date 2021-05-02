package io.swisschain.repositories.smart_contract_deployment_validation_requests;

import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;
import io.swisschain.domain.validation_requests.smart_contract_deployments.SmartContractDeploymentValidationRequest;

import java.sql.SQLException;

public interface SmartContractDeploymentValidationRequestRepository {

  SmartContractDeploymentValidationRequest getById(long smartContractDeploymentValidationRequestId)
      throws SQLException, OperationFailedException, OperationExhaustedException;

  void insert(SmartContractDeploymentValidationRequest smartContractDeploymentValidationRequest)
      throws Exception;

  void update(SmartContractDeploymentValidationRequest smartContractDeploymentValidationRequest)
      throws Exception;
}
