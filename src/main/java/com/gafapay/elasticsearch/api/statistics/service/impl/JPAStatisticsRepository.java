package com.gafapay.elasticsearch.api.statistics.service.impl;

import com.gafapay.elasticsearch.api.commonrequest.DropDownFilter;
import com.gafapay.elasticsearch.api.commonrequest.SearchFilter;
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
import com.gafapay.elasticsearch.api.statistics.service.StatisticsRepository;
import com.gafapay.elasticsearch.api.txnmaster.dao.TxnMasterDao;
import com.gafapay.elasticsearch.api.txnmaster.model.TxnMaster;
import com.gafapay.elasticsearch.api.txnmaster.model.request.GetAllTransactionsDataRequest;
import com.gafapay.elasticsearch.constant.AppConstants;
import com.gafapay.elasticsearch.database.model.ThirdPartyTxnCommissionFees;
import com.gafapay.elasticsearch.database.model.nodalbanktransaction.NodalBankTransaction;
import com.gafapay.elasticsearch.helper.ElasticSearchUtility;
import com.gafapay.elasticsearch.utils.Logger;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import org.elasticsearch.common.Strings;
import org.elasticsearch.index.IndexNotFoundException;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.PipelineAggregatorBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.filter.Filters;
import org.elasticsearch.search.aggregations.bucket.filter.FiltersAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.filter.FiltersAggregator;
import org.elasticsearch.search.aggregations.bucket.filter.ParsedFilter;
import org.elasticsearch.search.aggregations.bucket.filter.ParsedFilters;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.histogram.ParsedDateHistogram;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.nested.ParsedNested;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ParsedSum;
import org.elasticsearch.search.aggregations.metrics.ParsedTopHits;
import org.elasticsearch.search.aggregations.metrics.ParsedValueCount;
import org.elasticsearch.search.aggregations.metrics.SumAggregationBuilder;
import org.elasticsearch.search.aggregations.pipeline.ParsedBucketMetricValue;
import org.elasticsearch.search.aggregations.pipeline.ParsedSimpleValue;
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
public class JPAStatisticsRepository implements StatisticsRepository {
   @Autowired
   private ElasticSearchUtility elasticSearchUtility;
   @Autowired
   private ElasticsearchRestTemplate elasticsearchRestTemplate;
   @Autowired
   private TxnMasterDao txnMasterDao;

   public JPAStatisticsRepository() {
   }

   public GetTxnStatisticsDataResponse getTxnStatistics(GetTxnStatisticsDataRequest getTxnStatisticsDataRequest) {
      BoolQueryBuilder boolQueryBuilder = this.elasticSearchUtility.getDefaultFieldBoolQueryBuilder(getTxnStatisticsDataRequest.getCompany_id(), getTxnStatisticsDataRequest.getDate_filter(), getTxnStatisticsDataRequest.getDropdown_filter(), getTxnStatisticsDataRequest.getSearch_filter());
      boolQueryBuilder.must(QueryBuilders.matchQuery("display_end_user", true));
      DateHistogramInterval intervalType = this.elasticSearchUtility.getFilterType(getTxnStatisticsDataRequest.getDate_filter());
      List<Integer> transactionStatus = List.of(1, 2, 3, 4, 5);
      List<String> cashInTxnCode = List.of("CIB", "ACIB", "AWT");
      List<String> cashOutTxnCode = List.of("COB", "ACOB", "MS", "AS", "CS", "BP");
      AggregationBuilder totalAmountBuilder = AggregationBuilders.sum("total_amount").field("debit_amount");
      AggregationBuilder valueCountAggregationBuilder = AggregationBuilders.filters("remove_atc_txn_from_total_transaction_count", new QueryBuilder[]{(new BoolQueryBuilder()).mustNot(QueryBuilders.matchQuery("txn_code.keyword", "ATC"))}).subAggregation(AggregationBuilders.count("total_count").field("txn_status"));
      List<FiltersAggregator.KeyedFilter> keyedFilterList = Arrays.asList(new FiltersAggregator.KeyedFilter("1", QueryBuilders.termQuery("txn_status", 1)), new FiltersAggregator.KeyedFilter("2", QueryBuilders.termQuery("txn_status", 2)), new FiltersAggregator.KeyedFilter("3", QueryBuilders.termQuery("txn_status", 3)), new FiltersAggregator.KeyedFilter("4", QueryBuilders.termQuery("txn_status", 4)), new FiltersAggregator.KeyedFilter("5", QueryBuilders.termQuery("txn_status", 5)));
      FiltersAggregationBuilder txnStatusAgg = (FiltersAggregationBuilder)((FiltersAggregationBuilder)AggregationBuilders.filters("by_txn_status", (FiltersAggregator.KeyedFilter[])keyedFilterList.toArray((x$0) -> new FiltersAggregator.KeyedFilter[x$0])).subAggregation(totalAmountBuilder)).subAggregation(valueCountAggregationBuilder);
      AggregationBuilder commissionCountBuilder = AggregationBuilders.sum("total_amount").field("credit_amount");
      FilterAggregationBuilder commissionFilter = (FilterAggregationBuilder)AggregationBuilders.filter("total_commission", QueryBuilders.matchQuery("txn_code.keyword", "UTC")).subAggregation(commissionCountBuilder);
      AggregationBuilder chargesCountBuilder = AggregationBuilders.sum("total_amount").field("debit_amount");
      FilterAggregationBuilder chargesCountFilter = (FilterAggregationBuilder)AggregationBuilders.filter("total_charges", QueryBuilders.matchQuery("txn_code.keyword", "ATC")).subAggregation(chargesCountBuilder);
      AggregationBuilder cashInCountBuilder = AggregationBuilders.sum("total_amount").field("credit_amount");
      FilterAggregationBuilder cashInFilter = (FilterAggregationBuilder)AggregationBuilders.filter("total_cash_in", QueryBuilders.termsQuery("txn_code.keyword", cashInTxnCode)).subAggregation(cashInCountBuilder);
      AggregationBuilder cashOutCountBuilder = AggregationBuilders.sum("total_amount").field("txn_amount");
      FilterAggregationBuilder cashOutFilter = (FilterAggregationBuilder)AggregationBuilders.filter("total_cash_out", QueryBuilders.boolQuery().must(QueryBuilders.termsQuery("txn_code.keyword", cashOutTxnCode)).must(QueryBuilders.termQuery("txn_status", "3"))).subAggregation(cashOutCountBuilder);
      DateHistogramAggregationBuilder dateWiseAggregationBuilder = this.elasticSearchUtility.buildHistogramAggregationBuilder("by_txn_date", intervalType, getTxnStatisticsDataRequest.getDate_filter(), AggregationBuilders.count("total_count").field(getTxnStatisticsDataRequest.getDate_filter().getDate_field_name()), AggregationBuilders.sum("total_amount").field("debit_amount"));
      NativeSearchQuery searchQuery = (new NativeSearchQueryBuilder()).withAggregations(new AbstractAggregationBuilder[]{dateWiseAggregationBuilder}).withAggregations(new AbstractAggregationBuilder[]{txnStatusAgg}).withAggregations(new AbstractAggregationBuilder[]{cashInFilter}).withAggregations(new AbstractAggregationBuilder[]{cashOutFilter}).withAggregations(new AbstractAggregationBuilder[]{commissionFilter}).withAggregations(new AbstractAggregationBuilder[]{chargesCountFilter}).withQuery(boolQueryBuilder).addAggregation(PipelineAggregatorBuilders.minBucket("min_txn_amount", "by_txn_date>total_amount")).addAggregation(PipelineAggregatorBuilders.maxBucket("max_txn_amount", "by_txn_date>total_amount")).addAggregation(PipelineAggregatorBuilders.sumBucket("total_txn_amount", "by_txn_date>total_amount")).build();
      SearchHits<TxnMaster> response = this.elasticsearchRestTemplate.search(searchQuery, TxnMaster.class);
      Map<String, Aggregation> aggregationMap = ((Aggregations)((AggregationsContainer)Objects.requireNonNull(response.getAggregations())).aggregations()).asMap();
      ParsedFilters byTxnStatus = (ParsedFilters)aggregationMap.get("by_txn_status");
      ParsedDateHistogram byTxnDate = (ParsedDateHistogram)aggregationMap.get("by_txn_date");
      GetTxnStatisticsDataResponse txnStatisticsDataResponse = new GetTxnStatisticsDataResponse();
      GetTxnStatisticsDataResponse.TransactionStatisticsData getTxnStatisticsDataResponse = new GetTxnStatisticsDataResponse.TransactionStatisticsData();
      getTxnStatisticsDataResponse.setTotalCashIn(((ParsedSum)((ParsedFilter)aggregationMap.get("total_cash_in")).getAggregations().getAsMap().get("total_amount")).value());
      getTxnStatisticsDataResponse.setTotalCashOut(((ParsedSum)((ParsedFilter)aggregationMap.get("total_cash_out")).getAggregations().getAsMap().get("total_amount")).value());
      getTxnStatisticsDataResponse.setTotalCommission(((ParsedSum)((ParsedFilter)aggregationMap.get("total_commission")).getAggregations().getAsMap().get("total_amount")).value());
      getTxnStatisticsDataResponse.setTotalCharges(((ParsedSum)((ParsedFilter)aggregationMap.get("total_charges")).getAggregations().getAsMap().get("total_amount")).value());
      List<Map<String, Object>> statusWiseStatisticsList = new ArrayList(byTxnStatus.getBuckets().size());
      byTxnStatus.getBuckets().parallelStream().forEachOrdered((bucket) -> {
         Map<String, Object> map = new HashMap();
         map.put("transaction_status", Integer.parseInt(bucket.getKeyAsString()));
         map.put("total_amount", ((ParsedSum)bucket.getAggregations().getAsMap().get("total_amount")).value());
         map.put("total_count", ((ParsedValueCount)((Filters.Bucket)((ParsedFilters)bucket.getAggregations().getAsMap().get("remove_atc_txn_from_total_transaction_count")).getBuckets().get(0)).getAggregations().getAsMap().get("total_count")).value());
         statusWiseStatisticsList.add(map);
      });
      long totalTransactions = statusWiseStatisticsList.stream().flatMapToLong((stringObjectMap) -> {
         Object totalCountValue = stringObjectMap.get("total_count");
         return totalCountValue instanceof Number ? LongStream.of(((Number)totalCountValue).longValue()) : LongStream.empty();
      }).sum();
      getTxnStatisticsDataResponse.setTotalTransactions(totalTransactions);
      getTxnStatisticsDataResponse.setStatusWiseStatistics(statusWiseStatisticsList);
      List<Map<String, Object>> transactions = new ArrayList(byTxnDate.getBuckets().size());
      byTxnDate.getBuckets().parallelStream().forEachOrdered((bucket) -> {
         Map<String, Object> map = new HashMap();
         Map<String, Aggregation> aggregation = bucket.getAggregations().getAsMap();
         map.put("timestamp", ZonedDateTime.parse(bucket.getKey().toString()).toEpochSecond());
         map.put("total_count", ((ParsedValueCount)aggregation.get("total_count")).value());
         map.put("total_amount", aggregation.containsKey("total_amount") && !Objects.isNull(aggregation.get("total_amount")) ? ((ParsedSum)aggregation.get("total_amount")).value() : null);
         transactions.add(map);
      });
      getTxnStatisticsDataResponse.setMinTransactionAmount(Double.isInfinite(((ParsedBucketMetricValue)aggregationMap.get("min_txn_amount")).value()) ? (double)0.0F : ((ParsedBucketMetricValue)aggregationMap.get("min_txn_amount")).value());
      getTxnStatisticsDataResponse.setMaxTransactionAmount(Double.isInfinite(((ParsedBucketMetricValue)aggregationMap.get("max_txn_amount")).value()) ? (double)0.0F : ((ParsedBucketMetricValue)aggregationMap.get("max_txn_amount")).value());
      getTxnStatisticsDataResponse.setTotalTransactionAmount(((ParsedSimpleValue)aggregationMap.get("total_txn_amount")).value());
      getTxnStatisticsDataResponse.setTransactions(transactions);
      txnStatisticsDataResponse.setTransactionStatisticsData(getTxnStatisticsDataResponse);
      GetTxnStatisticsDataResponse.Activity activity = new GetTxnStatisticsDataResponse.Activity();
      GetAllTransactionsDataRequest getAllTransactionsDataRequest = new GetAllTransactionsDataRequest();
      getAllTransactionsDataRequest.setCompany_id(getAllTransactionsDataRequest.getCompany_id());
      Calendar startDateCalendar = Calendar.getInstance();
      startDateCalendar.set(10, 0);
      startDateCalendar.set(12, 0);
      startDateCalendar.set(13, 0);
      getAllTransactionsDataRequest.setEnd_date(Instant.now().getEpochSecond());
      startDateCalendar.add(6, -1);
      getAllTransactionsDataRequest.setStart_date((new Date(startDateCalendar.getTimeInMillis())).toInstant().getEpochSecond());
      List<TxnMaster> allTransactions = this.txnMasterDao.getAllTransactions(getAllTransactionsDataRequest);
      Set<String> userId = new HashSet();

      for(TxnMaster txnMaster : allTransactions) {
         userId.add(txnMaster.getDebitAccountTypeId());
         userId.add(txnMaster.getCreditAccountTypeId());
      }

      activity.setDailyCountOfActiveUsers((long)userId.size());
      startDateCalendar.add(6, 1);
      startDateCalendar.add(3, -1);
      getAllTransactionsDataRequest.setStart_date((new Date(startDateCalendar.getTimeInMillis())).toInstant().getEpochSecond());
      allTransactions = this.txnMasterDao.getAllTransactions(getAllTransactionsDataRequest);
      userId.clear();

      for(TxnMaster txnMaster : allTransactions) {
         userId.add(txnMaster.getDebitAccountTypeId());
         userId.add(txnMaster.getCreditAccountTypeId());
      }

      activity.setWeeklyCountOfActiveUsers((long)userId.size());
      userId.clear();
      startDateCalendar.add(3, 1);
      startDateCalendar.add(2, -1);
      getAllTransactionsDataRequest.setStart_date((new Date(startDateCalendar.getTimeInMillis())).toInstant().getEpochSecond());

      for(TxnMaster txnMaster : this.txnMasterDao.getAllTransactions(getAllTransactionsDataRequest)) {
         userId.add(txnMaster.getDebitAccountTypeId());
         userId.add(txnMaster.getCreditAccountTypeId());
      }

      activity.setMonthlyCountOfActiveUsers((long)userId.size());
      userId.clear();
      startDateCalendar.add(2, 1);
      startDateCalendar.add(1, -1);
      getAllTransactionsDataRequest.setStart_date((new Date(startDateCalendar.getTimeInMillis())).toInstant().getEpochSecond());

      for(TxnMaster txnMaster : this.txnMasterDao.getAllTransactions(getAllTransactionsDataRequest)) {
         userId.add(txnMaster.getDebitAccountTypeId());
         userId.add(txnMaster.getCreditAccountTypeId());
      }

      activity.setYearlyCountOfActiveUsers((long)userId.size());
      getAllTransactionsDataRequest.setEnd_date((Long)null);
      getAllTransactionsDataRequest.setStart_date((Long)null);
      allTransactions = this.txnMasterDao.getAllTransactions(getAllTransactionsDataRequest);
      activity.setOneTimeUsage(this.elasticSearchUtility.countNumberOfSingleUseUser(allTransactions));
      txnStatisticsDataResponse.setActivity(activity);
      return txnStatisticsDataResponse;
   }

