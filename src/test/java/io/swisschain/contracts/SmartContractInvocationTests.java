package io.swisschain.contracts;

import io.swisschain.contracts.common.*;
import io.swisschain.contracts.smart_contracts.DataMetamodel;
import io.swisschain.contracts.smart_contracts.DataType;
import io.swisschain.contracts.smart_contracts.FunctionArgument;
import io.swisschain.contracts.smart_contracts.invocation.SmartContractInvocation;
import io.swisschain.contracts.smart_contracts.invocation.SmartContractInvocationContext;
import io.swisschain.contracts.smart_contracts.invocation.SmartContractInvoker;
import io.swisschain.contracts.smart_contracts.invocation.SmartContractMethod;
import io.swisschain.domain.primitives.NetworkType;
import io.swisschain.services.JsonSerializer;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SmartContractInvocationTests {
  private static SmartContractInvocation create(Instant date) {
    return new SmartContractInvocation(
        100001,
        new Blockchain("ethereum-ropsten", "ethereum", NetworkType.Test),
        new SmartContractInvoker(
            "0x4A9BC420a42D4386D1A84CC560e7324779D86733",
            new BrokerAccount(300001, "Source broker account")),
        new SmartContractMethod("add", "0x4A9BC420a42D4386D1A84CC560e7324779D86734"),
        "0x4A9BC420a42D4386D1A84CC560e7324779D86735",
        Arrays.asList(
            new FunctionArgument(
                new DataMetamodel(
                    "Composite",
                    DataType.Composite,
                    0,
                    0,
                    false,
                    "composite",
                    Arrays.asList(
                        new DataMetamodel(
                            "Decimal",
                            DataType.Decimal,
                            1,
                            3,
                            false,
                            "decimal",
                            new ArrayList<>(),
                            true),
                        new DataMetamodel(
                            "Bool", DataType.Bool, 1, 3, false, "native", new ArrayList<>(), true)),
                    true),
                Arrays.asList(
                    new FunctionArgument(
                        new DataMetamodel(
                            "String",
                            DataType.String,
                            0,
                            0,
                            false,
                            "string",
                            new ArrayList<>(),
                            true),
                        new ArrayList<>(),
                        "value2"),
                    new FunctionArgument(
                        new DataMetamodel(
                            "Int", DataType.Int, 0, 0, false, "int", new ArrayList<>(), true),
                        new ArrayList<>(),
                        "value3")),
                "value1"),
            new FunctionArgument(
                new DataMetamodel("Int", DataType.Int, 0, 0, false, "int", new ArrayList<>(), true),
                new ArrayList<>(),
                "value4")),
        new Unit(
            new Asset(100001, "ETH", "0x3A9BC420a42D4386D1A84CC560e7324779D86734"),
            BigDecimal.valueOf(3.9812)),
        new Unit(
            new Asset(100001, "ETH", "0x3A9BC420a42D4386D1A84CC560e7324779D86734"),
            BigDecimal.valueOf(0.657)),
        new SmartContractInvocationContext(
            "This a original client document in JSON format",
            "1.0.0",
            "This a document signature",
            "Smart contract name",
            "Code hash",
            "Brokerage",
            "1000458",
            new RequestContext(
                "D2B5B7E5-15CF-44C8-8C3E-361B421DE671",
                "B92EDE96-2D93-4744-B7DE-5358278C7C23",
                "10.0.0.1",
                date)));
  }

  @Test
  public void smart_contract_invocation_are_equals() {

    // arrange
    var date = Instant.now();
    var smartContractInvocation1 = create(date);
    var smartContractInvocation2 = create(date);

    // ack

    // assert

    assertEquals(smartContractInvocation1, smartContractInvocation2);
  }

  @Test
  public void smart_contract_invocation_not_equals() {

    // arrange
    var date = Instant.now();

    var smartContractInvocation1 = create(date);
    var smartContractInvocation2 = create(date);

    // ack

    smartContractInvocation1.getFee().setAmount(BigDecimal.valueOf(9));

    // assert

    assertNotEquals(smartContractInvocation1, smartContractInvocation2);
  }

  @Test
  public void smart_contract_invocations_are_equals_after_serialization() throws Exception {

    // arrange

    var js = new JsonSerializer();

    var smartContractInvocation = create(Instant.now());

    // ack

    var json = js.serialize(smartContractInvocation);

    var deserializedSmartContractInvocation = js.deserialize(json, SmartContractInvocation.class);

    // assert

    assertEquals(smartContractInvocation, deserializedSmartContractInvocation);
  }
}
