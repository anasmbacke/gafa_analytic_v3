package com.gafapay.elasticsearch.api.statistics.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class GetNodalBankStatisticsResponse extends CommonAPIDataResponse {
   @JsonProperty("nodal_bank_statistics")
   private NodalBankStatisticsDetails nodalBankStatisticsDetails;

   public NodalBankStatisticsDetails getNodalBankStatisticsDetails() {
      return this.nodalBankStatisticsDetails;
   }

   @JsonProperty("nodal_bank_statistics")
   public void setNodalBankStatisticsDetails(final NodalBankStatisticsDetails nodalBankStatisticsDetails) {
      this.nodalBankStatisticsDetails = nodalBankStatisticsDetails;
   }

   public GetNodalBankStatisticsResponse() {
   }

   public static class NodalBankStatisticsDetails {
      @JsonProperty("deposit")
      private Map<String, Object> deposit;
      @JsonProperty("withdraw")
      private Map<String, Object> withdraw;

      public Map<String, Object> getDeposit() {
         return this.deposit;
      }

      public Map<String, Object> getWithdraw() {
         return this.withdraw;
      }

      @JsonProperty("deposit")
      public void setDeposit(final Map<String, Object> deposit) {
         this.deposit = deposit;
      }

      @JsonProperty("withdraw")
      public void setWithdraw(final Map<String, Object> withdraw) {
         this.withdraw = withdraw;
      }

      public NodalBankStatisticsDetails() {
      }
   }
}
