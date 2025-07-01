package com.gafapay.elasticsearch.database.repository.mongodb.impl;

import com.gafapay.elasticsearch.database.mongodb.ProductMaster;
import com.gafapay.elasticsearch.database.repository.mongodb.ProductMasterQueryDao;
import com.gafapay.elasticsearch.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ProductMasterQueryDaoImpl implements ProductMasterQueryDao {
   @Autowired
   private MongoTemplate mongoTemplate;

   public ProductMasterQueryDaoImpl() {
   }

   public ProductMaster findByCompanyIdAndProductId(String companyId, String productId) {
      Criteria criteria = new Criteria();
      criteria.andOperator(new Criteria[]{Criteria.where("_id").is(productId), Criteria.where("company_id").is(companyId), Criteria.where("is_active").is(true)});
      Query query = new Query(criteria);
      Logger.info("Mongo ProductMaster Table Find " + query);
      return (ProductMaster)this.mongoTemplate.findOne(query, ProductMaster.class);
   }
}
