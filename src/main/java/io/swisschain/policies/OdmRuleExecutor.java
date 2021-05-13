package io.swisschain.policies;

import io.swisschain.contracts.documents.ValidatorResolution;
import io.swisschain.domain.policies.RuleExecutionOutput;
import io.swisschain.domain.validators.Validator;
import io.swisschain.domain.validators.ValidatorRequest;
import io.swisschain.domain.validators.ValidatorRequestStatus;
import io.swisschain.odm.OdmClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class OdmRuleExecutor<T> {
  private static final Logger logger = LogManager.getLogger();

  private final OdmClient<T> odmClient;

  public OdmRuleExecutor(OdmClient<T> odmClient) {
    this.odmClient = odmClient;
  }

  protected RuleExecutionOutput execute(
      T request, List<ValidatorRequest> validatorRequests, List<Validator> validators)
      throws Exception {

    var validatorResolutions = new ArrayList<ValidatorResolution>();

    var completedValidatorRequests =
        validatorRequests.stream()
            .filter(
                validatorRequest ->
                    validatorRequest.getStatus() == ValidatorRequestStatus.Completed)
            .collect(Collectors.toList());

    for (var validatorRequest : completedValidatorRequests) {
      validatorResolutions.add(
          new ValidatorResolution(
              validatorRequest.getValidatorId(),
              validatorRequest.getDocument(),
              validatorRequest.getSignature(),
              validatorRequest.getResolution(),
              validatorRequest.getResolutionMessage(),
              validatorRequest.getDeviceInfo(),
              validatorRequest.getIp(),
              validatorRequest.getUpdatedAt()));
    }

    var result = odmClient.getDecision(request, validatorResolutions);

    switch (result.decision) {
      case Approve:
        return RuleExecutionOutput.createApprove();
      case Decline:
        return RuleExecutionOutput.createReject(result.comment);
      case Validate:
        var requestedValidators = new ArrayList<Validator>();

        for (var validatorId : result.validators) {
          var validator = getValidatorById(validatorId, validators);
          if (validator != null) {
            requestedValidators.add(validator);
          } else {
            logger.warn(String.format("Validator %s not found", validatorId));
          }
        }

        if (requestedValidators.size() == 0) {
          return RuleExecutionOutput.createReject("Can not reach required votes number");
        }
        return RuleExecutionOutput.createValidate(requestedValidators);
      default:
        throw new Exception(String.format("Unknown decision %s", result.decision.name()));
    }
  }

  private static Validator getValidatorById(String validatorId, List<Validator> validators) {
    return validators.stream()
        .filter(validator -> validator.getId().equals(validatorId))
        .findFirst()
        .orElse(null);
  }
}
