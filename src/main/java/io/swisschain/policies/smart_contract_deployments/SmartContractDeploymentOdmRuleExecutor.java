package io.swisschain.policies.smart_contract_deployments;

import io.swisschain.domain.policies.RuleExecutionOutput;
import io.swisschain.domain.validation_requests.smart_contract_deployments.SmartContractDeploymentValidationRequest;
import io.swisschain.domain.validators.Validator;
import io.swisschain.domain.validators.ValidatorRequest;
import io.swisschain.odm.OdmClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SmartContractDeploymentOdmRuleExecutor implements SmartContractDeploymentRuleExecutor {
  private static final Logger logger = LogManager.getLogger();

  private final OdmClient odmClient;

  public SmartContractDeploymentOdmRuleExecutor(OdmClient odmClient) {
    this.odmClient = odmClient;
  }

  public RuleExecutionOutput execute(
      SmartContractDeploymentValidationRequest transferValidationRequest,
      List<ValidatorRequest> validatorRequests,
      List<Validator> validators)
      throws Exception {

    throw new Exception("Not implemented");
  }
}
