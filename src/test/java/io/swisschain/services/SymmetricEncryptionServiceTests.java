package io.swisschain.services;

import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.Base64;

import static org.junit.Assert.assertEquals;

public class SymmetricEncryptionServiceTests {
  private SymmetricEncryptionService service;

  @Before
  public void initialize() {
    service = new SymmetricEncryptionService();
  }

  @Test
  public void encrypt_and_decrypt() {
    // arrange

    var key = service.generateKey();
    var data = Utils.generateRandomString(500);

    // act

    var encryptedData = service.encrypt(data.getBytes(Charset.defaultCharset()), key);
    var decryptedData = service.decrypt(encryptedData, key);

    // assert

    assertEquals(data, new String(decryptedData, Charset.defaultCharset()));
  }

  @Test
  public void decrypt() {
    // arrange

    var key = Base64.getDecoder().decode("mMdmPb+2YAu843XdNUVQ2TsIr5HCe012IlUzI2UWyVc=");
    var encryptedData =
        Base64.getDecoder().decode("E7Ew1uau+pA+Hs9wrkkcBLuIQFyTZox2eK/eDKC3gL00Z1OQGN10HDx5n84=");

    // act

    var decryptedDate = service.decrypt(encryptedData, key);

    // assert

    assertEquals("FFFGFSJVS8IL", new String(decryptedDate, Charset.defaultCharset()));
  }
}
