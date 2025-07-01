package com.gafapay.elasticsearch.api.activitylogs.model;

import com.gafapay.elasticsearch.database.model.ElasticSearchCommonFieldModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(
   indexName = "activity_logs"
)
@JsonIgnoreProperties(
   ignoreUnknown = true
)
public class ActivityLogs extends ElasticSearchCommonFieldModel {
   @Id
   public String id;
   @Field(
      type = FieldType.Text,
      name = "log_type"
   )
   public String logType;
   @Field(
      type = FieldType.Text,
      name = "title"
   )
   public String title;
   @Field(
      type = FieldType.Text,
      name = "description"
   )
   public String description;
   @Field(
      type = FieldType.Integer,
      name = "user_type"
   )
   public Integer userType;
   @Field(
      type = FieldType.Text,
      name = "user_id"
   )
   public String userId;
   @Field(
      type = FieldType.Text,
      name = "txn_id"
   )
   public String txnId;
   @Field(
      type = FieldType.Long,
      name = "lat"
   )
   public Long lat;
   @Field(
      type = FieldType.Long,
      name = "lng"
   )
   public Long lng;
   @Field(
      type = FieldType.Text,
      name = "ip_address"
   )
   public String ipAddress;
   @Field(
      name = "device_info"
   )
   public Map<String, Object> deviceInfo;
   @Field(
      name = "meta_data"
   )
   public Map<String, Object> metaData;
   @Field(
      type = FieldType.Long,
      name = "log_date"
   )
   public Long logDate;

   protected ActivityLogs(final ActivityLogsBuilder<?, ?> b) {
      super(b);
      this.id = b.id;
      this.logType = b.logType;
      this.title = b.title;
      this.description = b.description;
      this.userType = b.userType;
      this.userId = b.userId;
      this.txnId = b.txnId;
      this.lat = b.lat;
      this.lng = b.lng;
      this.ipAddress = b.ipAddress;
      this.deviceInfo = b.deviceInfo;
      this.metaData = b.metaData;
      this.logDate = b.logDate;
   }

   public static ActivityLogsBuilder<?, ?> builder() {
      return new ActivityLogsBuilderImpl();
   }

   public ActivityLogs() {
   }

   public String getId() {
      return this.id;
   }

   public String getLogType() {
      return this.logType;
   }

   public String getTitle() {
      return this.title;
   }

   public String getDescription() {
      return this.description;
   }

   public Integer getUserType() {
      return this.userType;
   }

   public String getUserId() {
      return this.userId;
   }

   public String getTxnId() {
      return this.txnId;
   }

   public Long getLat() {
      return this.lat;
   }

   public Long getLng() {
      return this.lng;
   }

   public String getIpAddress() {
      return this.ipAddress;
   }

   public Map<String, Object> getDeviceInfo() {
      return this.deviceInfo;
   }

   public Map<String, Object> getMetaData() {
      return this.metaData;
   }

   public Long getLogDate() {
      return this.logDate;
   }

   public void setId(final String id) {
      this.id = id;
   }

   public void setLogType(final String logType) {
      this.logType = logType;
   }

   public void setTitle(final String title) {
      this.title = title;
   }

   public void setDescription(final String description) {
      this.description = description;
   }

   public void setUserType(final Integer userType) {
      this.userType = userType;
   }

   public void setUserId(final String userId) {
      this.userId = userId;
   }

   public void setTxnId(final String txnId) {
      this.txnId = txnId;
   }

   public void setLat(final Long lat) {
      this.lat = lat;
   }

   public void setLng(final Long lng) {
      this.lng = lng;
   }

   public void setIpAddress(final String ipAddress) {
      this.ipAddress = ipAddress;
   }

   public void setDeviceInfo(final Map<String, Object> deviceInfo) {
      this.deviceInfo = deviceInfo;
   }

   public void setMetaData(final Map<String, Object> metaData) {
      this.metaData = metaData;
   }

   public void setLogDate(final Long logDate) {
      this.logDate = logDate;
   }

   public abstract static class ActivityLogsBuilder<C extends ActivityLogs, B extends ActivityLogsBuilder<C, B>> extends ElasticSearchCommonFieldModel.ElasticSearchCommonFieldModelBuilder<C, B> {
      private String id;
      private String logType;
      private String title;
      private String description;
      private Integer userType;
      private String userId;
      private String txnId;
      private Long lat;
      private Long lng;
      private String ipAddress;
      private Map<String, Object> deviceInfo;
      private Map<String, Object> metaData;
      private Long logDate;

      public ActivityLogsBuilder() {
      }

      protected abstract B self();

      public abstract C build();

      public B id(final String id) {
         this.id = id;
         return (B)this.self();
      }

      public B logType(final String logType) {
         this.logType = logType;
         return (B)this.self();
      }

      public B title(final String title) {
         this.title = title;
         return (B)this.self();
      }

      public B description(final String description) {
         this.description = description;
         return (B)this.self();
      }

      public B userType(final Integer userType) {
         this.userType = userType;
         return (B)this.self();
      }

      public B userId(final String userId) {
         this.userId = userId;
         return (B)this.self();
      }

      public B txnId(final String txnId) {
         this.txnId = txnId;
         return (B)this.self();
      }

      public B lat(final Long lat) {
         this.lat = lat;
         return (B)this.self();
      }

      public B lng(final Long lng) {
         this.lng = lng;
         return (B)this.self();
      }

      public B ipAddress(final String ipAddress) {
         this.ipAddress = ipAddress;
         return (B)this.self();
      }

      public B deviceInfo(final Map<String, Object> deviceInfo) {
         this.deviceInfo = deviceInfo;
         return (B)this.self();
      }

      public B metaData(final Map<String, Object> metaData) {
         this.metaData = metaData;
         return (B)this.self();
      }

      public B logDate(final Long logDate) {
         this.logDate = logDate;
         return (B)this.self();
      }

      public String toString() {
         String var10000 = super.toString();
         return "ActivityLogs.ActivityLogsBuilder(super=" + var10000 + ", id=" + this.id + ", logType=" + this.logType + ", title=" + this.title + ", description=" + this.description + ", userType=" + this.userType + ", userId=" + this.userId + ", txnId=" + this.txnId + ", lat=" + this.lat + ", lng=" + this.lng + ", ipAddress=" + this.ipAddress + ", deviceInfo=" + this.deviceInfo + ", metaData=" + this.metaData + ", logDate=" + this.logDate + ")";
      }

      // $FF: synthetic method
      // $FF: bridge method
      public ElasticSearchCommonFieldModel build() {
         return this.build();
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected ElasticSearchCommonFieldModel.ElasticSearchCommonFieldModelBuilder self() {
         return this.self();
      }
   }

   private static final class ActivityLogsBuilderImpl extends ActivityLogsBuilder<ActivityLogs, ActivityLogsBuilderImpl> {
      private ActivityLogsBuilderImpl() {
      }

      protected ActivityLogsBuilderImpl self() {
         return this;
      }

      public ActivityLogs build() {
         return new ActivityLogs(this);
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected ActivityLogsBuilder self() {
         return this.self();
      }

      // $FF: synthetic method
      // $FF: bridge method
      public ElasticSearchCommonFieldModel build() {
         return this.build();
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected ElasticSearchCommonFieldModel.ElasticSearchCommonFieldModelBuilder self() {
         return this.self();
      }
   }
}
