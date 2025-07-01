package com.gafapay.elasticsearch.api.auditlogs.dao;

import com.gafapay.elasticsearch.api.auditlogs.model.request.GetAllAuditLogsDataRequest;
import com.gafapay.elasticsearch.api.auditlogs.model.request.GetAuditLogsDataRequest;
import com.gafapay.elasticsearch.database.model.AuditLogs;
import java.util.List;

public interface AuditLogsDao {
   void saveOrUpdate(AuditLogs auditLogs);

   List<AuditLogs> getAllAuditLogs(GetAllAuditLogsDataRequest getAllAuditLogsDataRequest);

   AuditLogs getAuditLogsById(GetAuditLogsDataRequest getAuditLogsDataRequest);
}
