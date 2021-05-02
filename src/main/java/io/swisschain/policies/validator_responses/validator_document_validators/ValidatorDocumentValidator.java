package io.swisschain.policies.validator_responses.validator_document_validators;

import io.swisschain.contracts.common.ValidatorDocument;

public interface ValidatorDocumentValidator {
  boolean validate(ValidatorDocument document, String originMessage);
}
