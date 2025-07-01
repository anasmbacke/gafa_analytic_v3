package com.gafapay.elasticsearch.api.statistics.controller;

import com.gafapay.elasticsearch.api.statistics.handler.StatisticsResourceHandler;
import com.gafapay.elasticsearch.api.statistics.model.request.AgentCommissionStatisticsRequest;
import com.gafapay.elasticsearch.api.statistics.model.request.CorporateStatisticsRequest;
import com.gafapay.elasticsearch.api.statistics.model.request.GetCashFlowStatisticsDataRequest;
import com.gafapay.elasticsearch.api.statistics.model.request.GetNodalBankStatisticsRequest;
import com.gafapay.elasticsearch.api.statistics.model.request.GetPaymentModeStatisticsDataRequest;
import com.gafapay.elasticsearch.api.statistics.model.request.GetSettlementStatisticDataRequest;
import com.gafapay.elasticsearch.api.statistics.model.request.GetTxnStatisticsDataRequest;
import com.gafapay.elasticsearch.api.statistics.model.request.GetTxnTypeStatisticsDataRequest;
import com.gafapay.elasticsearch.api.statistics.model.request.RevenueSharingStatisticsRequest;
import com.gafapay.elasticsearch.api.statistics.model.request.ThirdPartyProviderStatisticsRequest;
import com.gafapay.elasticsearch.api.statistics.model.request.ThirdPartyVendorsStatisticsRequest;
import com.gafapay.elasticsearch.api.statistics.model.request.UserWiseTransactionStatisticsRequest;
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
public class StatisticsController {
   @Autowired
   private StatisticsResourceHandler statisticsResourceHandler;

   public StatisticsController() {
   }

   @PostMapping({"statistics"})
   public ResponseEntity<JsonNode> getTxnStatistics(@RequestHeader HttpHeaders httpHeaders, @RequestBody GetTxnStatisticsDataRequest getTxnStatisticsDataRequest) {
      return this.statisticsResourceHandler.getTxnStatistics(httpHeaders, getTxnStatisticsDataRequest);
   }

   @PostMapping({"transaction_type_statistics"})
   public ResponseEntity<JsonNode> getTxnTypeStatistics(@RequestHeader HttpHeaders headers, @RequestBody GetTxnTypeStatisticsDataRequest getTxnTypeStatisticsDataRequest) {
      return this.statisticsResourceHandler.getTxnTypeStatistics(headers, getTxnTypeStatisticsDataRequest);
   }

   @PostMapping({"payment_mode_statistics"})
   public ResponseEntity<JsonNode> getPaymentModeStatistics(@RequestHeader HttpHeaders headers, @RequestBody GetPaymentModeStatisticsDataRequest getPaymentModeStatisticsDataRequest) {
      return this.statisticsResourceHandler.getPaymentModeStatistics(headers, getPaymentModeStatisticsDataRequest);
   }

   @PostMapping({"settlement_statistics"})
   public ResponseEntity<JsonNode> getSettlementStatisticData(@RequestHeader HttpHeaders httpHeaders, @RequestBody GetSettlementStatisticDataRequest getSettlementStatisticDataRequest) {
      return this.statisticsResourceHandler.getSettlementStatisticData(httpHeaders, getSettlementStatisticDataRequest);
   }

   @PostMapping({"cash_flow_statistics"})
   public ResponseEntity<JsonNode> getCashFlowStatistic(@RequestHeader HttpHeaders httpHeaders, @RequestBody GetCashFlowStatisticsDataRequest getCashFlowStatisticsDataRequest) {
      return this.statisticsResourceHandler.getCashFlowStatistic(httpHeaders, getCashFlowStatisticsDataRequest);
   }

   @PostMapping({"agent_commission_statistics"})
   public ResponseEntity<JsonNode> agentCommissionStatistics(@RequestHeader HttpHeaders httpHeaders, @RequestBody AgentCommissionStatisticsRequest agentCommissionStatisticsRequest) {
      return this.statisticsResourceHandler.agentCommissionStatistics(httpHeaders, agentCommissionStatisticsRequest);
   }

   @PostMapping({"revenue_statistics"})
   public ResponseEntity<JsonNode> getRevenueStatistics(@RequestHeader HttpHeaders httpHeaders, @RequestBody GetTxnStatisticsDataRequest getTxnStatisticsDataRequest) {
      return this.statisticsResourceHandler.getRevenueStatistics(httpHeaders, getTxnStatisticsDataRequest);
   }

   @PostMapping({"txn_type_statistics"})
   public ResponseEntity<JsonNode> getTxnTypeWiseStatistics(@RequestHeader HttpHeaders httpHeaders, @RequestBody GetTxnStatisticsDataRequest getTxnStatisticsDataRequest) {
      return this.statisticsResourceHandler.getTxnTypeWiseStatistics(httpHeaders, getTxnStatisticsDataRequest);
   }

   @PostMapping({"nodal_bank_statistics"})
   public ResponseEntity<JsonNode> getNodalBankStatistics(@RequestHeader HttpHeaders headers, @RequestBody GetNodalBankStatisticsRequest getNodalBankStatisticsRequest) {
      return this.statisticsResourceHandler.getNodalBankStatistics(headers, getNodalBankStatisticsRequest);
   }

   @PostMapping({"userwise_transaction_statistics"})
   public ResponseEntity<JsonNode> userWiseTransactionStatistics(@RequestHeader HttpHeaders headers, @RequestBody UserWiseTransactionStatisticsRequest userWiseTransactionStatisticsRequest) {
      return this.statisticsResourceHandler.userWiseTransactionStatistics(headers, userWiseTransactionStatisticsRequest);
   }

   @PostMapping({"revenue_sharing_statistics"})
   public ResponseEntity<JsonNode> getRevenueSharingStatistics(@RequestHeader HttpHeaders headers, @RequestBody RevenueSharingStatisticsRequest revenueSharingStatisticsRequest) {
      return this.statisticsResourceHandler.getRevenueSharingStatistics(headers, revenueSharingStatisticsRequest);
   }

   @PostMapping({"third_party_vendors_statistics"})
   public ResponseEntity<JsonNode> getThirdPartyVendorsStatistics(@RequestHeader HttpHeaders headers, @RequestBody ThirdPartyVendorsStatisticsRequest thirdPartyVendorsStatisticsRequest) {
      return this.statisticsResourceHandler.getThirdPartyVendorsStatistics(headers, thirdPartyVendorsStatisticsRequest);
   }

   @PostMapping({"third_party_provider_statistics"})
   public ResponseEntity<JsonNode> getThirdPartyProviderStatistics(@RequestHeader HttpHeaders headers, @RequestBody ThirdPartyProviderStatisticsRequest thirdPartyProviderStatisticsRequest) {
      return this.statisticsResourceHandler.getThirdPartyProviderStatistics(headers, thirdPartyProviderStatisticsRequest);
   }

   @PostMapping({"corporate/txn_statistics"})
   public ResponseEntity<JsonNode> corporateWiseStatistics(@RequestHeader HttpHeaders headers, @RequestBody CorporateStatisticsRequest corporateStatisticsRequest) {
      return this.statisticsResourceHandler.corporateWiseStatistics(headers, corporateStatisticsRequest);
   }
}
