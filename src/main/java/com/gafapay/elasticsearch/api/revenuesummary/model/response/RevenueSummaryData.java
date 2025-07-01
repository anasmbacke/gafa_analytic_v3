package com.gafapay.elasticsearch.api.revenuesummary.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.gafapay.elasticsearch.api.revenuesummary.model.RevenueSummary;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class RevenueSummaryData extends CommonAPIDataResponse {
   @JsonProperty("_id")
   private String id;
   @JsonProperty("summary_date")
   private Long summaryDate;
   @JsonProperty("total_revenue")
   private Double totalRevenue;
   @JsonProperty("revenue_detail_summary")
   private List<Map<String, Object>> revenueDetailSummary;
   @JsonProperty("is_active")
   private Boolean isActive;
   @JsonProperty("created_by")
   private String createdBy;
   @JsonProperty("created_date")
   private Long createdDate;
   @JsonProperty("updated_by")
   private String updatedBy;
   @JsonProperty("updated_date")
   private Long updatedDate;

   public static RevenueSummaryData setRevenueSummary(RevenueSummary revenueSummary) {
      RevenueSummaryData revenueSummaryData = new RevenueSummaryData();
      revenueSummaryData.setId(revenueSummary.getId());
      revenueSummaryData.setSummaryDate(revenueSummary.getSummaryDate());
      revenueSummaryData.setTotalRevenue(revenueSummary.getTotalRevenue());
      revenueSummaryData.setRevenueDetailSummary(revenueSummary.getRevenueDetailSummary());
      revenueSummaryData.setIsActive(revenueSummary.getIsActive());
      revenueSummaryData.setCreatedDate(revenueSummary.getCreatedDate());
      revenueSummaryData.setUpdatedDate(revenueSummary.getUpdatedDate());
      revenueSummaryData.setUpdatedBy(revenueSummary.getUpdatedBy());
      revenueSummaryData.setCreatedBy(revenueSummary.getCreatedBy());
      return revenueSummaryData;
   }

   public String getId() {
      return this.id;
   }

   public Long getSummaryDate() {
      return this.summaryDate;
   }

   public Double getTotalRevenue() {
      return this.totalRevenue;
   }

   public List<Map<String, Object>> getRevenueDetailSummary() {
      return this.revenueDetailSummary;
   }

   public Boolean getIsActive() {
      return this.isActive;
   }

   public String getCreatedBy() {
      return this.createdBy;
   }

   public Long getCreatedDate() {
      return this.createdDate;
   }

   public String getUpdatedBy() {
      return this.updatedBy;
   }

   public Long getUpdatedDate() {
      return this.updatedDate;
   }

   @JsonProperty("_id")
   public void setId(final String id) {
      this.id = id;
   }

   @JsonProperty("summary_date")
   public void setSummaryDate(final Long summaryDate) {
      this.summaryDate = summaryDate;
   }

   @JsonProperty("total_revenue")
   public void setTotalRevenue(final Double totalRevenue) {
      this.totalRevenue = totalRevenue;
   }

   @JsonProperty("revenue_detail_summary")
   public void setRevenueDetailSummary(final List<Map<String, Object>> revenueDetailSummary) {
      this.revenueDetailSummary = revenueDetailSummary;
   }

   @JsonProperty("is_active")
   public void setIsActive(final Boolean isActive) {
      this.isActive = isActive;
   }

   @JsonProperty("created_by")
   public void setCreatedBy(final String createdBy) {
      this.createdBy = createdBy;
   }

   @JsonProperty("created_date")
   public void setCreatedDate(final Long createdDate) {
      this.createdDate = createdDate;
   }

   @JsonProperty("updated_by")
   public void setUpdatedBy(final String updatedBy) {
      this.updatedBy = updatedBy;
   }

   @JsonProperty("updated_date")
   public void setUpdatedDate(final Long updatedDate) {
      this.updatedDate = updatedDate;
   }

   public RevenueSummaryData() {
   }
}
