package io.swisschain.repositories.transferValidationRequests;

import io.swisschain.domain.exceptions.OperationExhaustedException;
import io.swisschain.domain.exceptions.OperationFailedException;
import io.swisschain.domain.transfers.TransferValidationRequest;

import java.sql.SQLException;

public interface TransferValidationRequestRepository {

  TransferValidationRequest getById(long transferValidationRequestId)
      throws SQLException, OperationFailedException, OperationExhaustedException;

  void insert(TransferValidationRequest transferValidationRequest) throws Exception;

  void update(TransferValidationRequest transferValidationRequest) throws Exception;
}
