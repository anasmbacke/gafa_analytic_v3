package com.gafapay.elasticsearch.api.revenuesummary.controller;

import com.gafapay.elasticsearch.api.revenuesummary.handler.RevenueSummaryResourceHandler;
import com.gafapay.elasticsearch.api.revenuesummary.model.request.GetAllRevenueSummaryDataRequest;
import com.gafapay.elasticsearch.api.revenuesummary.model.request.GetRevenueSummaryDataRequest;
import com.gafapay.elasticsearch.api.statistics.model.request.GetTxnStatisticsDataRequest;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"${app.baseurl}analytics/"})
public class RevenueSummaryController {
   @Autowired
   private RevenueSummaryResourceHandler revenueSummaryResourceHandler;

   public RevenueSummaryController() {
   }

   @GetMapping({"revenue_summary"})
   public ResponseEntity<JsonNode> getAllRevenueSummary(@RequestHeader HttpHeaders httpHeaders, @ModelAttribute GetAllRevenueSummaryDataRequest getAllRevenueSummaryDataRequest) {
      return this.revenueSummaryResourceHandler.getAllRevenueSummary(httpHeaders, getAllRevenueSummaryDataRequest);
   }

   @GetMapping({"revenue_summary/{id}"})
   public ResponseEntity<JsonNode> getRevenueSummary(@RequestHeader HttpHeaders httpHeaders, @PathVariable String id, @ModelAttribute GetRevenueSummaryDataRequest getRevenueSummaryDataRequest) {
      getRevenueSummaryDataRequest.setId(id);
      return this.revenueSummaryResourceHandler.getRevenueSummary(httpHeaders, getRevenueSummaryDataRequest);
   }

   @PostMapping({"revenue_summary"})
   public ResponseEntity<JsonNode> getRevenueSummaryReport(@RequestHeader HttpHeaders httpHeaders, @RequestBody GetTxnStatisticsDataRequest getTxnStatisticsDataRequest) {
      return this.revenueSummaryResourceHandler.getRevenueSummaryReport(httpHeaders, getTxnStatisticsDataRequest);
   }
}
