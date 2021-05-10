package io.swisschain.odm;

import io.swisschain.contracts.documents.ValidatorResolution;
import io.swisschain.contracts.smart_contracts.deployment.SmartContractDeployment;
import io.swisschain.odm.models.DecisionResponse;
import io.swisschain.odm.models.RequestWrapper;
import io.swisschain.odm.models.smart_contract_deployments.SmartContractDeploymentValidationRequest;
import io.swisschain.odm.models.smart_contract_deployments.SmartContractDeploymentValidationRequestWrapper;
import io.swisschain.odm.models.smart_contract_deployments.SmartContractDeploymentValidationResponseWrapper;
import io.swisschain.services.JsonSerializer;

import java.util.List;
import java.util.UUID;

public class SmartContractDeploymentOdmClientImp extends OdmClientImp<SmartContractDeployment> {

  public SmartContractDeploymentOdmClientImp(String url, JsonSerializer jsonSerializer) {
    super(url, jsonSerializer);
  }

  @Override
  protected RequestWrapper wrap(
      SmartContractDeployment data, List<ValidatorResolution> validatorResolutions) {
    return new SmartContractDeploymentValidationRequestWrapper(
        UUID.randomUUID().toString(),
        new SmartContractDeploymentValidationRequest(data, validatorResolutions));
  }

  @Override
  protected DecisionResponse getResponse(String data) throws Exception {
    var response =
        jsonSerializer.deserialize(data, SmartContractDeploymentValidationResponseWrapper.class);
    return response.smartContractDeploymentDecisionResponse;
  }
}
