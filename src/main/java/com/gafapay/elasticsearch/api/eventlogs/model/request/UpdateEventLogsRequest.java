package com.gafapay.elasticsearch.api.eventlogs.model.request;

import com.gafapay.elasticsearch.api.commonrequest.CommonAPIDataRequest;
import java.util.Map;
import java.util.Objects;

public class UpdateEventLogsRequest extends CommonAPIDataRequest {
   private Map<String, Object> updateEvnentdataMap;

   public boolean checkBadRequest() {
      if (!Objects.isNull(this.getUpdateEvnentdataMap()) && !this.getUpdateEvnentdataMap().isEmpty()) {
         return !this.getUpdateEvnentdataMap().containsKey("id") || Objects.isNull(this.getUpdateEvnentdataMap().get("id"));
      } else {
         return true;
      }
   }

   public Map<String, Object> getUpdateEvnentdataMap() {
      return this.updateEvnentdataMap;
   }

   public void setUpdateEvnentdataMap(final Map<String, Object> updateEvnentdataMap) {
      this.updateEvnentdataMap = updateEvnentdataMap;
   }

   public UpdateEventLogsRequest() {
   }
}
