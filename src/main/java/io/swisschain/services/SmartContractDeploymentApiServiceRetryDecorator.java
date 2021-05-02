package io.swisschain.services;

import com.evanlennick.retry4j.exception.RetriesExhaustedException;
import com.evanlennick.retry4j.exception.UnexpectedException;
import io.swisschain.common.durability.RetryPolicies;
import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;
import io.swisschain.domain.validation_requests.smart_contract_deployments.SmartContractDeploymentValidationRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SmartContractDeploymentApiServiceRetryDecorator
    implements SmartContractDeploymentApiService {
  private final Logger logger = LogManager.getLogger();
  private final SmartContractDeploymentApiService smartContractDeploymentApiService;

  public SmartContractDeploymentApiServiceRetryDecorator(
      SmartContractDeploymentApiService smartContractDeploymentApiService) {
    this.smartContractDeploymentApiService = smartContractDeploymentApiService;
  }

  @Override
  public List<SmartContractDeploymentValidationRequest> get()
      throws OperationFailedException, OperationExhaustedException {
    try {
      var status =
          RetryPolicies.ExecuteWithDefaultGrpcConfig(
              o ->
                  logger.warn(
                      "Failed to get smart contract deployment validation requests.",
                      o.getLastExceptionThatCausedRetry()),
              smartContractDeploymentApiService::get);
      return status.getResult();
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to get smart contract deployment validation requests. Retries exhausted with total tries %d duration %d ms.",
              exception.getStatus().getTotalTries(),
              exception.getStatus().getTotalElapsedDuration().toMillis()));
      throw new OperationExhaustedException(exception);
    } catch (UnexpectedException exception) {
      logger.error(
          "An unexpected error occurred while getting smart contract deployment validation requests.",
          exception);
      throw new OperationFailedException(exception);
    }
  }

  @Override
  public void confirm(SmartContractDeploymentValidationRequest validationRequest)
      throws OperationFailedException, OperationExhaustedException {
    try {
      RetryPolicies.ExecuteWithDefaultGrpcConfig(
          o ->
              logger.warn(
                  String.format(
                      "Failed to confirm smart contract deployment validation request %d.",
                      validationRequest.getId()),
                  o.getLastExceptionThatCausedRetry()),
          () -> {
            smartContractDeploymentApiService.confirm(validationRequest);
            return null;
          });
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to confirm smart contract deployment validation request %d. Retries exhausted with total tries %d duration %d ms.",
              validationRequest.getId(),
              exception.getStatus().getTotalTries(),
              exception.getStatus().getTotalElapsedDuration().toMillis()));
      throw new OperationExhaustedException(exception);
    } catch (UnexpectedException exception) {
      logger.error(
          String.format(
              "An unexpected error occurred while confirming smart contract deployment validation request %d.",
              validationRequest.getId()),
          exception);
      throw new OperationFailedException(exception);
    }
  }

  @Override
  public void reject(SmartContractDeploymentValidationRequest validationRequest)
      throws OperationFailedException, OperationExhaustedException {
    try {
      RetryPolicies.ExecuteWithDefaultGrpcConfig(
          o ->
              logger.warn(
                  String.format(
                      "Failed to reject smart contract deployment validation request %d.",
                      validationRequest.getId()),
                  o.getLastExceptionThatCausedRetry()),
          () -> {
            smartContractDeploymentApiService.reject(validationRequest);
            return null;
          });
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to reject smart contract deployment validation request %d. Retries exhausted with total tries %d duration %d ms.",
              validationRequest.getId(),
              exception.getStatus().getTotalTries(),
              exception.getStatus().getTotalElapsedDuration().toMillis()));
      throw new OperationExhaustedException(exception);
    } catch (UnexpectedException exception) {
      logger.error(
          String.format(
              "An unexpected error occurred while rejecting smart contract deployment validation request %d.",
              validationRequest.getId()),
          exception);
      throw new OperationFailedException(exception);
    }
  }
}
