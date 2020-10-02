package io.swisschain.domain.policies;

import java.util.List;

public class PolicyOutput {
  private PolicyOutputAction action;
  private List<String> approvers;

  public PolicyOutput() {}

  public PolicyOutput(PolicyOutputAction action, List<String> approvers) {
    this.action = action;
    this.approvers = approvers;
  }

  public PolicyOutputAction getAction() {
    return action;
  }

  public void setAction(PolicyOutputAction action) {
    this.action = action;
  }

  public List<String> getApprovers() {
    return approvers;
  }

  public void setApprovers(List<String> approvers) {
    this.approvers = approvers;
  }

  public static PolicyOutput createEmpty(){
    return new PolicyOutput(PolicyOutputAction.None, null);
  }

  public static PolicyOutput createApprove(){
    return new PolicyOutput(PolicyOutputAction.Approve, null);
  }
}
