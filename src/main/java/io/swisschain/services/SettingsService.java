package io.swisschain.services;

import io.swisschain.config.AppConfig;
import io.swisschain.crypto.asymmetric.AsymmetricEncryptionService;
import io.swisschain.crypto.symmetric.SymmetricEncryptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.crypto.InvalidCipherTextException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.CountDownLatch;

public class SettingsService {
  private static final String DEFAULT_LOCAL_FILE_NAME = "settings.json";

  private final Logger logger = LogManager.getLogger();

  private final CountDownLatch countDownLatch = new CountDownLatch(1);
  private final AsymmetricEncryptionService asymmetricEncryptionService;
  private final SymmetricEncryptionService symmetricEncryptionService;
  private final SettingsKeysService settingsKeysService;
  private final JsonSerializer jsonSerializer;
  private final String settingsUrl;
  private AppConfig config;

  public SettingsService(
      AsymmetricEncryptionService asymmetricEncryptionService,
      SymmetricEncryptionService symmetricEncryptionService,
      SettingsKeysService settingsKeysService,
      JsonSerializer jsonSerializer,
      String settingsUrl) {
    this.asymmetricEncryptionService = asymmetricEncryptionService;
    this.symmetricEncryptionService = symmetricEncryptionService;
    this.settingsKeysService = settingsKeysService;
    this.jsonSerializer = jsonSerializer;
    this.settingsUrl = settingsUrl;
  }

  public AppConfig get() {
    return config;
  }

  public void set(String encryptedKey, String nonce, String encryptedSettings) {
    if (config != null) {
      return;
    }

    byte[] key;
    try {
      key =
          asymmetricEncryptionService.decrypt(
              Base64.getDecoder().decode(encryptedKey), settingsKeysService.getPrivate());
    } catch (IOException | InvalidCipherTextException exception) {
      logger.error("An error occurred while decrypt key", exception);
      return;
    }

    var decryptedSettings =
        symmetricEncryptionService.decrypt(
            Base64.getDecoder().decode(encryptedSettings), key, Base64.getDecoder().decode(nonce));

    var settingsJson = new String(decryptedSettings, StandardCharsets.UTF_8);

    try {
      config = jsonSerializer.deserialize(settingsJson, AppConfig.class);
    } catch (Exception exception) {
      logger.error("An error occurred while deserializing settings", exception);
      return;
    }
    countDownLatch.countDown();
  }

  public AppConfig load() {
    if (config != null) {
      return config;
    }
    if (settingsUrl == null || settingsUrl.isEmpty() || settingsUrl.equals("secure")) {
      loadSecure();
    } else if (settingsUrl.startsWith("http")) {
      config = loadRemote(settingsUrl);
    } else {
      config = loadLocal(settingsUrl);
    }
    return config;
  }

  private void loadSecure() {
    try {
      countDownLatch.await();
    } catch (InterruptedException exception) {
      logger.error("An error occurred while waiting secure settings", exception);
    }
  }

  private AppConfig loadRemote(String url) {
    try {
      final var inputStream =
          new BufferedReader(new InputStreamReader(new URL(url).openConnection().getInputStream()));
      return jsonSerializer.deserialize(inputStream, AppConfig.class);
    } catch (Exception exception) {
      logger.error("An error occurred while loading remote settings", exception);
    }
    return null;
  }

  public AppConfig loadLocal(String url) {
    try {
      if (url.equals("local")) {
        final var inputStream =
            SettingsService.class.getClassLoader().getResourceAsStream(DEFAULT_LOCAL_FILE_NAME);
        return jsonSerializer.deserialize(inputStream, AppConfig.class);
      } else {
        final var inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(url)));
        return jsonSerializer.deserialize(inputStream, AppConfig.class);
      }
    } catch (Exception exception) {
      logger.error("An error occurred while loading local settings", exception);
    }
    return null;
  }
}
