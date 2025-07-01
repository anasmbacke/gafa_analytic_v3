package com.gafapay.elasticsearch.api.nodalreconciliationreport.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class NodalReconciliationReportResponse extends CommonAPIDataResponse {
   @JsonProperty("nodal_reconciliation")
   private List<Map<String, Object>> nodalReconciliationReportList;

   public List<Map<String, Object>> getNodalReconciliationReportList() {
      return this.nodalReconciliationReportList;
   }

   @JsonProperty("nodal_reconciliation")
   public void setNodalReconciliationReportList(final List<Map<String, Object>> nodalReconciliationReportList) {
      this.nodalReconciliationReportList = nodalReconciliationReportList;
   }

   public NodalReconciliationReportResponse() {
   }
}
