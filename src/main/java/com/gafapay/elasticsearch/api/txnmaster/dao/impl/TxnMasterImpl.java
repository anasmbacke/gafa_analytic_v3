package com.gafapay.elasticsearch.api.txnmaster.dao.impl;

import com.gafapay.elasticsearch.api.txnmaster.dao.TxnMasterDao;
import com.gafapay.elasticsearch.api.txnmaster.model.TxnMaster;
import com.gafapay.elasticsearch.api.txnmaster.model.request.GetAllTransactionsDataRequest;
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
public class TxnMasterImpl implements TxnMasterDao {
   @Autowired
   private ElasticsearchRestTemplate elasticsearchRestTemplate;

   public TxnMasterImpl() {
   }

   public List<TxnMaster> getAllTransactions(GetAllTransactionsDataRequest getAllTransactionsDataRequest) {
      NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
      BoolQueryBuilder builder = new BoolQueryBuilder();
      builder.must(QueryBuilders.matchQuery("display_end_user", true));
      builder.must(QueryBuilders.rangeQuery("created_date").from(Utils.getStartDateForESFilter(getAllTransactionsDataRequest.getStart_date())).to(Utils.getEndDateForESFilter(getAllTransactionsDataRequest.getEnd_date())));
      if (!StringUtils.isEmpty(getAllTransactionsDataRequest.getCompany_id())) {
         builder.must(QueryBuilders.matchQuery("company_id", getAllTransactionsDataRequest.getCompany_id()));
      }

      if (!StringUtils.isEmpty(getAllTransactionsDataRequest.getUser_id())) {
         builder.must(QueryBuilders.multiMatchQuery(getAllTransactionsDataRequest.getUser_id(), new String[]{"credit_account_type_id", "debit_account_type_id"}));
      }

      if (!StringUtils.isEmpty(getAllTransactionsDataRequest.getTo_user_id())) {
         builder.must(QueryBuilders.multiMatchQuery(getAllTransactionsDataRequest.getTo_user_id(), new String[]{"credit_account_type_id", "debit_account_type_id"}));
      }

      if (!StringUtils.isEmpty(getAllTransactionsDataRequest.getUser_type())) {
         builder.must(QueryBuilders.multiMatchQuery(getAllTransactionsDataRequest.getUser_type(), new String[]{"credit_account_type", "debit_account_type"}));
      }

      if (!StringUtils.isEmpty(getAllTransactionsDataRequest.getCurrency_id())) {
         builder.must(QueryBuilders.multiMatchQuery(getAllTransactionsDataRequest.getCurrency_id(), new String[]{"credit_currency_id", "debit_currency_id"}));
      }

      if (!StringUtils.isEmpty(getAllTransactionsDataRequest.getWallet_id())) {
         builder.must(QueryBuilders.multiMatchQuery(getAllTransactionsDataRequest.getWallet_id(), new String[]{"debit_type_id", "credit_type_id"}));
      }

      if (!Objects.isNull(getAllTransactionsDataRequest.getTxn_code()) && !getAllTransactionsDataRequest.getTxn_code().isEmpty()) {
         builder.must(QueryBuilders.matchQuery("txn_code", getAllTransactionsDataRequest.getTxn_code()));
      }

      if (Objects.nonNull(getAllTransactionsDataRequest.getTxn_status())) {
         builder.must(QueryBuilders.matchQuery("txn_status", getAllTransactionsDataRequest.getTxn_status()));
      }

      if (Objects.nonNull(getAllTransactionsDataRequest.getPayment_mode())) {
         builder.must(QueryBuilders.matchQuery("payment_mode", getAllTransactionsDataRequest.getPayment_mode()));
      }

      if (!StringUtils.isEmpty(getAllTransactionsDataRequest.getSearch_keyword())) {
         builder.must(QueryBuilders.matchQuery("txn_number", getAllTransactionsDataRequest.getSearch_keyword()));
      }

      if (Objects.nonNull(getAllTransactionsDataRequest.getTxn_ids()) && !getAllTransactionsDataRequest.getTxn_ids().isEmpty()) {
         builder.must(QueryBuilders.termsQuery("id", getAllTransactionsDataRequest.getTxn_ids()));
      }

      if (!Objects.isNull(getAllTransactionsDataRequest.getTxn_codes_ignore())) {
         String[] valuesArray = (String[])getAllTransactionsDataRequest.getTxn_codes_ignore().toArray(new String[0]);

         for(String txnCode : valuesArray) {
            builder.should(QueryBuilders.matchQuery("txn_code", txnCode));
         }
      }

      if (!StringUtils.isEmpty(getAllTransactionsDataRequest.getCredit_account_type_id())) {
         builder.must(QueryBuilders.matchQuery("credit_account_type_id", getAllTransactionsDataRequest.getCredit_account_type_id()));
      }

      if (!StringUtils.isEmpty(getAllTransactionsDataRequest.getDebit_account_type_id())) {
         builder.must(QueryBuilders.matchQuery("debit_account_type_id", getAllTransactionsDataRequest.getDebit_account_type_id()));
      }

      if (!StringUtils.isEmpty(getAllTransactionsDataRequest.getMerchant_category_id())) {
         builder.must(QueryBuilders.matchQuery("merchant_info.business_category.id.keyword", getAllTransactionsDataRequest.getMerchant_category_id()));
      }

      if (!StringUtils.isEmpty(getAllTransactionsDataRequest.getVendor_id())) {
         builder.must(QueryBuilders.matchQuery("payment_master.vendor_provider_info.id.keyword", getAllTransactionsDataRequest.getVendor_id()));
      }

      if (!Objects.isNull(getAllTransactionsDataRequest.getTxn_type())) {
         builder.must(QueryBuilders.matchQuery("txn_type", getAllTransactionsDataRequest.getTxn_type()));
      }

      if (!StringUtils.isEmpty(getAllTransactionsDataRequest.getDebit_type_id())) {
         builder.must(QueryBuilders.matchQuery("debit_type_id", getAllTransactionsDataRequest.getDebit_type_id()));
      }

      if (!StringUtils.isEmpty(getAllTransactionsDataRequest.getCredit_type_id())) {
         builder.must(QueryBuilders.matchQuery("credit_type_id", getAllTransactionsDataRequest.getCredit_type_id()));
      }

      if (!Objects.isNull(getAllTransactionsDataRequest.getTxn_codes())) {
         String query_filter = getAllTransactionsDataRequest.getTxn_codes();
         ObjectMapper objectMapper = new ObjectMapper();
         List<String> queryFilterListMap = new ArrayList();

         try {
            if (!StringUtils.isEmpty(query_filter)) {
               query_filter = URLDecoder.decode(getAllTransactionsDataRequest.getTxn_codes(), StandardCharsets.UTF_8.name());
               queryFilterListMap = (List)objectMapper.readValue(query_filter, new TypeReference<List<String>>() {
               });
            }
         } catch (Exception e) {
            Logger.error(e.getMessage());
         }

         BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

         for(String txnCode : queryFilterListMap) {
            boolQueryBuilder.should(QueryBuilders.matchQuery("txn_code", txnCode));
         }

         builder.must(boolQueryBuilder);
      }

      if (!StringUtils.isEmpty(getAllTransactionsDataRequest.getPayment_type_id())) {
         builder.must(QueryBuilders.multiMatchQuery(getAllTransactionsDataRequest.getPayment_type_id(), new String[]{"debit_type_id", "credit_type_id"}));
      }

      if (!StringUtils.isEmpty(getAllTransactionsDataRequest.getStore_id())) {
         builder.must(QueryBuilders.matchQuery("metadata.store_id", getAllTransactionsDataRequest.getStore_id()));
      }

      queryBuilder.withPageable(Utils.getSkipAndLimitForESFilter(getAllTransactionsDataRequest.getSkip(), getAllTransactionsDataRequest.getLimit()));
      Utils.sortBy(queryBuilder, getAllTransactionsDataRequest.getSorting());
      NativeSearchQuery query = queryBuilder.withFilter(builder).build();
      return (List)this.elasticsearchRestTemplate.search(query, TxnMaster.class).get().map(SearchHit::getContent).collect(Collectors.toList());
   }

