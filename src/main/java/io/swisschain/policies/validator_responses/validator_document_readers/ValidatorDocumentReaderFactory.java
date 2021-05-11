package io.swisschain.policies.validator_responses.validator_document_readers;

import io.swisschain.domain.validators.ValidatorRequestType;
import io.swisschain.services.JsonSerializer;

public class ValidatorDocumentReaderFactory {

  private final TransferValidatorDocumentReader transferValidatorDocumentReader;
  private final SmartContractDeploymentValidatorDocumentReader
      smartContractDeploymentValidatorDocumentReader;
  private final SmartContractInvocationValidatorDocumentReader
      smartContractInvocationValidatorDocumentReader;

  public ValidatorDocumentReaderFactory(JsonSerializer jsonSerializer) {
    transferValidatorDocumentReader = new TransferValidatorDocumentReader(jsonSerializer);
    smartContractDeploymentValidatorDocumentReader =
        new SmartContractDeploymentValidatorDocumentReader(jsonSerializer);
    smartContractInvocationValidatorDocumentReader =
        new SmartContractInvocationValidatorDocumentReader(jsonSerializer);
  }

  public ValidatorDocumentReader get(ValidatorRequestType type) throws Exception {
    switch (type) {
      case Transfer:
        return transferValidatorDocumentReader;
      case SmartContractDeployment:
        return smartContractDeploymentValidatorDocumentReader;
      case SmartContractInvocation:
        return smartContractInvocationValidatorDocumentReader;
      default:
        throw new Exception();
    }
  }
}
