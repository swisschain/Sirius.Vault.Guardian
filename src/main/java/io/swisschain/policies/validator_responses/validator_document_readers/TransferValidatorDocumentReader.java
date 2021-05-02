package io.swisschain.policies.validator_responses.validator_document_readers;

import io.swisschain.contracts.common.ValidatorDocument;
import io.swisschain.contracts.transfers.TransferValidatorDocument;
import io.swisschain.services.JsonSerializer;

public class TransferValidatorDocumentReader implements ValidatorDocumentReader {

  private final JsonSerializer jsonSerializer;

  public TransferValidatorDocumentReader(JsonSerializer jsonSerializer) {
    this.jsonSerializer = jsonSerializer;
  }

  @Override
  public ValidatorDocument read(String value) {
    TransferValidatorDocument document;

    try {
      document = jsonSerializer.deserialize(value, TransferValidatorDocument.class);
    } catch (Exception exception) {
      return null;
    }

    return document;
  }
}
