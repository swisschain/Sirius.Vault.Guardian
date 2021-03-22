package io.swisschain.repositories.validatorRequests;

import io.swisschain.domain.validators.ValidatorRequest;
import io.swisschain.repositories.DbConnectionFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ValidatorRequestRepositoryImp implements ValidatorRequestRepository {

  private final String tableName = "validator_requests";
  private final DbConnectionFactory connectionFactory;

  public ValidatorRequestRepositoryImp(DbConnectionFactory connectionFactory) {
    this.connectionFactory = connectionFactory;
  }

  public List<ValidatorRequest> getByTransferValidationRequestId(long transferValidationRequestId)
      throws Exception {
    var sql =
        "SELECT *\n"
            + String.format("FROM %s.%s\n", connectionFactory.getSchema(), tableName)
            + "WHERE transfer_validation_request_id = ?;";

    try (var connection = this.connectionFactory.create();
        var statement = connection.prepareStatement(sql)) {
      statement.setLong(1, transferValidationRequestId);

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

  public ValidatorRequest get(String validatorId, long transferValidationRequestId)
      throws Exception {
    var sql =
        "SELECT *\n"
            + String.format("FROM %s.%s\n", connectionFactory.getSchema(), tableName)
            + "WHERE validator_id = ? AND transfer_validation_request_id = ?;";

    try (var connection = this.connectionFactory.create();
        var statement = connection.prepareStatement(sql)) {
      statement.setString(1, validatorId);
      statement.setLong(2, transferValidationRequestId);

      var resultSet = statement.executeQuery();

      if (resultSet.next()) {
        var entity = new ValidatorRequestEntity();
        entity.map(resultSet);

        return toDomain(entity);
      }

      return null;
    }
  }

  public void insert(ValidatorRequest validatorRequest) throws Exception {
    var sql =
        String.format("INSERT INTO %s.%s(\n", connectionFactory.getSchema(), tableName)
            + "validator_id, transfer_validation_request_id, encrypted_message, encrypted_key, \"key\", nonce, created_at)\n"
            + "VALUES (?, ?, ?, ?, ?, ?, ?); ";

    try (var connection = this.connectionFactory.create();
        var statement = connection.prepareStatement(sql)) {
      statement.setString(1, validatorRequest.getValidatorId());
      statement.setLong(2, validatorRequest.getTransferValidationRequestId());
      statement.setString(3, validatorRequest.getEncryptedMessage());
      statement.setString(4, validatorRequest.getEncryptedKey());
      statement.setString(5, validatorRequest.getKey());
      statement.setString(6, validatorRequest.getNonce());
      statement.setTimestamp(7, Timestamp.from(validatorRequest.getCreatedAt()));

      statement.execute();
    }
  }

  public ValidatorRequest toDomain(ValidatorRequestEntity entity) {
    try {
      return new ValidatorRequest(
          entity.getValidatorId(),
          entity.getTransferValidationRequestId(),
          entity.getEncryptedMessage(),
          entity.getEncryptedKey(),
          entity.getKey(),
          entity.getNonce(),
          entity.getCreatedAt());
    } catch (Exception exception) {
      throw new IllegalArgumentException("Can not read validator request", exception);
    }
  }
}
