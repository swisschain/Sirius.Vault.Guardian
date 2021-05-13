package io.swisschain.policies.validator_responses.validator_document_readers;

import io.swisschain.contracts.documents.ValidatorDocument;

public interface ValidatorDocumentReader {
  ValidatorDocument read(String value);
}
