package com.gafapay.elasticsearch.api.auditlogs.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetAuditLogsDataResponse extends CommonAPIDataResponse {
   @JsonProperty("audit_logs")
   private AuditLogsData auditLogs;

   public AuditLogsData getAuditLogs() {
      return this.auditLogs;
   }

   @JsonProperty("audit_logs")
   public void setAuditLogs(final AuditLogsData auditLogs) {
      this.auditLogs = auditLogs;
   }

   public GetAuditLogsDataResponse() {
   }
}
