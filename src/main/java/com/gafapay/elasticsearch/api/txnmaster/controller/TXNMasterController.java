package com.gafapay.elasticsearch.api.txnmaster.controller;

import com.gafapay.elasticsearch.api.txnmaster.handler.TXNMasterResourceHandler;
import com.gafapay.elasticsearch.api.txnmaster.model.request.GetAllTransactionsDataRequest;
import com.gafapay.elasticsearch.api.txnmaster.model.request.GetTransactionDataRequest;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"${app.baseurl}analytics/"})
public class TXNMasterController {
   @Autowired
   private TXNMasterResourceHandler txnMasterResourceHandler;

   public TXNMasterController() {
   }

   @GetMapping({"transactions"})
   public ResponseEntity<JsonNode> getAllTransactions(@RequestHeader HttpHeaders httpHeaders, @ModelAttribute GetAllTransactionsDataRequest getAllTransactionsDataRequest) {
      return this.txnMasterResourceHandler.getAllTransactions(httpHeaders, getAllTransactionsDataRequest);
   }

   @GetMapping({"transactions/{id}"})
   public ResponseEntity<JsonNode> getTransactionDetail(@RequestHeader HttpHeaders httpHeaders, @PathVariable String id, @ModelAttribute GetTransactionDataRequest getTransactionDataRequest) {
      getTransactionDataRequest.setId(id);
      return this.txnMasterResourceHandler.getTransactionDetail(httpHeaders, getTransactionDataRequest);
   }
}
