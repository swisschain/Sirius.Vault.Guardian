package io.swisschain.policies;

import io.swisschain.domain.validators.ValidatorRequestType;
import io.swisschain.policies.transfers.TransferValidationPolicy;

public class ValidationPolicyFactory {

  private final TransferValidationPolicy transferValidationPolicy;

  public ValidationPolicyFactory(TransferValidationPolicy transferValidationPolicy) {

    this.transferValidationPolicy = transferValidationPolicy;
  }

  public ValidationPolicy get(ValidatorRequestType type) throws Exception {
    switch (type) {
      case Transfer:
        return transferValidationPolicy;
      case SmartContractDeployment:
        return null;
      default:
        throw new Exception();
    }
  }
}
