package com.gafapay.elasticsearch.api.revenuesummary.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class GetAllRevenueSummaryDataResponse extends CommonAPIDataResponse {
   @JsonProperty("revenue_summary")
   private List<RevenueSummaryData> revenueSummaryDataList;

   public List<RevenueSummaryData> getRevenueSummaryDataList() {
      return this.revenueSummaryDataList;
   }

   @JsonProperty("revenue_summary")
   public void setRevenueSummaryDataList(final List<RevenueSummaryData> revenueSummaryDataList) {
      this.revenueSummaryDataList = revenueSummaryDataList;
   }

   public GetAllRevenueSummaryDataResponse() {
   }
}
