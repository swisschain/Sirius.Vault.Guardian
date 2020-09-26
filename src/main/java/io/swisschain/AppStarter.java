package io.swisschain;

import io.swisschain.config.Config;
import io.swisschain.config.ConfigLoader;
import io.swisschain.isAlive.IsAliveService;
import io.swisschain.repositories.DbConnectionFactory;
import io.swisschain.repositories.transfers.TransferValidationRequestRepository;
import io.swisschain.sirius.ChannelFactory;
import io.swisschain.sirius.guardianApi.GuardianApiClient;
import io.swisschain.sirius.vaultApi.VaultApiClient;
import io.swisschain.tasks.TransferValidationTask;
import io.swisschain.tasks.ValidatorApprovalTask;
import io.swisschain.utils.AppVersion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AppStarter {
  private static final Logger logger = LogManager.getLogger();

  public static void main(String[] args) throws InterruptedException {
    Config config = ConfigLoader.loadConfig();

    if (config == null) {
      logger.error("Can not load config");
      return;
    }

    // API clients

    var vaultApiClient =
        new VaultApiClient(
            ChannelFactory.create(
                config.vaultApi.host,
                config.vaultApi.port,
                config.vaultApi.ssl,
                config.vaultApi.apiKey));

    var guardianApiClient =
        new GuardianApiClient(
            ChannelFactory.create(
                config.guardianApi.host,
                config.guardianApi.port,
                config.guardianApi.ssl,
                config.guardianApi.apiKey));

    // Repositories

    var connectionFactory =
        new DbConnectionFactory(
            config.db.url, config.db.user, config.db.password, config.db.schema);

    var transferValidationRequestRepository =
        new TransferValidationRequestRepository(connectionFactory);

    // Services

    // Tasks

    var hostProcessId = getHostProcessId();

    var service = Executors.newScheduledThreadPool(2);

    service.scheduleWithFixedDelay(
        new TransferValidationTask(
            vaultApiClient, transferValidationRequestRepository, hostProcessId),
        0,
        1,
        TimeUnit.SECONDS);
    service.scheduleWithFixedDelay(
        new ValidatorApprovalTask(
            vaultApiClient, guardianApiClient, transferValidationRequestRepository, hostProcessId),
        0,
        1,
        TimeUnit.SECONDS);

    initShutdownHook();

    new IsAliveService(5000).start();

    while (true) {
      Thread.sleep(10000);
    }
  }

  static String getHostProcessId() {
    return String.format("%s-%d", AppVersion.HOSTNAME, ProcessHandle.current().pid());
  }

  static void initShutdownHook() {
    Runtime.getRuntime()
        .addShutdownHook(new Thread(() -> logger.info("Stopping " + AppVersion.NAME)));
  }
}
