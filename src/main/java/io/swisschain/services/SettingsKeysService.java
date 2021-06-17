package io.swisschain.services;

import io.swisschain.crypto.asymmetric.AsymmetricEncryptionKeyPair;
import io.swisschain.crypto.asymmetric.AsymmetricEncryptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class SettingsKeysService {
  private static final String KEYS_FILE_NAME = "settings-keys.json";
  private static final Logger logger = LogManager.getLogger();

  private final AsymmetricEncryptionService asymmetricEncryptionService;
  private final JsonSerializer jsonSerializer;

  private AsymmetricEncryptionKeyPair keyPair;

  public SettingsKeysService(
      AsymmetricEncryptionService asymmetricEncryptionService, JsonSerializer jsonSerializer) {

    this.asymmetricEncryptionService = asymmetricEncryptionService;
    this.jsonSerializer = jsonSerializer;
  }

  public void init() throws Exception {
    AsymmetricEncryptionKeyPair keyPair = null;

    var filePath = Path.of(System.getProperty("user.dir"), KEYS_FILE_NAME);

    if (Files.exists(filePath)) {
      final var content = Files.readString(filePath);
      try {
        keyPair = jsonSerializer.deserialize(content, AsymmetricEncryptionKeyPair.class);
      } catch (Exception exception) {
        logger.error("An error occurred while deserializing settings key pair", exception);
      }
    }

    if (keyPair == null) {
      keyPair = asymmetricEncryptionService.generateKeyPairPem();

      final var content = jsonSerializer.serialize(keyPair);
      Files.write(filePath, content.getBytes(StandardCharsets.UTF_8));

      logger.info("Settings key pair generated");
    }

    this.keyPair = keyPair;
  }

  public String getPublic() {
    return keyPair != null ? keyPair.getPublicKey() : null;
  }

  public String getPrivate() {
    return keyPair != null ? keyPair.getPrivateKey() : null;
  }
}
