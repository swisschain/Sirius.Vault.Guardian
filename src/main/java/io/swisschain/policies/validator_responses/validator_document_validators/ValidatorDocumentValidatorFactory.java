package io.swisschain.policies.validator_responses.validator_document_validators;

import io.swisschain.domain.validators.ValidatorRequestType;
import io.swisschain.services.JsonSerializer;

public class ValidatorDocumentValidatorFactory {

  private final TransferValidatorDocumentValidator transferValidatorDocumentValidator;

  public ValidatorDocumentValidatorFactory(JsonSerializer jsonSerializer) {
    transferValidatorDocumentValidator = new TransferValidatorDocumentValidator(jsonSerializer);
  }

  public ValidatorDocumentValidator get(ValidatorRequestType type) throws Exception {
    switch (type) {
      case Transfer:
        return transferValidatorDocumentValidator;
      case SmartContractDeployment:
        return null;
      default:
        throw new Exception();
    }
  }
}
