package io.swisschain.tasks;

import io.swisschain.services.PolicyService;
import io.swisschain.services.TransferValidationRequestApiService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HandleValidationRequestsTask implements Runnable {
  private static final Logger logger = LogManager.getLogger();

  private final TransferValidationRequestApiService transferValidationRequestApiService;
  private final PolicyService policyService;

  public HandleValidationRequestsTask(
      TransferValidationRequestApiService transferValidationRequestApiService,
      PolicyService policyService) {
    this.transferValidationRequestApiService = transferValidationRequestApiService;
    this.policyService = policyService;
    logger.info("TransferValidationTask created");
  }

  @Override
  public void run() {
    var transferValidationRequests = transferValidationRequestApiService.getRequests();

    if (transferValidationRequests == null || transferValidationRequests.size() == 0) {
      return;
    }

    for (var transferValidationRequest : transferValidationRequests) {
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
  }
}
