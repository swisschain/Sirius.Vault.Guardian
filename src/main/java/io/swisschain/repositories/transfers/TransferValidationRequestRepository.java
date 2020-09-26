package io.swisschain.repositories.transfers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swisschain.domain.transfers.ApprovalContext;
import io.swisschain.domain.transfers.TransferDetails;
import io.swisschain.domain.transfers.TransferValidationRequest;
import io.swisschain.repositories.DbConnectionFactory;
import io.swisschain.repositories.Repository;
import io.swisschain.repositories.exceptions.AlreadyExistsException;

import java.sql.SQLException;
import java.sql.Timestamp;

public class TransferValidationRequestRepository extends Repository {
  private final String tableName = "transfer_validation_requests";
  private final ObjectMapper mapper = new ObjectMapper();
  private final DbConnectionFactory connectionFactory;

  public TransferValidationRequestRepository(DbConnectionFactory connectionFactory) {
    this.connectionFactory = connectionFactory;
  }

  public TransferValidationRequest getById(long transferValidationRequestId) throws Exception {
    String sql =
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

  public void insert(TransferValidationRequest transferValidationRequest)
      throws Exception, AlreadyExistsException {
    var sql =
        String.format("INSERT INTO %s.%s(\n", connectionFactory.getSchema(), tableName)
            + "id, details, approval_context, customer_signature, sirius_signature, status, created_at, updated_at)\n"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?); ";

    try (var connection = this.connectionFactory.create();
        var statement = connection.prepareStatement(sql)) {
      statement.setLong(1, transferValidationRequest.getId());
      statement.setString(2, mapper.writeValueAsString(transferValidationRequest.getDetails()));
      statement.setString(
          3, mapper.writeValueAsString(transferValidationRequest.getApprovalContext()));
      statement.setString(4, transferValidationRequest.getCustomerSignature());
      statement.setString(5, transferValidationRequest.getSiriusSignature());
      statement.setString(6, transferValidationRequest.getStatus().name());
      statement.setTimestamp(7, Timestamp.from(transferValidationRequest.getCreatedAt()));
      statement.setTimestamp(8, Timestamp.from(transferValidationRequest.getUpdatedAt()));

      statement.execute();
    } catch (SQLException exception) {
      if (exception.getSQLState().equals(UniqueViolationErrorCode)) {
        throw new AlreadyExistsException();
      }
      throw new Exception(
          "An unexpected error occurred while inserting transfer validation request.", exception);
    }
  }

  public void update(TransferValidationRequest transferValidationRequest) throws Exception {
    var sql =
        String.format("UPDATE %s.%s\n", connectionFactory.getSchema(), tableName)
            + "SET details = ?, approval_context = ?, customer_signature = ?, sirius_signature = ?, status = ?, created_at = ?, updated_at = ?\n"
            + "WHERE id = ?;";

    try (var connection = this.connectionFactory.create();
        var statement = connection.prepareStatement(sql)) {
      statement.setString(1, mapper.writeValueAsString(transferValidationRequest.getDetails()));
      statement.setString(
          2, mapper.writeValueAsString(transferValidationRequest.getApprovalContext()));
      statement.setString(3, transferValidationRequest.getCustomerSignature());
      statement.setString(4, transferValidationRequest.getSiriusSignature());
      statement.setString(5, transferValidationRequest.getStatus().name());
      statement.setTimestamp(6, Timestamp.from(transferValidationRequest.getCreatedAt()));
      statement.setTimestamp(7, Timestamp.from(transferValidationRequest.getUpdatedAt()));
      statement.setLong(8, transferValidationRequest.getId());

      statement.execute();
    } catch (SQLException exception) {
      throw new Exception(
          "An unexpected error occurred while inserting transfer validation request.", exception);
    }
  }

  public TransferValidationRequest toDomain(TransferValidationRequestEntity entity) {
    try {
      return new TransferValidationRequest(
          entity.getId(),
          mapper.readValue(entity.getDetails(), TransferDetails.class),
          mapper.readValue(entity.getApprovalContext(), ApprovalContext.class),
          entity.getCustomerSignature(),
          entity.getSiriusSignature(),
          entity.getStatus(),
          entity.getCreatedAt(),
          entity.getUpdatedAt());
    } catch (Exception exception) {
      throw new IllegalArgumentException("Can not read transfer validation request", exception);
    }
  }
}
