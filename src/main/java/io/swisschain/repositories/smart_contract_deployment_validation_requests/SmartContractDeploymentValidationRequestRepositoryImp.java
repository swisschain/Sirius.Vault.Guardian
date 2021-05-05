package io.swisschain.repositories.smart_contract_deployment_validation_requests;

import io.swisschain.contracts.smart_contracts.deployment.SmartContractDeployment;
import io.swisschain.domain.validation_requests.smart_contract_deployments.SmartContractDeploymentValidationRequest;
import io.swisschain.repositories.DbConnectionFactory;
import io.swisschain.services.JsonSerializer;

import java.sql.SQLException;
import java.sql.Timestamp;

public class SmartContractDeploymentValidationRequestRepositoryImp
    implements SmartContractDeploymentValidationRequestRepository {
  private final String tableName = "smart_contract_deployment_validation_requests";
  private final DbConnectionFactory connectionFactory;
  private final JsonSerializer jsonSerializer;

  public SmartContractDeploymentValidationRequestRepositoryImp(
      DbConnectionFactory connectionFactory, JsonSerializer jsonSerializer) {
    this.connectionFactory = connectionFactory;
    this.jsonSerializer = jsonSerializer;
  }

  public SmartContractDeploymentValidationRequest getById(
      long smartContractDeploymentValidationRequestId) throws SQLException {
    var sql =
        "SELECT *\n"
            + String.format("FROM %s.%s\n", connectionFactory.getSchema(), tableName)
            + "WHERE id = ?;";

    try (var connection = this.connectionFactory.create();
        var statement = connection.prepareStatement(sql)) {
      statement.setLong(1, smartContractDeploymentValidationRequestId);

      var resultSet = statement.executeQuery();

      if (resultSet.next()) {
        var entity = new SmartContractDeploymentValidationRequestEntity();
        entity.map(resultSet);

        return toDomain(entity);
      }

      return null;
    }
  }

  public void insert(
      SmartContractDeploymentValidationRequest smartContractDeploymentValidationRequest)
      throws Exception {
    var sql =
        String.format("INSERT INTO %s.%s(\n", connectionFactory.getSchema(), tableName)
            + "id, tenant_id, smart_contract_deployment, status, document, signature, reject_reason_message, created_at, updated_at)\n"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?); ";

    try (var connection = this.connectionFactory.create();
        var statement = connection.prepareStatement(sql)) {
      statement.setLong(1, smartContractDeploymentValidationRequest.getId());
      statement.setString(2, smartContractDeploymentValidationRequest.getTenantId());
      statement.setString(
          3,
          jsonSerializer.serialize(
              smartContractDeploymentValidationRequest.getSmartContractDeployment()));
      statement.setString(4, smartContractDeploymentValidationRequest.getStatus().name());
      statement.setString(5, smartContractDeploymentValidationRequest.getDocument());
      statement.setString(6, smartContractDeploymentValidationRequest.getSignature());
      statement.setString(7, smartContractDeploymentValidationRequest.getRejectReasonMessage());
      statement.setTimestamp(
          8, Timestamp.from(smartContractDeploymentValidationRequest.getCreatedAt()));
      statement.setTimestamp(
          9, Timestamp.from(smartContractDeploymentValidationRequest.getUpdatedAt()));

      statement.execute();
    }
  }

  public void update(
      SmartContractDeploymentValidationRequest smartContractDeploymentValidationRequest)
      throws Exception {
    var sql =
        String.format("UPDATE %s.%s\n", connectionFactory.getSchema(), tableName)
            + "SET tenant_id = ?, smart_contract_deployment = ?, status = ?, document = ?, signature = ?, reject_reason_message = ?, created_at = ?, updated_at = ?\n"
            + "WHERE id = ?;";

    try (var connection = this.connectionFactory.create();
        var statement = connection.prepareStatement(sql)) {
      statement.setString(1, smartContractDeploymentValidationRequest.getTenantId());
      statement.setString(
          2,
          jsonSerializer.serialize(
              smartContractDeploymentValidationRequest.getSmartContractDeployment()));
      statement.setString(3, smartContractDeploymentValidationRequest.getStatus().name());
      statement.setString(4, smartContractDeploymentValidationRequest.getDocument());
      statement.setString(5, smartContractDeploymentValidationRequest.getSignature());
      statement.setString(6, smartContractDeploymentValidationRequest.getRejectReasonMessage());
      statement.setTimestamp(
          7, Timestamp.from(smartContractDeploymentValidationRequest.getCreatedAt()));
      statement.setTimestamp(
          8, Timestamp.from(smartContractDeploymentValidationRequest.getUpdatedAt()));
      statement.setLong(9, smartContractDeploymentValidationRequest.getId());

      statement.execute();
    }
  }

  public SmartContractDeploymentValidationRequest toDomain(
      SmartContractDeploymentValidationRequestEntity entity) {
    try {
      return new SmartContractDeploymentValidationRequest(
          entity.getId(),
          entity.getTenantId(),
          jsonSerializer.deserialize(
              entity.getSmartContractDeployment(), SmartContractDeployment.class),
          entity.getStatus(),
          entity.getDocument(),
          entity.getSignature(),
          entity.getRejectReasonMessage(),
          entity.getCreatedAt(),
          entity.getUpdatedAt());
    } catch (Exception exception) {
      throw new IllegalArgumentException(
          "Can not read smart contract deployment validation request", exception);
    }
  }
}
