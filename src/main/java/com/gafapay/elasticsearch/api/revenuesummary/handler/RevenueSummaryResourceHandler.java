package com.gafapay.elasticsearch.api.revenuesummary.handler;

import com.gafapay.elasticsearch.api.revenuesummary.model.request.GetAllRevenueSummaryDataRequest;
import com.gafapay.elasticsearch.api.revenuesummary.model.request.GetRevenueSummaryDataRequest;
import com.gafapay.elasticsearch.api.revenuesummary.model.response.GetAllRevenueSummaryDataResponse;
import com.gafapay.elasticsearch.api.revenuesummary.model.response.GetRevenueSummaryDataResponse;
import com.gafapay.elasticsearch.api.revenuesummary.model.response.GetRevenueSummaryReportResponse;
import com.gafapay.elasticsearch.api.revenuesummary.service.RevenueSummaryRepository;
import com.gafapay.elasticsearch.api.statistics.model.request.GetTxnStatisticsDataRequest;
import com.gafapay.elasticsearch.helper.HeaderProcessingHelper;
import com.gafapay.elasticsearch.utils.Utils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RevenueSummaryResourceHandler {
   private final RevenueSummaryRepository revenueSummaryRepository;

   public RevenueSummaryResourceHandler(RevenueSummaryRepository revenueSummaryRepository) {
      this.revenueSummaryRepository = revenueSummaryRepository;
   }

   public ResponseEntity<JsonNode> getAllRevenueSummary(HttpHeaders httpHeaders, GetAllRevenueSummaryDataRequest getAllRevenueSummaryDataRequest) {
      HeaderProcessingHelper.setRequestHeaders(getAllRevenueSummaryDataRequest, httpHeaders);
      if (getAllRevenueSummaryDataRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         GetAllRevenueSummaryDataResponse getAllRevenueSummaryDataResponse = this.revenueSummaryRepository.getAllRevenueSummary(getAllRevenueSummaryDataRequest);
         return getAllRevenueSummaryDataResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(getAllRevenueSummaryDataResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(getAllRevenueSummaryDataResponse), HttpStatus.OK);
      }
   }

   public ResponseEntity<JsonNode> getRevenueSummary(HttpHeaders httpHeaders, GetRevenueSummaryDataRequest getRevenueSummaryDataRequest) {
      HeaderProcessingHelper.setRequestHeaders(getRevenueSummaryDataRequest, httpHeaders);
      if (getRevenueSummaryDataRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         GetRevenueSummaryDataResponse getRevenueSummaryDataResponse = this.revenueSummaryRepository.getRevenueSummary(getRevenueSummaryDataRequest);
         return getRevenueSummaryDataResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(getRevenueSummaryDataResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(getRevenueSummaryDataResponse), HttpStatus.OK);
      }
   }

   public ResponseEntity<JsonNode> getRevenueSummaryReport(HttpHeaders httpHeaders, GetTxnStatisticsDataRequest getTxnStatisticsDataRequest) {
      HeaderProcessingHelper.setRequestHeaders(getTxnStatisticsDataRequest, httpHeaders);
      if (getTxnStatisticsDataRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         GetRevenueSummaryReportResponse getRevenueSummaryReportResponse = this.revenueSummaryRepository.getRevenueSummaryReport(getTxnStatisticsDataRequest);
         return getRevenueSummaryReportResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(getRevenueSummaryReportResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(getRevenueSummaryReportResponse), HttpStatus.OK);
      }
   }
}
