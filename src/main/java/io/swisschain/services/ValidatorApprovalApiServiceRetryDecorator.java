package io.swisschain.services;

import com.evanlennick.retry4j.exception.RetriesExhaustedException;
import com.evanlennick.retry4j.exception.UnexpectedException;
import io.swisschain.common.durability.RetryPolicies;
import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;
import io.swisschain.domain.validators.ValidatorApproval;
import io.swisschain.domain.validators.ValidatorRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ValidatorApprovalApiServiceRetryDecorator implements ValidatorApprovalApiService {
  private final Logger logger = LogManager.getLogger();
  private final ValidatorApprovalApiService validatorApprovalApiService;

  public ValidatorApprovalApiServiceRetryDecorator(
      ValidatorApprovalApiService validatorApprovalApiService) {
    this.validatorApprovalApiService = validatorApprovalApiService;
  }

  @Override
  public List<ValidatorApproval> get()
      throws OperationFailedException, OperationExhaustedException {
    try {
      var status =
          RetryPolicies.ExecuteWithDefaultGrpcConfig(
              o ->
                  logger.warn(
                      "Failed to get validators approvals.", o.getLastExceptionThatCausedRetry()),
              validatorApprovalApiService::get);
      return status.getResult();
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to get validators approvals. Retries exhausted with total tries %d duration %d ms.",
              exception.getStatus().getTotalTries(),
              exception.getStatus().getTotalElapsedDuration().toMillis()));
      throw new OperationExhaustedException(exception);
    } catch (UnexpectedException exception) {
      logger.error("An unexpected error occurred while getting validators approvals.", exception);
      throw new OperationFailedException(exception);
    }
  }

  @Override
  public void confirm(String validatorId, long transferValidationRequestId)
      throws OperationFailedException, OperationExhaustedException {
    try {
      RetryPolicies.ExecuteWithDefaultGrpcConfig(
          o ->
              logger.warn(
                  String.format(
                      "Failed to confirm validator approval %s of validation request %d.",
                      validatorId, transferValidationRequestId),
                  o.getLastExceptionThatCausedRetry()),
          () -> {
            validatorApprovalApiService.confirm(validatorId, transferValidationRequestId);
            return null;
          });
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to confirm validator approval %s of validation request %d. Retries exhausted with total tries %d duration %d ms.",
              validatorId,
              transferValidationRequestId,
              exception.getStatus().getTotalTries(),
              exception.getStatus().getTotalElapsedDuration().toMillis()));
      throw new OperationExhaustedException(exception);
    } catch (UnexpectedException exception) {
      logger.error(
          String.format(
              "An unexpected error occurred while confirming validator approval %s of validation request %d.",
              validatorId, transferValidationRequestId),
          exception);
      throw new OperationFailedException(exception);
    }
  }

  @Override
  public void create(
      String tenantId, long transferValidationRequestId, ValidatorRequest validatorRequest)
      throws OperationFailedException, OperationExhaustedException {
    try {
      RetryPolicies.ExecuteWithDefaultGrpcConfig(
          o ->
              logger.warn(
                  String.format(
                      "Failed to create approval request for validator %s of validation request %d.",
                      validatorRequest.getValidatorId(), transferValidationRequestId),
                  o.getLastExceptionThatCausedRetry()),
          () -> {
            validatorApprovalApiService.create(
                tenantId, transferValidationRequestId, validatorRequest);
            return null;
          });
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to create approval request for validator %s of validation request %d. Retries exhausted with total tries %d duration %d ms.",
              validatorRequest.getValidatorId(),
              transferValidationRequestId,
              exception.getStatus().getTotalTries(),
              exception.getStatus().getTotalElapsedDuration().toMillis()));
      throw new OperationExhaustedException(exception);
    } catch (UnexpectedException exception) {
      logger.error(
          String.format(
              "An unexpected error occurred while creating approval request for validator %s of validation request %d.",
              validatorRequest.getValidatorId(), transferValidationRequestId),
          exception);
      throw new OperationFailedException(exception);
    }
  }
}
