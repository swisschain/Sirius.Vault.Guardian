package io.swisschain.services;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HashServiceTests {

  private HashService service;

  @Before
  public void initialize() {
    service = new HashService();
  }

  @Test
  public void compute_valid_hash_from_key_keeper_public_key() {

    // arrange

    String value =
        "-----BEGIN PUBLIC KEY-----\nMIGdMA0GCSqGSIb3DQEBAQUAA4GLADCBhwKBgQC4Wt1Qqxu6ykcZiH1FvKyaZdUO\nMlF41KlnVq1IPi91X00rqIgiQl2odsdmJvJ3gqhw68APYmxctgj9gBkfhnTxiUbZ\n5kDwGRBuCdv5mQpKf6Qv9IkXHh9pF1IUrFJGo4tNDDgfFbqM32Dp7S3AOGt6fk5i\nFVZnZuWFEzzMkTIjvwIBAw==\n-----END PUBLIC KEY-----";

    // ack

    String actualResult = service.computeHash(value);

    // assert

    assertEquals("xY22Pqt1jUUIDVBo+6MWUmdmdA/8UwzTOnZ1bOMFRcs=", actualResult);
  }
}
