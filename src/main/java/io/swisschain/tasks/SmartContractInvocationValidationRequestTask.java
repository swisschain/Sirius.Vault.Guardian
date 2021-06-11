package io.swisschain.tasks;

import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;
import io.swisschain.policies.smart_contract_invocations.SmartContractInvocationValidationRequestProcessor;
import io.swisschain.services.SmartContractInvocationApiService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SmartContractInvocationValidationRequestTask implements Runnable {
  private static final Logger logger = LogManager.getLogger();

  private final SmartContractInvocationApiService apiService;
  private final SmartContractInvocationValidationRequestProcessor validationRequestProcessor;

  public SmartContractInvocationValidationRequestTask(
      SmartContractInvocationApiService apiService,
      SmartContractInvocationValidationRequestProcessor validationRequestProcessor) {
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
                  "An error occurred while processing smart contract invocation validation request. ValidationRequestId: %d; TenantId: %s",
                  validationRequest.getId(), validationRequest.getTenantId()),
              exception);
        }
      }
    } catch (OperationExhaustedException exception) {
      // ignore
    } catch (OperationFailedException exception) {
      logger.error(
          "Operation failed while processing smart contract invocation validation requests.",
          exception);
    } catch (Exception exception) {
      logger.error(
          "An unexpected error occurred while processing smart contract invocation validation requests.",
          exception);
    }
  }
}
