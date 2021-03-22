package io.swisschain.services;

import com.evanlennick.retry4j.exception.RetriesExhaustedException;
import com.evanlennick.retry4j.exception.UnexpectedException;
import io.swisschain.common.durability.RetryPolicies;
import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;
import io.swisschain.domain.validators.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ValidatorApiServiceRetryDecorator implements ValidatorApiService {
  private final Logger logger = LogManager.getLogger();
  private final ValidatorApiService validatorApiService;

  public ValidatorApiServiceRetryDecorator(ValidatorApiService validatorApiService) {
    this.validatorApiService = validatorApiService;
  }

  @Override
  public List<Validator> get(String tenantId)
      throws OperationFailedException, OperationExhaustedException {
    try {
      var status =
          RetryPolicies.ExecuteWithDefaultGrpcConfig(
              o ->
                  logger.warn(
                      String.format("Failed to get validators by tenant %s.", tenantId),
                      o.getLastExceptionThatCausedRetry()),
              () -> validatorApiService.get(tenantId));
      return status.getResult();
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to get validators by tenant %s. Retries exhausted with total tries %d duration %d ms.",
              tenantId,
              exception.getStatus().getTotalTries(),
              exception.getStatus().getTotalElapsedDuration().toMillis()));
      throw new OperationExhaustedException(exception);
    } catch (UnexpectedException exception) {
      logger.error(
          String.format(
              "An unexpected error occurred while getting validators by tenant %s.", tenantId),
          exception);
      throw new OperationFailedException(exception);
    }
  }

  @Override
  public Validator getById(String tenantId, String validatorId)
      throws OperationFailedException, OperationExhaustedException {
    try {
      var status =
          RetryPolicies.ExecuteWithDefaultGrpcConfig(
              o ->
                  logger.warn(
                      String.format(
                          "Failed to get validator by id %s by tenant %s.", validatorId, tenantId),
                      o.getLastExceptionThatCausedRetry()),
              () -> validatorApiService.getById(tenantId, validatorId));
      return status.getResult();
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to get validator by id %s by tenant %s. Retries exhausted with total tries %d duration %d ms.",
              validatorId,
              tenantId,
              exception.getStatus().getTotalTries(),
              exception.getStatus().getTotalElapsedDuration().toMillis()));
      throw new OperationExhaustedException(exception);
    } catch (UnexpectedException exception) {
      logger.error(
          String.format(
              "An unexpected error occurred while getting validator by id %s by tenant %s.",
              validatorId, tenantId),
          exception);
      throw new OperationFailedException(exception);
    }
  }
}
