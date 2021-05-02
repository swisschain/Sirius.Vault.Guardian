package io.swisschain.domain.policies;

import io.swisschain.domain.validators.Validator;

import java.util.ArrayList;
import java.util.List;

public class RuleExecutionOutput {
  private RuleExecutionAction action;
  private List<Validator> validators;
  private String rejectReasonMessage;

  public RuleExecutionOutput(
      RuleExecutionAction action, List<Validator> validators, String rejectReasonMessage) {
    this.action = action;
    this.validators = validators;
    this.rejectReasonMessage = rejectReasonMessage;
  }

  public static RuleExecutionOutput createApprove() {
    return new RuleExecutionOutput(RuleExecutionAction.Approve, new ArrayList<>(), null);
  }

  public static RuleExecutionOutput createReject(String rejectReasonMessage) {
    return new RuleExecutionOutput(
        RuleExecutionAction.Reject, new ArrayList<>(), rejectReasonMessage);
  }

  public static RuleExecutionOutput createValidate(List<Validator> validators) {
    return new RuleExecutionOutput(RuleExecutionAction.Validate, validators, null);
  }

  public RuleExecutionAction getAction() {
    return action;
  }

  public void setAction(RuleExecutionAction action) {
    this.action = action;
  }

  public List<Validator> getValidators() {
    return validators;
  }

  public void setValidators(List<Validator> validators) {
    this.validators = validators;
  }

  public String getRejectReasonMessage() {
    return rejectReasonMessage;
  }

  public void setRejectReasonMessage(String rejectReasonMessage) {
    this.rejectReasonMessage = rejectReasonMessage;
  }
}
