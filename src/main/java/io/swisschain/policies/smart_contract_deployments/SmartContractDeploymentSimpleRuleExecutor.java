package io.swisschain.policies.smart_contract_deployments;

import io.swisschain.domain.policies.RuleExecutionOutput;
import io.swisschain.domain.validation_requests.smart_contracts.SmartContractDeploymentValidationRequest;
import io.swisschain.domain.validators.Validator;
import io.swisschain.domain.validators.ValidatorRequest;
import io.swisschain.policies.SimpleRuleExecutor;

import java.util.List;

public class SmartContractDeploymentSimpleRuleExecutor extends SimpleRuleExecutor
    implements SmartContractDeploymentRuleExecutor {
  public RuleExecutionOutput execute(
      SmartContractDeploymentValidationRequest smartContractDeploymentValidationRequest,
      List<ValidatorRequest> validationRequests,
      List<Validator> validators) {
    return execute(validationRequests, validators);
  }
}
