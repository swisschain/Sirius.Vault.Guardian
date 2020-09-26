package io.swisschain.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionFactory {
  private final String url;
  private final String user;
  private final String password;
  private final String schema;

  public DbConnectionFactory(String url, String user, String password, String schema) {
    this.url = url;
    this.user = user;
    this.password = password;
    this.schema = schema;
  }

  public Connection create() throws SQLException {
    return DriverManager.getConnection(url, user, password);
  }

  public String getSchema() {
    return schema;
  }
}
