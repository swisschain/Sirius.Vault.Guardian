package io.swisschain.policies.transfers;

import io.swisschain.contracts.documents.Document;
import io.swisschain.contracts.documents.DocumentStatus;
import io.swisschain.contracts.documents.ValidatorResolution;
import io.swisschain.contracts.documents.transfers.TransferDocument;
import io.swisschain.crypto.asymmetric.AsymmetricEncryptionService;
import io.swisschain.domain.validation_requests.ValidationRequest;
import io.swisschain.domain.validation_requests.transfers.TransferValidationRequest;
import io.swisschain.policies.DocumentBuilder;
import io.swisschain.services.JsonSerializer;

import java.time.Instant;
import java.util.List;

public class TransferDocumentBuilder extends DocumentBuilder {

  public TransferDocumentBuilder(
      AsymmetricEncryptionService asymmetricEncryptionService,
      String privateKey,
      JsonSerializer jsonSerializer) {
    super(asymmetricEncryptionService, privateKey, jsonSerializer);
  }

  @Override
  protected Document createDocument(
      ValidationRequest validationRequest,
      List<ValidatorResolution> validatorResolutions,
      List<String> requestedValidators,
      DocumentStatus status) {
    return new TransferDocument(
        validationRequest.getId(),
        validationRequest.getTenantId(),
        ((TransferValidationRequest) validationRequest).getTransfer(),
        validatorResolutions,
        requestedValidators,
        status,
        validationRequest.getRejectReasonMessage(),
        Instant.now());
  }
}
