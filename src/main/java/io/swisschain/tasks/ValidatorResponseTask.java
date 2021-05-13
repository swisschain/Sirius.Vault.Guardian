package io.swisschain.tasks;

import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;
import io.swisschain.policies.validator_responses.ValidatorResponseProcessor;
import io.swisschain.services.ValidatorResponseApiService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ValidatorResponseTask implements Runnable {
  private static final Logger logger = LogManager.getLogger();
  private final ValidatorResponseApiService validatorResponseApiService;
  private final ValidatorResponseProcessor validatorResponseProcessor;

  public ValidatorResponseTask(
      ValidatorResponseApiService validatorResponseApiService,
      ValidatorResponseProcessor validatorResponseProcessor) {
    this.validatorResponseApiService = validatorResponseApiService;
    this.validatorResponseProcessor = validatorResponseProcessor;
  }

  @Override
  public void run() {
    try {
      for (var validatorApproval : validatorResponseApiService.get()) {
        try {
          validatorResponseProcessor.process(validatorApproval);
        } catch (Exception exception) {
          logger.error(
              String.format(
                  "An error occurred while processing approval result. ValidationRequestId: %s; ValidatorId: %s",
                  validatorApproval.getValidationRequestId(), validatorApproval.getValidatorId()),
              exception);
        }
      }
    } catch (OperationExhaustedException exception) {
      logger.error("Operation exhausted while processing approval results.", exception);
    } catch (OperationFailedException exception) {
      logger.error("Operation failed while processing approval results.", exception);
    } catch (Exception exception) {
      logger.error("An unexpected error occurred while processing approval results.", exception);
    }
  }
}
