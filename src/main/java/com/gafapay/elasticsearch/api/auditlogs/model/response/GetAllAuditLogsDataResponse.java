package com.gafapay.elasticsearch.api.auditlogs.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;

public class GetAllAuditLogsDataResponse extends CommonAPIDataResponse {
   @JsonProperty("audit_logs")
   private List<AuditLogsData> auditLogs;
   @JsonProperty("cursor")
   @JsonInclude(Include.NON_NULL)
   private String cursor;

   public List<AuditLogsData> getAuditLogs() {
      return this.auditLogs;
   }

   public String getCursor() {
      return this.cursor;
   }

   @JsonProperty("audit_logs")
   public void setAuditLogs(final List<AuditLogsData> auditLogs) {
      this.auditLogs = auditLogs;
   }

   @JsonProperty("cursor")
   public void setCursor(final String cursor) {
      this.cursor = cursor;
   }

   public GetAllAuditLogsDataResponse() {
   }
}
