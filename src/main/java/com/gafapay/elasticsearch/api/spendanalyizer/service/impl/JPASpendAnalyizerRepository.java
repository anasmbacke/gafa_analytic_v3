package com.gafapay.elasticsearch.api.spendanalyizer.service.impl;

import com.gafapay.elasticsearch.api.spendanalyizer.model.request.TransferByCategoryStatisticsRequest;
import com.gafapay.elasticsearch.api.spendanalyizer.model.request.TransferByUserStatisticsRequest;
import com.gafapay.elasticsearch.api.spendanalyizer.model.request.TransferByUtilityStatisticsRequest;
import com.gafapay.elasticsearch.api.spendanalyizer.model.response.TransferByCategoryStatisticsResponse;
import com.gafapay.elasticsearch.api.spendanalyizer.model.response.TransferByUserStatisticsResponse;
import com.gafapay.elasticsearch.api.spendanalyizer.model.response.TransferByUtilityStatisticsResponse;
import com.gafapay.elasticsearch.api.spendanalyizer.service.SpendAnalyizerRepository;
import com.gafapay.elasticsearch.api.txnmaster.model.TxnMaster;
import com.gafapay.elasticsearch.helper.ElasticSearchUtility;
import com.gafapay.elasticsearch.utils.Logger;
import com.gafapay.elasticsearch.utils.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.elasticsearch.common.Strings;
import org.elasticsearch.index.IndexNotFoundException;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.PipelineAggregatorBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.ParsedDateHistogram;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ParsedSum;
import org.elasticsearch.search.aggregations.metrics.ParsedTopHits;
import org.elasticsearch.search.aggregations.metrics.ParsedValueCount;
import org.elasticsearch.search.aggregations.metrics.TopHits;
import org.elasticsearch.search.aggregations.pipeline.ParsedSimpleValue;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.NoSuchIndexException;
import org.springframework.data.elasticsearch.core.AggregationsContainer;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

@Service
public class JPASpendAnalyizerRepository implements SpendAnalyizerRepository {
   @Autowired
   private ElasticSearchUtility elasticSearchUtility;
   @Autowired
   private ElasticsearchRestTemplate elasticsearchRestTemplate;

   public JPASpendAnalyizerRepository() {
   }

