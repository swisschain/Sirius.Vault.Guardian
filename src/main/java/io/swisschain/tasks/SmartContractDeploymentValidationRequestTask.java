package io.swisschain.tasks;

import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;
import io.swisschain.policies.smart_contract_deployments.SmartContractDeploymentValidationRequestProcessor;
import io.swisschain.services.SmartContractDeploymentApiService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SmartContractDeploymentValidationRequestTask implements Runnable {
  private static final Logger logger = LogManager.getLogger();

  private final SmartContractDeploymentApiService apiService;
  private final SmartContractDeploymentValidationRequestProcessor validationRequestProcessor;

  public SmartContractDeploymentValidationRequestTask(
      SmartContractDeploymentApiService apiService,
      SmartContractDeploymentValidationRequestProcessor validationRequestProcessor) {
    this.apiService = apiService;
    this.validationRequestProcessor = validationRequestProcessor;
  }

  @Override
  public void run() {
    try {
      for (var validationRequest : apiService.get()) {
        try {
          validationRequestProcessor.process(validationRequest);
        } catch (Exception exception) {
          logger.error(
              String.format(
                  "An error occurred while processing smart contract deployment validation request. ValidationRequestId: %d; TenantId: %s",
                  validationRequest.getId(), validationRequest.getTenantId()),
              exception);
        }
      }
    } catch (OperationExhaustedException exception) {
      logger.error(
          "Operation exhausted while processing smart contract deployment validation requests.",
          exception);
    } catch (OperationFailedException exception) {
      logger.error(
          "Operation failed while processing smart contract deployment validation requests.",
          exception);
    } catch (Exception exception) {
      logger.error(
          "An unexpected error occurred while processing smart contract deployment validation requests.",
          exception);
    }
  }
}
