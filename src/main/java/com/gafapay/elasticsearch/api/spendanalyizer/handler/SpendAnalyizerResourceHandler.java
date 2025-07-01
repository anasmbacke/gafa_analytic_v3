package com.gafapay.elasticsearch.api.spendanalyizer.handler;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.gafapay.elasticsearch.api.spendanalyizer.model.request.TransferByCategoryStatisticsRequest;
import com.gafapay.elasticsearch.api.spendanalyizer.model.request.TransferByUserStatisticsRequest;
import com.gafapay.elasticsearch.api.spendanalyizer.model.request.TransferByUtilityStatisticsRequest;
import com.gafapay.elasticsearch.api.spendanalyizer.service.SpendAnalyizerRepository;
import com.gafapay.elasticsearch.helper.HeaderProcessingHelper;
import com.gafapay.elasticsearch.utils.Utils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class SpendAnalyizerResourceHandler {
   private final SpendAnalyizerRepository spendAnalyizerRepository;

   public SpendAnalyizerResourceHandler(SpendAnalyizerRepository spendAnalyizerRepository) {
      this.spendAnalyizerRepository = spendAnalyizerRepository;
   }

   public ResponseEntity<JsonNode> transferByUserStatistics(HttpHeaders headers, TransferByUserStatisticsRequest transferByUserStatisticsRequest) {
      HeaderProcessingHelper.setRequestHeaders(transferByUserStatisticsRequest, headers);
      if (transferByUserStatisticsRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         CommonAPIDataResponse commonAPIDataResponse = this.spendAnalyizerRepository.transferByUserStatistics(transferByUserStatisticsRequest);
         return commonAPIDataResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(commonAPIDataResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(commonAPIDataResponse), HttpStatus.OK);
      }
   }

   public ResponseEntity<JsonNode> transferByCategoryStatistics(HttpHeaders headers, TransferByCategoryStatisticsRequest transferByCategoryStatisticsRequest) {
      HeaderProcessingHelper.setRequestHeaders(transferByCategoryStatisticsRequest, headers);
      if (transferByCategoryStatisticsRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         CommonAPIDataResponse commonAPIDataResponse = this.spendAnalyizerRepository.transferByCategoryStatistics(transferByCategoryStatisticsRequest);
         return commonAPIDataResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(commonAPIDataResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(commonAPIDataResponse), HttpStatus.OK);
      }
   }

   public ResponseEntity<JsonNode> transferByUtilityStatistics(HttpHeaders headers, TransferByUtilityStatisticsRequest transferByUtilityStatisticsRequest) {
      HeaderProcessingHelper.setRequestHeaders(transferByUtilityStatisticsRequest, headers);
      if (transferByUtilityStatisticsRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         CommonAPIDataResponse commonAPIDataResponse = this.spendAnalyizerRepository.transferByUtilityStatistics(transferByUtilityStatisticsRequest);
         return commonAPIDataResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(commonAPIDataResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(commonAPIDataResponse), HttpStatus.OK);
      }
   }
}
