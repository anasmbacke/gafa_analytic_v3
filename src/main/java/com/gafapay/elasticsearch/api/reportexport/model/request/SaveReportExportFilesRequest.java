package com.gafapay.elasticsearch.api.reportexport.model.request;

import com.gafapay.elasticsearch.api.commonrequest.CommonAPIDataRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class SaveReportExportFilesRequest extends CommonAPIDataRequest {
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

   public Boolean checkBadRequest() {
      return StringUtils.isEmpty(this.getCompany_id()) ? true : false;
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

   public SaveReportExportFilesRequest() {
   }
}
