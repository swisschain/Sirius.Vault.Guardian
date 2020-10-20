package io.swisschain.services;

import io.swisschain.contracts.Resolution;
import io.swisschain.domain.policies.RuleExecutionOutput;
import io.swisschain.domain.transfers.TransferValidationRequest;
import io.swisschain.domain.validators.Validator;
import io.swisschain.domain.validators.ValidatorResponse;

import java.util.List;

public class SimpleRuleExecutor {
  public RuleExecutionOutput execute(
      TransferValidationRequest transferValidationRequest,
      List<ValidatorResponse> validatorResponses,
      List<Validator> validators) {
    var sourceAddressGroup =
        transferValidationRequest.getTransferDetails().getSourceAddress().getGroup();
    var destinationAddressGroup =
        transferValidationRequest.getTransferDetails().getDestinationAddress().getGroup();

    if (sourceAddressGroup != null && sourceAddressGroup.equals(destinationAddressGroup)) {
      return RuleExecutionOutput.createApprove();
    }

    boolean isRejected = false;
    for (var validatorResponse : validatorResponses) {
      if (validatorResponse.getResolution() == Resolution.Rejected) {
        isRejected = true;
        break;
      }
    }

    if (isRejected) {
      return RuleExecutionOutput.createReject("Rejected by validator");
    }

    boolean isApproved = false;
    for (var validatorResponse : validatorResponses) {
      if (validatorResponse.getResolution() == Resolution.Approved) {
        isApproved = true;
        break;
      }
    }

    if (isApproved) {
      return RuleExecutionOutput.createApprove();
    }

    if(validators.size() == 0){
      return RuleExecutionOutput.createApprove();
    }

    return RuleExecutionOutput.createValidate(validators);
  }
}
