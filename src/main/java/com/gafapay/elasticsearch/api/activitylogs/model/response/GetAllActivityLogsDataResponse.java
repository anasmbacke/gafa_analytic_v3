package com.gafapay.elasticsearch.api.activitylogs.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class GetAllActivityLogsDataResponse extends CommonAPIDataResponse {
   @JsonProperty("activity_logs")
   private List<Logs> activityLogs;

   public List<Logs> getActivityLogs() {
      return this.activityLogs;
   }

   @JsonProperty("activity_logs")
   public void setActivityLogs(final List<Logs> activityLogs) {
      this.activityLogs = activityLogs;
   }

   public GetAllActivityLogsDataResponse() {
   }
}
