package io.swisschain.policies.smart_contract_deployments;

import io.swisschain.contracts.documents.Document;
import io.swisschain.contracts.documents.DocumentStatus;
import io.swisschain.contracts.documents.ValidatorResolution;
import io.swisschain.contracts.documents.smart_contracts.SmartContractDeploymentDocument;
import io.swisschain.crypto.asymmetric.AsymmetricEncryptionService;
import io.swisschain.domain.validation_requests.ValidationRequest;
import io.swisschain.domain.validation_requests.smart_contract_deployments.SmartContractDeploymentValidationRequest;
import io.swisschain.policies.DocumentBuilder;
import io.swisschain.services.JsonSerializer;

import java.time.Instant;
import java.util.List;

public class SmartContractDeploymentDocumentBuilder extends DocumentBuilder {
  public SmartContractDeploymentDocumentBuilder(
      AsymmetricEncryptionService asymmetricEncryptionService,
      String privateKey,
      JsonSerializer jsonSerializer) {
    super(asymmetricEncryptionService, privateKey, jsonSerializer);
  }

  @Override
  protected Document createDocument(
      ValidationRequest validationRequest,
      List<ValidatorResolution> validatorResolutions,
      List<String> requestedValidators,
      DocumentStatus status) {
    return new SmartContractDeploymentDocument(
        validationRequest.getId(),
        validationRequest.getTenantId(),
        ((SmartContractDeploymentValidationRequest) validationRequest).getSmartContractDeployment(),
        validatorResolutions,
        requestedValidators,
        status,
        validationRequest.getRejectReasonMessage(),
        Instant.now());
  }
}
