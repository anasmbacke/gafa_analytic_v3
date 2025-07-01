package com.gafapay.elasticsearch.api.spendanalyizer.controller;

import com.gafapay.elasticsearch.api.spendanalyizer.handler.SpendAnalyizerResourceHandler;
import com.gafapay.elasticsearch.api.spendanalyizer.model.request.TransferByCategoryStatisticsRequest;
import com.gafapay.elasticsearch.api.spendanalyizer.model.request.TransferByUserStatisticsRequest;
import com.gafapay.elasticsearch.api.spendanalyizer.model.request.TransferByUtilityStatisticsRequest;
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
public class SpendAnalyizerController {
   @Autowired
   private SpendAnalyizerResourceHandler spendAnalyizerResourceHandler;

   public SpendAnalyizerController() {
   }

   @PostMapping({"transfer_by_user"})
   public ResponseEntity<JsonNode> transferByUserStatistics(@RequestHeader HttpHeaders headers, @RequestBody TransferByUserStatisticsRequest transferByUserStatisticsRequest) {
      return this.spendAnalyizerResourceHandler.transferByUserStatistics(headers, transferByUserStatisticsRequest);
   }

   @PostMapping({"transfer_by_category"})
   public ResponseEntity<JsonNode> transferByCategoryStatistics(@RequestHeader HttpHeaders headers, @RequestBody TransferByCategoryStatisticsRequest transferByCategoryStatisticsRequest) {
      return this.spendAnalyizerResourceHandler.transferByCategoryStatistics(headers, transferByCategoryStatisticsRequest);
   }

   @PostMapping({"payment_statistics"})
   public ResponseEntity<JsonNode> transferByUtilityStatistics(@RequestHeader HttpHeaders headers, @RequestBody TransferByUtilityStatisticsRequest transferByUtilityStatisticsRequest) {
      return this.spendAnalyizerResourceHandler.transferByUtilityStatistics(headers, transferByUtilityStatisticsRequest);
   }
}
