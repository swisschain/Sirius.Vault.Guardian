package io.swisschain.domain;

public class KeyKeeper {
  private final String keyId;
  private final String publicKey;
  private final String description;

  public KeyKeeper(String keyId, String publicKey, String description) {
    this.keyId = keyId;
    this.publicKey = publicKey;
    this.description = description;
  }

  public String getKeyId() {
    return keyId;
  }

  public String getPublicKey() {
    return publicKey;
  }

  public String getDescription() {
    return description;
  }
}
