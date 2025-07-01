package com.gafapay.elasticsearch.database.repository.mongodb.impl;

import com.gafapay.elasticsearch.database.mongodb.BankMaster;
import com.gafapay.elasticsearch.database.repository.mongodb.BankMasterQueryDao;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class BankMasterQueryDaoImpl implements BankMasterQueryDao {
   private final MongoTemplate mongoTemplate;

   public BankMasterQueryDaoImpl(MongoTemplate mongoTemplate) {
      this.mongoTemplate = mongoTemplate;
   }

   public BankMaster findById(String id) {
      Criteria criteria = new Criteria();
      criteria.andOperator(new Criteria[]{Criteria.where("_id").is(id)});
      Query query = new Query(criteria);
      return (BankMaster)this.mongoTemplate.findOne(query, BankMaster.class);
   }
}