   public TransferByCategoryStatisticsResponse transferByCategoryStatistics(TransferByCategoryStatisticsRequest transferByCategoryStatisticsRequest) {
      TransferByCategoryStatisticsResponse transferByCategoryStatisticsResponse = new TransferByCategoryStatisticsResponse();
      BoolQueryBuilder boolQueryBuilder = this.elasticSearchUtility.getDefaultFieldBoolQueryBuilder(transferByCategoryStatisticsRequest.getCompany_id(), transferByCategoryStatisticsRequest.getDate_filter(), transferByCategoryStatisticsRequest.getDropdown_filter(), transferByCategoryStatisticsRequest.getSearch_filter());
      if (transferByCategoryStatisticsRequest.getDebit_account_type_id() != null) {
         boolQueryBuilder.must(QueryBuilders.matchQuery("debit_account_type_id", transferByCategoryStatisticsRequest.getDebit_account_type_id()));
      }

      boolQueryBuilder.must(QueryBuilders.matchQuery("credit_account_type", 3));
      boolQueryBuilder.must(QueryBuilders.matchQuery("txn_status", 3));
      transferByCategoryStatisticsRequest.getDate_filter().setStart_date(transferByCategoryStatisticsRequest.getDate_filter().getStart_date() + 19800L);
      transferByCategoryStatisticsRequest.getDate_filter().setEnd_date(transferByCategoryStatisticsRequest.getDate_filter().getEnd_date() + 19800L);
      DateHistogramInterval intervalType = this.elasticSearchUtility.getFilterType(transferByCategoryStatisticsRequest.getDate_filter());
      TermsAggregationBuilder aggregationBuilders = (TermsAggregationBuilder)AggregationBuilders.terms("group_by_business_category_wise").field("merchant_info.business_category.id.keyword");
      DateHistogramAggregationBuilder dateWiseAggregationBuilder = this.elasticSearchUtility.buildHistogramAggregationBuilder(transferByCategoryStatisticsRequest.getDate_filter().getDate_field_name(), intervalType, transferByCategoryStatisticsRequest.getDate_filter(), AggregationBuilders.count("total_count").field("debit_amount"), AggregationBuilders.sum("total_amount").field("debit_amount"));
      aggregationBuilders.subAggregation(PipelineAggregatorBuilders.sumBucket("total_amount_of_statistics", transferByCategoryStatisticsRequest.getDate_filter().getDate_field_name() + ">total_amount"));
      aggregationBuilders.subAggregation(PipelineAggregatorBuilders.sumBucket("total_count_of_statistics", transferByCategoryStatisticsRequest.getDate_filter().getDate_field_name() + ">total_count"));
      List<FieldSortBuilder> sorts = new ArrayList();
      sorts.add((FieldSortBuilder)(new FieldSortBuilder("total_amount_of_statistics")).order(SortOrder.DESC));
      aggregationBuilders.subAggregation(dateWiseAggregationBuilder);
      aggregationBuilders.subAggregation(AggregationBuilders.topHits("top_hits").size(1).fetchSource(new String[]{"merchant_info.business_category"}, Strings.EMPTY_ARRAY));
      NativeSearchQuery searchQuery = (new NativeSearchQueryBuilder()).withQuery(boolQueryBuilder).withAggregations(new AbstractAggregationBuilder[]{aggregationBuilders}).build();
      SearchHits<TxnMaster> response = null;

      try {
         response = this.elasticsearchRestTemplate.search(searchQuery, TxnMaster.class);
      } catch (IndexNotFoundException | NoSuchIndexException var12) {
         Logger.error("Index Not Found Exception occure!!");
         transferByCategoryStatisticsResponse.setValidationMessage("TXN_MASTER_DATA_NOT_FOUND");
         transferByCategoryStatisticsResponse.setCheckValidationFailed(true);
         return transferByCategoryStatisticsResponse;
      }

      if (response.isEmpty()) {
         transferByCategoryStatisticsResponse.setValidationMessage("TXN_MASTER_DATA_NOT_FOUND");
         transferByCategoryStatisticsResponse.setCheckValidationFailed(true);
         return transferByCategoryStatisticsResponse;
      } else {
         Map<String, Aggregation> aggregationMap = ((Aggregations)((AggregationsContainer)Objects.requireNonNull(response.getAggregations())).aggregations()).asMap();
         List<TransferByCategoryStatisticsResponse.TransferByCategoryStatisticsDetails> spendAnalyticsDetailsList = new ArrayList();
         ((ParsedStringTerms)aggregationMap.get("group_by_business_category_wise")).getBuckets().forEach((listOfAccounts) -> {
            Map<String, Aggregation> bucketAggregationMap = listOfAccounts.getAggregations().getAsMap();
            TransferByCategoryStatisticsResponse.TransferByCategoryStatisticsDetails spendAnalyticsDetails = new TransferByCategoryStatisticsResponse.TransferByCategoryStatisticsDetails();
            Map<String, Object> finalMapOfData = new HashMap();
            List<Map<String, Object>> listOfStatisticsMapData = new ArrayList();
            ((ParsedDateHistogram)bucketAggregationMap.get(transferByCategoryStatisticsRequest.getDate_filter().getDate_field_name())).getBuckets().forEach((e) -> {
               Map<String, Object> stringObjectMap = new HashMap();
               stringObjectMap.put("timestamp", Long.parseLong(e.getKeyAsString()) / 1000L);
               stringObjectMap.put("total_count", ((ParsedValueCount)e.getAggregations().getAsMap().get("total_count")).value());
               stringObjectMap.put("total_amount", ((ParsedSum)e.getAggregations().getAsMap().get("total_amount")).value());
               listOfStatisticsMapData.add(stringObjectMap);
            });
            finalMapOfData.put("statistics", listOfStatisticsMapData);
            finalMapOfData.put("id", listOfAccounts.getKeyAsString());
            finalMapOfData.put("total_statistics_amount", ((ParsedSimpleValue)bucketAggregationMap.get("total_amount_of_statistics")).value());
            finalMapOfData.put("total_statistics_count", ((ParsedSimpleValue)bucketAggregationMap.get("total_count_of_statistics")).value());
            TopHits topHits = (ParsedTopHits)bucketAggregationMap.get("top_hits");
            if (topHits != null) {
               SearchHit hit = topHits.getHits().getAt(0);
               Map<String, Object> map = Utils.convertJsonStringToHashMap(hit.getSourceAsString());
               if (map != null) {
                  Map<String, Object> merchantInfo = map.containsKey("merchant_info") && !Objects.isNull(map.get("merchant_info")) ? (Map)map.get("merchant_info") : null;
                  if (merchantInfo != null) {
                     Map<String, Object> businessCategory = merchantInfo.containsKey("business_category") && !Objects.isNull(merchantInfo.get("business_category")) ? (Map)merchantInfo.get("business_category") : null;
                     if (businessCategory != null) {
                        for(String key : businessCategory.keySet()) {
                           if (!key.equalsIgnoreCase("id")) {
                              finalMapOfData.put(key, businessCategory.getOrDefault(key, (Object)null));
                           }
                        }
                     }
                  }
               }
            }

            spendAnalyticsDetails.setSpendAnalyticsCategoryInfo(finalMapOfData);
            spendAnalyticsDetailsList.add(spendAnalyticsDetails);
         });
         transferByCategoryStatisticsResponse.setTransferByCategoryStatisticsList(spendAnalyticsDetailsList);
         return transferByCategoryStatisticsResponse;
      }
   }

