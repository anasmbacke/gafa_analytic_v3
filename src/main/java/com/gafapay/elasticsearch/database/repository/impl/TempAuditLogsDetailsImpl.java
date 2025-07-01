package com.gafapay.elasticsearch.database.repository.impl;

import com.gafapay.elasticsearch.database.model.TempAuditLogs;
import com.gafapay.elasticsearch.database.repository.elasticsearch.TempAuditLogsDetailsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TempAuditLogsDetailsImpl implements TempAuditLogsDetailsDao {
   @Autowired
   private ElasticsearchRestTemplate elasticsearchRestTemplate;

   public TempAuditLogsDetailsImpl() {
   }

   public TempAuditLogs findByReferenceById(String id) {
      return (TempAuditLogs)this.elasticsearchRestTemplate.get(id, TempAuditLogs.class);
   }

   public void deleteById(String id) {
      this.elasticsearchRestTemplate.delete(id, TempAuditLogs.class);
   }
}
