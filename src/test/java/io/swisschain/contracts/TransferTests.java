package io.swisschain.contracts;

import io.swisschain.contracts.common.*;
import io.swisschain.contracts.transfers.Transfer;
import io.swisschain.contracts.transfers.TransferContext;
import io.swisschain.contracts.transfers.TransferDestination;
import io.swisschain.contracts.transfers.TransferSource;
import io.swisschain.domain.primitives.NetworkType;
import io.swisschain.domain.primitives.TagType;
import io.swisschain.services.JsonSerializer;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TransferTests {

  @Test
  public void transfers_are_equals() {

    // arrange
    var date = Instant.now();
    var transfer1 = create(date);
    var transfer2 = create(date);

    // ack

    // assert

    assertEquals(transfer1, transfer2);
  }

  @Test
  public void transfers_not_equals() {

    // arrange
    var date = Instant.now();

    var transfer1 = create(date);
    var transfer2 = create(date);

    // ack

    transfer1.getValue().setAmount(BigDecimal.valueOf(9));

    // assert

    assertNotEquals(transfer1, transfer2);
  }

  @Test
  public void transfers_are_equals_after_serialization() throws Exception {

    // arrange

    var js = new JsonSerializer();

    var transfer = create(Instant.now());

    // ack

    var json = js.serialize(transfer);

    var deserializedTransfer = js.deserialize(json, Transfer.class);

    // assert

    assertEquals(transfer, deserializedTransfer);
  }

  private static Transfer create(Instant date) {
    return new Transfer(
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
                date)));
  }
}
