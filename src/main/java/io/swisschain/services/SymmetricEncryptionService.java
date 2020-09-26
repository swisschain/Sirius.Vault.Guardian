package io.swisschain.services;

import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.AEADBlockCipher;
import org.bouncycastle.crypto.modes.GCMBlockCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Arrays;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.security.SecureRandom;

public class SymmetricEncryptionService {
  private final int KeyBitSize = 256;
  private final int MacBitSize = 128;
  private final int NonceBitSize = 128;

  private final SecureRandom random;

  public SymmetricEncryptionService() {
    random = new SecureRandom();
  }

  public byte[] encrypt(byte[] data, byte[] key) {
    if (data == null || data.length == 0) {
      throw new IllegalArgumentException("Value cannot be null empty. Parameter name: data.");
    }

    if (key == null) {
      throw new IllegalArgumentException("Value cannot be null. Parameter name: key.");
    }

    if (key.length != KeyBitSize / 8) {
      throw new IllegalArgumentException(
          String.format("Key should be %s bit. Parameter name: key.", KeyBitSize));
    }

    var nonce = new byte[NonceBitSize / 8];
    random.nextBytes(nonce);

    var cipher = new GCMBlockCipher(new AESEngine());

    var parameters = new AEADParameters(new KeyParameter(key), MacBitSize, nonce);
    cipher.init(true, parameters);

    var encryptedData = process(cipher, data);

    return Arrays.concatenate(nonce, encryptedData);
  }

  public byte[] decrypt(byte[] data, byte[] key) {
    if (data == null || data.length == 0) {
      throw new IllegalArgumentException("Value cannot be null or empty. Parameter name: data.");
    }

    if (key == null) {
      throw new IllegalArgumentException("Value cannot be null. Parameter name: key.");
    }

    if (key.length != KeyBitSize / 8) {
      throw new IllegalArgumentException(
          String.format("Key should be %s bit. Parameter name: key.", KeyBitSize));
    }

    var nonce = Arrays.copyOfRange(data, 0, NonceBitSize / 8);

    var cipher = new GCMBlockCipher(new AESEngine());

    var parameters = new AEADParameters(new KeyParameter(key), MacBitSize, nonce);
    cipher.init(false, parameters);

    var cipherData = Arrays.copyOfRange(data, nonce.length, data.length);

    return process(cipher, cipherData);
  }

  public byte[] generateKey() {
    var key = new byte[KeyBitSize / 8];
    random.nextBytes(key);
    key[key.length - 1] &= 0x7F;
    return key;
  }

  @Nullable
  private byte[] process(@NotNull AEADBlockCipher cipher, @NotNull byte[] in) {
    var out = new byte[cipher.getOutputSize(in.length)];
    var length = cipher.processBytes(in, 0, in.length, out, 0);
    try {
      cipher.doFinal(out, length);
    } catch (InvalidCipherTextException e) {
      return null;
    }

    return out;
  }
}
