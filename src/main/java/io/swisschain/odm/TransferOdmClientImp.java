package io.swisschain.odm;

import io.swisschain.contracts.documents.ValidatorResolution;
import io.swisschain.contracts.transfers.Transfer;
import io.swisschain.odm.models.DecisionResponse;
import io.swisschain.odm.models.RequestWrapper;
import io.swisschain.odm.models.transfers.TransferValidationRequestWrapper;
import io.swisschain.odm.models.transfers.TransferValidationResponseWrapper;
import io.swisschain.odm.models.transfers.TransferValidationRequest;
import io.swisschain.services.JsonSerializer;

import java.util.List;
import java.util.UUID;

public class TransferOdmClientImp extends OdmClientImp<Transfer> {

  public TransferOdmClientImp(String url, JsonSerializer jsonSerializer) {
    super(url, jsonSerializer);
  }

  @Override
  protected RequestWrapper wrap(Transfer data, List<ValidatorResolution> validatorResolutions) {
    return new TransferValidationRequestWrapper(
        UUID.randomUUID().toString(), new TransferValidationRequest(data, validatorResolutions));
  }

  @Override
  protected DecisionResponse getResponse(String data) throws Exception {
    var response = jsonSerializer.deserialize(data, TransferValidationResponseWrapper.class);
    return response.transferValidationRequest;
  }
}
