package com.gafapay.elasticsearch.api.bankreconciliationreport.controller;

import com.gafapay.elasticsearch.api.bankreconciliationreport.handler.BankReconciliationReportResourceHandler;
import com.gafapay.elasticsearch.api.bankreconciliationreport.model.request.GetBankReconciliationReportRequest;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"${app.baseurl}analytics/"})
public class BankReconciliationReportController {
   @Autowired
   private BankReconciliationReportResourceHandler bankReconciliationReportResourceHandler;

   public BankReconciliationReportController() {
   }

   @PostMapping({"bank_reconciliation"})
   public ResponseEntity<JsonNode> getBankReconciliationReport(@RequestHeader HttpHeaders headers, @RequestBody GetBankReconciliationReportRequest getBankReconciliationReportRequest) {
      return this.bankReconciliationReportResourceHandler.getBankReconciliationReport(headers, getBankReconciliationReportRequest);
   }
}
