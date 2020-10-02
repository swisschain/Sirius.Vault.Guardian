package io.swisschain.tasks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.grpc.StatusRuntimeException;
import io.swisschain.crypto.symmetric.SymmetricEncryptionService;
import io.swisschain.domain.approvals.ValidatorApproval;
import io.swisschain.domain.messages.ValidatorApprovalRequestMessage;
import io.swisschain.domain.transfers.TransferValidationRequestStatus;
import io.swisschain.repositories.transfers.TransferValidationRequestRepository;
import io.swisschain.services.ValidatorApprovalRequestService;
import io.swisschain.sirius.guardianApi.GuardianApiClient;
import io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ValidatorApprovalTask implements Runnable {
  private static final Logger logger = LogManager.getLogger();

  private final GuardianApiClient guardianApiClient;
  private final TransferValidationRequestRepository transferValidationRequestRepository;
  private final SymmetricEncryptionService symmetricEncryptionService;
  private final ObjectMapper mapper = new ObjectMapper();

  public ValidatorApprovalTask(
      GuardianApiClient guardianApiClient,
      TransferValidationRequestRepository transferValidationRequestRepository,
      SymmetricEncryptionService symmetricEncryptionService) {
    this.guardianApiClient = guardianApiClient;
    this.transferValidationRequestRepository = transferValidationRequestRepository;
    this.symmetricEncryptionService = symmetricEncryptionService;

    logger.info("ValidatorApprovalTask created");
  }

  @Override
  public void run() {
    try {
      var approvals = getApprovals();

      if (approvals == null) {
        return;
      }

      for (var approval : approvals) {
        logger.info(
            String.format(
                "Validator approval received. ValidatorId: %s TransferApprovalRequestId: %d",
                approval.getValidatorId(), approval.getTransferApprovalRequestId()));

        var transferValidationRequest =
            transferValidationRequestRepository.getById(approval.getTransferApprovalRequestId());

        if (transferValidationRequest == null) {
          logger.warn(
              String.format(
                  "Received approval for unknown transfer approval request. ValidatorId: %s TransferApprovalRequestId: %d",
                  approval.getValidatorId(), approval.getTransferApprovalRequestId()));
          continue;
        }

        if (transferValidationRequest.getStatus() == TransferValidationRequestStatus.Processing) {
          var decryptedData =
              symmetricEncryptionService.decrypt(
                  Base64.getDecoder().decode(approval.getMessage()),
                  ValidatorApprovalRequestService.key,
                  ValidatorApprovalRequestService.nonce);

          var json = new String(decryptedData, StandardCharsets.UTF_8);

          logger.info(String.format("Decrypted message: %s", json));

          ValidatorApprovalRequestMessage validatorResponseMessage = null;
          try {
            validatorResponseMessage = mapper.readValue(json, ValidatorApprovalRequestMessage.class);
          } catch (JsonProcessingException exception) {
            logger.error("Can not deserialize message", exception);
          }

          if (validatorResponseMessage.getResolution().equals("approve")) {
            transferValidationRequest.approve();
            transferValidationRequestRepository.update(transferValidationRequest);
          } else if (validatorResponseMessage.getResolution().equals("reject")) {
            transferValidationRequest.reject();
            transferValidationRequestRepository.update(transferValidationRequest);
          }

          // TODO: add approver to context
          continue;
        } else {
          logger.info(
              String.format(
                  "Transfer approval request already processed. ValidatorId: %s TransferApprovalRequestId: %d",
                  approval.getValidatorId(), approval.getTransferApprovalRequestId()));
        }

        confirm(approval.getValidatorId(), approval.getTransferApprovalRequestId());
      }

    } catch (Exception exception) {
      logger.error("An error occurred while processing approval results.", exception);
    }
  }

  private List<ValidatorApproval> getApprovals() {
    try {
      var request = ValidatorApprovalRequests.GetApprovalResultsRequest.newBuilder().build();
      var response = guardianApiClient.getTransactions().getApprovalResults(request);

      var validatorApprovals = new ArrayList<ValidatorApproval>();

      for (var validatorApproval : response.getPayloadList()) {
        validatorApprovals.add(
            new ValidatorApproval(
                validatorApproval.getValidatorId(),
                Long.parseLong(validatorApproval.getTransferSigningRequestId()),
                validatorApproval.getResolutionDocumentEncBase64(),
                validatorApproval.getSignature()));
      }

      return validatorApprovals;
    } catch (StatusRuntimeException statusRuntimeException) {
      logger.warn(
          String.format(
              "Unable to get list of approval results due to %s network status",
              statusRuntimeException.getStatus().getCode().name()));
      return null;
    }
  }

  private void confirm(String validatorId, long transferValidationRequestId) {
    var request =
        ValidatorApprovalRequests.AcknowledgeResultRequest.newBuilder()
            .setTransferSigningRequestId(Long.toString(transferValidationRequestId))
            .setValidatorId(validatorId)
            .build();

    var response = guardianApiClient.getTransactions().acknowledgeResult(request);
  }
}
