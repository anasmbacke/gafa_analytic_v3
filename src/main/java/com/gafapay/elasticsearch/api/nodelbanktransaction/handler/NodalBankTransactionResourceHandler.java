package com.gafapay.elasticsearch.api.nodelbanktransaction.handler;

import com.gafapay.elasticsearch.api.nodelbanktransaction.model.request.GetAllNodalBankTransactionRequest;
import com.gafapay.elasticsearch.api.nodelbanktransaction.model.request.GetNodalBankTransactionRequest;
import com.gafapay.elasticsearch.api.nodelbanktransaction.model.response.GetAllNodalBankTransactionResponse;
import com.gafapay.elasticsearch.api.nodelbanktransaction.model.response.GetNodalBankTransactionResponse;
import com.gafapay.elasticsearch.api.nodelbanktransaction.service.NodalBankTransactionRepository;
import com.gafapay.elasticsearch.helper.HeaderProcessingHelper;
import com.gafapay.elasticsearch.utils.Utils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class NodalBankTransactionResourceHandler {
   private final NodalBankTransactionRepository nodalBankTransactionRepository;

   public NodalBankTransactionResourceHandler(NodalBankTransactionRepository nodalBankTransactionRepository) {
      this.nodalBankTransactionRepository = nodalBankTransactionRepository;
   }

   public ResponseEntity<JsonNode> getNodalBankTransaction(HttpHeaders httpHeaders, GetNodalBankTransactionRequest getNodalBankTransactionRequest) {
      HeaderProcessingHelper.setRequestHeaders(getNodalBankTransactionRequest, httpHeaders);
      if (getNodalBankTransactionRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         GetNodalBankTransactionResponse getNodalBankTransactionResponse = this.nodalBankTransactionRepository.getNodalBankTransaction(getNodalBankTransactionRequest);
         return getNodalBankTransactionResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(getNodalBankTransactionResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(getNodalBankTransactionResponse), HttpStatus.OK);
      }
   }

   public ResponseEntity<JsonNode> getAllNodalBankTransaction(HttpHeaders httpHeaders, GetAllNodalBankTransactionRequest getAllNodalBankTransactionRequest) {
      HeaderProcessingHelper.setRequestHeaders(getAllNodalBankTransactionRequest, httpHeaders);
      if (getAllNodalBankTransactionRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         GetAllNodalBankTransactionResponse getAllNodalBankTransactionResponse = this.nodalBankTransactionRepository.getAllNodalBankTransaction(getAllNodalBankTransactionRequest);
         return getAllNodalBankTransactionResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(getAllNodalBankTransactionResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(getAllNodalBankTransactionResponse), HttpStatus.OK);
      }
   }
}
