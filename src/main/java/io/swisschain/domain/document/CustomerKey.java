package io.swisschain.domain.document;

public class CustomerKey {
  private String publicKey;

  public CustomerKey(String publicKey) {
    this.publicKey = publicKey;
  }

  public String getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }
}
