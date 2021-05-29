package io.swisschain.sirius.guardianApi;

import io.grpc.ManagedChannel;
import io.swisschain.sirius.guardianApi.generated.validation_requests.ValidationRequestsGrpc;
import io.swisschain.sirius.guardianApi.generated.validation_results.ValidationResultsGrpc;
import io.swisschain.sirius.guardianApi.generated.validators.ValidatorsGrpc;

import java.util.concurrent.TimeUnit;

public class GuardianApiClient {
  private final ManagedChannel channel;

  private final ValidatorsGrpc.ValidatorsBlockingStub validators;
  private final ValidationRequestsGrpc.ValidationRequestsBlockingStub validationRequests;
  private final ValidationResultsGrpc.ValidationResultsBlockingStub validationResults;

  public GuardianApiClient(ManagedChannel channel) {
    this.channel = channel;

    this.validators = ValidatorsGrpc.newBlockingStub(channel);
    this.validationRequests = ValidationRequestsGrpc.newBlockingStub(channel);
    this.validationResults = ValidationResultsGrpc.newBlockingStub(channel);
  }

  public ValidatorsGrpc.ValidatorsBlockingStub getValidators() {
    return this.validators;
  }

  public ValidationRequestsGrpc.ValidationRequestsBlockingStub getValidationRequests() {
    return this.validationRequests;
  }

  public ValidationResultsGrpc.ValidationResultsBlockingStub getValidationResults() {
    return this.validationResults;
  }

  public void shutdown() throws InterruptedException {
    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  }
}
