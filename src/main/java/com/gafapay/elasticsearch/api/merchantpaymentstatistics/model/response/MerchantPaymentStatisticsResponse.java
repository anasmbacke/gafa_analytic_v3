package com.gafapay.elasticsearch.api.merchantpaymentstatistics.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class MerchantPaymentStatisticsResponse extends CommonAPIDataResponse {
   @JsonProperty("merchant_payment_statistics")
   private Map<String, Object> merchantPaymentStatistics;

   public Map<String, Object> getMerchantPaymentStatistics() {
      return this.merchantPaymentStatistics;
   }

   @JsonProperty("merchant_payment_statistics")
   public void setMerchantPaymentStatistics(final Map<String, Object> merchantPaymentStatistics) {
      this.merchantPaymentStatistics = merchantPaymentStatistics;
   }

   public MerchantPaymentStatisticsResponse() {
   }
}
