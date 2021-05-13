package io.swisschain.contracts.smart_contracts.deployment;

import io.swisschain.contracts.common.Blockchain;
import io.swisschain.contracts.common.Unit;
import io.swisschain.contracts.smart_contracts.FunctionArgument;

import java.util.List;
import java.util.Objects;

/** Represents a smart contract deployment details. */
public class SmartContractDeployment {
  private long id;
  private Blockchain blockchain;
  private SmartContractDeployer deployer;
  private List<FunctionArgument> arguments;
  private Unit payment;
  private Unit fee;
  private SmartContractDeploymentContext context;

  public SmartContractDeployment() {}

  public SmartContractDeployment(
      long id,
      Blockchain blockchain,
      SmartContractDeployer deployer,
      List<FunctionArgument> arguments,
      Unit payment,
      Unit fee,
      SmartContractDeploymentContext context) {
    this.id = id;
    this.blockchain = blockchain;
    this.deployer = deployer;
    this.arguments = arguments;
    this.payment = payment;
    this.fee = fee;
    this.context = context;
  }

  /** Gets the identifier. */
  public long getId() {
    return id;
  }

  /** Sets the identifier. */
  public void setId(long id) {
    this.id = id;
  }

  /** Gets the blockchain. */
  public Blockchain getBlockchain() {
    return blockchain;
  }

  /** Sets the blockchain. */
  public void setBlockchain(Blockchain blockchain) {
    this.blockchain = blockchain;
  }

  /** Gets the deployer broker account. */
  public SmartContractDeployer getDeployer() {
    return deployer;
  }

  /** Sets the deployer broker account. */
  public void setDeployer(SmartContractDeployer deployer) {
    this.deployer = deployer;
  }

  /** Gets the smart contract constructor arguments. */
  public List<FunctionArgument> getArguments() {
    return arguments;
  }

  /** Sets the smart contract constructor arguments. */
  public void setArguments(List<FunctionArgument> arguments) {
    this.arguments = arguments;
  }

  /** Gets the payment amount and asset for payable operations. */
  public Unit getPayment() {
    return payment;
  }

  /** Sets the payment amount and asset for payable operations. */
  public void setPayment(Unit payment) {
    this.payment = payment;
  }

  /** Gets the amount and asset for deployment. */
  public Unit getFee() {
    return fee;
  }

  /** Sets the amount and asset for deployment. */
  public void setFee(Unit fee) {
    this.fee = fee;
  }

  /** Gets the deployment context. */
  public SmartContractDeploymentContext getContext() {
    return context;
  }

  /** Sets the deployment context. */
  public void setContext(SmartContractDeploymentContext context) {
    this.context = context;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    var that = (SmartContractDeployment) o;
    return id == that.id
        && blockchain.equals(that.blockchain)
        && deployer.equals(that.deployer)
        && arguments.equals(that.arguments)
        && payment.equals(that.payment)
        && fee.equals(that.fee)
        && context.equals(that.context);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, blockchain, deployer, arguments, payment, fee, context);
  }
}
