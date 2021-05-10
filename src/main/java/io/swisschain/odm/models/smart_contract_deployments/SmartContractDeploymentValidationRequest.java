package io.swisschain.odm.models.smart_contract_deployments;

import io.swisschain.contracts.documents.ValidatorResolution;
import io.swisschain.contracts.smart_contracts.deployment.SmartContractDeployment;

import java.util.List;

public class SmartContractDeploymentValidationRequest {
  public SmartContractDeployment smartContractDeployment;
  public List<ValidatorResolution> validatorResolutions;

  public SmartContractDeploymentValidationRequest() {}

  public SmartContractDeploymentValidationRequest(
      SmartContractDeployment smartContractDeployment,
      List<ValidatorResolution> validatorResolutions) {
    this.smartContractDeployment = smartContractDeployment;
    this.validatorResolutions = validatorResolutions;
  }
}
