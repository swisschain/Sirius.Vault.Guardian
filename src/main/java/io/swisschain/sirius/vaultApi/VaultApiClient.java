package io.swisschain.sirius.vaultApi;

import io.grpc.ManagedChannel;
import io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsGrpc;

import java.util.concurrent.TimeUnit;

public class VaultApiClient {
  private final ManagedChannel channel;

  private final TransferValidationRequestsGrpc.TransferValidationRequestsBlockingStub
      transferValidationRequests;

  public VaultApiClient(ManagedChannel channel) {
    this.channel = channel;

    this.transferValidationRequests = TransferValidationRequestsGrpc.newBlockingStub(channel);
  }

  public TransferValidationRequestsGrpc.TransferValidationRequestsBlockingStub
      getTransferValidationRequests() {
    return this.transferValidationRequests;
  }

  public void shutdown() throws InterruptedException {
    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  }
}
