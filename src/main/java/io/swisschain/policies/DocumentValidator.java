package io.swisschain.policies;

import io.swisschain.crypto.asymmetric.AsymmetricEncryptionService;
import io.swisschain.domain.document.CustomerKey;
import io.swisschain.domain.document.SignatureValidationResult;
import io.swisschain.domain.document.TenantKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

  public SignatureValidationResult validateWithApiKey(
      String document, String signature, String apiKey, String tenantId) {
    if (document == null || document.isEmpty()) {
      return SignatureValidationResult.CreateValid();
    }

    if ((signature == null || signature.isEmpty()) && (apiKey == null || apiKey.isEmpty())) {
      return SignatureValidationResult.CreateValid();
    }

    return validateWithTenantId(document, signature, tenantId);
  }

  public SignatureValidationResult validateWithTenantId(
      String document, String signature, String tenantId) {
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
    return validateWithPublicKey(document, signature, key);
  }

  public SignatureValidationResult validateWithPublicKey(
      String document, String signature, String publicKey) {
    byte[] signatureData;
    try {
      signatureData = Base64.getDecoder().decode(signature);
    } catch (IllegalArgumentException exception) {
      logger.warn("Invalid signature format", exception);
      return SignatureValidationResult.CreateInvalid("Invalid signature");
    }
    boolean isValid;
    try {
      isValid =
          asymmetricEncryptionService.verifySignature(
              document.getBytes(StandardCharsets.UTF_8), signatureData, publicKey);
    } catch (Exception exception) {
      logger.error("An error occurred while verifying document signature", exception);
      return SignatureValidationResult.CreateInvalid("Unknown error");
    }
    if (!isValid) {
      return SignatureValidationResult.CreateInvalid("Wrong document signature");
    }
    return SignatureValidationResult.CreateValid();
  }
}
