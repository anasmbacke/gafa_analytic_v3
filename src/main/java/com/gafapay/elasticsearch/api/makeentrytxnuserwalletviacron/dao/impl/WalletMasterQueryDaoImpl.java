package com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.dao.impl;

import com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.dao.WalletMasterQueryDao;
import com.gafapay.elasticsearch.database.mongodb.WalletMaster;
import com.gafapay.elasticsearch.utils.Logger;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class WalletMasterQueryDaoImpl implements WalletMasterQueryDao {
   private final MongoTemplate mongoTemplate;

   public WalletMasterQueryDaoImpl(MongoTemplate mongoTemplate) {
      this.mongoTemplate = mongoTemplate;
   }

   public WalletMaster findWalletById(String companyId, String walletId) {
      Criteria criteria = new Criteria();
      criteria.andOperator(new Criteria[]{Criteria.where("company_id").is(companyId), Criteria.where("_id").is(walletId), Criteria.where("is_active").is(true)});
      Query query = new Query(criteria);
      Logger.info("Mongo Wallet Master Table Find Query : " + query);
      return (WalletMaster)this.mongoTemplate.findOne(query, WalletMaster.class);
   }
}
