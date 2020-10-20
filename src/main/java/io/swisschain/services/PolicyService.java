package io.swisschain.services;

import io.swisschain.contracts.ValidatorDocument;
import io.swisschain.crypto.asymmetric.AsymmetricEncryptionService;
import io.swisschain.crypto.symmetric.SymmetricEncryptionService;
import io.swisschain.domain.policies.RuleExecutionAction;
import io.swisschain.domain.policies.RuleExecutionOutput;
import io.swisschain.domain.transfers.TransferValidationRequest;
import io.swisschain.domain.transfers.TransferValidationRequestStatus;
import io.swisschain.domain.validators.Validator;
import io.swisschain.domain.validators.ValidatorApproval;
import io.swisschain.domain.validators.ValidatorRequest;
import io.swisschain.domain.validators.ValidatorResponse;
import io.swisschain.repositories.TransferValidationRequestRepository;
import io.swisschain.repositories.ValidatorRequestRepository;
import io.swisschain.repositories.ValidatorResponseRepository;
import io.swisschain.repositories.exceptions.AlreadyExistsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.crypto.InvalidCipherTextException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

public class PolicyService {
  private static final Logger logger = LogManager.getLogger();

  private final SimpleRuleExecutor simpleRuleExecutor;
  private final ValidatorsApiService validatorsApiService;
  private final TransferValidationRequestApiService transferValidationRequestApiService;
  private final TransferValidationRequestRepository transferValidationRequestRepository;
  private final ValidatorRequestRepository validatorRequestRepository;
  private final ValidatorResponseRepository validatorResponseRepository;
  private final SymmetricEncryptionService symmetricEncryptionService;
  private final AsymmetricEncryptionService asymmetricEncryptionService;
  private final DocumentBuilder documentBuilder;
  private final JsonSerializer jsonSerializer;

  public PolicyService(
      SimpleRuleExecutor simpleRuleExecutor,
      ValidatorsApiService validatorsApiService,
      TransferValidationRequestApiService transferValidationRequestApiService,
      TransferValidationRequestRepository transferValidationRequestRepository,
      ValidatorRequestRepository validatorRequestRepository,
      ValidatorResponseRepository validatorResponseRepository,
      SymmetricEncryptionService symmetricEncryptionService,
      AsymmetricEncryptionService asymmetricEncryptionService,
      DocumentBuilder documentBuilder,
      JsonSerializer jsonSerializer) {
    this.simpleRuleExecutor = simpleRuleExecutor;
    this.validatorsApiService = validatorsApiService;
    this.transferValidationRequestApiService = transferValidationRequestApiService;
    this.transferValidationRequestRepository = transferValidationRequestRepository;
    this.validatorRequestRepository = validatorRequestRepository;
    this.validatorResponseRepository = validatorResponseRepository;
    this.symmetricEncryptionService = symmetricEncryptionService;
    this.asymmetricEncryptionService = asymmetricEncryptionService;
    this.documentBuilder = documentBuilder;
    this.jsonSerializer = jsonSerializer;
  }

  /** Processes new transfer validation request. */
  public void processNew(TransferValidationRequest transferValidationRequest) throws Exception {
    var existingTransferValidationRequest =
        transferValidationRequestRepository.getById(transferValidationRequest.getId());

    if (existingTransferValidationRequest != null) {
      return;
    }

    logger.info(
        String.format(
            "Transfer validation request received. Id: %s", transferValidationRequest.getId()));

    try {
      transferValidationRequestRepository.insert(transferValidationRequest);
    } catch (AlreadyExistsException exception) {
      logger.warn(
          String.format(
              "Transfer validation request already exists. Id: %d",
              transferValidationRequest.getId()));
      return;
    }

    processTransferValidationRequest(transferValidationRequest);
  }

