package io.swisschain.sirius.guardianApi;

import io.grpc.ManagedChannel;
import io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorsGrpc;

import java.util.concurrent.TimeUnit;

public class GuardianApiClient {
  private final ManagedChannel channel;

  private final ValidatorsGrpc.ValidatorsBlockingStub transferValidationRequests;

  public GuardianApiClient(ManagedChannel channel) {
    this.channel = channel;

    this.transferValidationRequests = ValidatorsGrpc.newBlockingStub(channel);
  }

  public ValidatorsGrpc.ValidatorsBlockingStub getTransactions() {
    return this.transferValidationRequests;
  }

  public void shutdown() throws InterruptedException {
    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  }
}
