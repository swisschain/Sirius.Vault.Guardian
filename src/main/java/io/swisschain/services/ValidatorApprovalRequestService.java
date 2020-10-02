package io.swisschain.services;

import io.swisschain.domain.messages.*;

import java.time.Instant;
import java.util.Base64;

public class ValidatorApprovalRequestService {
    public static byte[] key = Base64.getDecoder().decode("uL6FPG4k5UFHstWYuDySJl4Y6NOH2/4WaC/7iDSelCk=");
    public static byte[] nonce =Base64.getDecoder().decode("NwWJ5vtVL2fRmJP+nnrZaQ==");

    /*
    public static ValidatorApprovalRequestService() {
        key = Base64.getDecoder().decode("uL6FPG4k5UFHstWYuDySJl4Y6NOH2/4WaC/7iDSelCk=");
        nonce = Base64.getDecoder().decode("NwWJ5vtVL2fRmJP+nnrZaQ==");


        return new TransferDetail(
                "3.9812",
                new Asset(
                        "0x3A9BC420a42D4386D1A84CC560e7324779D86734",
                        "100001",
                        "ETH"),
                "ethereum-ropsten",
                "ethereum",
                new ClientContext(
                        "1000045",
                        "4000762",
                        "Mr. White",
                        "Mr. Red",
                        "10.0.25.179",
                        Instant.now().toString()
                ),
                new DestinationAddress(
                        "0x1A9BC420a42D4386D1A84CC560e7324779D86734",
                        "No name",
                        "1000457",
                        "this is a text tag value",
                        "text"
                ),
                "0.657",
                "test",
                "D2B5B7E5-15CF-44C8-8C3E-361B421DE671",
                new SourceAddress(
                        "0x4A9BC420a42D4386D1A84CC560e7324779D86734",
                        null,
                        "1000458"
                ));


    }
     */
}
