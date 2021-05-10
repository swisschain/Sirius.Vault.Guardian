package io.swisschain.odm;

import io.swisschain.contracts.common.Asset;
import io.swisschain.contracts.common.Blockchain;
import io.swisschain.contracts.common.BrokerAccount;
import io.swisschain.contracts.common.Unit;
import io.swisschain.contracts.documents.Resolution;
import io.swisschain.contracts.transfers.Transfer;
import io.swisschain.contracts.transfers.TransferContext;
import io.swisschain.contracts.transfers.TransferDestination;
import io.swisschain.contracts.transfers.TransferSource;
import io.swisschain.domain.policies.RuleExecutionAction;
import io.swisschain.domain.primitives.NetworkType;
import io.swisschain.domain.primitives.TagType;
import io.swisschain.domain.validation_requests.ValidationRequestStatus;
import io.swisschain.domain.validation_requests.transfers.TransferValidationRequest;
import io.swisschain.domain.validators.Validator;
import io.swisschain.domain.validators.ValidatorRequest;
import io.swisschain.domain.validators.ValidatorRequestStatus;
import io.swisschain.domain.validators.ValidatorRequestType;
import io.swisschain.policies.transfers.TransferOdmRuleExecutor;
import io.swisschain.services.JsonSerializer;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class TransferOdmRuleExecutorTests {

  private TransferOdmRuleExecutor executor;

  //@Before
  public void initialize() {
    executor =
        new TransferOdmRuleExecutor(
            new TransferOdmClientImp(
                "http://localhost:9060/DecisionService/rest/TransfersConfiguration/1.0/Transfers",
                new JsonSerializer()));
  }

 //@Test
  public void approve_small_transfer() throws Exception {
    // arrange

    var validationRequest =
        new TransferValidationRequest(
            1,
            "t1",
            new Transfer(
                1,
                new Blockchain("ethereum-classic", "ethereum-classic-1", NetworkType.Private),
                new TransferSource("ts1", new BrokerAccount(1, "b1"), null),
                new TransferDestination(
                    "ts2", "tag", TagType.Number, new BrokerAccount(2, "b2"), null),
                new Unit(new Asset(1, "ETC", "a1"), BigDecimal.valueOf(0.5)),
                new Unit(),
                new TransferContext()),
            ValidationRequestStatus.Processing,
            null,
            null,
            null,
            Instant.now(),
            Instant.now());

    var validatorRequests = new ArrayList<ValidatorRequest>();

    var validators = new ArrayList<Validator>();

    // act

    var result = executor.execute(validationRequest, validatorRequests, validators);

    // assert

    assertEquals(result.getAction(), RuleExecutionAction.Approve);
  }

  //@Test
  public void validate_medium_transfer() throws Exception {
    // arrange

    var validationRequest =
        new TransferValidationRequest(
            1,
            "t1",
            new Transfer(
                1,
                new Blockchain("ethereum-classic", "ethereum-classic-1", NetworkType.Private),
                new TransferSource("ts1", new BrokerAccount(1, "b1"), null),
                new TransferDestination(
                    "ts2", "tag", TagType.Number, new BrokerAccount(2, "b2"), null),
                new Unit(new Asset(1, "ETC", "a1"), BigDecimal.valueOf(2)),
                new Unit(),
                new TransferContext()),
            ValidationRequestStatus.Processing,
            null,
            null,
            null,
            Instant.now(),
            Instant.now());

    var validatorRequests = new ArrayList<ValidatorRequest>();

    var validators =
        new ArrayList<>(
            Arrays.asList(
                new Validator("first-validator-id", "v1"),
                new Validator("second-validator-id", "v2"),
                new Validator("third-validator-id", "v3")));

    // act

    var result = executor.execute(validationRequest, validatorRequests, validators);

    // assert

    assertEquals(result.getAction(), RuleExecutionAction.Validate);
  }

  //@Test
  public void approve_medium_transfer() throws Exception {
    // arrange

    var validationRequest =
        new TransferValidationRequest(
            1,
            "t1",
            new Transfer(
                1,
                new Blockchain("ethereum-classic", "ethereum-classic-1", NetworkType.Private),
                new TransferSource("ts1", new BrokerAccount(1, "b1"), null),
                new TransferDestination(
                    "ts2", "tag", TagType.Number, new BrokerAccount(2, "b2"), null),
                new Unit(new Asset(1, "ETC", "a1"), BigDecimal.valueOf(2)),
                new Unit(),
                new TransferContext()),
            ValidationRequestStatus.Processing,
            null,
            null,
            null,
            Instant.now(),
            Instant.now());

    var validatorRequests =
        new ArrayList<>(
            Collections.singletonList(
                new ValidatorRequest(
                    "1",
                    "first-validator-id",
                    "t1",
                    null,
                    null,
                    null,
                    ValidatorRequestStatus.Completed,
                    ValidatorRequestType.SmartContractDeployment,
                    1,
                    null,
                    null,
                    Resolution.Approved,
                    null,
                    null,
                    null,
                    Instant.now(),
                    Instant.now())));

    var validators =
        new ArrayList<>(
            Arrays.asList(
                new Validator("first-validator-id", "v1"),
                new Validator("second-validator-id", "v2"),
                new Validator("third-validator-id", "v3")));

    // act

    var result = executor.execute(validationRequest, validatorRequests, validators);

    // assert

    assertEquals(result.getAction(), RuleExecutionAction.Approve);
  }

  //@Test
  public void reject_medium_transfer() throws Exception {
    // arrange

    var validationRequest =
        new TransferValidationRequest(
            1,
            "t1",
            new Transfer(
                1,
                new Blockchain("ethereum-classic", "ethereum-classic-1", NetworkType.Private),
                new TransferSource("ts1", new BrokerAccount(1, "b1"), null),
                new TransferDestination(
                    "ts2", "tag", TagType.Number, new BrokerAccount(2, "b2"), null),
                new Unit(new Asset(1, "ETC", "a1"), BigDecimal.valueOf(2)),
                new Unit(),
                new TransferContext()),
            ValidationRequestStatus.Processing,
            null,
            null,
            null,
            Instant.now(),
            Instant.now());

    var validatorRequests =
        new ArrayList<>(
            Collections.singletonList(
                new ValidatorRequest(
                    "1",
                    "first-validator-id",
                    "t1",
                    null,
                    null,
                    null,
                    ValidatorRequestStatus.Completed,
                    ValidatorRequestType.SmartContractDeployment,
                    1,
                    null,
                    null,
                    Resolution.Rejected,
                    null,
                    null,
                    null,
                    Instant.now(),
                    Instant.now())));

    var validators =
        new ArrayList<>(
            Arrays.asList(
                new Validator("first-validator-id", "v1"),
                new Validator("second-validator-id", "v2"),
                new Validator("third-validator-id", "v3")));

    // act

    var result = executor.execute(validationRequest, validatorRequests, validators);

    // assert

    assertEquals(result.getAction(), RuleExecutionAction.Reject);
  }

  //@Test
  public void reject_medium_transfer_no_validators() throws Exception {
    // arrange

    var validationRequest =
        new TransferValidationRequest(
            1,
            "t1",
            new Transfer(
                1,
                new Blockchain("ethereum-classic", "ethereum-classic-1", NetworkType.Private),
                new TransferSource("ts1", new BrokerAccount(1, "b1"), null),
                new TransferDestination(
                    "ts2", "tag", TagType.Number, new BrokerAccount(2, "b2"), null),
                new Unit(new Asset(1, "ETC", "a1"), BigDecimal.valueOf(2)),
                new Unit(),
                new TransferContext()),
            ValidationRequestStatus.Processing,
            null,
            null,
            null,
            Instant.now(),
            Instant.now());

    var validatorRequests = new ArrayList<ValidatorRequest>();

    var validators = new ArrayList<Validator>();

    // act

    var result = executor.execute(validationRequest, validatorRequests, validators);

    // assert

    assertEquals(result.getAction(), RuleExecutionAction.Reject);
  }
}
