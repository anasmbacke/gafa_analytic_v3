package com.gafapay.elasticsearch.api.merchantpaymentstatistics.service.impl;

import com.gafapay.elasticsearch.api.merchantpaymentstatistics.model.request.MerchantPaymentStatisticsRequest;
import com.gafapay.elasticsearch.api.merchantpaymentstatistics.model.response.MerchantPaymentStatisticsResponse;
import com.gafapay.elasticsearch.api.merchantpaymentstatistics.service.MerchantPaymentStatisticsRepository;
import com.gafapay.elasticsearch.api.txnmaster.model.TxnMaster;
import com.gafapay.elasticsearch.helper.ElasticSearchUtility;
import com.gafapay.elasticsearch.utils.Logger;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import org.elasticsearch.index.IndexNotFoundException;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.ParsedDateHistogram;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ParsedSum;
import org.elasticsearch.search.aggregations.metrics.ParsedValueCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.NoSuchIndexException;
import org.springframework.data.elasticsearch.core.AggregationsContainer;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

@Service
public class JPAMerchantPaymentStatisticsRepository implements MerchantPaymentStatisticsRepository {
   @Autowired
   private ElasticSearchUtility elasticSearchUtility;
   @Autowired
   private ElasticsearchRestTemplate elasticsearchRestTemplate;

   public JPAMerchantPaymentStatisticsRepository() {
   }

   public MerchantPaymentStatisticsResponse merchantPaymentStatistics(MerchantPaymentStatisticsRequest merchantPaymentStatisticsRequest) {
      MerchantPaymentStatisticsResponse merchantPaymentStatisticsResponse = new MerchantPaymentStatisticsResponse();
      BoolQueryBuilder boolQueryBuilder = this.elasticSearchUtility.getDefaultFieldBoolQueryBuilder(merchantPaymentStatisticsRequest.getCompany_id(), merchantPaymentStatisticsRequest.getDate_filter(), merchantPaymentStatisticsRequest.getDropdown_filter(), merchantPaymentStatisticsRequest.getSearch_filter());
      if (merchantPaymentStatisticsRequest.getCredit_account_type_id() != null) {
         boolQueryBuilder.must(QueryBuilders.matchQuery("credit_account_type_id", merchantPaymentStatisticsRequest.getCredit_account_type_id()));
      }

      TermsAggregationBuilder aggregationBuilders = (TermsAggregationBuilder)((TermsAggregationBuilder)((TermsAggregationBuilder)AggregationBuilders.terms("group_by_sender").field("debit_account_type_id.keyword")).subAggregation(AggregationBuilders.sum("total_amount").field("debit_amount"))).subAggregation(AggregationBuilders.count("total_count").field("debit_amount"));
      DateHistogramInterval intervalType = this.elasticSearchUtility.getFilterType(merchantPaymentStatisticsRequest.getDate_filter());
      DateHistogramAggregationBuilder dateWiseAggregationBuilder = this.elasticSearchUtility.buildHistogramAggregationBuilder("by_txn_date", intervalType, merchantPaymentStatisticsRequest.getDate_filter(), AggregationBuilders.count("total_count").field(merchantPaymentStatisticsRequest.getDate_filter().getDate_field_name()), AggregationBuilders.sum("total_amount").field("debit_amount"));
      NativeSearchQuery searchQuery = (new NativeSearchQueryBuilder()).withQuery(boolQueryBuilder).withAggregations(new AbstractAggregationBuilder[]{aggregationBuilders}).withAggregations(new AbstractAggregationBuilder[]{dateWiseAggregationBuilder}).build();
      SearchHits<TxnMaster> response = null;

      try {
         response = this.elasticsearchRestTemplate.search(searchQuery, TxnMaster.class);
      } catch (IndexNotFoundException | NoSuchIndexException var17) {
         Logger.error("Index Not Found Exception occure!!");
         merchantPaymentStatisticsResponse.setValidationMessage("TXN_MASTER_DATA_NOT_FOUND");
         merchantPaymentStatisticsResponse.setCheckValidationFailed(true);
         return merchantPaymentStatisticsResponse;
      }

      if (response.isEmpty()) {
         merchantPaymentStatisticsResponse.setValidationMessage("TXN_MASTER_DATA_NOT_FOUND");
         merchantPaymentStatisticsResponse.setCheckValidationFailed(true);
         return merchantPaymentStatisticsResponse;
      } else {
         Map<String, Aggregation> aggregationMap = ((Aggregations)((AggregationsContainer)Objects.requireNonNull(response.getAggregations())).aggregations()).asMap();
         AtomicReference<Long> noOfPayments = new AtomicReference(0L);
         AtomicReference<Double> totalPayments = new AtomicReference((double)0.0F);
         List<String> userIds = new ArrayList();
         Map<String, Object> stringObjectMap = new HashMap();
         Map<String, Object> userWiseMap = new HashMap();
         ParsedDateHistogram byTxnDate = (ParsedDateHistogram)aggregationMap.get("by_txn_date");
         ((ParsedStringTerms)aggregationMap.get("group_by_sender")).getBuckets().forEach((listOfAccounts) -> {
            Map<String, Aggregation> bucketAggregationMap = listOfAccounts.getAggregations().getAsMap();
            userIds.add(listOfAccounts.getKeyAsString());
            userWiseMap.put(listOfAccounts.getKeyAsString(), ((ParsedSum)bucketAggregationMap.get("total_amount")).getValue());
            noOfPayments.set((Long)noOfPayments.get() + ((ParsedValueCount)bucketAggregationMap.get("total_count")).getValue());
            totalPayments.set((Double)totalPayments.get() + ((ParsedSum)bucketAggregationMap.get("total_amount")).getValue());
         });
         List<Map<String, Object>> transactions = new ArrayList(byTxnDate.getBuckets().size());
         byTxnDate.getBuckets().parallelStream().forEachOrdered((bucket) -> {
            Map<String, Object> map = new HashMap();
            Map<String, Aggregation> aggregation = bucket.getAggregations().getAsMap();
            map.put("timestamp", ZonedDateTime.parse(bucket.getKey().toString()).toEpochSecond());
            map.put("total_count", ((ParsedValueCount)aggregation.get("total_count")).value());
            map.put("total_amount", aggregation.containsKey("total_amount") && !Objects.isNull(aggregation.get("total_amount")) ? ((ParsedSum)aggregation.get("total_amount")).value() : null);
            transactions.add(map);
         });
         stringObjectMap.put("transactions", transactions);
         stringObjectMap.put("no_of_payments", noOfPayments);
         stringObjectMap.put("total_payments", totalPayments);
         stringObjectMap.put("avg_value_of_transactions", (Double)totalPayments.get() / (double)(Long)noOfPayments.get());
         this.elasticSearchUtility.fetchTransactionWithUserFirstTime(merchantPaymentStatisticsRequest.getCompany_id(), merchantPaymentStatisticsRequest.getCredit_account_type_id(), userIds, stringObjectMap, merchantPaymentStatisticsRequest.getDate_filter().getStart_date(), userWiseMap);
         merchantPaymentStatisticsResponse.setMerchantPaymentStatistics(stringObjectMap);
         return merchantPaymentStatisticsResponse;
      }
   }
}
