package com.gafapay.elasticsearch.api.totalrecords.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TotalRecordResponse extends CommonAPIDataResponse {
   @JsonProperty("total_count")
   private Long totalCount;

   public Long getTotalCount() {
      return this.totalCount;
   }

   @JsonProperty("total_count")
   public void setTotalCount(final Long totalCount) {
      this.totalCount = totalCount;
   }

   public TotalRecordResponse() {
   }
}
