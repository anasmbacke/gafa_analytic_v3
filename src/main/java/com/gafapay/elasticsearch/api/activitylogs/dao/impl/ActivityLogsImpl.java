package com.gafapay.elasticsearch.api.activitylogs.dao.impl;

import com.gafapay.elasticsearch.api.activitylogs.dao.ActivityLogsDao;
import com.gafapay.elasticsearch.api.activitylogs.model.ActivityLogs;
import com.gafapay.elasticsearch.api.activitylogs.model.request.GetAllActivityLogsDataRequest;
import com.gafapay.elasticsearch.utils.Utils;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;

@Repository
public class ActivityLogsImpl implements ActivityLogsDao {
   @Autowired
   private ElasticsearchRestTemplate elasticsearchRestTemplate;

   public ActivityLogsImpl() {
   }

   public List<ActivityLogs> getActivityLogs(GetAllActivityLogsDataRequest getAllActivityLogsDataRequest) {
      NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
      BoolQueryBuilder builder = new BoolQueryBuilder();
      if (!StringUtils.isEmpty(getAllActivityLogsDataRequest.getCompany_id())) {
         builder.must(QueryBuilders.matchQuery("company_id", getAllActivityLogsDataRequest.getCompany_id()));
      }

      if (!StringUtils.isEmpty(getAllActivityLogsDataRequest.getUser_id())) {
         builder.must(QueryBuilders.matchQuery("user_id", getAllActivityLogsDataRequest.getUser_id()));
      }

      if (!StringUtils.isEmpty(getAllActivityLogsDataRequest.getLog_type())) {
         builder.must(QueryBuilders.matchQuery("log_type", getAllActivityLogsDataRequest.getLog_type()));
      }

      if (Objects.nonNull(getAllActivityLogsDataRequest.getUser_type())) {
         builder.must(QueryBuilders.matchQuery("user_type", getAllActivityLogsDataRequest.getUser_type()));
      }

      if (!StringUtils.isEmpty(getAllActivityLogsDataRequest.getSearch_keyword())) {
         builder.must(QueryBuilders.matchQuery("description", getAllActivityLogsDataRequest.getSearch_keyword()));
      }

      builder.must(QueryBuilders.rangeQuery("created_date").from(Utils.getStartDateForESFilter(getAllActivityLogsDataRequest.getStart_date())).to(Utils.getEndDateForESFilter(getAllActivityLogsDataRequest.getEnd_date())));
      queryBuilder.withPageable(Utils.getSkipAndLimitForESFilter(getAllActivityLogsDataRequest.getSkip(), getAllActivityLogsDataRequest.getLimit()));
      Utils.sortBy(queryBuilder, getAllActivityLogsDataRequest.getSorting());
      NativeSearchQuery query = queryBuilder.withFilter(builder).build();
      return (List)this.elasticsearchRestTemplate.search(query, ActivityLogs.class).get().map(SearchHit::getContent).collect(Collectors.toList());
   }

   public void saveActivityLogs(ActivityLogs activityLogs) {
      this.elasticsearchRestTemplate.save(activityLogs);
   }
}
