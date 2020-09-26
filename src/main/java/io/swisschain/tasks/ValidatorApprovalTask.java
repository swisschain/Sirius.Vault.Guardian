package io.swisschain.tasks;

import io.grpc.StatusRuntimeException;
import io.swisschain.domain.approvals.ValidatorApproval;
import io.swisschain.domain.approvals.ValidatorApprovalStatus;
import io.swisschain.domain.transfers.TransferValidationRequestStatus;
import io.swisschain.repositories.transfers.TransferValidationRequestRepository;
import io.swisschain.sirius.guardianApi.GuardianApiClient;
import io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests;
import io.swisschain.sirius.vaultApi.VaultApiClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ValidatorApprovalTask implements Runnable {
  private static final Logger logger = LogManager.getLogger();

  private final VaultApiClient vaultApiClient;
  private final GuardianApiClient guardianApiClient;
  private final TransferValidationRequestRepository transferValidationRequestRepository;
  private final String hostProcessId;

  public ValidatorApprovalTask(
      VaultApiClient vaultApiClient,
      GuardianApiClient guardianApiClient,
      TransferValidationRequestRepository transferValidationRequestRepository,
      String hostProcessId) {
    this.vaultApiClient = vaultApiClient;
    this.guardianApiClient = guardianApiClient;
    this.transferValidationRequestRepository = transferValidationRequestRepository;
    this.hostProcessId = hostProcessId;

    logger.info("ValidatorApprovalTask created");
  }

  public static ValidatorApprovalStatus map(
      @NotNull ValidatorApprovalRequests.GetApprovalResponse.ResolutionStatus value) {
    switch (value) {
      case approve:
        return ValidatorApprovalStatus.Approved;
      case reject:
        return ValidatorApprovalStatus.Rejected;
      case skip:
        return ValidatorApprovalStatus.Skipped;
      default:
        throw new IllegalArgumentException(
            String.format("Unknown resolution status. %s", value.name()));
    }
  }

  @Override
  public void run() {
    try {
      var approvals = getApprovals();

      if (approvals == null) {
        return;
      }

      for (var approval : approvals) {
        var transferValidationRequest =
            transferValidationRequestRepository.getById(approval.getTransferApprovalRequestId());

        if (transferValidationRequest == null) {
          logger.warn(
              String.format(
                  "Received approval for unknown transfer approval request. ValidatorId: %s TransferApprovalRequestId: %d",
                  approval.getValidatorId(), approval.getTransferApprovalRequestId()));
          // TODO: send acknowledge
          continue;
        }

        if (transferValidationRequest.getStatus() != TransferValidationRequestStatus.Processing) {
          logger.info(
              String.format(
                  "Received approval for processed transfer approval request. ValidatorId: %s TransferApprovalRequestId: %d",
                  approval.getValidatorId(), approval.getTransferApprovalRequestId()));
          // TODO: send acknowledge
          continue;
        }

        // TODO: process approve
        // TODO: send acknowledge
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
                map(validatorApproval.getResolution()),
                validatorApproval.getResolutionMessage(),
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
}
