package com.gafapay.elasticsearch.api.statistics.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class CorporateStatisticsResponse extends CommonAPIDataResponse {
   @JsonProperty("corporate_wise_statistics")
   private List<Map<String, Object>> corporateList;
   @JsonProperty("minimum_txn_amount")
   private Double minimumTxnAmount;
   @JsonProperty("maximum_txn_amount")
   private Double maximumTxnAmount;
   @JsonProperty("total_txn_amount")
   private Double totalTxnAmount;
   @JsonProperty("total_txn_count")
   private Double totalTxnCount;

   public List<Map<String, Object>> getCorporateList() {
      return this.corporateList;
   }

   public Double getMinimumTxnAmount() {
      return this.minimumTxnAmount;
   }

   public Double getMaximumTxnAmount() {
      return this.maximumTxnAmount;
   }

   public Double getTotalTxnAmount() {
      return this.totalTxnAmount;
   }

   public Double getTotalTxnCount() {
      return this.totalTxnCount;
   }

   @JsonProperty("corporate_wise_statistics")
   public void setCorporateList(final List<Map<String, Object>> corporateList) {
      this.corporateList = corporateList;
   }

   @JsonProperty("minimum_txn_amount")
   public void setMinimumTxnAmount(final Double minimumTxnAmount) {
      this.minimumTxnAmount = minimumTxnAmount;
   }

   @JsonProperty("maximum_txn_amount")
   public void setMaximumTxnAmount(final Double maximumTxnAmount) {
      this.maximumTxnAmount = maximumTxnAmount;
   }

   @JsonProperty("total_txn_amount")
   public void setTotalTxnAmount(final Double totalTxnAmount) {
      this.totalTxnAmount = totalTxnAmount;
   }

   @JsonProperty("total_txn_count")
   public void setTotalTxnCount(final Double totalTxnCount) {
      this.totalTxnCount = totalTxnCount;
   }

   public CorporateStatisticsResponse() {
   }
}
