package io.swisschain.tasks;

import com.google.protobuf.Timestamp;
import io.grpc.StatusRuntimeException;
import io.swisschain.domain.transfers.*;
import io.swisschain.mappers.NetworkTypeMapper;
import io.swisschain.primitives.TagType;
import io.swisschain.repositories.exceptions.AlreadyExistsException;
import io.swisschain.repositories.transfers.TransferValidationRequestRepository;
import io.swisschain.sirius.vaultApi.VaultApiClient;
import io.swisschain.sirius.vaultApi.generated.common.Common;
import io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class TransferValidationTask implements Runnable {
  private static final Logger logger = LogManager.getLogger();

  private final VaultApiClient vaultApiClient;
  private final TransferValidationRequestRepository transferValidationRequestRepository;
  private final String hostProcessId;

  public TransferValidationTask(
      VaultApiClient vaultApiClient,
      TransferValidationRequestRepository transferValidationRequestRepository,
      String hostProcessId) {
    this.vaultApiClient = vaultApiClient;
    this.transferValidationRequestRepository = transferValidationRequestRepository;
    this.hostProcessId = hostProcessId;

    logger.info("TransferValidationTask created");
  }

  private static TagType map(@NotNull Common.TagType tagType) {
    switch (tagType) {
      case TEXT:
        return TagType.Text;
      case NUMBER:
        return TagType.Number;
      default:
        throw new IllegalArgumentException(String.format("Unknown tag type. %s", tagType.name()));
    }
  }

  @Override
  public void run() {
    try {
      var requests = getRequests();

      if (requests == null) {
        return;
      }

      for (var request : requests) {
        try {
          var existingRequest = transferValidationRequestRepository.getById(request.getId());

          if (existingRequest != null) {
            if (existingRequest.getStatus() == TransferValidationRequestStatus.Approved) {
              confirm(request.getId(), "policy result", "signature");
            }

            if (existingRequest.getStatus() == TransferValidationRequestStatus.Rejected) {
              reject(
                  request.getId(),
                  TransferValidationRequestsOuterClass.TransferValidationRequestRejectionReason
                      .REJECTED_POLICY,
                  "Invalid policy.",
                  "policy result",
                  "signature");
            }
          } else {
            transferValidationRequestRepository.insert(request);

            // TODO: Call policy service and process output
          }
        } catch (AlreadyExistsException e) {
          logger.warn(
              String.format(
                  "Transfer validation request already processed. Id: %d", request.getId()));
        } catch (Exception exception) {
          logger.error(
              "An error occurred while processing transfer validation request.", exception);

          reject(
              request.getId(),
              TransferValidationRequestsOuterClass.TransferValidationRequestRejectionReason
                  .REJECTED_POLICY,
              "Invalid policy.",
              "policy result",
              "signature");
        }
      }

    } catch (Exception exception) {
      logger.error("An error occurred while processing transaction signing requests.", exception);
    }
  }

  @Nullable
  private List<TransferValidationRequest> getRequests() {
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

  private void confirm(Long transferValidationRequestId, String policyResult, String signature) {
    var conformationRequest =
        TransferValidationRequestsOuterClass.ConfirmTransferValidationRequestRequest.newBuilder()
            .setRequestId(
                String.format("Guardian:TransferValidationRequest:%d", transferValidationRequestId))
            .setTransferValidationRequestId(transferValidationRequestId)
            .setPolicyResult(policyResult)
            .setSignature(signature)
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
          String.format("Transfer validation request confirmed. %d", transferValidationRequestId));
    }
  }

  private void reject(
      Long transferValidationRequestId,
      TransferValidationRequestsOuterClass.TransferValidationRequestRejectionReason reason,
      String message,
      String policyResult,
      String signature) {
    var rejectRequest =
        TransferValidationRequestsOuterClass.RejectTransferValidationRequestRequest.newBuilder()
            .setRequestId(
                String.format("Guardian:TransferValidationRequest:%d", transferValidationRequestId))
            .setTransferValidationRequestId(transferValidationRequestId)
            .setReasonMessage(message)
            .setReason(reason)
            .setPolicyResult(policyResult)
            .setSignature(signature)
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
          String.format("Transfer validation request rejected. %d", transferValidationRequestId));
    }
  }

  private TransferValidationRequest map(
      TransferValidationRequestsOuterClass.TransferValidationRequest validationRequest) {
    return TransferValidationRequest.create(
        validationRequest.getId(),
        map(validationRequest.getDetails()),
        validationRequest.getCustomerSignature(),
        validationRequest.getSiriusSignature());
  }

  private TransferDetails map(
      TransferValidationRequestsOuterClass.TransferDetails transferDetails) {
    return new TransferDetails(
        transferDetails.getOperationId(),
        map(transferDetails.getBlockchain()),
        map(transferDetails.getAsset()),
        map(transferDetails.getSourceAddress()),
        map(transferDetails.getDestinationAddress()),
        new BigDecimal(transferDetails.getAmount().getValue()),
        new BigDecimal(transferDetails.getFeeLimit().getValue()),
        map(transferDetails.getClientContext()));
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
            ? map(destinationAddress.getTagType().getTagType())
            : TagType.None);
  }

  private ClientContext map(TransferValidationRequestsOuterClass.ClientContext clientContext) {
    return new ClientContext(
        clientContext.hasUserId() ? clientContext.getUserId().getValue() : null,
        clientContext.hasApiKeyId() ? clientContext.getApiKeyId().getValue() : null,
        clientContext.hasAccountReferenceId()
            ? clientContext.getAccountReferenceId().getValue()
            : null,
        clientContext.hasWithdrawalReferenceId()
            ? clientContext.getWithdrawalReferenceId().getValue()
            : null,
        clientContext.hasIp() ? clientContext.getIp().getValue() : null,
        clientContext.hasTimestamp() ? map(clientContext.getTimestamp().getTimestamp()) : null);
  }

  private Instant map(Timestamp timestamp) {
    return Instant.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos());
  }
}
