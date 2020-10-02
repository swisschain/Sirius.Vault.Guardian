package io.swisschain.crypto.symmetric;

import io.swisschain.crypto.Utils;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    var nonce = service.generateNonce();
    var data = Utils.generateRandomString(500);

    // act

    var encryptedData = service.encrypt(data.getBytes(StandardCharsets.UTF_8), key, nonce);
    var decryptedData = service.decrypt(encryptedData, key, nonce);

    var originString = new String(data.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
    var actualValue = new String(decryptedData, StandardCharsets.UTF_8);
    // assert

    assertEquals(data, actualValue);
  }

  @Test
  public void decrypt() {
    // arrange

    var key = Base64.getDecoder().decode("Sbu9VBzfZPhrZDbUJ+5iH7zM1FW22eVwZ6f9rkiGnVU=");
    var nonce = Base64.getDecoder().decode("zcK58atDZhXgXcqs5DdTIA==");
    var encryptedData =
        Base64.getDecoder()
            .decode(
                "CjjckdqhNsUuC2tpxUMveEi+2ewnheU05ZeM4Xcdxvg+OMjg+K1ymW85kDlxltvAJC3ylC+5eivOH5fnOhfqwM11/1Oqa0QYtzM+uSkBoeuE+UnRny1yWN9qv7m2gL9qvXhxgsLE7bxkvsYjihkDILsMYFwxE2qNXBOoRG1s8uwbekq9CjFdrVrxtj26+TgSoOyNQDwxotgauUK2eTjOJoDpG0fcvgyBryaFsUA7f9s75Xjc3dJf3ZLKU/GXljvkDIxwzE6gtp7yfH76x7icd05EKgF+lKQawtZkoQMTGMk8h2vBoL+fzXPJlXhaYFUh0Y+MWk3YBieTJlV9Pa4yfh0JXrnsx8wmdqhtXCPBEW8OmHIEuqXHwcVj2u9bmu/+a+bOQ9te+UVOFoqXULr1d6QDS1fW4EK6KTkQYbMwZDrBBIb+NDonP6QDek8nvdcuqJSyKULyT39syyojxcEJX75CnRd4oB/iDEaKft822Cm7nzNeL04iIwrFxILjXrezArRadYZ02pACO+wc+WsWs8iXH5XTcNAOxmmfOaJvCY0LSeyEVDjVnW+ebR0t6gS5cy3meaBE+BLqswYGxzSbrgqkaDT49mfCWVvnPMGCY4lKPw9uYuH4bbwawsjVrLguA78yGabc9uhtHHlgzBgFuyXfb6b0yLrq0zn7P8BCeXEdSwcHE1sbnnNckx75NhEMP7AvGbNUmxTg1VikkivIbPb8Jtl2S4WjfEW7y2/GC8mCSoofaeKF67GatbmSWcvba0Usv/kbNZOVs/hKsSAlKXLSygMoWg9hsFhImmisfFFchFPwjmdoIorZ0dgVOJSsc4oqERX9kS7tA9tEZ7xCdWnqSl5cBFAUpTyICHuyMgpYNgmHu8sck9mqV6tNCfMi9rzfyk25dzNwuQHmT6xeB3lFlML4PgSRHO/7vhqiRCd2vkyIr2CYh1M+Uwq9aSviLNRgq3rkUfdGn1oJCx7qbg==");

    // act

    var decryptedData = service.decrypt(encryptedData, key, nonce);

    // assert

    assertEquals(
        "{\"amount\":\"3.9812\",\"asset\":{\"assetAddress\":\"0x3A9BC420a42D4386D1A84CC560e7324779D86734\",\"assetId\":\"100001\",\"symbol\":\"ETH\"},\"blockchainId\":\"ethereum-ropsten\",\"blockchainProtocolId\":\"ethereum\",\"clientContext\":{\"userId\":\"1000045\",\"apiKeyId\":\"4000762\",\"accountReferenceId\":\"Mr. White\",\"withdrawalReferenceId\":\"Mr. Red\",\"ip\":\"10.0.25.179\",\"timestamp\":\"2020-09-30T12:34:22.425645700Z\"},\"destination\":{\"address\":\"0x1A9BC420a42D4386D1A84CC560e7324779D86734\",\"name\":\"No name\",\"group\":\"1000457\",\"tag\":\"this is a text tag value\",\"tagType\":\"text\"},\"feeLimit\":\"0.657\",\"networkType\":\"test\",\"operationId\":\"D2B5B7E5-15CF-44C8-8C3E-361B421DE671\",\"source\":{\"address\":\"0x4A9BC420a42D4386D1A84CC560e7324779D86734\",\"name\":null,\"group\":\"1000458\"}}",
        new String(decryptedData, Charset.defaultCharset()));
  }

  @Test
  public void decrypt_external1() {
    // arrange

    var key = Base64.getDecoder().decode("uL6FPG4k5UFHstWYuDySJl4Y6NOH2/4WaC/7iDSelCk=");
    var nonce = Base64.getDecoder().decode("NwWJ5vtVL2fRmJP+nnrZaQ==");
    var originValue =
        "{\"amount\":\"3.9812\",\"asset\":{\"assetAddress\":\"0x3A9BC420a42D4386D1A84CC560e7324779D86734\",\"assetId\":\"100001\",\"symbol\":\"ETH\"},\"blockchainId\":\"ethereum-ropsten\",\"blockchainProtocolId\":\"ethereum\",\"clientContext\":{\"userId\":\"1000045\",\"apiKeyId\":\"4000762\",\"accountReferenceId\":\"Mr. White\",\"withdrawalReferenceId\":\"Mr. Red\",\"ip\":\"10.0.25.179\",\"timestamp\":\"2020-09-30T13:41:12.209060300Z\"},\"destination\":{\"address\":\"0x1A9BC420a42D4386D1A84CC560e7324779D86734\",\"name\":\"No name\",\"group\":\"1000457\",\"tag\":\"this is a text tag value\",\"tagType\":\"text\"},\"feeLimit\":\"0.657\",\"networkType\":\"test\",\"operationId\":\"D2B5B7E5-15CF-44C8-8C3E-361B421DE671\",\"source\":{\"address\":\"0x4A9BC420a42D4386D1A84CC560e7324779D86734\",\"name\":null,\"group\":\"1000458\"}}";
    var encryptedOriginValue =
        "29U6B+E/y3QK+M2ZHHd1D3V2v5R3E9O6zuj4o6gToeTC/G/wTLqSSf6jYWSpnNgcvvGZCg+F1bgWxlHuaj9OHRaSzBuhugSDIb5sicKPjPboaXVRxQWh9ZaK14vcv1JtQG2uA/vWUSEMgPCi78KL8albDXNQQq/H25bIjCyHxCaQ1Oxb6XvCkJFop/6C6qn3mHE9m7lfJRJvGgaz+F/YTa6vgqWf7ejfsjaPzd+U17sV+gQJO7NoG5N3cDTT7K98fNMKIgVNSFjkN/gOrjuakkzums/+oflrMVPTkUezBSDASjOYT7LiQv1tJBZzTtNYRUllCXriQAyXXr9SAsmPxpYHdug0imqNjW+00N2u7d1tAPl7KHSXoH40hdCssatXynCv4A8ercarSrqRBbV9crI7Y4nXOuP6BmfMd2i28R2Y0HffSpt1w5sEC95vZjNJHzd10uMCXuvKcJcTvfplA+BC8B8CUCnLfu/GV+sq7Vo671m0wS2ECPrPMfCBniJAedV4G7lnYNYjfyM9sudVIhUWut6rPV4K/PTYDVHTSMLGh9PRZzljuLO83bwoIYOVTi6zQ1kcGBAwVCzsEVtLkxy1IAXo97r3A3c/n0IScwECwXzzSFbX76x9WHbBB5zP/mmq4kZVJyi7es7cTvLAApL9ph8ZdWR/UHaTiiFsPf5pZq1L+6DTVXxCeHFw9qKjaKqZhZVdcuvR1MDq9ag1lbQkNJINVHgW0N3bIARCOyZ0MkIo8VlcbHDgBMbquvzjZslB08FhGdmETeuQ7giWXbedrTtYQgDXUVbX0c8l37C6d/j2MrtH8ln44PFV+CjBhexILtmBkztkseoeayMbU6xm6dJVmdBodS8KyGGFnYiLACGGPnoyl78EWm7N7dxMdRHS4S++qbPsNFPVNCKsqf7T8zSgk9onRtJkwBM2zB4P9HnFNK1yCbRZ4bTHrhLIqXFI8yhRHiml3q6Y7NCIog==";
    var encryptedData = Base64.getDecoder().decode(encryptedOriginValue);

    // act

    var decryptedData = service.decrypt(encryptedData, key, nonce);

    var actualValue = new String(decryptedData, StandardCharsets.UTF_8);

    // assert

    assertEquals(originValue, actualValue);
  }
}
