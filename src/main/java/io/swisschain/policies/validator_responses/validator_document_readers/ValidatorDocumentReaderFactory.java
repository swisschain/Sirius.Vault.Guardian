package io.swisschain.policies.validator_responses.validator_document_readers;

import io.swisschain.domain.validators.ValidatorRequestType;
import io.swisschain.services.JsonSerializer;

public class ValidatorDocumentReaderFactory {

  private final TransferValidatorDocumentReader transferValidatorDocumentReader;

  public ValidatorDocumentReaderFactory(JsonSerializer jsonSerializer) {
    transferValidatorDocumentReader = new TransferValidatorDocumentReader(jsonSerializer);
  }

  public ValidatorDocumentReader get(ValidatorRequestType type) throws Exception {
    switch (type) {
      case Transfer:
        return transferValidatorDocumentReader;
      case SmartContractDeployment:
        return null;
      default:
        throw new Exception();
    }
  }
}
