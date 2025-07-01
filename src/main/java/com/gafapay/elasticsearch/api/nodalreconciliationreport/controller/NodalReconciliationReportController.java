package com.gafapay.elasticsearch.api.nodalreconciliationreport.controller;

import com.gafapay.elasticsearch.api.nodalreconciliationreport.handler.NodalReconciliationReportResourceHandler;
import com.gafapay.elasticsearch.api.nodalreconciliationreport.model.request.NodalReconciliationReportRequest;
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
public class NodalReconciliationReportController {
   @Autowired
   private NodalReconciliationReportResourceHandler nodalReconciliationReportResourceHandler;

   public NodalReconciliationReportController() {
   }

   @PostMapping({"nodal_reconciliation"})
   public ResponseEntity<JsonNode> nodalReconciliationReport(@RequestHeader HttpHeaders headers, @RequestBody NodalReconciliationReportRequest nodalReconciliationReportRequest) {
      return this.nodalReconciliationReportResourceHandler.nodalReconciliationReport(headers, nodalReconciliationReportRequest);
   }
}