  public void processApproval(ValidatorApproval validatorApproval) throws Exception {
    logger.info(
        String.format(
            "Validator approval received. ValidatorId: %s TransferApprovalRequestId: %d",
            validatorApproval.getValidatorId(), validatorApproval.getTransferApprovalRequestId()));

    var transferValidationRequest =
        transferValidationRequestRepository.getById(
            validatorApproval.getTransferApprovalRequestId());

    if (transferValidationRequest == null) {
      logger.warn(
          String.format(
              "Received approval for unknown transfer approval request. ValidatorId: %s TransferApprovalRequestId: %d",
              validatorApproval.getValidatorId(),
              validatorApproval.getTransferApprovalRequestId()));

      return;
    }

    var validatorRequest =
        validatorRequestRepository.get(
            validatorApproval.getValidatorId(), validatorApproval.getTransferApprovalRequestId());

    if (validatorRequest == null) {
      logger.warn(
          String.format(
              "Received approval for unknown validator request. ValidatorId: %s TransferApprovalRequestId: %d",
              validatorApproval.getValidatorId(),
              validatorApproval.getTransferApprovalRequestId()));

      return;
    }

    if (transferValidationRequest.getStatus() == TransferValidationRequestStatus.Processing) {
      var decryptedData =
          symmetricEncryptionService.decrypt(
              Base64.getDecoder().decode(validatorApproval.getDocument()),
              Base64.getDecoder().decode(validatorRequest.getKey()),
              Base64.getDecoder().decode(validatorRequest.getNonce()));

      var json = new String(decryptedData, StandardCharsets.UTF_8);

      ValidatorDocument validatorDocument;

      try {
        validatorDocument = jsonSerializer.deserialize(json, ValidatorDocument.class);
      } catch (Exception exception) {
        logger.error("Can not deserialize validator document", exception);
        return;
      }

      var validator = validatorsApiService.getValidatorById(validatorApproval.getValidatorId());

      if (validator == null) {
        logger.error(
            String.format(
                "Can not find validator. Validator Id: %s", validatorApproval.getValidatorId()));
        return;
      }

      var isSignatureValid =
          asymmetricEncryptionService.verifySignature(
              Base64.getDecoder().decode(validatorApproval.getDocument()),
              Base64.getDecoder().decode(validatorApproval.getSignature()),
              validator.getPublicKey());

      if (!isSignatureValid) {
        logger.error(
            String.format(
                "Signature is invalid. ValidatorId: %s TransferApprovalRequestId: %d",
                validatorApproval.getValidatorId(),
                validatorApproval.getTransferApprovalRequestId()));
        return;
      }

      var isTransferDetailsValid =
          validatorDocument
              .getTransferDetails()
              .equals(transferValidationRequest.getTransferDetails());

      if (!isTransferDetailsValid) {
        logger.error(
            String.format(
                "Transfer details is invalid. ValidatorId: %s TransferApprovalRequestId: %d",
                validatorApproval.getValidatorId(),
                validatorApproval.getTransferApprovalRequestId()));
        return;
      }

      try {
        validatorResponseRepository.insert(
            ValidatorResponse.create(
                validatorApproval.getValidatorId(),
                validatorApproval.getTransferApprovalRequestId(),
                validatorApproval.getDocument(),
                validatorApproval.getSignature(),
                validatorDocument.getResolution(),
                validatorDocument.getResolutionMessage(),
                validatorApproval.getDeviceInfo(),
                validatorApproval.getIp()));
      } catch (AlreadyExistsException exception) {
        logger.error(
            String.format(
                "The validator response already exists. ValidatorId: %s TransferApprovalRequestId: %d",
                validatorApproval.getValidatorId(),
                validatorApproval.getTransferApprovalRequestId()));
      }

      processTransferValidationRequest(transferValidationRequest);
    } else {
      logger.info(
          String.format(
              "Transfer approval request already processed. ValidatorId: %s TransferApprovalRequestId: %d",
              validatorApproval.getValidatorId(),
              validatorApproval.getTransferApprovalRequestId()));
    }

    validatorsApiService.confirm(
        validatorApproval.getValidatorId(), validatorApproval.getTransferApprovalRequestId());
  }

