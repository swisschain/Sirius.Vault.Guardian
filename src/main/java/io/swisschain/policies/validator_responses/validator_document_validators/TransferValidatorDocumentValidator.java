package io.swisschain.policies.validator_responses.validator_document_validators;

import io.swisschain.contracts.documents.ValidatorDocument;
import io.swisschain.contracts.transfers.Transfer;
import io.swisschain.contracts.documents.transfers.TransferValidatorDocument;
import io.swisschain.services.JsonSerializer;

public class TransferValidatorDocumentValidator implements ValidatorDocumentValidator {

  private final JsonSerializer jsonSerializer;

  public TransferValidatorDocumentValidator(JsonSerializer jsonSerializer) {
    this.jsonSerializer = jsonSerializer;
  }

  @Override
  public boolean validate(ValidatorDocument document, String originMessage) {
    Transfer originTransfer;

    try {
      originTransfer = jsonSerializer.deserialize(originMessage, Transfer.class);
    } catch (Exception exception) {
      return false;
    }

    var transfer = ((TransferValidatorDocument) document).getTransfer();

    return transfer.equals(originTransfer);
  }
}
