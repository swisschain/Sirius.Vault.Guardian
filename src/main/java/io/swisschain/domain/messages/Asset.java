package io.swisschain.domain.messages;

public class Asset {
    private String assetAddress;
    private String assetId;
    private String symbol;

    public Asset() {
    }

    public Asset(String assetAddress, String assetId, String symbol) {
        this.assetAddress = assetAddress;
        this.assetId = assetId;
        this.symbol = symbol;
    }

    public String getAssetAddress() {
        return assetAddress;
    }

    public void setAssetAddress(String assetAddress) {
        this.assetAddress = assetAddress;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
