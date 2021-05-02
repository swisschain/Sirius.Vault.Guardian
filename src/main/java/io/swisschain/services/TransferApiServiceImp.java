package io.swisschain.services;

import io.swisschain.contracts.transfers.Transfer;
import io.swisschain.contracts.transfers.TransferContext;
import io.swisschain.contracts.transfers.TransferDestination;
import io.swisschain.contracts.transfers.TransferSource;
import io.swisschain.domain.primitives.TagType;
import io.swisschain.domain.validation_requests.transfers.TransferValidationRequest;
import io.swisschain.mappers.TagTypeMapper;
import io.swisschain.sirius.vaultApi.VaultApiClient;
import io.swisschain.sirius.vaultApi.generated.transfer_validation_requests.TransferValidationRequestsOuterClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransferApiServiceImp implements TransferApiService {
  private final Logger logger = LogManager.getLogger();
  private final VaultApiClient vaultApiClient;
  private final String hostProcessId;

  public TransferApiServiceImp(VaultApiClient vaultApiClient, String hostProcessId) {
    this.vaultApiClient = vaultApiClient;
    this.hostProcessId = hostProcessId;
  }

  @Override
  public List<TransferValidationRequest> get() {
    var request =
        TransferValidationRequestsOuterClass.GetTransferValidationRequestsRequest.newBuilder()
            .build();
    var response = vaultApiClient.getTransferValidationRequests().get(request);

    if (response.getBodyCase()
        == TransferValidationRequestsOuterClass.GetTransferValidationRequestsResponse.BodyCase
            .ERROR) {
      logger.error(
          String.format(
              "An error occurred while getting transfer validation requests. %s %s",
              response.getError().getErrorCode().name(), response.getError().getErrorMessage()));
      return new ArrayList<>();
    }

    return response.getResponse().getRequestsList().stream()
        .map(this::map)
        .collect(Collectors.toList());
  }

  @Override
  public void confirm(TransferValidationRequest validationRequest) {
    var conformationRequest =
        TransferValidationRequestsOuterClass.ConfirmTransferValidationRequestRequest.newBuilder()
            .setRequestId(
                String.format("Guardian:TransferValidationRequest:%d", validationRequest.getId()))
            .setTransferValidationRequestId(validationRequest.getId())
            .setDocument(validationRequest.getDocument())
            .setSignature(validationRequest.getSignature())
            .setHostProcessId(hostProcessId)
            .build();

    var response = vaultApiClient.getTransferValidationRequests().confirm(conformationRequest);

    if (response.getBodyCase()
        == TransferValidationRequestsOuterClass.ConfirmTransferValidationRequestResponse.BodyCase
            .ERROR) {
      var message =
          String.format(
              "It is not possible to confirm transfer validation request %d. %s %s",
              validationRequest.getId(),
              response.getError().getErrorCode().name(),
              response.getError().getErrorMessage());
      if (response.getError().getErrorCode()
          == TransferValidationRequestsOuterClass.ConfirmTransferValidationRequestErrorResponseBody
              .ErrorCode.INVALID_STATE) {
        logger.warn(message);
      } else {
        logger.error(message);
      }
    } else {
      logger.info(
          String.format("Transfer validation request %d confirmed.", validationRequest.getId()));
    }
  }

  @Override
  public void reject(TransferValidationRequest validationRequest) {
    var rejectRequestBuilder =
        TransferValidationRequestsOuterClass.RejectTransferValidationRequestRequest.newBuilder()
            .setRequestId(
                String.format("Guardian:TransferValidationRequest:%d", validationRequest.getId()))
            .setTransferValidationRequestId(validationRequest.getId())
            .setRejectionReason(
                TransferValidationRequestsOuterClass.RejectTransferValidationRequestRequest
                    .RejectionReason.REJECTED_BY_POLICY)
            .setDocument(validationRequest.getDocument())
            .setSignature(validationRequest.getSignature())
            .setHostProcessId(hostProcessId);

    if (validationRequest.getRejectReasonMessage() != null) {
      rejectRequestBuilder.setRejectionReasonMessage(validationRequest.getRejectReasonMessage());
    }

    var rejectRequest = rejectRequestBuilder.build();
    var response = vaultApiClient.getTransferValidationRequests().reject(rejectRequest);

    if (response.getBodyCase()
        == TransferValidationRequestsOuterClass.RejectTransferValidationRequestResponse.BodyCase
            .ERROR) {
      var message =
          String.format(
              "It is not possible to reject transfer validation request %d. %s %s",
              validationRequest.getId(),
              response.getError().getErrorCode().name(),
              response.getError().getErrorMessage());
      if (response.getError().getErrorCode()
          == TransferValidationRequestsOuterClass.RejectTransferValidationRequestErrorResponseBody
              .ErrorCode.INVALID_STATE) {
        logger.warn(message);
      } else {
        logger.error(message);
      }
    } else {
      logger.info(
          String.format("Transfer validation request %d rejected.", validationRequest.getId()));
    }
  }

  private TransferValidationRequest map(
      TransferValidationRequestsOuterClass.TransferValidationRequest validationRequest) {
    return TransferValidationRequest.create(
        validationRequest.getId(),
        validationRequest.getTenantId(),
        map(validationRequest.getTransfer()));
  }

  private Transfer map(TransferValidationRequestsOuterClass.Transfer transfer) {
    return new Transfer(
        transfer.getId(),
        Mapper.map(transfer.getBlockchain()),
        map(transfer.getSource()),
        map(transfer.getDestination()),
        Mapper.map(transfer.getValue()),
        Mapper.map(transfer.getFee()),
        map(transfer.getContext()));
  }

  private TransferSource map(TransferValidationRequestsOuterClass.TransferSource transferSource) {
    return new TransferSource(
        transferSource.getAddress(),
        Mapper.map(transferSource.getBrokerAccount()),
        Mapper.map(transferSource.getAccount()));
  }

  private TransferDestination map(
      TransferValidationRequestsOuterClass.TransferDestination transferDestination) {
    return new TransferDestination(
        transferDestination.getAddress(),
        transferDestination.hasTag() ? transferDestination.getTag().getValue() : null,
        transferDestination.hasTagType()
            ? TagTypeMapper.map(transferDestination.getTagType().getTagType())
            : TagType.None,
        Mapper.map(transferDestination.getBrokerAccount()),
        Mapper.map(transferDestination.getAccount()));
  }

  private TransferContext map(
      TransferValidationRequestsOuterClass.TransferContext transferContext) {
    return new TransferContext(
        transferContext.hasDocument() ? transferContext.getDocument().getValue() : null,
        transferContext.hasDocumentVersion()
            ? transferContext.getDocumentVersion().getValue()
            : null,
        transferContext.hasSignature() ? transferContext.getSignature().getValue() : null,
        transferContext.hasWithdrawalReferenceId()
            ? transferContext.getWithdrawalReferenceId().getValue()
            : null,
        transferContext.getComponent(),
        transferContext.getOperationType(),
        Mapper.map(transferContext.getRequestContext()));
  }
}
