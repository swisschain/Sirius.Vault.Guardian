package io.swisschain.policies.validator_responses.validator_document_readers;

import io.swisschain.contracts.documents.ValidatorDocument;
import io.swisschain.contracts.documents.smart_contracts.deployment.SmartContractDeploymentValidatorDocument;
import io.swisschain.services.JsonSerializer;

public class SmartContractDeploymentValidatorDocumentReader implements ValidatorDocumentReader {

  private final JsonSerializer jsonSerializer;

  public SmartContractDeploymentValidatorDocumentReader(JsonSerializer jsonSerializer) {
    this.jsonSerializer = jsonSerializer;
  }

  @Override
  public ValidatorDocument read(String value) {
    SmartContractDeploymentValidatorDocument document;

    try {
      document = jsonSerializer.deserialize(value, SmartContractDeploymentValidatorDocument.class);
    } catch (Exception exception) {
      return null;
    }

    return document;
  }
}
