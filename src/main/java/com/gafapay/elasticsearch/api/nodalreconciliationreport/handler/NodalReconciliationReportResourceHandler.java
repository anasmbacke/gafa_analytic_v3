package com.gafapay.elasticsearch.api.nodalreconciliationreport.handler;

import com.gafapay.elasticsearch.api.nodalreconciliationreport.model.request.NodalReconciliationReportRequest;
import com.gafapay.elasticsearch.api.nodalreconciliationreport.model.response.NodalReconciliationReportResponse;
import com.gafapay.elasticsearch.api.nodalreconciliationreport.service.NodalReconciliationReportRepository;
import com.gafapay.elasticsearch.helper.HeaderProcessingHelper;
import com.gafapay.elasticsearch.utils.Utils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class NodalReconciliationReportResourceHandler {
   private final NodalReconciliationReportRepository nodalReconciliationReportRepository;

   public NodalReconciliationReportResourceHandler(NodalReconciliationReportRepository nodalReconciliationReportRepository) {
      this.nodalReconciliationReportRepository = nodalReconciliationReportRepository;
   }

   public ResponseEntity<JsonNode> nodalReconciliationReport(HttpHeaders headers, NodalReconciliationReportRequest nodalReconciliationReportRequest) {
      HeaderProcessingHelper.setRequestHeaders(nodalReconciliationReportRequest, headers);
      if (nodalReconciliationReportRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         NodalReconciliationReportResponse nodalReconciliationReport = this.nodalReconciliationReportRepository.nodalReconciliationReport(nodalReconciliationReportRequest);
         return nodalReconciliationReport.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(nodalReconciliationReport.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(nodalReconciliationReport), HttpStatus.OK);
      }
   }
}
