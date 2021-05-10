package io.swisschain.odm;

import io.swisschain.contracts.documents.ValidatorResolution;
import io.swisschain.odm.models.DecisionResponse;

import java.util.List;

public interface OdmClient<T> {
  DecisionResponse getDecision(T request, List<ValidatorResolution> validatorResolutions)
      throws Exception;
}
