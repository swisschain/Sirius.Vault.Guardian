package io.swisschain.repositories.validatorResponses;

import com.evanlennick.retry4j.exception.RetriesExhaustedException;
import com.evanlennick.retry4j.exception.UnexpectedException;
import io.swisschain.common.durability.RetryPolicies;
import io.swisschain.domain.exceptions.AlreadyExistsException;
import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;
import io.swisschain.domain.validators.ValidatorResponse;
import io.swisschain.repositories.PostgresErrorCodes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class ValidatorResponseRepositoryRetryDecorator implements ValidatorResponseRepository {
  private final Logger logger = LogManager.getLogger();
  private final ValidatorResponseRepository validatorResponseRepository;

  public ValidatorResponseRepositoryRetryDecorator(
      ValidatorResponseRepository validatorResponseRepository) {
    this.validatorResponseRepository = validatorResponseRepository;
  }

  @Override
  public List<ValidatorResponse> getByTransferValidationRequestId(long transferValidationRequestId)
      throws OperationExhaustedException, OperationFailedException {
    try {
      var status =
          RetryPolicies.ExecuteWithDefaultRepositoryConfig(
              o ->
                  logger.warn(
                      String.format(
                          "Failed to get validator responses by transfer validation request %d.",
                          transferValidationRequestId),
                      o.getLastExceptionThatCausedRetry()),
              () ->
                  validatorResponseRepository.getByTransferValidationRequestId(
                      transferValidationRequestId));
      return status.getResult();
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to get validator responses by transfer validation request %d. Retries exhausted with total tries %d duration %d ms.",
              transferValidationRequestId,
              exception.getStatus().getTotalTries(),
              exception.getStatus().getTotalElapsedDuration().toMillis()));
      throw new OperationExhaustedException(exception);
    } catch (UnexpectedException exception) {
      logger.error(
          String.format(
              "An unexpected error occurred while getting validator responses by transfer validation request %d.",
              transferValidationRequestId),
          exception);
      throw new OperationFailedException(exception);
    }
  }

  @Override
  public void insert(ValidatorResponse validatorResponse)
      throws AlreadyExistsException, OperationExhaustedException, OperationFailedException {
    try {
      RetryPolicies.ExecuteWithDefaultRepositoryConfig(
          o ->
              logger.warn(
                  String.format(
                      "Failed to insert transfer validator response with validator id %s and transfer validation request %d.",
                      validatorResponse.getValidatorId(),
                      validatorResponse.getTransferValidationRequestId()),
                  o.getLastExceptionThatCausedRetry()),
          () -> {
            validatorResponseRepository.insert(validatorResponse);
            return null;
          });
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to insert transfer validator response with validator id %s and transfer validation request %d. Retries exhausted with total tries %d duration %d ms.",
              validatorResponse.getValidatorId(),
              validatorResponse.getTransferValidationRequestId(),
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
              "An unexpected error occurred while inserting transfer validator response with validator id %s and transfer validation request %d.",
              validatorResponse.getValidatorId(),
              validatorResponse.getTransferValidationRequestId()),
          exception);
      throw new OperationFailedException(exception);
    }
  }
}
