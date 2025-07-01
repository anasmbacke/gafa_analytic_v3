package com.gafapay.elasticsearch.api.revenuesummary.service.impl;

import com.gafapay.elasticsearch.api.revenuesummary.dao.RevenueSummaryDao;
import com.gafapay.elasticsearch.api.revenuesummary.model.RevenueSummary;
import com.gafapay.elasticsearch.api.revenuesummary.model.request.GetAllRevenueSummaryDataRequest;
import com.gafapay.elasticsearch.api.revenuesummary.model.request.GetRevenueSummaryDataRequest;
import com.gafapay.elasticsearch.api.revenuesummary.model.response.GetAllRevenueSummaryDataResponse;
import com.gafapay.elasticsearch.api.revenuesummary.model.response.GetRevenueSummaryDataResponse;
import com.gafapay.elasticsearch.api.revenuesummary.model.response.GetRevenueSummaryReportResponse;
import com.gafapay.elasticsearch.api.revenuesummary.model.response.RevenueSummaryData;
import com.gafapay.elasticsearch.api.revenuesummary.service.RevenueSummaryRepository;
import com.gafapay.elasticsearch.api.statistics.model.request.GetTxnStatisticsDataRequest;
import com.gafapay.elasticsearch.api.txnmaster.model.TxnMaster;
import com.gafapay.elasticsearch.constant.AppConstants;
import com.gafapay.elasticsearch.helper.ElasticSearchUtility;
import com.gafapay.elasticsearch.utils.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.lucene.search.join.ScoreMode;
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
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.nested.ParsedNested;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ParsedSum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.NoSuchIndexException;
import org.springframework.data.elasticsearch.core.AggregationsContainer;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

@Service
public class JPARevenueSummaryRepository implements RevenueSummaryRepository {
   @Autowired
   private RevenueSummaryDao revenueSummaryDao;
   @Autowired
   private ElasticSearchUtility elasticSearchUtility;
   @Autowired
   private ElasticsearchRestTemplate elasticsearchRestTemplate;

   public JPARevenueSummaryRepository() {
   }

   public GetAllRevenueSummaryDataResponse getAllRevenueSummary(GetAllRevenueSummaryDataRequest getAllRevenueSummaryDataRequest) {
      GetAllRevenueSummaryDataResponse getAllRevenueSummaryDataResponse = new GetAllRevenueSummaryDataResponse();

      List<RevenueSummary> revenueSummaryList;
      try {
         revenueSummaryList = this.revenueSummaryDao.getAllRevenueSummary(getAllRevenueSummaryDataRequest);
      } catch (Exception var5) {
         getAllRevenueSummaryDataResponse.setValidationMessage("ELASTIC_SEARCH_REVENUE_SUMMARY_DATA_NOT_FOUND");
         getAllRevenueSummaryDataResponse.setCheckValidationFailed(true);
         return getAllRevenueSummaryDataResponse;
      }

      if (!Objects.isNull(revenueSummaryList) && !revenueSummaryList.isEmpty()) {
         List<RevenueSummaryData> revenueSummaryDataList = new ArrayList(revenueSummaryList.size());
         revenueSummaryList.parallelStream().forEachOrdered((revenueSummary) -> revenueSummaryDataList.add(RevenueSummaryData.setRevenueSummary(revenueSummary)));
         getAllRevenueSummaryDataResponse.setRevenueSummaryDataList(revenueSummaryDataList);
         return getAllRevenueSummaryDataResponse;
      } else {
         getAllRevenueSummaryDataResponse.setValidationMessage("ELASTIC_SEARCH_REVENUE_SUMMARY_DATA_NOT_FOUND");
         getAllRevenueSummaryDataResponse.setCheckValidationFailed(true);
         return getAllRevenueSummaryDataResponse;
      }
   }

   public GetRevenueSummaryDataResponse getRevenueSummary(GetRevenueSummaryDataRequest getRevenueSummaryDataRequest) {
      GetRevenueSummaryDataResponse getRevenueSummaryDataResponse = new GetRevenueSummaryDataResponse();

      RevenueSummary revenueSummary;
      try {
         revenueSummary = this.revenueSummaryDao.getRevenueSummaryById(getRevenueSummaryDataRequest);
      } catch (Exception var5) {
         getRevenueSummaryDataResponse.setValidationMessage("ELASTIC_SEARCH_REVENUE_SUMMARY_DATA_NOT_FOUND");
         getRevenueSummaryDataResponse.setCheckValidationFailed(true);
         return getRevenueSummaryDataResponse;
      }

      if (Objects.isNull(revenueSummary)) {
         getRevenueSummaryDataResponse.setValidationMessage("ELASTIC_SEARCH_REVENUE_SUMMARY_DATA_NOT_FOUND");
         getRevenueSummaryDataResponse.setCheckValidationFailed(true);
         return getRevenueSummaryDataResponse;
      } else {
         getRevenueSummaryDataResponse.setRevenueSummaryData(RevenueSummaryData.setRevenueSummary(revenueSummary));
         return getRevenueSummaryDataResponse;
      }
   }

