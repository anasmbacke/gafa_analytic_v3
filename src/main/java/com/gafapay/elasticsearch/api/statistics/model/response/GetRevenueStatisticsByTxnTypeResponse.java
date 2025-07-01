package com.gafapay.elasticsearch.api.statistics.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class GetRevenueStatisticsByTxnTypeResponse extends CommonAPIDataResponse {
   @JsonProperty("statistics")
   private List<RevenueStatisticsByTxnType> revenueStatisticsByTxnTypeList;

   public List<RevenueStatisticsByTxnType> getRevenueStatisticsByTxnTypeList() {
      return this.revenueStatisticsByTxnTypeList;
   }

   @JsonProperty("statistics")
   public void setRevenueStatisticsByTxnTypeList(final List<RevenueStatisticsByTxnType> revenueStatisticsByTxnTypeList) {
      this.revenueStatisticsByTxnTypeList = revenueStatisticsByTxnTypeList;
   }

   public GetRevenueStatisticsByTxnTypeResponse() {
   }

   public static class RevenueStatisticsByTxnType {
      @JsonProperty("txn_type")
      private Integer txnType;
      @JsonProperty("overall")
      private Double overall;
      @JsonProperty("total_count")
      private Integer totalCount;
      @JsonProperty("total_amount")
      private Double totalAmount;

      public Integer getTxnType() {
         return this.txnType;
      }

      public Double getOverall() {
         return this.overall;
      }

      public Integer getTotalCount() {
         return this.totalCount;
      }

      public Double getTotalAmount() {
         return this.totalAmount;
      }

      @JsonProperty("txn_type")
      public void setTxnType(final Integer txnType) {
         this.txnType = txnType;
      }

      @JsonProperty("overall")
      public void setOverall(final Double overall) {
         this.overall = overall;
      }

      @JsonProperty("total_count")
      public void setTotalCount(final Integer totalCount) {
         this.totalCount = totalCount;
      }

      @JsonProperty("total_amount")
      public void setTotalAmount(final Double totalAmount) {
         this.totalAmount = totalAmount;
      }

      public RevenueStatisticsByTxnType() {
      }
   }
}