   public GetTxnTypeStatisticsDataResponse getTxnTypeStatistics(GetTxnTypeStatisticsDataRequest getTxnTypeStatisticsDataRequest) {
      BoolQueryBuilder boolQueryBuilder = this.elasticSearchUtility.getDefaultFieldBoolQueryBuilder(getTxnTypeStatisticsDataRequest.getCompany_id(), getTxnTypeStatisticsDataRequest.getDate_filter(), getTxnTypeStatisticsDataRequest.getDropdown_filter(), getTxnTypeStatisticsDataRequest.getSearch_filter());
      boolQueryBuilder.must(QueryBuilders.matchQuery("txn_status", 3));
      FiltersAggregationBuilder txnStatusAgg = (FiltersAggregationBuilder)((FiltersAggregationBuilder)AggregationBuilders.filters("by_txn_code", this.elasticSearchUtility.convertTxnCodeToKeyedFilter(getTxnTypeStatisticsDataRequest.getProduct_code())).subAggregation(AggregationBuilders.sum("debit_total_amount").field("debit_amount"))).subAggregation(AggregationBuilders.sum("credit_total_amount").field("credit_amount"));
      DateHistogramInterval intervalType = this.elasticSearchUtility.getFilterType(getTxnTypeStatisticsDataRequest.getDate_filter());
      DateHistogramAggregationBuilder dateWiseAggregationBuilder = this.elasticSearchUtility.buildHistogramAggregationBuilder("txn_code.keyword", intervalType, getTxnTypeStatisticsDataRequest.getDate_filter(), txnStatusAgg);
      NativeSearchQuery searchQuery = (new NativeSearchQueryBuilder()).withAggregations(new AbstractAggregationBuilder[]{dateWiseAggregationBuilder}).withQuery(boolQueryBuilder).build();
      SearchHits<TxnMaster> response = this.elasticsearchRestTemplate.search(searchQuery, TxnMaster.class);
      Map<String, Aggregation> aggregationMap = ((Aggregations)((AggregationsContainer)Objects.requireNonNull(response.getAggregations())).aggregations()).asMap();
      List<? extends Histogram.Bucket> dateBuckets = ((ParsedDateHistogram)aggregationMap.get("txn_code.keyword")).getBuckets();
      Map<String, List<Map<String, Object>>> statisticsMap = new HashMap();

      for(Histogram.Bucket bucket : dateBuckets) {
         ParsedFilters parsedFilters = (ParsedFilters)bucket.getAggregations().get("by_txn_code");

         for(Filters.Bucket filterBucket : parsedFilters.getBuckets()) {
            Map<String, Object> aggMap = new HashMap();
            aggMap.put("timestamp", ZonedDateTime.parse(bucket.getKey().toString()).toEpochSecond());
            aggMap.put("credit_amount", ((ParsedSum)filterBucket.getAggregations().getAsMap().get("credit_total_amount")).value());
            aggMap.put("debit_amount", ((ParsedSum)filterBucket.getAggregations().getAsMap().get("debit_total_amount")).value());
            aggMap.put("total_amount", ((ParsedSum)filterBucket.getAggregations().getAsMap().get("debit_total_amount")).value());
            aggMap.put("total_count", filterBucket.getDocCount());
            List<Map<String, Object>> aggStatisticsMapList = (List)statisticsMap.get(filterBucket.getKeyAsString());
            if (Objects.isNull(aggStatisticsMapList)) {
               List<Map<String, Object>> statisticsList = new ArrayList(5);
               statisticsList.add(aggMap);
               statisticsMap.put(filterBucket.getKeyAsString(), statisticsList);
            } else {
               aggStatisticsMapList.add(aggMap);
               statisticsMap.put(filterBucket.getKeyAsString(), aggStatisticsMapList);
            }
         }
      }

      List<Map<String, Object>> resultMap = new ArrayList(statisticsMap.size());
      statisticsMap.forEach((key, value) -> {
         Map<String, Object> map = new HashMap();
         map.put("txn_code", key);
         map.put("statistics", value);
         resultMap.add(map);
      });
      GetTxnTypeStatisticsDataResponse.TransactionStatisticsData getTxnStatisticsDataResponse = new GetTxnTypeStatisticsDataResponse.TransactionStatisticsData();
      getTxnStatisticsDataResponse.setTxnCodeStatistics(resultMap);
      GetTxnTypeStatisticsDataResponse getTxnTypeStatisticsDataResponse = new GetTxnTypeStatisticsDataResponse();
      getTxnTypeStatisticsDataResponse.setTransactionStatisticsData(getTxnStatisticsDataResponse);
      return getTxnTypeStatisticsDataResponse;
   }

