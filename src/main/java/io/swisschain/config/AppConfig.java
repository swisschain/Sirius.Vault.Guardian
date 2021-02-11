package io.swisschain.config;
import io.swisschain.config.clients.ClientsConfig;
import io.swisschain.config.db.DbConfig;
import io.swisschain.config.keys.KeysConfig;
import io.swisschain.config.tasks.TasksConfig;

public class AppConfig {
  public String name;
  public DbConfig db;
  public TasksConfig tasks;
  public KeysConfig keys;
  public ClientsConfig clients;
}
