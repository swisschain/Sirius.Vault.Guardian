package io.swisschain.policies.smart_contract_deployments;

import io.swisschain.domain.policies.RuleExecutionAction;
import io.swisschain.domain.validation_requests.smart_contracts.SmartContractDeploymentValidationRequest;
import io.swisschain.domain.validator_messages.MessageType;
import io.swisschain.domain.validators.ValidatorRequestType;
import io.swisschain.policies.ValidationPolicy;
import io.swisschain.policies.validator_requests.ValidatorRequestProcessor;
import io.swisschain.repositories.smart_contract_deployment_validation_requests.SmartContractDeploymentValidationRequestRepository;
import io.swisschain.repositories.validator_requests.ValidatorRequestRepository;
import io.swisschain.services.JsonSerializer;
import io.swisschain.services.SmartContractDeploymentApiService;
import io.swisschain.services.ValidatorApiService;

public class SmartContractDeploymentValidationPolicy implements ValidationPolicy {

  private final SmartContractDeploymentRuleExecutor ruleExecutor;
  private final ValidatorApiService validatorApiService;
  private final SmartContractDeploymentApiService smartContractDeploymentApiService;
  private final SmartContractDeploymentValidationRequestRepository
      smartContractDeploymentValidationRequestRepository;
  private final ValidatorRequestRepository validatorRequestRepository;
  private final ValidatorRequestProcessor validatorRequestProcessor;
  private final SmartContractDeploymentDocumentBuilder documentBuilder;
  private final JsonSerializer jsonSerializer;

  public SmartContractDeploymentValidationPolicy(
      SmartContractDeploymentRuleExecutor ruleExecutor,
      ValidatorApiService validatorApiService,
      SmartContractDeploymentApiService smartContractDeploymentApiService,
      SmartContractDeploymentValidationRequestRepository
          smartContractDeploymentValidationRequestRepository,
      ValidatorRequestRepository validatorRequestRepository,
      ValidatorRequestProcessor validatorRequestProcessor,
      SmartContractDeploymentDocumentBuilder documentBuilder,
      JsonSerializer jsonSerializer) {
    this.ruleExecutor = ruleExecutor;
    this.validatorApiService = validatorApiService;
    this.smartContractDeploymentApiService = smartContractDeploymentApiService;
    this.smartContractDeploymentValidationRequestRepository =
        smartContractDeploymentValidationRequestRepository;
    this.validatorRequestRepository = validatorRequestRepository;
    this.validatorRequestProcessor = validatorRequestProcessor;
    this.documentBuilder = documentBuilder;
    this.jsonSerializer = jsonSerializer;
  }

  public void execute(long smartContractDeploymentValidationRequestId) throws Exception {
    var smartContractDeploymentValidationRequest =
        smartContractDeploymentValidationRequestRepository.getById(
            smartContractDeploymentValidationRequestId);

    execute(smartContractDeploymentValidationRequest);
  }

  public void execute(
      SmartContractDeploymentValidationRequest smartContractDeploymentValidationRequest)
      throws Exception {
    var validatorRequests =
        validatorRequestRepository.getByValidationRequestId(
            smartContractDeploymentValidationRequest.getId(),
            ValidatorRequestType.SmartContractDeployment);

    var validators =
        validatorApiService.get(smartContractDeploymentValidationRequest.getTenantId());

    var ruleExecutionOutput =
        ruleExecutor.execute(
            smartContractDeploymentValidationRequest, validatorRequests, validators);

    if (ruleExecutionOutput.getAction() == RuleExecutionAction.Validate) {
      smartContractDeploymentValidationRequest.processing();
      var message =
          jsonSerializer.serialize(
              smartContractDeploymentValidationRequest.getSmartContractDeployment());
      validatorRequestProcessor.process(
          smartContractDeploymentValidationRequest.getId(),
          smartContractDeploymentValidationRequest.getTenantId(),
          message,
          MessageType.SmartContractDeployment,
          ValidatorRequestType.SmartContractDeployment,
          ruleExecutionOutput.getValidators());
    } else {
      if (ruleExecutionOutput.getAction() == RuleExecutionAction.Approve) {
        smartContractDeploymentValidationRequest.approve();
        var signedDocument =
            documentBuilder.build(smartContractDeploymentValidationRequest, validatorRequests);
        smartContractDeploymentValidationRequest.updateDocument(
            signedDocument.getDocument(), signedDocument.getSignature());
        smartContractDeploymentApiService.confirm(smartContractDeploymentValidationRequest);
      } else if (ruleExecutionOutput.getAction() == RuleExecutionAction.Reject) {
        smartContractDeploymentValidationRequest.reject(
            ruleExecutionOutput.getRejectReasonMessage());
        var signedDocument =
            documentBuilder.build(smartContractDeploymentValidationRequest, validatorRequests);
        smartContractDeploymentValidationRequest.updateDocument(
            signedDocument.getDocument(), signedDocument.getSignature());
        smartContractDeploymentApiService.reject(smartContractDeploymentValidationRequest);
      }
    }
    smartContractDeploymentValidationRequestRepository.update(
        smartContractDeploymentValidationRequest);
  }
}
