package com.gafapay.elasticsearch.api.merchantpaymentstatistics.controller;

import com.gafapay.elasticsearch.api.merchantpaymentstatistics.handler.MerchantPaymentStatisticsHandler;
import com.gafapay.elasticsearch.api.merchantpaymentstatistics.model.request.MerchantPaymentStatisticsRequest;
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
public class MerchantPaymentStatisticsController {
   @Autowired
   private MerchantPaymentStatisticsHandler merchantPaymentStatisticsHandler;

   public MerchantPaymentStatisticsController() {
   }

   @PostMapping({"merchant_payment_statistics"})
   public ResponseEntity<JsonNode> merchantPaymentStatistics(@RequestHeader HttpHeaders headers, @RequestBody MerchantPaymentStatisticsRequest merchantPaymentStatisticsRequest) {
      return this.merchantPaymentStatisticsHandler.merchantPaymentStatistics(headers, merchantPaymentStatisticsRequest);
   }
}
