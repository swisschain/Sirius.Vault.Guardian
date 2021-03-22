package io.swisschain.repositories.validatorRequests;

import com.evanlennick.retry4j.exception.RetriesExhaustedException;
import com.evanlennick.retry4j.exception.UnexpectedException;
import io.swisschain.common.durability.RetryPolicies;
import io.swisschain.domain.exceptions.AlreadyExistsException;
import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;
import io.swisschain.domain.validators.ValidatorRequest;
import io.swisschain.repositories.PostgresErrorCodes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class ValidatorRequestRepositoryRetryDecorator implements ValidatorRequestRepository {
  private final Logger logger = LogManager.getLogger();
  private final ValidatorRequestRepository validatorRequestRepository;

  public ValidatorRequestRepositoryRetryDecorator(
      ValidatorRequestRepository validatorRequestRepository) {
    this.validatorRequestRepository = validatorRequestRepository;
  }

  @Override
  public List<ValidatorRequest> getByTransferValidationRequestId(long transferValidationRequestId)
      throws OperationExhaustedException, OperationFailedException {
    try {
      var status =
          RetryPolicies.ExecuteWithDefaultRepositoryConfig(
              o ->
                  logger.warn(
                      String.format(
                          "Failed to get validator requests by transfer validation request %d.",
                          transferValidationRequestId),
                      o.getLastExceptionThatCausedRetry()),
              () ->
                  validatorRequestRepository.getByTransferValidationRequestId(
                      transferValidationRequestId));
      return status.getResult();
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to get validator requests by transfer validation request %d. Retries exhausted with total tries %d duration %d ms.",
              transferValidationRequestId,
              exception.getStatus().getTotalTries(),
              exception.getStatus().getTotalElapsedDuration().toMillis()));
      throw new OperationExhaustedException(exception);
    } catch (UnexpectedException exception) {
      logger.error(
          String.format(
              "An unexpected error occurred while getting validator requests by transfer validation request %d.",
              transferValidationRequestId),
          exception);
      throw new OperationFailedException(exception);
    }
  }

  @Override
  public ValidatorRequest get(String validatorId, long transferValidationRequestId)
      throws OperationExhaustedException, OperationFailedException {
    try {
      var status =
          RetryPolicies.ExecuteWithDefaultRepositoryConfig(
              o ->
                  logger.warn(
                      String.format(
                          "Failed to get validator request by validator id %s by transfer validation request %d.",
                          validatorId, transferValidationRequestId),
                      o.getLastExceptionThatCausedRetry()),
              () -> validatorRequestRepository.get(validatorId, transferValidationRequestId));
      return status.getResult();
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to get validator request by validator id %s by transfer validation request %d. Retries exhausted with total tries %d duration %d ms.",
              validatorId,
              transferValidationRequestId,
              exception.getStatus().getTotalTries(),
              exception.getStatus().getTotalElapsedDuration().toMillis()));
      throw new OperationExhaustedException(exception);
    } catch (UnexpectedException exception) {
      logger.error(
          String.format(
              "An unexpected error occurred while getting validator request by validator id %s by transfer validation request %d.",
              validatorId, transferValidationRequestId),
          exception);
      throw new OperationFailedException(exception);
    }
  }

  @Override
  public void insert(ValidatorRequest validatorRequest)
      throws AlreadyExistsException, OperationExhaustedException, OperationFailedException {
    try {
      RetryPolicies.ExecuteWithDefaultRepositoryConfig(
          o ->
              logger.warn(
                  String.format(
                      "Failed to insert transfer validator request with validator id %s and transfer validation request %d.",
                      validatorRequest.getValidatorId(),
                      validatorRequest.getTransferValidationRequestId()),
                  o.getLastExceptionThatCausedRetry()),
          () -> {
            validatorRequestRepository.insert(validatorRequest);
            return null;
          });
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to insert transfer validator request with validator id %s and transfer validation request %d. Retries exhausted with total tries %d duration %d ms.",
              validatorRequest.getValidatorId(),
              validatorRequest.getTransferValidationRequestId(),
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
              "An unexpected error occurred while inserting transfer validator request with validator id %s and transfer validation request %d.",
              validatorRequest.getValidatorId(), validatorRequest.getTransferValidationRequestId()),
          exception);
      throw new OperationFailedException(exception);
    }
  }
}
