package com.gafapay.elasticsearch.api.thirdpartytxncommissionfees.dao.impl;

import com.gafapay.elasticsearch.api.thirdpartytxncommissionfees.dao.ThirdPartyTxnCommissionFeesDao;
import com.gafapay.elasticsearch.api.thirdpartytxncommissionfees.model.request.GetAllThirdPartyTxnCommissionFeesRequest;
import com.gafapay.elasticsearch.database.model.ThirdPartyTxnCommissionFees;
import com.gafapay.elasticsearch.utils.Logger;
import com.gafapay.elasticsearch.utils.Utils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
public class ThirdPartyTxnCommissionFeesImpl implements ThirdPartyTxnCommissionFeesDao {
   @Autowired
   private ElasticsearchRestTemplate elasticsearchRestTemplate;

   public ThirdPartyTxnCommissionFeesImpl() {
   }

   public void saveOrUpdate(ThirdPartyTxnCommissionFees thirdPartyTxnCommissionFees) {
      this.elasticsearchRestTemplate.save(thirdPartyTxnCommissionFees);
   }

   public List<ThirdPartyTxnCommissionFees> getAllThirdPartyTxnCommissionFeesByFilters(GetAllThirdPartyTxnCommissionFeesRequest getAllThirdPartyTxnCommissionFeesRequest) {
      NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
      BoolQueryBuilder builder = new BoolQueryBuilder();
      builder.must(QueryBuilders.rangeQuery("created_date").from(Utils.getStartDateForESFilter(getAllThirdPartyTxnCommissionFeesRequest.getStart_date())).to(Utils.getEndDateForESFilter(getAllThirdPartyTxnCommissionFeesRequest.getEnd_date())));
      if (!StringUtils.isEmpty(getAllThirdPartyTxnCommissionFeesRequest.getCompany_id())) {
         builder.must(QueryBuilders.matchQuery("company_id", getAllThirdPartyTxnCommissionFeesRequest.getCompany_id()));
      }

      if (!Objects.isNull(getAllThirdPartyTxnCommissionFeesRequest.getTxn_id())) {
         String query_filter = getAllThirdPartyTxnCommissionFeesRequest.getTxn_id();
         ObjectMapper objectMapper = new ObjectMapper();
         List<String> queryFilterListMap = new ArrayList();

         try {
            if (!StringUtils.isEmpty(query_filter)) {
               query_filter = URLDecoder.decode(getAllThirdPartyTxnCommissionFeesRequest.getTxn_id(), StandardCharsets.UTF_8.name());
               queryFilterListMap = (List)objectMapper.readValue(query_filter, new TypeReference<List<String>>() {
               });
            }
         } catch (Exception e) {
            Logger.error(e.getMessage());
         }

         BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

         for(String txnId : queryFilterListMap) {
            boolQueryBuilder.should(QueryBuilders.matchQuery("txn_id", txnId));
         }

         builder.must(boolQueryBuilder);
      }

      if (!Objects.isNull(getAllThirdPartyTxnCommissionFeesRequest.getType())) {
         builder.must(QueryBuilders.matchQuery("type", getAllThirdPartyTxnCommissionFeesRequest.getType()));
      }

      if (!StringUtils.isEmpty(getAllThirdPartyTxnCommissionFeesRequest.getContract_type()) && !getAllThirdPartyTxnCommissionFeesRequest.getContract_type().isEmpty()) {
         builder.must(QueryBuilders.matchQuery("contract_type", getAllThirdPartyTxnCommissionFeesRequest.getContract_type()));
      }

      queryBuilder.withPageable(Utils.getSkipAndLimitForESFilter(getAllThirdPartyTxnCommissionFeesRequest.getSkip(), getAllThirdPartyTxnCommissionFeesRequest.getLimit()));
      Utils.sortBy(queryBuilder, getAllThirdPartyTxnCommissionFeesRequest.getSorting());
      NativeSearchQuery query = queryBuilder.withFilter(builder).build();
      return (List)this.elasticsearchRestTemplate.search(query, ThirdPartyTxnCommissionFees.class).get().map(SearchHit::getContent).collect(Collectors.toList());
   }

   public ThirdPartyTxnCommissionFees getById(String id) {
      return (ThirdPartyTxnCommissionFees)this.elasticsearchRestTemplate.get(id, ThirdPartyTxnCommissionFees.class);
   }
}
