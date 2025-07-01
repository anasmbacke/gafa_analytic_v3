package com.gafapay.elasticsearch.api.txnmaster.handler;

import com.gafapay.elasticsearch.api.txnmaster.model.request.GetAllTransactionsDataRequest;
import com.gafapay.elasticsearch.api.txnmaster.model.request.GetTransactionDataRequest;
import com.gafapay.elasticsearch.api.txnmaster.model.response.GetAllTransactionsDataResponse;
import com.gafapay.elasticsearch.api.txnmaster.model.response.GetTransactionDataResponse;
import com.gafapay.elasticsearch.api.txnmaster.service.TXNMasterRepository;
import com.gafapay.elasticsearch.helper.HeaderProcessingHelper;
import com.gafapay.elasticsearch.utils.Utils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TXNMasterResourceHandler {
   private final TXNMasterRepository txnMasterRepository;

   public TXNMasterResourceHandler(TXNMasterRepository txnMasterRepository) {
      this.txnMasterRepository = txnMasterRepository;
   }

   public ResponseEntity<JsonNode> getAllTransactions(HttpHeaders httpHeaders, GetAllTransactionsDataRequest getAllTransactionsDataRequest) {
      HeaderProcessingHelper.setRequestHeaders(getAllTransactionsDataRequest, httpHeaders);
      if (getAllTransactionsDataRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("Bad Request"), HttpStatus.OK);
      } else {
         GetAllTransactionsDataResponse getTxnResponse = this.txnMasterRepository.getAllTransactions(getAllTransactionsDataRequest);
         return getTxnResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(getTxnResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(getTxnResponse), HttpStatus.OK);
      }
   }

   public ResponseEntity<JsonNode> getTransactionDetail(HttpHeaders httpHeaders, GetTransactionDataRequest getTransactionDataRequest) {
      HeaderProcessingHelper.setRequestHeaders(getTransactionDataRequest, httpHeaders);
      if (getTransactionDataRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         GetTransactionDataResponse getTransactionDataResponse = this.txnMasterRepository.getTransactionDetail(getTransactionDataRequest);
         return getTransactionDataResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(getTransactionDataResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(getTransactionDataResponse), HttpStatus.OK);
      }
   }
}
