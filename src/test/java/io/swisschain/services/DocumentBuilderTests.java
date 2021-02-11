package io.swisschain.services;

import io.swisschain.contracts.*;
import io.swisschain.crypto.asymmetric.AsymmetricEncryptionService;
import io.swisschain.domain.transfers.TransferValidationRequest;
import io.swisschain.domain.transfers.TransferValidationRequestStatus;
import io.swisschain.domain.validators.ValidatorRequest;
import io.swisschain.domain.validators.ValidatorResponse;
import io.swisschain.primitives.NetworkType;
import io.swisschain.primitives.TagType;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class DocumentBuilderTests {
  private static final String privateKey =
      "-----BEGIN RSA PRIVATE KEY-----\n"
          + "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKdNztDYLMWdUc7j\n"
          + "rXzayrSWy0xZR9SebB+d63hy1jxMiFAiboZqz/DaE799HXcXl1gkMs/fMbUX5Yun\n"
          + "ZHeNjzOb+mNDXEn5/gdB/IQhoQpY6wy0rCN6zeBizwwhTqh5DRe/Z1E2gfUKhCI7\n"
          + "KuoLsrDm6vr7snSVZjm2jYXvdyYPAgEDAoGAG+JNIs6yIO+NontHlM8hyMPMjLmL\n"
          + "+MUSBUT8lBMjtLdsDVsSa7x3/Xmt9T+E6S6ZOVtdzU/dni6mQfE7aUJCiFS6RIMW\n"
          + "HiXUuuGJctleSkEiZ5pjCzX+6bvi80xPfoqfDW4f0mE/YcnXTfkAcXGUawWa3NjG\n"
          + "F0NeqzgcKDj4jBcCQQDinGH3JYLWDgZEnTTh6Z/NkKKxDinyHcJd824eRbgA8jMi\n"
          + "zsS0pK6CcXAOPkb2gFmbTt3mxLzkxcbdpFbx8t7FAkEAvQBmObISQPOftCaeJ4Gt\n"
          + "BIv8ZUu2RLajnYnqOSv5OGCUB9meOmD7zQbeVun6a7fVKfrwAEksHGhviEA9p7D+\n"
          + "wwJBAJcS6/oZAeQJWYMTeJabv95gbHYJcUwT1ulM9BQueqtMIhc0gyMYdFb2SrQp\n"
          + "hKRVkRI0k+8t00MuhJPC5KFMlIMCQH4ARCZ2ttX3v81vFBpWc1hdUu4yeYMkbROx\n"
          + "RtDH+3rrDVqRFCbrUoivPuSb/EfP43FR9VWGHWhFn7Aq08UgqdcCQQCUT8ESGsz+\n"
          + "m9zgfn/6wkKVzvzz/mReaGowJVMAkH5aC0y9nilJ6tpNZxWdo26+Z0rCVdA1n+jn\n"
          + "OV1zdhTgICIx\n"
          + "-----END RSA PRIVATE KEY-----";

  private static final String publicKey =
      "-----BEGIN PUBLIC KEY-----\n"
          + "MIGdMA0GCSqGSIb3DQEBAQUAA4GLADCBhwKBgQCnTc7Q2CzFnVHO46182sq0lstM\n"
          + "WUfUnmwfnet4ctY8TIhQIm6Gas/w2hO/fR13F5dYJDLP3zG1F+WLp2R3jY8zm/pj\n"
          + "Q1xJ+f4HQfyEIaEKWOsMtKwjes3gYs8MIU6oeQ0Xv2dRNoH1CoQiOyrqC7Kw5ur6\n"
          + "+7J0lWY5to2F73cmDwIBAw==\n"
          + "-----END PUBLIC KEY-----";

  private AsymmetricEncryptionService asymmetricEncryptionService;
  private DocumentBuilder documentBuilder;

  private static TransferValidationRequest getTransferValidationRequest() {
    return new TransferValidationRequest(
        100002,
        "tenant-identifier",
        new TransferDetails(
            100001,
            new Blockchain("ethereum-ropsten", "ethereum", NetworkType.Test),
            new Asset(100001, "ETH", "0x3A9BC420a42D4386D1A84CC560e7324779D86734"),
            new SourceAddress("0x4A9BC420a42D4386D1A84CC560e7324779D86734", null, "1000458"),
            new DestinationAddress(
                "0x1A9BC420a42D4386D1A84CC560e7324779D86734",
                "No name",
                "1000457",
                "this is a text tag value",
                TagType.Text),
            BigDecimal.valueOf(3.9812),
            BigDecimal.valueOf(0.657),
            new TransferContext(
                "This a original client document in JSON format",
                "This a document signature",
                "Mr. White",
                "Mr. Red",
                "Brokerage",
                "transfer",
                "1000458",
                "1000457",
                new RequestContext(
                    "D2B5B7E5-15CF-44C8-8C3E-361B421DE671",
                    "B92EDE96-2D93-4744-B7DE-5358278C7C23",
                    "10.0.0.1",
                    Instant.now()))),
        TransferValidationRequestStatus.Approved,
        null,
        null,
        null,
        Instant.now(),
        Instant.now());
  }

  private static List<ValidatorRequest> getValidatorRequests(
      TransferValidationRequest transferValidationRequest) {
    var validatorRequests = new ArrayList<ValidatorRequest>();

    validatorRequests.add(
        new ValidatorRequest(
            "validator1",
            transferValidationRequest.getId(),
            "encryptedMessage-validator1",
            "encryptedKey-validator1",
            "key-validator1",
            "nonce-validator1",
            Instant.now()));

    validatorRequests.add(
        new ValidatorRequest(
            "validator2",
            transferValidationRequest.getId(),
            "encryptedMessage-validator2",
            "encryptedKey-validator2",
            "key-validator2",
            "nonce-validator2",
            Instant.now()));

    return validatorRequests;
  }

  private static List<ValidatorResponse> getValidatorResponses(
      TransferValidationRequest transferValidationRequest) {
    var validatorResponses = new ArrayList<ValidatorResponse>();

    validatorResponses.add(
        new ValidatorResponse(
            "validator1",
            transferValidationRequest.getId(),
            "document-validator1",
            "signature-validator1",
            Resolution.Approved,
            "resolutionMessage-validator1",
            "deviceInfo-validator1",
            "ip-validator1",
            Instant.now()));

    validatorResponses.add(
        new ValidatorResponse(
            "validator2",
            transferValidationRequest.getId(),
            "document-validator2",
            "signature-validator2",
            Resolution.Approved,
            "resolutionMessage-validator2",
            "deviceInfo-validator2",
            "ip-validator2",
            Instant.now()));

    return validatorResponses;
  }

  @Before
  public void initialize() {
    asymmetricEncryptionService = new AsymmetricEncryptionService();
    documentBuilder =
        new DocumentBuilder(asymmetricEncryptionService, privateKey, new JsonSerializer());
  }

  @Test
  public void document_signature_is_valid() throws Exception {
    // arrange

    var transferValidationRequest = getTransferValidationRequest();
    var validatorRequests = getValidatorRequests(transferValidationRequest);
    var validatorResponses = getValidatorResponses(transferValidationRequest);

    // ack

    var signedDocument =
        documentBuilder.build(transferValidationRequest, validatorResponses, validatorRequests);

    var signatureIsValid = asymmetricEncryptionService.verifySignature(
        signedDocument.getDocument().getBytes(StandardCharsets.UTF_8),
        Base64.getDecoder().decode(signedDocument.getSignature()),
        publicKey);

    // assert

    assertTrue(signatureIsValid);
  }
}
