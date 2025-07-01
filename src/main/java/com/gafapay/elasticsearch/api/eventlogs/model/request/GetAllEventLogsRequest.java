package com.gafapay.elasticsearch.api.eventlogs.model.request;

import com.gafapay.elasticsearch.api.commonrequest.CommonAllAPIDataRequest;
import org.apache.commons.lang3.StringUtils;

public class GetAllEventLogsRequest extends CommonAllAPIDataRequest {
   private Integer event_type;
   private String event_log_number;

   public boolean checkBadRequest() {
      return StringUtils.isEmpty(this.getCompany_id());
   }

   public Integer getEvent_type() {
      return this.event_type;
   }

   public String getEvent_log_number() {
      return this.event_log_number;
   }

   public void setEvent_type(final Integer event_type) {
      this.event_type = event_type;
   }

   public void setEvent_log_number(final String event_log_number) {
      this.event_log_number = event_log_number;
   }

   public GetAllEventLogsRequest() {
   }
}
