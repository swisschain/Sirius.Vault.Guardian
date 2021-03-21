package io.swisschain.tasks;

import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;
import io.swisschain.services.PolicyService;
import io.swisschain.services.TransferApiService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HandleValidationRequestsTask implements Runnable {
  private static final Logger logger = LogManager.getLogger();

  private final TransferApiService transferApiService;
  private final PolicyService policyService;

  public HandleValidationRequestsTask(
      TransferApiService transferApiService, PolicyService policyService) {
    this.transferApiService = transferApiService;
    this.policyService = policyService;
    logger.info("TransferValidationTask created");
  }

  @Override
  public void run() {
    try {
      for (var transferValidationRequest : transferApiService.get()) {
        try {
          policyService.processNew(transferValidationRequest);
        } catch (Exception exception) {
          logger.error(
              String.format(
                  "An error occurred while processing transfer validation request. TransferSigningRequestId: %d; TenantId: %s",
                  transferValidationRequest.getId(), transferValidationRequest.getTenantId()),
              exception);
        }
      }
    } catch (OperationExhaustedException exception) {
      logger.error("Operation exhausted while processing transfer validation requests.", exception);
    } catch (OperationFailedException exception) {
      logger.error("Operation failed while processing transfer validation requests.", exception);
    } catch (Exception exception) {
      logger.error(
          "An unexpected error occurred while processing transfer validation requests.", exception);
    }
  }
}