  private void processTransferValidationRequest(TransferValidationRequest transferValidationRequest)
      throws Exception {
    var validatorRequests =
        validatorRequestRepository.getByTransferValidationRequestId(
            transferValidationRequest.getId());

    var validationResponses =
        validatorResponseRepository.getByTransferValidationRequestId(
            transferValidationRequest.getId());

    var executionOutput = executeRules(transferValidationRequest, validationResponses);

    processRuleExecutionOutput(
        transferValidationRequest, executionOutput, validatorRequests, validationResponses);
  }

  private void processRuleExecutionOutput(
      TransferValidationRequest transferValidationRequest,
      RuleExecutionOutput ruleExecutionOutput,
      List<ValidatorRequest> validatorRequests,
      List<ValidatorResponse> validatorResponses)
      throws Exception {
    if (ruleExecutionOutput.getAction() == RuleExecutionAction.Validate) {
      transferValidationRequest.processing();
      requestApprovals(transferValidationRequest, ruleExecutionOutput.getValidators());
    } else {
      if (ruleExecutionOutput.getAction() == RuleExecutionAction.Approve) {
        transferValidationRequest.approve();
        var signedDocument =
                documentBuilder.buid(transferValidationRequest, validatorResponses, validatorRequests);
        transferValidationRequest.updateDocument(signedDocument.getDocument(), signedDocument.getSignature());
        transferValidationRequestApiService.confirm(transferValidationRequest);
      } else if (ruleExecutionOutput.getAction() == RuleExecutionAction.Reject) {
        var signedDocument =
                documentBuilder.buid(transferValidationRequest, validatorResponses, validatorRequests);
        transferValidationRequest.reject(signedDocument.getRejectReasonMessage());
        transferValidationRequest.updateDocument(signedDocument.getDocument(), signedDocument.getSignature());
        transferValidationRequestApiService.reject(transferValidationRequest);
      }
    }
    transferValidationRequestRepository.update(transferValidationRequest);
  }

  private void requestApprovals(
      TransferValidationRequest transferValidationRequest, List<Validator> validators)
      throws Exception {

    for (var validator : validators) {
      var key = symmetricEncryptionService.generateKey();
      var nonce = symmetricEncryptionService.generateNonce();

      var message = jsonSerializer.serialize(transferValidationRequest.getTransferDetails());
      var encryptedMessage =
          symmetricEncryptionService.encrypt(message.getBytes(StandardCharsets.UTF_8), key, nonce);

      byte[] encryptedKey;
      try {
        encryptedKey = asymmetricEncryptionService.encrypt(key, validator.getPublicKey());
      } catch (IOException | InvalidCipherTextException exception) {
        logger.error(
            String.format(
                "An error occurred while encrypt validator message key. Id: %d; Validator Id: %s",
                transferValidationRequest.getId(), validator.getId()),
            exception);
        continue;
      }

      var validatorRequest =
          ValidatorRequest.create(
              validator.getId(),
              transferValidationRequest.getId(),
              Base64.getEncoder().encodeToString(encryptedMessage),
              Base64.getEncoder().encodeToString(encryptedKey),
              Base64.getEncoder().encodeToString(key),
              Base64.getEncoder().encodeToString(nonce));

      validatorsApiService.sendApprovalRequest(transferValidationRequest.getId(), validatorRequest);

      try {
        validatorRequestRepository.insert(validatorRequest);
      } catch (AlreadyExistsException exception) {
        logger.error(
            String.format(
                "The validator request already exists. Id: %d; Validator Id: %s",
                transferValidationRequest.getId(), validator.getId()),
            exception);
      }
    }
  }

  private RuleExecutionOutput executeRules(
      TransferValidationRequest transferValidationRequest,
      List<ValidatorResponse> validatorResponses) {
    // TODO: prepare validation context for rule engine

    var validators = validatorsApiService.getValidators();

    return simpleRuleExecutor.execute(transferValidationRequest, validatorResponses, validators);
  }
}
