package io.swisschain.services;

import io.swisschain.crypto.asymmetric.AsymmetricEncryptionService;
import io.swisschain.domain.document.CustomerKey;
import io.swisschain.domain.document.SignatureValidationResult;
import io.swisschain.domain.document.TenantKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

public class DocumentValidator {
  private static final Logger logger = LogManager.getLogger();

  private final AsymmetricEncryptionService asymmetricEncryptionService;
  private final CustomerKey customerKey;
  private final List<TenantKey> tenantKeys;

  public DocumentValidator(
      AsymmetricEncryptionService asymmetricEncryptionService,
      CustomerKey customerKey,
      List<TenantKey> tenantKeys) {
    this.asymmetricEncryptionService = asymmetricEncryptionService;
    this.customerKey = customerKey;
    this.tenantKeys = tenantKeys;
  }

  public SignatureValidationResult Validate(String document, String signature, String tenantId) {
    String key = null;

    if (signature == null || signature.isEmpty()) {
      return SignatureValidationResult.CreateInvalid("No document signature");
    }

    for (var tenantKey : tenantKeys) {
      if (tenantKey.getTenantId().equals(tenantId)) {
        key = tenantKey.getPublicKey();
        break;
      }
    }

    if (key == null) {
      key = customerKey.getPublicKey();
    }

    try {
      var isValid =
          asymmetricEncryptionService.verifySignature(
              document.getBytes(StandardCharsets.UTF_8),
              Base64.getDecoder().decode(signature),
              key);

      if (isValid) {
        return SignatureValidationResult.CreateValid();
      }

      return SignatureValidationResult.CreateInvalid("Wrong document signature");

    } catch (IOException exception) {
      logger.error("An error occurred while verifying document signature.", exception);
      return SignatureValidationResult.CreateInvalid("Unknown error");
    }
  }
}
