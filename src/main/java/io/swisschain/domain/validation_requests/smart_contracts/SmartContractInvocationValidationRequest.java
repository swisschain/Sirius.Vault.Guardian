package io.swisschain.domain.validation_requests.smart_contracts;

import io.swisschain.contracts.smart_contracts.invocation.SmartContractInvocation;
import io.swisschain.domain.validation_requests.ValidationRequest;
import io.swisschain.domain.validation_requests.ValidationRequestStatus;

import java.time.Instant;

public class SmartContractInvocationValidationRequest extends ValidationRequest {
  private SmartContractInvocation smartContractInvocation;

  public SmartContractInvocationValidationRequest() {
    super();
  }

  public SmartContractInvocationValidationRequest(
      long id,
      String tenantId,
      SmartContractInvocation smartContractInvocation,
      ValidationRequestStatus status,
      String document,
      String signature,
      String rejectReasonMessage,
      Instant createdAt,
      Instant updatedAt) {
    super(id, tenantId, status, document, signature, rejectReasonMessage, createdAt, updatedAt);
    this.smartContractInvocation = smartContractInvocation;
  }

  public static SmartContractInvocationValidationRequest create(
      long id, String tenantId, SmartContractInvocation smartContractInvocation) {
    var createdAt = Instant.now();
    return new SmartContractInvocationValidationRequest(
        id,
        tenantId,
        smartContractInvocation,
        ValidationRequestStatus.New,
        null,
        null,
        null,
        createdAt,
        createdAt);
  }

  public SmartContractInvocation getSmartContractInvocation() {
    return smartContractInvocation;
  }

  public void setSmartContractInvocation(SmartContractInvocation smartContractInvocation) {
    this.smartContractInvocation = smartContractInvocation;
  }
}
