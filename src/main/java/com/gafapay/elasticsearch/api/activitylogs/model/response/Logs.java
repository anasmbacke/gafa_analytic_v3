package com.gafapay.elasticsearch.api.activitylogs.model.response;

import com.gafapay.elasticsearch.api.activitylogs.model.ActivityLogs;
import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class Logs extends CommonAPIDataResponse {
   @JsonProperty("id")
   private String id;
   @JsonProperty("log_type")
   private String logType;
   @JsonProperty("title")
   private String title;
   @JsonProperty("description")
   private String description;
   @JsonProperty("user_id")
   private String userId;
   @JsonProperty("user_type")
   private Integer userType;
   @JsonProperty("txn_id")
   private String txnId;
   @JsonProperty("lat")
   private Long lat;
   @JsonProperty("lng")
   private Long lng;
   @JsonProperty("ip_address")
   private String ipAddress;
   @JsonProperty("device_info")
   private Map<String, Object> deviceInfo;
   @JsonProperty("meta_data")
   private Map<String, Object> metaData;
   @JsonProperty("log_date")
   private Long logDate;

   public static Logs setActivityLogs(ActivityLogs activityLog) {
      Logs logs = new Logs();
      logs.setId(activityLog.getId());
      logs.setLogType(activityLog.getLogType());
      logs.setTitle(activityLog.getTitle());
      logs.setDescription(activityLog.getDescription());
      logs.setUserId(activityLog.getUserId());
      logs.setTxnId(activityLog.getTxnId());
      logs.setLat(activityLog.getLat());
      logs.setLng(activityLog.getLng());
      logs.setIpAddress(activityLog.getIpAddress());
      logs.setDeviceInfo(activityLog.getDeviceInfo());
      logs.setMetaData(activityLog.getMetaData());
      logs.setLogDate(activityLog.getLogDate());
      logs.setUserType(activityLog.getUserType());
      return logs;
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

   public String getUserId() {
      return this.userId;
   }

   public Integer getUserType() {
      return this.userType;
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

   @JsonProperty("id")
   public void setId(final String id) {
      this.id = id;
   }

   @JsonProperty("log_type")
   public void setLogType(final String logType) {
      this.logType = logType;
   }

   @JsonProperty("title")
   public void setTitle(final String title) {
      this.title = title;
   }

   @JsonProperty("description")
   public void setDescription(final String description) {
      this.description = description;
   }

   @JsonProperty("user_id")
   public void setUserId(final String userId) {
      this.userId = userId;
   }

   @JsonProperty("user_type")
   public void setUserType(final Integer userType) {
      this.userType = userType;
   }

   @JsonProperty("txn_id")
   public void setTxnId(final String txnId) {
      this.txnId = txnId;
   }

   @JsonProperty("lat")
   public void setLat(final Long lat) {
      this.lat = lat;
   }

   @JsonProperty("lng")
   public void setLng(final Long lng) {
      this.lng = lng;
   }

   @JsonProperty("ip_address")
   public void setIpAddress(final String ipAddress) {
      this.ipAddress = ipAddress;
   }

   @JsonProperty("device_info")
   public void setDeviceInfo(final Map<String, Object> deviceInfo) {
      this.deviceInfo = deviceInfo;
   }

   @JsonProperty("meta_data")
   public void setMetaData(final Map<String, Object> metaData) {
      this.metaData = metaData;
   }

   @JsonProperty("log_date")
   public void setLogDate(final Long logDate) {
      this.logDate = logDate;
   }

   public Logs() {
   }
}
