package com.gafapay.elasticsearch.api.thirdpartytxncommissionfees.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetThirdPartyTxnCommissionFeesResponse extends CommonAPIDataResponse {
   @JsonProperty("third_party_txn_commission_fees")
   private ThirdPartyTxnCommissionFeesDetail thirdPartyTxnCommissionFeesDetail;

   public ThirdPartyTxnCommissionFeesDetail getThirdPartyTxnCommissionFeesDetail() {
      return this.thirdPartyTxnCommissionFeesDetail;
   }

   @JsonProperty("third_party_txn_commission_fees")
   public void setThirdPartyTxnCommissionFeesDetail(final ThirdPartyTxnCommissionFeesDetail thirdPartyTxnCommissionFeesDetail) {
      this.thirdPartyTxnCommissionFeesDetail = thirdPartyTxnCommissionFeesDetail;
   }

   public GetThirdPartyTxnCommissionFeesResponse() {
   }
}
