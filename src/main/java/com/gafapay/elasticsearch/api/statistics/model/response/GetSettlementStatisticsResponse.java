package com.gafapay.elasticsearch.api.statistics.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class GetSettlementStatisticsResponse extends CommonAPIDataResponse {
   @JsonProperty("settlement_statistics")
   private StatisticData statisticData;

   @JsonProperty("settlement_statistics")
   public void setStatisticData(final StatisticData statisticData) {
      this.statisticData = statisticData;
   }

   public StatisticData getStatisticData() {
      return this.statisticData;
   }

   public GetSettlementStatisticsResponse() {
   }

   public static class StatisticData {
      @JsonProperty("total_transactions")
      private long totalTransactions;
      @JsonProperty("average_settlement_time")
      private int averageSettlementTime;
      @JsonProperty("status_wise_statistics")
      private List<Map<String, Object>> statusWiseStatistics;
      @JsonProperty("transactions")
      private List<Map<String, Object>> transactions;

      public long getTotalTransactions() {
         return this.totalTransactions;
      }

      public int getAverageSettlementTime() {
         return this.averageSettlementTime;
      }

      public List<Map<String, Object>> getStatusWiseStatistics() {
         return this.statusWiseStatistics;
      }

      public List<Map<String, Object>> getTransactions() {
         return this.transactions;
      }

      @JsonProperty("total_transactions")
      public void setTotalTransactions(final long totalTransactions) {
         this.totalTransactions = totalTransactions;
      }

      @JsonProperty("average_settlement_time")
      public void setAverageSettlementTime(final int averageSettlementTime) {
         this.averageSettlementTime = averageSettlementTime;
      }

      @JsonProperty("status_wise_statistics")
      public void setStatusWiseStatistics(final List<Map<String, Object>> statusWiseStatistics) {
         this.statusWiseStatistics = statusWiseStatistics;
      }

      @JsonProperty("transactions")
      public void setTransactions(final List<Map<String, Object>> transactions) {
         this.transactions = transactions;
      }

      public StatisticData() {
      }
   }
}
