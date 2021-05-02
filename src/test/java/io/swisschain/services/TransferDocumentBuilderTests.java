package io.swisschain.services;

import io.swisschain.contracts.common.*;
import io.swisschain.contracts.transfers.Transfer;
import io.swisschain.contracts.transfers.TransferContext;
import io.swisschain.contracts.transfers.TransferDestination;
import io.swisschain.contracts.transfers.TransferSource;
import io.swisschain.crypto.asymmetric.AsymmetricEncryptionService;
import io.swisschain.domain.primitives.NetworkType;
import io.swisschain.domain.primitives.TagType;
import io.swisschain.domain.validation_requests.ValidationRequestStatus;
import io.swisschain.domain.validation_requests.transfers.TransferValidationRequest;
import io.swisschain.domain.validators.ValidatorRequest;
import io.swisschain.domain.validators.ValidatorRequestStatus;
import io.swisschain.domain.validators.ValidatorRequestType;
import io.swisschain.policies.transfers.TransferDocumentBuilder;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TransferDocumentBuilderTests {
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
  private TransferDocumentBuilder transferDocumentBuilder;

  @Before
  public void initialize() {
    asymmetricEncryptionService = new AsymmetricEncryptionService();
    transferDocumentBuilder =
        new TransferDocumentBuilder(asymmetricEncryptionService, privateKey, new JsonSerializer());
  }

  @Test
  public void document_signature_is_valid() throws Exception {
    // arrange

    var transferValidationRequest = createTransferValidationRequest();
    var validatorRequests = createValidatorRequests(transferValidationRequest);

    // ack

    var signedDocument =
        transferDocumentBuilder.build(transferValidationRequest, validatorRequests);

    var signatureIsValid =
        asymmetricEncryptionService.verifySignature(
            signedDocument.getDocument().getBytes(StandardCharsets.UTF_8),
            Base64.getDecoder().decode(signedDocument.getSignature()),
            publicKey);

    // assert

    assertTrue(signatureIsValid);
  }

  private static TransferValidationRequest createTransferValidationRequest() {
    return new TransferValidationRequest(
        100002,
        "tenant-identifier",
        new Transfer(
            100001,
            new Blockchain("ethereum-ropsten", "ethereum", NetworkType.Test),
            new TransferSource(
                "0x4A9BC420a42D4386D1A84CC560e7324779D86734",
                new BrokerAccount(300001, "Source broker account"),
                new Account(400001, "Account reference Id", new User(50001, "User native id"))),
            new TransferDestination(
                "0x1A9BC420a42D4386D1A84CC560e7324779D86734",
                "this is a text tag value",
                TagType.Text,
                new BrokerAccount(300002, "Destination broker account"),
                new Account(400002, "Account reference Id", new User(50002, "User native id"))),
            new Unit(
                new Asset(100001, "ETH", "0x3A9BC420a42D4386D1A84CC560e7324779D86734"),
                BigDecimal.valueOf(3.9812)),
            new Unit(
                new Asset(100001, "ETH", "0x3A9BC420a42D4386D1A84CC560e7324779D86734"),
                BigDecimal.valueOf(0.657)),
            new TransferContext(
                "This a original client document in JSON format",
                "1.0.0",
                "This a document signature",
                "Withdrawal reference id",
                "Brokerage",
                "1000458",
                new RequestContext(
                    "D2B5B7E5-15CF-44C8-8C3E-361B421DE671",
                    "B92EDE96-2D93-4744-B7DE-5358278C7C23",
                    "10.0.0.1",
                    Instant.now()))),
        ValidationRequestStatus.Approved,
        null,
        null,
        null,
        Instant.now(),
        Instant.now());
  }

  private static List<ValidatorRequest> createValidatorRequests(
      TransferValidationRequest transferValidationRequest) {
    var validatorRequests = new ArrayList<ValidatorRequest>();

    validatorRequests.add(
        new ValidatorRequest(
            "id1",
            "validator1",
            "tenantId",
            "encryptedMessage-validator1",
            "encryptedKey-validator1",
            "nonce-validator1",
            ValidatorRequestStatus.Completed,
            ValidatorRequestType.Transfer,
            transferValidationRequest.getId(),
            "document-validator1",
            "signature-validator1",
            Resolution.Approved,
            "resolution-message-validator1",
            "device-info-validator1",
            "ip-validator1",
            Instant.now(),
            Instant.now()));

    validatorRequests.add(
        new ValidatorRequest(
            "id1",
            "validator2",
            "tenantId",
            "encryptedMessage-validator2",
            "encryptedKey-validator2",
            "nonce-validator2",
            ValidatorRequestStatus.Completed,
            ValidatorRequestType.Transfer,
            transferValidationRequest.getId(),
            "document-validator2",
            "signature-validator2",
            Resolution.Approved,
            "resolution-message-validator2",
            "device-info-validator2",
            "ip-validator2",
            Instant.now(),
            Instant.now()));

    return validatorRequests;
  }
}
