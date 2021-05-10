package io.swisschain.contracts.documents.smart_contracts;

import io.swisschain.contracts.documents.Resolution;
import io.swisschain.contracts.documents.ValidatorDocument;
import io.swisschain.contracts.smart_contracts.deployment.SmartContractDeployment;

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
