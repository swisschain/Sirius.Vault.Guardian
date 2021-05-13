package io.swisschain.policies;

import io.swisschain.contracts.documents.Document;
import io.swisschain.contracts.documents.DocumentStatus;
import io.swisschain.contracts.documents.ValidatorResolution;
import io.swisschain.crypto.asymmetric.AsymmetricEncryptionService;
import io.swisschain.domain.policies.SignedDocument;
import io.swisschain.domain.validation_requests.ValidationRequest;
import io.swisschain.domain.validators.ValidatorRequest;
import io.swisschain.domain.validators.ValidatorRequestStatus;
import io.swisschain.services.JsonSerializer;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public abstract class DocumentBuilder {
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

  public SignedDocument build(
      ValidationRequest validationRequest, List<ValidatorRequest> validatorRequests)
      throws Exception {
    var validatorResolutions = new ArrayList<ValidatorResolution>();

    var completedValidatorRequests =
        validatorRequests.stream()
            .filter(
                validatorRequest ->
                    validatorRequest.getStatus() == ValidatorRequestStatus.Completed)
            .collect(Collectors.toList());

    for (var validatorRequest : completedValidatorRequests) {
      validatorResolutions.add(
          new ValidatorResolution(
              validatorRequest.getValidatorId(),
              validatorRequest.getDocument(),
              validatorRequest.getSignature(),
              validatorRequest.getResolution(),
              validatorRequest.getResolutionMessage(),
              validatorRequest.getDeviceInfo(),
              validatorRequest.getIp(),
              validatorRequest.getUpdatedAt()));
    }

    var requestedValidators =
        validatorRequests.stream()
            .map(ValidatorRequest::getValidatorId)
            .collect(Collectors.toList());

    DocumentStatus status;

    switch (validationRequest.getStatus()) {
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
            String.format("Unknown validation request status. %s", validationRequest.getStatus()));
    }

    var document =
        createDocument(validationRequest, validatorResolutions, requestedValidators, status);

    var json = jsonSerializer.serialize(document);

    byte[] signature =
        asymmetricEncryptionService.generateSignature(
            json.getBytes(StandardCharsets.UTF_8), privateKey);

    return new SignedDocument(json, Base64.getEncoder().encodeToString(signature));
  }

  protected abstract Document createDocument(
      ValidationRequest validationRequest,
      List<ValidatorResolution> validatorResolutions,
      List<String> requestedValidators,
      DocumentStatus status);
}
