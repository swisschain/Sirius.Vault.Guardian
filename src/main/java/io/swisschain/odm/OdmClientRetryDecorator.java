package io.swisschain.odm;

import com.evanlennick.retry4j.exception.RetriesExhaustedException;
import com.evanlennick.retry4j.exception.UnexpectedException;
import io.swisschain.common.durability.RetryPolicies;
import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;
import io.swisschain.odm.contracts.policy.PolicyRequest;
import io.swisschain.odm.contracts.policy.PolicyResponse;
import io.swisschain.odm.contracts.selector.PolicySelectorRequest;
import io.swisschain.odm.contracts.selector.PolicySelectorResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OdmClientRetryDecorator implements OdmClient {
  private final Logger logger = LogManager.getLogger();
  private final OdmClient odmClient;

  public OdmClientRetryDecorator(OdmClient odmClient) {
    this.odmClient = odmClient;
  }

  @Override
  public PolicySelectorResponse getPolicy(
      PolicySelectorRequest policySelectorRequest, String requestId)
      throws OperationFailedException, OperationExhaustedException {
    try {
      var status =
          RetryPolicies.ExecuteWithDefaultRestConfig(
              o -> logger.warn("Failed to get policy.", o.getLastExceptionThatCausedRetry()),
              () -> odmClient.getPolicy(policySelectorRequest, requestId));
      return status.getResult();
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to get policy. Retries exhausted with total tries %d duration %d ms.",
              exception.getStatus().getTotalTries(),
              exception.getStatus().getTotalElapsedDuration().toMillis()));
      throw new OperationExhaustedException(exception);
    } catch (UnexpectedException exception) {
      logger.error("An unexpected error occurred while getting policy.", exception);
      throw new OperationFailedException(exception);
    }
  }

  @Override
  public PolicyResponse getResolution(
      PolicyRequest policyRequest,
      String requestId,
      String policyService,
      String policyServiceVersion,
      String policy,
      String policyVersion)
      throws OperationFailedException, OperationExhaustedException {
    try {
      var status =
          RetryPolicies.ExecuteWithDefaultRestConfig(
              o -> logger.warn("Failed to get resolution.", o.getLastExceptionThatCausedRetry()),
              () ->
                  odmClient.getResolution(
                      policyRequest,
                      requestId,
                      policyService,
                      policyServiceVersion,
                      policy,
                      policyVersion));
      return status.getResult();
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to get resolution. Retries exhausted with total tries %d duration %d ms.",
              exception.getStatus().getTotalTries(),
              exception.getStatus().getTotalElapsedDuration().toMillis()));
      throw new OperationExhaustedException(exception);
    } catch (UnexpectedException exception) {
      logger.error("An unexpected error occurred while getting resolution.", exception);
      throw new OperationFailedException(exception);
    }
  }
}
