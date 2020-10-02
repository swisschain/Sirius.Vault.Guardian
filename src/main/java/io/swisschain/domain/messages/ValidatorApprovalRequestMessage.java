package io.swisschain.domain.messages;

public class ValidatorApprovalRequestMessage {
    private String resolution;
    private String resolutionMessage;
    private TransferDetails transferDetails;

    public ValidatorApprovalRequestMessage() {
    }

    public ValidatorApprovalRequestMessage(String resolution, String resolutionMessage, TransferDetails transferDetails) {
        this.resolution = resolution;
        this.resolutionMessage = resolutionMessage;
        this.transferDetails = transferDetails;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getResolutionMessage() {
        return resolutionMessage;
    }

    public void setResolutionMessage(String resolutionMessage) {
        this.resolutionMessage = resolutionMessage;
    }

    public TransferDetails getTransferDetails() {
        return transferDetails;
    }

    public void setTransferDetail(TransferDetails transferDetails) {
        this.transferDetails = transferDetails;
    }
}
