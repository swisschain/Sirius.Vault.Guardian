package io.swisschain.policies.smart_contract_deployments;

import io.swisschain.domain.policies.RuleExecutionOutput;
import io.swisschain.domain.validation_requests.smart_contracts.SmartContractDeploymentValidationRequest;
import io.swisschain.domain.validators.Validator;
import io.swisschain.domain.validators.ValidatorRequest;

import java.util.List;

public interface SmartContractDeploymentRuleExecutor {
  RuleExecutionOutput execute(
      SmartContractDeploymentValidationRequest smartContractDeploymentValidationRequest,
      List<ValidatorRequest> validationRequests,
      List<Validator> validators)
      throws Exception;
}
