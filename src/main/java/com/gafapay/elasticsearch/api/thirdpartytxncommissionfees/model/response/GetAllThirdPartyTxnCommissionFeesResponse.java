package com.gafapay.elasticsearch.api.thirdpartytxncommissionfees.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class GetAllThirdPartyTxnCommissionFeesResponse extends CommonAPIDataResponse {
   @JsonProperty("third_party_txn_commission_fees")
   private List<ThirdPartyTxnCommissionFeesDetail> thirdPartyTxnCommissionFeesDetailList;

   public List<ThirdPartyTxnCommissionFeesDetail> getThirdPartyTxnCommissionFeesDetailList() {
      return this.thirdPartyTxnCommissionFeesDetailList;
   }

   @JsonProperty("third_party_txn_commission_fees")
   public void setThirdPartyTxnCommissionFeesDetailList(final List<ThirdPartyTxnCommissionFeesDetail> thirdPartyTxnCommissionFeesDetailList) {
      this.thirdPartyTxnCommissionFeesDetailList = thirdPartyTxnCommissionFeesDetailList;
   }

   public GetAllThirdPartyTxnCommissionFeesResponse() {
   }
}
