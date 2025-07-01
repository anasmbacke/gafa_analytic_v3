package com.gafapay.elasticsearch.api.reportexport.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonFieldAPIResponse;
import com.gafapay.elasticsearch.database.mongodb.ReportExportFiles;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class ReportExportFilesDetails extends CommonFieldAPIResponse {
   @JsonProperty("id")
   private String id;
   @JsonProperty("report_name")
   private String reportName;
   @JsonProperty("download_url")
   private String downloadUrl;
   @JsonProperty("file_format")
   private Integer fileFormat;
   @JsonProperty("filter_applied")
   private Map<String, Object> filterApplied;
   @JsonProperty("user_id")
   private String userId;
   @JsonProperty("user_type")
   private Integer userType;
   @JsonProperty("report_date")
   private Long reportDate;

   public ReportExportFilesDetails(ReportExportFiles reportExportFiles) {
      this.setId(reportExportFiles.getId());
      this.setReportName(reportExportFiles.getReportName());
      this.setDownloadUrl(reportExportFiles.getDownloadUrl());
      this.setFileFormat(reportExportFiles.getFileFormat());
      this.setFilterApplied(reportExportFiles.getFilterApplied());
      this.setUserId(reportExportFiles.getUserId());
      this.setUserType(reportExportFiles.getUserType());
      this.setReportDate(reportExportFiles.getReportDate());
      this.setCreatedBy(reportExportFiles.getCreatedBy());
      this.setUpdatedBy(reportExportFiles.getUpdatedBy());
      this.setIsActive(reportExportFiles.getIsActive());
      this.setCreatedDate(reportExportFiles.getCreatedDate());
      this.setUpdatedDate(reportExportFiles.getUpdatedDate());
   }

   public String getId() {
      return this.id;
   }

   public String getReportName() {
      return this.reportName;
   }

   public String getDownloadUrl() {
      return this.downloadUrl;
   }

   public Integer getFileFormat() {
      return this.fileFormat;
   }

   public Map<String, Object> getFilterApplied() {
      return this.filterApplied;
   }

   public String getUserId() {
      return this.userId;
   }

   public Integer getUserType() {
      return this.userType;
   }

   public Long getReportDate() {
      return this.reportDate;
   }

   @JsonProperty("id")
   public void setId(final String id) {
      this.id = id;
   }

   @JsonProperty("report_name")
   public void setReportName(final String reportName) {
      this.reportName = reportName;
   }

   @JsonProperty("download_url")
   public void setDownloadUrl(final String downloadUrl) {
      this.downloadUrl = downloadUrl;
   }

   @JsonProperty("file_format")
   public void setFileFormat(final Integer fileFormat) {
      this.fileFormat = fileFormat;
   }

   @JsonProperty("filter_applied")
   public void setFilterApplied(final Map<String, Object> filterApplied) {
      this.filterApplied = filterApplied;
   }

   @JsonProperty("user_id")
   public void setUserId(final String userId) {
      this.userId = userId;
   }

   @JsonProperty("user_type")
   public void setUserType(final Integer userType) {
      this.userType = userType;
   }

   @JsonProperty("report_date")
   public void setReportDate(final Long reportDate) {
      this.reportDate = reportDate;
   }

   public ReportExportFilesDetails() {
   }
}
