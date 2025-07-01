package com.gafapay.elasticsearch.api.revenuesummary.dao.impl;

import com.gafapay.elasticsearch.api.revenuesummary.dao.RevenueSummaryDao;
import com.gafapay.elasticsearch.api.revenuesummary.model.RevenueSummary;
import com.gafapay.elasticsearch.api.revenuesummary.model.request.GetAllRevenueSummaryDataRequest;
import com.gafapay.elasticsearch.api.revenuesummary.model.request.GetRevenueSummaryDataRequest;
import com.gafapay.elasticsearch.utils.Utils;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;

@Repository
public class RevenueSummaryImpl implements RevenueSummaryDao {
   @Autowired
   private ElasticsearchRestTemplate elasticsearchRestTemplate;

   public RevenueSummaryImpl() {
   }

   public void saveOrUpdate(RevenueSummary revenueSummary) {
      this.elasticsearchRestTemplate.save(revenueSummary);
   }

   public List<RevenueSummary> getAllRevenueSummary(GetAllRevenueSummaryDataRequest getAllRevenueSummaryDataRequest) {
      NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
      BoolQueryBuilder builder = new BoolQueryBuilder();
      if (!StringUtils.isEmpty(getAllRevenueSummaryDataRequest.getCompany_id())) {
         builder.must(QueryBuilders.matchQuery("company_id", getAllRevenueSummaryDataRequest.getCompany_id()));
      }

      if (Objects.nonNull(getAllRevenueSummaryDataRequest.getTotal_revenue())) {
         builder.must(QueryBuilders.matchQuery("total_revenue", getAllRevenueSummaryDataRequest.getTotal_revenue()));
      }

      if (Objects.nonNull(getAllRevenueSummaryDataRequest.getSummary_date())) {
         builder.must(QueryBuilders.matchQuery("summary_date", getAllRevenueSummaryDataRequest.getSummary_date()));
      }

      builder.must(QueryBuilders.rangeQuery("created_date").from(Utils.getStartDateForESFilter(getAllRevenueSummaryDataRequest.getStart_date())).to(Utils.getEndDateForESFilter(getAllRevenueSummaryDataRequest.getEnd_date())));
      queryBuilder.withPageable(Utils.getSkipAndLimitForESFilter(getAllRevenueSummaryDataRequest.getSkip(), getAllRevenueSummaryDataRequest.getLimit()));
      Utils.sortBy(queryBuilder, getAllRevenueSummaryDataRequest.getSorting());
      NativeSearchQuery query = queryBuilder.withFilter(builder).build();
      return (List)this.elasticsearchRestTemplate.search(query, RevenueSummary.class).get().map(SearchHit::getContent).collect(Collectors.toList());
   }

   public RevenueSummary getRevenueSummaryById(GetRevenueSummaryDataRequest getRevenueSummaryDataRequest) {
      return (RevenueSummary)this.elasticsearchRestTemplate.get(getRevenueSummaryDataRequest.getId(), RevenueSummary.class);
   }
}
