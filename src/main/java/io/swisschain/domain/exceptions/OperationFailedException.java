package io.swisschain.domain.exceptions;

public class OperationFailedException extends Exception {
  public OperationFailedException(Throwable cause) {
    super(cause);
  }
}
