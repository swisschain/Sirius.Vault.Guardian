package io.swisschain.policies.transfers;

import io.swisschain.domain.exceptions.AlreadyExistsException;
import io.swisschain.domain.validation_requests.ValidationRequestStatus;
import io.swisschain.domain.validation_requests.transfers.TransferValidationRequest;
import io.swisschain.policies.DocumentValidator;
import io.swisschain.repositories.transfer_validation_requests.TransferValidationRequestRepository;
import io.swisschain.services.TransferApiService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class TransferValidationRequestProcessor {

  private static final Logger logger = LogManager.getLogger();

  private final TransferValidationPolicy transferValidationPolicy;
  private final TransferApiService transferApiService;
  private final TransferValidationRequestRepository transferValidationRequestRepository;
  private final TransferDocumentBuilder transferDocumentBuilder;
  private final DocumentValidator documentValidator;

  public TransferValidationRequestProcessor(
      TransferValidationPolicy transferValidationPolicy,
      TransferApiService transferApiService,
      TransferValidationRequestRepository transferValidationRequestRepository,
      TransferDocumentBuilder transferDocumentBuilder,
      DocumentValidator documentValidator) {
    this.transferValidationPolicy = transferValidationPolicy;
    this.transferApiService = transferApiService;
    this.transferValidationRequestRepository = transferValidationRequestRepository;
    this.transferDocumentBuilder = transferDocumentBuilder;
    this.documentValidator = documentValidator;
  }

  public void process(TransferValidationRequest transferValidationRequest) throws Exception {
    var existingTransferValidationRequest =
        transferValidationRequestRepository.getById(transferValidationRequest.getId());

    if (existingTransferValidationRequest == null) {
      try {
        transferValidationRequestRepository.insert(transferValidationRequest);
        logger.info(
            String.format(
                "Transfer validation request received. Id: %s", transferValidationRequest.getId()));
      } catch (AlreadyExistsException exception) {
        logger.warn(
            String.format(
                "Transfer validation request already exists. Id: %d",
                transferValidationRequest.getId()));
      }
    } else {
      if (existingTransferValidationRequest.getStatus() != ValidationRequestStatus.New) {
        return;
      }
      transferValidationRequest = existingTransferValidationRequest;
    }

    var signatureValidationResult =
        documentValidator.validateWithApiKey(
            transferValidationRequest.getTransfer().getContext().getDocument(),
            transferValidationRequest.getTransfer().getContext().getSignature(),
            transferValidationRequest.getTransfer().getContext().getRequestContext().getApiKeyId(),
            transferValidationRequest.getTenantId());

    if (!signatureValidationResult.isValid()) {
      transferValidationRequest.reject(signatureValidationResult.getReason());
      var signedDocument =
          transferDocumentBuilder.build(transferValidationRequest, new ArrayList<>());
      transferValidationRequest.updateDocument(
          signedDocument.getDocument(), signedDocument.getSignature());
      transferApiService.reject(transferValidationRequest);
      transferValidationRequestRepository.update(transferValidationRequest);
    } else {
      transferValidationPolicy.execute(transferValidationRequest);
    }
  }
}
