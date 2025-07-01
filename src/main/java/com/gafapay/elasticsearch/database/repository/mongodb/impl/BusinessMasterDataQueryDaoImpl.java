package com.gafapay.elasticsearch.database.repository.mongodb.impl;

import com.gafapay.elasticsearch.database.mongodb.BusinessMaster;
import com.gafapay.elasticsearch.database.repository.mongodb.BusinessMasterDataQueryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class BusinessMasterDataQueryDaoImpl implements BusinessMasterDataQueryDao {
   @Autowired
   private MongoTemplate mongoTemplate;

   public BusinessMasterDataQueryDaoImpl() {
   }

   public BusinessMaster findByCompanyIdAndUserId(String companyId, String userId) {
      Criteria criteria = new Criteria();
      criteria.andOperator(new Criteria[]{Criteria.where("company_id").is(companyId), Criteria.where("user_id").is(userId)});
      Query query = new Query(criteria);
      return (BusinessMaster)this.mongoTemplate.findOne(query, BusinessMaster.class);
   }
}