   public TransferByUtilityStatisticsResponse transferByUtilityStatistics(TransferByUtilityStatisticsRequest transferByUtilityStatisticsRequest) {
      TransferByUtilityStatisticsResponse transferByUtilityStatisticsResponse = new TransferByUtilityStatisticsResponse();
      BoolQueryBuilder boolQueryBuilder = this.elasticSearchUtility.getDefaultFieldBoolQueryBuilder(transferByUtilityStatisticsRequest.getCompany_id(), transferByUtilityStatisticsRequest.getDate_filter(), transferByUtilityStatisticsRequest.getDropdown_filter(), transferByUtilityStatisticsRequest.getSearch_filter());
      boolQueryBuilder.must(QueryBuilders.matchQuery("txn_status", 3));
      if (transferByUtilityStatisticsRequest.getDebit_account_type_id() != null) {
         boolQueryBuilder.must(QueryBuilders.matchQuery("debit_account_type_id", transferByUtilityStatisticsRequest.getDebit_account_type_id()));
      }

      transferByUtilityStatisticsRequest.getDate_filter().setStart_date(transferByUtilityStatisticsRequest.getDate_filter().getStart_date() + 19800L);
      transferByUtilityStatisticsRequest.getDate_filter().setEnd_date(transferByUtilityStatisticsRequest.getDate_filter().getEnd_date() + 19800L);
      DateHistogramInterval intervalType = this.elasticSearchUtility.getFilterType(transferByUtilityStatisticsRequest.getDate_filter());
      TermsAggregationBuilder aggregationBuilders = (TermsAggregationBuilder)AggregationBuilders.terms("group_by_vendor_id").field("payment_master.vendor_provider_info.id.keyword");
      DateHistogramAggregationBuilder dateWiseAggregationBuilder = this.elasticSearchUtility.buildHistogramAggregationBuilder(transferByUtilityStatisticsRequest.getDate_filter().getDate_field_name(), intervalType, transferByUtilityStatisticsRequest.getDate_filter(), AggregationBuilders.count("total_count").field("debit_amount"), AggregationBuilders.sum("total_amount").field("debit_amount"));
      aggregationBuilders.subAggregation(PipelineAggregatorBuilders.sumBucket("total_amount_of_statistics", transferByUtilityStatisticsRequest.getDate_filter().getDate_field_name() + ">total_amount"));
      aggregationBuilders.subAggregation(PipelineAggregatorBuilders.sumBucket("total_count_of_statistics", transferByUtilityStatisticsRequest.getDate_filter().getDate_field_name() + ">total_count"));
      List<FieldSortBuilder> sorts = new ArrayList();
      sorts.add((FieldSortBuilder)(new FieldSortBuilder("total_amount_of_statistics")).order(SortOrder.DESC));
      aggregationBuilders.subAggregation(dateWiseAggregationBuilder);
      aggregationBuilders.subAggregation(AggregationBuilders.topHits("top_hits").size(1).fetchSource(new String[]{"payment_master.vendor_provider_info"}, Strings.EMPTY_ARRAY));
      NativeSearchQuery searchQuery = (new NativeSearchQueryBuilder()).withQuery(boolQueryBuilder).withAggregations(new AbstractAggregationBuilder[]{aggregationBuilders}).build();
      SearchHits<TxnMaster> response = null;

      try {
         response = this.elasticsearchRestTemplate.search(searchQuery, TxnMaster.class);
      } catch (IndexNotFoundException | NoSuchIndexException var12) {
         Logger.error("Index Not Found Exception occure!!");
         transferByUtilityStatisticsResponse.setValidationMessage("TXN_MASTER_DATA_NOT_FOUND");
         transferByUtilityStatisticsResponse.setCheckValidationFailed(true);
         return transferByUtilityStatisticsResponse;
      }

      if (response.isEmpty()) {
         transferByUtilityStatisticsResponse.setValidationMessage("TXN_MASTER_DATA_NOT_FOUND");
         transferByUtilityStatisticsResponse.setCheckValidationFailed(true);
         return transferByUtilityStatisticsResponse;
      } else {
         Map<String, Aggregation> aggregationMap = ((Aggregations)((AggregationsContainer)Objects.requireNonNull(response.getAggregations())).aggregations()).asMap();
         List<TransferByUtilityStatisticsResponse.SpendAnalyticsDetails> spendAnalyticsDetailsList = new ArrayList();
         ((ParsedStringTerms)aggregationMap.get("group_by_vendor_id")).getBuckets().forEach((listOfAccounts) -> {
            Map<String, Aggregation> bucketAggregationMap = listOfAccounts.getAggregations().getAsMap();
            TransferByUtilityStatisticsResponse.SpendAnalyticsDetails spendAnalyticsDetails = new TransferByUtilityStatisticsResponse.SpendAnalyticsDetails();
            Map<String, Object> finalMapOfData = new HashMap();
            List<Map<String, Object>> listOfStatisticsMapData = new ArrayList();
            ((ParsedDateHistogram)bucketAggregationMap.get(transferByUtilityStatisticsRequest.getDate_filter().getDate_field_name())).getBuckets().forEach((e) -> {
               Map<String, Object> stringObjectMap = new HashMap();
               stringObjectMap.put("timestamp", Long.parseLong(e.getKeyAsString()) / 1000L);
               stringObjectMap.put("total_count", ((ParsedValueCount)e.getAggregations().getAsMap().get("total_count")).value());
               stringObjectMap.put("total_amount", ((ParsedSum)e.getAggregations().getAsMap().get("total_amount")).value());
               listOfStatisticsMapData.add(stringObjectMap);
            });
            finalMapOfData.put("statistics", listOfStatisticsMapData);
            finalMapOfData.put("id", listOfAccounts.getKeyAsString());
            finalMapOfData.put("total_statistics_amount", ((ParsedSimpleValue)bucketAggregationMap.get("total_amount_of_statistics")).value());
            finalMapOfData.put("total_statistics_count", ((ParsedSimpleValue)bucketAggregationMap.get("total_count_of_statistics")).value());
            TopHits topHits = (ParsedTopHits)bucketAggregationMap.get("top_hits");
            if (topHits != null) {
               SearchHit hit = topHits.getHits().getAt(0);
               Map<String, Object> map = Utils.convertJsonStringToHashMap(hit.getSourceAsString());
               if (map != null) {
                  Map<String, Object> paymentMaster = !Objects.isNull(map.get("payment_master")) && map.containsKey("payment_master") ? (Map)map.get("payment_master") : null;
                  if (paymentMaster != null) {
                     Map<String, Object> vendorProviderInfo = !Objects.isNull(paymentMaster.get("vendor_provider_info")) && paymentMaster.containsKey("vendor_provider_info") ? (Map)paymentMaster.get("vendor_provider_info") : null;
                     if (vendorProviderInfo != null) {
                        finalMapOfData.put("id", vendorProviderInfo.containsKey("id") && !Objects.isNull(vendorProviderInfo.get("id")) ? vendorProviderInfo.get("id").toString() : null);
                        finalMapOfData.put("provider_image", vendorProviderInfo.containsKey("provider_image") && !Objects.isNull(vendorProviderInfo.get("provider_image")) ? vendorProviderInfo.get("provider_image").toString() : null);
                        finalMapOfData.put("provider_name", vendorProviderInfo.containsKey("provider_name") && !Objects.isNull(vendorProviderInfo.get("provider_name")) ? vendorProviderInfo.get("provider_name").toString() : null);
                     }
                  }
               }
            }

            spendAnalyticsDetails.setSpendAnalyticsUtilityInfo(finalMapOfData);
            spendAnalyticsDetailsList.add(spendAnalyticsDetails);
         });
         transferByUtilityStatisticsResponse.setSpendAnalyizerForUtilityDataList(spendAnalyticsDetailsList);
         return transferByUtilityStatisticsResponse;
      }
   }

