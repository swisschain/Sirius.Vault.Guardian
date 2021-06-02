package io.swisschain.domain.validation_requests.smart_contracts;

import io.swisschain.contracts.smart_contracts.deployment.SmartContractDeployment;
import io.swisschain.domain.validation_requests.ValidationRequest;
import io.swisschain.domain.validation_requests.ValidationRequestStatus;

import java.time.Instant;

public class SmartContractDeploymentValidationRequest extends ValidationRequest {
  private SmartContractDeployment smartContractDeployment;

  public SmartContractDeploymentValidationRequest() {
    super();
  }

  public SmartContractDeploymentValidationRequest(
      long id,
      String tenantId,
      long vaultId,
      SmartContractDeployment smartContractDeployment,
      ValidationRequestStatus status,
      String document,
      String signature,
      String rejectReasonMessage,
      Instant createdAt,
      Instant updatedAt) {
    super(
        id,
        tenantId,
        vaultId,
        status,
        document,
        signature,
        rejectReasonMessage,
        createdAt,
        updatedAt);
    this.smartContractDeployment = smartContractDeployment;
  }

  public static SmartContractDeploymentValidationRequest create(
      long id, String tenantId, long vaultId, SmartContractDeployment smartContractDeployment) {
    var createdAt = Instant.now();
    return new SmartContractDeploymentValidationRequest(
        id,
        tenantId,
        vaultId,
        smartContractDeployment,
        ValidationRequestStatus.New,
        null,
        null,
        null,
        createdAt,
        createdAt);
  }

  public SmartContractDeployment getSmartContractDeployment() {
    return smartContractDeployment;
  }

  public void setSmartContractDeployment(SmartContractDeployment smartContractDeployment) {
    this.smartContractDeployment = smartContractDeployment;
  }
}
