package com.gafapay.elasticsearch.api.statistics.service;

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
import com.gafapay.elasticsearch.api.statistics.model.response.CorporateStatisticsResponse;
import com.gafapay.elasticsearch.api.statistics.model.response.GetCashFlowStatisticsResponse;
import com.gafapay.elasticsearch.api.statistics.model.response.GetNodalBankStatisticsResponse;
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

public interface StatisticsRepository {
   GetTxnStatisticsDataResponse getTxnStatistics(GetTxnStatisticsDataRequest getTxnStatisticsDataRequest);

   GetTxnTypeStatisticsDataResponse getTxnTypeStatistics(GetTxnTypeStatisticsDataRequest getTxnTypeStatisticsDataRequest);

   GetPaymentModeStatisticsDataResponse getPaymentModeStatistics(GetPaymentModeStatisticsDataRequest getPaymentModeStatisticsDataRequest);

   GetSettlementStatisticsResponse getSettlementStatisticData(GetSettlementStatisticDataRequest getSettlementStatisticDataRequest);

   GetCashFlowStatisticsResponse getCashFlowStatistic(GetCashFlowStatisticsDataRequest getCashFlowStatisticsDataRequest);

   AgentCommissionStatisticsResponse agentCommissionStatistics(AgentCommissionStatisticsRequest agentCommissionStatisticsRequest);

   GetRevenueStatisticsResponse getRevenueStatistics(GetTxnStatisticsDataRequest getTxnStatisticsDataRequest);

   GetRevenueStatisticsByTxnTypeResponse getTxnTypeWiseStatistics(GetTxnStatisticsDataRequest getTxnStatisticsDataRequest);

   GetNodalBankStatisticsResponse getNodalBankStatistics(GetNodalBankStatisticsRequest getNodalBankStatisticsRequest);

   UserWiseTransactionStatisticsResponse userWiseTransactionStatistics(UserWiseTransactionStatisticsRequest userWiseTransactionStatisticsRequest);

   RevenueSharingStatisticsResponse getRevenueSharingStatistics(RevenueSharingStatisticsRequest revenueSharingStatisticsRequest);

   ThirdPartyVendorsStatisticsResponse getThirdPartyVendorsStatistics(ThirdPartyVendorsStatisticsRequest thirdPartyVendorsStatisticsRequest);

   ThirdPartyProviderStatisticsResponse getThirdPartyProviderStatistics(ThirdPartyProviderStatisticsRequest thirdPartyProviderStatisticsRequest);

   CorporateStatisticsResponse corporateWiseStatistics(CorporateStatisticsRequest corporateStatisticsRequest);
}
