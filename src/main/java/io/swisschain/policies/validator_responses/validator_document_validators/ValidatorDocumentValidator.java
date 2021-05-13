package io.swisschain.policies.validator_responses.validator_document_validators;

import io.swisschain.contracts.documents.ValidatorDocument;

public interface ValidatorDocumentValidator {
  boolean validate(ValidatorDocument document, String originMessage);
}
