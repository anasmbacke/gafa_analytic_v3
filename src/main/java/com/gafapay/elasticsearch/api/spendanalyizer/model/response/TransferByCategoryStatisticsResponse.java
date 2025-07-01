package com.gafapay.elasticsearch.api.spendanalyizer.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TransferByCategoryStatisticsResponse extends CommonAPIDataResponse {
   @JsonProperty("transfer_statistics_by_categories")
   private List<TransferByCategoryStatisticsDetails> transferByCategoryStatisticsList = new ArrayList();

   public List<TransferByCategoryStatisticsDetails> getTransferByCategoryStatisticsList() {
      return this.transferByCategoryStatisticsList;
   }

   @JsonProperty("transfer_statistics_by_categories")
   public void setTransferByCategoryStatisticsList(final List<TransferByCategoryStatisticsDetails> transferByCategoryStatisticsList) {
      this.transferByCategoryStatisticsList = transferByCategoryStatisticsList;
   }

   public TransferByCategoryStatisticsResponse() {
   }

   public static class TransferByCategoryStatisticsDetails {
      @JsonProperty("merchant_category_info")
      private Map<String, Object> spendAnalyticsCategoryInfo;

      public Map<String, Object> getSpendAnalyticsCategoryInfo() {
         return this.spendAnalyticsCategoryInfo;
      }

      @JsonProperty("merchant_category_info")
      public void setSpendAnalyticsCategoryInfo(final Map<String, Object> spendAnalyticsCategoryInfo) {
         this.spendAnalyticsCategoryInfo = spendAnalyticsCategoryInfo;
      }

      public TransferByCategoryStatisticsDetails() {
      }
   }
}
