package io.swisschain.services;

import io.swisschain.contracts.documents.transfers.TransferValidatorDocument;
import io.swisschain.crypto.asymmetric.AsymmetricEncryptionService;
import io.swisschain.crypto.symmetric.SymmetricEncryptionService;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.Assert.assertTrue;

public class ValidatorResolutionTests {
  private SymmetricEncryptionService symmetricEncryptionService;
  private AsymmetricEncryptionService asymmetricEncryptionService;
  private JsonSerializer jsonSerializer;

  //@Before
  public void initialize() {
    asymmetricEncryptionService = new AsymmetricEncryptionService();
    symmetricEncryptionService = new SymmetricEncryptionService();
    jsonSerializer = new JsonSerializer();
  }

  //@Test
  public void decrypt_and_validate_signature() throws Exception {
    // arrange

    var nonce = Base64.getDecoder().decode("vniDjkrg54+nbxGBWZlfIA==");
    var key = Base64.getDecoder().decode("YEqzPY/eKj3vxbPvKCwfXyrOFJLiac306ukcmIP5LDM=");

    var encryptedValidatorResolution =
        Base64.getDecoder()
            .decode(
                "ACUzcprn0jz9EGaWcoVTcLb9e3yoQPGZ5L8XtyKqXnawWxNufXTvLZQsUHvPF3uSI1t+CkMvVOfcSCZdAsCbKd/MTJOPwiHl8kLB+B7xcN5vsOHbKwzkDBGig04yZ9bqac1vwQoSInmpWTUAYc4p3Yk7z6qonXF8AtXRP0yGkXOH+Kx5mIFTnN5Nd/uRDnIiAcNhZ6NvP8OTgRNQledspjpqHZGyI7pfBfpSkzMLgiQk2KrrONHsV8SaCZprTTFnnwdJVuw85Vm6sDFfcoAZWFRJQ6sAucCmVjTqCjP8wLU+MH9G9U8ijjjt7D9Rh8zeXaOLUaPeXecjY92Z4O7i76F5qroFpQHTL1DotU1Gl6qc30wAdtaJQYUbbBDbEN3fKihuQ8vl3wG/UBHyrmPVOTv7wajwYRTLu+GoD12j/Vfu3AfVQm/FUP4X0bwhIemJ8RMWE5b4UWjbRoxxdBhAM+yiXUlK2/a+cpoqSHaRbPIA/q2wAeF/Jj1SPLW3hzGv9f68fZ5/1EGIOf3NSx6udtf9pjIWEQVf3apH6k76rAd3GFuoHR4mbsg47/CgyHOiIzcvlJVifc7KcNr931EsCV37EJBDNL7cPDoQqPlNkIbdV3vEdPwrPaXyg70z3DCT0FNsqf+AlTpNF9tdU7MNHumhtfDMaDtJmbb+FsY6TuJwY/mgxo1NvlVfJWLolvU69Ec07wDlZ9H3JWdmK6iRpJhK87aLb95K67Wxjr2y1x30wyvG+Tvk+3QPimY4wSv0xLC9hrbCQz94De0xOHIQJ0vy3zbHcEkrIxL681Ki1OfjyjdLAtP59GKqHcIcBvroJhg9sI+aKuFrpjcD7WApqRF/6NnisQXOatNLiHEv3Ztc9moHVd5umNPDtyDHIaU5iZq9EJNAP/o5jlgWJQssL2lOp90UDx8qXjOrCE6pJ7uVd4wtA0dbScn+2psNPz4rY/LWqwsNKR+Wpa6Ut5RmMK8P2MKfuXEFXyoZ/0gsMnPcDyZNhEWIDkwtWDsFRSMdORxG0aWdM0HzxiwLcReuFduPMYTUVMQh0dgziY+VsXuEUtpS/SSLqpuqFLow14uOcc2DQ8neCiD+K6k6vnKT6TiUboY83Jgbwuyq4Bg/lg2Wzm1k38wBQLfer//MpqQU8O7oReTI23Yl4t8hUv6NAI3ZYOmHwvmiYUWFCDSfKF+VJrMkPdN8+7zuxaygVFd6HpRqKdTn6RAVJ5qkhnxoaY77LTMo9nvH7N0eGlWXzFPuAkhQMC26vlyPo9RKb88XHRxYR41ff8oWOaWKTVMk1NtzhZEBtBgmyZ6wxLkttNPaPH3DWTAEI/ZK2uEDF+H1Kae4I1LbiVAC7en9TJoMpwEklhrHozFCujVSr1S0OguRHobfwrABmImNDcdyAPatQK3wLKyaOsbNH73VxrJ0G0ORS0704Xx5u4o7WP7gBi6UbbiL8Vp/6tIsq2Bk/kyhH01BuGBSCFOdDyDh4SzvmCKS9ZQgW8Uxtx4uFSz3BpIPoPGGlvcSbeop980C9PJh/9D8afIwt9SQWk6P4DuKKuxssrTZl3XOB9SlbcO5Q4c=");

    var signature =
        Base64.getDecoder()
            .decode(
                "GoKOmrfeMz/x8xwtHaBEJRpDCSgg5CVgTmB7BTFToNf1JIb8iL6ee9pSRm7YstmVFvyYbJhwWsZI8Q+wg+9p/XPbO2jxNPvFr5O2uR/yZGiVNz8VGLlp0EU5qpNzSfvpIBb2tREbXZe2xEI/PKHOAx0Cz+3aTeDaqCCtrJ5cxkA=");

    var publicKey =
        "-----BEGIN PUBLIC KEY-----\nMIGdMA0GCSqGSIb3DQEBAQUAA4GLADCBhwKBgQCKNOstGPIO0Vf58c2di4+QJU6ZkuwZ9I+gQyAZM6Sdze+CLrZS1h67HF7150U9+vG3uCs+C+mYCkC4afH2pDkDcW7V37DQI8MWYU2HtoeY7j9toYbGVMQdKcGI1Z7w/mAn+uU3wIRERSNhGZTU5qPF6rFsL0Ueg82Bx7j23Lu6awIBAw==\n-----END PUBLIC KEY-----";

    // act

    var decryptedJson =
        symmetricEncryptionService.decrypt(encryptedValidatorResolution, key, nonce);

    jsonSerializer.deserialize(
        new String(decryptedJson, StandardCharsets.UTF_8), TransferValidatorDocument.class);

    var isSignatureValid =
        asymmetricEncryptionService.verifySignature(decryptedJson, signature, publicKey);

    // assert

    assertTrue(isSignatureValid);
  }
}
