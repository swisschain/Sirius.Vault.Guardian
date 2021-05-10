package io.swisschain.odm.models.smart_contract_deployments;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swisschain.odm.models.DecisionResponse;
import io.swisschain.odm.models.ResponseWrapper;

public class SmartContractDeploymentValidationResponseWrapper extends ResponseWrapper {
  @JsonProperty("smart_contract_deployment_decision_response")
  public DecisionResponse smartContractDeploymentDecisionResponse;
}
