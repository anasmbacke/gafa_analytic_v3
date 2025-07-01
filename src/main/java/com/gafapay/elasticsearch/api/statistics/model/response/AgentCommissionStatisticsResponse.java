package com.gafapay.elasticsearch.api.statistics.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class AgentCommissionStatisticsResponse extends CommonAPIDataResponse {
   @JsonProperty("commission_statistics")
   private List<CommissionStatistic> commissionStatisticList;

   @JsonProperty("commission_statistics")
   public void setCommissionStatisticList(final List<CommissionStatistic> commissionStatisticList) {
      this.commissionStatisticList = commissionStatisticList;
   }

   public List<CommissionStatistic> getCommissionStatisticList() {
      return this.commissionStatisticList;
   }

   public AgentCommissionStatisticsResponse() {
   }

   public static class CommissionStatistic {
      @JsonProperty("id")
      private String id;
      @JsonProperty("total_count")
      private Long totalCount;
      @JsonProperty("total_amount")
      private Double totalAmount;

      @JsonProperty("id")
      public void setId(final String id) {
         this.id = id;
      }

      @JsonProperty("total_count")
      public void setTotalCount(final Long totalCount) {
         this.totalCount = totalCount;
      }

      @JsonProperty("total_amount")
      public void setTotalAmount(final Double totalAmount) {
         this.totalAmount = totalAmount;
      }

      public String getId() {
         return this.id;
      }

      public Long getTotalCount() {
         return this.totalCount;
      }

      public Double getTotalAmount() {
         return this.totalAmount;
      }

      public CommissionStatistic() {
      }
   }
}