   public void saveTxnMaster(TxnMaster txnMaster) {
      Logger.info("Save Txn master" + Utils.convertObjectToJsonString(txnMaster, ""));

      try {
         this.elasticsearchRestTemplate.save(txnMaster);
      } catch (Exception e) {
         Logger.info("Save Failed" + e.getMessage());
      }

   }

   public TxnMaster getTxnMasterById(String txnId) {
      NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
      BoolQueryBuilder builder = new BoolQueryBuilder();
      builder.must(QueryBuilders.matchQuery("id", txnId));
      NativeSearchQuery query = queryBuilder.withFilter(builder).build();
      return (TxnMaster)this.elasticsearchRestTemplate.searchOne(query, TxnMaster.class).getContent();
   }

   public List<TxnMaster> getAllTransactionsByCompanyId(String companyId) {
      NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
      BoolQueryBuilder builder = new BoolQueryBuilder();
      builder.must(QueryBuilders.matchQuery("company_id", companyId));
      NativeSearchQuery query = queryBuilder.withFilter(builder).build();
      return (List)this.elasticsearchRestTemplate.search(query, TxnMaster.class).get().map(SearchHit::getContent).collect(Collectors.toList());
   }

   public void saveAllTxnMaster(List<TxnMaster> txnMasterList) {
      this.elasticsearchRestTemplate.save(txnMasterList);
   }
}
