package io.swisschain.odm.models.smart_contract_invocations;

import io.swisschain.contracts.documents.ValidatorResolution;
import io.swisschain.contracts.smart_contracts.invocation.SmartContractInvocation;

import java.util.List;

public class SmartContractInvocationValidationRequest {
  public SmartContractInvocation smartContractInvocation;
  public List<ValidatorResolution> validatorResolutions;

  public SmartContractInvocationValidationRequest() {}

  public SmartContractInvocationValidationRequest(
      SmartContractInvocation smartContractInvocation,
      List<ValidatorResolution> validatorResolutions) {
    this.smartContractInvocation = smartContractInvocation;
    this.validatorResolutions = validatorResolutions;
  }
}
