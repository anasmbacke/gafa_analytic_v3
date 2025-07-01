package com.gafapay.elasticsearch.api.bankreconciliationreport.service;

import com.gafapay.elasticsearch.api.bankreconciliationreport.model.request.GetBankReconciliationReportRequest;
import com.gafapay.elasticsearch.api.bankreconciliationreport.model.response.GetBankReconciliationReportResponse;

public interface BankReconciliationReportRepository {
   GetBankReconciliationReportResponse getBankReconciliationReport(GetBankReconciliationReportRequest getBankReconciliationReportRequest);
}
