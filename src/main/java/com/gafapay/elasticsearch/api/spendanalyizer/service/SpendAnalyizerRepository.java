package com.gafapay.elasticsearch.api.spendanalyizer.service;

import com.gafapay.elasticsearch.api.spendanalyizer.model.request.TransferByCategoryStatisticsRequest;
import com.gafapay.elasticsearch.api.spendanalyizer.model.request.TransferByUserStatisticsRequest;
import com.gafapay.elasticsearch.api.spendanalyizer.model.request.TransferByUtilityStatisticsRequest;
import com.gafapay.elasticsearch.api.spendanalyizer.model.response.TransferByCategoryStatisticsResponse;
import com.gafapay.elasticsearch.api.spendanalyizer.model.response.TransferByUserStatisticsResponse;
import com.gafapay.elasticsearch.api.spendanalyizer.model.response.TransferByUtilityStatisticsResponse;

public interface SpendAnalyizerRepository {
   TransferByUserStatisticsResponse transferByUserStatistics(TransferByUserStatisticsRequest transferByUserStatisticsRequest);

   TransferByCategoryStatisticsResponse transferByCategoryStatistics(TransferByCategoryStatisticsRequest transferByCategoryStatisticsRequest);

   TransferByUtilityStatisticsResponse transferByUtilityStatistics(TransferByUtilityStatisticsRequest transferByUtilityStatisticsRequest);
}
