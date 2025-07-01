package com.gafapay.elasticsearch.database.repository.mongodb.impl;

import com.gafapay.elasticsearch.database.mongodb.CurrencyMasterData;
import com.gafapay.elasticsearch.database.repository.mongodb.CurrencyMasterDataQueryDao;
import com.gafapay.elasticsearch.utils.Logger;
import java.util.List;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CurrencyMasterDataQueryDaoImpl implements CurrencyMasterDataQueryDao {
   private final MongoTemplate mongoTemplate;

   public CurrencyMasterDataQueryDaoImpl(MongoTemplate mongoTemplate) {
      this.mongoTemplate = mongoTemplate;
   }

   public CurrencyMasterData findByCompanyIdAndCurrencyId(String companyId, String currencyId) {
      Criteria criteria = new Criteria();
      criteria.andOperator(new Criteria[]{Criteria.where("_id").is(currencyId), Criteria.where("company_id").is(companyId), Criteria.where("is_active").is(true)});
      Query query = new Query(criteria);
      Logger.info("Mongo Currency Master Table Find " + query);
      return (CurrencyMasterData)this.mongoTemplate.findOne(query, CurrencyMasterData.class);
   }

   public List<CurrencyMasterData> findByCompanyId(String companyId) {
      Criteria criteria = new Criteria();
      criteria.andOperator(new Criteria[]{Criteria.where("company_id").is(companyId), Criteria.where("is_active").is(true)});
      Query query = new Query(criteria);
      return this.mongoTemplate.find(query, CurrencyMasterData.class);
   }
}
