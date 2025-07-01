package com.gafapay.elasticsearch.api.topmostearneragents.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class TopMostEarnerAgentsResponse extends CommonAPIDataResponse {
   @JsonProperty("earning_by_agents")
   private List<TopMostEarnerAgent> topMostEarnerAgentList;

   public List<TopMostEarnerAgent> getTopMostEarnerAgentList() {
      return this.topMostEarnerAgentList;
   }

   @JsonProperty("earning_by_agents")
   public void setTopMostEarnerAgentList(final List<TopMostEarnerAgent> topMostEarnerAgentList) {
      this.topMostEarnerAgentList = topMostEarnerAgentList;
   }

   public TopMostEarnerAgentsResponse() {
   }

   public static class TopMostEarnerAgent {
      @JsonProperty("user_id")
      private String userId;
      @JsonProperty("product_wise_statistics")
      private List<Statistics> statistics;

      @JsonProperty("user_id")
      public void setUserId(final String userId) {
         this.userId = userId;
      }

      @JsonProperty("product_wise_statistics")
      public void setStatistics(final List<Statistics> statistics) {
         this.statistics = statistics;
      }

      public String getUserId() {
         return this.userId;
      }

      public List<Statistics> getStatistics() {
         return this.statistics;
      }

      public TopMostEarnerAgent() {
      }
   }

   public static class Statistics {
      @JsonProperty("product_code")
      private String productCode;
      @JsonProperty("total_count")
      private Long totalCount;
      @JsonProperty("total_amount")
      private Double totalAmount;

      @JsonProperty("product_code")
      public void setProductCode(final String productCode) {
         this.productCode = productCode;
      }

      @JsonProperty("total_count")
      public void setTotalCount(final Long totalCount) {
         this.totalCount = totalCount;
      }

      @JsonProperty("total_amount")
      public void setTotalAmount(final Double totalAmount) {
         this.totalAmount = totalAmount;
      }

      public String getProductCode() {
         return this.productCode;
      }

      public Long getTotalCount() {
         return this.totalCount;
      }

      public Double getTotalAmount() {
         return this.totalAmount;
      }

      public Statistics() {
      }
   }
}
