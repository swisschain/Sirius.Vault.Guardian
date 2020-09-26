package io.swisschain.repositories;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;

public class DbMigration {
  private static final Logger logger = LogManager.getLogger();

  public static boolean migrateDatabase(String url, String user, String password, String schema) {
    try {
      Flyway.configure()
          .dataSource(url, user, password)
          .defaultSchema(schema)
          .schemas(schema)
          .load()
          .migrate();
    } catch (Exception exception) {
      logger.error("Unable to migrate database.", exception);
      return false;
    }

    return true;
  }
}
