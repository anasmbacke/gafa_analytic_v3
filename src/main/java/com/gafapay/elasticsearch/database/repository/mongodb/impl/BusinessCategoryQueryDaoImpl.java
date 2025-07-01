package com.gafapay.elasticsearch.database.repository.mongodb.impl;

import com.gafapay.elasticsearch.database.mongodb.BusinessCategory;
import com.gafapay.elasticsearch.database.repository.mongodb.BusinessCategoryQueryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class BusinessCategoryQueryDaoImpl implements BusinessCategoryQueryDao {
   @Autowired
   private MongoTemplate mongoTemplate;

   public BusinessCategoryQueryDaoImpl() {
   }

   public void save(BusinessCategory businessCategory) {
      this.mongoTemplate.save(businessCategory);
   }

   public long update(String id, Update updateDocument) {
      Criteria criteria = new Criteria();
      criteria.andOperator(new Criteria[]{Criteria.where("_id").is(id)});
      Query query = new Query(criteria);
      return this.mongoTemplate.updateFirst(query, updateDocument, BusinessCategory.class).getModifiedCount();
   }

   public int deleteById(String id) {
      Criteria criteria = new Criteria();
      criteria.andOperator(new Criteria[]{Criteria.where("_id").is(id)});
      Query query = new Query(criteria);
      return this.mongoTemplate.remove(query, BusinessCategory.class).getDeletedCount() == 1L ? 1 : 0;
   }

   public BusinessCategory findById(String id) {
      Criteria criteria = new Criteria();
      criteria.andOperator(new Criteria[]{Criteria.where("_id").is(id)});
      Query query = new Query(criteria);
      return (BusinessCategory)this.mongoTemplate.findOne(query, BusinessCategory.class);
   }

   public BusinessCategory findByNameAndCompanyId(String name, String companyId) {
      Criteria criteria = new Criteria();
      criteria.andOperator(new Criteria[]{Criteria.where("company_id").is(companyId).and("name").is(name).is("is_active").is(true)});
      Query query = new Query(criteria);
      return (BusinessCategory)this.mongoTemplate.findOne(query, BusinessCategory.class);
   }
}
