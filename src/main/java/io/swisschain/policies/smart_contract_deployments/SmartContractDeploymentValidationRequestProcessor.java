package io.swisschain.policies.smart_contract_deployments;

import io.swisschain.domain.exceptions.AlreadyExistsException;
import io.swisschain.domain.validation_requests.ValidationRequestStatus;
import io.swisschain.domain.validation_requests.smart_contract_deployments.SmartContractDeploymentValidationRequest;
import io.swisschain.policies.DocumentValidator;
import io.swisschain.repositories.smart_contract_deployment_validation_requests.SmartContractDeploymentValidationRequestRepository;
import io.swisschain.services.SmartContractDeploymentApiService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class SmartContractDeploymentValidationRequestProcessor {

  private static final Logger logger = LogManager.getLogger();

  private final SmartContractDeploymentValidationPolicy validationPolicy;
  private final SmartContractDeploymentApiService apiService;
  private final SmartContractDeploymentValidationRequestRepository validationRequestRepository;
  private final SmartContractDeploymentDocumentBuilder documentBuilder;
  private final DocumentValidator documentValidator;

  public SmartContractDeploymentValidationRequestProcessor(
      SmartContractDeploymentValidationPolicy validationPolicy,
      SmartContractDeploymentApiService apiService,
      SmartContractDeploymentValidationRequestRepository validationRequestRepository,
      SmartContractDeploymentDocumentBuilder documentBuilder,
      DocumentValidator documentValidator) {
    this.validationPolicy = validationPolicy;
    this.apiService = apiService;
    this.validationRequestRepository = validationRequestRepository;
    this.documentBuilder = documentBuilder;
    this.documentValidator = documentValidator;
  }

  public void process(
      SmartContractDeploymentValidationRequest smartContractDeploymentValidationRequest)
      throws Exception {
    var existingSmartContractDeploymentValidationRequest =
        validationRequestRepository.getById(smartContractDeploymentValidationRequest.getId());

    if (existingSmartContractDeploymentValidationRequest == null) {
      try {
        validationRequestRepository.insert(smartContractDeploymentValidationRequest);
        logger.info(
            String.format(
                "Transfer validation request received. Id: %s",
                smartContractDeploymentValidationRequest.getId()));
      } catch (AlreadyExistsException exception) {
        logger.warn(
            String.format(
                "Transfer validation request already exists. Id: %d",
                smartContractDeploymentValidationRequest.getId()));
      }
    } else {
      if (existingSmartContractDeploymentValidationRequest.getStatus()
          != ValidationRequestStatus.New) {
        return;
      }
      smartContractDeploymentValidationRequest = existingSmartContractDeploymentValidationRequest;
    }

    var signatureValidationResult =
        documentValidator.validateWithApiKey(
            smartContractDeploymentValidationRequest
                .getSmartContractDeployment()
                .getContext()
                .getDocument(),
            smartContractDeploymentValidationRequest
                .getSmartContractDeployment()
                .getContext()
                .getSignature(),
            smartContractDeploymentValidationRequest
                .getSmartContractDeployment()
                .getContext()
                .getRequestContext()
                .getApiKeyId(),
            smartContractDeploymentValidationRequest.getTenantId());

    if (!signatureValidationResult.isValid()) {
      smartContractDeploymentValidationRequest.reject(signatureValidationResult.getReason());
      var signedDocument =
          documentBuilder.build(smartContractDeploymentValidationRequest, new ArrayList<>());
      smartContractDeploymentValidationRequest.updateDocument(
          signedDocument.getDocument(), signedDocument.getSignature());
      apiService.reject(smartContractDeploymentValidationRequest);
      validationRequestRepository.update(smartContractDeploymentValidationRequest);
    } else {
      validationPolicy.execute(smartContractDeploymentValidationRequest);
    }
  }
}
