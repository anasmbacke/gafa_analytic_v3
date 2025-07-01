package com.gafapay.elasticsearch.api.revenuesummary.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetRevenueSummaryDataResponse extends CommonAPIDataResponse {
   @JsonProperty("revenue_summary")
   private RevenueSummaryData revenueSummaryData;

   public RevenueSummaryData getRevenueSummaryData() {
      return this.revenueSummaryData;
   }

   @JsonProperty("revenue_summary")
   public void setRevenueSummaryData(final RevenueSummaryData revenueSummaryData) {
      this.revenueSummaryData = revenueSummaryData;
   }

   public GetRevenueSummaryDataResponse() {
   }
}