   public GetPaymentModeStatisticsDataResponse getPaymentModeStatistics(GetPaymentModeStatisticsDataRequest getPaymentModeStatisticsDataRequest) {
      BoolQueryBuilder boolQueryBuilder = this.elasticSearchUtility.getDefaultFieldBoolQueryBuilder(getPaymentModeStatisticsDataRequest.getCompany_id(), getPaymentModeStatisticsDataRequest.getDate_filter(), getPaymentModeStatisticsDataRequest.getDropdown_filter(), getPaymentModeStatisticsDataRequest.getSearch_filter());
      boolQueryBuilder.must(QueryBuilders.matchQuery("display_end_user", true));
      List<FiltersAggregator.KeyedFilter> keyedFilterList = Arrays.asList(new FiltersAggregator.KeyedFilter("by_cash", QueryBuilders.termQuery("payment_mode", 1)), new FiltersAggregator.KeyedFilter("by_card", QueryBuilders.termQuery("payment_mode", 2)), new FiltersAggregator.KeyedFilter("by_wallet", QueryBuilders.termQuery("payment_mode", 3)), new FiltersAggregator.KeyedFilter("by_net_banking", QueryBuilders.termQuery("payment_mode", 4)), new FiltersAggregator.KeyedFilter("by_upi", QueryBuilders.termQuery("payment_mode", 5)));
      FiltersAggregationBuilder paymentModeAgg = (FiltersAggregationBuilder)AggregationBuilders.filters("by_payment_mode", (FiltersAggregator.KeyedFilter[])keyedFilterList.toArray((x$0) -> new FiltersAggregator.KeyedFilter[x$0])).subAggregation(AggregationBuilders.sum("total_amount").field("debit_amount"));
      NativeSearchQuery searchQuery = (new NativeSearchQueryBuilder()).withAggregations(new AbstractAggregationBuilder[]{paymentModeAgg}).withQuery(boolQueryBuilder).build();
      SearchHits<TxnMaster> response = this.elasticsearchRestTemplate.search(searchQuery, TxnMaster.class);
      Map<String, Aggregation> aggregationMap = ((Aggregations)((AggregationsContainer)Objects.requireNonNull(response.getAggregations())).aggregations()).asMap();
      List<? extends Filters.Bucket> byPaymentModeBuckets = ((ParsedFilters)aggregationMap.get("by_payment_mode")).getBuckets();
      List<Map<String, Object>> paymentModeStatistics = new ArrayList(byPaymentModeBuckets.size());
      byPaymentModeBuckets.forEach((paymentMode) -> {
         Map<String, Object> hashMap = new HashMap();
         switch (paymentMode.getKeyAsString()) {
            case "by_cash" -> hashMap.put("payment_mode", 1);
            case "by_card" -> hashMap.put("payment_mode", 2);
            case "by_wallet" -> hashMap.put("payment_mode", 3);
            case "by_net_banking" -> hashMap.put("payment_mode", 4);
            case "by_upi" -> hashMap.put("payment_mode", 5);
         }

         hashMap.put("total_count", paymentMode.getDocCount());
         hashMap.put("total_amount", ((ParsedSum)paymentMode.getAggregations().getAsMap().get("total_amount")).value());
         paymentModeStatistics.add(hashMap);
      });
      GetPaymentModeStatisticsDataResponse paymentModeStatisticsDataResponse = new GetPaymentModeStatisticsDataResponse();
      GetPaymentModeStatisticsDataResponse.TransactionStatisticsData transactionStatisticsData = new GetPaymentModeStatisticsDataResponse.TransactionStatisticsData();
      transactionStatisticsData.setTxnCodeStatistics(paymentModeStatistics);
      paymentModeStatisticsDataResponse.setTransactionStatisticsData(transactionStatisticsData);
      return paymentModeStatisticsDataResponse;
   }

   public GetSettlementStatisticsResponse getSettlementStatisticData(GetSettlementStatisticDataRequest getSettlementStatisticDataRequest) {
      DateHistogramInterval intervalType = this.elasticSearchUtility.getFilterType(getSettlementStatisticDataRequest.getDate_filter());
      AggregationBuilder totalAmount = AggregationBuilders.sum("total_amount").field("credit_amount");
      AggregationBuilder totalCount = AggregationBuilders.count("total_count").field("txn_status");
      List<FiltersAggregator.KeyedFilter> keyedFilterList = Arrays.asList(new FiltersAggregator.KeyedFilter("1", QueryBuilders.termQuery("txn_status", 1)), new FiltersAggregator.KeyedFilter("2", QueryBuilders.termQuery("txn_status", 2)), new FiltersAggregator.KeyedFilter("3", QueryBuilders.termQuery("txn_status", 3)), new FiltersAggregator.KeyedFilter("4", QueryBuilders.termQuery("txn_status", 4)), new FiltersAggregator.KeyedFilter("5", QueryBuilders.termQuery("txn_status", 5)));
      FiltersAggregationBuilder txnStatusAgg = (FiltersAggregationBuilder)((FiltersAggregationBuilder)AggregationBuilders.filters("by_txn_status", (FiltersAggregator.KeyedFilter[])keyedFilterList.toArray((x$0) -> new FiltersAggregator.KeyedFilter[x$0])).subAggregation(totalAmount)).subAggregation(totalCount);
      AggregationBuilder successCount = AggregationBuilders.count("total_count").field("txn_status");
      FilterAggregationBuilder successFilter = (FilterAggregationBuilder)AggregationBuilders.filter("success_count", QueryBuilders.matchQuery("txn_status", "3")).subAggregation(successCount);
      AggregationBuilder failCount = AggregationBuilders.count("total_count").field("txn_status");
      FilterAggregationBuilder failFilter = (FilterAggregationBuilder)AggregationBuilders.filter("failed_count", QueryBuilders.matchQuery("txn_status", "4")).subAggregation(failCount);
      AggregationBuilder rejectCount = AggregationBuilders.count("total_count").field("txn_status");
      FilterAggregationBuilder rejectFilter = (FilterAggregationBuilder)AggregationBuilders.filter("rejected_count", QueryBuilders.matchQuery("txn_status", "5")).subAggregation(rejectCount);
      BoolQueryBuilder boolQueryBuilder = this.elasticSearchUtility.getDefaultFieldBoolQueryBuilder(getSettlementStatisticDataRequest.getCompany_id(), getSettlementStatisticDataRequest.getDate_filter(), getSettlementStatisticDataRequest.getDropdown_filter(), getSettlementStatisticDataRequest.getSearch_filter());
      List<String> settlementTxnCode;
      if (!Objects.isNull(getSettlementStatisticDataRequest.getDropdown_filter()) && !Objects.isNull(getSettlementStatisticDataRequest.getDropdown_filter().getUser_type()) && getSettlementStatisticDataRequest.getDropdown_filter().getUser_type() == 2) {
         settlementTxnCode = List.of("COB", "CS");
      } else if (!Objects.isNull(getSettlementStatisticDataRequest.getDropdown_filter()) && !Objects.isNull(getSettlementStatisticDataRequest.getDropdown_filter().getUser_type()) && getSettlementStatisticDataRequest.getDropdown_filter().getUser_type() == 3) {
         settlementTxnCode = List.of("MS");
      } else if (!Objects.isNull(getSettlementStatisticDataRequest.getDropdown_filter()) && !Objects.isNull(getSettlementStatisticDataRequest.getDropdown_filter().getUser_type()) && getSettlementStatisticDataRequest.getDropdown_filter().getUser_type() == 5) {
         settlementTxnCode = List.of("AS", "ACOB");
      } else {
         settlementTxnCode = List.of("COB", "CS", "MS", "AS", "ACOB");
      }

      boolQueryBuilder.must(QueryBuilders.termsQuery("txn_code.keyword", (String[])settlementTxnCode.toArray((x$0) -> new String[x$0])));
      DateHistogramAggregationBuilder dateWiseAggregationBuilder = this.elasticSearchUtility.buildHistogramAggregationBuilder("by_txn_date", intervalType, getSettlementStatisticDataRequest.getDate_filter(), successFilter, failFilter, rejectFilter, AggregationBuilders.count("total_count").field(getSettlementStatisticDataRequest.getDate_filter().getDate_field_name()), AggregationBuilders.sum("total_amount").field("debit_amount"));
      NativeSearchQuery searchQuery = (new NativeSearchQueryBuilder()).withQuery(boolQueryBuilder).withAggregations(new AbstractAggregationBuilder[]{dateWiseAggregationBuilder}).withAggregations(new AbstractAggregationBuilder[]{txnStatusAgg}).addAggregation(PipelineAggregatorBuilders.sumBucket("total_txn_count", "by_txn_status>total_count")).build();
      SearchHits<TxnMaster> response = this.elasticsearchRestTemplate.search(searchQuery, TxnMaster.class);
      GetSettlementStatisticsResponse getStatisticsResponse = new GetSettlementStatisticsResponse();
      GetSettlementStatisticsResponse.StatisticData statisticData = new GetSettlementStatisticsResponse.StatisticData();
      Map<String, Aggregation> aggregationMap = ((Aggregations)((AggregationsContainer)Objects.requireNonNull(response.getAggregations())).aggregations()).asMap();
      ParsedFilters byTxnStatus = (ParsedFilters)aggregationMap.get("by_txn_status");
      ParsedDateHistogram byTxnDate = (ParsedDateHistogram)aggregationMap.get("by_txn_date");
      List<Map<String, Object>> statusWiseStatisticsList = new ArrayList(byTxnStatus.getBuckets().size());
      byTxnStatus.getBuckets().parallelStream().forEachOrdered((bucket) -> {
         Map<String, Object> map = new HashMap();
         map.put("transaction_status", Integer.parseInt(bucket.getKeyAsString()));
         map.put("total_amount", ((ParsedSum)bucket.getAggregations().getAsMap().get("total_amount")).value());
         map.put("total_count", ((ParsedValueCount)bucket.getAggregations().getAsMap().get("total_count")).value());
         statusWiseStatisticsList.add(map);
      });
      double totalTransactions = ((ParsedSimpleValue)aggregationMap.get("total_txn_count")).value();
      statisticData.setTotalTransactions((long)totalTransactions);
      statisticData.setStatusWiseStatistics(statusWiseStatisticsList);
      List<Map<String, Object>> transactions = new ArrayList(byTxnDate.getBuckets().size());
      byTxnDate.getBuckets().parallelStream().forEachOrdered((bucket) -> {
         Map<String, Object> map = new HashMap();
         Map<String, Aggregation> aggregation = bucket.getAggregations().getAsMap();
         map.put("timestamp", ZonedDateTime.parse(bucket.getKey().toString()).toEpochSecond());
         map.put("total_count", ((ParsedValueCount)aggregation.get("total_count")).value());
         map.put("success_count", ((ParsedFilter)aggregation.get("success_count")).getDocCount());
         map.put("failed_count", ((ParsedFilter)aggregation.get("failed_count")).getDocCount());
         map.put("rejected_count", ((ParsedFilter)aggregation.get("rejected_count")).getDocCount());
         transactions.add(map);
      });
      statisticData.setTransactions(transactions);
      getStatisticsResponse.setStatisticData(statisticData);
      return getStatisticsResponse;
   }

