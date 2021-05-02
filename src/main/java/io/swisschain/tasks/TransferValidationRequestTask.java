package io.swisschain.tasks;

import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;
import io.swisschain.policies.transfers.TransferValidationRequestProcessor;
import io.swisschain.services.TransferApiService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TransferValidationRequestTask implements Runnable {
  private static final Logger logger = LogManager.getLogger();

  private final TransferApiService apiService;
  private final TransferValidationRequestProcessor validationRequestProcessor;

  public TransferValidationRequestTask(
      TransferApiService apiService,
      TransferValidationRequestProcessor validationRequestProcessor) {
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
                  "An error occurred while processing transfer validation request. ValidationRequestId: %d; TenantId: %s",
                  validationRequest.getId(), validationRequest.getTenantId()),
              exception);
        }
      }
    } catch (OperationExhaustedException exception) {
      logger.error("Operation exhausted while processing transfer validation requests.", exception);
    } catch (OperationFailedException exception) {
      logger.error("Operation failed while processing transfer validation requests.", exception);
    } catch (Exception exception) {
      logger.error(
          "An unexpected error occurred while processing transfer validation requests.", exception);
    }
  }
}
