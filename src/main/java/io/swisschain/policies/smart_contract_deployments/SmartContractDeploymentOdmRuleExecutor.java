package io.swisschain.policies.smart_contract_deployments;

import io.swisschain.contracts.smart_contracts.deployment.SmartContractDeployment;
import io.swisschain.domain.policies.RuleExecutionOutput;
import io.swisschain.domain.validation_requests.smart_contract_deployments.SmartContractDeploymentValidationRequest;
import io.swisschain.domain.validators.Validator;
import io.swisschain.domain.validators.ValidatorRequest;
import io.swisschain.odm.OdmClient;
import io.swisschain.policies.OdmRuleExecutor;

import java.util.List;

public class SmartContractDeploymentOdmRuleExecutor extends OdmRuleExecutor<SmartContractDeployment>
    implements SmartContractDeploymentRuleExecutor {

  public SmartContractDeploymentOdmRuleExecutor(OdmClient<SmartContractDeployment> odmClient) {
    super(odmClient);
  }

  public RuleExecutionOutput execute(
      SmartContractDeploymentValidationRequest smartContractDeploymentValidationRequest,
      List<ValidatorRequest> validatorRequests,
      List<Validator> validators)
      throws Exception {
    return execute(
        smartContractDeploymentValidationRequest.getSmartContractDeployment(),
        validatorRequests,
        validators);
  }
}
