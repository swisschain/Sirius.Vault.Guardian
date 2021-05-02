package io.swisschain.services;

import com.evanlennick.retry4j.exception.RetriesExhaustedException;
import com.evanlennick.retry4j.exception.UnexpectedException;
import io.swisschain.common.durability.RetryPolicies;
import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;
import io.swisschain.domain.validators.ValidatorResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ValidatorResponseApiServiceRetryDecorator implements ValidatorResponseApiService {
  private final Logger logger = LogManager.getLogger();
  private final ValidatorResponseApiService validatorResponseApiService;

  public ValidatorResponseApiServiceRetryDecorator(
      ValidatorResponseApiService validatorResponseApiService) {
    this.validatorResponseApiService = validatorResponseApiService;
  }

  @Override
  public List<ValidatorResponse> get()
      throws OperationFailedException, OperationExhaustedException {
    try {
      var status =
          RetryPolicies.ExecuteWithDefaultGrpcConfig(
              o ->
                  logger.warn(
                      "Failed to get validators approvals.", o.getLastExceptionThatCausedRetry()),
              validatorResponseApiService::get);
      return status.getResult();
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to get validators approvals. Retries exhausted with total tries %d duration %d ms.",
              exception.getStatus().getTotalTries(),
              exception.getStatus().getTotalElapsedDuration().toMillis()));
      throw new OperationExhaustedException(exception);
    } catch (UnexpectedException exception) {
      logger.error("An unexpected error occurred while getting validators approvals.", exception);
      throw new OperationFailedException(exception);
    }
  }
}
