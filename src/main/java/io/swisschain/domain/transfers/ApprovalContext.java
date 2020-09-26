package io.swisschain.domain.transfers;

import java.util.List;

public class ApprovalContext {
  private List<ApprovalResult> approvedBy;
  private List<ApprovalResult> rejectedBy;
  private List<ApprovalResult> skippedBy;
  private List<String> validators;
}
