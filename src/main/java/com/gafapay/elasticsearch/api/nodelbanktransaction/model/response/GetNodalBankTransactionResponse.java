package com.gafapay.elasticsearch.api.nodelbanktransaction.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetNodalBankTransactionResponse extends CommonAPIDataResponse {
   @JsonProperty("nodal_bank_transaction")
   private NodalBankTransactionData nodalBankTransactionData;

   public NodalBankTransactionData getNodalBankTransactionData() {
      return this.nodalBankTransactionData;
   }

   @JsonProperty("nodal_bank_transaction")
   public void setNodalBankTransactionData(final NodalBankTransactionData nodalBankTransactionData) {
      this.nodalBankTransactionData = nodalBankTransactionData;
   }

   public GetNodalBankTransactionResponse() {
   }
}
