package io.swisschain.contracts;

import io.swisschain.primitives.NetworkType;
import io.swisschain.primitives.TagType;
import io.swisschain.services.JsonSerializer;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TransferDetailsTests {
  private static TransferDetails getTransferDetails(Instant date) {
    return new TransferDetails(
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
                date)));
  }

  @Test
  public void transfer_details_are_equals() {

    // arrange
    var date = Instant.now();
    var transferDetails1 = getTransferDetails(date);
    var transferDetails2 = getTransferDetails(date);

    // ack

    // assert

    assertEquals(transferDetails1, transferDetails2);
  }

  @Test
  public void transfer_details_not_equals() {

    // arrange
    var date = Instant.now();

    var transferDetails1 = getTransferDetails(date);
    var transferDetails2 = getTransferDetails(date);

    // ack

    transferDetails1.setAmount(BigDecimal.valueOf(9));

    // assert

    assertNotEquals(transferDetails1, transferDetails2);
  }

  @Test
  public void transfer_details_are_equals_after_serialization() throws Exception {

    // arrange

    var js = new JsonSerializer();

    var transferDetails = getTransferDetails(Instant.now());

    // ack

    var json = js.serialize(transferDetails);

    var deserializedTransferDetails = js.deserialize(json, TransferDetails.class);

    // assert

    assertEquals(transferDetails, deserializedTransferDetails);
  }
}
