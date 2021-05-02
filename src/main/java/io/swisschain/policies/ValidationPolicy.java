package io.swisschain.policies;

public interface ValidationPolicy {
  void execute(long transferValidationRequestId) throws Exception;
}
