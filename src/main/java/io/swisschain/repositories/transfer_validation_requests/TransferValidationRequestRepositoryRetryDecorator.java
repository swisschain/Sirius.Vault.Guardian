package io.swisschain.repositories.transfer_validation_requests;

import com.evanlennick.retry4j.exception.RetriesExhaustedException;
import com.evanlennick.retry4j.exception.UnexpectedException;
import io.swisschain.common.durability.RetryPolicies;
import io.swisschain.domain.exceptions.AlreadyExistsException;
import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;
import io.swisschain.domain.validation_requests.transfers.TransferValidationRequest;
import io.swisschain.repositories.PostgresErrorCodes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class TransferValidationRequestRepositoryRetryDecorator
    implements TransferValidationRequestRepository {
  private final Logger logger = LogManager.getLogger();
  private final TransferValidationRequestRepository transferValidationRequestRepository;

  public TransferValidationRequestRepositoryRetryDecorator(
      TransferValidationRequestRepository transferValidationRequestRepository) {
    this.transferValidationRequestRepository = transferValidationRequestRepository;
  }

  @Override
  public TransferValidationRequest getById(long transferValidationRequestId)
      throws OperationExhaustedException, OperationFailedException {
    try {
      var status =
          RetryPolicies.ExecuteWithDefaultRepositoryConfig(
              o ->
                  logger.warn(
                      String.format(
                          "Failed to get transfer validation request by id %d.",
                          transferValidationRequestId),
                      o.getLastExceptionThatCausedRetry()),
              () -> transferValidationRequestRepository.getById(transferValidationRequestId));
      return status.getResult();
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to get transfer validation request by id %d. Retries exhausted with total tries %d duration %d ms.",
              transferValidationRequestId,
              exception.getStatus().getTotalTries(),
              exception.getStatus().getTotalElapsedDuration().toMillis()));
      throw new OperationExhaustedException(exception);
    } catch (UnexpectedException exception) {
      logger.error(
          String.format(
              "An unexpected error occurred while getting transfer validation request by id %d.",
              transferValidationRequestId),
          exception);
      throw new OperationFailedException(exception);
    }
  }

  @Override
  public void insert(TransferValidationRequest transferValidationRequest)
      throws AlreadyExistsException, OperationExhaustedException, OperationFailedException {
    try {
      RetryPolicies.ExecuteWithDefaultRepositoryConfig(
          o ->
              logger.warn(
                  String.format(
                      "Failed to insert transfer validation request %d.",
                      transferValidationRequest.getId()),
                  o.getLastExceptionThatCausedRetry()),
          () -> {
            transferValidationRequestRepository.insert(transferValidationRequest);
            return null;
          });
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to insert transfer validation request %d. Retries exhausted with total tries %d duration %d ms.",
              transferValidationRequest.getId(),
              exception.getStatus().getTotalTries(),
              exception.getStatus().getTotalElapsedDuration().toMillis()));
      throw new OperationExhaustedException(exception);
    } catch (UnexpectedException exception) {
      if (exception.getCause() != null
          && SQLException.class.isAssignableFrom(exception.getCause().getClass())) {
        if (((SQLException) exception.getCause())
            .getSQLState()
            .equals(PostgresErrorCodes.UniqueViolationErrorCode)) {
          throw new AlreadyExistsException(exception);
        }
      }
      logger.error(
          String.format(
              "An unexpected error occurred while inserting transfer validation request %d.",
              transferValidationRequest.getId()),
          exception);
      throw new OperationFailedException(exception);
    }
  }

  @Override
  public void update(TransferValidationRequest transferValidationRequest)
      throws OperationExhaustedException, OperationFailedException {
    try {
      RetryPolicies.ExecuteWithDefaultRepositoryConfig(
          o ->
              logger.warn(
                  String.format(
                      "Failed to update transfer validation request %d.",
                      transferValidationRequest.getId()),
                  o.getLastExceptionThatCausedRetry()),
          () -> {
            transferValidationRequestRepository.update(transferValidationRequest);
            return null;
          });
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to update transfer validation request %d. Retries exhausted with total tries %d duration %d ms.",
              transferValidationRequest.getId(),
              exception.getStatus().getTotalTries(),
              exception.getStatus().getTotalElapsedDuration().toMillis()));
      throw new OperationExhaustedException(exception);
    } catch (UnexpectedException exception) {
      logger.error(
          String.format(
              "An unexpected error occurred while updating transfer validation request %d.",
              transferValidationRequest.getId()),
          exception);
      throw new OperationFailedException(exception);
    }
  }
}
