package com.gafapay.elasticsearch.api.auditlogs.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.gafapay.elasticsearch.database.model.AuditLogs;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class AuditLogsData extends CommonAPIDataResponse {
   @JsonProperty("id")
   private String id;
   @JsonProperty("table_name")
   private String tableName;
   @JsonProperty("action")
   private Integer action;
   @JsonProperty("action_date")
   private Long actionDate;
   @JsonProperty("old_data")
   private Map<String, Object> oldData;
   @JsonProperty("new_data")
   public Map<String, Object> newData;
   @JsonProperty("database_name")
   public String databaseName;
   @JsonProperty("changed_data")
   public Map<String, Object> changedData;
   @JsonProperty("audit_log_number")
   public String auditLogNumber;
   @JsonProperty("resource_name")
   public String resourceName;
   @JsonProperty("is_active")
   private Boolean isActive;
   @JsonProperty("created_by")
   private String createdBy;
   @JsonProperty("created_date")
   private Long createdDate;

   public static AuditLogsData setAuditLogs(AuditLogs auditLogs) {
      AuditLogsData auditLogsData = new AuditLogsData();
      auditLogsData.setId(auditLogs.getId());
      auditLogsData.setTableName(auditLogs.getTableName());
      auditLogsData.setAction(auditLogs.getAction());
      auditLogsData.setActionDate(auditLogs.getActionDate());
      auditLogsData.setOldData(auditLogs.getOldData());
      auditLogsData.setNewData(auditLogs.getNewData());
      auditLogsData.setDatabaseName(auditLogs.getDatabaseName());
      auditLogsData.setChangedData(auditLogs.getChangedData());
      auditLogsData.setAuditLogNumber(auditLogs.getAuditLogNumber());
      auditLogsData.setResourceName(auditLogs.getResourceName());
      auditLogsData.setIsActive(auditLogs.getIsActive());
      auditLogsData.setCreatedDate(auditLogs.getCreatedDate());
      auditLogsData.setCreatedBy(auditLogs.getCreatedBy());
      return auditLogsData;
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

   public Boolean getIsActive() {
      return this.isActive;
   }

   public String getCreatedBy() {
      return this.createdBy;
   }

   public Long getCreatedDate() {
      return this.createdDate;
   }

   @JsonProperty("id")
   public void setId(final String id) {
      this.id = id;
   }

   @JsonProperty("table_name")
   public void setTableName(final String tableName) {
      this.tableName = tableName;
   }

   @JsonProperty("action")
   public void setAction(final Integer action) {
      this.action = action;
   }

   @JsonProperty("action_date")
   public void setActionDate(final Long actionDate) {
      this.actionDate = actionDate;
   }

   @JsonProperty("old_data")
   public void setOldData(final Map<String, Object> oldData) {
      this.oldData = oldData;
   }

   @JsonProperty("new_data")
   public void setNewData(final Map<String, Object> newData) {
      this.newData = newData;
   }

   @JsonProperty("database_name")
   public void setDatabaseName(final String databaseName) {
      this.databaseName = databaseName;
   }

   @JsonProperty("changed_data")
   public void setChangedData(final Map<String, Object> changedData) {
      this.changedData = changedData;
   }

   @JsonProperty("audit_log_number")
   public void setAuditLogNumber(final String auditLogNumber) {
      this.auditLogNumber = auditLogNumber;
   }

   @JsonProperty("resource_name")
   public void setResourceName(final String resourceName) {
      this.resourceName = resourceName;
   }

   @JsonProperty("is_active")
   public void setIsActive(final Boolean isActive) {
      this.isActive = isActive;
   }

   @JsonProperty("created_by")
   public void setCreatedBy(final String createdBy) {
      this.createdBy = createdBy;
   }

   @JsonProperty("created_date")
   public void setCreatedDate(final Long createdDate) {
      this.createdDate = createdDate;
   }

   public AuditLogsData() {
   }
}
