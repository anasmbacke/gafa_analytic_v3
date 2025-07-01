package com.gafapay.elasticsearch.api.topmostearneragents.service.impl;

import com.gafapay.elasticsearch.api.topmostearneragents.model.request.TopMostEarnerAgentsRequest;
import com.gafapay.elasticsearch.api.topmostearneragents.model.response.TopMostEarnerAgentsResponse;
import com.gafapay.elasticsearch.api.topmostearneragents.service.TopMostEarnerAgentRepository;
import com.gafapay.elasticsearch.api.txnmaster.model.TxnMaster;
import com.gafapay.elasticsearch.constant.AppConstants;
import com.gafapay.elasticsearch.helper.ElasticSearchUtility;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.nested.ParsedNested;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ParsedSum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.AggregationsContainer;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

@Service
public class JPATopMostEarnerAgentRepository implements TopMostEarnerAgentRepository {
   @Autowired
   private ElasticSearchUtility elasticSearchUtility;
   @Autowired
   private ElasticsearchRestTemplate elasticsearchRestTemplate;

   public JPATopMostEarnerAgentRepository() {
   }

   public TopMostEarnerAgentsResponse topMostEarnerAgents(TopMostEarnerAgentsRequest topMostEarnerAgentsRequest) {
      TopMostEarnerAgentsResponse topMostEarnerAgentsResponse = new TopMostEarnerAgentsResponse();
      NestedAggregationBuilder nestedBuilder = (NestedAggregationBuilder)AggregationBuilders.nested("txn_commissions", "txn_commissions").subAggregation(((TermsAggregationBuilder)((TermsAggregationBuilder)AggregationBuilders.terms("group_by_user_id").field("txn_commissions.user_id.keyword")).size(topMostEarnerAgentsRequest.getDropdown_filter().getTop_most_earner_agent_count()).order(BucketOrder.aggregation("final_total_amount.value", false)).subAggregation(((TermsAggregationBuilder)AggregationBuilders.terms("group_by_product_code").field("txn_commissions.product_code.keyword")).subAggregation(AggregationBuilders.sum("total_amount").field("txn_commissions.final_commission")))).subAggregation(AggregationBuilders.sum("final_total_amount").field("txn_commissions.final_commission")));
      BoolQueryBuilder builder = this.elasticSearchUtility.getDefaultFieldBoolQueryBuilder(topMostEarnerAgentsRequest.getCompany_id(), topMostEarnerAgentsRequest.getDate_filter(), topMostEarnerAgentsRequest.getDropdown_filter(), topMostEarnerAgentsRequest.getSearch_filter());
      NativeSearchQuery searchQuery = (new NativeSearchQueryBuilder()).withQuery(builder).withAggregations(new AbstractAggregationBuilder[]{nestedBuilder}).build();
      SearchHits<TxnMaster> response = this.elasticsearchRestTemplate.search(searchQuery, TxnMaster.class);
      Map<String, Aggregation> aggregationMap = ((Aggregations)((AggregationsContainer)Objects.requireNonNull(response.getAggregations())).aggregations()).asMap();
      List<TopMostEarnerAgentsResponse.TopMostEarnerAgent> topMostEarnerAgentList = new ArrayList(5);
      ((ParsedStringTerms)((ParsedNested)aggregationMap.get("txn_commissions")).getAggregations().getAsMap().get("group_by_user_id")).getBuckets().forEach((bucket) -> {
         TopMostEarnerAgentsResponse.TopMostEarnerAgent topMostEarnerAgent = new TopMostEarnerAgentsResponse.TopMostEarnerAgent();
         topMostEarnerAgent.setUserId(bucket.getKeyAsString());
         List<TopMostEarnerAgentsResponse.Statistics> statisticsList = new ArrayList(5);
         ((ParsedStringTerms)bucket.getAggregations().getAsMap().get("group_by_product_code")).getBuckets().forEach((product) -> {
            TopMostEarnerAgentsResponse.Statistics statistics = new TopMostEarnerAgentsResponse.Statistics();
            statistics.setProductCode(product.getKeyAsString());
            statistics.setTotalCount(product.getDocCount());
            Double total_amount = ((ParsedSum)product.getAggregations().getAsMap().get("total_amount")).value();
            statistics.setTotalAmount(Double.parseDouble(AppConstants.decimalFormat.format(total_amount)));
            statisticsList.add(statistics);
         });
         topMostEarnerAgent.setStatistics(statisticsList);
         topMostEarnerAgentList.add(topMostEarnerAgent);
      });
      topMostEarnerAgentsResponse.setTopMostEarnerAgentList(topMostEarnerAgentList);
      return topMostEarnerAgentsResponse;
   }
}
