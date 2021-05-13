package io.swisschain.services;

import com.evanlennick.retry4j.exception.RetriesExhaustedException;
import com.evanlennick.retry4j.exception.UnexpectedException;
import io.swisschain.common.durability.RetryPolicies;
import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;
import io.swisschain.domain.validation_requests.transfers.TransferValidationRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TransferApiServiceRetryDecorator implements TransferApiService {
  private final Logger logger = LogManager.getLogger();
  private final TransferApiService transferApiService;

  public TransferApiServiceRetryDecorator(TransferApiService transferApiService) {
    this.transferApiService = transferApiService;
  }

  @Override
  public List<TransferValidationRequest> get()
      throws OperationFailedException, OperationExhaustedException {
    try {
      var status =
          RetryPolicies.ExecuteWithDefaultGrpcConfig(
              o ->
                  logger.warn(
                      "Failed to get transfer validation requests.",
                      o.getLastExceptionThatCausedRetry()),
              transferApiService::get);
      return status.getResult();
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to get transfer validation requests. Retries exhausted with total tries %d duration %d ms.",
              exception.getStatus().getTotalTries(),
              exception.getStatus().getTotalElapsedDuration().toMillis()));
      throw new OperationExhaustedException(exception);
    } catch (UnexpectedException exception) {
      logger.error(
          "An unexpected error occurred while getting transfer validation requests.", exception);
      throw new OperationFailedException(exception);
    }
  }

  @Override
  public void confirm(TransferValidationRequest transferValidationRequest)
      throws OperationFailedException, OperationExhaustedException {
    try {
      RetryPolicies.ExecuteWithDefaultGrpcConfig(
          o ->
              logger.warn(
                  String.format(
                      "Failed to confirm transfer validation request %d.",
                      transferValidationRequest.getId()),
                  o.getLastExceptionThatCausedRetry()),
          () -> {
            transferApiService.confirm(transferValidationRequest);
            return null;
          });
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to confirm transfer validation request %d. Retries exhausted with total tries %d duration %d ms.",
              transferValidationRequest.getId(),
              exception.getStatus().getTotalTries(),
              exception.getStatus().getTotalElapsedDuration().toMillis()));
      throw new OperationExhaustedException(exception);
    } catch (UnexpectedException exception) {
      logger.error(
          String.format(
              "An unexpected error occurred while confirming transfer validation request %d.",
              transferValidationRequest.getId()),
          exception);
      throw new OperationFailedException(exception);
    }
  }

  @Override
  public void reject(TransferValidationRequest transferValidationRequest)
      throws OperationFailedException, OperationExhaustedException {
    try {
      RetryPolicies.ExecuteWithDefaultGrpcConfig(
          o ->
              logger.warn(
                  String.format(
                      "Failed to reject transfer validation request %d.",
                      transferValidationRequest.getId()),
                  o.getLastExceptionThatCausedRetry()),
          () -> {
            transferApiService.reject(transferValidationRequest);
            return null;
          });
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to reject transfer validation request %d. Retries exhausted with total tries %d duration %d ms.",
              transferValidationRequest.getId(),
              exception.getStatus().getTotalTries(),
              exception.getStatus().getTotalElapsedDuration().toMillis()));
      throw new OperationExhaustedException(exception);
    } catch (UnexpectedException exception) {
      logger.error(
          String.format(
              "An unexpected error occurred while rejecting transfer validation request %d.",
              transferValidationRequest.getId()),
          exception);
      throw new OperationFailedException(exception);
    }
  }
}
