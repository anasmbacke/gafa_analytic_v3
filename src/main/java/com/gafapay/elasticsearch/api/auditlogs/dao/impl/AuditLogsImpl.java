package com.gafapay.elasticsearch.api.auditlogs.dao.impl;

import com.gafapay.elasticsearch.api.auditlogs.dao.AuditLogsDao;
import com.gafapay.elasticsearch.api.auditlogs.model.request.GetAllAuditLogsDataRequest;
import com.gafapay.elasticsearch.api.auditlogs.model.request.GetAuditLogsDataRequest;
import com.gafapay.elasticsearch.database.model.AuditLogs;
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
public class AuditLogsImpl implements AuditLogsDao {
   @Autowired
   private ElasticsearchRestTemplate elasticsearchRestTemplate;

   public AuditLogsImpl() {
   }

   public void saveOrUpdate(AuditLogs auditLogs) {
      this.elasticsearchRestTemplate.save(auditLogs);
   }

   public List<AuditLogs> getAllAuditLogs(GetAllAuditLogsDataRequest getAllAuditLogsDataRequest) {
      NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
      BoolQueryBuilder builder = new BoolQueryBuilder();
      builder.must(QueryBuilders.matchQuery("company_id", getAllAuditLogsDataRequest.getCompany_id()));
      if (Objects.nonNull(getAllAuditLogsDataRequest.getAction())) {
         builder.must(QueryBuilders.matchQuery("action", getAllAuditLogsDataRequest.getAction()));
      }

      if (Objects.nonNull(getAllAuditLogsDataRequest.getAction_date())) {
         builder.must(QueryBuilders.matchQuery("action_date", getAllAuditLogsDataRequest.getAction_date()));
      }

      if (!StringUtils.isEmpty(getAllAuditLogsDataRequest.getAudit_log_number())) {
         builder.must(QueryBuilders.matchQuery("audit_log_number", getAllAuditLogsDataRequest.getAudit_log_number()));
      }

      if (!StringUtils.isEmpty(getAllAuditLogsDataRequest.getCreated_by())) {
         builder.must(QueryBuilders.matchQuery("created_by", getAllAuditLogsDataRequest.getCreated_by()));
      }

      if (!StringUtils.isEmpty(getAllAuditLogsDataRequest.getSearch_keyword())) {
         builder.must(QueryBuilders.multiMatchQuery(getAllAuditLogsDataRequest.getSearch_keyword(), new String[]{"resource_name", "audit_log_number"}));
      }

      builder.must(QueryBuilders.rangeQuery("created_date").from(Utils.getStartDateForESFilter(getAllAuditLogsDataRequest.getStart_date())).to(Utils.getEndDateForESFilter(getAllAuditLogsDataRequest.getEnd_date())));
      queryBuilder.withPageable(Utils.getSkipAndLimitForESFilter(getAllAuditLogsDataRequest.getSkip(), getAllAuditLogsDataRequest.getLimit()));
      Utils.sortBy(queryBuilder, getAllAuditLogsDataRequest.getSorting());
      NativeSearchQuery query = queryBuilder.withFilter(builder).build();
      return (List)this.elasticsearchRestTemplate.search(query, AuditLogs.class).get().map(SearchHit::getContent).collect(Collectors.toList());
   }

   public AuditLogs getAuditLogsById(GetAuditLogsDataRequest getAuditLogsDataRequest) {
      return (AuditLogs)this.elasticsearchRestTemplate.get(getAuditLogsDataRequest.getId(), AuditLogs.class);
   }
}
