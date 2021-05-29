package io.swisschain.repositories.validator_requests;

import io.swisschain.domain.validators.ValidatorRequest;
import io.swisschain.domain.validators.ValidatorRequestType;
import io.swisschain.repositories.DbConnectionFactory;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ValidatorRequestRepositoryImp implements ValidatorRequestRepository {

  private final String tableName = "validator_requests";
  private final DbConnectionFactory connectionFactory;

  public ValidatorRequestRepositoryImp(DbConnectionFactory connectionFactory) {
    this.connectionFactory = connectionFactory;
  }

  public ValidatorRequest getById(String validatorRequestId) throws SQLException {
    var sql =
        "SELECT *\n"
            + String.format("FROM %s.%s\n", connectionFactory.getSchema(), tableName)
            + "WHERE id = ?;";

    try (var connection = this.connectionFactory.create();
        var statement = connection.prepareStatement(sql)) {
      statement.setString(1, validatorRequestId);

      var resultSet = statement.executeQuery();

      if (resultSet.next()) {
        var entity = new ValidatorRequestEntity();
        entity.map(resultSet);

        return toDomain(entity);
      }

      return null;
    }
  }

  public List<ValidatorRequest> getByValidationRequestId(
      long validationRequestId, ValidatorRequestType requestType) throws Exception {
    var sql =
        "SELECT *\n"
            + String.format("FROM %s.%s\n", connectionFactory.getSchema(), tableName)
            + "WHERE validation_request_id = ? AND \"type\" = ?;";

    try (var connection = this.connectionFactory.create();
        var statement = connection.prepareStatement(sql)) {
      statement.setLong(1, validationRequestId);
      statement.setString(2, requestType.name());

      var resultSet = statement.executeQuery();

      var validatorRequests = new ArrayList<ValidatorRequest>();

      while (resultSet.next()) {
        var entity = new ValidatorRequestEntity();
        entity.map(resultSet);

        validatorRequests.add(toDomain(entity));
      }

      return validatorRequests;
    }
  }

  public ValidatorRequest getByValidatorId(String validatorId, long validationRequestId)
      throws SQLException {
    var sql =
        "SELECT *\n"
            + String.format("FROM %s.%s\n", connectionFactory.getSchema(), tableName)
            + "WHERE validation_request_id = ? and validator_id = ?;";

    try (var connection = this.connectionFactory.create();
        var statement = connection.prepareStatement(sql)) {
      statement.setLong(1, validationRequestId);
      statement.setString(2, validatorId);

      var resultSet = statement.executeQuery();

      if (resultSet.next()) {
        var entity = new ValidatorRequestEntity();
        entity.map(resultSet);

        return toDomain(entity);
      }

      return null;
    }
  }

  public boolean insert(ValidatorRequest validatorRequest) throws Exception {
    var sql =
        String.format("INSERT INTO %s.%s(\n", connectionFactory.getSchema(), tableName)
            + "id, validator_id, tenant_id, message, \"key\", nonce, status, \"type\", validation_request_id, document, signature, resolution, resolution_message, device_info, ip, created_at, updated_at)\n"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "
            + "ON CONFLICT ON CONSTRAINT ux_validator_request "
            + "DO NOTHING;";

    try (var connection = this.connectionFactory.create();
        var statement = connection.prepareStatement(sql)) {
      statement.setString(1, validatorRequest.getId());
      statement.setString(2, validatorRequest.getValidatorId());
      statement.setString(3, validatorRequest.getTenantId());
      statement.setString(4, validatorRequest.getMessage());
      statement.setString(5, validatorRequest.getKey());
      statement.setString(6, validatorRequest.getNonce());
      statement.setString(7, validatorRequest.getStatus().name());
      statement.setString(8, validatorRequest.getType().name());
      statement.setLong(9, validatorRequest.getValidationRequestId());
      statement.setString(10, validatorRequest.getDocument());
      statement.setString(11, validatorRequest.getSignature());
      statement.setString(
          12,
          validatorRequest.getResolution() != null
              ? validatorRequest.getResolution().name()
              : null);
      statement.setString(13, validatorRequest.getResolutionMessage());
      statement.setString(14, validatorRequest.getDeviceInfo());
      statement.setString(15, validatorRequest.getIp());
      statement.setTimestamp(16, Timestamp.from(validatorRequest.getCreatedAt()));
      statement.setTimestamp(17, Timestamp.from(validatorRequest.getUpdatedAt()));

      return statement.executeUpdate() == 1;
    }
  }

  public void update(ValidatorRequest validatorRequest) throws Exception {
    var sql =
        String.format("UPDATE %s.%s\n", connectionFactory.getSchema(), tableName)
            + "SET validator_id = ?, tenant_id = ?, message = ?, \"key\" = ?, nonce = ?, status = ?, \"type\" = ?, validation_request_id = ?, document = ?, signature = ?, resolution = ?, resolution_message = ?, device_info = ?, ip = ?, created_at = ?, updated_at = ?\n"
            + "WHERE id = ?;";

    try (var connection = this.connectionFactory.create();
        var statement = connection.prepareStatement(sql)) {
      statement.setString(1, validatorRequest.getValidatorId());
      statement.setString(2, validatorRequest.getTenantId());
      statement.setString(3, validatorRequest.getMessage());
      statement.setString(4, validatorRequest.getKey());
      statement.setString(5, validatorRequest.getNonce());
      statement.setString(6, validatorRequest.getStatus().name());
      statement.setString(7, validatorRequest.getType().name());
      statement.setLong(8, validatorRequest.getValidationRequestId());
      statement.setString(9, validatorRequest.getDocument());
      statement.setString(10, validatorRequest.getSignature());
      statement.setString(
          11,
          validatorRequest.getResolution() != null
              ? validatorRequest.getResolution().name()
              : null);
      statement.setString(12, validatorRequest.getResolutionMessage());
      statement.setString(13, validatorRequest.getDeviceInfo());
      statement.setString(14, validatorRequest.getIp());
      statement.setTimestamp(15, Timestamp.from(validatorRequest.getCreatedAt()));
      statement.setTimestamp(16, Timestamp.from(validatorRequest.getUpdatedAt()));
      statement.setString(17, validatorRequest.getId());

      statement.execute();
    }
  }

  public ValidatorRequest toDomain(ValidatorRequestEntity entity) {
    try {
      return new ValidatorRequest(
          entity.getId(),
          entity.getValidatorId(),
          entity.getTenantId(),
          entity.getMessage(),
          entity.getKey(),
          entity.getNonce(),
          entity.getStatus(),
          entity.getType(),
          entity.getValidationRequestId(),
          entity.getDocument(),
          entity.getSignature(),
          entity.getResolution(),
          entity.getResolutionMessage(),
          entity.getDeviceInfo(),
          entity.getIp(),
          entity.getCreatedAt(),
          entity.getUpdatedAt());
    } catch (Exception exception) {
      throw new IllegalArgumentException("Can not read validator request", exception);
    }
  }
}
