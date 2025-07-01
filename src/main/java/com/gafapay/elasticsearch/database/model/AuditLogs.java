package com.gafapay.elasticsearch.database.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(
   indexName = "audit_logs"
)
@JsonIgnoreProperties(
   ignoreUnknown = true
)
public class AuditLogs extends ElasticSearchCommonFieldModel {
   @Id
   @Field("id")
   public String id;
   @Field(
      name = "table_name"
   )
   private String tableName;
   @Field(
      name = "action"
   )
   private Integer action;
   @Field(
      name = "action_date"
   )
   private Long actionDate;
   @Field(
      name = "old_data"
   )
   private Map<String, Object> oldData;
   @Field(
      name = "new_data"
   )
   public Map<String, Object> newData;
   @Field(
      name = "database_name"
   )
   public String databaseName;
   @Field(
      name = "changed_data"
   )
   public Map<String, Object> changedData;
   @Field(
      name = "audit_log_number"
   )
   public String auditLogNumber;
   @Field(
      name = "resource_name"
   )
   public String resourceName;

   protected AuditLogs(final AuditLogsBuilder<?, ?> b) {
      super(b);
      this.id = b.id;
      this.tableName = b.tableName;
      this.action = b.action;
      this.actionDate = b.actionDate;
      this.oldData = b.oldData;
      this.newData = b.newData;
      this.databaseName = b.databaseName;
      this.changedData = b.changedData;
      this.auditLogNumber = b.auditLogNumber;
      this.resourceName = b.resourceName;
   }

   public static AuditLogsBuilder<?, ?> builder() {
      return new AuditLogsBuilderImpl();
   }

   public String getId() {
      return this.id;
   }

   public String getTableName() {
      return this.tableName;
   }

   public Integer getAction() {
      return this.action;
   }

   public Long getActionDate() {
      return this.actionDate;
   }

   public Map<String, Object> getOldData() {
      return this.oldData;
   }

   public Map<String, Object> getNewData() {
      return this.newData;
   }

   public String getDatabaseName() {
      return this.databaseName;
   }

   public Map<String, Object> getChangedData() {
      return this.changedData;
   }

   public String getAuditLogNumber() {
      return this.auditLogNumber;
   }

   public String getResourceName() {
      return this.resourceName;
   }

   public void setId(final String id) {
      this.id = id;
   }

   public void setTableName(final String tableName) {
      this.tableName = tableName;
   }

   public void setAction(final Integer action) {
      this.action = action;
   }

   public void setActionDate(final Long actionDate) {
      this.actionDate = actionDate;
   }

   public void setOldData(final Map<String, Object> oldData) {
      this.oldData = oldData;
   }

   public void setNewData(final Map<String, Object> newData) {
      this.newData = newData;
   }

   public void setDatabaseName(final String databaseName) {
      this.databaseName = databaseName;
   }

   public void setChangedData(final Map<String, Object> changedData) {
      this.changedData = changedData;
   }

   public void setAuditLogNumber(final String auditLogNumber) {
      this.auditLogNumber = auditLogNumber;
   }

   public void setResourceName(final String resourceName) {
      this.resourceName = resourceName;
   }

   public AuditLogs() {
   }

   public abstract static class AuditLogsBuilder<C extends AuditLogs, B extends AuditLogsBuilder<C, B>> extends ElasticSearchCommonFieldModel.ElasticSearchCommonFieldModelBuilder<C, B> {
      private String id;
      private String tableName;
      private Integer action;
      private Long actionDate;
      private Map<String, Object> oldData;
      private Map<String, Object> newData;
      private String databaseName;
      private Map<String, Object> changedData;
      private String auditLogNumber;
      private String resourceName;

      public AuditLogsBuilder() {
      }

      protected abstract B self();

      public abstract C build();

      public B id(final String id) {
         this.id = id;
         return (B)this.self();
      }

      public B tableName(final String tableName) {
         this.tableName = tableName;
         return (B)this.self();
      }

      public B action(final Integer action) {
         this.action = action;
         return (B)this.self();
      }

      public B actionDate(final Long actionDate) {
         this.actionDate = actionDate;
         return (B)this.self();
      }

      public B oldData(final Map<String, Object> oldData) {
         this.oldData = oldData;
         return (B)this.self();
      }

      public B newData(final Map<String, Object> newData) {
         this.newData = newData;
         return (B)this.self();
      }

      public B databaseName(final String databaseName) {
         this.databaseName = databaseName;
         return (B)this.self();
      }

      public B changedData(final Map<String, Object> changedData) {
         this.changedData = changedData;
         return (B)this.self();
      }

      public B auditLogNumber(final String auditLogNumber) {
         this.auditLogNumber = auditLogNumber;
         return (B)this.self();
      }

      public B resourceName(final String resourceName) {
         this.resourceName = resourceName;
         return (B)this.self();
      }

      public String toString() {
         String var10000 = super.toString();
         return "AuditLogs.AuditLogsBuilder(super=" + var10000 + ", id=" + this.id + ", tableName=" + this.tableName + ", action=" + this.action + ", actionDate=" + this.actionDate + ", oldData=" + this.oldData + ", newData=" + this.newData + ", databaseName=" + this.databaseName + ", changedData=" + this.changedData + ", auditLogNumber=" + this.auditLogNumber + ", resourceName=" + this.resourceName + ")";
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

   private static final class AuditLogsBuilderImpl extends AuditLogsBuilder<AuditLogs, AuditLogsBuilderImpl> {
      private AuditLogsBuilderImpl() {
      }

      protected AuditLogsBuilderImpl self() {
         return this;
      }

      public AuditLogs build() {
         return new AuditLogs(this);
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected AuditLogsBuilder self() {
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
