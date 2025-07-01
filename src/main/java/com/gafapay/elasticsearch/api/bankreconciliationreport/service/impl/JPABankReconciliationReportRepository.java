package com.gafapay.elasticsearch.api.bankreconciliationreport.service.impl;

import com.gafapay.elasticsearch.api.bankreconciliationreport.model.request.GetBankReconciliationReportRequest;
import com.gafapay.elasticsearch.api.bankreconciliationreport.model.response.GetBankReconciliationReportResponse;
import com.gafapay.elasticsearch.api.bankreconciliationreport.service.BankReconciliationReportRepository;
import com.gafapay.elasticsearch.constant.AppConstants;
import com.gafapay.elasticsearch.database.model.nodalbanktransaction.NodalBankTransaction;
import com.gafapay.elasticsearch.helper.ElasticSearchUtility;
import com.gafapay.elasticsearch.utils.Logger;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.elasticsearch.common.Strings;
import org.elasticsearch.index.IndexNotFoundException;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.ParsedDateHistogram;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ParsedSum;
import org.elasticsearch.search.aggregations.metrics.ParsedTopHits;
import org.elasticsearch.search.aggregations.metrics.TopHits;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
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
public class JPABankReconciliationReportRepository implements BankReconciliationReportRepository {
   @Autowired
   private ElasticSearchUtility elasticSearchUtility;
   @Autowired
   private ElasticsearchRestTemplate elasticsearchRestTemplate;

   public JPABankReconciliationReportRepository() {
   }

   public GetBankReconciliationReportResponse getBankReconciliationReport(GetBankReconciliationReportRequest getBankReconciliationReportRequest) {
      GetBankReconciliationReportResponse getBankReconciliationReportResponse = new GetBankReconciliationReportResponse();
      BoolQueryBuilder boolQueryBuilder = this.elasticSearchUtility.getDefaultFieldBoolQueryBuilder(getBankReconciliationReportRequest.getCompany_id(), getBankReconciliationReportRequest.getDate_filter(), getBankReconciliationReportRequest.getDropdown_filter(), getBankReconciliationReportRequest.getSearch_filter());
      DateHistogramInterval intervalType = this.elasticSearchUtility.getFilterType(getBankReconciliationReportRequest.getDate_filter());
      getBankReconciliationReportRequest.getDate_filter().setStart_date(getBankReconciliationReportRequest.getDate_filter().getStart_date() + 19800L);
      getBankReconciliationReportRequest.getDate_filter().setEnd_date(getBankReconciliationReportRequest.getDate_filter().getEnd_date() + 19800L);
      DateHistogramAggregationBuilder dateWiseAggregationBuilder = this.elasticSearchUtility.buildHistogramAggregationBuilder("by_txn_date", intervalType, getBankReconciliationReportRequest.getDate_filter(), AggregationBuilders.topHits("top_hits").sort("created_date", SortOrder.DESC).size(1).fetchSource(new String[]{"closing_balance"}, Strings.EMPTY_ARRAY), ((TermsAggregationBuilder)AggregationBuilders.terms("group_by_operation_type").field("operation_type")).subAggregation(AggregationBuilders.sum("total_amount").field("amount")));
      NativeSearchQuery searchQuery = (new NativeSearchQueryBuilder()).withQuery(boolQueryBuilder).withSorts(new SortBuilder[]{SortBuilders.fieldSort("created_date").order(SortOrder.DESC)}).withAggregations(new AbstractAggregationBuilder[]{dateWiseAggregationBuilder}).build();

      SearchHits<NodalBankTransaction> response;
      try {
         response = this.elasticsearchRestTemplate.search(searchQuery, NodalBankTransaction.class);
      } catch (IndexNotFoundException | NoSuchIndexException var10) {
         Logger.error("Index Not Found Exception occur!!");
         return null;
      }

      List<GetBankReconciliationReportResponse.BankReconciliation> bankReconciliationList = new ArrayList();
      Map<String, Aggregation> aggregationMap = ((Aggregations)((AggregationsContainer)Objects.requireNonNull(response.getAggregations())).aggregations()).asMap();
      ((ParsedDateHistogram)aggregationMap.get("by_txn_date")).getBuckets().forEach((bucket) -> {
         GetBankReconciliationReportResponse.BankReconciliation bankReconciliation = new GetBankReconciliationReportResponse.BankReconciliation();
         bankReconciliation.setTimestamp(ZonedDateTime.parse(bucket.getKey().toString()).toEpochSecond());
         double closingBalance = (double)0.0F;
         TopHits topHits = (ParsedTopHits)bucket.getAggregations().getAsMap().get("top_hits");
         if (!Objects.isNull(topHits) && !Arrays.asList(topHits.getHits().getHits()).isEmpty()) {
            Map<String, Object> nodalBankTransaction = topHits.getHits().getAt(0).getSourceAsMap();
            closingBalance = !Objects.isNull(nodalBankTransaction) && nodalBankTransaction.containsKey("closing_balance") && !Objects.isNull(nodalBankTransaction.get("closing_balance")) ? Double.parseDouble(nodalBankTransaction.get("closing_balance").toString()) : (double)0.0F;
         }

         bankReconciliation.setClosingBalance(closingBalance);
         if (!((ParsedLongTerms)bucket.getAggregations().getAsMap().get("group_by_operation_type")).getBuckets().isEmpty()) {
            double creditBalance = (double)0.0F;
            double debitBalance = (double)0.0F;

            for(Terms.Bucket operationType : ((ParsedLongTerms)bucket.getAggregations().getAsMap().get("group_by_operation_type")).getBuckets()) {
               if (Integer.valueOf(operationType.getKeyAsString()).equals(AppConstants.OPERATION_TYPE_CREDIT)) {
                  creditBalance = ((ParsedSum)operationType.getAggregations().getAsMap().get("total_amount")).getValue();
               } else if (Integer.valueOf(operationType.getKeyAsString()).equals(AppConstants.OPERATION_TYPE_DEBIT)) {
                  debitBalance = ((ParsedSum)operationType.getAggregations().getAsMap().get("total_amount")).getValue();
               }
            }

            bankReconciliation.setCredit(creditBalance);
            bankReconciliation.setDebit(debitBalance);
         }

         bankReconciliationList.add(bankReconciliation);
      });
      getBankReconciliationReportResponse.setBankReconciliationList(bankReconciliationList);
      return getBankReconciliationReportResponse;
   }
}
