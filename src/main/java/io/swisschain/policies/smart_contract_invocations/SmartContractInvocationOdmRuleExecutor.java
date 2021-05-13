package io.swisschain.policies.smart_contract_invocations;

import io.swisschain.contracts.smart_contracts.invocation.SmartContractInvocation;
import io.swisschain.domain.policies.RuleExecutionOutput;
import io.swisschain.domain.validation_requests.smart_contracts.SmartContractInvocationValidationRequest;
import io.swisschain.domain.validators.Validator;
import io.swisschain.domain.validators.ValidatorRequest;
import io.swisschain.odm.OdmClient;
import io.swisschain.policies.OdmRuleExecutor;

import java.util.List;

public class SmartContractInvocationOdmRuleExecutor extends OdmRuleExecutor<SmartContractInvocation>
    implements SmartContractInvocationRuleExecutor {

  public SmartContractInvocationOdmRuleExecutor(OdmClient<SmartContractInvocation> odmClient) {
    super(odmClient);
  }

  public RuleExecutionOutput execute(
      SmartContractInvocationValidationRequest smartContractInvocationValidationRequest,
      List<ValidatorRequest> validatorRequests,
      List<Validator> validators)
      throws Exception {
    return execute(
        smartContractInvocationValidationRequest.getSmartContractInvocation(),
        validatorRequests,
        validators);
  }
}
