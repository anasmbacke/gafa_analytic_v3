package com.gafapay.elasticsearch.api.transactionperformance.service;

import com.gafapay.elasticsearch.api.transactionperformance.model.request.UserTransactionPerformanceRequest;
import com.gafapay.elasticsearch.api.transactionperformance.model.response.UserTransactionPerformanceResponse;

public interface UserTransactionPerformanceRepository {
   UserTransactionPerformanceResponse userTransactionPerformance(UserTransactionPerformanceRequest userTransactionPerformanceRequest);
}
