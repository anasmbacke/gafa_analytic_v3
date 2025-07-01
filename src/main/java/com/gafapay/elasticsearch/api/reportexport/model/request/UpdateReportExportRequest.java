package com.gafapay.elasticsearch.api.reportexport.model.request;

import com.gafapay.elasticsearch.api.commonrequest.CommonAPIDataRequest;
import java.util.Map;
import java.util.Objects;

public class UpdateReportExportRequest extends CommonAPIDataRequest {
   private Map<String, Object> reportExportFilesMap;

   public boolean checkBadRequest() {
      if (!Objects.isNull(this.getReportExportFilesMap()) && !this.getReportExportFilesMap().isEmpty()) {
         return !this.getReportExportFilesMap().containsKey("id") || Objects.isNull(this.getReportExportFilesMap().get("id"));
      } else {
         return true;
      }
   }

   public Map<String, Object> getReportExportFilesMap() {
      return this.reportExportFilesMap;
   }

   public void setReportExportFilesMap(final Map<String, Object> reportExportFilesMap) {
      this.reportExportFilesMap = reportExportFilesMap;
   }

   public UpdateReportExportRequest() {
   }
}
