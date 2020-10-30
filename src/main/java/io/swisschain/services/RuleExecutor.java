package io.swisschain.services;

import io.swisschain.domain.policies.RuleExecutionOutput;
import io.swisschain.domain.transfers.TransferValidationRequest;
import io.swisschain.domain.validators.Validator;
import io.swisschain.domain.validators.ValidatorResponse;

import java.util.List;

public interface RuleExecutor {
    RuleExecutionOutput execute(
            TransferValidationRequest transferValidationRequest,
            List<ValidatorResponse> validatorResponses,
            List<Validator> validators) throws Exception;
}
