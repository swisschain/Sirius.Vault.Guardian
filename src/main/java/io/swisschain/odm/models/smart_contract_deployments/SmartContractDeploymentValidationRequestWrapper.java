package io.swisschain.odm.models.smart_contract_deployments;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swisschain.odm.models.RequestWrapper;

public class SmartContractDeploymentValidationRequestWrapper extends RequestWrapper {
  @JsonProperty("smart_contract_deployment_validation_request")
  public SmartContractDeploymentValidationRequest smartContractDeploymentValidationRequest;

  public SmartContractDeploymentValidationRequestWrapper() {}

  public SmartContractDeploymentValidationRequestWrapper(
      String decisionId,
      SmartContractDeploymentValidationRequest smartContractDeploymentValidationRequest) {
    super(decisionId);
    this.smartContractDeploymentValidationRequest = smartContractDeploymentValidationRequest;
  }
}
