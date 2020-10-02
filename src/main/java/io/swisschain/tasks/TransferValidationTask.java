package io.swisschain.tasks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.Timestamp;
import io.grpc.StatusRuntimeException;
import io.swisschain.crypto.asymmetric.AsymmetricEncryptionService;
import io.swisschain.crypto.symmetric.SymmetricEncryptionService;
import io.swisschain.domain.policies.PolicyOutputAction;
import io.swisschain.domain.transfers.*;
import io.swisschain.mappers.NetworkTypeMapper;
import io.swisschain.primitives.TagType;
import io.swisschain.repositories.exceptions.AlreadyExistsException;
import io.swisschain.repositories.transfers.TransferValidationRequestRepository;
import io.swisschain.services.PolicyService;
import io.swisschain.services.ValidatorApprovalRequestService;
import io.swisschain.services.ValidatorService;
import io.swisschain.sirius.guardianApi.GuardianApiClient;
import io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests;
import io.swisschain.sirius.vaultApi.VaultApiClient;
import io.swisschain.sirius.vaultApi.generated.common.Common;
import io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class TransferValidationTask implements Runnable {
  private static final Logger logger = LogManager.getLogger();

  private final VaultApiClient vaultApiClient;
  private final GuardianApiClient guardianApiClient;
  private final TransferValidationRequestRepository transferValidationRequestRepository;
  private final AsymmetricEncryptionService asymmetricEncryptionService;
  private final SymmetricEncryptionService symmetricEncryptionService;
  private final ValidatorService validatorService;
  private final PolicyService policyService;
  private final String hostProcessId;
  private final ObjectMapper mapper = new ObjectMapper();

  public TransferValidationTask(
      VaultApiClient vaultApiClient,
      GuardianApiClient guardianApiClient,
      TransferValidationRequestRepository transferValidationRequestRepository,
      AsymmetricEncryptionService asymmetricEncryptionService,
      SymmetricEncryptionService symmetricEncryptionService,
      ValidatorService validatorService,
      PolicyService policyService,
      String hostProcessId) {
    this.vaultApiClient = vaultApiClient;
    this.guardianApiClient = guardianApiClient;
    this.transferValidationRequestRepository = transferValidationRequestRepository;
    this.asymmetricEncryptionService = asymmetricEncryptionService;
    this.symmetricEncryptionService = symmetricEncryptionService;
    this.validatorService = validatorService;
    this.policyService = policyService;
    this.hostProcessId = hostProcessId;

    logger.info("TransferValidationTask created");
  }
  /*
   @Override
   public void run() {
     if(ValidatorApprovalRequestService.payload == null){
       ValidatorApprovalRequestService.payload = ValidatorApprovalRequestService.Create();

       var mapper = new ObjectMapper();

       String json = "";
       try {
         json = mapper.writeValueAsString(ValidatorApprovalRequestService.payload);
       } catch (JsonProcessingException e) {
         e.printStackTrace();
       }

       var validator = validatorService.getAll().stream().findFirst().get();

       ValidatorApprovalRequestService.key = symmetricEncryptionService.generateKey();
       ValidatorApprovalRequestService.nonce = symmetricEncryptionService.generateNonce();

       var encryptedJson = symmetricEncryptionService.encrypt(
               json.getBytes(StandardCharsets.UTF_8),
               ValidatorApprovalRequestService.key,
               ValidatorApprovalRequestService.nonce);

       byte[] encryptedKey = null;
       try {
         encryptedKey = asymmetricEncryptionService.encrypt(
                 ValidatorApprovalRequestService.key,
                 validator.getPublicKey());
       } catch (IOException | InvalidCipherTextException e) {
         e.printStackTrace();
       }

       var validatorRequest = ValidatorApprovalRequests.CreateApprovalRequestRequest.ValidatorRequest.newBuilder()
               .setValidaditorId(validator.id)
               .setTransactionDetailsEncBase64(Base64.getEncoder().encodeToString(encryptedJson))
               .setSecretEncBase64(Base64.getEncoder().encodeToString(encryptedKey))
               .setIvNonce(Base64.getEncoder().encodeToString(ValidatorApprovalRequestService.nonce))
               .build();

       var request = ValidatorApprovalRequests.CreateApprovalRequestRequest.newBuilder()
               .setRequestId(String.format("request-%d", ValidatorApprovalRequestService.transferSigningRequestId))
               .setTransferSigningRequestId(Integer.toString(ValidatorApprovalRequestService.transferSigningRequestId))
               .addValidatorRequests(validatorRequest)
               .build();

       var response = guardianApiClient.getTransactions().createApprovalRequest(request);

     }
   }

  */

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
        logger.info(String.format("Transfer validation request received. Id: %s", request.getId()));

        if (!validatorService.hasValidators()) {
          confirm(request.getId(), "policy result", "signature");
          continue;
        }

        try {
          var transferValidationRequest =
              transferValidationRequestRepository.getById(request.getId());

          if (transferValidationRequest == null) {
            try {
              transferValidationRequestRepository.insert(request);
            } catch (AlreadyExistsException exception) {
              logger.warn(
                  String.format(
                      "Transfer validation request already processed. Id: %d", request.getId()));
            }

            transferValidationRequest = request;
          }

          if (transferValidationRequest.getStatus() == TransferValidationRequestStatus.Approved) {
            confirm(transferValidationRequest.getId(), "policy result", "signature");

            logger.info(
                String.format("Transfer validation request confirmed. Id: %s", request.getId()));
            continue;
          }

          if (transferValidationRequest.getStatus() == TransferValidationRequestStatus.Rejected) {
            reject(
                request.getId(),
                TransferValidationRequestsOuterClass.TransferValidationRequestRejectionReason
                    .REJECTED_POLICY,
                "Invalid policy.",
                "policy result",
                "signature");
            logger.info(
                String.format("Transfer validation request rejected. Id: %s", request.getId()));
            continue;
          }

          if (transferValidationRequest.getStatus() == TransferValidationRequestStatus.Pending) {
            var policyResult = policyService.apply(request);

            if (policyResult.getAction() == PolicyOutputAction.Approve) {
              transferValidationRequest.validate();
              transferValidationRequestRepository.update(transferValidationRequest);

              requestApprovals(request);
            } else {
              transferValidationRequest.approve();
              transferValidationRequestRepository.update(transferValidationRequest);

              confirm(request.getId(), "policy result", "signature");
            }
          }
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

  private void requestApprovals(TransferValidationRequest transferValidationRequest)
      throws JsonProcessingException {
    var requestBuilder =
        ValidatorApprovalRequests.CreateApprovalRequestRequest.newBuilder()
            .setRequestId(String.format("Guardian:Create:%d", transferValidationRequest.getId()))
            .setTransferSigningRequestId(Long.toString(transferValidationRequest.getId()));

    var transferDetails = map(transferValidationRequest.getDetails());

    var json = mapper.writeValueAsString(transferDetails);

    var encryptedJson =
        symmetricEncryptionService.encrypt(
            json.getBytes(StandardCharsets.UTF_8),
            ValidatorApprovalRequestService.key,
            ValidatorApprovalRequestService.nonce);

    for (var validator : validatorService.getAll()) {
      byte[] encryptedKey = null;
      try {
        encryptedKey =
            asymmetricEncryptionService.encrypt(
                ValidatorApprovalRequestService.key, validator.getPublicKey());
      } catch (IOException | InvalidCipherTextException e) {
        e.printStackTrace();
      }

      var validatorRequestBuilder =
          ValidatorApprovalRequests.CreateApprovalRequestRequest.ValidatorRequest.newBuilder()
              .setValidaditorId(validator.id)
              .setTransactionDetailsEncBase64(Base64.getEncoder().encodeToString(encryptedJson))
              .setSecretEncBase64(Base64.getEncoder().encodeToString(encryptedKey))
              .setIvNonce(
                  Base64.getEncoder().encodeToString(ValidatorApprovalRequestService.nonce));
      requestBuilder.addValidatorRequests(validatorRequestBuilder);
    }

    var response =
        guardianApiClient.getTransactions().createApprovalRequest(requestBuilder.build());

    logger.info(String.format("Approval requests sent. Id: %s", transferValidationRequest.getId()));
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

  private io.swisschain.domain.messages.TransferDetails map(
      io.swisschain.domain.transfers.TransferDetails transferDetail) {
    return new io.swisschain.domain.messages.TransferDetails(
        transferDetail.getAmount().toString(),
        new io.swisschain.domain.messages.Asset(
            transferDetail.getAsset().getAddress(),
            Long.toString(transferDetail.getAsset().getId()),
            transferDetail.getAsset().getSymbol()),
        transferDetail.getBlockchain().getId(),
        transferDetail.getBlockchain().getProtocolId(),
        new io.swisschain.domain.messages.ClientContext(
            transferDetail.getClientContext().getUserId(),
            transferDetail.getClientContext().getApiKeyId(),
            transferDetail.getClientContext().getAccountReferenceId(),
            transferDetail.getClientContext().getWithdrawalReferenceId(),
            transferDetail.getClientContext().getIp(),
            transferDetail.getClientContext().getTimestamp() != null
                ? transferDetail.getClientContext().getTimestamp().toString()
                : null),
        new io.swisschain.domain.messages.DestinationAddress(
            transferDetail.getDestinationAddress().getAddress(),
            transferDetail.getDestinationAddress().getName(),
            transferDetail.getDestinationAddress().getGroup(),
            transferDetail.getDestinationAddress().getTag(),
            transferDetail.getDestinationAddress().getTagType().name()),
        transferDetail.getFeeLimit().toString(),
        transferDetail.getBlockchain().getNetworkType().name(),
        Long.toString(transferDetail.getOperationId()),
        new io.swisschain.domain.messages.SourceAddress(
            transferDetail.getSourceAddress().getAddress(),
            transferDetail.getSourceAddress().getName(),
            transferDetail.getSourceAddress().getGroup()));
  }
}
