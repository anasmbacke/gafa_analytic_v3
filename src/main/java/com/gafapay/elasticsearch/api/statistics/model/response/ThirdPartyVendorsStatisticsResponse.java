package com.gafapay.elasticsearch.api.statistics.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class ThirdPartyVendorsStatisticsResponse extends CommonAPIDataResponse {
   @JsonProperty("third_party_vendors_statistics")
   private List<ThirdPartyVendor> thirdPartyVendorStatisticsList;

   public List<ThirdPartyVendor> getThirdPartyVendorStatisticsList() {
      return this.thirdPartyVendorStatisticsList;
   }

   @JsonProperty("third_party_vendors_statistics")
   public void setThirdPartyVendorStatisticsList(final List<ThirdPartyVendor> thirdPartyVendorStatisticsList) {
      this.thirdPartyVendorStatisticsList = thirdPartyVendorStatisticsList;
   }

   public ThirdPartyVendorsStatisticsResponse() {
   }

   public static class ThirdPartyVendor {
      @JsonProperty("type_key_info")
      private Map<String, Object> typeKeyInfo;
      @JsonProperty("commission")
      private Double commission;
      @JsonProperty("fees")
      private Double fess;
      @JsonProperty("total_transaction")
      private Integer totalTransaction;

      public Map<String, Object> getTypeKeyInfo() {
         return this.typeKeyInfo;
      }

      public Double getCommission() {
         return this.commission;
      }

      public Double getFess() {
         return this.fess;
      }

      public Integer getTotalTransaction() {
         return this.totalTransaction;
      }

      @JsonProperty("type_key_info")
      public void setTypeKeyInfo(final Map<String, Object> typeKeyInfo) {
         this.typeKeyInfo = typeKeyInfo;
      }

      @JsonProperty("commission")
      public void setCommission(final Double commission) {
         this.commission = commission;
      }

      @JsonProperty("fees")
      public void setFess(final Double fess) {
         this.fess = fess;
      }

      @JsonProperty("total_transaction")
      public void setTotalTransaction(final Integer totalTransaction) {
         this.totalTransaction = totalTransaction;
      }

      public ThirdPartyVendor() {
      }
   }
}
