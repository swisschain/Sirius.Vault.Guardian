package io.swisschain.sirius.vaultApi;

import io.grpc.ManagedChannel;
import io.swisschain.sirius.vaultApi.generated.smart_contract_deployment_validation_requests.SmartContractDeploymentValidationRequestsGrpc;
import io.swisschain.sirius.vaultApi.generated.transfer_validation_requests.TransferValidationRequestsGrpc;

import java.util.concurrent.TimeUnit;

public class VaultApiClient {
  private final ManagedChannel channel;

  private final TransferValidationRequestsGrpc.TransferValidationRequestsBlockingStub
      transferValidationRequests;

  private final SmartContractDeploymentValidationRequestsGrpc
          .SmartContractDeploymentValidationRequestsBlockingStub
      smartContractDeploymentValidationRequests;

  public VaultApiClient(ManagedChannel channel) {
    this.channel = channel;

    this.transferValidationRequests = TransferValidationRequestsGrpc.newBlockingStub(channel);
    this.smartContractDeploymentValidationRequests =
        SmartContractDeploymentValidationRequestsGrpc.newBlockingStub(channel);
  }

  public TransferValidationRequestsGrpc.TransferValidationRequestsBlockingStub
      getTransferValidationRequests() {
    return this.transferValidationRequests;
  }

  public SmartContractDeploymentValidationRequestsGrpc
          .SmartContractDeploymentValidationRequestsBlockingStub
      getSmartContractDeploymentValidationRequests() {
    return this.smartContractDeploymentValidationRequests;
  }

  public void shutdown() throws InterruptedException {
    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  }
}
