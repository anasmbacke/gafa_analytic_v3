package com.gafapay.elasticsearch.api.spendanalyizer.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TransferByUserStatisticsResponse extends CommonAPIDataResponse {
   @JsonProperty("transfer_statistics_by_users")
   List<SpendAnalyticsDetails> spendAnalyizerForTxnDataList = new ArrayList();

   public List<SpendAnalyticsDetails> getSpendAnalyizerForTxnDataList() {
      return this.spendAnalyizerForTxnDataList;
   }

   @JsonProperty("transfer_statistics_by_users")
   public void setSpendAnalyizerForTxnDataList(final List<SpendAnalyticsDetails> spendAnalyizerForTxnDataList) {
      this.spendAnalyizerForTxnDataList = spendAnalyizerForTxnDataList;
   }

   public TransferByUserStatisticsResponse() {
   }

   public static class SpendAnalyticsDetails {
      @JsonProperty("user_info")
      private Map<String, Object> spendAnalyticsUserInfo;

      public Map<String, Object> getSpendAnalyticsUserInfo() {
         return this.spendAnalyticsUserInfo;
      }

      @JsonProperty("user_info")
      public void setSpendAnalyticsUserInfo(final Map<String, Object> spendAnalyticsUserInfo) {
         this.spendAnalyticsUserInfo = spendAnalyticsUserInfo;
      }

      public SpendAnalyticsDetails() {
      }
   }
}
