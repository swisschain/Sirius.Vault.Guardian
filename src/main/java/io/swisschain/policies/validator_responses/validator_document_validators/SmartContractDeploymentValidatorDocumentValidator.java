package io.swisschain.policies.validator_responses.validator_document_validators;

import io.swisschain.contracts.documents.ValidatorDocument;
import io.swisschain.contracts.documents.smart_contracts.deployment.SmartContractDeploymentValidatorDocument;
import io.swisschain.contracts.smart_contracts.deployment.SmartContractDeployment;
import io.swisschain.services.JsonSerializer;

public class SmartContractDeploymentValidatorDocumentValidator
    implements ValidatorDocumentValidator {

  private final JsonSerializer jsonSerializer;

  public SmartContractDeploymentValidatorDocumentValidator(JsonSerializer jsonSerializer) {
    this.jsonSerializer = jsonSerializer;
  }

  @Override
  public boolean validate(ValidatorDocument document, String originMessage) {
    SmartContractDeployment originSmartContractDeployment;

    try {
      originSmartContractDeployment =
          jsonSerializer.deserialize(originMessage, SmartContractDeployment.class);
    } catch (Exception exception) {
      return false;
    }

    var smartContractDeployment =
        ((SmartContractDeploymentValidatorDocument) document).getDeployment();

    return smartContractDeployment.equals(originSmartContractDeployment);
  }
}
