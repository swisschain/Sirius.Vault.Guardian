package io.swisschain.contracts;

import io.swisschain.contracts.common.*;
import io.swisschain.contracts.smart_contracts.DataMetamodel;
import io.swisschain.contracts.smart_contracts.DataType;
import io.swisschain.contracts.smart_contracts.FunctionArgument;
import io.swisschain.contracts.smart_contracts.deployment.SmartContractDeployer;
import io.swisschain.contracts.smart_contracts.deployment.SmartContractDeployment;
import io.swisschain.contracts.smart_contracts.deployment.SmartContractDeploymentContext;
import io.swisschain.domain.primitives.NetworkType;
import io.swisschain.services.JsonSerializer;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SmartContractDeploymentTests {
  private static SmartContractDeployment create(Instant date) {
    return new SmartContractDeployment(
        100001,
        new Blockchain("ethereum-ropsten", "ethereum", NetworkType.Test),
        new SmartContractDeployer(
            "0x4A9BC420a42D4386D1A84CC560e7324779D86734",
            new BrokerAccount(300001, "Source broker account")),
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
        new SmartContractDeploymentContext(
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
  public void smart_contract_deployments_are_equals() {

    // arrange
    var date = Instant.now();
    var smartContractDeployment1 = create(date);
    var smartContractDeployment2 = create(date);

    // ack

    // assert

    assertEquals(smartContractDeployment1, smartContractDeployment2);
  }

  @Test
  public void smart_contract_deployments_not_equals() {

    // arrange
    var date = Instant.now();

    var smartContractDeployment1 = create(date);
    var smartContractDeployment2 = create(date);

    // ack

    smartContractDeployment1.getFee().setAmount(BigDecimal.valueOf(9));

    // assert

    assertNotEquals(smartContractDeployment1, smartContractDeployment2);
  }

  @Test
  public void smart_contract_deployments_are_equals_after_serialization() throws Exception {

    // arrange

    var js = new JsonSerializer();

    var smartContractDeployment = create(Instant.now());

    // ack

    var json = js.serialize(smartContractDeployment);

    var deserializedSmartContractDeployment = js.deserialize(json, SmartContractDeployment.class);

    // assert

    assertEquals(smartContractDeployment, deserializedSmartContractDeployment);
  }

  @Test
  public void
      smart_contract_deployments_are_equals_after_serialization_with_different_decimal_scale()
          throws Exception {

    // arrange

    var js = new JsonSerializer();

    var smartContractDeployment = create(Instant.now());
    smartContractDeployment.getPayment().setAmount(new BigDecimal("1.5"));

    // ack

    var json = js.serialize(smartContractDeployment);

    var deserializedSmartContractDeployment = js.deserialize(json, SmartContractDeployment.class);
    deserializedSmartContractDeployment.getPayment().setAmount(new BigDecimal("1.50"));

    // assert

    assertEquals(smartContractDeployment, deserializedSmartContractDeployment);
  }
}