   public GetCashFlowStatisticsResponse getCashFlowStatistic(GetCashFlowStatisticsDataRequest getCashFlowStatisticsDataRequest) {
      DateHistogramInterval intervalType = this.elasticSearchUtility.getFilterType(getCashFlowStatisticsDataRequest.getDate_filter());
      AggregationBuilder cashInCountBuilder = AggregationBuilders.sum("total_amount").field("debit_amount");
      FilterAggregationBuilder cashInFilter = (FilterAggregationBuilder)((FilterAggregationBuilder)AggregationBuilders.filter("total_cash_in", QueryBuilders.termQuery("debit_account_type_id", getCashFlowStatisticsDataRequest.getDropdown_filter().getAdmin_user_id())).subAggregation(cashInCountBuilder)).subAggregation(AggregationBuilders.count("total_count").field("debit_amount"));
      AggregationBuilder cashOutCountBuilder = AggregationBuilders.sum("total_amount").field("credit_amount");
      FilterAggregationBuilder cashOutFilter = (FilterAggregationBuilder)((FilterAggregationBuilder)AggregationBuilders.filter("total_cash_out", QueryBuilders.termQuery("credit_account_type_id", getCashFlowStatisticsDataRequest.getDropdown_filter().getAdmin_user_id())).subAggregation(cashOutCountBuilder)).subAggregation(AggregationBuilders.count("total_count").field("credit_amount"));
      DateHistogramAggregationBuilder dateWiseAggregationBuilder = this.elasticSearchUtility.buildHistogramAggregationBuilder("by_txn_date", intervalType, getCashFlowStatisticsDataRequest.getDate_filter(), cashInFilter, cashOutFilter);
      BoolQueryBuilder builder = this.elasticSearchUtility.getDefaultFieldBoolQueryBuilder(getCashFlowStatisticsDataRequest.getCompany_id(), getCashFlowStatisticsDataRequest.getDate_filter(), getCashFlowStatisticsDataRequest.getDropdown_filter(), getCashFlowStatisticsDataRequest.getSearch_filter());
      NativeSearchQuery searchQuery = (new NativeSearchQueryBuilder()).withAggregations(new AbstractAggregationBuilder[]{dateWiseAggregationBuilder}).withAggregations(new AbstractAggregationBuilder[]{AggregationBuilders.count("total_transactions").field("debit_amount")}).withAggregations(new AbstractAggregationBuilder[]{cashInFilter}).withAggregations(new AbstractAggregationBuilder[]{cashOutFilter}).withQuery(builder).build();
      SearchHits<TxnMaster> response = this.elasticsearchRestTemplate.search(searchQuery, TxnMaster.class);
      Map<String, Aggregation> aggregationMap = ((Aggregations)((AggregationsContainer)Objects.requireNonNull(response.getAggregations())).aggregations()).asMap();
      ParsedDateHistogram byTxnDate = (ParsedDateHistogram)aggregationMap.get("by_txn_date");
      GetCashFlowStatisticsResponse getCashStatisticsResponse = new GetCashFlowStatisticsResponse();
      GetCashFlowStatisticsResponse.CashStatics cashStatics = new GetCashFlowStatisticsResponse.CashStatics();
      cashStatics.setTotalCashIn(((ParsedSum)((ParsedFilter)aggregationMap.get("total_cash_in")).getAggregations().get("total_amount")).value());
      cashStatics.setTotalCashOut(((ParsedSum)((ParsedFilter)aggregationMap.get("total_cash_out")).getAggregations().get("total_amount")).value());
      cashStatics.setNetBalance(cashStatics.getTotalCashIn() - cashStatics.getTotalCashOut());
      List<Map<String, Object>> transactions = (List)byTxnDate.getBuckets().stream().map((bucket) -> {
         Map<String, Aggregation> aggregation = bucket.getAggregations().getAsMap();
         Map<String, Object> map = new HashMap();
         map.put("timestamp", Long.parseLong(bucket.getKeyAsString()) / 1000L);
         map.put("total_cash_in", ((ParsedSum)((ParsedFilter)aggregation.get("total_cash_in")).getAggregations().get("total_amount")).value());
         map.put("no_of_cash_in", ((ParsedValueCount)((ParsedFilter)aggregation.get("total_cash_in")).getAggregations().get("total_count")).value());
         map.put("total_cash_out", ((ParsedSum)((ParsedFilter)aggregation.get("total_cash_out")).getAggregations().get("total_amount")).value());
         map.put("no_of_cash_out", ((ParsedValueCount)((ParsedFilter)aggregation.get("total_cash_out")).getAggregations().get("total_count")).value());
         Double netbalance = (Double)map.get("total_cash_in") - (Double)map.get("total_cash_out");
         map.put("net_balance", netbalance);
         return map;
      }).collect(Collectors.toList());
      cashStatics.setCashFlowData(transactions);
      getCashStatisticsResponse.setCashStatics(cashStatics);
      return getCashStatisticsResponse;
   }

   public AgentCommissionStatisticsResponse agentCommissionStatistics(AgentCommissionStatisticsRequest agentCommissionStatisticsRequest) {
      AgentCommissionStatisticsResponse agentCommissionStatisticsResponse = new AgentCommissionStatisticsResponse();
      AggregationBuilder groupByBuilder = null;
      switch (agentCommissionStatisticsRequest.getDropdown_filter().getAgent_commission_group_by()) {
         case 1 -> groupByBuilder = AggregationBuilders.terms("commission").field("txn_commissions.tree_level_id.keyword");
         case 2 -> groupByBuilder = AggregationBuilders.terms("commission").field("txn_commissions.product_id.keyword");
         case 3 -> groupByBuilder = ((TermsAggregationBuilder)AggregationBuilders.terms("commission").field("txn_commissions.tree_level_id.keyword")).subAggregation(AggregationBuilders.terms("tree_id").field("txn_commissions.tree_id.keyword"));
      }

      if (Objects.isNull(groupByBuilder)) {
         agentCommissionStatisticsResponse.setCheckValidationFailed(true);
         agentCommissionStatisticsResponse.setValidationMessage("ELASTIC_SEARCH_PLEASE_PASS_THE_CORRECT_AGENT_COMMISSION_GROUP_BY_FILTER");
         return agentCommissionStatisticsResponse;
      } else {
         groupByBuilder.subAggregation(AggregationBuilders.sum("total_amount").field("txn_commissions.final_commission"));
         NestedAggregationBuilder nestedBuilder = (NestedAggregationBuilder)AggregationBuilders.nested("txn_commissions", "txn_commissions").subAggregation(groupByBuilder);
         BoolQueryBuilder builder = this.elasticSearchUtility.getDefaultFieldBoolQueryBuilder(agentCommissionStatisticsRequest.getCompany_id(), agentCommissionStatisticsRequest.getDate_filter(), agentCommissionStatisticsRequest.getDropdown_filter(), agentCommissionStatisticsRequest.getSearch_filter());
         NativeSearchQuery searchQuery = (new NativeSearchQueryBuilder()).withQuery(builder).withAggregations(new AbstractAggregationBuilder[]{nestedBuilder}).build();
         SearchHits<TxnMaster> response = this.elasticsearchRestTemplate.search(searchQuery, TxnMaster.class);
         Map<String, Aggregation> aggregationMap = ((Aggregations)((AggregationsContainer)Objects.requireNonNull(response.getAggregations())).aggregations()).asMap();
         List<AgentCommissionStatisticsResponse.CommissionStatistic> commissionStatisticList = new ArrayList(5);
         ((ParsedStringTerms)((ParsedNested)aggregationMap.get("txn_commissions")).getAggregations().getAsMap().get("commission")).getBuckets().forEach((commission) -> {
            AgentCommissionStatisticsResponse.CommissionStatistic commissionStatistic = new AgentCommissionStatisticsResponse.CommissionStatistic();
            if (agentCommissionStatisticsRequest.getDropdown_filter().getAgent_commission_group_by().equals(3)) {
               ((ParsedStringTerms)commission.getAggregations().getAsMap().get("tree_id")).getBuckets().forEach((bucket) -> commissionStatistic.setId(bucket.getKeyAsString()));
            } else {
               commissionStatistic.setId(commission.getKeyAsString());
            }

            commissionStatistic.setTotalCount(commission.getDocCount());
            Double total_amount = ((ParsedSum)commission.getAggregations().getAsMap().getOrDefault("total_amount", (Object)null)).value();
            commissionStatistic.setTotalAmount(Double.parseDouble(AppConstants.decimalFormat.format(total_amount)));
            commissionStatisticList.add(commissionStatistic);
         });
         agentCommissionStatisticsResponse.setCommissionStatisticList(commissionStatisticList);
         return agentCommissionStatisticsResponse;
      }
   }

