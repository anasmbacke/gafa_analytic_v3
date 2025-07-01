package com.gafapay.elasticsearch.api.nodelbanktransaction.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class GetAllNodalBankTransactionResponse extends CommonAPIDataResponse {
   @JsonProperty("nodal_bank_transaction")
   private List<NodalBankTransactionData> nodalBankTransactionDataList;

   public List<NodalBankTransactionData> getNodalBankTransactionDataList() {
      return this.nodalBankTransactionDataList;
   }

   @JsonProperty("nodal_bank_transaction")
   public void setNodalBankTransactionDataList(final List<NodalBankTransactionData> nodalBankTransactionDataList) {
      this.nodalBankTransactionDataList = nodalBankTransactionDataList;
   }

   public GetAllNodalBankTransactionResponse() {
   }
}
