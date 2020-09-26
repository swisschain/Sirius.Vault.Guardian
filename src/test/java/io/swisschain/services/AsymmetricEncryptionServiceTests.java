package io.swisschain.services;

import org.bouncycastle.crypto.CryptoException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AsymmetricEncryptionServiceTests {

  private AsymmetricEncryptionService service;

  @Before
  public void initialize() {
    service = new AsymmetricEncryptionService();
  }

  @Test
  public void encrypt_and_decrypt() throws IOException {
    // arrange

    var secret = new SymmetricEncryptionService().generateKey();

    // act

    var pair = service.generateKeyPairPem();

    var encryptedData = service.encrypt(secret, pair.getPublicKey());

    var decryptedData = service.decrypt(encryptedData, pair.getPrivateKey());

    // assert

    var secretBase64 = Base64.getEncoder().encodeToString(secret);
    var decryptedDataBase64 = Base64.getEncoder().encodeToString(decryptedData);

    assertEquals(secretBase64, decryptedDataBase64);
  }

  @Test
  public void decrypt() throws IOException {
    // arrange

    var privateKey =
        "-----BEGIN RSA PRIVATE KEY-----\r\nMIICWwIBAAKBgQCHC8H/fW5NO1HV3mvn/zQ6vq47dE8Tz8wXFDeAF7Db7GSw7F0V\r\noHn3HFXmwx0QlaPUhlP+q8PWhy99Ya+taS4bSfPv7LL8t3Jyle5FGdkxXAbTu7YY\r\nxSv4Ms6ctZL6JpJSHdVqAMssm1n49a7W5uCeeeGX3PA5Y0nXvD4zmdvY+QIBAwKB\r\ngBaB9aqU57eJ4vj6Z1FVM18fx7STYoNNTK6DXpVZSCSnZh18ui5FaakvY6Z12i1u\r\nRfjBDf/HS05r3T+QR/I8MlmjPdT6JE+NPyUKCbN2xQMSyU9DytrNTIhUEow5B4Q4\r\neyXA4oIIGi/mZ9h65MkwHVfIKTNPDqJ74EvwYuNbnfNpAkEA0WvrjqAmBhoAynqm\r\nUs92JNzqG/3ZgqMNLmCQm2EipVeWlO4RarJrj4gZMkCtuG06mt4JEyzqLpUIZhBD\r\niywYtwJBAKUVBoE4+WHdk485af5rqMZyDgj3HnK9uQv+wsQEvi5X2QOYTGV7oaVi\r\nzOIRcg3DVS4MWu9rgE1zB7Pcm+j8C88CQQCLnUe0asQEEVXcUcQ3NPlt6JwSqTus\r\nbLN0QGBnlhcY5Q8N9AucdvJfsBDMKx5683xnPrC3c0bJuLBECteyHWXPAkBuDgRW\r\nJfuWk7e00PFUR8XZoV6wpL73KSYH/yyCrdQe5TtXut2Y/RZuQd3sC6FegjjJXZH0\r\n8lWI91p36GfwqAffAkEArpaKuoz9+ZPbhzqwb93xUdUQlKo/xm1QTgahwSRavBQG\r\n0N/GYL8zp1U7Ez9pYSp4ailBVbK2wHc9V08W6sD81w==\r\n-----END RSA PRIVATE KEY-----\r\n";
    var encryptedData =
        "GPEfp91caH3eNJPRCxNWrkNdbBjJppPzhDEvphN+QUESOhUCmPPKQ36dBywUlK16rTqTLWnSqe+NO4+jnCjfTxRuk+n9F6dpCkKDK+QvRzBCvPkvLeZAY+RWI5IzAVm6rr0PwoMHMqARgnkC90QVQqISkWKK2tHje1kqYfS4+2A=";
    // act

    var decryptedDate = service.decrypt(Base64.getDecoder().decode(encryptedData), privateKey);

    // assert

    assertEquals(
        "HE5ADDM79BXSUIHHH1IZZ74ZCDMGPECKFIMZ1UBJL704HXQ8Q96RVIJKKG790FR59MNM6SQXZ8O1GASPXOZPHVZ78BPVWBSFP445JDB8TJ1W3RDJXSMMPBYNWQN4C4BX",
        new String(decryptedDate, Charset.defaultCharset()));
  }

  @Test
  public void sign_and_verify() throws IOException, CryptoException {
    // arrange

    var data = Utils.generateRandomString(128);

    // act

    var keyPair = service.generateKeyPairPem();
    var signature =
        service.generateSignature(data.getBytes(Charset.defaultCharset()), keyPair.getPrivateKey());

    var isValid =
        service.verifySignature(
            data.getBytes(Charset.defaultCharset()), signature, keyPair.getPublicKey());

    // assert

    assertTrue(isValid);
  }

  @Test
  public void verify_signature() throws IOException {
    // arrange

    var publicKey =
        "-----BEGIN PUBLIC KEY-----\r\nMIGdMA0GCSqGSIb3DQEBAQUAA4GLADCBhwKBgQDCW1th70Ih+V8eP5n2Q+Q6lXQI\r\nO/bGkrH1zslSuQmyIkqYhPmd2054TOhyZBgVL8L7u9hERNAT1lAEnQ7sDoej1eKG\r\n5fZcKRmkwchFpLSbNNGjqhxNpOA7f1ZOrQca+cSze4szDyKRHoA9idwGa0b72hwr\r\nIZngDOeQiYwpAQRs/QIBAw==\r\n-----END PUBLIC KEY-----\r\n";

    var data =
        "W97DQOHBJ4YXO62O2QTRY5HYGP8KZOYG9NCOI9M747GQEKRUPJOWCTEL8TP9KU7WWXY8LK16CAKCVK7CHMM4N0FQLC9AY99AC19YD4HUZL95J2W7FXVGUMJ06X1VA0K7";

    var signature =
        "Cgg36VmJlMsYhBl/W5ut2a4nRANbdGoov1/UwHsnOed7M3eywnxI3u4YGSBc6GKklZLGqJBQLRxRCqYw8hi6toxMgkOLfogGDCI19gXOhB4M8ARo23bu+dkjlAyYWZmDf0+oOLy2zlsxkxTQ8BtSVBc5ohAzmZzNAOiqtVQzQes=";

    // act

    var isValid =
        service.verifySignature(
            data.getBytes(Charset.defaultCharset()),
            Base64.getDecoder().decode(signature),
            publicKey);

    // assert

    assertTrue(isValid);
  }
}
