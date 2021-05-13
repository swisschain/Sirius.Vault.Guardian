package io.swisschain.repositories.smart_contract_invocation_validation_requests;

import com.evanlennick.retry4j.exception.RetriesExhaustedException;
import com.evanlennick.retry4j.exception.UnexpectedException;
import io.swisschain.common.durability.RetryPolicies;
import io.swisschain.domain.exceptions.AlreadyExistsException;
import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;
import io.swisschain.domain.validation_requests.smart_contracts.SmartContractInvocationValidationRequest;
import io.swisschain.repositories.PostgresErrorCodes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class SmartContractInvocationValidationRequestRepositoryRetryDecorator
    implements SmartContractInvocationValidationRequestRepository {
  private final Logger logger = LogManager.getLogger();
  private final SmartContractInvocationValidationRequestRepository
      smartContractInvocationValidationRequestRepository;

  public SmartContractInvocationValidationRequestRepositoryRetryDecorator(
      SmartContractInvocationValidationRequestRepository
          smartContractInvocationValidationRequestRepository) {
    this.smartContractInvocationValidationRequestRepository =
        smartContractInvocationValidationRequestRepository;
  }

  @Override
  public SmartContractInvocationValidationRequest getById(
      long smartContractInvocationValidationRequestId)
      throws OperationExhaustedException, OperationFailedException {
    try {
      var status =
          RetryPolicies.ExecuteWithDefaultRepositoryConfig(
              o ->
                  logger.warn(
                      String.format(
                          "Failed to get smart contract invocation validation request by id %d.",
                          smartContractInvocationValidationRequestId),
                      o.getLastExceptionThatCausedRetry()),
              () ->
                  smartContractInvocationValidationRequestRepository.getById(
                      smartContractInvocationValidationRequestId));
      return status.getResult();
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to get smart contract invocation validation request by id %d. Retries exhausted with total tries %d duration %d ms.",
              smartContractInvocationValidationRequestId,
              exception.getStatus().getTotalTries(),
              exception.getStatus().getTotalElapsedDuration().toMillis()));
      throw new OperationExhaustedException(exception);
    } catch (UnexpectedException exception) {
      logger.error(
          String.format(
              "An unexpected error occurred while getting smart contract invocation validation request by id %d.",
              smartContractInvocationValidationRequestId),
          exception);
      throw new OperationFailedException(exception);
    }
  }

  @Override
  public void insert(
      SmartContractInvocationValidationRequest smartContractInvocationValidationRequest)
      throws AlreadyExistsException, OperationExhaustedException, OperationFailedException {
    try {
      RetryPolicies.ExecuteWithDefaultRepositoryConfig(
          o ->
              logger.warn(
                  String.format(
                      "Failed to insert smart contract invocation validation request %d.",
                      smartContractInvocationValidationRequest.getId()),
                  o.getLastExceptionThatCausedRetry()),
          () -> {
            smartContractInvocationValidationRequestRepository.insert(
                smartContractInvocationValidationRequest);
            return null;
          });
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to insert smart contract invocation validation request %d. Retries exhausted with total tries %d duration %d ms.",
              smartContractInvocationValidationRequest.getId(),
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
              "An unexpected error occurred while inserting smart contract invocation validation request %d.",
              smartContractInvocationValidationRequest.getId()),
          exception);
      throw new OperationFailedException(exception);
    }
  }

  @Override
  public void update(
      SmartContractInvocationValidationRequest smartContractInvocationValidationRequest)
      throws OperationExhaustedException, OperationFailedException {
    try {
      RetryPolicies.ExecuteWithDefaultRepositoryConfig(
          o ->
              logger.warn(
                  String.format(
                      "Failed to update smart contract invocation validation request %d.",
                      smartContractInvocationValidationRequest.getId()),
                  o.getLastExceptionThatCausedRetry()),
          () -> {
            smartContractInvocationValidationRequestRepository.update(
                smartContractInvocationValidationRequest);
            return null;
          });
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to update smart contract invocation validation request %d. Retries exhausted with total tries %d duration %d ms.",
              smartContractInvocationValidationRequest.getId(),
              exception.getStatus().getTotalTries(),
              exception.getStatus().getTotalElapsedDuration().toMillis()));
      throw new OperationExhaustedException(exception);
    } catch (UnexpectedException exception) {
      logger.error(
          String.format(
              "An unexpected error occurred while updating smart contract invocation validation request %d.",
              smartContractInvocationValidationRequest.getId()),
          exception);
      throw new OperationFailedException(exception);
    }
  }
}
