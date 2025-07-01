package com.gafapay.elasticsearch.api.totalrecords.handler;

import com.gafapay.elasticsearch.api.migrationoftxnmaster.model.request.TotalRecordRequest;
import com.gafapay.elasticsearch.api.totalrecords.model.response.TotalRecordResponse;
import com.gafapay.elasticsearch.api.totalrecords.service.TotalRecordsRepository;
import com.gafapay.elasticsearch.helper.HeaderProcessingHelper;
import com.gafapay.elasticsearch.utils.Utils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TotalRecordsResourceHandler {
   private final TotalRecordsRepository totalRecordsRepository;

   public TotalRecordsResourceHandler(TotalRecordsRepository totalRecordsRepository) {
      this.totalRecordsRepository = totalRecordsRepository;
   }

   public ResponseEntity<JsonNode> totalRecord(HttpHeaders httpHeaders, TotalRecordRequest totalRecordRequest) {
      HeaderProcessingHelper.setRequestHeaders(totalRecordRequest, httpHeaders);
      if (totalRecordRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         TotalRecordResponse totalRecordResponse = this.totalRecordsRepository.totalRecord(totalRecordRequest);
         return totalRecordResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(totalRecordResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(totalRecordResponse), HttpStatus.OK);
      }
   }
}
