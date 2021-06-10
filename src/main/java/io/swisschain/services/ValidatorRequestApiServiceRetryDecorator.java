package io.swisschain.services;

import com.evanlennick.retry4j.exception.RetriesExhaustedException;
import com.evanlennick.retry4j.exception.UnexpectedException;
import io.swisschain.common.durability.RetryPolicies;
import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ValidatorRequestApiServiceRetryDecorator implements ValidatorRequestApiService {
  private final Logger logger = LogManager.getLogger();
  private final ValidatorRequestApiService validatorRequestApiService;

  public ValidatorRequestApiServiceRetryDecorator(
      ValidatorRequestApiService validatorRequestApiService) {
    this.validatorRequestApiService = validatorRequestApiService;
  }

  @Override
  public void confirm(String validatorId, String validationRequestId)
      throws OperationFailedException, OperationExhaustedException {
    try {
      RetryPolicies.ExecuteWithDefaultGrpcConfig(
          o ->
              logger.warn(
                  String.format(
                      "Failed to confirm validator approval %s of validation request %s: %s",
                      validatorId,
                      validationRequestId,
                      o.getLastExceptionThatCausedRetry().getMessage())),
          () -> {
            validatorRequestApiService.confirm(validatorId, validationRequestId);
            return null;
          });
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to confirm validator approval %s of validation request %s. Retries exhausted with total tries %d duration %d ms.",
              validatorId,
              validationRequestId,
              exception.getStatus().getTotalTries(),
              exception.getStatus().getTotalElapsedDuration().toMillis()));
      throw new OperationExhaustedException(exception);
    } catch (UnexpectedException exception) {
      logger.error(
          String.format(
              "An unexpected error occurred while confirming validator approval %s of validation request %s.",
              validatorId, validationRequestId),
          exception);
      throw new OperationFailedException(exception);
    }
  }

  @Override
  public void create(
      String tenantId,
      long vaultId,
      String validationRequestId,
      String validatorId,
      String message,
      String key,
      String nonce)
      throws OperationFailedException, OperationExhaustedException {
    try {
      RetryPolicies.ExecuteWithDefaultGrpcConfig(
          o ->
              logger.warn(
                  String.format(
                      "Failed to create approval request for validator %s of validation request %s: %s",
                      validatorId,
                      validationRequestId,
                      o.getLastExceptionThatCausedRetry().getMessage())),
          () -> {
            validatorRequestApiService.create(
                tenantId, vaultId, validationRequestId, validatorId, message, key, nonce);
            return null;
          });
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to create approval request for validator %s of validation request %s. Retries exhausted with total tries %d duration %d ms.",
              validatorId,
              validationRequestId,
              exception.getStatus().getTotalTries(),
              exception.getStatus().getTotalElapsedDuration().toMillis()));
      throw new OperationExhaustedException(exception);
    } catch (UnexpectedException exception) {
      logger.error(
          String.format(
              "An unexpected error occurred while creating approval request for validator %s of validation request %s.",
              validatorId, validationRequestId),
          exception);
      throw new OperationFailedException(exception);
    }
  }
}
