package io.swisschain.policies;

import io.swisschain.contracts.documents.Resolution;
import io.swisschain.domain.policies.RuleExecutionOutput;
import io.swisschain.domain.validators.Validator;
import io.swisschain.domain.validators.ValidatorRequest;

import java.util.List;

public abstract class SimpleRuleExecutor {
  public RuleExecutionOutput execute(
      List<ValidatorRequest> validationRequests, List<Validator> validators) {
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
