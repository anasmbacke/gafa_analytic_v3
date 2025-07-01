package com.gafapay.elasticsearch.api.thirdpartytxncommissionfees.model.request;

import com.gafapay.elasticsearch.api.commonrequest.CommonAPIDataRequest;
import org.apache.commons.lang3.StringUtils;

public class GetThirdPartyTxnCommissionFeesRequest extends CommonAPIDataRequest {
   private String id;

   public boolean checkBadRequest() {
      return StringUtils.isEmpty(this.getCompany_id()) || StringUtils.isEmpty(this.getId());
   }

   public String getId() {
      return this.id;
   }

   public void setId(final String id) {
      this.id = id;
   }

   public GetThirdPartyTxnCommissionFeesRequest() {
   }
}
