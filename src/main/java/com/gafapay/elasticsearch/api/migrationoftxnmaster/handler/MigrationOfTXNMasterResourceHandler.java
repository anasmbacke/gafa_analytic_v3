package com.gafapay.elasticsearch.api.migrationoftxnmaster.handler;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.gafapay.elasticsearch.api.migrationoftxnmaster.model.request.TotalRecordRequest;
import com.gafapay.elasticsearch.api.migrationoftxnmaster.model.request.UpdateTxnRecords;
import com.gafapay.elasticsearch.api.migrationoftxnmaster.service.MigrationOfTXNMasterRepository;
import com.gafapay.elasticsearch.api.nodelbanktransaction.model.response.GetAllNodalBankTransactionResponse;
import com.gafapay.elasticsearch.helper.HeaderProcessingHelper;
import com.gafapay.elasticsearch.utils.Utils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class MigrationOfTXNMasterResourceHandler {
   private final MigrationOfTXNMasterRepository migrationOfTXNMasterRepository;

   public MigrationOfTXNMasterResourceHandler(MigrationOfTXNMasterRepository migrationOfTXNMasterRepository) {
      this.migrationOfTXNMasterRepository = migrationOfTXNMasterRepository;
   }

   public ResponseEntity<JsonNode> migrationOfTxnMaster(HttpHeaders httpHeaders, TotalRecordRequest totalRecordRequest) {
      HeaderProcessingHelper.setRequestHeaders(totalRecordRequest, httpHeaders);
      GetAllNodalBankTransactionResponse getAllNodalBankTransactionResponse = this.migrationOfTXNMasterRepository.migrationOfTxnMaster(totalRecordRequest);
      return getAllNodalBankTransactionResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(getAllNodalBankTransactionResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(getAllNodalBankTransactionResponse), HttpStatus.OK);
   }

   public ResponseEntity<JsonNode> updateTxnRecord(HttpHeaders httpHeaders, UpdateTxnRecords updateTxnRecords) {
      HeaderProcessingHelper.setRequestHeaders(updateTxnRecords, httpHeaders);
      if (updateTxnRecords.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         CommonAPIDataResponse commonAPIDataResponse = this.migrationOfTXNMasterRepository.updateTxnRecord(updateTxnRecords);
         return commonAPIDataResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(commonAPIDataResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(commonAPIDataResponse), HttpStatus.OK);
      }
   }
}
