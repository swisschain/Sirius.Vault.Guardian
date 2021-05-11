package io.swisschain.policies.smart_contract_invocations;

import io.swisschain.domain.policies.RuleExecutionAction;
import io.swisschain.domain.validation_requests.smart_contracts.SmartContractInvocationValidationRequest;
import io.swisschain.domain.validator_messages.MessageType;
import io.swisschain.domain.validators.ValidatorRequestType;
import io.swisschain.policies.ValidationPolicy;
import io.swisschain.policies.validator_requests.ValidatorRequestProcessor;
import io.swisschain.repositories.smart_contract_invocation_validation_requests.SmartContractInvocationValidationRequestRepository;
import io.swisschain.repositories.validator_requests.ValidatorRequestRepository;
import io.swisschain.services.JsonSerializer;
import io.swisschain.services.SmartContractInvocationApiService;
import io.swisschain.services.ValidatorApiService;

public class SmartContractInvocationValidationPolicy implements ValidationPolicy {

  private final SmartContractInvocationRuleExecutor ruleExecutor;
  private final ValidatorApiService validatorApiService;
  private final SmartContractInvocationApiService smartContractInvocationApiService;
  private final SmartContractInvocationValidationRequestRepository
      smartContractInvocationValidationRequestRepository;
  private final ValidatorRequestRepository validatorRequestRepository;
  private final ValidatorRequestProcessor validatorRequestProcessor;
  private final SmartContractInvocationDocumentBuilder documentBuilder;
  private final JsonSerializer jsonSerializer;

  public SmartContractInvocationValidationPolicy(
      SmartContractInvocationRuleExecutor ruleExecutor,
      ValidatorApiService validatorApiService,
      SmartContractInvocationApiService smartContractInvocationApiService,
      SmartContractInvocationValidationRequestRepository
          smartContractInvocationValidationRequestRepository,
      ValidatorRequestRepository validatorRequestRepository,
      ValidatorRequestProcessor validatorRequestProcessor,
      SmartContractInvocationDocumentBuilder documentBuilder,
      JsonSerializer jsonSerializer) {
    this.ruleExecutor = ruleExecutor;
    this.validatorApiService = validatorApiService;
    this.smartContractInvocationApiService = smartContractInvocationApiService;
    this.smartContractInvocationValidationRequestRepository =
        smartContractInvocationValidationRequestRepository;
    this.validatorRequestRepository = validatorRequestRepository;
    this.validatorRequestProcessor = validatorRequestProcessor;
    this.documentBuilder = documentBuilder;
    this.jsonSerializer = jsonSerializer;
  }

  public void execute(long smartContractInvocationValidationRequestId) throws Exception {
    var smartContractInvocationValidationRequest =
        smartContractInvocationValidationRequestRepository.getById(
            smartContractInvocationValidationRequestId);

    execute(smartContractInvocationValidationRequest);
  }

  public void execute(
      SmartContractInvocationValidationRequest smartContractInvocationValidationRequest)
      throws Exception {
    var validatorRequests =
        validatorRequestRepository.getByValidationRequestId(
            smartContractInvocationValidationRequest.getId(),
            ValidatorRequestType.SmartContractInvocation);

    var validators =
        validatorApiService.get(smartContractInvocationValidationRequest.getTenantId());

    var ruleExecutionOutput =
        ruleExecutor.execute(
            smartContractInvocationValidationRequest, validatorRequests, validators);

    if (ruleExecutionOutput.getAction() == RuleExecutionAction.Validate) {
      smartContractInvocationValidationRequest.processing();
      var message =
          jsonSerializer.serialize(
              smartContractInvocationValidationRequest.getSmartContractInvocation());
      validatorRequestProcessor.process(
          smartContractInvocationValidationRequest.getId(),
          smartContractInvocationValidationRequest.getTenantId(),
          message,
          MessageType.SmartContractInvocation,
          ValidatorRequestType.SmartContractInvocation,
          ruleExecutionOutput.getValidators());
    } else {
      if (ruleExecutionOutput.getAction() == RuleExecutionAction.Approve) {
        smartContractInvocationValidationRequest.approve();
        var signedDocument =
            documentBuilder.build(smartContractInvocationValidationRequest, validatorRequests);
        smartContractInvocationValidationRequest.updateDocument(
            signedDocument.getDocument(), signedDocument.getSignature());
        smartContractInvocationApiService.confirm(smartContractInvocationValidationRequest);
      } else if (ruleExecutionOutput.getAction() == RuleExecutionAction.Reject) {
        smartContractInvocationValidationRequest.reject(
            ruleExecutionOutput.getRejectReasonMessage());
        var signedDocument =
            documentBuilder.build(smartContractInvocationValidationRequest, validatorRequests);
        smartContractInvocationValidationRequest.updateDocument(
            signedDocument.getDocument(), signedDocument.getSignature());
        smartContractInvocationApiService.reject(smartContractInvocationValidationRequest);
      }
    }
    smartContractInvocationValidationRequestRepository.update(
        smartContractInvocationValidationRequest);
  }
}
