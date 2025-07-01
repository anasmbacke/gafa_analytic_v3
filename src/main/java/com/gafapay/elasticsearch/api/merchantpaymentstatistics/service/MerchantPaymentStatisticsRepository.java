package com.gafapay.elasticsearch.api.merchantpaymentstatistics.service;

import com.gafapay.elasticsearch.api.merchantpaymentstatistics.model.request.MerchantPaymentStatisticsRequest;
import com.gafapay.elasticsearch.api.merchantpaymentstatistics.model.response.MerchantPaymentStatisticsResponse;

public interface MerchantPaymentStatisticsRepository {
   MerchantPaymentStatisticsResponse merchantPaymentStatistics(MerchantPaymentStatisticsRequest merchantPaymentStatisticsRequest);
}
