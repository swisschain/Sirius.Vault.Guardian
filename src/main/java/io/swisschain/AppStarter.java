package io.swisschain;

import io.swisschain.config.AppConfig;
import io.swisschain.config.loaders.ConfigLoader;
import io.swisschain.crypto.asymmetric.AsymmetricEncryptionService;
import io.swisschain.crypto.symmetric.SymmetricEncryptionService;
import io.swisschain.domain.document.CustomerKey;
import io.swisschain.domain.document.TenantKey;
import io.swisschain.isAlive.IsAliveService;
import io.swisschain.odm.OdmClient;
import io.swisschain.repositories.*;
import io.swisschain.services.*;
import io.swisschain.sirius.ChannelFactory;
import io.swisschain.sirius.guardianApi.GuardianApiClient;
import io.swisschain.sirius.vaultApi.VaultApiClient;
import io.swisschain.tasks.*;
import io.swisschain.utils.AppVersion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
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

    var transferValidationRequestRepository =
        new TransferValidationRequestRepository(connectionFactory, jsonSerializer);

    var validatorRequestRepository = new ValidatorRequestRepository(connectionFactory);

    var validatorResponseRepository = new ValidatorResponseRepository(connectionFactory);

    // Services

    var asymmetricEncryptionService = new AsymmetricEncryptionService();
    var symmetricEncryptionService = new SymmetricEncryptionService();

    var transferValidationRequestApiService =
        new TransferValidationRequestApiService(vaultApiClient, hostProcessId);

    var validatorsApiService = new ValidatorsApiService(guardianApiClient);

    var documentBuilder =
        new DocumentBuilder(
            asymmetricEncryptionService, appConfig.keys.guardian.privateKey, jsonSerializer);

    var customerKey = new CustomerKey(appConfig.keys.customer.publicKey);
    var tenantKeys = new ArrayList<TenantKey>();

    for (var tenantKeyConfig : appConfig.keys.tenants) {
      tenantKeys.add(new TenantKey(tenantKeyConfig.tenantId, tenantKeyConfig.publicKey));
    }

    var documentValidator =
        new DocumentValidator(asymmetricEncryptionService, customerKey, tenantKeys);

    RuleExecutor ruleExecutor;

    if (appConfig.clients.odmApi != null && appConfig.clients.odmApi.enable) {
      var odmClient =
          new OdmClient(
              appConfig.clients.odmApi.baseUrl,
              appConfig.clients.odmApi.policySelectorPath,
              jsonSerializer);
      ruleExecutor = new OdmRuleExecutor(odmClient);
      logger.info("RuleExecutor: ODM");
    } else {
      ruleExecutor = new SimpleRuleExecutor();
      logger.info("RuleExecutor: Simple");
    }

    var policyService =
        new PolicyService(
            ruleExecutor,
            validatorsApiService,
            transferValidationRequestApiService,
            transferValidationRequestRepository,
            validatorRequestRepository,
            validatorResponseRepository,
            symmetricEncryptionService,
            asymmetricEncryptionService,
            documentBuilder,
            documentValidator,
            jsonSerializer);

    // Tasks

    var service = Executors.newScheduledThreadPool(2);

    service.scheduleWithFixedDelay(
        new HandleValidationRequestsTask(transferValidationRequestApiService, policyService),
        0,
        appConfig.tasks != null && appConfig.tasks.validationRequestsPeriodInSeconds > 0
            ? appConfig.tasks.validationRequestsPeriodInSeconds
            : 1,
        TimeUnit.SECONDS);
    service.scheduleWithFixedDelay(
        new HandleValidatorResponsesTask(validatorsApiService, policyService),
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
