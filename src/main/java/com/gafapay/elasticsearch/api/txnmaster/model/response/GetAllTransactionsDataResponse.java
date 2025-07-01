package com.gafapay.elasticsearch.api.txnmaster.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class GetAllTransactionsDataResponse extends CommonAPIDataResponse {
   @JsonProperty("transactions")
   private List<TxnData> txnMasterList;

   public List<TxnData> getTxnMasterList() {
      return this.txnMasterList;
   }

   @JsonProperty("transactions")
   public void setTxnMasterList(final List<TxnData> txnMasterList) {
      this.txnMasterList = txnMasterList;
   }

   public GetAllTransactionsDataResponse() {
   }
}
