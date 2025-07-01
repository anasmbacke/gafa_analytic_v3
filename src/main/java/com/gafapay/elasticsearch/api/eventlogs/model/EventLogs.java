package com.gafapay.elasticsearch.api.eventlogs.model;

import com.gafapay.elasticsearch.database.model.CommonFieldModel;
import com.gafapay.elasticsearch.utils.Utils;
import java.util.Map;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(
   collection = "eventlogs"
)
public class EventLogs extends CommonFieldModel {
   @Field("id")
   private String id;
   @Field("event_name")
   private String eventName;
   @Field("event_type")
   private Integer eventType;
   @Field("event_date")
   private Long eventDate;
   @Field("old_value")
   private Map<String, Object> oldValue;
   @Field("new_value")
   private Map<String, Object> newValue;
   @Field("event_log_number")
   private String eventLogNumber;
   @Field("object_type")
   private String objectType;
   @Field("object_type_id")
   private String ObjectTypeId;

   private static String $default$id() {
      return Utils.generateUUID();
   }

   protected EventLogs(final EventLogsBuilder<?, ?> b) {
      super(b);
      if (b.id$set) {
         this.id = b.id$value;
      } else {
         this.id = $default$id();
      }

      this.eventName = b.eventName;
      this.eventType = b.eventType;
      this.eventDate = b.eventDate;
      this.oldValue = b.oldValue;
      this.newValue = b.newValue;
      this.eventLogNumber = b.eventLogNumber;
      this.objectType = b.objectType;
      this.ObjectTypeId = b.ObjectTypeId;
   }

   public static EventLogsBuilder<?, ?> builder() {
      return new EventLogsBuilderImpl();
   }

   public String getId() {
      return this.id;
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

   public void setId(final String id) {
      this.id = id;
   }

   public void setEventName(final String eventName) {
      this.eventName = eventName;
   }

   public void setEventType(final Integer eventType) {
      this.eventType = eventType;
   }

   public void setEventDate(final Long eventDate) {
      this.eventDate = eventDate;
   }

   public void setOldValue(final Map<String, Object> oldValue) {
      this.oldValue = oldValue;
   }

   public void setNewValue(final Map<String, Object> newValue) {
      this.newValue = newValue;
   }

   public void setEventLogNumber(final String eventLogNumber) {
      this.eventLogNumber = eventLogNumber;
   }

   public void setObjectType(final String objectType) {
      this.objectType = objectType;
   }

   public void setObjectTypeId(final String ObjectTypeId) {
      this.ObjectTypeId = ObjectTypeId;
   }

   public EventLogs() {
      this.id = $default$id();
   }

   public abstract static class EventLogsBuilder<C extends EventLogs, B extends EventLogsBuilder<C, B>> extends CommonFieldModel.CommonFieldModelBuilder<C, B> {
      private boolean id$set;
      private String id$value;
      private String eventName;
      private Integer eventType;
      private Long eventDate;
      private Map<String, Object> oldValue;
      private Map<String, Object> newValue;
      private String eventLogNumber;
      private String objectType;
      private String ObjectTypeId;

      public EventLogsBuilder() {
      }

      protected abstract B self();

      public abstract C build();

      public B id(final String id) {
         this.id$value = id;
         this.id$set = true;
         return (B)this.self();
      }

      public B eventName(final String eventName) {
         this.eventName = eventName;
         return (B)this.self();
      }

      public B eventType(final Integer eventType) {
         this.eventType = eventType;
         return (B)this.self();
      }

      public B eventDate(final Long eventDate) {
         this.eventDate = eventDate;
         return (B)this.self();
      }

      public B oldValue(final Map<String, Object> oldValue) {
         this.oldValue = oldValue;
         return (B)this.self();
      }

      public B newValue(final Map<String, Object> newValue) {
         this.newValue = newValue;
         return (B)this.self();
      }

      public B eventLogNumber(final String eventLogNumber) {
         this.eventLogNumber = eventLogNumber;
         return (B)this.self();
      }

      public B objectType(final String objectType) {
         this.objectType = objectType;
         return (B)this.self();
      }

      public B ObjectTypeId(final String ObjectTypeId) {
         this.ObjectTypeId = ObjectTypeId;
         return (B)this.self();
      }

      public String toString() {
         String var10000 = super.toString();
         return "EventLogs.EventLogsBuilder(super=" + var10000 + ", id$value=" + this.id$value + ", eventName=" + this.eventName + ", eventType=" + this.eventType + ", eventDate=" + this.eventDate + ", oldValue=" + this.oldValue + ", newValue=" + this.newValue + ", eventLogNumber=" + this.eventLogNumber + ", objectType=" + this.objectType + ", ObjectTypeId=" + this.ObjectTypeId + ")";
      }

      // $FF: synthetic method
      // $FF: bridge method
      public CommonFieldModel build() {
         return this.build();
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected CommonFieldModel.CommonFieldModelBuilder self() {
         return this.self();
      }
   }

   private static final class EventLogsBuilderImpl extends EventLogsBuilder<EventLogs, EventLogsBuilderImpl> {
      private EventLogsBuilderImpl() {
      }

      protected EventLogsBuilderImpl self() {
         return this;
      }

      public EventLogs build() {
         return new EventLogs(this);
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected EventLogsBuilder self() {
         return this.self();
      }

      // $FF: synthetic method
      // $FF: bridge method
      public CommonFieldModel build() {
         return this.build();
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected CommonFieldModel.CommonFieldModelBuilder self() {
         return this.self();
      }
   }
}
