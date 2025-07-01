package com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.dao.impl;

import com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.dao.UserMasterDataQueryDao;
import com.gafapay.elasticsearch.database.mongodb.UserMasterData;
import com.gafapay.elasticsearch.utils.Logger;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserMasterDataQueryDaoImpl implements UserMasterDataQueryDao {
   @Autowired
   private MongoTemplate mongoTemplate;

   public UserMasterDataQueryDaoImpl() {
   }

   public UserMasterData findById(String id) {
      Criteria criteria = new Criteria();
      criteria.andOperator(new Criteria[]{Criteria.where("_id").is(id)});
      Query query = new Query(criteria);
      Logger.info("Mongo UserMasterData Table Find Query " + query);
      return (UserMasterData)this.mongoTemplate.findOne(query, UserMasterData.class);
   }

   public Integer findUserTypeByUserId(String userId) {
      MatchOperation matchOperation = Aggregation.match(Criteria.where("_id").is(userId));
      ProjectionOperation projectionOperation = Aggregation.project(new String[]{"user_type"});
      Aggregation aggregation = Aggregation.newAggregation(new AggregationOperation[]{matchOperation, projectionOperation});
      UserMasterData userMasterData = (UserMasterData)this.mongoTemplate.aggregate(aggregation, "user_master", UserMasterData.class).getUniqueMappedResult();
      return userMasterData != null && userMasterData.getUserType() != null ? userMasterData.getUserType() : null;
   }

   public List<String> getAllUserIdsByCompanyIdAndUserType(String companyId, Integer userType) {
      MatchOperation matchOperation = Aggregation.match(Criteria.where("company_id").is(companyId).and("user_type").is(userType));
      ProjectionOperation projectionOperation = Aggregation.project(new String[]{"_id"});
      Aggregation aggregation = Aggregation.newAggregation(new AggregationOperation[]{matchOperation, projectionOperation});
      List<UserMasterData> userMasterData = this.mongoTemplate.aggregate(aggregation, "user_master", UserMasterData.class).getMappedResults();
      return !userMasterData.isEmpty() ? (List)userMasterData.stream().map(UserMasterData::getId).collect(Collectors.toList()) : null;
   }

   public String getCompanyAdminIdByCompanyId(String companyId) {
      Criteria criteria = new Criteria();
      criteria.andOperator(new Criteria[]{Criteria.where("is_active").is(true), Criteria.where("company_id").is(companyId), Criteria.where("is_superuser").is(false), Criteria.where("user_type").is(1)});
      Query query = new Query(criteria);
      UserMasterData userMasterData = (UserMasterData)this.mongoTemplate.findOne(query, UserMasterData.class);
      return !Objects.isNull(userMasterData) ? userMasterData.getId() : null;
   }

   public void findByCompanyIdAndDateWiseAllCountOfUser(String companyId, Long startDate, Long endDate, Map<String, Object> stringObjectMap) {
      Criteria criteria = new Criteria();
      criteria.andOperator(new Criteria[]{Criteria.where("company_id").is(companyId).and("is_active").is(true)});
      Query query = new Query(criteria);
      stringObjectMap.put("total_customer", this.mongoTemplate.find(query, UserMasterData.class).size());
      criteria = new Criteria();
      criteria.andOperator(new Criteria[]{Criteria.where("company_id").is(companyId).and("is_active").is(true).and("created_date").gte(startDate).lte(endDate)});
      query = new Query(criteria);
      stringObjectMap.put("new_onboarding", this.mongoTemplate.find(query, UserMasterData.class).size());
   }

   public UserMasterData getAdminInfo(String companyId) {
      Criteria criteria = new Criteria();
      criteria.andOperator(new Criteria[]{Criteria.where("is_active").is(true), Criteria.where("company_id").is(companyId), Criteria.where("is_superuser").is(false), Criteria.where("user_type").is(1)});
      Query query = new Query(criteria);
      return (UserMasterData)this.mongoTemplate.findOne(query, UserMasterData.class);
   }
}
