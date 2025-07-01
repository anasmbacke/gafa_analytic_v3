package com.gafapay.elasticsearch.api.transactionperformance.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class UserTransactionPerformanceResponse extends CommonAPIDataResponse {
   @JsonProperty("user_transaction_performance")
   Map<String, Object> userTransactionPerformance;

   public Map<String, Object> getUserTransactionPerformance() {
      return this.userTransactionPerformance;
   }

   @JsonProperty("user_transaction_performance")
   public void setUserTransactionPerformance(final Map<String, Object> userTransactionPerformance) {
      this.userTransactionPerformance = userTransactionPerformance;
   }

   public UserTransactionPerformanceResponse() {
   }
}
