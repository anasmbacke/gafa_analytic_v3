package com.gafapay.elasticsearch.api.bankreconciliationreport.handler;

import com.gafapay.elasticsearch.api.bankreconciliationreport.model.request.GetBankReconciliationReportRequest;
import com.gafapay.elasticsearch.api.bankreconciliationreport.model.response.GetBankReconciliationReportResponse;
import com.gafapay.elasticsearch.api.bankreconciliationreport.service.BankReconciliationReportRepository;
import com.gafapay.elasticsearch.helper.HeaderProcessingHelper;
import com.gafapay.elasticsearch.utils.Utils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class BankReconciliationReportResourceHandler {
   private final BankReconciliationReportRepository bankReconciliationReportRepository;

   public BankReconciliationReportResourceHandler(BankReconciliationReportRepository bankReconciliationReportRepository) {
      this.bankReconciliationReportRepository = bankReconciliationReportRepository;
   }

   public ResponseEntity<JsonNode> getBankReconciliationReport(HttpHeaders headers, GetBankReconciliationReportRequest getBankReconciliationReportRequest) {
      HeaderProcessingHelper.setRequestHeaders(getBankReconciliationReportRequest, headers);
      if (getBankReconciliationReportRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         GetBankReconciliationReportResponse getBankReconciliationReportResponse = this.bankReconciliationReportRepository.getBankReconciliationReport(getBankReconciliationReportRequest);
         return getBankReconciliationReportResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(getBankReconciliationReportResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(getBankReconciliationReportResponse), HttpStatus.OK);
      }
   }
}
