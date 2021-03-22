package io.swisschain.repositories.transferValidationRequests;

import io.swisschain.contracts.TransferDetails;
import io.swisschain.domain.transfers.TransferValidationRequest;
import io.swisschain.repositories.DbConnectionFactory;
import io.swisschain.services.JsonSerializer;

import java.sql.SQLException;
import java.sql.Timestamp;

public class TransferValidationRequestRepositoryImp implements TransferValidationRequestRepository {
  private final String tableName = "transfer_validation_requests";
  private final DbConnectionFactory connectionFactory;
  private final JsonSerializer jsonSerializer;

  public TransferValidationRequestRepositoryImp(
      DbConnectionFactory connectionFactory, JsonSerializer jsonSerializer) {
    this.connectionFactory = connectionFactory;
    this.jsonSerializer = jsonSerializer;
  }

  public TransferValidationRequest getById(long transferValidationRequestId) throws SQLException {
    var sql =
        "SELECT *\n"
            + String.format("FROM %s.%s\n", connectionFactory.getSchema(), tableName)
            + "WHERE id = ?;";

    try (var connection = this.connectionFactory.create();
        var statement = connection.prepareStatement(sql)) {
      statement.setLong(1, transferValidationRequestId);

      var resultSet = statement.executeQuery();

      if (resultSet.next()) {
        var entity = new TransferValidationRequestEntity();
        entity.map(resultSet);

        return toDomain(entity);
      }

      return null;
    }
  }

  public void insert(TransferValidationRequest transferValidationRequest) throws Exception {
    var sql =
        String.format("INSERT INTO %s.%s(\n", connectionFactory.getSchema(), tableName)
            + "id, tenant_id, transfer_details, status, document, signature, reject_reason_message, created_at, updated_at)\n"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?); ";

    try (var connection = this.connectionFactory.create();
        var statement = connection.prepareStatement(sql)) {
      statement.setLong(1, transferValidationRequest.getId());
      statement.setString(2, transferValidationRequest.getTenantId());
      statement.setString(
          3, jsonSerializer.serialize(transferValidationRequest.getTransferDetails()));
      statement.setString(4, transferValidationRequest.getStatus().name());
      statement.setString(5, transferValidationRequest.getDocument());
      statement.setString(6, transferValidationRequest.getSignature());
      statement.setString(7, transferValidationRequest.getRejectReasonMessage());
      statement.setTimestamp(8, Timestamp.from(transferValidationRequest.getCreatedAt()));
      statement.setTimestamp(9, Timestamp.from(transferValidationRequest.getUpdatedAt()));

      statement.execute();
    }
  }

  public void update(TransferValidationRequest transferValidationRequest) throws Exception {
    var sql =
        String.format("UPDATE %s.%s\n", connectionFactory.getSchema(), tableName)
            + "SET tenant_id = ?, transfer_details = ?, status = ?, document = ?, signature = ?, reject_reason_message = ?, created_at = ?, updated_at = ?\n"
            + "WHERE id = ?;";

    try (var connection = this.connectionFactory.create();
        var statement = connection.prepareStatement(sql)) {
      statement.setString(1, transferValidationRequest.getTenantId());
      statement.setString(
          2, jsonSerializer.serialize(transferValidationRequest.getTransferDetails()));
      statement.setString(3, transferValidationRequest.getStatus().name());
      statement.setString(4, transferValidationRequest.getDocument());
      statement.setString(5, transferValidationRequest.getSignature());
      statement.setString(6, transferValidationRequest.getRejectReasonMessage());
      statement.setTimestamp(7, Timestamp.from(transferValidationRequest.getCreatedAt()));
      statement.setTimestamp(8, Timestamp.from(transferValidationRequest.getUpdatedAt()));
      statement.setLong(9, transferValidationRequest.getId());

      statement.execute();
    }
  }

  public TransferValidationRequest toDomain(TransferValidationRequestEntity entity) {
    try {
      return new TransferValidationRequest(
          entity.getId(),
          entity.getTenantId(),
          jsonSerializer.deserialize(entity.getTransferDetails(), TransferDetails.class),
          entity.getStatus(),
          entity.getDocument(),
          entity.getSignature(),
          entity.getRejectReasonMessage(),
          entity.getCreatedAt(),
          entity.getUpdatedAt());
    } catch (Exception exception) {
      throw new IllegalArgumentException("Can not read transfer validation request", exception);
    }
  }
}
