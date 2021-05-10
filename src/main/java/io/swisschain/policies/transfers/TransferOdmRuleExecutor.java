package io.swisschain.policies.transfers;

import io.swisschain.contracts.transfers.Transfer;
import io.swisschain.domain.policies.RuleExecutionOutput;
import io.swisschain.domain.validation_requests.transfers.TransferValidationRequest;
import io.swisschain.domain.validators.Validator;
import io.swisschain.domain.validators.ValidatorRequest;
import io.swisschain.odm.OdmClient;
import io.swisschain.policies.OdmRuleExecutor;

import java.util.List;

public class TransferOdmRuleExecutor extends OdmRuleExecutor<Transfer>
    implements TransferRuleExecutor {

  public TransferOdmRuleExecutor(OdmClient<Transfer> odmClient) {
    super(odmClient);
  }

  public RuleExecutionOutput execute(
      TransferValidationRequest transferValidationRequest,
      List<ValidatorRequest> validatorRequests,
      List<Validator> validators)
      throws Exception {
    return execute(transferValidationRequest.getTransfer(), validatorRequests, validators);
  }
}
