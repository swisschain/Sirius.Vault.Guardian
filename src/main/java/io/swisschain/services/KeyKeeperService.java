package io.swisschain.services;

import io.swisschain.domain.KeyKeeper;

import java.util.ArrayList;
import java.util.List;

public class KeyKeeperService {
  private final List<KeyKeeper> keyKeepers = new ArrayList<>();
  private final HashService hashService;

  public KeyKeeperService(HashService hashService) {
    this.hashService = hashService;
  }

  public void add(String publicKey, String description) {
    String keyId = hashService.computeHash(publicKey);

    if (getByKeyId(keyId) == null) {
      keyKeepers.add(new KeyKeeper(keyId, publicKey, description));
    }
  }

  public List<KeyKeeper> getAll() {
    return keyKeepers;
  }

  public KeyKeeper getByKeyId(String keyId) {
    return keyKeepers.stream()
        .filter(keyKeeper -> keyId.equals(keyKeeper.getKeyId()))
        .findAny()
        .orElse(null);
  }
}
