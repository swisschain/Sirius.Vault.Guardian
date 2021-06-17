package io.swisschain.crypto.asymmetric;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AsymmetricEncryptionKeyPair {
  private final String privateKey;
  private final String publicKey;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public AsymmetricEncryptionKeyPair(
      @JsonProperty("privateKey") String privateKey, @JsonProperty("publicKey") String publicKey) {
    this.privateKey = privateKey;
    this.publicKey = publicKey;
  }

  public String getPrivateKey() {
    return privateKey;
  }

  public String getPublicKey() {
    return publicKey;
  }
}
