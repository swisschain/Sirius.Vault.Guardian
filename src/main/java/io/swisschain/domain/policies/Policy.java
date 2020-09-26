package io.swisschain.domain.policies;

public class Policy {
  private String blockchain;
  private double amount;
  private int confirmations;

  public Policy(String blockchain, double amount, int confirmations) {
    this.blockchain = blockchain;
    this.amount = amount;
    this.confirmations = confirmations;
  }

  public String getBlockchain() {
    return blockchain;
  }

  public double getAmount() {
    return amount;
  }

  public int getConfirmations() {
    return confirmations;
  }
}
