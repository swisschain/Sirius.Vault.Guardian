package io.swisschain.crypto.symmetric;

import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.modes.GCMBlockCipher;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.jetbrains.annotations.Nullable;

import java.security.SecureRandom;
import java.util.Arrays;

public class SymmetricEncryptionService {
  private final int KeyBitSize = 256;
  private final int MacBitSize = 128;
  private final int NonceBitSize = 128;

  private final SecureRandom random;

  public SymmetricEncryptionService() {
    random = new SecureRandom();
  }

  public byte[] encrypt(byte[] data, byte[] key, byte[] nonce) {
    return process(data, key, nonce, true);
  }

  public byte[] decrypt(byte[] data, byte[] key, byte[] nonce) {
    return process(data, key, nonce, false);
  }

  public byte[] generateKey() {
    var key = new byte[KeyBitSize / 8];
    random.nextBytes(key);
    key[key.length - 1] &= 0x7F;
    return key;
  }

  public byte[] generateNonce() {
    var nonce = new byte[NonceBitSize / 8];
    random.nextBytes(nonce);
    return nonce;
  }

  @Nullable
  private byte[] process(byte[] data, byte[] key, byte[] nonce, boolean encrypt) {
    if (data == null || data.length == 0) {
      throw new IllegalArgumentException("Value cannot be null or empty. Parameter name: data.");
    }

    if (key == null) {
      throw new IllegalArgumentException("Value cannot be null. Parameter name: key.");
    }

    if (nonce == null) {
      throw new IllegalArgumentException("Value cannot be null. Parameter name: nonce.");
    }

    if (key.length != KeyBitSize / 8) {
      throw new IllegalArgumentException(
          String.format("Key should be %s bit. Parameter name: key.", KeyBitSize));
    }

    if (nonce.length != NonceBitSize / 8) {
      throw new IllegalArgumentException(
          String.format("Nonce should be %s bit. Parameter name: nonce.", NonceBitSize));
    }

    var cipher =
        new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()), new PKCS7Padding());

    var parameters = new ParametersWithIV(new KeyParameter(key), nonce);
    cipher.init(encrypt, parameters);

    var buffer = new byte[cipher.getOutputSize(data.length)];
    var length = cipher.processBytes(data, 0, data.length, buffer, 0);

    try {
      length += cipher.doFinal(buffer, length);
    } catch (InvalidCipherTextException e) {
      return null;
    }
    return Arrays.copyOfRange(buffer, 0, length);
  }
}
