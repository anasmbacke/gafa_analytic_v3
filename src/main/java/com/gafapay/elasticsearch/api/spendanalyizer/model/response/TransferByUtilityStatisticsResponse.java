package com.gafapay.elasticsearch.api.spendanalyizer.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TransferByUtilityStatisticsResponse extends CommonAPIDataResponse {
   @JsonProperty("payment_statistics")
   List<SpendAnalyticsDetails> spendAnalyizerForUtilityDataList = new ArrayList();

   public List<SpendAnalyticsDetails> getSpendAnalyizerForUtilityDataList() {
      return this.spendAnalyizerForUtilityDataList;
   }

   @JsonProperty("payment_statistics")
   public void setSpendAnalyizerForUtilityDataList(final List<SpendAnalyticsDetails> spendAnalyizerForUtilityDataList) {
      this.spendAnalyizerForUtilityDataList = spendAnalyizerForUtilityDataList;
   }

   public TransferByUtilityStatisticsResponse() {
   }

   public static class SpendAnalyticsDetails {
      @JsonProperty("vendor_provider_info")
      private Map<String, Object> spendAnalyticsUtilityInfo;

      public Map<String, Object> getSpendAnalyticsUtilityInfo() {
         return this.spendAnalyticsUtilityInfo;
      }

      @JsonProperty("vendor_provider_info")
      public void setSpendAnalyticsUtilityInfo(final Map<String, Object> spendAnalyticsUtilityInfo) {
         this.spendAnalyticsUtilityInfo = spendAnalyticsUtilityInfo;
      }

      public SpendAnalyticsDetails() {
      }
   }
}
