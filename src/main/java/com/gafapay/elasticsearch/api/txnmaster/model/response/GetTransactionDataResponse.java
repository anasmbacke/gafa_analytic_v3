package com.gafapay.elasticsearch.api.txnmaster.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetTransactionDataResponse extends CommonAPIDataResponse {
   @JsonProperty("transaction")
   private TxnData txnData;

   public TxnData getTxnData() {
      return this.txnData;
   }

   @JsonProperty("transaction")
   public void setTxnData(final TxnData txnData) {
      this.txnData = txnData;
   }

   public GetTransactionDataResponse() {
   }
}
