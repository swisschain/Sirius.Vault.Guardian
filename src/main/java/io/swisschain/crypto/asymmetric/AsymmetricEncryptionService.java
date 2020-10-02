package io.swisschain.crypto.asymmetric;

import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.encodings.PKCS1Encoding;
import org.bouncycastle.crypto.engines.RSAEngine;
import org.bouncycastle.crypto.generators.RSAKeyPairGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.RSAKeyGenerationParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.signers.RSADigestSigner;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.crypto.util.PrivateKeyInfoFactory;
import org.bouncycastle.crypto.util.SubjectPublicKeyInfoFactory;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.SecureRandom;

public class AsymmetricEncryptionService {
  public static final String PrivateKeyType = "RSA PRIVATE KEY";
  public static final String PublicKeyType = "PUBLIC KEY";

  private static final long PublicExponent = 3;
  private static final int Strength = 1024;
  private static final int Certainty = 25;

  private final SecureRandom secureRandom = new SecureRandom();

  public byte[] generateSignature(byte[] data, String privateKey)
      throws IOException, CryptoException {
    var signer = new RSADigestSigner(new SHA256Digest());
    signer.init(true, getPrivateKeyParameters(privateKey));
    signer.update(data, 0, data.length);
    return signer.generateSignature();
  }

  public boolean verifySignature(byte[] data, byte[] signature, String publicKey)
      throws IOException {
    var signer = new RSADigestSigner(new SHA256Digest());
    signer.init(false, getPublicKeyParameters(publicKey));
    signer.update(data, 0, data.length);
    return signer.verifySignature(signature);
  }

  public byte[] encrypt(byte[] data, String publicKey) throws IOException, InvalidCipherTextException {
    var cipher = new PKCS1Encoding(new RSAEngine());
    cipher.init(true, getPublicKeyParameters(publicKey));
    return cipher.processBlock(data, 0, data.length);
  }

  public byte[] decrypt(byte[] data, String privateKey) throws IOException, InvalidCipherTextException {
    var cipher = new PKCS1Encoding(new RSAEngine());
    cipher.init(false, getPrivateKeyParameters(privateKey));
    return cipher.processBlock(data, 0, data.length);
  }

  public AsymmetricEncryptionKeyPair generateKeyPairPem() throws IOException {
    var keyPairGenerator = new RSAKeyPairGenerator();

    var parameters =
        new RSAKeyGenerationParameters(
            BigInteger.valueOf(PublicExponent), secureRandom, Strength, Certainty);

    keyPairGenerator.init(parameters);

    var keyPair = keyPairGenerator.generateKeyPair();

    var privateKey = exportPrivateKey(keyPair.getPrivate());
    var publicKey = exportPublicKey(keyPair.getPublic());

    return new AsymmetricEncryptionKeyPair(privateKey, publicKey);
  }

  private String exportPublicKey(AsymmetricKeyParameter keyParameter) throws IOException {
    var publicKeyInfo = SubjectPublicKeyInfoFactory.createSubjectPublicKeyInfo(keyParameter);
    var encodedKey = publicKeyInfo.toASN1Primitive().getEncoded();
    try (var writer = new StringWriter();
        var pemWriter = new PemWriter(writer)) {
      pemWriter.writeObject(new PemObject(PublicKeyType, encodedKey));
      pemWriter.flush();
      return writer.toString();
    }
  }

  private String exportPrivateKey(AsymmetricKeyParameter privateKeyParameter) throws IOException {
    var privateKeyInfo = PrivateKeyInfoFactory.createPrivateKeyInfo(privateKeyParameter);
    try (var writer = new StringWriter();
        var pemWriter = new PemWriter(writer)) {
      pemWriter.writeObject(new PemObject(PrivateKeyType, privateKeyInfo.getEncoded()));
      pemWriter.flush();
      return writer.toString();
    }
  }

  private AsymmetricKeyParameter getPrivateKeyParameters(String privateKey) throws IOException {
    try (var reader = new StringReader(privateKey);
        var pemReader = new PemReader(reader)) {
      return PrivateKeyFactory.createKey(pemReader.readPemObject().getContent());
    }
  }

  private RSAKeyParameters getPublicKeyParameters(String publicKey) throws IOException {
    try (var reader = new StringReader(publicKey);
        var pemReader = new PemReader(reader)) {

      var content = pemReader.readPemObject().getContent();
      var asn1PublicKey = SubjectPublicKeyInfo.getInstance(content).parsePublicKey();
      var key = org.bouncycastle.asn1.pkcs.RSAPublicKey.getInstance(asn1PublicKey);

      return new RSAKeyParameters(false, key.getModulus(), key.getPublicExponent());
    }
  }
}
