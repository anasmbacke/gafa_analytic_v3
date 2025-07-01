package com.gafapay.elasticsearch.database.repository.mongodb.impl;

import com.gafapay.elasticsearch.database.mongodb.UserBanks;
import com.gafapay.elasticsearch.database.repository.mongodb.UserBanksQueryDao;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserBanksQueryDaoImpl implements UserBanksQueryDao {
   private final MongoTemplate mongoTemplate;

   public UserBanksQueryDaoImpl(MongoTemplate mongoTemplate) {
      this.mongoTemplate = mongoTemplate;
   }

   public UserBanks findById(String id) {
      Criteria criteria = new Criteria();
      criteria.andOperator(new Criteria[]{Criteria.where("_id").is(id)});
      Query query = new Query(criteria);
      return (UserBanks)this.mongoTemplate.findOne(query, UserBanks.class);
   }
}
