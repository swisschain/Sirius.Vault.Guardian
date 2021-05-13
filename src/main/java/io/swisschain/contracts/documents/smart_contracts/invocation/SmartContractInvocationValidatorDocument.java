package io.swisschain.contracts.documents.smart_contracts.invocation;

import io.swisschain.contracts.documents.Resolution;
import io.swisschain.contracts.documents.ValidatorDocument;
import io.swisschain.contracts.smart_contracts.invocation.SmartContractInvocation;

/** Represents validator smart contract invocation validation document. */
public class SmartContractInvocationValidatorDocument extends ValidatorDocument {
  private SmartContractInvocation invocation;

  public SmartContractInvocationValidatorDocument() {
    super();
  }

  public SmartContractInvocationValidatorDocument(
      Resolution resolution, String resolutionMessage, SmartContractInvocation invocation) {
    super(resolution, resolutionMessage);
    this.invocation = invocation;
  }

  /** Gets smart contract invocation details. */
  public SmartContractInvocation getInvocation() {
    return invocation;
  }

  /** Sets smart contract invocation details. */
  public void setInvocation(SmartContractInvocation invocation) {
    this.invocation = invocation;
  }
}
