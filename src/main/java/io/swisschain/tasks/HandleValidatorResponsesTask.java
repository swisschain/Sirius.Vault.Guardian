package io.swisschain.tasks;

import io.swisschain.services.PolicyService;
import io.swisschain.services.ValidatorsApiService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HandleValidatorResponsesTask implements Runnable {
  private static final Logger logger = LogManager.getLogger();
  private final ValidatorsApiService validatorsApiService;
  private final PolicyService policyService;

  public HandleValidatorResponsesTask(
      ValidatorsApiService validatorsApiService, PolicyService policyService) {
    this.validatorsApiService = validatorsApiService;
    this.policyService = policyService;

    logger.info("HandleValidatorResponsesTask created");
  }

  @Override
  public void run() {
    var validatorApprovals = validatorsApiService.getApprovals();

    if (validatorApprovals == null || validatorApprovals.size() == 0) {
      return;
    }

    for (var validatorApproval : validatorApprovals) {
      try {
        policyService.processApproval(validatorApproval);
      } catch (Exception exception) {
        logger.error("An error occurred while processing approval result.", exception);
      }
    }
  }
}
