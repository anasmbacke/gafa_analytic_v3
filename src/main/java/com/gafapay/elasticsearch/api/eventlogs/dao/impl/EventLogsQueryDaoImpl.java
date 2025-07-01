package com.gafapay.elasticsearch.api.eventlogs.dao.impl;

import com.gafapay.elasticsearch.api.eventlogs.dao.EventLogsQueryDao;
import com.gafapay.elasticsearch.api.eventlogs.model.EventLogs;
import com.gafapay.elasticsearch.api.eventlogs.model.request.GetAllEventLogsRequest;
import com.gafapay.elasticsearch.utils.Utils;
import io.micrometer.common.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class EventLogsQueryDaoImpl implements EventLogsQueryDao {
   @Autowired
   private MongoTemplate mongoTemplate;

   public EventLogsQueryDaoImpl() {
   }

   public void save(EventLogs eventLogs) {
      this.mongoTemplate.save(eventLogs);
   }

   public long update(String id, Update updateDocument) {
      Criteria criteria = new Criteria();
      criteria.andOperator(new Criteria[]{Criteria.where("_id").is(id)});
      Query query = new Query(criteria);
      return this.mongoTemplate.updateFirst(query, updateDocument, EventLogs.class).getModifiedCount();
   }

   public int deleteById(String id) {
      Criteria criteria = new Criteria();
      criteria.andOperator(new Criteria[]{Criteria.where("_id").is(id)});
      Query query = new Query(criteria);
      return this.mongoTemplate.remove(query, EventLogs.class).getDeletedCount() == 1L ? 1 : 0;
   }

   public EventLogs findById(String id) {
      Criteria criteria = new Criteria();
      criteria.andOperator(new Criteria[]{Criteria.where("_id").is(id)});
      Query query = new Query(criteria);
      return (EventLogs)this.mongoTemplate.findOne(query, EventLogs.class);
   }

   public List<EventLogs> findAllByFilter(GetAllEventLogsRequest getAllEventLogsRequest) {
      List<Criteria> criteriaArrayList = new ArrayList(0);
      List<Criteria> searchKeyWordArrayList = new ArrayList(1);
      criteriaArrayList.add(Criteria.where("company_id").is(getAllEventLogsRequest.getCompany_id()));
      if (!StringUtils.isEmpty(getAllEventLogsRequest.getEvent_log_number())) {
         criteriaArrayList.add(Criteria.where("event_log_number").is(getAllEventLogsRequest.getEvent_log_number()));
      }

      if (!Objects.isNull(getAllEventLogsRequest.getEvent_type())) {
         criteriaArrayList.add(Criteria.where("event_type").is(getAllEventLogsRequest.getEvent_type()));
      }

      if (!StringUtils.isEmpty(getAllEventLogsRequest.getSearch_keyword())) {
         getAllEventLogsRequest.setSearch_keyword(Utils.replaceString(getAllEventLogsRequest.getSearch_keyword()));
         searchKeyWordArrayList.add(Criteria.where("event_name").regex(getAllEventLogsRequest.getSearch_keyword(), "si"));
         searchKeyWordArrayList.add(Criteria.where("object_type").regex(getAllEventLogsRequest.getSearch_keyword(), "si"));
      }

      criteriaArrayList.add(Utils.setStartDateAndEndDate(getAllEventLogsRequest.getStart_date(), getAllEventLogsRequest.getEnd_date(), "created_date"));
      Criteria criteria = new Criteria();
      criteria.andOperator(criteriaArrayList);
      Query query = new Query(criteria);
      if (getAllEventLogsRequest.getSkip() != null && getAllEventLogsRequest.getLimit() != null) {
         query.skip((long)getAllEventLogsRequest.getSkip()).limit(getAllEventLogsRequest.getLimit());
      }

      return this.mongoTemplate.find(query, EventLogs.class);
   }
}
