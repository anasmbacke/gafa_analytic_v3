package com.gafapay.elasticsearch.api.statistics.handler;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
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
import com.gafapay.elasticsearch.api.statistics.model.response.AgentCommissionStatisticsResponse;
import com.gafapay.elasticsearch.api.statistics.model.response.GetCashFlowStatisticsResponse;
import com.gafapay.elasticsearch.api.statistics.model.response.GetPaymentModeStatisticsDataResponse;
import com.gafapay.elasticsearch.api.statistics.model.response.GetRevenueStatisticsByTxnTypeResponse;
import com.gafapay.elasticsearch.api.statistics.model.response.GetRevenueStatisticsResponse;
import com.gafapay.elasticsearch.api.statistics.model.response.GetSettlementStatisticsResponse;
import com.gafapay.elasticsearch.api.statistics.model.response.GetTxnStatisticsDataResponse;
import com.gafapay.elasticsearch.api.statistics.model.response.GetTxnTypeStatisticsDataResponse;
import com.gafapay.elasticsearch.api.statistics.model.response.RevenueSharingStatisticsResponse;
import com.gafapay.elasticsearch.api.statistics.model.response.ThirdPartyProviderStatisticsResponse;
import com.gafapay.elasticsearch.api.statistics.model.response.ThirdPartyVendorsStatisticsResponse;
import com.gafapay.elasticsearch.api.statistics.model.response.UserWiseTransactionStatisticsResponse;
import com.gafapay.elasticsearch.api.statistics.service.StatisticsRepository;
import com.gafapay.elasticsearch.helper.HeaderProcessingHelper;
import com.gafapay.elasticsearch.utils.Utils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class StatisticsResourceHandler {
   private final StatisticsRepository statisticsRepository;

   public StatisticsResourceHandler(StatisticsRepository statisticsRepository) {
      this.statisticsRepository = statisticsRepository;
   }

   public ResponseEntity<JsonNode> getTxnStatistics(HttpHeaders httpHeaders, GetTxnStatisticsDataRequest getTxnStatisticsDataRequest) {
      HeaderProcessingHelper.setRequestHeaders(getTxnStatisticsDataRequest, httpHeaders);
      if (getTxnStatisticsDataRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         GetTxnStatisticsDataResponse txnStatisticsResponse = this.statisticsRepository.getTxnStatistics(getTxnStatisticsDataRequest);
         return txnStatisticsResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(txnStatisticsResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(txnStatisticsResponse), HttpStatus.OK);
      }
   }

   public ResponseEntity<JsonNode> getTxnTypeStatistics(HttpHeaders headers, GetTxnTypeStatisticsDataRequest getTxnTypeStatisticsDataRequest) {
      HeaderProcessingHelper.setRequestHeaders(getTxnTypeStatisticsDataRequest, headers);
      if (getTxnTypeStatisticsDataRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         GetTxnTypeStatisticsDataResponse txnStatisticsResponse = this.statisticsRepository.getTxnTypeStatistics(getTxnTypeStatisticsDataRequest);
         return txnStatisticsResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(txnStatisticsResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(txnStatisticsResponse), HttpStatus.OK);
      }
   }

   public ResponseEntity<JsonNode> getPaymentModeStatistics(HttpHeaders headers, GetPaymentModeStatisticsDataRequest getPaymentModeStatisticsDataRequest) {
      HeaderProcessingHelper.setRequestHeaders(getPaymentModeStatisticsDataRequest, headers);
      if (getPaymentModeStatisticsDataRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         GetPaymentModeStatisticsDataResponse paymentModeStatisticsResponse = this.statisticsRepository.getPaymentModeStatistics(getPaymentModeStatisticsDataRequest);
         return paymentModeStatisticsResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(paymentModeStatisticsResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(paymentModeStatisticsResponse), HttpStatus.OK);
      }
   }

   public ResponseEntity<JsonNode> getSettlementStatisticData(HttpHeaders httpHeaders, GetSettlementStatisticDataRequest getSettlementStatisticDataRequest) {
      HeaderProcessingHelper.setRequestHeaders(getSettlementStatisticDataRequest, httpHeaders);
      if (getSettlementStatisticDataRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("Bad Request"), HttpStatus.OK);
      } else {
         GetSettlementStatisticsResponse getSettlementStatisticsResponse = this.statisticsRepository.getSettlementStatisticData(getSettlementStatisticDataRequest);
         return getSettlementStatisticsResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(getSettlementStatisticsResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(getSettlementStatisticsResponse), HttpStatus.OK);
      }
   }

   public ResponseEntity<JsonNode> getCashFlowStatistic(HttpHeaders httpHeaders, GetCashFlowStatisticsDataRequest getCashFlowStatisticsDataRequest) {
      HeaderProcessingHelper.setRequestHeaders(getCashFlowStatisticsDataRequest, httpHeaders);
      if (getCashFlowStatisticsDataRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("Bad Request"), HttpStatus.OK);
      } else {
         GetCashFlowStatisticsResponse getCashStatisticsResponse = this.statisticsRepository.getCashFlowStatistic(getCashFlowStatisticsDataRequest);
         return getCashStatisticsResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(getCashStatisticsResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(getCashStatisticsResponse), HttpStatus.OK);
      }
   }

   public ResponseEntity<JsonNode> agentCommissionStatistics(HttpHeaders httpHeaders, AgentCommissionStatisticsRequest agentCommissionStatisticsRequest) {
      HeaderProcessingHelper.setRequestHeaders(agentCommissionStatisticsRequest, httpHeaders);
      if (agentCommissionStatisticsRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         AgentCommissionStatisticsResponse agentCommissionStatisticsResponse = this.statisticsRepository.agentCommissionStatistics(agentCommissionStatisticsRequest);
         return agentCommissionStatisticsResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(agentCommissionStatisticsResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(agentCommissionStatisticsResponse), HttpStatus.OK);
      }
   }

   public ResponseEntity<JsonNode> getRevenueStatistics(HttpHeaders httpHeaders, GetTxnStatisticsDataRequest getTxnStatisticsDataRequest) {
      HeaderProcessingHelper.setRequestHeaders(getTxnStatisticsDataRequest, httpHeaders);
      if (getTxnStatisticsDataRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         GetRevenueStatisticsResponse getRevenueStatisticsResponse = this.statisticsRepository.getRevenueStatistics(getTxnStatisticsDataRequest);
         return getRevenueStatisticsResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(getRevenueStatisticsResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(getRevenueStatisticsResponse), HttpStatus.OK);
      }
   }

   public ResponseEntity<JsonNode> getTxnTypeWiseStatistics(HttpHeaders httpHeaders, GetTxnStatisticsDataRequest getTxnStatisticsDataRequest) {
      HeaderProcessingHelper.setRequestHeaders(getTxnStatisticsDataRequest, httpHeaders);
      if (getTxnStatisticsDataRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         GetRevenueStatisticsByTxnTypeResponse getRevenueStatisticsByTxnTypeResponse = this.statisticsRepository.getTxnTypeWiseStatistics(getTxnStatisticsDataRequest);
         return getRevenueStatisticsByTxnTypeResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(getRevenueStatisticsByTxnTypeResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(getRevenueStatisticsByTxnTypeResponse), HttpStatus.OK);
      }
   }

   public ResponseEntity<JsonNode> getNodalBankStatistics(HttpHeaders headers, GetNodalBankStatisticsRequest getNodalBankStatisticsRequest) {
      HeaderProcessingHelper.setRequestHeaders(getNodalBankStatisticsRequest, headers);
      if (getNodalBankStatisticsRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         CommonAPIDataResponse commonAPIDataResponse = this.statisticsRepository.getNodalBankStatistics(getNodalBankStatisticsRequest);
         return commonAPIDataResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(commonAPIDataResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(commonAPIDataResponse), HttpStatus.OK);
      }
   }

   public ResponseEntity<JsonNode> userWiseTransactionStatistics(HttpHeaders headers, UserWiseTransactionStatisticsRequest userWiseTransactionStatisticsRequest) {
      HeaderProcessingHelper.setRequestHeaders(userWiseTransactionStatisticsRequest, headers);
      if (userWiseTransactionStatisticsRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         UserWiseTransactionStatisticsResponse userWiseTransactionStatisticsResponse = this.statisticsRepository.userWiseTransactionStatistics(userWiseTransactionStatisticsRequest);
         return userWiseTransactionStatisticsResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(userWiseTransactionStatisticsResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(userWiseTransactionStatisticsResponse), HttpStatus.OK);
      }
   }

   public ResponseEntity<JsonNode> getRevenueSharingStatistics(HttpHeaders headers, RevenueSharingStatisticsRequest revenueSharingStatisticsRequest) {
      HeaderProcessingHelper.setRequestHeaders(revenueSharingStatisticsRequest, headers);
      if (revenueSharingStatisticsRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         RevenueSharingStatisticsResponse revenueSharingStatisticsResponse = this.statisticsRepository.getRevenueSharingStatistics(revenueSharingStatisticsRequest);
         return revenueSharingStatisticsResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(revenueSharingStatisticsResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(revenueSharingStatisticsResponse), HttpStatus.OK);
      }
   }

   public ResponseEntity<JsonNode> getThirdPartyVendorsStatistics(HttpHeaders headers, ThirdPartyVendorsStatisticsRequest thirdPartyVendorsStatisticsRequest) {
      HeaderProcessingHelper.setRequestHeaders(thirdPartyVendorsStatisticsRequest, headers);
      if (thirdPartyVendorsStatisticsRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         ThirdPartyVendorsStatisticsResponse thirdPartyVendorsStatisticsResponse = this.statisticsRepository.getThirdPartyVendorsStatistics(thirdPartyVendorsStatisticsRequest);
         return thirdPartyVendorsStatisticsResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(thirdPartyVendorsStatisticsResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(thirdPartyVendorsStatisticsResponse), HttpStatus.OK);
      }
   }

   public ResponseEntity<JsonNode> getThirdPartyProviderStatistics(HttpHeaders headers, ThirdPartyProviderStatisticsRequest thirdPartyProviderStatisticsRequest) {
      HeaderProcessingHelper.setRequestHeaders(thirdPartyProviderStatisticsRequest, headers);
      if (thirdPartyProviderStatisticsRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         ThirdPartyProviderStatisticsResponse thirdPartyProviderStatisticsResponse = this.statisticsRepository.getThirdPartyProviderStatistics(thirdPartyProviderStatisticsRequest);
         return thirdPartyProviderStatisticsResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(thirdPartyProviderStatisticsResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(thirdPartyProviderStatisticsResponse), HttpStatus.OK);
      }
   }

   public ResponseEntity<JsonNode> corporateWiseStatistics(HttpHeaders headers, CorporateStatisticsRequest corporateStatisticsRequest) {
      HeaderProcessingHelper.setRequestHeaders(corporateStatisticsRequest, headers);
      if (corporateStatisticsRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         CommonAPIDataResponse commonAPIDataResponse = this.statisticsRepository.corporateWiseStatistics(corporateStatisticsRequest);
         return commonAPIDataResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(commonAPIDataResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(commonAPIDataResponse), HttpStatus.OK);
      }
   }
}
