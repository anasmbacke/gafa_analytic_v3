package com.gafapay.elasticsearch.api.nodalreconciliationreport.service;

import com.gafapay.elasticsearch.api.nodalreconciliationreport.model.request.NodalReconciliationReportRequest;
import com.gafapay.elasticsearch.api.nodalreconciliationreport.model.response.NodalReconciliationReportResponse;

public interface NodalReconciliationReportRepository {
   NodalReconciliationReportResponse nodalReconciliationReport(NodalReconciliationReportRequest nodalReconciliationReportRequest);
}
