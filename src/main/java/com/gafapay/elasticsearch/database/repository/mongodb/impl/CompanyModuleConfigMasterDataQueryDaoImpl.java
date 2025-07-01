package com.gafapay.elasticsearch.database.repository.mongodb.impl;

import com.gafapay.elasticsearch.database.mongodb.CompanyModuleConfigData;
import com.gafapay.elasticsearch.database.repository.mongodb.CompanyModuleConfigMasterDataQueryDao;
import java.util.List;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyModuleConfigMasterDataQueryDaoImpl implements CompanyModuleConfigMasterDataQueryDao {
   private final MongoTemplate mongoTemplate;

   public CompanyModuleConfigMasterDataQueryDaoImpl(MongoTemplate mongoTemplate) {
      this.mongoTemplate = mongoTemplate;
   }

   public List<CompanyModuleConfigData> findByCompanyIdAndModuleId(String companyId, String moduleId) {
      Criteria criteria = new Criteria();
      criteria.andOperator(new Criteria[]{Criteria.where("company_id").is(companyId).and("module_id").is(moduleId).and("is_active").is(true)});
      Query query = new Query(criteria);
      return this.mongoTemplate.find(query, CompanyModuleConfigData.class);
   }
}
