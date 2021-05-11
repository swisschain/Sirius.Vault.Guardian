package io.swisschain.odm;

import io.swisschain.contracts.documents.ValidatorResolution;
import io.swisschain.contracts.smart_contracts.invocation.SmartContractInvocation;
import io.swisschain.odm.models.DecisionResponse;
import io.swisschain.odm.models.RequestWrapper;
import io.swisschain.odm.models.smart_contract_invocations.SmartContractInvocationValidationRequest;
import io.swisschain.odm.models.smart_contract_invocations.SmartContractInvocationValidationRequestWrapper;
import io.swisschain.odm.models.smart_contract_invocations.SmartContractInvocationValidationResponseWrapper;
import io.swisschain.services.JsonSerializer;

import java.util.List;
import java.util.UUID;

public class SmartContractInvocationOdmClientImp extends OdmClientImp<SmartContractInvocation> {

  public SmartContractInvocationOdmClientImp(String url, JsonSerializer jsonSerializer) {
    super(url, jsonSerializer);
  }

  @Override
  protected RequestWrapper wrap(
      SmartContractInvocation data, List<ValidatorResolution> validatorResolutions) {
    return new SmartContractInvocationValidationRequestWrapper(
        UUID.randomUUID().toString(),
        new SmartContractInvocationValidationRequest(data, validatorResolutions));
  }

  @Override
  protected DecisionResponse getResponse(String data) throws Exception {
    var response =
        jsonSerializer.deserialize(data, SmartContractInvocationValidationResponseWrapper.class);
    return response.smartContractInvocationDecisionResponse;
  }
}
