package io.swisschain.tasks;

import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;
import io.swisschain.services.PolicyService;
import io.swisschain.services.ValidatorApprovalApiService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HandleValidatorResponsesTask implements Runnable {
  private static final Logger logger = LogManager.getLogger();
  private final ValidatorApprovalApiService validatorApprovalApiService;
  private final PolicyService policyService;

  public HandleValidatorResponsesTask(
      ValidatorApprovalApiService validatorApprovalApiService, PolicyService policyService) {
    this.validatorApprovalApiService = validatorApprovalApiService;
    this.policyService = policyService;

    logger.info("HandleValidatorResponsesTask created");
  }

  @Override
  public void run() {
    try {
      for (var validatorApproval : validatorApprovalApiService.get()) {
        try {
          policyService.processApproval(validatorApproval);
        } catch (Exception exception) {
          logger.error(
              String.format(
                  "An error occurred while processing approval result. TransferSigningRequestId: %d; ValidatorId: %s",
                  validatorApproval.getTransferApprovalRequestId(),
                  validatorApproval.getValidatorId()),
              exception);
        }
      }
    } catch (OperationExhaustedException exception) {
      logger.error("Operation exhausted while processing approval results.", exception);
    } catch (OperationFailedException exception) {
      logger.error("Operation failed while processing approval results.", exception);
    } catch (Exception exception) {
      logger.error("An unexpected error occurred while processing approval results.", exception);
    }
  }
}
