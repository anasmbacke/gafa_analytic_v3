package com.gafapay.elasticsearch.api.eventlogs.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetEventLogsResponse extends CommonAPIDataResponse {
   @JsonProperty("event_logs")
   private EventLogsMaster eventLogsMaster;

   public EventLogsMaster getEventLogsMaster() {
      return this.eventLogsMaster;
   }

   @JsonProperty("event_logs")
   public void setEventLogsMaster(final EventLogsMaster eventLogsMaster) {
      this.eventLogsMaster = eventLogsMaster;
   }

   public GetEventLogsResponse() {
   }
}
