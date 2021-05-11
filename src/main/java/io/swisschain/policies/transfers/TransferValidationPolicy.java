package io.swisschain.policies.transfers;

import io.swisschain.domain.policies.RuleExecutionAction;
import io.swisschain.domain.validation_requests.transfers.TransferValidationRequest;
import io.swisschain.domain.validator_messages.MessageType;
import io.swisschain.domain.validators.ValidatorRequestType;
import io.swisschain.policies.ValidationPolicy;
import io.swisschain.policies.validator_requests.ValidatorRequestProcessor;
import io.swisschain.repositories.transfer_validation_requests.TransferValidationRequestRepository;
import io.swisschain.repositories.validator_requests.ValidatorRequestRepository;
import io.swisschain.services.JsonSerializer;
import io.swisschain.services.TransferApiService;
import io.swisschain.services.ValidatorApiService;

public class TransferValidationPolicy implements ValidationPolicy {

  private final TransferRuleExecutor ruleExecutor;
  private final ValidatorApiService validatorApiService;
  private final TransferApiService transferApiService;
  private final TransferValidationRequestRepository transferValidationRequestRepository;
  private final ValidatorRequestRepository validatorRequestRepository;
  private final ValidatorRequestProcessor validatorRequestProcessor;
  private final TransferDocumentBuilder documentBuilder;
  private final JsonSerializer jsonSerializer;

  public TransferValidationPolicy(
      TransferRuleExecutor ruleExecutor,
      ValidatorApiService validatorApiService,
      TransferApiService transferApiService,
      TransferValidationRequestRepository transferValidationRequestRepository,
      ValidatorRequestRepository validatorRequestRepository,
      ValidatorRequestProcessor validatorRequestProcessor,
      TransferDocumentBuilder documentBuilder,
      JsonSerializer jsonSerializer) {
    this.ruleExecutor = ruleExecutor;
    this.validatorApiService = validatorApiService;
    this.transferApiService = transferApiService;
    this.transferValidationRequestRepository = transferValidationRequestRepository;
    this.validatorRequestRepository = validatorRequestRepository;
    this.validatorRequestProcessor = validatorRequestProcessor;
    this.documentBuilder = documentBuilder;
    this.jsonSerializer = jsonSerializer;
  }

  public void execute(long transferValidationRequestId) throws Exception {
    var transferValidationRequest =
        transferValidationRequestRepository.getById(transferValidationRequestId);

    execute(transferValidationRequest);
  }

  public void execute(TransferValidationRequest transferValidationRequest) throws Exception {
    var validatorRequests =
        validatorRequestRepository.getByValidationRequestId(
            transferValidationRequest.getId(), ValidatorRequestType.Transfer);

    var validators = validatorApiService.get(transferValidationRequest.getTenantId());

    var ruleExecutionOutput =
        ruleExecutor.execute(transferValidationRequest, validatorRequests, validators);

    if (ruleExecutionOutput.getAction() == RuleExecutionAction.Validate) {
      transferValidationRequest.processing();
      var message = jsonSerializer.serialize(transferValidationRequest.getTransfer());
      validatorRequestProcessor.process(
          transferValidationRequest.getId(),
          transferValidationRequest.getTenantId(),
          message,
          MessageType.Transfer,
          ValidatorRequestType.Transfer,
          ruleExecutionOutput.getValidators());
    } else {
      if (ruleExecutionOutput.getAction() == RuleExecutionAction.Approve) {
        transferValidationRequest.approve();
        var signedDocument = documentBuilder.build(transferValidationRequest, validatorRequests);
        transferValidationRequest.updateDocument(
            signedDocument.getDocument(), signedDocument.getSignature());
        transferApiService.confirm(transferValidationRequest);
      } else if (ruleExecutionOutput.getAction() == RuleExecutionAction.Reject) {
        transferValidationRequest.reject(ruleExecutionOutput.getRejectReasonMessage());
        var signedDocument = documentBuilder.build(transferValidationRequest, validatorRequests);
        transferValidationRequest.updateDocument(
            signedDocument.getDocument(), signedDocument.getSignature());
        transferApiService.reject(transferValidationRequest);
      }
    }
    transferValidationRequestRepository.update(transferValidationRequest);
  }
}
