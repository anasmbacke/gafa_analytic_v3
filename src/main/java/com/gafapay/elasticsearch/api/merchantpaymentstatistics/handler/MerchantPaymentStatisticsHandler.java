package com.gafapay.elasticsearch.api.merchantpaymentstatistics.handler;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.gafapay.elasticsearch.api.merchantpaymentstatistics.model.request.MerchantPaymentStatisticsRequest;
import com.gafapay.elasticsearch.api.merchantpaymentstatistics.service.MerchantPaymentStatisticsRepository;
import com.gafapay.elasticsearch.helper.HeaderProcessingHelper;
import com.gafapay.elasticsearch.utils.Utils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class MerchantPaymentStatisticsHandler {
   private final MerchantPaymentStatisticsRepository merchantPaymentStatisticsRepository;

   public MerchantPaymentStatisticsHandler(MerchantPaymentStatisticsRepository merchantPaymentStatisticsRepository) {
      this.merchantPaymentStatisticsRepository = merchantPaymentStatisticsRepository;
   }

   public ResponseEntity<JsonNode> merchantPaymentStatistics(HttpHeaders headers, MerchantPaymentStatisticsRequest merchantPaymentStatisticsRequest) {
      HeaderProcessingHelper.setRequestHeaders(merchantPaymentStatisticsRequest, headers);
      if (merchantPaymentStatisticsRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         CommonAPIDataResponse commonAPIDataResponse = this.merchantPaymentStatisticsRepository.merchantPaymentStatistics(merchantPaymentStatisticsRequest);
         return commonAPIDataResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(commonAPIDataResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(commonAPIDataResponse), HttpStatus.OK);
      }
   }
}
