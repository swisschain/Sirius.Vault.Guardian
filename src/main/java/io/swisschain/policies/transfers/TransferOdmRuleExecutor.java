package io.swisschain.policies.transfers;

import io.swisschain.domain.policies.RuleExecutionOutput;
import io.swisschain.domain.validation_requests.transfers.TransferValidationRequest;
import io.swisschain.domain.validators.Validator;
import io.swisschain.domain.validators.ValidatorRequest;
import io.swisschain.odm.OdmClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TransferOdmRuleExecutor implements TransferRuleExecutor {
  private static final Logger logger = LogManager.getLogger();

  private final OdmClient odmClient;

  public TransferOdmRuleExecutor(OdmClient odmClient) {
    this.odmClient = odmClient;
  }

  public RuleExecutionOutput execute(
      TransferValidationRequest transferValidationRequest,
      List<ValidatorRequest> validatorRequests,
      List<Validator> validators)
      throws Exception {

    throw new Exception("Not implemented");
  }
}
