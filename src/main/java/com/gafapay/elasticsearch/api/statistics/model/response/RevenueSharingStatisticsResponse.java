package com.gafapay.elasticsearch.api.statistics.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class RevenueSharingStatisticsResponse extends CommonAPIDataResponse {
   @JsonProperty("revenue_sharing_statistics_list")
   private List<RevenueSharingStatistics> revenueSharingStatisticsList;

   public List<RevenueSharingStatistics> getRevenueSharingStatisticsList() {
      return this.revenueSharingStatisticsList;
   }

   @JsonProperty("revenue_sharing_statistics_list")
   public void setRevenueSharingStatisticsList(final List<RevenueSharingStatistics> revenueSharingStatisticsList) {
      this.revenueSharingStatisticsList = revenueSharingStatisticsList;
   }

   public RevenueSharingStatisticsResponse() {
   }

   public static class RevenueSharingStatistics {
      @JsonProperty("timestamp")
      private Long timestamp;
      @JsonProperty("gross_revenue")
      private Double grossRevenue;
      @JsonProperty("total_tax")
      private Double totalTax;
      @JsonProperty("net_revenue")
      private Double netRevenue;
      @JsonProperty("company_share")
      private Double companyShare;
      @JsonProperty("bank_partner_share_list")
      private List<Map<String, Object>> bankPartnerShareList;
      @JsonProperty("total_bank_partner_share")
      private Double totalBankPartnerShare;

      public Long getTimestamp() {
         return this.timestamp;
      }

      public Double getGrossRevenue() {
         return this.grossRevenue;
      }

      public Double getTotalTax() {
         return this.totalTax;
      }

      public Double getNetRevenue() {
         return this.netRevenue;
      }

      public Double getCompanyShare() {
         return this.companyShare;
      }

      public List<Map<String, Object>> getBankPartnerShareList() {
         return this.bankPartnerShareList;
      }

      public Double getTotalBankPartnerShare() {
         return this.totalBankPartnerShare;
      }

      @JsonProperty("timestamp")
      public void setTimestamp(final Long timestamp) {
         this.timestamp = timestamp;
      }

      @JsonProperty("gross_revenue")
      public void setGrossRevenue(final Double grossRevenue) {
         this.grossRevenue = grossRevenue;
      }

      @JsonProperty("total_tax")
      public void setTotalTax(final Double totalTax) {
         this.totalTax = totalTax;
      }

      @JsonProperty("net_revenue")
      public void setNetRevenue(final Double netRevenue) {
         this.netRevenue = netRevenue;
      }

      @JsonProperty("company_share")
      public void setCompanyShare(final Double companyShare) {
         this.companyShare = companyShare;
      }

      @JsonProperty("bank_partner_share_list")
      public void setBankPartnerShareList(final List<Map<String, Object>> bankPartnerShareList) {
         this.bankPartnerShareList = bankPartnerShareList;
      }

      @JsonProperty("total_bank_partner_share")
      public void setTotalBankPartnerShare(final Double totalBankPartnerShare) {
         this.totalBankPartnerShare = totalBankPartnerShare;
      }

      public RevenueSharingStatistics() {
      }
   }
}
