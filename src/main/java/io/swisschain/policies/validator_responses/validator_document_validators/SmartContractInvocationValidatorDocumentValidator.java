package io.swisschain.policies.validator_responses.validator_document_validators;

import io.swisschain.contracts.documents.ValidatorDocument;
import io.swisschain.contracts.documents.smart_contracts.invocation.SmartContractInvocationValidatorDocument;
import io.swisschain.contracts.smart_contracts.invocation.SmartContractInvocation;
import io.swisschain.services.JsonSerializer;

public class SmartContractInvocationValidatorDocumentValidator
    implements ValidatorDocumentValidator {

  private final JsonSerializer jsonSerializer;

  public SmartContractInvocationValidatorDocumentValidator(JsonSerializer jsonSerializer) {
    this.jsonSerializer = jsonSerializer;
  }

  @Override
  public boolean validate(ValidatorDocument document, String originMessage) {
    SmartContractInvocation originSmartContractInvocation;

    try {
      originSmartContractInvocation =
          jsonSerializer.deserialize(originMessage, SmartContractInvocation.class);
    } catch (Exception exception) {
      return false;
    }

    var smartContractInvocation =
        ((SmartContractInvocationValidatorDocument) document).getInvocation();

    return smartContractInvocation.equals(originSmartContractInvocation);
  }
}