   public TransferByUserStatisticsResponse transferByUserStatistics(TransferByUserStatisticsRequest transferByUserStatisticsRequest) {
      TransferByUserStatisticsResponse transferByUserStatisticsResponse = new TransferByUserStatisticsResponse();
      BoolQueryBuilder boolQueryBuilder = this.elasticSearchUtility.getDefaultFieldBoolQueryBuilder(transferByUserStatisticsRequest.getCompany_id(), transferByUserStatisticsRequest.getDate_filter(), transferByUserStatisticsRequest.getDropdown_filter(), transferByUserStatisticsRequest.getSearch_filter());
      if (transferByUserStatisticsRequest.getDebit_account_type_id() != null) {
         boolQueryBuilder.must(QueryBuilders.matchQuery("debit_account_type_id", transferByUserStatisticsRequest.getDebit_account_type_id()));
      }

      for(String ignore_txn : List.of("swt", "swtr", "sww", "iwt")) {
         boolQueryBuilder.mustNot(QueryBuilders.matchQuery("txn_code", ignore_txn));
      }

      boolQueryBuilder.must(QueryBuilders.matchQuery("txn_status", 3));
      transferByUserStatisticsRequest.getDate_filter().setStart_date(transferByUserStatisticsRequest.getDate_filter().getStart_date() + 19800L);
      transferByUserStatisticsRequest.getDate_filter().setEnd_date(transferByUserStatisticsRequest.getDate_filter().getEnd_date() + 19800L);
      DateHistogramInterval intervalType = this.elasticSearchUtility.getFilterType(transferByUserStatisticsRequest.getDate_filter());
      TermsAggregationBuilder aggregationBuilders = (TermsAggregationBuilder)AggregationBuilders.terms("group_by_credit_account_type_id").field("credit_account_type_id.keyword");
      DateHistogramAggregationBuilder dateWiseAggregationBuilder = this.elasticSearchUtility.buildHistogramAggregationBuilder(transferByUserStatisticsRequest.getDate_filter().getDate_field_name(), intervalType, transferByUserStatisticsRequest.getDate_filter(), AggregationBuilders.count("total_count").field("debit_amount"), AggregationBuilders.sum("total_amount").field("debit_amount"));
      aggregationBuilders.subAggregation(PipelineAggregatorBuilders.sumBucket("total_amount_of_statistics", transferByUserStatisticsRequest.getDate_filter().getDate_field_name() + ">total_amount"));
      aggregationBuilders.subAggregation(PipelineAggregatorBuilders.sumBucket("total_count_of_statistics", transferByUserStatisticsRequest.getDate_filter().getDate_field_name() + ">total_count"));
      List<FieldSortBuilder> sorts = new ArrayList();
      sorts.add((FieldSortBuilder)(new FieldSortBuilder("total_amount_of_statistics")).order(SortOrder.DESC));
      aggregationBuilders.subAggregation(dateWiseAggregationBuilder);
      aggregationBuilders.subAggregation(AggregationBuilders.topHits("top_hits").sort("created_date", SortOrder.DESC).size(1).fetchSource(new String[]{"credit_account"}, Strings.EMPTY_ARRAY));
      NativeSearchQuery searchQuery = (new NativeSearchQueryBuilder()).withQuery(boolQueryBuilder).withAggregations(new AbstractAggregationBuilder[]{aggregationBuilders}).build();
      SearchHits<TxnMaster> response = null;

      try {
         response = this.elasticsearchRestTemplate.search(searchQuery, TxnMaster.class);
      } catch (IndexNotFoundException | NoSuchIndexException var13) {
         Logger.error("Index Not Found Exception occure!!");
         transferByUserStatisticsResponse.setValidationMessage("TXN_MASTER_DATA_NOT_FOUND");
         transferByUserStatisticsResponse.setCheckValidationFailed(true);
         return transferByUserStatisticsResponse;
      }

      if (response.isEmpty()) {
         transferByUserStatisticsResponse.setValidationMessage("TXN_MASTER_DATA_NOT_FOUND");
         transferByUserStatisticsResponse.setCheckValidationFailed(true);
         return transferByUserStatisticsResponse;
      } else {
         Map<String, Aggregation> aggregationMap = ((Aggregations)((AggregationsContainer)Objects.requireNonNull(response.getAggregations())).aggregations()).asMap();
         List<TransferByUserStatisticsResponse.SpendAnalyticsDetails> spendAnalyticsDetailsList = new ArrayList();
         ((ParsedStringTerms)aggregationMap.get("group_by_credit_account_type_id")).getBuckets().forEach((listOfAccounts) -> {
            Map<String, Aggregation> bucketAggregationMap = listOfAccounts.getAggregations().getAsMap();
            TransferByUserStatisticsResponse.SpendAnalyticsDetails spendAnalyticsDetails = new TransferByUserStatisticsResponse.SpendAnalyticsDetails();
            Map<String, Object> finalMapOfData = new HashMap();
            List<Map<String, Object>> listOfStatisticsMapData = new ArrayList();
            ((ParsedDateHistogram)bucketAggregationMap.get(transferByUserStatisticsRequest.getDate_filter().getDate_field_name())).getBuckets().forEach((e) -> {
               Map<String, Object> stringObjectMap = new HashMap();
               stringObjectMap.put("timestamp", Long.parseLong(e.getKeyAsString()) / 1000L);
               stringObjectMap.put("total_count", ((ParsedValueCount)e.getAggregations().getAsMap().get("total_count")).value());
               stringObjectMap.put("total_amount", ((ParsedSum)e.getAggregations().getAsMap().get("total_amount")).value());
               listOfStatisticsMapData.add(stringObjectMap);
            });
            finalMapOfData.put("statistics", listOfStatisticsMapData);
            finalMapOfData.put("id", listOfAccounts.getKeyAsString());
            finalMapOfData.put("total_statistics_amount", ((ParsedSimpleValue)bucketAggregationMap.get("total_amount_of_statistics")).value());
            finalMapOfData.put("total_statistics_count", ((ParsedSimpleValue)bucketAggregationMap.get("total_count_of_statistics")).value());
            TopHits topHits = (ParsedTopHits)bucketAggregationMap.get("top_hits");
            if (topHits != null) {
               SearchHit hit = topHits.getHits().getAt(0);
               Map<String, Object> map = Utils.convertJsonStringToHashMap(hit.getSourceAsString());
               if (map != null) {
                  Map<String, Object> creditAccountSource = map.containsKey("credit_account") && !Objects.isNull(map.get("credit_account")) ? (Map)map.get("credit_account") : null;
                  if (creditAccountSource != null) {
                     finalMapOfData.put("image", creditAccountSource.containsKey("image") && !Objects.isNull(creditAccountSource.get("image")) ? creditAccountSource.get("image").toString() : null);
                     finalMapOfData.put("user_type", creditAccountSource.containsKey("user_type") && !Objects.isNull(creditAccountSource.get("user_type")) ? creditAccountSource.get("user_type").toString() : null);
                     finalMapOfData.put("address", creditAccountSource.containsKey("address") && !Objects.isNull(creditAccountSource.get("address")) ? creditAccountSource.get("address").toString() : null);
                     finalMapOfData.put("dial_code", creditAccountSource.containsKey("dial_code") && !Objects.isNull(creditAccountSource.get("dial_code")) ? creditAccountSource.get("dial_code").toString() : null);
                     finalMapOfData.put("last_name", creditAccountSource.containsKey("last_name") && !Objects.isNull(creditAccountSource.get("last_name")) ? creditAccountSource.get("last_name").toString() : null);
                     finalMapOfData.put("phone_number", creditAccountSource.containsKey("phone_number") && !Objects.isNull(creditAccountSource.get("phone_number")) ? creditAccountSource.get("phone_number").toString() : null);
                     finalMapOfData.put("postal_code", creditAccountSource.containsKey("postal_code") && !Objects.isNull(creditAccountSource.get("postal_code")) ? creditAccountSource.get("postal_code").toString() : null);
                     finalMapOfData.put("first_name", creditAccountSource.containsKey("first_name") && !Objects.isNull(creditAccountSource.get("first_name")) ? creditAccountSource.get("first_name").toString() : null);
                     finalMapOfData.put("email", creditAccountSource.containsKey("email") && !Objects.isNull(creditAccountSource.get("email")) ? creditAccountSource.get("email").toString() : null);
                     finalMapOfData.put("display_name", creditAccountSource.containsKey("display_name") && !Objects.isNull(creditAccountSource.get("display_name")) ? creditAccountSource.get("display_name").toString() : null);
                  }
               }
            }

            spendAnalyticsDetails.setSpendAnalyticsUserInfo(finalMapOfData);
            spendAnalyticsDetailsList.add(spendAnalyticsDetails);
         });
         transferByUserStatisticsResponse.setSpendAnalyizerForTxnDataList(spendAnalyticsDetailsList);
         return transferByUserStatisticsResponse;
      }
   }
}
