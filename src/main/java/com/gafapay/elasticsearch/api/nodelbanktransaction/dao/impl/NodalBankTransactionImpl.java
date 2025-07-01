package com.gafapay.elasticsearch.api.nodelbanktransaction.dao.impl;

import com.gafapay.elasticsearch.api.nodelbanktransaction.dao.NodalBankTransactionDao;
import com.gafapay.elasticsearch.api.nodelbanktransaction.model.request.GetAllNodalBankTransactionRequest;
import com.gafapay.elasticsearch.api.nodelbanktransaction.model.request.GetNodalBankTransactionRequest;
import com.gafapay.elasticsearch.database.model.nodalbanktransaction.NodalBankTransaction;
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
public class NodalBankTransactionImpl implements NodalBankTransactionDao {
   @Autowired
   private ElasticsearchRestTemplate elasticsearchRestTemplate;

   public NodalBankTransactionImpl() {
   }

   public void saveOrUpdate(NodalBankTransaction nodalBankTransaction) {
      this.elasticsearchRestTemplate.save(nodalBankTransaction);
   }

   public List<NodalBankTransaction> getAllNodalBankTransaction(GetAllNodalBankTransactionRequest getAllNodalBankTransactionRequest) {
      NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
      BoolQueryBuilder builder = new BoolQueryBuilder();
      if (!StringUtils.isEmpty(getAllNodalBankTransactionRequest.getCompany_id())) {
         builder.must(QueryBuilders.matchQuery("company_id", getAllNodalBankTransactionRequest.getCompany_id()));
      }

      if (!StringUtils.isEmpty(getAllNodalBankTransactionRequest.getNodal_bank_id())) {
         builder.must(QueryBuilders.matchQuery("nodal_bank_id", getAllNodalBankTransactionRequest.getNodal_bank_id()));
      }

      if (Objects.nonNull(getAllNodalBankTransactionRequest.getOperation_type())) {
         builder.must(QueryBuilders.matchQuery("operation_type", getAllNodalBankTransactionRequest.getOperation_type()));
      }

      if (Objects.nonNull(getAllNodalBankTransactionRequest.getPurpose())) {
         builder.must(QueryBuilders.matchQuery("purpose", getAllNodalBankTransactionRequest.getPurpose()));
      }

      if (!StringUtils.isEmpty(getAllNodalBankTransactionRequest.getTxn_number())) {
         builder.must(QueryBuilders.matchQuery("txn_number", getAllNodalBankTransactionRequest.getTxn_number()));
      }

      if (Objects.nonNull(getAllNodalBankTransactionRequest.getTxn_date())) {
         builder.must(QueryBuilders.matchQuery("txn_date", getAllNodalBankTransactionRequest.getTxn_date()));
      }

      if (Objects.nonNull(getAllNodalBankTransactionRequest.getTxn_status())) {
         builder.must(QueryBuilders.matchQuery("txn_status", getAllNodalBankTransactionRequest.getTxn_status()));
      }

      if (!Objects.isNull(getAllNodalBankTransactionRequest.getReference_txn_id()) && !getAllNodalBankTransactionRequest.getReference_txn_id().isEmpty()) {
         BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

         for(String referenceTxnId : getAllNodalBankTransactionRequest.getReference_txn_id()) {
            boolQueryBuilder.should(QueryBuilders.matchQuery("reference_txn_id", referenceTxnId));
         }

         builder.must(boolQueryBuilder);
      }

      builder.must(QueryBuilders.rangeQuery("created_date").from(Utils.getStartDateForESFilter(getAllNodalBankTransactionRequest.getStart_date())).to(Utils.getEndDateForESFilter(getAllNodalBankTransactionRequest.getEnd_date())));
      queryBuilder.withPageable(Utils.getSkipAndLimitForESFilter(getAllNodalBankTransactionRequest.getSkip(), getAllNodalBankTransactionRequest.getLimit()));
      Utils.sortBy(queryBuilder, getAllNodalBankTransactionRequest.getSorting());
      NativeSearchQuery query = queryBuilder.withFilter(builder).build();
      return (List)this.elasticsearchRestTemplate.search(query, NodalBankTransaction.class).get().map(SearchHit::getContent).collect(Collectors.toList());
   }

   public NodalBankTransaction getNodalBankTransactionById(GetNodalBankTransactionRequest getNodalBankTransactionRequest) {
      return (NodalBankTransaction)this.elasticsearchRestTemplate.get(getNodalBankTransactionRequest.getId(), NodalBankTransaction.class);
   }
}
