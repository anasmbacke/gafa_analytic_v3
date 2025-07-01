package com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.dao.impl;

import com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.dao.TxnUserWalletDao;
import com.gafapay.elasticsearch.database.model.TxnUserWallet;
import java.util.Objects;
import java.util.Optional;
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
public class TxnUserWalletImpl implements TxnUserWalletDao {
   @Autowired
   private ElasticsearchRestTemplate elasticsearchRestTemplate;

   public TxnUserWalletImpl() {
   }

   public void saveTxnMaster(TxnUserWallet txnUserWallet) {
      this.elasticsearchRestTemplate.save(txnUserWallet);
   }

   public TxnUserWallet findByTypeAndTypeIdAndCurrencyIdAndIsMasterEntry(Integer type, String typeId, String currencyId, Boolean isMasterEntry, String companyId) {
      NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
      BoolQueryBuilder builder = new BoolQueryBuilder();
      if (!StringUtils.isEmpty(companyId)) {
         builder.must(QueryBuilders.matchQuery("company_id", companyId));
      }

      if (!Objects.isNull(type)) {
         builder.must(QueryBuilders.matchQuery("type", type));
      }

      if (!StringUtils.isEmpty(typeId)) {
         builder.must(QueryBuilders.matchQuery("type_id", typeId));
      }

      if (!StringUtils.isEmpty(currencyId)) {
         builder.must(QueryBuilders.matchQuery("currency_id", currencyId));
      }

      if (!Objects.isNull(isMasterEntry)) {
         builder.must(QueryBuilders.matchQuery("is_master_entry", isMasterEntry));
      }

      NativeSearchQuery query = queryBuilder.withFilter(builder).build();
      Optional<TxnUserWallet> elasticSearchTxnUserWallet = this.elasticsearchRestTemplate.search(query, TxnUserWallet.class).get().map(SearchHit::getContent).findFirst();
      return (TxnUserWallet)elasticSearchTxnUserWallet.orElse((Object)null);
   }
}
