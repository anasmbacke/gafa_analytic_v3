package com.gafapay.elasticsearch.api.statistics.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class ThirdPartyProviderStatisticsResponse extends CommonAPIDataResponse {
   @JsonProperty("third_party_provider_statistics")
   private List<ThirdPartyProvider> thirdPartyProviderList;

   public List<ThirdPartyProvider> getThirdPartyProviderList() {
      return this.thirdPartyProviderList;
   }

   @JsonProperty("third_party_provider_statistics")
   public void setThirdPartyProviderList(final List<ThirdPartyProvider> thirdPartyProviderList) {
      this.thirdPartyProviderList = thirdPartyProviderList;
   }

   public ThirdPartyProviderStatisticsResponse() {
   }

   public static class ThirdPartyProvider {
      @JsonProperty("provider_code_info")
      private Map<String, Object> providerCodeInfo;
      @JsonProperty("commission")
      private Double commission;
      @JsonProperty("fees")
      private Double fess;
      @JsonProperty("total_transaction")
      private Integer totalTransaction;

      @JsonProperty("provider_code_info")
      public void setProviderCodeInfo(final Map<String, Object> providerCodeInfo) {
         this.providerCodeInfo = providerCodeInfo;
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

      public Map<String, Object> getProviderCodeInfo() {
         return this.providerCodeInfo;
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

      public ThirdPartyProvider() {
      }
   }
}