   public GetRevenueStatisticsResponse getRevenueStatistics(GetTxnStatisticsDataRequest getTxnStatisticsDataRequest) {
      GetRevenueStatisticsResponse getRevenueStatisticsResponse = new GetRevenueStatisticsResponse();
      BoolQueryBuilder boolQueryBuilder = this.elasticSearchUtility.getDefaultFieldBoolQueryBuilder(getTxnStatisticsDataRequest.getCompany_id(), getTxnStatisticsDataRequest.getDate_filter(), getTxnStatisticsDataRequest.getDropdown_filter(), getTxnStatisticsDataRequest.getSearch_filter());
      DateHistogramInterval intervalType = this.elasticSearchUtility.getFilterType(getTxnStatisticsDataRequest.getDate_filter());
      DateHistogramAggregationBuilder dateWiseAggregationBuilder = this.elasticSearchUtility.buildHistogramAggregationBuilder("by_txn_date", intervalType, getTxnStatisticsDataRequest.getDate_filter(), ((NestedAggregationBuilder)AggregationBuilders.nested("txn_charges", "txn_charges").subAggregation(AggregationBuilders.count("total_count").field("txn_charges.final_charge"))).subAggregation(AggregationBuilders.sum("total_amount").field("txn_charges.final_charge")));
      TermsAggregationBuilder termsAggregationBuilder = (TermsAggregationBuilder)((TermsAggregationBuilder)AggregationBuilders.terms("group_by_txn_code").field("txn_code.keyword")).subAggregation(((NestedAggregationBuilder)AggregationBuilders.nested("txn_charges", "txn_charges").subAggregation(AggregationBuilders.count("total_count").field("txn_charges.final_charge"))).subAggregation(AggregationBuilders.sum("total_amount").field("txn_charges.final_charge")));
      NestedAggregationBuilder nestedAggregationBuilder = (NestedAggregationBuilder)AggregationBuilders.nested("txn_charges", "txn_charges").subAggregation(((TermsAggregationBuilder)AggregationBuilders.terms("group_by_user_type").field("txn_charges.user_type")).subAggregation(AggregationBuilders.sum("total_amount").field("txn_charges.final_charge")));
      NativeSearchQuery searchQuery = (new NativeSearchQueryBuilder()).withQuery(boolQueryBuilder).withAggregations(new AbstractAggregationBuilder[]{dateWiseAggregationBuilder}).withAggregations(new AbstractAggregationBuilder[]{termsAggregationBuilder}).withAggregations(new AbstractAggregationBuilder[]{nestedAggregationBuilder}).build();
      SearchHits<TxnMaster> response = null;

      try {
         response = this.elasticsearchRestTemplate.search(searchQuery, TxnMaster.class);
      } catch (NoSuchIndexException e) {
         Logger.error("Index Not Find Exception " + e);
         getRevenueStatisticsResponse.setCheckValidationFailed(true);
         getRevenueStatisticsResponse.setValidationMessage("REVENUE_STATISTICS_DATA_NOT_FOUND");
         return getRevenueStatisticsResponse;
      }

      Map<String, Aggregation> aggregationMap = ((Aggregations)((AggregationsContainer)Objects.requireNonNull(response.getAggregations())).aggregations()).asMap();
      List<GetRevenueStatisticsResponse.OverAllRevenueStatistics> overAllRevenueStatisticsList = new ArrayList(5);
      List<GetRevenueStatisticsResponse.ProductWiseRevenueStatistics> productWiseRevenueStatisticsList = new ArrayList(5);
      List<GetRevenueStatisticsResponse.UserWiseRevenueStatistics> userWiseRevenueStatisticsList = new ArrayList(5);
      ((ParsedDateHistogram)aggregationMap.get("by_txn_date")).getBuckets().forEach((bucket) -> {
         GetRevenueStatisticsResponse.OverAllRevenueStatistics overAllRevenueStatistics = new GetRevenueStatisticsResponse.OverAllRevenueStatistics();
         overAllRevenueStatistics.setTimeStamp(ZonedDateTime.parse(bucket.getKey().toString()).toEpochSecond());
         overAllRevenueStatistics.setMonth(this.elasticSearchUtility.getTheMonth(overAllRevenueStatistics.getTimeStamp()));
         overAllRevenueStatistics.setTotalCount((int)((ParsedValueCount)((ParsedNested)bucket.getAggregations().getAsMap().get("txn_charges")).getAggregations().getAsMap().get("total_count")).value());
         overAllRevenueStatistics.setTotalAmount(Double.valueOf(AppConstants.decimalFormat.format(((ParsedSum)((ParsedNested)bucket.getAggregations().getAsMap().get("txn_charges")).getAggregations().getAsMap().get("total_amount")).value())));
         overAllRevenueStatisticsList.add(overAllRevenueStatistics);
      });
      Map<String, Double> calculateProductWisePercentage = new HashMap(5);
      ((ParsedStringTerms)aggregationMap.get("group_by_txn_code")).getBuckets().forEach((bucket) -> {
         GetRevenueStatisticsResponse.ProductWiseRevenueStatistics productWiseRevenueStatistics = new GetRevenueStatisticsResponse.ProductWiseRevenueStatistics();
         productWiseRevenueStatistics.setProductCode(bucket.getKeyAsString());
         productWiseRevenueStatistics.setTotalAmount(Double.valueOf(AppConstants.decimalFormat.format(((ParsedSum)((ParsedNested)bucket.getAggregations().getAsMap().get("txn_charges")).getAggregations().getAsMap().get("total_amount")).value())));
         productWiseRevenueStatisticsList.add(productWiseRevenueStatistics);
         calculateProductWisePercentage.put(productWiseRevenueStatistics.getProductCode(), productWiseRevenueStatistics.getTotalAmount());
      });
      Map<String, Double> finalPercentageProductWiseList = this.elasticSearchUtility.calculatePercentageOfProducts(calculateProductWisePercentage);
      if (!Objects.isNull(finalPercentageProductWiseList) && !finalPercentageProductWiseList.isEmpty()) {
         for(String key : finalPercentageProductWiseList.keySet()) {
            productWiseRevenueStatisticsList.forEach((productWiseRevenueStatistics) -> {
               if (productWiseRevenueStatistics.getProductCode().equals(key)) {
                  productWiseRevenueStatistics.setOverall(Double.valueOf(AppConstants.decimalFormat.format(finalPercentageProductWiseList.get(key))));
               }

            });
         }
      }

      Map<String, Double> calculateUserWisePercentage = new HashMap();
      ((ParsedLongTerms)((ParsedNested)aggregationMap.get("txn_charges")).getAggregations().getAsMap().get("group_by_user_type")).getBuckets().forEach((bucket) -> {
         GetRevenueStatisticsResponse.UserWiseRevenueStatistics userWiseRevenueStatistics = new GetRevenueStatisticsResponse.UserWiseRevenueStatistics();
         userWiseRevenueStatistics.setUserType(Integer.parseInt(bucket.getKeyAsString()));
         userWiseRevenueStatistics.setTotalAmount(Double.valueOf(AppConstants.decimalFormat.format(((ParsedSum)bucket.getAggregations().getAsMap().get("total_amount")).value())));
         userWiseRevenueStatisticsList.add(userWiseRevenueStatistics);
         calculateUserWisePercentage.put(String.valueOf(userWiseRevenueStatistics.getUserType()), userWiseRevenueStatistics.getTotalAmount());
      });
      Map<String, Double> finalPercentageUserWiseList = this.elasticSearchUtility.calculatePercentageOfProducts(calculateUserWisePercentage);
      if (!Objects.isNull(finalPercentageUserWiseList) && !finalPercentageUserWiseList.isEmpty()) {
         for(String key : finalPercentageUserWiseList.keySet()) {
            userWiseRevenueStatisticsList.forEach((userWiseRevenueStatistics) -> {
               if (userWiseRevenueStatistics.getUserType().equals(Integer.parseInt(key))) {
                  userWiseRevenueStatistics.setOverall(Double.valueOf(AppConstants.decimalFormat.format(finalPercentageUserWiseList.get(key))));
               }

            });
         }
      }

      getRevenueStatisticsResponse.setTotalRevenue(overAllRevenueStatisticsList.stream().map(GetRevenueStatisticsResponse.OverAllRevenueStatistics::getTotalAmount).mapToDouble(Double::doubleValue).sum());
      getRevenueStatisticsResponse.setOverAllRevenueStatisticsList(overAllRevenueStatisticsList);
      getRevenueStatisticsResponse.setProductWiseRevenueStatisticsList(productWiseRevenueStatisticsList);
      getRevenueStatisticsResponse.setUserWiseRevenueStatisticsList(userWiseRevenueStatisticsList);
      return getRevenueStatisticsResponse;
   }

