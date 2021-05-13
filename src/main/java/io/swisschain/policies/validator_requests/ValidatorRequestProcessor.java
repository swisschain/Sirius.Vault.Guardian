package io.swisschain.policies.validator_requests;

import io.swisschain.crypto.asymmetric.AsymmetricEncryptionService;
import io.swisschain.crypto.symmetric.SymmetricEncryptionService;
import io.swisschain.domain.exceptions.AlreadyExistsException;
import io.swisschain.domain.validator_messages.MessageType;
import io.swisschain.domain.validator_messages.ValidatorRequestMessage;
import io.swisschain.domain.validators.Validator;
import io.swisschain.domain.validators.ValidatorRequest;
import io.swisschain.domain.validators.ValidatorRequestType;
import io.swisschain.repositories.validator_requests.ValidatorRequestRepository;
import io.swisschain.services.JsonSerializer;
import io.swisschain.services.ValidatorRequestApiService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.crypto.InvalidCipherTextException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

public class ValidatorRequestProcessor {

  private static final Logger logger = LogManager.getLogger();

  private final ValidatorRequestApiService validatorRequestApiService;
  private final ValidatorRequestRepository validatorRequestRepository;
  private final SymmetricEncryptionService symmetricEncryptionService;
  private final AsymmetricEncryptionService asymmetricEncryptionService;
  private final JsonSerializer jsonSerializer;

  public ValidatorRequestProcessor(
      ValidatorRequestApiService validatorRequestApiService,
      ValidatorRequestRepository validatorRequestRepository,
      SymmetricEncryptionService symmetricEncryptionService,
      AsymmetricEncryptionService asymmetricEncryptionService,
      JsonSerializer jsonSerializer) {
    this.validatorRequestApiService = validatorRequestApiService;
    this.validatorRequestRepository = validatorRequestRepository;
    this.symmetricEncryptionService = symmetricEncryptionService;
    this.asymmetricEncryptionService = asymmetricEncryptionService;
    this.jsonSerializer = jsonSerializer;
  }

  public void process(
      long validationRequestId,
      String tenantId,
      String message,
      MessageType messageType,
      ValidatorRequestType validatorRequestType,
      List<Validator> validators)
      throws Exception {

    for (var validator : validators) {
      var key = symmetricEncryptionService.generateKey();
      var nonce = symmetricEncryptionService.generateNonce();

      var validatorRequestMessage = new ValidatorRequestMessage(messageType, message);

      var encryptedMessage =
          symmetricEncryptionService.encrypt(
              jsonSerializer.serialize(validatorRequestMessage).getBytes(StandardCharsets.UTF_8),
              key,
              nonce);

      byte[] encryptedKey;
      try {
        encryptedKey = asymmetricEncryptionService.encrypt(key, validator.getPublicKey());
      } catch (IOException | InvalidCipherTextException exception) {
        logger.error(
            String.format(
                "An error occurred while encrypt validator message key. Id: %d; Validator Id: %s",
                validationRequestId, validator.getId()),
            exception);
        continue;
      }

      var validatorRequest =
          ValidatorRequest.create(
              validator.getId(),
              tenantId,
              message,
              Base64.getEncoder().encodeToString(key),
              Base64.getEncoder().encodeToString(nonce),
              validatorRequestType,
              validationRequestId);

      validatorRequestApiService.create(
          tenantId,
          validatorRequest.getId(),
          validator.getId(),
          Base64.getEncoder().encodeToString(encryptedMessage),
          Base64.getEncoder().encodeToString(encryptedKey),
          Base64.getEncoder().encodeToString(nonce));

      try {
        validatorRequestRepository.insert(validatorRequest);
      } catch (AlreadyExistsException exception) {
        logger.error(
            String.format(
                "The validator request already exists. Id: %d; Validator Id: %s",
                validationRequestId, validator.getId()),
            exception);
      }
    }
  }
}
