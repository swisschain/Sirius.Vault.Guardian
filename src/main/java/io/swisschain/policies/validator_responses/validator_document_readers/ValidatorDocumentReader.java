package io.swisschain.policies.validator_responses.validator_document_readers;

import io.swisschain.contracts.common.ValidatorDocument;

public interface ValidatorDocumentReader {
  ValidatorDocument read(String value);
}
