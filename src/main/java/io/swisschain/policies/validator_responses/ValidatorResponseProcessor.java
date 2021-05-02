package io.swisschain.policies.validator_responses;

import io.swisschain.crypto.symmetric.SymmetricEncryptionService;
import io.swisschain.domain.validator_messages.ValidatorResponseMessage;
import io.swisschain.domain.validators.ValidatorResponse;
import io.swisschain.policies.DocumentValidator;
import io.swisschain.policies.ValidationPolicyFactory;
import io.swisschain.policies.validator_responses.validator_document_readers.ValidatorDocumentReaderFactory;
import io.swisschain.policies.validator_responses.validator_document_validators.ValidatorDocumentValidatorFactory;
import io.swisschain.repositories.validator_requests.ValidatorRequestRepository;
import io.swisschain.services.JsonSerializer;
import io.swisschain.services.ValidatorApiService;
import io.swisschain.services.ValidatorRequestApiService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ValidatorResponseProcessor {
  private static final Logger logger = LogManager.getLogger();
  private final ValidatorApiService validatorApiService;
  private final ValidatorRequestApiService validatorRequestApiService;
  private final ValidatorRequestRepository validatorRequestRepository;
  private final SymmetricEncryptionService symmetricEncryptionService;
  private final ValidatorDocumentReaderFactory validatorDocumentReaderFactory;
  private final ValidatorDocumentValidatorFactory validatorDocumentValidatorFactory;
  private final ValidationPolicyFactory validationPolicyFactory;
  private final DocumentValidator documentValidator;
  private final JsonSerializer jsonSerializer;

  public ValidatorResponseProcessor(
      ValidatorApiService validatorApiService,
      ValidatorRequestApiService validatorRequestApiService,
      ValidatorRequestRepository validatorRequestRepository,
      SymmetricEncryptionService symmetricEncryptionService,
      ValidatorDocumentReaderFactory validatorDocumentReaderFactory,
      ValidatorDocumentValidatorFactory validatorDocumentValidatorFactory,
      ValidationPolicyFactory validationPolicyFactory,
      DocumentValidator documentValidator,
      JsonSerializer jsonSerializer) {
    this.validatorApiService = validatorApiService;
    this.validatorRequestApiService = validatorRequestApiService;
    this.validatorRequestRepository = validatorRequestRepository;
    this.symmetricEncryptionService = symmetricEncryptionService;
    this.validatorDocumentReaderFactory = validatorDocumentReaderFactory;
    this.validatorDocumentValidatorFactory = validatorDocumentValidatorFactory;
    this.validationPolicyFactory = validationPolicyFactory;
    this.documentValidator = documentValidator;
    this.jsonSerializer = jsonSerializer;
  }

  public void process(ValidatorResponse validatorResponse) throws Exception {
    logger.info(
        String.format(
            "Validator response received. ValidatorId: %s ValidationRequestId: %s",
            validatorResponse.getValidatorId(), validatorResponse.getValidationRequestId()));

    var validatorRequest =
        validatorRequestRepository.getById(validatorResponse.getValidationRequestId());

    if (validatorRequest == null) {
      logger.warn(
          String.format(
              "Validator response validator request not found. ValidatorId: %s ValidationRequestId: %s",
              validatorResponse.getValidatorId(), validatorResponse.getValidationRequestId()));

      validatorRequestApiService.confirm(
          validatorResponse.getValidatorId(), validatorResponse.getValidationRequestId());

      return;
    }

    var validator =
        validatorApiService.getById(
            validatorRequest.getTenantId(), validatorResponse.getValidatorId());

    if (validator == null) {
      logger.error(
          String.format(
              "Validator response validator not found. Validator Id: %s",
              validatorResponse.getValidatorId()));

      validatorRequestApiService.confirm(
          validatorResponse.getValidatorId(), validatorResponse.getValidationRequestId());

      return;
    }

    var decryptedData =
        symmetricEncryptionService.decrypt(
            Base64.getDecoder().decode(validatorResponse.getMessage()),
            Base64.getDecoder().decode(validatorRequest.getKey()),
            Base64.getDecoder().decode(validatorRequest.getNonce()));

    var decryptedMessage = new String(decryptedData, StandardCharsets.UTF_8);

    ValidatorResponseMessage message;

    try {
      message = jsonSerializer.deserialize(decryptedMessage, ValidatorResponseMessage.class);
    } catch (Exception exception) {
      logger.error(
          String.format(
              "Validator response message has wrong format. ValidatorId: %s ValidationRequestId: %s",
              validatorResponse.getValidatorId(), validatorResponse.getValidationRequestId()),
          exception);

      validatorRequestApiService.confirm(
          validatorResponse.getValidatorId(), validatorResponse.getValidationRequestId());

      return;
    }

    var signatureValidationResult =
        documentValidator.validateWithPublicKey(
            message.getDocument(), message.getSignature(), validator.getPublicKey());

    if (!signatureValidationResult.isValid()) {
      logger.error(
          String.format(
              "Validator signature is invalid '%s'. ValidatorId: %s ValidationRequestId: %s",
              signatureValidationResult.getReason(),
              validatorResponse.getValidatorId(),
              validatorResponse.getValidationRequestId()));

      validatorRequestApiService.confirm(
          validatorResponse.getValidatorId(), validatorResponse.getValidationRequestId());

      return;
    }

    var validatorDocument =
        validatorDocumentReaderFactory.get(validatorRequest.getType()).read(message.getDocument());

    var isDocumentValid =
        validatorDocumentValidatorFactory
            .get(validatorRequest.getType())
            .validate(validatorDocument, validatorRequest.getMessage());

    if (!isDocumentValid) {
      logger.error(
          String.format(
              "Validator document is invalid. ValidatorId: %s ValidationRequestId: %s",
              validatorResponse.getValidatorId(), validatorResponse.getValidationRequestId()));

      validatorRequestApiService.confirm(
          validatorResponse.getValidatorId(), validatorResponse.getValidationRequestId());

      return;
    }

    validatorRequest.complete(
        message.getDocument(),
        message.getSignature(),
        validatorDocument.getResolution(),
        validatorDocument.getResolutionMessage(),
        validatorResponse.getDeviceInfo(),
        validatorResponse.getIp());

    validatorRequestRepository.update(validatorRequest);

    validationPolicyFactory
        .get(validatorRequest.getType())
        .execute(validatorRequest.getValidationRequestId());
  }
}
