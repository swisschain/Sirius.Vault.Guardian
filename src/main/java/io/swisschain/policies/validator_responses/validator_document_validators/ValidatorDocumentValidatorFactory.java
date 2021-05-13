package io.swisschain.policies.validator_responses.validator_document_validators;

import io.swisschain.domain.validators.ValidatorRequestType;
import io.swisschain.services.JsonSerializer;

public class ValidatorDocumentValidatorFactory {

  private final TransferValidatorDocumentValidator transferValidatorDocumentValidator;
  private final SmartContractDeploymentValidatorDocumentValidator
      smartContractDeploymentValidatorDocumentValidator;
  private final SmartContractInvocationValidatorDocumentValidator
      smartContractInvocationValidatorDocumentValidator;

  public ValidatorDocumentValidatorFactory(JsonSerializer jsonSerializer) {
    transferValidatorDocumentValidator = new TransferValidatorDocumentValidator(jsonSerializer);
    smartContractDeploymentValidatorDocumentValidator =
        new SmartContractDeploymentValidatorDocumentValidator(jsonSerializer);
    smartContractInvocationValidatorDocumentValidator =
        new SmartContractInvocationValidatorDocumentValidator(jsonSerializer);
  }

  public ValidatorDocumentValidator get(ValidatorRequestType type) throws Exception {
    switch (type) {
      case Transfer:
        return transferValidatorDocumentValidator;
      case SmartContractDeployment:
        return smartContractDeploymentValidatorDocumentValidator;
      case SmartContractInvocation:
        return smartContractInvocationValidatorDocumentValidator;
      default:
        throw new Exception();
    }
  }
}
