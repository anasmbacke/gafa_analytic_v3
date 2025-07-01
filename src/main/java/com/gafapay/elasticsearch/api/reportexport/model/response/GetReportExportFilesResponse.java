package com.gafapay.elasticsearch.api.reportexport.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetReportExportFilesResponse extends CommonAPIDataResponse {
   @JsonProperty("report_export_files")
   private ReportExportFilesDetails reportExportFilesDetails;

   public ReportExportFilesDetails getReportExportFilesDetails() {
      return this.reportExportFilesDetails;
   }

   @JsonProperty("report_export_files")
   public void setReportExportFilesDetails(final ReportExportFilesDetails reportExportFilesDetails) {
      this.reportExportFilesDetails = reportExportFilesDetails;
   }

   public GetReportExportFilesResponse() {
   }
}
