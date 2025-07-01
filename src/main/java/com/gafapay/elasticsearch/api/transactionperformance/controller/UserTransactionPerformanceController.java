package com.gafapay.elasticsearch.api.transactionperformance.controller;

import com.gafapay.elasticsearch.api.transactionperformance.handler.UserTransactionPerformanceHandler;
import com.gafapay.elasticsearch.api.transactionperformance.model.request.UserTransactionPerformanceRequest;
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
public class UserTransactionPerformanceController {
   @Autowired
   private UserTransactionPerformanceHandler userTransactionPerformanceHandler;

   public UserTransactionPerformanceController() {
   }

   @PostMapping({"user_transaction_performance"})
   public ResponseEntity<JsonNode> userTransactionPerformance(@RequestHeader HttpHeaders httpHeaders, @RequestBody UserTransactionPerformanceRequest userTransactionPerformanceRequest) {
      return this.userTransactionPerformanceHandler.userTransactionPerformance(httpHeaders, userTransactionPerformanceRequest);
   }
}