   public GetRevenueStatisticsByTxnTypeResponse getTxnTypeWiseStatistics(GetTxnStatisticsDataRequest getTxnStatisticsDataRequest) {
      BoolQueryBuilder boolQueryBuilder = this.elasticSearchUtility.getDefaultFieldBoolQueryBuilder(getTxnStatisticsDataRequest.getCompany_id(), getTxnStatisticsDataRequest.getDate_filter(), getTxnStatisticsDataRequest.getDropdown_filter(), getTxnStatisticsDataRequest.getSearch_filter());
      boolQueryBuilder.mustNot(QueryBuilders.matchQuery("txn_type", AppConstants.TRANSACTION_TYPE_EIGHT_FOR_CHARGES));
      boolQueryBuilder.must(QueryBuilders.matchQuery("txn_status", "3"));
      Map<String, Object> aggregationScriptForTxnTypeWiseTotalAmount = new HashMap();
      aggregationScriptForTxnTypeWiseTotalAmount.put("lang", "painless");
      aggregationScriptForTxnTypeWiseTotalAmount.put("source", "doc['txn_type'].value == 1 || doc['txn_type'].value == 6 ? doc['credit_amount'] : doc['debit_amount']");
      TermsAggregationBuilder termsAggregationBuilder = (TermsAggregationBuilder)((TermsAggregationBuilder)((TermsAggregationBuilder)AggregationBuilders.terms("group_by_txn_type").field("txn_type")).subAggregation(AggregationBuilders.count("total_count").script(Script.parse(aggregationScriptForTxnTypeWiseTotalAmount)))).subAggregation(AggregationBuilders.sum("total_amount").script(Script.parse(aggregationScriptForTxnTypeWiseTotalAmount)));
      NativeSearchQuery searchQuery = (new NativeSearchQueryBuilder()).withQuery(boolQueryBuilder).withAggregations(new AbstractAggregationBuilder[]{termsAggregationBuilder}).build();
      SearchHits<TxnMaster> response = this.elasticsearchRestTemplate.search(searchQuery, TxnMaster.class);
      Map<String, Aggregation> aggregationMap = ((Aggregations)((AggregationsContainer)Objects.requireNonNull(response.getAggregations())).aggregations()).asMap();
      GetRevenueStatisticsByTxnTypeResponse getRevenueStatisticsByTxnTypeResponse = new GetRevenueStatisticsByTxnTypeResponse();
      List<GetRevenueStatisticsByTxnTypeResponse.RevenueStatisticsByTxnType> revenueStatisticsByTxnTypeList = new ArrayList(5);
      Map<String, Double> calculatePercentageBYTxnTypeWise = new HashMap(5);
      ((ParsedLongTerms)aggregationMap.get("group_by_txn_type")).getBuckets().forEach((bucket) -> {
         GetRevenueStatisticsByTxnTypeResponse.RevenueStatisticsByTxnType revenueStatisticsByTxnType = new GetRevenueStatisticsByTxnTypeResponse.RevenueStatisticsByTxnType();
         revenueStatisticsByTxnType.setTxnType(Integer.parseInt(bucket.getKeyAsString()));
         revenueStatisticsByTxnType.setTotalCount((int)((ParsedValueCount)bucket.getAggregations().getAsMap().get("total_count")).value());
         revenueStatisticsByTxnType.setTotalAmount(Double.valueOf(AppConstants.decimalFormat.format(((ParsedSum)bucket.getAggregations().getAsMap().get("total_amount")).value())));
         revenueStatisticsByTxnTypeList.add(revenueStatisticsByTxnType);
         calculatePercentageBYTxnTypeWise.put(String.valueOf(revenueStatisticsByTxnType.getTxnType()), revenueStatisticsByTxnType.getTotalAmount());
      });
      Map<String, Double> finalPercentageTxnTypeWiseList = this.elasticSearchUtility.calculatePercentageOfProducts(calculatePercentageBYTxnTypeWise);
      if (!Objects.isNull(finalPercentageTxnTypeWiseList) && !finalPercentageTxnTypeWiseList.isEmpty()) {
         for(String key : finalPercentageTxnTypeWiseList.keySet()) {
            revenueStatisticsByTxnTypeList.forEach((userWiseRevenueStatistics) -> {
               if (userWiseRevenueStatistics.getTxnType().equals(Integer.parseInt(key))) {
                  userWiseRevenueStatistics.setOverall(Double.valueOf(AppConstants.decimalFormat.format(finalPercentageTxnTypeWiseList.get(key))));
               }

            });
         }
      }

      getRevenueStatisticsByTxnTypeResponse.setRevenueStatisticsByTxnTypeList(revenueStatisticsByTxnTypeList);
      return getRevenueStatisticsByTxnTypeResponse;
   }

   public GetNodalBankStatisticsResponse getNodalBankStatistics(GetNodalBankStatisticsRequest getNodalBankStatisticsRequest) {
      GetNodalBankStatisticsResponse getNodalBankStatisticsResponse = new GetNodalBankStatisticsResponse();
      BoolQueryBuilder boolQueryBuilder = this.elasticSearchUtility.getDefaultFieldBoolQueryBuilder(getNodalBankStatisticsRequest.getCompany_id(), getNodalBankStatisticsRequest.getDate_filter(), getNodalBankStatisticsRequest.getDropdown_filter(), getNodalBankStatisticsRequest.getSearch_filter());
      TermsAggregationBuilder nestedAggregationBuilder = (TermsAggregationBuilder)((TermsAggregationBuilder)((TermsAggregationBuilder)AggregationBuilders.terms("group_by_operation_type").field("operation_type")).subAggregation(AggregationBuilders.count("total_count").field("amount"))).subAggregation(AggregationBuilders.sum("total_amount").field("amount"));
      NativeSearchQuery searchQuery = (new NativeSearchQueryBuilder()).withQuery(boolQueryBuilder).withAggregations(new AbstractAggregationBuilder[]{nestedAggregationBuilder}).build();
      SearchHits<NodalBankTransaction> response = null;

      try {
         response = this.elasticsearchRestTemplate.search(searchQuery, NodalBankTransaction.class);
      } catch (IndexNotFoundException | NoSuchIndexException var10) {
         Logger.error("Index Not Found Exception occure!!");
         getNodalBankStatisticsResponse.setValidationMessage("NODAL_BANK_STATISTICS_DATA_NOT_FOUND");
         getNodalBankStatisticsResponse.setCheckValidationFailed(true);
         return getNodalBankStatisticsResponse;
      }

      if (response.isEmpty()) {
         getNodalBankStatisticsResponse.setValidationMessage("TXN_MASTER_DATA_NOT_FOUND");
         getNodalBankStatisticsResponse.setCheckValidationFailed(true);
         return getNodalBankStatisticsResponse;
      } else {
         Map<String, Aggregation> aggregationMap = ((Aggregations)((AggregationsContainer)Objects.requireNonNull(response.getAggregations())).aggregations()).asMap();
         GetNodalBankStatisticsResponse.NodalBankStatisticsDetails getNodalBankStatisticsDetails = new GetNodalBankStatisticsResponse.NodalBankStatisticsDetails();
         new HashMap(1);
         ((ParsedLongTerms)aggregationMap.get("group_by_operation_type")).getBuckets().forEach((bucket) -> {
            Map<String, Object> map = new HashMap(1);
            Integer type = Integer.parseInt(bucket.getKeyAsString());
            Long count = (long)((ParsedValueCount)bucket.getAggregations().getAsMap().get("total_count")).value();
            Double amount = ((ParsedSum)bucket.getAggregations().getAsMap().get("total_amount")).value();
            map.put("operation_type", type);
            map.put("total_count", count);
            map.put("total_amount", amount);
            if (type.equals(AppConstants.OPERATION_TYPE_DEBIT)) {
               getNodalBankStatisticsDetails.setWithdraw(map);
            }

            if (type.equals(AppConstants.OPERATION_TYPE_CREDIT)) {
               getNodalBankStatisticsDetails.setDeposit(map);
            }

         });
         if (getNodalBankStatisticsDetails.getDeposit() == null && getNodalBankStatisticsDetails.getWithdraw() == null) {
            getNodalBankStatisticsResponse.setValidationMessage("NODAL_BANK_STATISTICS_DATA_NOT_FOUND");
            getNodalBankStatisticsResponse.setCheckValidationFailed(true);
            return getNodalBankStatisticsResponse;
         } else {
            getNodalBankStatisticsResponse.setNodalBankStatisticsDetails(getNodalBankStatisticsDetails);
            return getNodalBankStatisticsResponse;
         }
      }
   }

   public UserWiseTransactionStatisticsResponse userWiseTransactionStatistics(UserWiseTransactionStatisticsRequest userWiseTransactionStatisticsRequest) {
      UserWiseTransactionStatisticsResponse userWiseTransactionStatisticsResponse = new UserWiseTransactionStatisticsResponse();
      BoolQueryBuilder boolQueryBuilder = this.elasticSearchUtility.getDefaultFieldBoolQueryBuilder(userWiseTransactionStatisticsRequest.getCompany_id(), userWiseTransactionStatisticsRequest.getDate_filter(), userWiseTransactionStatisticsRequest.getDropdown_filter(), userWiseTransactionStatisticsRequest.getSearch_filter());
      if (!userWiseTransactionStatisticsRequest.getList_filter().isEmpty() && userWiseTransactionStatisticsRequest.getList_filter().containsKey("user_id") && !Objects.isNull(userWiseTransactionStatisticsRequest.getList_filter().get("user_id"))) {
         List<String> userIds = (List)userWiseTransactionStatisticsRequest.getList_filter().get("user_id");
         if (!userIds.isEmpty()) {
            boolQueryBuilder.must(QueryBuilders.termsQuery("transaction_by.account_id.keyword", userIds));
         }
      }

      TermsAggregationBuilder termsAggregationBuilder = (TermsAggregationBuilder)((TermsAggregationBuilder)((TermsAggregationBuilder)AggregationBuilders.terms("user_transaction").field("transaction_by.account_id.keyword")).subAggregation(AggregationBuilders.count("transaction_count").field("transaction_by.account_id.keyword"))).subAggregation(AggregationBuilders.sum("transaction_amount").field("txn_amount"));
      NativeSearchQuery searchQuery = (new NativeSearchQueryBuilder()).withAggregations(new AbstractAggregationBuilder[]{termsAggregationBuilder}).withQuery(boolQueryBuilder).build();
      SearchHits<TxnMaster> response = this.elasticsearchRestTemplate.search(searchQuery, TxnMaster.class);
      Map<String, Aggregation> aggregationMap = ((Aggregations)((AggregationsContainer)Objects.requireNonNull(response.getAggregations())).aggregations()).asMap();
      ParsedStringTerms parsedStringTerms = (ParsedStringTerms)aggregationMap.get("user_transaction");
      List<UserWiseTransactionStatisticsResponse.UserTransactions> userTransactionsList = new ArrayList(parsedStringTerms.getBuckets().size());

      for(Terms.Bucket bucket : parsedStringTerms.getBuckets()) {
         UserWiseTransactionStatisticsResponse.UserTransactions userTransactions = new UserWiseTransactionStatisticsResponse().new UserTransactions();
         userTransactions.setUser_id(bucket.getKeyAsString());
         Double totalAmount = ((ParsedSum)bucket.getAggregations().getAsMap().get("transaction_amount")).value();
         userTransactions.setTotal_amount(totalAmount);
         Long totalCount = (long)((ParsedValueCount)bucket.getAggregations().getAsMap().get("transaction_count")).value();
         userTransactions.setTotal_count(totalCount.intValue());
         userTransactionsList.add(userTransactions);
      }

      userWiseTransactionStatisticsResponse.setUserTransactionsList(userTransactionsList);
      return userWiseTransactionStatisticsResponse;
   }

