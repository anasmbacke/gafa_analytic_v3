package com.gafapay.elasticsearch.api.eventlogs.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SaveEventLogsResponse extends CommonAPIDataResponse {
   @JsonProperty("id")
   private String Id;

   public String getId() {
      return this.Id;
   }

   @JsonProperty("id")
   public void setId(final String Id) {
      this.Id = Id;
   }

   public SaveEventLogsResponse() {
   }
}
