package io.swisschain.policies.smart_contract_invocations;

import io.swisschain.domain.policies.RuleExecutionOutput;
import io.swisschain.domain.validation_requests.smart_contracts.SmartContractInvocationValidationRequest;
import io.swisschain.domain.validators.Validator;
import io.swisschain.domain.validators.ValidatorRequest;

import java.util.List;

public interface SmartContractInvocationRuleExecutor {
  RuleExecutionOutput execute(
      SmartContractInvocationValidationRequest smartContractInvocationValidationRequest,
      List<ValidatorRequest> validationRequests,
      List<Validator> validators)
      throws Exception;
}
