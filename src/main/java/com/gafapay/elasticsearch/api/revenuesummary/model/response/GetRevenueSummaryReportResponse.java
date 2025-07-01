package com.gafapay.elasticsearch.api.revenuesummary.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;

public class GetRevenueSummaryReportResponse extends CommonAPIDataResponse {
   @JsonProperty("revenue_summary")
   @JsonInclude(Include.NON_NULL)
   List<RevenueSummaryReport> revenueSummaryReportList;

   public List<RevenueSummaryReport> getRevenueSummaryReportList() {
      return this.revenueSummaryReportList;
   }

   @JsonProperty("revenue_summary")
   public void setRevenueSummaryReportList(final List<RevenueSummaryReport> revenueSummaryReportList) {
      this.revenueSummaryReportList = revenueSummaryReportList;
   }

   public GetRevenueSummaryReportResponse() {
   }

   @JsonInclude(Include.NON_NULL)
   public static class RevenueSummaryReport {
      @JsonProperty("txn_date")
      private Long txnDate;
      @JsonProperty("txn_id")
      private String txnId;
      @JsonProperty("txn_number")
      private String txnNumber;
      @JsonProperty("txn_amount")
      private Double txnAmount;
      @JsonProperty("txn_type")
      private Integer txnType;
      @JsonProperty("total_revenue")
      private Double totalRevenue;
      @JsonProperty("summary_date")
      private Long summaryDate;
      @JsonProperty("revenue_detail")
      private List<RevenueDetail> revenueDetailList;

      public Long getTxnDate() {
         return this.txnDate;
      }

      public String getTxnId() {
         return this.txnId;
      }

      public String getTxnNumber() {
         return this.txnNumber;
      }

      public Double getTxnAmount() {
         return this.txnAmount;
      }

      public Integer getTxnType() {
         return this.txnType;
      }

      public Double getTotalRevenue() {
         return this.totalRevenue;
      }

      public Long getSummaryDate() {
         return this.summaryDate;
      }

      public List<RevenueDetail> getRevenueDetailList() {
         return this.revenueDetailList;
      }

      @JsonProperty("txn_date")
      public void setTxnDate(final Long txnDate) {
         this.txnDate = txnDate;
      }

      @JsonProperty("txn_id")
      public void setTxnId(final String txnId) {
         this.txnId = txnId;
      }

      @JsonProperty("txn_number")
      public void setTxnNumber(final String txnNumber) {
         this.txnNumber = txnNumber;
      }

      @JsonProperty("txn_amount")
      public void setTxnAmount(final Double txnAmount) {
         this.txnAmount = txnAmount;
      }

      @JsonProperty("txn_type")
      public void setTxnType(final Integer txnType) {
         this.txnType = txnType;
      }

      @JsonProperty("total_revenue")
      public void setTotalRevenue(final Double totalRevenue) {
         this.totalRevenue = totalRevenue;
      }

      @JsonProperty("summary_date")
      public void setSummaryDate(final Long summaryDate) {
         this.summaryDate = summaryDate;
      }

      @JsonProperty("revenue_detail")
      public void setRevenueDetailList(final List<RevenueDetail> revenueDetailList) {
         this.revenueDetailList = revenueDetailList;
      }

      public RevenueSummaryReport() {
      }
   }

   public static class RevenueDetail {
      @JsonProperty("charge_id")
      private String chargeId;
      @JsonProperty("charge_name")
      private String chargeName;
      @JsonProperty("final_charge")
      private Double finalCharge;

      public String getChargeId() {
         return this.chargeId;
      }

      public String getChargeName() {
         return this.chargeName;
      }

      public Double getFinalCharge() {
         return this.finalCharge;
      }

      @JsonProperty("charge_id")
      public void setChargeId(final String chargeId) {
         this.chargeId = chargeId;
      }

      @JsonProperty("charge_name")
      public void setChargeName(final String chargeName) {
         this.chargeName = chargeName;
      }

      @JsonProperty("final_charge")
      public void setFinalCharge(final Double finalCharge) {
         this.finalCharge = finalCharge;
      }

      public RevenueDetail() {
      }
   }
}
