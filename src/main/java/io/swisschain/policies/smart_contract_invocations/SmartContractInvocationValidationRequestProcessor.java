package io.swisschain.policies.smart_contract_invocations;

import io.swisschain.domain.exceptions.AlreadyExistsException;
import io.swisschain.domain.validation_requests.ValidationRequestStatus;
import io.swisschain.domain.validation_requests.smart_contracts.SmartContractInvocationValidationRequest;
import io.swisschain.policies.DocumentValidator;
import io.swisschain.repositories.smart_contract_invocation_validation_requests.SmartContractInvocationValidationRequestRepository;
import io.swisschain.services.SmartContractInvocationApiService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class SmartContractInvocationValidationRequestProcessor {

  private static final Logger logger = LogManager.getLogger();

  private final SmartContractInvocationValidationPolicy validationPolicy;
  private final SmartContractInvocationApiService apiService;
  private final SmartContractInvocationValidationRequestRepository validationRequestRepository;
  private final SmartContractInvocationDocumentBuilder documentBuilder;
  private final DocumentValidator documentValidator;

  public SmartContractInvocationValidationRequestProcessor(
      SmartContractInvocationValidationPolicy validationPolicy,
      SmartContractInvocationApiService apiService,
      SmartContractInvocationValidationRequestRepository validationRequestRepository,
      SmartContractInvocationDocumentBuilder documentBuilder,
      DocumentValidator documentValidator) {
    this.validationPolicy = validationPolicy;
    this.apiService = apiService;
    this.validationRequestRepository = validationRequestRepository;
    this.documentBuilder = documentBuilder;
    this.documentValidator = documentValidator;
  }

  public void process(
      SmartContractInvocationValidationRequest smartContractInvocationValidationRequest)
      throws Exception {
    var existingSmartContractInvocationValidationRequest =
        validationRequestRepository.getById(smartContractInvocationValidationRequest.getId());

    if (existingSmartContractInvocationValidationRequest == null) {
      try {
        validationRequestRepository.insert(smartContractInvocationValidationRequest);
        logger.info(
            String.format(
                "Smart contract invocation validation request received. Id: %s",
                smartContractInvocationValidationRequest.getId()));
      } catch (AlreadyExistsException exception) {
        logger.warn(
            String.format(
                "Smart contract invocation validation request already exists. Id: %d",
                smartContractInvocationValidationRequest.getId()));
      }
    } else {
      if (existingSmartContractInvocationValidationRequest.getStatus()
          != ValidationRequestStatus.New) {
        return;
      }
      smartContractInvocationValidationRequest = existingSmartContractInvocationValidationRequest;
    }

    var signatureValidationResult =
        documentValidator.validateWithApiKey(
            smartContractInvocationValidationRequest
                .getSmartContractInvocation()
                .getContext()
                .getDocument(),
            smartContractInvocationValidationRequest
                .getSmartContractInvocation()
                .getContext()
                .getSignature(),
            smartContractInvocationValidationRequest
                .getSmartContractInvocation()
                .getContext()
                .getRequestContext()
                .getApiKeyId(),
            smartContractInvocationValidationRequest.getTenantId());

    if (!signatureValidationResult.isValid()) {
      smartContractInvocationValidationRequest.reject(signatureValidationResult.getReason());
      var signedDocument =
          documentBuilder.build(smartContractInvocationValidationRequest, new ArrayList<>());
      smartContractInvocationValidationRequest.updateDocument(
          signedDocument.getDocument(), signedDocument.getSignature());
      apiService.reject(smartContractInvocationValidationRequest);
      validationRequestRepository.update(smartContractInvocationValidationRequest);
    } else {
      validationPolicy.execute(smartContractInvocationValidationRequest);
    }
  }
}
