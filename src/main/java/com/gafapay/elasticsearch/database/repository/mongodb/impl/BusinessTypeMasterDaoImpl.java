package com.gafapay.elasticsearch.database.repository.mongodb.impl;

import com.gafapay.elasticsearch.database.mongodb.BusinessTypeMaster;
import com.gafapay.elasticsearch.database.repository.mongodb.BusinessTypeMasterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class BusinessTypeMasterDaoImpl implements BusinessTypeMasterDao {
   @Autowired
   private MongoTemplate mongoTemplate;

   public BusinessTypeMasterDaoImpl() {
   }

   public BusinessTypeMaster findByCompanyIdAndKey(String companyId, String key) {
      Criteria criteria = new Criteria();
      criteria.andOperator(new Criteria[]{Criteria.where("key").is(key).and("company_id").is(companyId)});
      Query query = new Query(criteria);
      return (BusinessTypeMaster)this.mongoTemplate.findOne(query, BusinessTypeMaster.class);
   }
}
