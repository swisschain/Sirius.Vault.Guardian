package io.swisschain.odm;

import com.evanlennick.retry4j.exception.RetriesExhaustedException;
import com.evanlennick.retry4j.exception.UnexpectedException;
import io.swisschain.common.durability.RetryPolicies;
import io.swisschain.contracts.documents.ValidatorResolution;
import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;
import io.swisschain.odm.models.DecisionResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class OdmClientRetryDecorator<T> implements OdmClient<T> {
  private final Logger logger = LogManager.getLogger();
  private final OdmClient<T> odmClient;

  public OdmClientRetryDecorator(OdmClient<T> odmClient) {
    this.odmClient = odmClient;
  }

  @Override
  public DecisionResponse getDecision(T request, List<ValidatorResolution> validatorResolutions)
      throws OperationFailedException, OperationExhaustedException {
    try {
      var status =
          RetryPolicies.ExecuteWithDefaultRestConfig(
              o -> logger.warn("Failed to get decision.", o.getLastExceptionThatCausedRetry()),
              () -> odmClient.getDecision(request, validatorResolutions));
      return status.getResult();
    } catch (RetriesExhaustedException exception) {
      logger.error(
          String.format(
              "Failed to get decision. Retries exhausted with total tries %d duration %d ms.",
              exception.getStatus().getTotalTries(),
              exception.getStatus().getTotalElapsedDuration().toMillis()));
      throw new OperationExhaustedException(exception);
    } catch (UnexpectedException exception) {
      logger.error("An unexpected error occurred while getting decision.", exception);
      throw new OperationFailedException(exception);
    }
  }
}
