package io.swisschain.odm;

import io.swisschain.contracts.common.*;
import io.swisschain.contracts.documents.Resolution;
import io.swisschain.contracts.smart_contracts.deployment.SmartContractDeployer;
import io.swisschain.contracts.smart_contracts.deployment.SmartContractDeployment;
import io.swisschain.contracts.smart_contracts.deployment.SmartContractDeploymentContext;
import io.swisschain.domain.policies.RuleExecutionAction;
import io.swisschain.domain.primitives.NetworkType;
import io.swisschain.domain.validation_requests.ValidationRequestStatus;
import io.swisschain.domain.validation_requests.smart_contracts.SmartContractDeploymentValidationRequest;
import io.swisschain.domain.validators.Validator;
import io.swisschain.domain.validators.ValidatorRequest;
import io.swisschain.domain.validators.ValidatorRequestStatus;
import io.swisschain.domain.validators.ValidatorRequestType;
import io.swisschain.policies.smart_contract_deployments.SmartContractDeploymentOdmRuleExecutor;
import io.swisschain.services.JsonSerializer;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class SmartContractDeploymentOdmRuleExecutorTests {

  private SmartContractDeploymentOdmRuleExecutor executor;

  // @Before
  public void initialize() {
    executor =
        new SmartContractDeploymentOdmRuleExecutor(
            new SmartContractDeploymentOdmClientImp(
                "http://localhost:9060/DecisionService/rest/SmartContractDeploymentConfiguration/1.0/SmartContractDeployment",
                new JsonSerializer()));
  }

  // @Test
  public void approve_trusted_smart_contract_deployment() throws Exception {
    // arrange

    var validationRequest =
        new SmartContractDeploymentValidationRequest(
            1,
            "t1",
            0,
            new SmartContractDeployment(
                10000,
                new Blockchain("ethereum-classic", "ethereum-classic-1", NetworkType.Private),
                new SmartContractDeployer("a1", new BrokerAccount(1, "b1")),
                new ArrayList<>(),
                new Unit(new Asset(1, "ETC", "a1"), BigDecimal.valueOf(200)),
                new Unit(),
                new SmartContractDeploymentContext(
                    "document",
                    "documentVersion",
                    "signature",
                    "sc1",
                    "hc1",
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
  public void validate_untrusted_smart_contract_deployment() throws Exception {
    // arrange

    var validationRequest =
        new SmartContractDeploymentValidationRequest(
            1,
            "t1",
            0,
            new SmartContractDeployment(
                10000,
                new Blockchain("ethereum-classic", "ethereum-classic-1", NetworkType.Private),
                new SmartContractDeployer("a1", new BrokerAccount(1, "b1")),
                new ArrayList<>(),
                new Unit(new Asset(1, "ETC", "a1"), BigDecimal.valueOf(200)),
                new Unit(),
                new SmartContractDeploymentContext(
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

    assertEquals(result.getAction(), RuleExecutionAction.Validate);
  }

  // @Test
  public void approve_untrusted_smart_contract_deployment() throws Exception {
    // arrange

    var validationRequest =
        new SmartContractDeploymentValidationRequest(
            1,
            "t1",
            0,
            new SmartContractDeployment(
                10000,
                new Blockchain("ethereum-classic", "ethereum-classic-1", NetworkType.Private),
                new SmartContractDeployer("a1", new BrokerAccount(1, "b1")),
                new ArrayList<>(),
                new Unit(new Asset(1, "ETC", "a1"), BigDecimal.valueOf(200)),
                new Unit(),
                new SmartContractDeploymentContext(
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

    var validators = new ArrayList<Validator>();

    // act

    var result = executor.execute(validationRequest, validatorRequests, validators);

    // assert

    assertEquals(result.getAction(), RuleExecutionAction.Approve);
  }

  // @Test
  public void decline_unknown_smart_contract_deployment() throws Exception {
    // arrange

    var validationRequest =
        new SmartContractDeploymentValidationRequest(
            1,
            "t1",
            0,
            new SmartContractDeployment(
                10000,
                new Blockchain("ethereum-classic", "ethereum-classic-1", NetworkType.Private),
                new SmartContractDeployer("a1", new BrokerAccount(1, "b1")),
                new ArrayList<>(),
                new Unit(new Asset(1, "ETC", "a1"), BigDecimal.valueOf(200)),
                new Unit(),
                new SmartContractDeploymentContext(
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
