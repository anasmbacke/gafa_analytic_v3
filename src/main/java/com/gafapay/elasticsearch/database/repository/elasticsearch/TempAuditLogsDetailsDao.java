package com.gafapay.elasticsearch.database.repository.elasticsearch;

import com.gafapay.elasticsearch.database.model.TempAuditLogs;

public interface TempAuditLogsDetailsDao {
   TempAuditLogs findByReferenceById(String id);

   void deleteById(String id);
}
