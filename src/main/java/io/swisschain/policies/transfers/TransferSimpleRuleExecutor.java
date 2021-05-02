package io.swisschain.policies.transfers;

import io.swisschain.contracts.common.Resolution;
import io.swisschain.domain.policies.RuleExecutionOutput;
import io.swisschain.domain.validation_requests.transfers.TransferValidationRequest;
import io.swisschain.domain.validators.Validator;
import io.swisschain.domain.validators.ValidatorRequest;

import java.util.List;

public class TransferSimpleRuleExecutor implements TransferRuleExecutor {
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

    var rejectedRequest =
        validationRequests.stream()
            .filter(validationRequest -> validationRequest.getResolution() == Resolution.Rejected)
            .findFirst()
            .orElse(null);

    if (rejectedRequest != null) {
      return RuleExecutionOutput.createReject("Rejected by validator");
    }

    var approvedRequest =
        validationRequests.stream()
            .filter(validationRequest -> validationRequest.getResolution() == Resolution.Approved)
            .findFirst()
            .orElse(null);

    if (approvedRequest != null) {
      return RuleExecutionOutput.createApprove();
    }

    if (validators.size() == 0) {
      return RuleExecutionOutput.createApprove();
    }

    return RuleExecutionOutput.createValidate(validators);
  }
}
