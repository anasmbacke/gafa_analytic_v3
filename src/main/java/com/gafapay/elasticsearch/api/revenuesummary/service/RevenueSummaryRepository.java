package com.gafapay.elasticsearch.api.revenuesummary.service;

import com.gafapay.elasticsearch.api.revenuesummary.model.request.GetAllRevenueSummaryDataRequest;
import com.gafapay.elasticsearch.api.revenuesummary.model.request.GetRevenueSummaryDataRequest;
import com.gafapay.elasticsearch.api.revenuesummary.model.response.GetAllRevenueSummaryDataResponse;
import com.gafapay.elasticsearch.api.revenuesummary.model.response.GetRevenueSummaryDataResponse;
import com.gafapay.elasticsearch.api.revenuesummary.model.response.GetRevenueSummaryReportResponse;
import com.gafapay.elasticsearch.api.statistics.model.request.GetTxnStatisticsDataRequest;

public interface RevenueSummaryRepository {
   GetAllRevenueSummaryDataResponse getAllRevenueSummary(GetAllRevenueSummaryDataRequest getAllRevenueSummaryDataRequest);

   GetRevenueSummaryDataResponse getRevenueSummary(GetRevenueSummaryDataRequest getRevenueSummaryDataRequest);

   GetRevenueSummaryReportResponse getRevenueSummaryReport(GetTxnStatisticsDataRequest getTxnStatisticsDataRequest);
}