   public RevenueSharingStatisticsResponse getRevenueSharingStatistics(RevenueSharingStatisticsRequest revenueSharingStatisticsRequest) {
      RevenueSharingStatisticsResponse revenueSharingStatisticsResponse = new RevenueSharingStatisticsResponse();
      BoolQueryBuilder boolQueryBuilder = this.elasticSearchUtility.getDefaultFieldBoolQueryBuilder(revenueSharingStatisticsRequest.getCompany_id(), revenueSharingStatisticsRequest.getDate_filter(), (DropDownFilter)null, (SearchFilter)null);
      boolQueryBuilder.must(QueryBuilders.matchQuery("contract_type", AppConstants.CONTRACT_TYPE_REVENUE_SHARING));
      revenueSharingStatisticsRequest.getDate_filter().setStart_date(revenueSharingStatisticsRequest.getDate_filter().getStart_date() + 19800L);
      revenueSharingStatisticsRequest.getDate_filter().setEnd_date(revenueSharingStatisticsRequest.getDate_filter().getEnd_date() + 19800L);
      DateHistogramInterval intervalType = this.elasticSearchUtility.getFilterType(revenueSharingStatisticsRequest.getDate_filter());
      SumAggregationBuilder totalGrossAmount = (SumAggregationBuilder)AggregationBuilders.sum("total_gross_revenue").field("contract_type_amount");
      SumAggregationBuilder totalTax = (SumAggregationBuilder)AggregationBuilders.sum("total_tax").field("total_tax");
      SumAggregationBuilder totalNetRevenue = (SumAggregationBuilder)AggregationBuilders.sum("total_net_revenue").field("net_revenue");
      SumAggregationBuilder totalCompanyShare = (SumAggregationBuilder)AggregationBuilders.sum("total_company_share").field("company_share");
      NestedAggregationBuilder nestedAggregationBuilder = (NestedAggregationBuilder)((NestedAggregationBuilder)AggregationBuilders.nested("partner_share_list", "partner_shares").subAggregation(((TermsAggregationBuilder)AggregationBuilders.terms("group_by_contract_id").field("partner_shares.third_party_contract_id.keyword")).subAggregation(AggregationBuilders.sum("total_particular_partner_share").field("partner_shares.contract_share")))).subAggregation(AggregationBuilders.sum("total_partner_share").field("partner_shares.contract_share"));
      DateHistogramAggregationBuilder dateWiseAggregationBuilder = this.elasticSearchUtility.buildHistogramAggregationBuilder("by_created_date", intervalType, revenueSharingStatisticsRequest.getDate_filter(), totalGrossAmount, totalTax, totalNetRevenue, totalCompanyShare, nestedAggregationBuilder);
      NativeSearchQuery searchQuery = (new NativeSearchQueryBuilder()).withQuery(boolQueryBuilder).withAggregations(new AbstractAggregationBuilder[]{dateWiseAggregationBuilder}).build();

      SearchHits<ThirdPartyTxnCommissionFees> response;
      try {
         response = this.elasticsearchRestTemplate.search(searchQuery, ThirdPartyTxnCommissionFees.class);
      } catch (Exception e) {
         Logger.error("Index Not Find Exception " + e);
         revenueSharingStatisticsResponse.setCheckValidationFailed(true);
         revenueSharingStatisticsResponse.setValidationMessage("REVENUE_SHARING_STATISTICS_DATA_NOT_FOUND");
         return revenueSharingStatisticsResponse;
      }

      Map<String, Aggregation> aggregationMap = ((Aggregations)((AggregationsContainer)Objects.requireNonNull(response.getAggregations())).aggregations()).asMap();
      List<RevenueSharingStatisticsResponse.RevenueSharingStatistics> revenueSharingStatisticsList = new ArrayList();
      ((ParsedDateHistogram)aggregationMap.get("by_created_date")).getBuckets().forEach((bucket) -> {
         RevenueSharingStatisticsResponse.RevenueSharingStatistics revenueSharingStatistics = new RevenueSharingStatisticsResponse.RevenueSharingStatistics();
         revenueSharingStatistics.setTimestamp(ZonedDateTime.parse(bucket.getKey().toString()).toEpochSecond());
         revenueSharingStatistics.setGrossRevenue(Double.parseDouble(AppConstants.decimalFormat.format(((ParsedSum)bucket.getAggregations().getAsMap().get("total_gross_revenue")).value())));
         revenueSharingStatistics.setTotalTax(Double.parseDouble(AppConstants.decimalFormat.format(((ParsedSum)bucket.getAggregations().getAsMap().get("total_tax")).value())));
         revenueSharingStatistics.setNetRevenue(Double.parseDouble(AppConstants.decimalFormat.format(((ParsedSum)bucket.getAggregations().getAsMap().get("total_net_revenue")).value())));
         revenueSharingStatistics.setCompanyShare(Double.parseDouble(AppConstants.decimalFormat.format(((ParsedSum)bucket.getAggregations().getAsMap().get("total_company_share")).value())));
         List<Map<String, Object>> partnerShareListMapList = new ArrayList();
         ((ParsedStringTerms)((ParsedNested)bucket.getAggregations().getAsMap().get("partner_share_list")).getAggregations().getAsMap().get("group_by_contract_id")).getBuckets().forEach((partnerShareList) -> {
            Map<String, Object> partnerShare = new HashMap();
            partnerShare.put("third_party_contract_id", partnerShareList.getKey());
            partnerShare.put("contract_share", Double.parseDouble(AppConstants.decimalFormat.format(((ParsedSum)partnerShareList.getAggregations().getAsMap().get("total_particular_partner_share")).value())));
            partnerShareListMapList.add(partnerShare);
         });
         revenueSharingStatistics.setBankPartnerShareList(partnerShareListMapList);
         revenueSharingStatistics.setTotalBankPartnerShare(Double.parseDouble(AppConstants.decimalFormat.format(((ParsedSum)((ParsedNested)bucket.getAggregations().getAsMap().get("partner_share_list")).getAggregations().getAsMap().get("total_partner_share")).value())));
         revenueSharingStatisticsList.add(revenueSharingStatistics);
      });
      revenueSharingStatisticsResponse.setRevenueSharingStatisticsList(revenueSharingStatisticsList);
      return revenueSharingStatisticsResponse;
   }

   public ThirdPartyVendorsStatisticsResponse getThirdPartyVendorsStatistics(ThirdPartyVendorsStatisticsRequest thirdPartyVendorsStatisticsRequest) {
      ThirdPartyVendorsStatisticsResponse thirdPartyVendorsStatisticsResponse = new ThirdPartyVendorsStatisticsResponse();
      BoolQueryBuilder boolQueryBuilder = this.elasticSearchUtility.getDefaultFieldBoolQueryBuilder(thirdPartyVendorsStatisticsRequest.getCompany_id(), thirdPartyVendorsStatisticsRequest.getDate_filter(), thirdPartyVendorsStatisticsRequest.getDropdown_filter(), thirdPartyVendorsStatisticsRequest.getSearch_filter());
      TermsAggregationBuilder termsAggregationBuilder = (TermsAggregationBuilder)((TermsAggregationBuilder)((TermsAggregationBuilder)((TermsAggregationBuilder)AggregationBuilders.terms("group_by_type_key_code").field("type_key_code.keyword")).subAggregation(AggregationBuilders.topHits("vendor_type_key_info").sort("type_key_info.created_date", SortOrder.DESC).size(1).fetchSource(new String[]{"type_key_info"}, Strings.EMPTY_ARRAY))).subAggregation(AggregationBuilders.sum("total_amount").field("contract_type_amount"))).subAggregation(AggregationBuilders.topHits("contract_type").sort("type_key_info.created_date", SortOrder.DESC).size(1).fetchSource(new String[]{"contract_type"}, Strings.EMPTY_ARRAY));
      NativeSearchQuery searchQuery = (new NativeSearchQueryBuilder()).withQuery(boolQueryBuilder).withAggregations(new AbstractAggregationBuilder[]{termsAggregationBuilder}).build();

      SearchHits<ThirdPartyTxnCommissionFees> response;
      try {
         response = this.elasticsearchRestTemplate.search(searchQuery, ThirdPartyTxnCommissionFees.class);
      } catch (Exception e) {
         Logger.error("Index Not Find Exception " + e);
         thirdPartyVendorsStatisticsResponse.setCheckValidationFailed(true);
         thirdPartyVendorsStatisticsResponse.setValidationMessage("THIRD_PARTY_VENDORS_STATISTICS_DATA_NOT_FOUND");
         return thirdPartyVendorsStatisticsResponse;
      }

      List<ThirdPartyVendorsStatisticsResponse.ThirdPartyVendor> thirdPartyVendorList = new ArrayList();
      Map<String, Aggregation> aggregationMap = ((Aggregations)((AggregationsContainer)Objects.requireNonNull(response.getAggregations())).aggregations()).asMap();
      ((ParsedStringTerms)aggregationMap.get("group_by_type_key_code")).getBuckets().forEach((bucket) -> {
         ThirdPartyVendorsStatisticsResponse.ThirdPartyVendor thirdPartyVendor = new ThirdPartyVendorsStatisticsResponse.ThirdPartyVendor();
         thirdPartyVendor.setTotalTransaction((int)bucket.getDocCount());
         Map<String, Object> contractType = ((ParsedTopHits)bucket.getAggregations().getAsMap().get("contract_type")).getHits().getAt(0).getSourceAsMap();
         if (!Objects.isNull(contractType) && contractType.containsKey("contract_type") && !Objects.isNull(contractType.get("contract_type"))) {
            double amount = Double.parseDouble(AppConstants.decimalFormat.format(((ParsedSum)bucket.getAggregations().getAsMap().get("total_amount")).value()));
            if (Integer.valueOf(contractType.get("contract_type").toString()).equals(AppConstants.CONTRACT_TYPE_COMMISSION)) {
               thirdPartyVendor.setCommission(amount);
            } else if (Integer.valueOf(contractType.get("contract_type").toString()).equals(AppConstants.CONTRACT_TYPE_FEES)) {
               thirdPartyVendor.setFess(amount);
            }
         }

         Map<String, Object> typeKeyInfo = (Map)((ParsedTopHits)bucket.getAggregations().getAsMap().get("vendor_type_key_info")).getHits().getAt(0).getSourceAsMap().get("type_key_info");
         if (!Objects.isNull(typeKeyInfo)) {
            thirdPartyVendor.setTypeKeyInfo(typeKeyInfo);
         }

         thirdPartyVendorList.add(thirdPartyVendor);
      });
      thirdPartyVendorsStatisticsResponse.setThirdPartyVendorStatisticsList(thirdPartyVendorList);
      return thirdPartyVendorsStatisticsResponse;
   }

