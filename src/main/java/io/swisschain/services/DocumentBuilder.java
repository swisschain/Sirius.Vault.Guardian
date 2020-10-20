package io.swisschain.services;

import io.swisschain.contracts.Document;
import io.swisschain.contracts.DocumentStatus;
import io.swisschain.contracts.ValidatorResolution;
import io.swisschain.crypto.asymmetric.AsymmetricEncryptionService;
import io.swisschain.domain.policies.SignedDocument;
import io.swisschain.domain.transfers.TransferValidationRequest;
import io.swisschain.domain.validators.ValidatorRequest;
import io.swisschain.domain.validators.ValidatorResponse;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class DocumentBuilder {
  private final AsymmetricEncryptionService asymmetricEncryptionService;
  private final String privateKey;
  private final JsonSerializer jsonSerializer;

  public DocumentBuilder(
      AsymmetricEncryptionService asymmetricEncryptionService,
      String privateKey,
      JsonSerializer jsonSerializer) {
    this.asymmetricEncryptionService = asymmetricEncryptionService;
    this.privateKey = privateKey;
    this.jsonSerializer = jsonSerializer;
  }

  public SignedDocument buid(
      TransferValidationRequest transferValidationRequest,
      List<ValidatorResponse> validatorResponses,
      List<ValidatorRequest> validatorRequests)
      throws Exception {
    var validatorResolutions = new ArrayList<ValidatorResolution>();

    for (var validatorResponse : validatorResponses) {
      validatorResolutions.add(
          new ValidatorResolution(
              validatorResponse.getValidatorId(),
              validatorResponse.getDocument(),
              validatorResponse.getSignature(),
              validatorResponse.getResolution(),
              validatorResponse.getResolutionMessage(),
              validatorResponse.getDeviceInfo(),
              validatorResponse.getIp(),
              validatorResponse.getCreatedAt()));
    }

    var requestedValidators = new ArrayList<String>();

    for (var validatorRequest : validatorRequests) {
      requestedValidators.add(validatorRequest.getValidatorId());
    }

    DocumentStatus status;

    switch (transferValidationRequest.getStatus()) {
      case Approved:
        status = DocumentStatus.Approved;
        break;
      case Rejected:
        status = DocumentStatus.Rejected;
        break;
      case Cancelled:
        status = DocumentStatus.Cancelled;
        break;
      default:
        throw new Exception(
            String.format(
                "Unknown transfer validation request status. %s",
                transferValidationRequest.getStatus()));
    }

    var document =
        new Document(
            transferValidationRequest.getId(),
            transferValidationRequest.getTenantId(),
            transferValidationRequest.getTransferDetails(),
            validatorResolutions,
            requestedValidators,
            status,
            transferValidationRequest.getRejectReasonMessage(),
            Instant.now());

    var json = jsonSerializer.serialize(document);

    byte[] signature =
        asymmetricEncryptionService.generateSignature(
            json.getBytes(StandardCharsets.UTF_8), privateKey);

    return new SignedDocument(json, Base64.getEncoder().encodeToString(signature));
  }
}
