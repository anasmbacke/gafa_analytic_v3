package com.gafapay.elasticsearch.api.reportexport.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SaveReportExportFilesResponse extends CommonAPIDataResponse {
   @JsonProperty("report_export_files_id")
   private String Id;

   public String getId() {
      return this.Id;
   }

   @JsonProperty("report_export_files_id")
   public void setId(final String Id) {
      this.Id = Id;
   }

   public SaveReportExportFilesResponse() {
   }
}
