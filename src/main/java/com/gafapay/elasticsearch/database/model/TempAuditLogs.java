package com.gafapay.elasticsearch.database.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(
   indexName = "temp_audit_logs_details"
)
@JsonIgnoreProperties(
   ignoreUnknown = true
)
public class TempAuditLogs extends ElasticSearchCommonFieldModel {
   @Id
   @Field("_id")
   public String id;
   @Field("title")
   private String title;
   @Field("user_id")
   private String userId;
   @Field("user_type")
   private Integer userType;
   @Field("reason")
   private String reason;
   @Field("device_info")
   private Map<String, Object> deviceInfo;
   @Field("reference_id")
   private String referenceId;

   protected TempAuditLogs(final TempAuditLogsBuilder<?, ?> b) {
      super(b);
      this.id = b.id;
      this.title = b.title;
      this.userId = b.userId;
      this.userType = b.userType;
      this.reason = b.reason;
      this.deviceInfo = b.deviceInfo;
      this.referenceId = b.referenceId;
   }

   public static TempAuditLogsBuilder<?, ?> builder() {
      return new TempAuditLogsBuilderImpl();
   }

   public String getId() {
      return this.id;
   }

   public String getTitle() {
      return this.title;
   }

   public String getUserId() {
      return this.userId;
   }

   public Integer getUserType() {
      return this.userType;
   }

   public String getReason() {
      return this.reason;
   }

   public Map<String, Object> getDeviceInfo() {
      return this.deviceInfo;
   }

   public String getReferenceId() {
      return this.referenceId;
   }

   public void setId(final String id) {
      this.id = id;
   }

   public void setTitle(final String title) {
      this.title = title;
   }

   public void setUserId(final String userId) {
      this.userId = userId;
   }

   public void setUserType(final Integer userType) {
      this.userType = userType;
   }

   public void setReason(final String reason) {
      this.reason = reason;
   }

   public void setDeviceInfo(final Map<String, Object> deviceInfo) {
      this.deviceInfo = deviceInfo;
   }

   public void setReferenceId(final String referenceId) {
      this.referenceId = referenceId;
   }

   public TempAuditLogs() {
   }

   public abstract static class TempAuditLogsBuilder<C extends TempAuditLogs, B extends TempAuditLogsBuilder<C, B>> extends ElasticSearchCommonFieldModel.ElasticSearchCommonFieldModelBuilder<C, B> {
      private String id;
      private String title;
      private String userId;
      private Integer userType;
      private String reason;
      private Map<String, Object> deviceInfo;
      private String referenceId;

      public TempAuditLogsBuilder() {
      }

      protected abstract B self();

      public abstract C build();

      public B id(final String id) {
         this.id = id;
         return (B)this.self();
      }

      public B title(final String title) {
         this.title = title;
         return (B)this.self();
      }

      public B userId(final String userId) {
         this.userId = userId;
         return (B)this.self();
      }

      public B userType(final Integer userType) {
         this.userType = userType;
         return (B)this.self();
      }

      public B reason(final String reason) {
         this.reason = reason;
         return (B)this.self();
      }

      public B deviceInfo(final Map<String, Object> deviceInfo) {
         this.deviceInfo = deviceInfo;
         return (B)this.self();
      }

      public B referenceId(final String referenceId) {
         this.referenceId = referenceId;
         return (B)this.self();
      }

      public String toString() {
         String var10000 = super.toString();
         return "TempAuditLogs.TempAuditLogsBuilder(super=" + var10000 + ", id=" + this.id + ", title=" + this.title + ", userId=" + this.userId + ", userType=" + this.userType + ", reason=" + this.reason + ", deviceInfo=" + this.deviceInfo + ", referenceId=" + this.referenceId + ")";
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

   private static final class TempAuditLogsBuilderImpl extends TempAuditLogsBuilder<TempAuditLogs, TempAuditLogsBuilderImpl> {
      private TempAuditLogsBuilderImpl() {
      }

      protected TempAuditLogsBuilderImpl self() {
         return this;
      }

      public TempAuditLogs build() {
         return new TempAuditLogs(this);
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected TempAuditLogsBuilder self() {
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
