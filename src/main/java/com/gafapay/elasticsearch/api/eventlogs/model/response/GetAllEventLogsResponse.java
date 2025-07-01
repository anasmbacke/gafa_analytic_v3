package com.gafapay.elasticsearch.api.eventlogs.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class GetAllEventLogsResponse extends CommonAPIDataResponse {
   @JsonProperty("event_logs")
   private List<EventLogsMaster> eventLogsMasters;

   public List<EventLogsMaster> getEventLogsMasters() {
      return this.eventLogsMasters;
   }

   @JsonProperty("event_logs")
   public void setEventLogsMasters(final List<EventLogsMaster> eventLogsMasters) {
      this.eventLogsMasters = eventLogsMasters;
   }

   public GetAllEventLogsResponse() {
   }
}
