package com.gafapay.elasticsearch.database.repository.mongodb.impl;

import com.gafapay.elasticsearch.database.mongodb.CompanyModuleData;
import com.gafapay.elasticsearch.database.repository.mongodb.CompanyModuleDataQueryDao;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyModuleDataQueryDaoImpl implements CompanyModuleDataQueryDao {
   private final MongoTemplate mongoTemplate;

   public CompanyModuleDataQueryDaoImpl(MongoTemplate mongoTemplate) {
      this.mongoTemplate = mongoTemplate;
   }

   public CompanyModuleData getCompanyModuleByCompanyIdAndGroupKey(String companyId, String groupType) {
      return (CompanyModuleData)this.mongoTemplate.findOne(new Query((new Criteria()).andOperator(new Criteria[]{Criteria.where("company_id").is(companyId).and("group_type").is(groupType)}).and("is_active").is(true)), CompanyModuleData.class);
   }
}
