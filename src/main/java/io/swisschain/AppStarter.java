package io.swisschain;

import io.swisschain.config.AppConfig;
import io.swisschain.config.loaders.ConfigLoader;
import io.swisschain.crypto.asymmetric.AsymmetricEncryptionService;
import io.swisschain.crypto.symmetric.SymmetricEncryptionService;
import io.swisschain.domain.document.CustomerKey;
import io.swisschain.domain.document.TenantKey;
import io.swisschain.isAlive.IsAliveService;
import io.swisschain.odm.OdmClientImp;
import io.swisschain.odm.OdmClientRetryDecorator;
import io.swisschain.policies.smart_contract_deployments.*;
import io.swisschain.policies.transfers.TransferDocumentBuilder;
import io.swisschain.policies.DocumentValidator;
import io.swisschain.policies.ValidationPolicyFactory;
import io.swisschain.policies.transfers.*;
import io.swisschain.policies.validator_requests.ValidatorRequestProcessor;
import io.swisschain.policies.validator_responses.ValidatorResponseProcessor;
import io.swisschain.policies.validator_responses.validator_document_readers.ValidatorDocumentReaderFactory;
import io.swisschain.policies.validator_responses.validator_document_validators.ValidatorDocumentValidatorFactory;
import io.swisschain.repositories.*;
import io.swisschain.repositories.smart_contract_deployment_validation_requests.SmartContractDeploymentValidationRequestRepositoryImp;
import io.swisschain.repositories.smart_contract_deployment_validation_requests.SmartContractDeploymentValidationRequestRepositoryRetryDecorator;
import io.swisschain.repositories.transfer_validation_requests.TransferValidationRequestRepositoryImp;
import io.swisschain.repositories.transfer_validation_requests.TransferValidationRequestRepositoryRetryDecorator;
import io.swisschain.repositories.validator_requests.ValidatorRequestRepositoryImp;
import io.swisschain.repositories.validator_requests.ValidatorRequestRepositoryRetryDecorator;
import io.swisschain.services.*;
import io.swisschain.sirius.ChannelFactory;
import io.swisschain.sirius.guardianApi.GuardianApiClient;
import io.swisschain.sirius.vaultApi.VaultApiClient;
import io.swisschain.tasks.*;
import io.swisschain.common.AppVersion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AppStarter {
  private static final Logger logger = LogManager.getLogger();

  public static void main(String[] args) throws InterruptedException {
    AppConfig appConfig = ConfigLoader.loadConfig();

    if (appConfig == null) {
      logger.error("Can not load config");
      return;
    }

    if (!DbMigration.migrateDatabase(
        appConfig.db.url, appConfig.db.user, appConfig.db.password, appConfig.db.schema)) {
      return;
    }

    var hostProcessId = String.format("%s-%d", AppVersion.HOSTNAME, ProcessHandle.current().pid());

    var jsonSerializer = new JsonSerializer();

    // API clients

    var vaultApiClient =
        new VaultApiClient(
            ChannelFactory.create(
                appConfig.clients.vaultApi.host,
                appConfig.clients.vaultApi.port,
                appConfig.clients.vaultApi.ssl,
                appConfig.clients.vaultApi.apiKey));

    var guardianApiClient =
        new GuardianApiClient(
            ChannelFactory.create(
                appConfig.clients.guardianApi.host,
                appConfig.clients.guardianApi.port,
                appConfig.clients.guardianApi.ssl,
                appConfig.clients.guardianApi.apiKey));

    // Repositories

    var connectionFactory =
        new DbConnectionFactory(
            appConfig.db.url, appConfig.db.user, appConfig.db.password, appConfig.db.schema);

    var smartContractDeploymentValidationRequestRepository =
        new SmartContractDeploymentValidationRequestRepositoryRetryDecorator(
            new SmartContractDeploymentValidationRequestRepositoryImp(
                connectionFactory, jsonSerializer));

    var transferValidationRequestRepository =
        new TransferValidationRequestRepositoryRetryDecorator(
            new TransferValidationRequestRepositoryImp(connectionFactory, jsonSerializer));

    var validatorRequestRepository =
        new ValidatorRequestRepositoryRetryDecorator(
            new ValidatorRequestRepositoryImp(connectionFactory));

    // Services

    var asymmetricEncryptionService = new AsymmetricEncryptionService();
    var symmetricEncryptionService = new SymmetricEncryptionService();

    var transferApiService =
        new TransferApiServiceRetryDecorator(
            new TransferApiServiceImp(vaultApiClient, hostProcessId));

    var smartContractDeploymentApiService =
        new SmartContractDeploymentApiServiceRetryDecorator(
            new SmartContractDeploymentApiServiceImp(vaultApiClient, hostProcessId));

    var validatorApiService =
        new ValidatorApiServiceRetryDecorator(new ValidatorApiServiceImp(guardianApiClient));

    var validatorRequestApiService =
        new ValidatorRequestApiServiceRetryDecorator(
            new ValidatorRequestApiServiceImp(guardianApiClient));

    var validatorResponseApiService =
        new ValidatorResponseApiServiceRetryDecorator(
            new ValidatorResponseApiServiceImp(guardianApiClient));

    var transferDocumentBuilder =
        new TransferDocumentBuilder(
            asymmetricEncryptionService, appConfig.keys.guardian.privateKey, jsonSerializer);

    var smartContractDeploymentDocumentBuilder =
        new SmartContractDeploymentDocumentBuilder(
            asymmetricEncryptionService, appConfig.keys.guardian.privateKey, jsonSerializer);

    var customerKey = new CustomerKey(appConfig.keys.customer.publicKey);
    var tenantKeys = new ArrayList<TenantKey>();

    for (var tenantKeyConfig : appConfig.keys.tenants) {
      tenantKeys.add(new TenantKey(tenantKeyConfig.tenantId, tenantKeyConfig.publicKey));
    }

    // Policies

    var documentValidator =
        new DocumentValidator(asymmetricEncryptionService, customerKey, tenantKeys);

    TransferRuleExecutor transferRuleExecutor;
    SmartContractDeploymentRuleExecutor smartContractDeploymentRuleExecutor;

    if (appConfig.clients.odmApi != null && appConfig.clients.odmApi.enable) {
      var odmClient =
          new OdmClientRetryDecorator(
              new OdmClientImp(
                  appConfig.clients.odmApi.baseUrl,
                  appConfig.clients.odmApi.policySelectorPath,
                  jsonSerializer));
      transferRuleExecutor = new TransferOdmRuleExecutor(odmClient);
      smartContractDeploymentRuleExecutor = new SmartContractDeploymentOdmRuleExecutor(odmClient);
      logger.info("RuleExecutor: ODM");
    } else {
      transferRuleExecutor = new TransferSimpleRuleExecutor();
      smartContractDeploymentRuleExecutor = new SmartContractDeploymentSimpleRuleExecutor();
      logger.info("RuleExecutor: Simple");
    }

    var validatorRequestProcessor =
        new ValidatorRequestProcessor(
            validatorRequestApiService,
            validatorRequestRepository,
            symmetricEncryptionService,
            asymmetricEncryptionService,
            jsonSerializer);

    var transferValidationPolicy =
        new TransferValidationPolicy(
            transferRuleExecutor,
            validatorApiService,
            transferApiService,
            transferValidationRequestRepository,
            validatorRequestRepository,
            validatorRequestProcessor,
            transferDocumentBuilder,
            jsonSerializer);

    var transferValidationRequestProcessor =
        new TransferValidationRequestProcessor(
            transferValidationPolicy,
            transferApiService,
            transferValidationRequestRepository,
            transferDocumentBuilder,
            documentValidator);

    var smartContractDeploymentValidationPolicy =
        new SmartContractDeploymentValidationPolicy(
            smartContractDeploymentRuleExecutor,
            validatorApiService,
            smartContractDeploymentApiService,
            smartContractDeploymentValidationRequestRepository,
            validatorRequestRepository,
            validatorRequestProcessor,
            smartContractDeploymentDocumentBuilder,
            jsonSerializer);

    var smartContractDeploymentValidationRequestProcessor =
        new SmartContractDeploymentValidationRequestProcessor(
            smartContractDeploymentValidationPolicy,
            smartContractDeploymentApiService,
            smartContractDeploymentValidationRequestRepository,
            smartContractDeploymentDocumentBuilder,
            documentValidator);

    var validationPolicyFactory = new ValidationPolicyFactory(transferValidationPolicy);
    var validatorDocumentReaderFactory = new ValidatorDocumentReaderFactory(jsonSerializer);
    var validatorDocumentValidatorFactory = new ValidatorDocumentValidatorFactory(jsonSerializer);

    var validatorResponseProcessor =
        new ValidatorResponseProcessor(
            validatorApiService,
            validatorRequestApiService,
            validatorRequestRepository,
            symmetricEncryptionService,
            validatorDocumentReaderFactory,
            validatorDocumentValidatorFactory,
            validationPolicyFactory,
            documentValidator,
            jsonSerializer);

    // Tasks

    var service = Executors.newScheduledThreadPool(3);

    service.scheduleWithFixedDelay(
        new TransferValidationRequestTask(transferApiService, transferValidationRequestProcessor),
        0,
        appConfig.tasks != null && appConfig.tasks.validationRequestsPeriodInSeconds > 0
            ? appConfig.tasks.validationRequestsPeriodInSeconds
            : 1,
        TimeUnit.SECONDS);
    service.scheduleWithFixedDelay(
        new SmartContractDeploymentValidationRequestTask(
            smartContractDeploymentApiService, smartContractDeploymentValidationRequestProcessor),
        0,
        appConfig.tasks != null && appConfig.tasks.validationRequestsPeriodInSeconds > 0
            ? appConfig.tasks.validationRequestsPeriodInSeconds
            : 1,
        TimeUnit.SECONDS);
    service.scheduleWithFixedDelay(
        new ValidatorResponseTask(validatorResponseApiService, validatorResponseProcessor),
        0,
        appConfig.tasks != null && appConfig.tasks.validatorResponsesPeriodInSeconds > 0
            ? appConfig.tasks.validatorResponsesPeriodInSeconds
            : 1,
        TimeUnit.SECONDS);

    initShutdownHook();

    new IsAliveService(5000).start();

    while (true) {
      Thread.sleep(10000);
    }
  }

  static void initShutdownHook() {
    Runtime.getRuntime()
        .addShutdownHook(new Thread(() -> logger.info("Stopping " + AppVersion.NAME)));
  }
}
