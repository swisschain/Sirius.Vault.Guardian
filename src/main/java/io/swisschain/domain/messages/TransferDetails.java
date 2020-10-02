package io.swisschain.domain.messages;

public class TransferDetails {
    private String amount;
    private Asset asset;
    private String blockchainId;
    private String blockchainProtocolId;
    private ClientContext clientContext;
    private DestinationAddress destination;
    private String feeLimit;
    private String networkType;
    private String operationId;
    private SourceAddress source;

    public TransferDetails() {
    }

    public TransferDetails(
            String amount,
            Asset asset,
            String blockchainId,
            String blockchainProtocolId,
            ClientContext clientContext,
            DestinationAddress destination,
            String feeLimit,
            String networkType,
            String operationId,
            SourceAddress source) {
        this.amount = amount;
        this.asset = asset;
        this.blockchainId = blockchainId;
        this.blockchainProtocolId = blockchainProtocolId;
        this.clientContext = clientContext;
        this.destination = destination;
        this.feeLimit = feeLimit;
        this.networkType = networkType;
        this.operationId = operationId;
        this.source = source;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public String getBlockchainId() {
        return blockchainId;
    }

    public void setBlockchainId(String blockchainId) {
        this.blockchainId = blockchainId;
    }

    public String getBlockchainProtocolId() {
        return blockchainProtocolId;
    }

    public void setBlockchainProtocolId(String blockchainProtocolId) {
        this.blockchainProtocolId = blockchainProtocolId;
    }

    public ClientContext getClientContext() {
        return clientContext;
    }

    public void setClientContext(ClientContext clientContext) {
        this.clientContext = clientContext;
    }

    public DestinationAddress getDestination() {
        return destination;
    }

    public void setDestination(DestinationAddress destination) {
        this.destination = destination;
    }

    public String getFeeLimit() {
        return feeLimit;
    }

    public void setFeeLimit(String feeLimit) {
        this.feeLimit = feeLimit;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public SourceAddress getSource() {
        return source;
    }

    public void setSource(SourceAddress source) {
        this.source = source;
    }
}
