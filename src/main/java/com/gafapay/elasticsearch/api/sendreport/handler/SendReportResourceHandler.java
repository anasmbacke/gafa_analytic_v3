package com.gafapay.elasticsearch.api.sendreport.handler;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.gafapay.elasticsearch.api.sendreport.service.SendReportRepository;
import com.gafapay.elasticsearch.helper.HeaderProcessingHelper;
import com.gafapay.elasticsearch.utils.Utils;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class SendReportResourceHandler {
   private final SendReportRepository sendReportRepository;

   public SendReportResourceHandler(SendReportRepository sendReportRepository) {
      this.sendReportRepository = sendReportRepository;
   }

   public ResponseEntity<JsonNode> sendReport(HttpHeaders httpHeaders, SendReportRequest sendReportRequest) throws IOException {
      HeaderProcessingHelper.setRequestHeaders(sendReportRequest, httpHeaders);
      if (sendReportRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         CommonAPIDataResponse sendReportDataResponse = this.sendReportRepository.sendStatisticsReport(sendReportRequest);
         return sendReportDataResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(sendReportDataResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(sendReportDataResponse), HttpStatus.OK);
      }
   }
}
