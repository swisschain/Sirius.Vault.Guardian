package io.swisschain.policies.transfers;

import io.swisschain.domain.policies.RuleExecutionOutput;
import io.swisschain.domain.validation_requests.transfers.TransferValidationRequest;
import io.swisschain.domain.validators.Validator;
import io.swisschain.domain.validators.ValidatorRequest;

import java.util.List;

public interface TransferRuleExecutor {
  RuleExecutionOutput execute(
      TransferValidationRequest transferValidationRequest,
      List<ValidatorRequest> validationRequests,
      List<Validator> validators)
      throws Exception;
}
