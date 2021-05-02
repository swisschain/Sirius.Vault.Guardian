package io.swisschain.contracts.smart_contracts.deployment;

import io.swisschain.contracts.common.Document;
import io.swisschain.contracts.common.DocumentStatus;
import io.swisschain.contracts.common.ValidatorResolution;

import java.time.Instant;
import java.util.List;

/**
 * Represents final signed document created by Guardian for the smart contract deployment validation
 * request.
 */
public class SmartContractDeploymentDocument extends Document {

  private SmartContractDeployment deployment;

  public SmartContractDeploymentDocument() {
    super();
  }

  public SmartContractDeploymentDocument(
      long validationRequestId,
      String tenantId,
      SmartContractDeployment deployment,
      List<ValidatorResolution> validatorResolutions,
      List<String> requestedValidators,
      DocumentStatus status,
      String rejectReasonMessage,
      Instant timestamp) {
    super(
        validationRequestId,
        tenantId,
        validatorResolutions,
        requestedValidators,
        status,
        rejectReasonMessage,
        timestamp);
    this.deployment = deployment;
  }

  /** Gets the smart contract deployment details that have been validated. */
  public SmartContractDeployment getDeployment() {
    return deployment;
  }

  /** Sets the smart contract deployment details that have been validated. */
  public void setDeployment(SmartContractDeployment deployment) {
    this.deployment = deployment;
  }
}
