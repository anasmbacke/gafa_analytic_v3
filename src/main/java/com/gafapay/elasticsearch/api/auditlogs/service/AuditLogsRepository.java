package com.gafapay.elasticsearch.api.auditlogs.service;

import com.gafapay.elasticsearch.api.auditlogs.model.request.GetAllAuditLogsDataRequest;
import com.gafapay.elasticsearch.api.auditlogs.model.request.GetAuditLogsDataRequest;
import com.gafapay.elasticsearch.api.auditlogs.model.response.GetAllAuditLogsDataResponse;
import com.gafapay.elasticsearch.api.auditlogs.model.response.GetAuditLogsDataResponse;

public interface AuditLogsRepository {
   GetAllAuditLogsDataResponse getAllAuditLogs(GetAllAuditLogsDataRequest getAllAuditLogsDataRequest);

   GetAuditLogsDataResponse getAuditLogs(GetAuditLogsDataRequest getAuditLogsDataRequest);
}
