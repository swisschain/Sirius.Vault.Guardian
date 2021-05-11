package io.swisschain.contracts.smart_contracts.invocation;

import io.swisschain.contracts.common.Blockchain;
import io.swisschain.contracts.common.Unit;
import io.swisschain.contracts.smart_contracts.FunctionArgument;

import java.util.List;
import java.util.Objects;

/** Represents a smart contract invocation details. */
public class SmartContractInvocation {
  private long id;
  private Blockchain blockchain;
  private SmartContractInvoker invoker;
  private SmartContractMethod method;
  private String smartContractAddress;
  private List<FunctionArgument> arguments;
  private Unit payment;
  private Unit fee;
  private SmartContractInvocationContext context;

  public SmartContractInvocation() {}

  public SmartContractInvocation(
      long id,
      Blockchain blockchain,
      SmartContractInvoker invoker,
      SmartContractMethod method,
      String smartContractAddress,
      List<FunctionArgument> arguments,
      Unit payment,
      Unit fee,
      SmartContractInvocationContext context) {
    this.id = id;
    this.blockchain = blockchain;
    this.invoker = invoker;
    this.method = method;
    this.smartContractAddress = smartContractAddress;
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

  /** Gets the invoker broker account. */
  public SmartContractInvoker getInvoker() {
    return invoker;
  }

  /** Sets the invoker broker account. */
  public void setInvoker(SmartContractInvoker invoker) {
    this.invoker = invoker;
  }

  /** Gets the smart contract method. */
  public SmartContractMethod getMethod() {
    return method;
  }

  /** Sets the smart contract method. */
  public void setMethod(SmartContractMethod method) {
    this.method = method;
  }

  /** Gets the smart contract address. */
  public String getSmartContractAddress() {
    return smartContractAddress;
  }

  /** Sets the smart contract address. */
  public void setSmartContractAddress(String smartContractAddress) {
    this.smartContractAddress = smartContractAddress;
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

  /** Gets the amount and asset for invocation. */
  public Unit getFee() {
    return fee;
  }

  /** Sets the amount and asset for invocation. */
  public void setFee(Unit fee) {
    this.fee = fee;
  }

  /** Gets the invocation context. */
  public SmartContractInvocationContext getContext() {
    return context;
  }

  /** Sets the invocation context. */
  public void setContext(SmartContractInvocationContext context) {
    this.context = context;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    var that = (SmartContractInvocation) o;
    return id == that.id
        && blockchain.equals(that.blockchain)
        && invoker.equals(that.invoker)
        && method.equals(that.method)
        && smartContractAddress.equals(that.smartContractAddress)
        && arguments.equals(that.arguments)
        && Objects.equals(payment, that.payment)
        && Objects.equals(fee, that.fee)
        && context.equals(that.context);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, blockchain, invoker, method, smartContractAddress, arguments, payment, fee, context);
  }
}
