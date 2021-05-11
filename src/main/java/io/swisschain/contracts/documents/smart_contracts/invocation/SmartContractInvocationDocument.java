package io.swisschain.contracts.documents.smart_contracts.invocation;

import io.swisschain.contracts.documents.Document;
import io.swisschain.contracts.documents.DocumentStatus;
import io.swisschain.contracts.documents.ValidatorResolution;
import io.swisschain.contracts.smart_contracts.invocation.SmartContractInvocation;

import java.time.Instant;
import java.util.List;

/**
 * Represents final signed document created by Guardian for the smart contract invocation validation
 * request.
 */
public class SmartContractInvocationDocument extends Document {

  private SmartContractInvocation invocation;

  public SmartContractInvocationDocument() {
    super();
  }

  public SmartContractInvocationDocument(
      long validationRequestId,
      String tenantId,
      SmartContractInvocation invocation,
      List<ValidatorResolution> validatorResolutions,
      List<String> requestedValidators,
      DocumentStatus status,
      String rejectReasonMessage,
      Instant timestamp) {
    super(
        validationRequestId,
        tenantId,
        validatorResolutions,
        requestedValidators,
        status,
        rejectReasonMessage,
        timestamp);
    this.invocation = invocation;
  }

  /** Gets the smart contract invocation details that have been validated. */
  public SmartContractInvocation getInvocation() {
    return invocation;
  }

  /** Sets the smart contract invocation details that have been validated. */
  public void setInvocation(SmartContractInvocation invocation) {
    this.invocation = invocation;
  }
}
