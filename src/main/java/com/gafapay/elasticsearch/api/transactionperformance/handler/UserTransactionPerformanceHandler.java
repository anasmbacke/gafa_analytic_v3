package com.gafapay.elasticsearch.api.transactionperformance.handler;

import com.gafapay.elasticsearch.api.transactionperformance.model.request.UserTransactionPerformanceRequest;
import com.gafapay.elasticsearch.api.transactionperformance.model.response.UserTransactionPerformanceResponse;
import com.gafapay.elasticsearch.api.transactionperformance.service.UserTransactionPerformanceRepository;
import com.gafapay.elasticsearch.helper.HeaderProcessingHelper;
import com.gafapay.elasticsearch.utils.Utils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserTransactionPerformanceHandler {
   private final UserTransactionPerformanceRepository userTransactionPerformanceRepository;

   public UserTransactionPerformanceHandler(UserTransactionPerformanceRepository userTransactionPerformanceRepository) {
      this.userTransactionPerformanceRepository = userTransactionPerformanceRepository;
   }

   public ResponseEntity<JsonNode> userTransactionPerformance(HttpHeaders httpHeaders, UserTransactionPerformanceRequest userTransactionPerformanceRequest) {
      HeaderProcessingHelper.setRequestHeaders(userTransactionPerformanceRequest, httpHeaders);
      if (userTransactionPerformanceRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         UserTransactionPerformanceResponse userTransactionPerformanceResponse = this.userTransactionPerformanceRepository.userTransactionPerformance(userTransactionPerformanceRequest);
         return userTransactionPerformanceResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(userTransactionPerformanceResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(userTransactionPerformanceResponse), HttpStatus.OK);
      }
   }
}
