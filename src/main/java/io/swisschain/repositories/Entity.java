package io.swisschain.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Entity {
  public abstract void map(ResultSet resultSet) throws SQLException;
}
