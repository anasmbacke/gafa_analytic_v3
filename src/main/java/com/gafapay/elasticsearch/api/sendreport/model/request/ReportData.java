package com.gafapay.elasticsearch.api.sendreport.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ReportData {
   @JsonProperty("headers")
   private List<String> headers;
   @JsonProperty("data")
   private List<List<String>> data;
   @JsonProperty("company_name")
   private String companyName;
   @JsonProperty("report_name")
   private String reportName;
   @JsonProperty("report_type")
   private Integer reportType;
   @JsonProperty("title_text")
   private String titleText;
   @JsonProperty("start_date")
   private String startDate;
   @JsonProperty("end_date")
   private String endDate;
   @JsonProperty("company_logo_url")
   private String companyLogoUrl;

   public List<String> getHeaders() {
      return this.headers;
   }

   public List<List<String>> getData() {
      return this.data;
   }

   public String getCompanyName() {
      return this.companyName;
   }

   public String getReportName() {
      return this.reportName;
   }

   public Integer getReportType() {
      return this.reportType;
   }

   public String getTitleText() {
      return this.titleText;
   }

   public String getStartDate() {
      return this.startDate;
   }

   public String getEndDate() {
      return this.endDate;
   }

   public String getCompanyLogoUrl() {
      return this.companyLogoUrl;
   }

   @JsonProperty("headers")
   public void setHeaders(final List<String> headers) {
      this.headers = headers;
   }

   @JsonProperty("data")
   public void setData(final List<List<String>> data) {
      this.data = data;
   }

   @JsonProperty("company_name")
   public void setCompanyName(final String companyName) {
      this.companyName = companyName;
   }

   @JsonProperty("report_name")
   public void setReportName(final String reportName) {
      this.reportName = reportName;
   }

   @JsonProperty("report_type")
   public void setReportType(final Integer reportType) {
      this.reportType = reportType;
   }

   @JsonProperty("title_text")
   public void setTitleText(final String titleText) {
      this.titleText = titleText;
   }

   @JsonProperty("start_date")
   public void setStartDate(final String startDate) {
      this.startDate = startDate;
   }

   @JsonProperty("end_date")
   public void setEndDate(final String endDate) {
      this.endDate = endDate;
   }

   @JsonProperty("company_logo_url")
   public void setCompanyLogoUrl(final String companyLogoUrl) {
      this.companyLogoUrl = companyLogoUrl;
   }

   public boolean equals(final Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ReportData)) {
         return false;
      } else {
         ReportData other = (ReportData)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$reportType = this.getReportType();
            Object other$reportType = other.getReportType();
            if (this$reportType == null) {
               if (other$reportType != null) {
                  return false;
               }
            } else if (!this$reportType.equals(other$reportType)) {
               return false;
            }

            Object this$headers = this.getHeaders();
            Object other$headers = other.getHeaders();
            if (this$headers == null) {
               if (other$headers != null) {
                  return false;
               }
            } else if (!this$headers.equals(other$headers)) {
               return false;
            }

            Object this$data = this.getData();
            Object other$data = other.getData();
            if (this$data == null) {
               if (other$data != null) {
                  return false;
               }
            } else if (!this$data.equals(other$data)) {
               return false;
            }

            Object this$companyName = this.getCompanyName();
            Object other$companyName = other.getCompanyName();
            if (this$companyName == null) {
               if (other$companyName != null) {
                  return false;
               }
            } else if (!this$companyName.equals(other$companyName)) {
               return false;
            }

            Object this$reportName = this.getReportName();
            Object other$reportName = other.getReportName();
            if (this$reportName == null) {
               if (other$reportName != null) {
                  return false;
               }
            } else if (!this$reportName.equals(other$reportName)) {
               return false;
            }

            Object this$titleText = this.getTitleText();
            Object other$titleText = other.getTitleText();
            if (this$titleText == null) {
               if (other$titleText != null) {
                  return false;
               }
            } else if (!this$titleText.equals(other$titleText)) {
               return false;
            }

            Object this$startDate = this.getStartDate();
            Object other$startDate = other.getStartDate();
            if (this$startDate == null) {
               if (other$startDate != null) {
                  return false;
               }
            } else if (!this$startDate.equals(other$startDate)) {
               return false;
            }

            Object this$endDate = this.getEndDate();
            Object other$endDate = other.getEndDate();
            if (this$endDate == null) {
               if (other$endDate != null) {
                  return false;
               }
            } else if (!this$endDate.equals(other$endDate)) {
               return false;
            }

            Object this$companyLogoUrl = this.getCompanyLogoUrl();
            Object other$companyLogoUrl = other.getCompanyLogoUrl();
            if (this$companyLogoUrl == null) {
               if (other$companyLogoUrl != null) {
                  return false;
               }
            } else if (!this$companyLogoUrl.equals(other$companyLogoUrl)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(final Object other) {
      return other instanceof ReportData;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $reportType = this.getReportType();
      result = result * 59 + ($reportType == null ? 43 : $reportType.hashCode());
      Object $headers = this.getHeaders();
      result = result * 59 + ($headers == null ? 43 : $headers.hashCode());
      Object $data = this.getData();
      result = result * 59 + ($data == null ? 43 : $data.hashCode());
      Object $companyName = this.getCompanyName();
      result = result * 59 + ($companyName == null ? 43 : $companyName.hashCode());
      Object $reportName = this.getReportName();
      result = result * 59 + ($reportName == null ? 43 : $reportName.hashCode());
      Object $titleText = this.getTitleText();
      result = result * 59 + ($titleText == null ? 43 : $titleText.hashCode());
      Object $startDate = this.getStartDate();
      result = result * 59 + ($startDate == null ? 43 : $startDate.hashCode());
      Object $endDate = this.getEndDate();
      result = result * 59 + ($endDate == null ? 43 : $endDate.hashCode());
      Object $companyLogoUrl = this.getCompanyLogoUrl();
      result = result * 59 + ($companyLogoUrl == null ? 43 : $companyLogoUrl.hashCode());
      return result;
   }

   public String toString() {
      List var10000 = this.getHeaders();
      return "ReportData(headers=" + var10000 + ", data=" + this.getData() + ", companyName=" + this.getCompanyName() + ", reportName=" + this.getReportName() + ", reportType=" + this.getReportType() + ", titleText=" + this.getTitleText() + ", startDate=" + this.getStartDate() + ", endDate=" + this.getEndDate() + ", companyLogoUrl=" + this.getCompanyLogoUrl() + ")";
   }

   public ReportData() {
   }

   public ReportData(final List<String> headers, final List<List<String>> data, final String companyName, final String reportName, final Integer reportType, final String titleText, final String startDate, final String endDate, final String companyLogoUrl) {
      this.headers = headers;
      this.data = data;
      this.companyName = companyName;
      this.reportName = reportName;
      this.reportType = reportType;
      this.titleText = titleText;
      this.startDate = startDate;
      this.endDate = endDate;
      this.companyLogoUrl = companyLogoUrl;
   }
}
