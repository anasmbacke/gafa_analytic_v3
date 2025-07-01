package com.gafapay.elasticsearch.api.reportexport.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class GetAllReportExportFilesResponse extends CommonAPIDataResponse {
   @JsonProperty("report_export_files")
   private List<ReportExportFilesDetails> reportExportFilesDetails;

   public List<ReportExportFilesDetails> getReportExportFilesDetails() {
      return this.reportExportFilesDetails;
   }

   @JsonProperty("report_export_files")
   public void setReportExportFilesDetails(final List<ReportExportFilesDetails> reportExportFilesDetails) {
      this.reportExportFilesDetails = reportExportFilesDetails;
   }

   public GetAllReportExportFilesResponse() {
   }
}
