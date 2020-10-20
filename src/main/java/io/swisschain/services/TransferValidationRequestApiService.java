package io.swisschain.services;

import com.google.protobuf.Timestamp;
import io.grpc.StatusRuntimeException;
import io.swisschain.contracts.*;
import io.swisschain.domain.transfers.TransferValidationRequest;
import io.swisschain.mappers.NetworkTypeMapper;
import io.swisschain.mappers.TagTypeMapper;
import io.swisschain.primitives.TagType;
import io.swisschain.sirius.vaultApi.VaultApiClient;
import io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class TransferValidationRequestApiService {
  private static final Logger logger = LogManager.getLogger();

  private final VaultApiClient vaultApiClient;
  private final String hostProcessId;

  public TransferValidationRequestApiService(VaultApiClient vaultApiClient, String hostProcessId) {
    this.vaultApiClient = vaultApiClient;
    this.hostProcessId = hostProcessId;
  }

  @Nullable
  public List<TransferValidationRequest> getRequests() {
    try {
      var request =
          TransferValidationRequestsOuterClass.GetTransferValidationRequestsRequest.newBuilder()
              .build();
      var response = vaultApiClient.getTransferValidationRequests().get(request);

      if (response.getBodyCase()
          == TransferValidationRequestsOuterClass.GetTransferValidationRequestsResponse.BodyCase
              .ERROR) {
        logger.error(
            String.format(
                "An error occurred while getting transfer validation requests. %s",
                response.getError().getErrorMessage()));
        return null;
      }

      var validationRequests = new ArrayList<TransferValidationRequest>();

      for (var validationRequest : response.getResponse().getRequestsList()) {
        try {
          validationRequests.add(map(validationRequest));
        } catch (Exception exception) {
          logger.error(
              String.format(
                  "Can not parse transfer validation request. TransferValidationRequestId: %d",
                  validationRequest.getId()),
              exception);
        }
      }

      return validationRequests;
    } catch (StatusRuntimeException statusRuntimeException) {
      logger.warn(
          String.format(
              "Unable to get list of transfer validation requests due to %s network status",
              statusRuntimeException.getStatus().getCode().name()));
      return null;
    }
  }

  public void confirm(TransferValidationRequest transferValidationRequest) {
    var conformationRequest =
        TransferValidationRequestsOuterClass.ConfirmTransferValidationRequestRequest.newBuilder()
            .setRequestId(
                String.format(
                    "Guardian:TransferValidationRequest:%d", transferValidationRequest.getId()))
            .setTransferValidationRequestId(transferValidationRequest.getId())
            .setDocument(transferValidationRequest.getDocument())
            .setSignature(transferValidationRequest.getSignature())
            .setHostProcessId(hostProcessId)
            .build();

    var response = vaultApiClient.getTransferValidationRequests().confirm(conformationRequest);

    if (response.getBodyCase()
        == TransferValidationRequestsOuterClass.ConfirmTransferValidationRequestResponse.BodyCase
            .ERROR) {
      logger.error(
          String.format(
              "An error occurred while confirming transfer validation request. %s",
              response.getError().getErrorMessage()));
    } else {
      logger.info(
          String.format(
              "Transfer validation request confirmed. %d", transferValidationRequest.getId()));
    }
  }

  public void reject(TransferValidationRequest transferValidationRequest) {
    var rejectRequest =
        TransferValidationRequestsOuterClass.RejectTransferValidationRequestRequest.newBuilder()
            .setRequestId(
                String.format(
                    "Guardian:TransferValidationRequest:%d", transferValidationRequest.getId()))
            .setTransferValidationRequestId(transferValidationRequest.getId())
            .setRejectionReason(
                TransferValidationRequestsOuterClass.TransferValidationRequestRejectionReason
                    .REJECTED_BY_POLICY)
            .setRejectionReasonMessage(transferValidationRequest.getRejectReasonMessage())
            .setDocument(transferValidationRequest.getDocument())
            .setSignature(transferValidationRequest.getSignature())
            .setHostProcessId(hostProcessId)
            .build();

    var response = vaultApiClient.getTransferValidationRequests().reject(rejectRequest);

    if (response.getBodyCase()
        == TransferValidationRequestsOuterClass.RejectTransferValidationRequestResponse.BodyCase
            .ERROR) {
      logger.error(
          String.format(
              "An error occurred while rejecting transfer validation request. %s",
              response.getError().getErrorMessage()));
    } else {
      logger.info(
          String.format(
              "Transfer validation request rejected. %d", transferValidationRequest.getId()));
    }
  }

  private TransferValidationRequest map(
      TransferValidationRequestsOuterClass.TransferValidationRequest transferValidationRequest) {
    return TransferValidationRequest.create(
        transferValidationRequest.getId(),
        transferValidationRequest.hasTenantId()
            ? transferValidationRequest.getTenantId().getValue()
            : null,
        new TransferDetails(
            transferValidationRequest.getOperationId(),
            map(transferValidationRequest.getBlockchain()),
            map(transferValidationRequest.getAsset()),
            map(transferValidationRequest.getSourceAddress()),
            map(transferValidationRequest.getDestinationAddress()),
            new BigDecimal(transferValidationRequest.getAmount().getValue()),
            new BigDecimal(transferValidationRequest.getFeeLimit().getValue()),
            map(transferValidationRequest.getTransferContext())));
  }

  private Blockchain map(TransferValidationRequestsOuterClass.Blockchain blockchain) {
    return new Blockchain(
        blockchain.getId(),
        blockchain.getProtocolId(),
        NetworkTypeMapper.map(blockchain.getNetworkType()));
  }

  private Asset map(TransferValidationRequestsOuterClass.Asset asset) {
    return new Asset(
        asset.getId(),
        asset.getSymbol(),
        asset.hasAddress() ? asset.getAddress().getValue() : null);
  }

  private SourceAddress map(TransferValidationRequestsOuterClass.SourceAddress sourceAddress) {
    return new SourceAddress(
        sourceAddress.getAddress(),
        sourceAddress.hasName() ? sourceAddress.getName().getValue() : null,
        sourceAddress.hasGroup() ? sourceAddress.getGroup().getValue() : null);
  }

  private DestinationAddress map(
      TransferValidationRequestsOuterClass.DestinationAddress destinationAddress) {
    return new DestinationAddress(
        destinationAddress.getAddress(),
        destinationAddress.hasName() ? destinationAddress.getName().getValue() : null,
        destinationAddress.hasGroup() ? destinationAddress.getGroup().getValue() : null,
        destinationAddress.hasTag() ? destinationAddress.getTag().getValue() : null,
        destinationAddress.hasTagType()
            ? TagTypeMapper.map(destinationAddress.getTagType().getTagType())
            : TagType.None);
  }

  private TransferContext map(
      TransferValidationRequestsOuterClass.TransferContext transferContext) {
    return new TransferContext(
        transferContext.hasDocument() ? transferContext.getDocument().getValue() : null,
        transferContext.hasSignature() ? transferContext.getSignature().getValue() : null,
        transferContext.hasAccountReferenceId()
            ? transferContext.getAccountReferenceId().getValue()
            : null,
        transferContext.hasWithdrawalReferenceId()
            ? transferContext.getWithdrawalReferenceId().getValue()
            : null,
        transferContext.getComponent(),
        transferContext.getOperationType(),
        transferContext.hasSourceGroup() ? transferContext.getSourceGroup().getValue() : null,
        transferContext.hasDestinationGroup()
            ? transferContext.getDestinationGroup().getValue()
            : null,
        map(transferContext.getRequestContext()));
  }

  private RequestContext map(TransferValidationRequestsOuterClass.RequestContext requestContext) {
    return new RequestContext(
        requestContext.hasUserId() ? requestContext.getUserId().getValue() : null,
        requestContext.hasApiKeyId() ? requestContext.getApiKeyId().getValue() : null,
        requestContext.hasIp() ? requestContext.getIp().getValue() : null,
        requestContext.hasTimestamp() ? map(requestContext.getTimestamp().getTimestamp()) : null);
  }

  private Instant map(Timestamp timestamp) {
    return Instant.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos());
  }
}
