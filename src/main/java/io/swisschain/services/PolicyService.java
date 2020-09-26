package io.swisschain.services;

import io.swisschain.domain.policies.Policy;
import io.swisschain.domain.policies.PolicyOutput;
import io.swisschain.domain.transfers.TransferValidationRequest;

import java.util.ArrayList;
import java.util.List;

public class PolicyService {
  private final List<Policy> policies = new ArrayList<>();

  public PolicyService() {}

  public void add(String blockchain, double amount, int confirmations) {
    policies.add(new Policy(blockchain, amount, confirmations));
  }

  public PolicyOutput apply(TransferValidationRequest transferValidationRequest) {
    for (Policy policy : policies) {}

    return null;
  }
}
