package io.swisschain.repositories.validator_requests;

import com.evanlennick.retry4j.exception.RetriesExhaustedException;
import com.evanlennick.retry4j.exception.UnexpectedException;
import io.swisschain.common.durability.RetryPolicies;
import io.swisschain.domain.exceptions.AlreadyExistsException;
import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;
import io.swisschain.domain.validators.ValidatorRequest;
import io.swisschain.domain.validators.ValidatorRequestType;
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
  public ValidatorRequest getById(String validatorRequestId)
      throws OperationExhaustedException, OperationFailedException {
    try {
      var status =
          RetryPolicies.ExecuteWithDefaultRepositoryConfig(
              o ->
                  logger.warn(
                      String.format(
                          "Failed to get validator request by id %s.", validatorRequestId),
                      o.getLastExceptionThatCausedRetry()),
              () -> validatorRequestRepository.getById(validatorRequestId));
      return status.getResult();
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to get validator request by id %s. Retries exhausted with total tries %d duration %d ms.",
              validatorRequestId,
              exception.getStatus().getTotalTries(),
              exception.getStatus().getTotalElapsedDuration().toMillis()));
      throw new OperationExhaustedException(exception);
    } catch (UnexpectedException exception) {
      logger.error(
          String.format(
              "An unexpected error occurred while getting validator request by id %s.",
              validatorRequestId),
          exception);
      throw new OperationFailedException(exception);
    }
  }

  @Override
  public List<ValidatorRequest> getByValidationRequestId(
      long validationRequestId, ValidatorRequestType requestType)
      throws OperationExhaustedException, OperationFailedException {
    try {
      var status =
          RetryPolicies.ExecuteWithDefaultRepositoryConfig(
              o ->
                  logger.warn(
                      String.format(
                          "Failed to get validator requests by validation request %d type %s.",
                          validationRequestId, requestType.name()),
                      o.getLastExceptionThatCausedRetry()),
              () ->
                  validatorRequestRepository.getByValidationRequestId(
                      validationRequestId, requestType));
      return status.getResult();
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to get validator requests by validation request %d type %s. Retries exhausted with total tries %d duration %d ms.",
              validationRequestId,
              requestType.name(),
              exception.getStatus().getTotalTries(),
              exception.getStatus().getTotalElapsedDuration().toMillis()));
      throw new OperationExhaustedException(exception);
    } catch (UnexpectedException exception) {
      logger.error(
          String.format(
              "An unexpected error occurred while getting validator requests by validation request %d type %s.",
              validationRequestId, requestType.name()),
          exception);
      throw new OperationFailedException(exception);
    }
  }

  @Override
  public ValidatorRequest getByValidatorId(String validatorId, long validationRequestId)
      throws OperationExhaustedException, OperationFailedException {
    try {
      var status =
          RetryPolicies.ExecuteWithDefaultRepositoryConfig(
              o ->
                  logger.warn(
                      String.format(
                          "Failed to get validator request by validation request %d by validator id %s.",
                          validationRequestId, validatorId),
                      o.getLastExceptionThatCausedRetry()),
              () -> validatorRequestRepository.getByValidatorId(validatorId, validationRequestId));
      return status.getResult();
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to get validator request by validation request %d by validator id %s. Retries exhausted with total tries %d duration %d ms.",
              validationRequestId,
              validatorId,
              exception.getStatus().getTotalTries(),
              exception.getStatus().getTotalElapsedDuration().toMillis()));
      throw new OperationExhaustedException(exception);
    } catch (UnexpectedException exception) {
      logger.error(
          String.format(
              "An unexpected error occurred while getting validator request by validation request %d by validator id %s.",
              validationRequestId, validatorId),
          exception);
      throw new OperationFailedException(exception);
    }
  }

  @Override
  public boolean insert(ValidatorRequest validatorRequest)
      throws AlreadyExistsException, OperationExhaustedException, OperationFailedException {
    try {
      var status =
          RetryPolicies.ExecuteWithDefaultRepositoryConfig(
              o ->
                  logger.warn(
                      String.format(
                          "Failed to insert validator request with validator id %s and transfer validation request %d.",
                          validatorRequest.getValidatorId(),
                          validatorRequest.getValidationRequestId()),
                      o.getLastExceptionThatCausedRetry()),
              () -> validatorRequestRepository.insert(validatorRequest));
      return status.getResult();
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to insert validator request with validator id %s and transfer validation request %d. Retries exhausted with total tries %d duration %d ms.",
              validatorRequest.getValidatorId(),
              validatorRequest.getValidationRequestId(),
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
              "An unexpected error occurred while inserting validator request with validator id %s and transfer validation request %d.",
              validatorRequest.getValidatorId(), validatorRequest.getValidationRequestId()),
          exception);
      throw new OperationFailedException(exception);
    }
  }

  @Override
  public void update(ValidatorRequest validatorRequest)
      throws AlreadyExistsException, OperationExhaustedException, OperationFailedException {
    try {
      RetryPolicies.ExecuteWithDefaultRepositoryConfig(
          o ->
              logger.warn(
                  String.format(
                      "Failed to update validator request with validator id %s and validation request %d.",
                      validatorRequest.getValidatorId(), validatorRequest.getValidationRequestId()),
                  o.getLastExceptionThatCausedRetry()),
          () -> {
            validatorRequestRepository.update(validatorRequest);
            return null;
          });
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to update validator request with validator id %s and validation request %d. Retries exhausted with total tries %d duration %d ms.",
              validatorRequest.getValidatorId(),
              validatorRequest.getValidationRequestId(),
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
              "An unexpected error occurred while updating validator request with validator id %s and validation request %d.",
              validatorRequest.getValidatorId(), validatorRequest.getValidationRequestId()),
          exception);
      throw new OperationFailedException(exception);
    }
  }
}
