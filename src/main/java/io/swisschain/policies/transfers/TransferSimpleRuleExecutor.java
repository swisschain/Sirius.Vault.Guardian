package io.swisschain.policies.transfers;

import io.swisschain.domain.policies.RuleExecutionOutput;
import io.swisschain.domain.validation_requests.transfers.TransferValidationRequest;
import io.swisschain.domain.validators.Validator;
import io.swisschain.domain.validators.ValidatorRequest;
import io.swisschain.policies.SimpleRuleExecutor;

import java.util.List;

public class TransferSimpleRuleExecutor extends SimpleRuleExecutor implements TransferRuleExecutor {
  public RuleExecutionOutput execute(
      TransferValidationRequest transferValidationRequest,
      List<ValidatorRequest> validationRequests,
      List<Validator> validators) {
    var sourceBrokerAccount =
        transferValidationRequest.getTransfer().getSource().getBrokerAccount();
    var destinationBrokerAccount =
        transferValidationRequest.getTransfer().getDestination().getBrokerAccount();

    if (destinationBrokerAccount != null && destinationBrokerAccount.equals(sourceBrokerAccount)) {
      return RuleExecutionOutput.createApprove();
    }

    return execute(validationRequests, validators);
  }
}
