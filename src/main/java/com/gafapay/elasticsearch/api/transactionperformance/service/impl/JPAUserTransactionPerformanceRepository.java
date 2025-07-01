package com.gafapay.elasticsearch.api.transactionperformance.service.impl;

import com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.dao.UserMasterDataQueryDao;
import com.gafapay.elasticsearch.api.transactionperformance.model.request.UserTransactionPerformanceRequest;
import com.gafapay.elasticsearch.api.transactionperformance.model.response.UserTransactionPerformanceResponse;
import com.gafapay.elasticsearch.api.transactionperformance.service.UserTransactionPerformanceRepository;
import com.gafapay.elasticsearch.api.txnmaster.model.TxnMaster;
import com.gafapay.elasticsearch.helper.ElasticSearchUtility;
import com.gafapay.elasticsearch.utils.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import org.elasticsearch.index.IndexNotFoundException;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
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
public class JPAUserTransactionPerformanceRepository implements UserTransactionPerformanceRepository {
   @Autowired
   ElasticSearchUtility elasticSearchUtility;
   @Autowired
   ElasticsearchRestTemplate elasticsearchRestTemplate;
   @Autowired
   UserMasterDataQueryDao userMasterDataQueryDao;

   public JPAUserTransactionPerformanceRepository() {
   }

   public UserTransactionPerformanceResponse userTransactionPerformance(UserTransactionPerformanceRequest userTransactionPerformanceRequest) {
      UserTransactionPerformanceResponse userTransactionPerformanceResponse = new UserTransactionPerformanceResponse();
      userTransactionPerformanceRequest.getDropdown_filter().setUser_type(2);
      BoolQueryBuilder boolQueryBuilder = this.elasticSearchUtility.getDefaultFieldBoolQueryBuilder(userTransactionPerformanceRequest.getCompany_id(), userTransactionPerformanceRequest.getDate_filter(), userTransactionPerformanceRequest.getDropdown_filter(), userTransactionPerformanceRequest.getSearch_filter());
      TermsAggregationBuilder groupBySender = (TermsAggregationBuilder)((TermsAggregationBuilder)((TermsAggregationBuilder)AggregationBuilders.terms("group_by_sender").field("debit_account_type_id.keyword")).subAggregation(AggregationBuilders.sum("total_amount").field("debit_amount"))).subAggregation(AggregationBuilders.count("total_count").field("debit_amount"));
      NativeSearchQuery searchQuery = (new NativeSearchQueryBuilder()).withQuery(boolQueryBuilder).withAggregations(new AbstractAggregationBuilder[]{groupBySender}).build();
      SearchHits<TxnMaster> response = null;

      try {
         response = this.elasticsearchRestTemplate.search(searchQuery, TxnMaster.class);
      } catch (IndexNotFoundException | NoSuchIndexException var21) {
         Logger.error("Index Not Found Exception occure!!");
         userTransactionPerformanceResponse.setValidationMessage("TXN_MASTER_DATA_NOT_FOUND");
         userTransactionPerformanceResponse.setCheckValidationFailed(true);
         return userTransactionPerformanceResponse;
      }

      if (response.isEmpty()) {
         userTransactionPerformanceResponse.setValidationMessage("TXN_MASTER_DATA_NOT_FOUND");
         userTransactionPerformanceResponse.setCheckValidationFailed(true);
         return userTransactionPerformanceResponse;
      } else {
         Map<String, Aggregation> aggregationMap = ((Aggregations)((AggregationsContainer)Objects.requireNonNull(response.getAggregations())).aggregations()).asMap();
         AtomicReference<Long> noOfPayments = new AtomicReference(0L);
         AtomicReference<Double> totalPayments = new AtomicReference((double)0.0F);
         List<String> userIds = new ArrayList();
         Map<String, Object> stringObjectMap = new HashMap();
         Map<String, Object> finalMap = new HashMap();
         Map<String, Object> userWiseMap = new HashMap();
         ParsedDateHistogram byTxnDate = (ParsedDateHistogram)aggregationMap.get("by_txn_date");
         ((ParsedStringTerms)aggregationMap.get("group_by_sender")).getBuckets().forEach((listOfAccounts) -> {
            Map<String, Aggregation> bucketAggregationMap = listOfAccounts.getAggregations().getAsMap();
            userIds.add(listOfAccounts.getKeyAsString());
            userWiseMap.put(listOfAccounts.getKeyAsString(), ((ParsedSum)bucketAggregationMap.get("total_amount")).getValue());
            noOfPayments.set((Long)noOfPayments.get() + ((ParsedValueCount)bucketAggregationMap.get("total_count")).getValue());
            totalPayments.set((Double)totalPayments.get() + ((ParsedSum)bucketAggregationMap.get("total_amount")).getValue());
         });
         stringObjectMap.put("no_of_payments", noOfPayments);
         stringObjectMap.put("total_payments", totalPayments);
         stringObjectMap.put("user_count", userIds.size());
         finalMap.put("avg_transaction_amount_per_customer", (Double)totalPayments.get() / (double)userIds.size());
         finalMap.put("avg_transaction_per_customer", (Long)noOfPayments.get() / (long)userIds.size());
         this.elasticSearchUtility.fetchTransactionWithUserFirstTime(userTransactionPerformanceRequest.getCompany_id(), (String)null, userIds, stringObjectMap, userTransactionPerformanceRequest.getDate_filter().getStart_date(), userWiseMap);
         stringObjectMap.put("returning_customer", userIds.size() - Integer.parseInt(stringObjectMap.get("no_of_new_customers").toString()));
         finalMap.put("new_customer_percentage", Double.parseDouble(stringObjectMap.get("no_of_new_customers").toString()) / (double)userIds.size() * (double)100.0F);
         finalMap.put("returning_customer_percentage", Double.parseDouble(stringObjectMap.get("returning_customer").toString()) / (double)userIds.size() * (double)100.0F);
         long noOfNewCustomers = Long.parseLong(stringObjectMap.get("no_of_new_customers").toString());
         double newCustomerPayments = Double.parseDouble(stringObjectMap.get("new_customer_payments").toString());
         double avgFirstTimeTxnPerCustomer = (double)0.0F;
         if (noOfNewCustomers != 0L) {
            avgFirstTimeTxnPerCustomer = newCustomerPayments / (double)noOfNewCustomers;
         }

         finalMap.put("avg_first_time_txn_per_customer", avgFirstTimeTxnPerCustomer);
         this.userMasterDataQueryDao.findByCompanyIdAndDateWiseAllCountOfUser(userTransactionPerformanceRequest.getCompany_id(), userTransactionPerformanceRequest.getDate_filter().getStart_date(), userTransactionPerformanceRequest.getDate_filter().getEnd_date(), finalMap);
         userTransactionPerformanceResponse.setUserTransactionPerformance(finalMap);
         return userTransactionPerformanceResponse;
      }
   }
}
