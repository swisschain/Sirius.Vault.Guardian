package io.swisschain.odm;

import io.swisschain.contracts.common.*;
import io.swisschain.contracts.documents.Resolution;
import io.swisschain.contracts.smart_contracts.FunctionArgument;
import io.swisschain.contracts.smart_contracts.invocation.SmartContractInvocation;
import io.swisschain.contracts.smart_contracts.invocation.SmartContractInvocationContext;
import io.swisschain.contracts.smart_contracts.invocation.SmartContractInvoker;
import io.swisschain.contracts.smart_contracts.invocation.SmartContractMethod;
import io.swisschain.domain.policies.RuleExecutionAction;
import io.swisschain.domain.primitives.NetworkType;
import io.swisschain.domain.validation_requests.ValidationRequestStatus;
import io.swisschain.domain.validation_requests.smart_contracts.SmartContractInvocationValidationRequest;
import io.swisschain.domain.validators.Validator;
import io.swisschain.domain.validators.ValidatorRequest;
import io.swisschain.domain.validators.ValidatorRequestStatus;
import io.swisschain.domain.validators.ValidatorRequestType;
import io.swisschain.policies.smart_contract_invocations.SmartContractInvocationOdmRuleExecutor;
import io.swisschain.services.JsonSerializer;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class SmartContractInvocationOdmRuleExecutorTests {

  private SmartContractInvocationOdmRuleExecutor executor;

  // @Before
  public void initialize() {
    executor =
        new SmartContractInvocationOdmRuleExecutor(
            new SmartContractInvocationOdmClientImp(
                "http://localhost:9060/DecisionService/rest/SmartContractInvocationConfiguration/1.0/SmartContractInvocation",
                new JsonSerializer()));
  }

  // @Test
  public void approve_trusted_smart_contract_invocation() throws Exception {
    // arrange

    var validationRequest =
        new SmartContractInvocationValidationRequest(
            1,
            "t1",
            new SmartContractInvocation(
                10000,
                new Blockchain("ethereum-classic", "ethereum-classic-1", NetworkType.Private),
                new SmartContractInvoker("a1", new BrokerAccount(1, "b1")),
                new SmartContractMethod("m1", "ma1"),
                "a1",
                new ArrayList<FunctionArgument>(
                    Arrays.asList(new FunctionArgument(), new FunctionArgument())),
                new Unit(new Asset(1, "ETC", "a1"), BigDecimal.valueOf(200)),
                new Unit(),
                new SmartContractInvocationContext(
                    "document",
                    "documentVersion",
                    "signature",
                    "sc1",
                    "hc1",
                    "c",
                    "rf",
                    new RequestContext("uid", "apikeyid", "ip", Instant.now()))),
            ValidationRequestStatus.Processing,
            null,
            null,
            null,
            Instant.now(),
            Instant.now());

    var a = new JsonSerializer().serialize(validationRequest.getSmartContractInvocation());
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

    assertEquals(result.getAction(), RuleExecutionAction.Approve);
  }

  // @Test
  public void validate_untrusted_smart_contract_invocation() throws Exception {
    // arrange

    var validationRequest =
        new SmartContractInvocationValidationRequest(
            1,
            "t1",
            new SmartContractInvocation(
                10000,
                new Blockchain("ethereum-classic", "ethereum-classic-1", NetworkType.Private),
                new SmartContractInvoker("a1", new BrokerAccount(1, "b1")),
                new SmartContractMethod("m3", "ma3"),
                "a1",
                new ArrayList<>(),
                new Unit(new Asset(1, "ETC", "a1"), BigDecimal.valueOf(200)),
                new Unit(),
                new SmartContractInvocationContext(
                    "document",
                    "documentVersion",
                    "signature",
                    "sc1",
                    "hc3",
                    "c",
                    "rf",
                    new RequestContext())),
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
                    ValidatorRequestType.SmartContractInvocation,
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

    assertEquals(result.getAction(), RuleExecutionAction.Validate);
  }

  // @Test
  public void approve_untrusted_smart_contract_invocation() throws Exception {
    // arrange

    var validationRequest =
        new SmartContractInvocationValidationRequest(
            1,
            "t1",
            new SmartContractInvocation(
                10000,
                new Blockchain("ethereum-classic", "ethereum-classic-1", NetworkType.Private),
                new SmartContractInvoker("a1", new BrokerAccount(1, "b1")),
                new SmartContractMethod("m3", "ma3"),
                "a1",
                new ArrayList<>(),
                new Unit(new Asset(1, "ETC", "a1"), BigDecimal.valueOf(200)),
                new Unit(),
                new SmartContractInvocationContext(
                    "document",
                    "documentVersion",
                    "signature",
                    "sc1",
                    "hc3",
                    "c",
                    "rf",
                    new RequestContext())),
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
                    "third-validator-id",
                    "t1",
                    null,
                    null,
                    null,
                    ValidatorRequestStatus.Completed,
                    ValidatorRequestType.SmartContractInvocation,
                    1,
                    null,
                    null,
                    Resolution.Approved,
                    null,
                    null,
                    null,
                    Instant.now(),
                    Instant.now())));

    var validators = new ArrayList<Validator>();

    // act

    var result = executor.execute(validationRequest, validatorRequests, validators);

    // assert

    assertEquals(result.getAction(), RuleExecutionAction.Approve);
  }

  // @Test
  public void decline_unknown_smart_contract_invocation() throws Exception {
    // arrange

    var validationRequest =
        new SmartContractInvocationValidationRequest(
            1,
            "t1",
            new SmartContractInvocation(
                10000,
                new Blockchain("ethereum-classic", "ethereum-classic-1", NetworkType.Private),
                new SmartContractInvoker("a1", new BrokerAccount(1, "b1")),
                new SmartContractMethod("m4", "ma4"),
                "a1",
                new ArrayList<>(),
                new Unit(new Asset(1, "ETC", "a1"), BigDecimal.valueOf(200)),
                new Unit(),
                new SmartContractInvocationContext(
                    "document",
                    "documentVersion",
                    "signature",
                    "sc1",
                    "Unknown",
                    "c",
                    "rf",
                    new RequestContext())),
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
