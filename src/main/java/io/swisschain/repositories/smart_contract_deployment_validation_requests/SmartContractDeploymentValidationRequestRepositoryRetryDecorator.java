package io.swisschain.repositories.smart_contract_deployment_validation_requests;

import com.evanlennick.retry4j.exception.RetriesExhaustedException;
import com.evanlennick.retry4j.exception.UnexpectedException;
import io.swisschain.common.durability.RetryPolicies;
import io.swisschain.domain.exceptions.AlreadyExistsException;
import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;
import io.swisschain.domain.validation_requests.smart_contract_deployments.SmartContractDeploymentValidationRequest;
import io.swisschain.repositories.PostgresErrorCodes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class SmartContractDeploymentValidationRequestRepositoryRetryDecorator
    implements SmartContractDeploymentValidationRequestRepository {
  private final Logger logger = LogManager.getLogger();
  private final SmartContractDeploymentValidationRequestRepository
      smartContractDeploymentValidationRequestRepository;

  public SmartContractDeploymentValidationRequestRepositoryRetryDecorator(
      SmartContractDeploymentValidationRequestRepository
          smartContractDeploymentValidationRequestRepository) {
    this.smartContractDeploymentValidationRequestRepository =
        smartContractDeploymentValidationRequestRepository;
  }

  @Override
  public SmartContractDeploymentValidationRequest getById(
      long smartContractDeploymentValidationRequestId)
      throws OperationExhaustedException, OperationFailedException {
    try {
      var status =
          RetryPolicies.ExecuteWithDefaultRepositoryConfig(
              o ->
                  logger.warn(
                      String.format(
                          "Failed to get smart contract deployment validation request by id %d.",
                          smartContractDeploymentValidationRequestId),
                      o.getLastExceptionThatCausedRetry()),
              () ->
                  smartContractDeploymentValidationRequestRepository.getById(
                      smartContractDeploymentValidationRequestId));
      return status.getResult();
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to get smart contract deployment validation request by id %d. Retries exhausted with total tries %d duration %d ms.",
              smartContractDeploymentValidationRequestId,
              exception.getStatus().getTotalTries(),
              exception.getStatus().getTotalElapsedDuration().toMillis()));
      throw new OperationExhaustedException(exception);
    } catch (UnexpectedException exception) {
      logger.error(
          String.format(
              "An unexpected error occurred while getting smart contract deployment validation request by id %d.",
              smartContractDeploymentValidationRequestId),
          exception);
      throw new OperationFailedException(exception);
    }
  }

  @Override
  public void insert(
      SmartContractDeploymentValidationRequest smartContractDeploymentValidationRequest)
      throws AlreadyExistsException, OperationExhaustedException, OperationFailedException {
    try {
      RetryPolicies.ExecuteWithDefaultRepositoryConfig(
          o ->
              logger.warn(
                  String.format(
                      "Failed to insert smart contract deployment validation request %d.",
                      smartContractDeploymentValidationRequest.getId()),
                  o.getLastExceptionThatCausedRetry()),
          () -> {
            smartContractDeploymentValidationRequestRepository.insert(
                smartContractDeploymentValidationRequest);
            return null;
          });
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to insert smart contract deployment validation request %d. Retries exhausted with total tries %d duration %d ms.",
              smartContractDeploymentValidationRequest.getId(),
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
              "An unexpected error occurred while inserting smart contract deployment validation request %d.",
              smartContractDeploymentValidationRequest.getId()),
          exception);
      throw new OperationFailedException(exception);
    }
  }

  @Override
  public void update(
      SmartContractDeploymentValidationRequest smartContractDeploymentValidationRequest)
      throws OperationExhaustedException, OperationFailedException {
    try {
      RetryPolicies.ExecuteWithDefaultRepositoryConfig(
          o ->
              logger.warn(
                  String.format(
                      "Failed to update smart contract deployment validation request %d.",
                      smartContractDeploymentValidationRequest.getId()),
                  o.getLastExceptionThatCausedRetry()),
          () -> {
            smartContractDeploymentValidationRequestRepository.update(
                smartContractDeploymentValidationRequest);
            return null;
          });
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to update smart contract deployment validation request %d. Retries exhausted with total tries %d duration %d ms.",
              smartContractDeploymentValidationRequest.getId(),
              exception.getStatus().getTotalTries(),
              exception.getStatus().getTotalElapsedDuration().toMillis()));
      throw new OperationExhaustedException(exception);
    } catch (UnexpectedException exception) {
      logger.error(
          String.format(
              "An unexpected error occurred while updating smart contract deployment validation request %d.",
              smartContractDeploymentValidationRequest.getId()),
          exception);
      throw new OperationFailedException(exception);
    }
  }
}
