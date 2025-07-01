package com.gafapay.elasticsearch.api.eventlogs.model.request;

import com.gafapay.elasticsearch.api.commonrequest.CommonAPIDataRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class SaveEventLogsRequest extends CommonAPIDataRequest {
   @JsonProperty("event_name")
   private String eventName;
   @JsonProperty("event_type")
   private Integer eventType;
   @JsonProperty("event_date")
   private Long eventDate;
   @JsonProperty("old_value")
   private Map<String, Object> oldValue;
   @JsonProperty("new_value")
   private Map<String, Object> newValue;
   @JsonProperty("event_log_number")
   private String eventLogNumber;
   @JsonProperty("object_type")
   private String objectType;
   @JsonProperty("object_type_id")
   private String ObjectTypeId;

   public Boolean checkBadRequest() {
      return StringUtils.isEmpty(this.getCompany_id()) ? true : false;
   }

   public String getEventName() {
      return this.eventName;
   }

   public Integer getEventType() {
      return this.eventType;
   }

   public Long getEventDate() {
      return this.eventDate;
   }

   public Map<String, Object> getOldValue() {
      return this.oldValue;
   }

   public Map<String, Object> getNewValue() {
      return this.newValue;
   }

   public String getEventLogNumber() {
      return this.eventLogNumber;
   }

   public String getObjectType() {
      return this.objectType;
   }

   public String getObjectTypeId() {
      return this.ObjectTypeId;
   }

   @JsonProperty("event_name")
   public void setEventName(final String eventName) {
      this.eventName = eventName;
   }

   @JsonProperty("event_type")
   public void setEventType(final Integer eventType) {
      this.eventType = eventType;
   }

   @JsonProperty("event_date")
   public void setEventDate(final Long eventDate) {
      this.eventDate = eventDate;
   }

   @JsonProperty("old_value")
   public void setOldValue(final Map<String, Object> oldValue) {
      this.oldValue = oldValue;
   }

   @JsonProperty("new_value")
   public void setNewValue(final Map<String, Object> newValue) {
      this.newValue = newValue;
   }

   @JsonProperty("event_log_number")
   public void setEventLogNumber(final String eventLogNumber) {
      this.eventLogNumber = eventLogNumber;
   }

   @JsonProperty("object_type")
   public void setObjectType(final String objectType) {
      this.objectType = objectType;
   }

   @JsonProperty("object_type_id")
   public void setObjectTypeId(final String ObjectTypeId) {
      this.ObjectTypeId = ObjectTypeId;
   }

   public SaveEventLogsRequest() {
   }
}
