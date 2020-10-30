package io.swisschain.services;

import io.swisschain.domain.policies.RuleExecutionOutput;
import io.swisschain.domain.transfers.TransferValidationRequest;
import io.swisschain.domain.validators.Validator;
import io.swisschain.domain.validators.ValidatorResponse;
import io.swisschain.odm.OdmClient;
import io.swisschain.odm.contracts.policy.*;
import io.swisschain.odm.contracts.selector.PolicySelectorRequest;
import io.swisschain.odm.contracts.selector.PolicySelectorResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OdmRuleExecutor implements RuleExecutor {
  private static final Logger logger = LogManager.getLogger();

  private final OdmClient odmClient;

  public OdmRuleExecutor(OdmClient odmClient) {
    this.odmClient = odmClient;
  }

  public RuleExecutionOutput execute(
      TransferValidationRequest transferValidationRequest,
      List<ValidatorResponse> validatorResponses,
      List<Validator> validators)
      throws Exception {

    var requestId =
        String.format(
            "Transfer:%d:%s", transferValidationRequest.getId(), Instant.now().toString());

    PolicySelectorResponse policy;

    try {
      policy =
          odmClient.getPolicy(
              new PolicySelectorRequest(
                  transferValidationRequest.getTenantId(),
                  null, // TODO: set broker account id
                  null, // TODO: set account id
                  transferValidationRequest.getTransferDetails().getBlockchain().getId(),
                  transferValidationRequest.getTransferDetails().getBlockchain().getNetworkType(),
                  transferValidationRequest.getTransferDetails().getSourceAddress().getAddress()),
              requestId);
    } catch (Exception exception) {
      throw new Exception(
          String.format(
              "An error occurred while getting policy. TenantId: %s; BlockchainId: %s; NetworkType: %s; Address: %s",
              transferValidationRequest.getTenantId(),
              transferValidationRequest.getTransferDetails().getBlockchain().getId(),
              transferValidationRequest
                  .getTransferDetails()
                  .getBlockchain()
                  .getNetworkType()
                  .name(),
              transferValidationRequest.getTransferDetails().getSourceAddress().getAddress()),
          exception);
    }

    PolicyResponse result;

    try {
      result =
          odmClient.getResolution(
              createPolicyRequest(transferValidationRequest, validatorResponses),
              requestId,
              policy.policyService,
              policy.policyServiceVersion,
              policy.policy,
              policy.policyVersion);
    } catch (Exception exception) {
      throw new Exception(
          String.format(
              "An error occurred while executing policy. TransferValidationRequestId: %d; PolicyService: %s; PolicyServiceVersion: %s; Policy: %s; PolicyVersion: %s",
              transferValidationRequest.getId(),
              policy.policyService,
              policy.policyServiceVersion,
              policy.policy,
              policy.policyVersion),
          exception);
    }

    switch (result.resolution) {
      case Approve:
        return RuleExecutionOutput.createApprove();
      case Decline:
        return RuleExecutionOutput.createReject(result.comment);
      case Wait:
        var selectedValidators = new ArrayList<Validator>();
        var notFoundValidators = new ArrayList<String>();
        for (var validatorId : result.notifyValidators) {
          var validator =
              validators.stream().filter(item -> item.getId().equals(validatorId)).findFirst();
          if (validator.isPresent()) {
            selectedValidators.add(validator.get());
          } else {
            notFoundValidators.add(validatorId);
            logger.warn(String.format("Validator not found. %s", validatorId));
          }
        }

        if (notFoundValidators.size() > 0) {
          return RuleExecutionOutput.createReject(
              String.format(
                  "Transfer can not be validated. Validators not found: %s",
                  String.join(",", notFoundValidators)));
        }

        return RuleExecutionOutput.createValidate(selectedValidators);
      default:
        throw new Exception(
            String.format("Unknown policy resolution. %s", result.resolution.name()));
    }
  }

  private PolicyRequest createPolicyRequest(
      TransferValidationRequest transferValidationRequest,
      List<ValidatorResponse> validatorResponses)
      throws Exception {
    var validatedBy = new ArrayList<ValidationResult>();

    for (var validatorResponse : validatorResponses) {

      ValidatorResolution validatorResolution;

      switch (validatorResponse.getResolution()) {
        case Approved:
          validatorResolution = ValidatorResolution.Approved;
          break;
        case Rejected:
          validatorResolution = ValidatorResolution.Rejected;
          break;
        case Skipped:
          validatorResolution = ValidatorResolution.Skipped;
          break;
        default:
          throw new Exception(
              String.format(
                  "Unknown validator resolution. %s", validatorResponse.getResolution().name()));
      }

      validatedBy.add(
          new ValidationResult(validatorResponse.getValidatorId(), validatorResolution));
    }

    return new PolicyRequest(
        new TransferDetails(
            transferValidationRequest.getTransferDetails().getOperationId(),
            new Blockchain(
                transferValidationRequest.getTransferDetails().getBlockchain().getId(),
                transferValidationRequest.getTransferDetails().getBlockchain().getProtocolId(),
                transferValidationRequest.getTransferDetails().getBlockchain().getNetworkType()),
            new Asset(
                transferValidationRequest.getTransferDetails().getAsset().getId(),
                transferValidationRequest.getTransferDetails().getAsset().getSymbol(),
                transferValidationRequest.getTransferDetails().getAsset().getAddress()),
            new SourceAddress(
                transferValidationRequest.getTransferDetails().getSourceAddress().getAddress(),
                transferValidationRequest.getTransferDetails().getSourceAddress().getName(),
                transferValidationRequest.getTransferDetails().getSourceAddress().getGroup()),
            new DestinationAddress(
                transferValidationRequest.getTransferDetails().getDestinationAddress().getAddress(),
                transferValidationRequest.getTransferDetails().getDestinationAddress().getName(),
                transferValidationRequest.getTransferDetails().getDestinationAddress().getGroup(),
                transferValidationRequest.getTransferDetails().getDestinationAddress().getTag(),
                transferValidationRequest
                    .getTransferDetails()
                    .getDestinationAddress()
                    .getTagType()),
            transferValidationRequest.getTransferDetails().getAmount(),
            transferValidationRequest.getTransferDetails().getFeeLimit()),
        validatedBy);
  }
}
