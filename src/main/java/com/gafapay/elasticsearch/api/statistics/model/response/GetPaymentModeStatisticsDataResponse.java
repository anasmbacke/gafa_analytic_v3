package com.gafapay.elasticsearch.api.statistics.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class GetPaymentModeStatisticsDataResponse extends CommonAPIDataResponse {
   @JsonProperty("transaction_statistics")
   private TransactionStatisticsData transactionStatisticsData;

   public TransactionStatisticsData getTransactionStatisticsData() {
      return this.transactionStatisticsData;
   }

   @JsonProperty("transaction_statistics")
   public void setTransactionStatisticsData(final TransactionStatisticsData transactionStatisticsData) {
      this.transactionStatisticsData = transactionStatisticsData;
   }

   public GetPaymentModeStatisticsDataResponse() {
   }

   public static class TransactionStatisticsData {
      @JsonProperty("payment_mode_statistics")
      private List<Map<String, Object>> txnCodeStatistics;

      public List<Map<String, Object>> getTxnCodeStatistics() {
         return this.txnCodeStatistics;
      }

      @JsonProperty("payment_mode_statistics")
      public void setTxnCodeStatistics(final List<Map<String, Object>> txnCodeStatistics) {
         this.txnCodeStatistics = txnCodeStatistics;
      }

      public TransactionStatisticsData() {
      }
   }
}
