package com.gafapay.elasticsearch.api.nodelbanktransaction.controller;

import com.gafapay.elasticsearch.api.nodelbanktransaction.handler.NodalBankTransactionResourceHandler;
import com.gafapay.elasticsearch.api.nodelbanktransaction.model.request.GetAllNodalBankTransactionRequest;
import com.gafapay.elasticsearch.api.nodelbanktransaction.model.request.GetNodalBankTransactionRequest;
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
public class NodalBankTransactionController {
   @Autowired
   private NodalBankTransactionResourceHandler nodalBankTransactionResourceHandler;

   public NodalBankTransactionController() {
   }

   @GetMapping({"nodal_bank_transactions/{id}"})
   public ResponseEntity<JsonNode> getNodalBankTransaction(@RequestHeader HttpHeaders httpHeaders, @PathVariable String id, @ModelAttribute GetNodalBankTransactionRequest getNodalBankTransactionRequest) {
      getNodalBankTransactionRequest.setId(id);
      return this.nodalBankTransactionResourceHandler.getNodalBankTransaction(httpHeaders, getNodalBankTransactionRequest);
   }

   @GetMapping({"nodal_bank_transactions"})
   public ResponseEntity<JsonNode> getAllNodalBankTransaction(@RequestHeader HttpHeaders httpHeaders, @ModelAttribute GetAllNodalBankTransactionRequest getAllNodalBankTransactionRequest) {
      return this.nodalBankTransactionResourceHandler.getAllNodalBankTransaction(httpHeaders, getAllNodalBankTransactionRequest);
   }
}
