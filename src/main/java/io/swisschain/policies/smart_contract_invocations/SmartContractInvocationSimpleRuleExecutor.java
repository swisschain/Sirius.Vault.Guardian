package io.swisschain.policies.smart_contract_invocations;

import io.swisschain.domain.policies.RuleExecutionOutput;
import io.swisschain.domain.validation_requests.smart_contracts.SmartContractInvocationValidationRequest;
import io.swisschain.domain.validators.Validator;
import io.swisschain.domain.validators.ValidatorRequest;
import io.swisschain.policies.SimpleRuleExecutor;

import java.util.List;

public class SmartContractInvocationSimpleRuleExecutor extends SimpleRuleExecutor
    implements SmartContractInvocationRuleExecutor {
  public RuleExecutionOutput execute(
      SmartContractInvocationValidationRequest smartContractInvocationValidationRequest,
      List<ValidatorRequest> validationRequests,
      List<Validator> validators) {
    return execute(validationRequests, validators);
  }
}
