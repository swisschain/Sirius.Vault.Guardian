package io.swisschain.services;

public class AsymmetricEncryptionKeyPair {
  private final String privateKey;
  private final String publicKey;

  public AsymmetricEncryptionKeyPair(String privateKey, String publicKey) {
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