   public GetRevenueSummaryReportResponse getRevenueSummaryReport(GetTxnStatisticsDataRequest getTxnStatisticsDataRequest) {
      GetRevenueSummaryReportResponse getRevenueSummaryReportResponse = new GetRevenueSummaryReportResponse();
      BoolQueryBuilder boolQueryBuilder = this.elasticSearchUtility.getDefaultFieldBoolQueryBuilder(getTxnStatisticsDataRequest.getCompany_id(), getTxnStatisticsDataRequest.getDate_filter(), getTxnStatisticsDataRequest.getDropdown_filter(), getTxnStatisticsDataRequest.getSearch_filter());
      NativeSearchQuery searchQuery;
      if (!getTxnStatisticsDataRequest.getDropdown_filter().getRevenue_summary_type().equals(AppConstants.DAY_WISE_REVENUE_SUMMARY_TYPE)) {
         getTxnStatisticsDataRequest.getDate_filter().setFilter_type(getTxnStatisticsDataRequest.getDropdown_filter().getRevenue_summary_type());
         getTxnStatisticsDataRequest.getDate_filter().setStart_date(getTxnStatisticsDataRequest.getDate_filter().getStart_date() + 19800L);
         getTxnStatisticsDataRequest.getDate_filter().setEnd_date(getTxnStatisticsDataRequest.getDate_filter().getEnd_date() + 19800L);
         DateHistogramInterval intervalType = this.elasticSearchUtility.getFilterType(getTxnStatisticsDataRequest.getDate_filter());
         DateHistogramAggregationBuilder dateWiseAggregationBuilder = this.elasticSearchUtility.buildHistogramAggregationBuilder("by_txn_date", intervalType, getTxnStatisticsDataRequest.getDate_filter(), ((NestedAggregationBuilder)AggregationBuilders.nested("txn_charges", "txn_charges").subAggregation(((TermsAggregationBuilder)((TermsAggregationBuilder)AggregationBuilders.terms("group_by_charge_id").field("txn_charges.charge_id.keyword")).subAggregation(AggregationBuilders.sum("total_amount").field("txn_charges.final_charge"))).subAggregation(AggregationBuilders.count("charge_name").field("txn_charges.charge_name.keyword")))).subAggregation(AggregationBuilders.sum("total_charge_amount").field("txn_charges.final_charge")));
         searchQuery = (new NativeSearchQueryBuilder()).withQuery(boolQueryBuilder).withAggregations(new AbstractAggregationBuilder[]{dateWiseAggregationBuilder}).build();
      } else {
         boolQueryBuilder.must(QueryBuilders.nestedQuery("txn_charges", QueryBuilders.boolQuery().must(QueryBuilders.existsQuery("txn_charges")), ScoreMode.None));
         searchQuery = (new NativeSearchQueryBuilder()).withQuery(boolQueryBuilder).build();
      }

      SearchHits<TxnMaster> response = null;

      try {
         response = this.elasticsearchRestTemplate.search(searchQuery, TxnMaster.class);
      } catch (IndexNotFoundException | NoSuchIndexException var8) {
         Logger.error("Index Not Found Exception occure!!");
         getRevenueSummaryReportResponse.setValidationMessage("REVENUE_SUMMARY_DATA_NOT_FOUND");
         getRevenueSummaryReportResponse.setCheckValidationFailed(true);
         return getRevenueSummaryReportResponse;
      }

      if (response.isEmpty()) {
         getRevenueSummaryReportResponse.setValidationMessage("REVENUE_SUMMARY_DATA_NOT_FOUND");
         getRevenueSummaryReportResponse.setCheckValidationFailed(true);
         return getRevenueSummaryReportResponse;
      } else {
         List<GetRevenueSummaryReportResponse.RevenueSummaryReport> revenueSummaryReportList = new ArrayList(5);
         if (!getTxnStatisticsDataRequest.getDropdown_filter().getRevenue_summary_type().equals(AppConstants.DAY_WISE_REVENUE_SUMMARY_TYPE)) {
            Map<String, Aggregation> aggregationMap = ((Aggregations)((AggregationsContainer)Objects.requireNonNull(response.getAggregations())).aggregations()).asMap();
            ((ParsedDateHistogram)aggregationMap.get("by_txn_date")).getBuckets().forEach((bucket) -> {
               GetRevenueSummaryReportResponse.RevenueSummaryReport revenueSummaryReport = new GetRevenueSummaryReportResponse.RevenueSummaryReport();
               List<GetRevenueSummaryReportResponse.RevenueDetail> revenueDetailList = new ArrayList(5);
               revenueSummaryReport.setSummaryDate(Long.valueOf(bucket.getKeyAsString()));
               revenueSummaryReport.setTotalRevenue(Double.valueOf(AppConstants.decimalFormat.format(((ParsedSum)((ParsedNested)bucket.getAggregations().getAsMap().get("txn_charges")).getAggregations().getAsMap().get("total_charge_amount")).value())));
               ((ParsedStringTerms)((ParsedNested)bucket.getAggregations().getAsMap().get("txn_charges")).getAggregations().getAsMap().get("group_by_charge_id")).getBuckets().forEach((bucketData) -> {
                  GetRevenueSummaryReportResponse.RevenueDetail revenueDetail = new GetRevenueSummaryReportResponse.RevenueDetail();
                  revenueDetail.setChargeId(bucketData.getKeyAsString());
                  revenueDetail.setFinalCharge(Double.valueOf(AppConstants.decimalFormat.format(((ParsedSum)bucketData.getAggregations().getAsMap().get("total_amount")).value())));
                  revenueDetail.setChargeName(((Aggregation)bucketData.getAggregations().getAsMap().get("charge_name")).getName());
                  revenueDetailList.add(revenueDetail);
               });
               revenueSummaryReport.setRevenueDetailList(revenueDetailList);
               revenueSummaryReportList.add(revenueSummaryReport);
            });
         } else {
            response.getSearchHits().forEach((txnMasterSearchHit) -> {
               GetRevenueSummaryReportResponse.RevenueSummaryReport revenueSummaryReport = new GetRevenueSummaryReportResponse.RevenueSummaryReport();
               List<GetRevenueSummaryReportResponse.RevenueDetail> revenueDetailList = new ArrayList(5);
               TxnMaster txnMaster = (TxnMaster)txnMasterSearchHit.getContent();
               revenueSummaryReport.setTxnId(txnMaster.getId());
               revenueSummaryReport.setTxnDate(txnMaster.getTxn_date());
               revenueSummaryReport.setTxnNumber(txnMaster.getTxnNumber());
               revenueSummaryReport.setTxnAmount(txnMaster.getTxnAmount());
               revenueSummaryReport.setTxnType(txnMaster.getTxnType());

               for(Map<String, Object> txnCharge : txnMaster.getTxnCharges()) {
                  GetRevenueSummaryReportResponse.RevenueDetail revenueDetail = new GetRevenueSummaryReportResponse.RevenueDetail();
                  revenueDetail.setChargeId(txnCharge.containsKey("charge_id") && !Objects.isNull(txnCharge.get("charge_id")) ? txnCharge.get("charge_id").toString() : null);
                  revenueDetail.setChargeName(txnCharge.containsKey("charge_name") && !Objects.isNull(txnCharge.get("charge_name")) ? txnCharge.get("charge_name").toString() : null);
                  revenueDetail.setFinalCharge(txnCharge.containsKey("final_charge") && !Objects.isNull(txnCharge.get("final_charge")) ? Double.valueOf(AppConstants.decimalFormat.format(txnCharge.get("final_charge"))) : null);
                  revenueDetailList.add(revenueDetail);
               }

               revenueSummaryReport.setRevenueDetailList(revenueDetailList);
               revenueSummaryReport.setTotalRevenue(revenueDetailList.stream().map(GetRevenueSummaryReportResponse.RevenueDetail::getFinalCharge).mapToDouble(Double::doubleValue).sum());
               revenueSummaryReportList.add(revenueSummaryReport);
            });
            getRevenueSummaryReportResponse.setRevenueSummaryReportList(revenueSummaryReportList);
         }

         getRevenueSummaryReportResponse.setRevenueSummaryReportList(revenueSummaryReportList);
         return getRevenueSummaryReportResponse;
      }
   }
}
