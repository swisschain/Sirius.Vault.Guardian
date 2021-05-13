package io.swisschain.policies;

import io.swisschain.domain.validators.ValidatorRequestType;
import io.swisschain.policies.smart_contract_deployments.SmartContractDeploymentValidationPolicy;
import io.swisschain.policies.smart_contract_invocations.SmartContractInvocationValidationPolicy;
import io.swisschain.policies.transfers.TransferValidationPolicy;

public class ValidationPolicyFactory {

  private final TransferValidationPolicy transferValidationPolicy;
  private final SmartContractDeploymentValidationPolicy smartContractDeploymentValidationPolicy;
  private final SmartContractInvocationValidationPolicy smartContractInvocationValidationPolicy;

  public ValidationPolicyFactory(
      TransferValidationPolicy transferValidationPolicy,
      SmartContractDeploymentValidationPolicy smartContractDeploymentValidationPolicy,
      SmartContractInvocationValidationPolicy smartContractInvocationValidationPolicy) {

    this.transferValidationPolicy = transferValidationPolicy;
    this.smartContractDeploymentValidationPolicy = smartContractDeploymentValidationPolicy;
    this.smartContractInvocationValidationPolicy = smartContractInvocationValidationPolicy;
  }

  public ValidationPolicy get(ValidatorRequestType type) throws Exception {
    switch (type) {
      case Transfer:
        return transferValidationPolicy;
      case SmartContractDeployment:
        return smartContractDeploymentValidationPolicy;
      case SmartContractInvocation:
        return smartContractInvocationValidationPolicy;
      default:
        throw new Exception();
    }
  }
}
