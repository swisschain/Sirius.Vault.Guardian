package io.swisschain.repositories.smart_contract_invocation_validation_requests;

import io.swisschain.contracts.smart_contracts.invocation.SmartContractInvocation;
import io.swisschain.domain.validation_requests.smart_contracts.SmartContractInvocationValidationRequest;
import io.swisschain.repositories.DbConnectionFactory;
import io.swisschain.services.JsonSerializer;

import java.sql.SQLException;
import java.sql.Timestamp;

public class SmartContractInvocationValidationRequestRepositoryImp
    implements SmartContractInvocationValidationRequestRepository {
  private final String tableName = "smart_contract_invocation_validation_requests";
  private final DbConnectionFactory connectionFactory;
  private final JsonSerializer jsonSerializer;

  public SmartContractInvocationValidationRequestRepositoryImp(
      DbConnectionFactory connectionFactory, JsonSerializer jsonSerializer) {
    this.connectionFactory = connectionFactory;
    this.jsonSerializer = jsonSerializer;
  }

  public SmartContractInvocationValidationRequest getById(
      long smartContractInvocationValidationRequestId) throws SQLException {
    var sql =
        "SELECT *\n"
            + String.format("FROM %s.%s\n", connectionFactory.getSchema(), tableName)
            + "WHERE id = ?;";

    try (var connection = this.connectionFactory.create();
        var statement = connection.prepareStatement(sql)) {
      statement.setLong(1, smartContractInvocationValidationRequestId);

      var resultSet = statement.executeQuery();

      if (resultSet.next()) {
        var entity = new SmartContractInvocationValidationRequestEntity();
        entity.map(resultSet);

        return toDomain(entity);
      }

      return null;
    }
  }

  public void insert(
      SmartContractInvocationValidationRequest smartContractInvocationValidationRequest)
      throws Exception {
    var sql =
        String.format("INSERT INTO %s.%s(\n", connectionFactory.getSchema(), tableName)
            + "id, tenant_id, vault_id, smart_contract_invocation, status, document, signature, reject_reason_message, created_at, updated_at)\n"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";

    try (var connection = this.connectionFactory.create();
        var statement = connection.prepareStatement(sql)) {
      statement.setLong(1, smartContractInvocationValidationRequest.getId());
      statement.setString(2, smartContractInvocationValidationRequest.getTenantId());
      statement.setLong(3, smartContractInvocationValidationRequest.getVaultId());
      statement.setString(
          4,
          jsonSerializer.serialize(
              smartContractInvocationValidationRequest.getSmartContractInvocation()));
      statement.setString(5, smartContractInvocationValidationRequest.getStatus().name());
      statement.setString(6, smartContractInvocationValidationRequest.getDocument());
      statement.setString(7, smartContractInvocationValidationRequest.getSignature());
      statement.setString(8, smartContractInvocationValidationRequest.getRejectReasonMessage());
      statement.setTimestamp(
          9, Timestamp.from(smartContractInvocationValidationRequest.getCreatedAt()));
      statement.setTimestamp(
          10, Timestamp.from(smartContractInvocationValidationRequest.getUpdatedAt()));

      statement.execute();
    }
  }

  public void update(
      SmartContractInvocationValidationRequest smartContractInvocationValidationRequest)
      throws Exception {
    var sql =
        String.format("UPDATE %s.%s\n", connectionFactory.getSchema(), tableName)
            + "SET tenant_id = ?, vault_id = ?, smart_contract_invocation = ?, status = ?, document = ?, signature = ?, reject_reason_message = ?, created_at = ?, updated_at = ?\n"
            + "WHERE id = ?;";

    try (var connection = this.connectionFactory.create();
        var statement = connection.prepareStatement(sql)) {
      statement.setString(1, smartContractInvocationValidationRequest.getTenantId());
      statement.setLong(2, smartContractInvocationValidationRequest.getVaultId());
      statement.setString(
          3,
          jsonSerializer.serialize(
              smartContractInvocationValidationRequest.getSmartContractInvocation()));
      statement.setString(4, smartContractInvocationValidationRequest.getStatus().name());
      statement.setString(5, smartContractInvocationValidationRequest.getDocument());
      statement.setString(6, smartContractInvocationValidationRequest.getSignature());
      statement.setString(7, smartContractInvocationValidationRequest.getRejectReasonMessage());
      statement.setTimestamp(
          8, Timestamp.from(smartContractInvocationValidationRequest.getCreatedAt()));
      statement.setTimestamp(
          9, Timestamp.from(smartContractInvocationValidationRequest.getUpdatedAt()));
      statement.setLong(10, smartContractInvocationValidationRequest.getId());

      statement.execute();
    }
  }

  public SmartContractInvocationValidationRequest toDomain(
      SmartContractInvocationValidationRequestEntity entity) {
    try {
      return new SmartContractInvocationValidationRequest(
          entity.getId(),
          entity.getTenantId(),
          entity.getVaultId(),
          jsonSerializer.deserialize(
              entity.getSmartContractInvocation(), SmartContractInvocation.class),
          entity.getStatus(),
          entity.getDocument(),
          entity.getSignature(),
          entity.getRejectReasonMessage(),
          entity.getCreatedAt(),
          entity.getUpdatedAt());
    } catch (Exception exception) {
      throw new IllegalArgumentException(
          "Can not read smart contract invocation validation request", exception);
    }
  }
}
