package io.swisschain.odm;

import io.swisschain.odm.contracts.policy.PolicyRequest;
import io.swisschain.odm.contracts.policy.PolicyResponse;
import io.swisschain.odm.contracts.selector.PolicySelectorRequest;
import io.swisschain.odm.contracts.selector.PolicySelectorResponse;

public interface OdmClient {
  PolicySelectorResponse getPolicy(PolicySelectorRequest policySelectorRequest, String requestId)throws Exception;

  PolicyResponse getResolution(
      PolicyRequest policyRequest,
      String requestId,
      String policyService,
      String policyServiceVersion,
      String policy,
      String policyVersion)throws Exception;
}
