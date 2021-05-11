package io.swisschain.policies.validator_responses.validator_document_readers;

import io.swisschain.contracts.documents.ValidatorDocument;
import io.swisschain.contracts.documents.smart_contracts.invocation.SmartContractInvocationValidatorDocument;
import io.swisschain.services.JsonSerializer;

public class SmartContractInvocationValidatorDocumentReader implements ValidatorDocumentReader {

  private final JsonSerializer jsonSerializer;

  public SmartContractInvocationValidatorDocumentReader(JsonSerializer jsonSerializer) {
    this.jsonSerializer = jsonSerializer;
  }

  @Override
  public ValidatorDocument read(String value) {
    SmartContractInvocationValidatorDocument document;

    try {
      document = jsonSerializer.deserialize(value, SmartContractInvocationValidatorDocument.class);
    } catch (Exception exception) {
      return null;
    }

    return document;
  }
}