   public ThirdPartyProviderStatisticsResponse getThirdPartyProviderStatistics(ThirdPartyProviderStatisticsRequest thirdPartyProviderStatisticsRequest) {
      ThirdPartyProviderStatisticsResponse thirdPartyProviderStatisticsResponse = new ThirdPartyProviderStatisticsResponse();
      BoolQueryBuilder boolQueryBuilder = this.elasticSearchUtility.getDefaultFieldBoolQueryBuilder(thirdPartyProviderStatisticsRequest.getCompany_id(), thirdPartyProviderStatisticsRequest.getDate_filter(), thirdPartyProviderStatisticsRequest.getDropdown_filter(), thirdPartyProviderStatisticsRequest.getSearch_filter());
      TermsAggregationBuilder termsAggregationBuilder = (TermsAggregationBuilder)((TermsAggregationBuilder)((TermsAggregationBuilder)((TermsAggregationBuilder)AggregationBuilders.terms("group_by_provider_code").field("provider_code.keyword")).subAggregation(AggregationBuilders.topHits("provider_code_info").sort("provider_code_info.created_date", SortOrder.DESC).size(1).fetchSource(new String[]{"provider_code_info"}, Strings.EMPTY_ARRAY))).subAggregation(AggregationBuilders.sum("total_amount").field("contract_type_amount"))).subAggregation(AggregationBuilders.topHits("contract_type").sort("created_date", SortOrder.DESC).size(1).fetchSource(new String[]{"contract_type"}, Strings.EMPTY_ARRAY));
      NativeSearchQuery searchQuery = (new NativeSearchQueryBuilder()).withQuery(boolQueryBuilder).withAggregations(new AbstractAggregationBuilder[]{termsAggregationBuilder}).build();

      SearchHits<ThirdPartyTxnCommissionFees> response;
      try {
         response = this.elasticsearchRestTemplate.search(searchQuery, ThirdPartyTxnCommissionFees.class);
      } catch (Exception e) {
         Logger.error("Index Not Find Exception " + e);
         thirdPartyProviderStatisticsResponse.setCheckValidationFailed(true);
         thirdPartyProviderStatisticsResponse.setValidationMessage("THIRD_PARTY_PROVIDERS_STATISTICS_DATA_NOT_FOUND");
         return thirdPartyProviderStatisticsResponse;
      }

      List<ThirdPartyProviderStatisticsResponse.ThirdPartyProvider> thirdPartyProviderList = new ArrayList();
      Map<String, Aggregation> aggregationMap = ((Aggregations)((AggregationsContainer)Objects.requireNonNull(response.getAggregations())).aggregations()).asMap();
      ((ParsedStringTerms)aggregationMap.get("group_by_provider_code")).getBuckets().forEach((bucket) -> {
         ThirdPartyProviderStatisticsResponse.ThirdPartyProvider thirdPartyProvider = new ThirdPartyProviderStatisticsResponse.ThirdPartyProvider();
         thirdPartyProvider.setTotalTransaction((int)bucket.getDocCount());
         Map<String, Object> contractType = ((ParsedTopHits)bucket.getAggregations().getAsMap().get("contract_type")).getHits().getAt(0).getSourceAsMap();
         if (!Objects.isNull(contractType) && contractType.containsKey("contract_type") && !Objects.isNull(contractType.get("contract_type"))) {
            double amount = Double.parseDouble(AppConstants.decimalFormat.format(((ParsedSum)bucket.getAggregations().getAsMap().get("total_amount")).value()));
            if (Integer.valueOf(contractType.get("contract_type").toString()).equals(AppConstants.CONTRACT_TYPE_COMMISSION)) {
               thirdPartyProvider.setCommission(amount);
            } else if (Integer.valueOf(contractType.get("contract_type").toString()).equals(AppConstants.CONTRACT_TYPE_FEES)) {
               thirdPartyProvider.setFess(amount);
            }
         }

         Map<String, Object> providerCodeInfo = (Map)((ParsedTopHits)bucket.getAggregations().getAsMap().get("provider_code_info")).getHits().getAt(0).getSourceAsMap().get("provider_code_info");
         if (!Objects.isNull(providerCodeInfo)) {
            thirdPartyProvider.setProviderCodeInfo(providerCodeInfo);
         }

         thirdPartyProviderList.add(thirdPartyProvider);
      });
      thirdPartyProviderStatisticsResponse.setThirdPartyProviderList(thirdPartyProviderList);
      return thirdPartyProviderStatisticsResponse;
   }

   public CorporateStatisticsResponse corporateWiseStatistics(CorporateStatisticsRequest corporateStatisticsRequest) {
      CorporateStatisticsResponse corporateStatisticsResponse = new CorporateStatisticsResponse();
      BoolQueryBuilder boolQueryBuilder = this.elasticSearchUtility.getDefaultFieldBoolQueryBuilder(corporateStatisticsRequest.getCompany_id(), corporateStatisticsRequest.getDate_filter(), corporateStatisticsRequest.getDropdown_filter(), corporateStatisticsRequest.getSearch_filter());
      boolQueryBuilder.must(QueryBuilders.matchQuery("txn_code.keyword", "CBP"));
      TermsAggregationBuilder termsAggregationBuilder = (TermsAggregationBuilder)((TermsAggregationBuilder)((TermsAggregationBuilder)AggregationBuilders.terms("group_by_corporates").field("debit_account_type_id.keyword")).order(BucketOrder.aggregation("total_count", false)).subAggregation(AggregationBuilders.count("total_count").field("debit_amount"))).subAggregation(AggregationBuilders.sum("total_txn_amount").field("debit_amount"));
      NativeSearchQuery searchQuery = (new NativeSearchQueryBuilder()).withQuery(boolQueryBuilder).withAggregations(new AbstractAggregationBuilder[]{termsAggregationBuilder}).addAggregation(PipelineAggregatorBuilders.minBucket("min_txn_amount", "group_by_corporates>total_txn_amount")).addAggregation(PipelineAggregatorBuilders.maxBucket("max_txn_amount", "group_by_corporates>total_txn_amount")).addAggregation(PipelineAggregatorBuilders.sumBucket("total_txn_amount", "group_by_corporates>total_txn_amount")).addAggregation(PipelineAggregatorBuilders.sumBucket("total_txn_count", "group_by_corporates>total_count")).build();

      SearchHits<TxnMaster> response;
      try {
         response = this.elasticsearchRestTemplate.search(searchQuery, TxnMaster.class);
      } catch (Exception e) {
         Logger.error("Index Not Find Exception " + e);
         corporateStatisticsResponse.setCheckValidationFailed(true);
         corporateStatisticsResponse.setValidationMessage("TXN_MASTER_DATA_NOT_FOUND");
         return corporateStatisticsResponse;
      }

      if (response == null) {
         corporateStatisticsResponse.setValidationMessage("TXN_MASTER_DATA_NOT_FOUND");
         corporateStatisticsResponse.setCheckValidationFailed(true);
         return corporateStatisticsResponse;
      } else {
         List<Map<String, Object>> corporateStatisticsList = new ArrayList();
         Map<String, Aggregation> aggregationMap = ((Aggregations)((AggregationsContainer)Objects.requireNonNull(response.getAggregations())).aggregations()).asMap();
         ((ParsedStringTerms)aggregationMap.get("group_by_corporates")).getBuckets().forEach((bucket) -> {
            Map<String, Object> corporate = new HashMap(5);
            corporate.put("corporate_id", bucket.getKeyAsString());
            corporate.put("total_count", (int)((ParsedValueCount)bucket.getAggregations().getAsMap().get("total_count")).getValue());
            corporate.put("total_txn_amount", ((ParsedSum)bucket.getAggregations().getAsMap().get("total_txn_amount")).getValue());
            corporateStatisticsList.add(corporate);
         });
         corporateStatisticsResponse.setMinimumTxnAmount(Double.isInfinite(((ParsedBucketMetricValue)aggregationMap.get("min_txn_amount")).value()) ? (double)0.0F : ((ParsedBucketMetricValue)aggregationMap.get("min_txn_amount")).value());
         corporateStatisticsResponse.setMaximumTxnAmount(Double.isInfinite(((ParsedBucketMetricValue)aggregationMap.get("max_txn_amount")).value()) ? (double)0.0F : ((ParsedBucketMetricValue)aggregationMap.get("max_txn_amount")).value());
         corporateStatisticsResponse.setTotalTxnAmount(((ParsedSimpleValue)aggregationMap.get("total_txn_amount")).value());
         corporateStatisticsResponse.setTotalTxnCount(((ParsedSimpleValue)aggregationMap.get("total_txn_count")).value());
         corporateStatisticsResponse.setCorporateList(corporateStatisticsList);
         return corporateStatisticsResponse;
      }
   }
}
