package io.swisschain.contracts.smart_contracts.deployment;

import io.swisschain.contracts.common.Resolution;
import io.swisschain.contracts.common.ValidatorDocument;

/** Represents validator smart contract deployment validation document. */
public class SmartContractDeploymentValidatorDocument extends ValidatorDocument {
  private SmartContractDeployment deployment;

  public SmartContractDeploymentValidatorDocument() {
    super();
  }

  public SmartContractDeploymentValidatorDocument(
      Resolution resolution, String resolutionMessage, SmartContractDeployment deployment) {
    super(resolution, resolutionMessage);
    this.deployment = deployment;
  }

  /** Gets smart contract deployment details. */
  public SmartContractDeployment getDeployment() {
    return deployment;
  }

  /** Sets smart contract deployment details. */
  public void setDeployment(SmartContractDeployment deployment) {
    this.deployment = deployment;
  }
}
