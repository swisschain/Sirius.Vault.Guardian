package io.swisschain.repositories.validatorResponses;

import io.swisschain.domain.validators.ValidatorResponse;
import io.swisschain.repositories.DbConnectionFactory;
import io.swisschain.domain.exceptions.AlreadyExistsException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ValidatorResponseRepositoryImp implements ValidatorResponseRepository {
  private final String tableName = "validator_responses";
  private final DbConnectionFactory connectionFactory;

  public ValidatorResponseRepositoryImp(DbConnectionFactory connectionFactory) {
    this.connectionFactory = connectionFactory;
  }

  public List<ValidatorResponse> getByTransferValidationRequestId(long transferValidationRequestId)
      throws Exception {
    var sql =
        "SELECT *\n"
            + String.format("FROM %s.%s\n", connectionFactory.getSchema(), tableName)
            + "WHERE transfer_validation_request_id = ?;";

    try (var connection = this.connectionFactory.create();
        var statement = connection.prepareStatement(sql)) {
      statement.setLong(1, transferValidationRequestId);

      var resultSet = statement.executeQuery();

      var validatorRequests = new ArrayList<ValidatorResponse>();

      while (resultSet.next()) {
        var entity = new ValidatorResponseEntity();
        entity.map(resultSet);

        validatorRequests.add(toDomain(entity));
      }

      return validatorRequests;
    }
  }

  public void insert(ValidatorResponse validatorResponse) throws Exception, AlreadyExistsException {
    var sql =
        String.format("INSERT INTO %s.%s(\n", connectionFactory.getSchema(), tableName)
            + "validator_id, transfer_validation_request_id, document, signature, resolution, resolution_message, device_info, ip, created_at)\n"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?); ";

    try (var connection = this.connectionFactory.create();
        var statement = connection.prepareStatement(sql)) {
      statement.setString(1, validatorResponse.getValidatorId());
      statement.setLong(2, validatorResponse.getTransferValidationRequestId());
      statement.setString(3, validatorResponse.getDocument());
      statement.setString(4, validatorResponse.getSignature());
      statement.setString(5, validatorResponse.getResolution().name());
      statement.setString(6, validatorResponse.getResolutionMessage());
      statement.setString(7, validatorResponse.getDeviceInfo());
      statement.setString(8, validatorResponse.getIp());
      statement.setTimestamp(9, Timestamp.from(validatorResponse.getCreatedAt()));

      statement.execute();
    }
  }

  public ValidatorResponse toDomain(ValidatorResponseEntity entity) {
    try {
      return new ValidatorResponse(
          entity.getValidatorId(),
          entity.getTransferValidationRequestId(),
          entity.getDocument(),
          entity.getSignature(),
          entity.getResolution(),
          entity.getResolutionMessage(),
          entity.getDeviceInfo(),
          entity.getIp(),
          entity.getCreatedAt());
    } catch (Exception exception) {
      throw new IllegalArgumentException("Can not read validator request", exception);
    }
  }
}
