package io.swisschain.policies.smart_contract_invocations;

import io.swisschain.contracts.documents.Document;
import io.swisschain.contracts.documents.DocumentStatus;
import io.swisschain.contracts.documents.ValidatorResolution;
import io.swisschain.contracts.documents.smart_contracts.invocation.SmartContractInvocationDocument;
import io.swisschain.crypto.asymmetric.AsymmetricEncryptionService;
import io.swisschain.domain.validation_requests.ValidationRequest;
import io.swisschain.domain.validation_requests.smart_contracts.SmartContractInvocationValidationRequest;
import io.swisschain.policies.DocumentBuilder;
import io.swisschain.services.JsonSerializer;

import java.time.Instant;
import java.util.List;

public class SmartContractInvocationDocumentBuilder extends DocumentBuilder {
  public SmartContractInvocationDocumentBuilder(
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
    return new SmartContractInvocationDocument(
        validationRequest.getId(),
        validationRequest.getTenantId(),
        ((SmartContractInvocationValidationRequest) validationRequest).getSmartContractInvocation(),
        validatorResolutions,
        requestedValidators,
        status,
        validationRequest.getRejectReasonMessage(),
        Instant.now());
  }
}
