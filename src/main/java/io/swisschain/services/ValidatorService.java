package io.swisschain.services;

import io.swisschain.crypto.HashService;
import io.swisschain.domain.validators.Validator;

import java.util.ArrayList;
import java.util.List;

public class ValidatorService {
  private final List<Validator> validators = new ArrayList<>();
  private final HashService hashService;

  public ValidatorService(HashService hashService) {
    this.hashService = hashService;
  }

  public void add(String name, String publicKey) {
    var id = hashService.computeHash(publicKey);

    if (getByKeyId(id) == null) {
      validators.add(new Validator(id, name, publicKey));
    }
  }

  public boolean hasValidators(){
    return validators.size() > 0;
  }

  public List<Validator> getAll() {
    return validators;
  }

  public Validator getByKeyId(String keyId) {
    return validators.stream()
        .filter(validator -> keyId.equals(validator.getId()))
        .findAny()
        .orElse(null);
  }
}
