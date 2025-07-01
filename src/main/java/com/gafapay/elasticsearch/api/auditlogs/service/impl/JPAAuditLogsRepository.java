package com.gafapay.elasticsearch.api.auditlogs.service.impl;

import com.gafapay.elasticsearch.api.auditlogs.dao.AuditLogsDao;
import com.gafapay.elasticsearch.api.auditlogs.model.request.GetAllAuditLogsDataRequest;
import com.gafapay.elasticsearch.api.auditlogs.model.request.GetAuditLogsDataRequest;
import com.gafapay.elasticsearch.api.auditlogs.model.response.AuditLogsData;
import com.gafapay.elasticsearch.api.auditlogs.model.response.GetAllAuditLogsDataResponse;
import com.gafapay.elasticsearch.api.auditlogs.model.response.GetAuditLogsDataResponse;
import com.gafapay.elasticsearch.api.auditlogs.service.AuditLogsRepository;
import com.gafapay.elasticsearch.database.model.AuditLogs;
import com.gafapay.elasticsearch.helper.ElasticSearchUtility;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JPAAuditLogsRepository implements AuditLogsRepository {
   @Autowired
   private AuditLogsDao auditLogsDao;
   @Autowired
   private ElasticSearchUtility elasticSearchUtility;

   public JPAAuditLogsRepository() {
   }

   public GetAllAuditLogsDataResponse getAllAuditLogs(GetAllAuditLogsDataRequest getAllAuditLogsDataRequest) {
      GetAllAuditLogsDataResponse getAllAuditLogsDataResponse = new GetAllAuditLogsDataResponse();
      List<AuditLogsData> auditLogsDataList = null;

      try {
         if (getAllAuditLogsDataRequest.getStart_date() == null || getAllAuditLogsDataRequest.getEnd_date() == null) {
            LocalDate currentDate = LocalDate.now();
            LocalDateTime fromDateTime = LocalDateTime.of(currentDate, LocalTime.MIN);
            LocalDateTime toDateTime = LocalDateTime.of(currentDate, LocalTime.MAX);
            getAllAuditLogsDataRequest.setStart_date(fromDateTime.toEpochSecond(ZoneOffset.UTC) - 19800L);
            getAllAuditLogsDataRequest.setEnd_date(toDateTime.toEpochSecond(ZoneOffset.UTC) - 19800L);
         }

         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
         Instant fromInstant = Instant.ofEpochSecond(getAllAuditLogsDataRequest.getStart_date() + 19800L);
         Instant toInstant = Instant.ofEpochSecond(getAllAuditLogsDataRequest.getEnd_date() + 19800L);
         String from = formatter.format(fromInstant.atOffset(ZoneOffset.UTC));
         String to = formatter.format(toInstant.atOffset(ZoneOffset.UTC));
         Map<String, Object> dataDogFilterQuery = new HashMap();
         Map<String, Object> dataDogPageQuery = new HashMap();
         StringBuilder stringBuilder = new StringBuilder();
         stringBuilder.append("service:audit_logs AND @company_id:" + getAllAuditLogsDataRequest.getCompany_id());
         if (getAllAuditLogsDataRequest.getId() != null) {
            stringBuilder.append(" AND @id:" + getAllAuditLogsDataRequest.getId());
         }

         if (getAllAuditLogsDataRequest.getAction() != null) {
            stringBuilder.append(" AND @action:" + getAllAuditLogsDataRequest.getAction());
         }

         if (getAllAuditLogsDataRequest.getAction_date() != null) {
            stringBuilder.append(" AND + @action_date:" + getAllAuditLogsDataRequest.getAction_date());
         }

         if (getAllAuditLogsDataRequest.getAudit_log_number() != null) {
            stringBuilder.append(" AND @audit_log_number:" + getAllAuditLogsDataRequest.getAudit_log_number());
         }

         if (getAllAuditLogsDataRequest.getCreated_by() != null) {
            stringBuilder.append(" AND @created_by:" + getAllAuditLogsDataRequest.getCreated_by());
         }

         dataDogFilterQuery.put("query", stringBuilder);
         dataDogFilterQuery.put("from", from);
         dataDogFilterQuery.put("to", to);
         getAllAuditLogsDataRequest.setFilter(dataDogFilterQuery);
         dataDogPageQuery.put("limit", Objects.isNull(getAllAuditLogsDataRequest.getLimit()) ? 10 : getAllAuditLogsDataRequest.getLimit());
         if (getAllAuditLogsDataRequest.getCursor() != null && !StringUtils.isEmpty(getAllAuditLogsDataRequest.getCursor())) {
            dataDogPageQuery.put("cursor", getAllAuditLogsDataRequest.getCursor());
         }

         getAllAuditLogsDataRequest.setPage(dataDogPageQuery);
         auditLogsDataList = this.elasticSearchUtility.getAllAuditLogsFromDataDog(getAllAuditLogsDataRequest);
      } catch (Exception var12) {
         getAllAuditLogsDataResponse.setValidationMessage("ELASTIC_SEARCH_AUDIT_LOGS_NOT_FOUND");
         getAllAuditLogsDataResponse.setCheckValidationFailed(true);
         return getAllAuditLogsDataResponse;
      }

      if (!Objects.isNull(auditLogsDataList) && !auditLogsDataList.isEmpty()) {
         getAllAuditLogsDataResponse.setAuditLogs(auditLogsDataList);
         if (getAllAuditLogsDataRequest.getAfter() != null && !StringUtils.isEmpty(getAllAuditLogsDataRequest.getAfter())) {
            getAllAuditLogsDataResponse.setCursor(getAllAuditLogsDataRequest.getAfter());
         }

         return getAllAuditLogsDataResponse;
      } else {
         getAllAuditLogsDataResponse.setValidationMessage("ELASTIC_SEARCH_AUDIT_LOGS_NOT_FOUND");
         getAllAuditLogsDataResponse.setCheckValidationFailed(true);
         return getAllAuditLogsDataResponse;
      }
   }

   public GetAuditLogsDataResponse getAuditLogs(GetAuditLogsDataRequest getAuditLogsDataRequest) {
      GetAuditLogsDataResponse getAuditLogsDataResponse = new GetAuditLogsDataResponse();

      AuditLogs auditLogs;
      try {
         auditLogs = this.auditLogsDao.getAuditLogsById(getAuditLogsDataRequest);
      } catch (Exception var5) {
         getAuditLogsDataResponse.setValidationMessage("ELASTIC_SEARCH_AUDIT_LOGS_NOT_FOUND");
         getAuditLogsDataResponse.setCheckValidationFailed(true);
         return getAuditLogsDataResponse;
      }

      if (Objects.isNull(auditLogs)) {
         getAuditLogsDataResponse.setValidationMessage("ELASTIC_SEARCH_AUDIT_LOGS_NOT_FOUND");
         getAuditLogsDataResponse.setCheckValidationFailed(true);
         return getAuditLogsDataResponse;
      } else {
         getAuditLogsDataResponse.setAuditLogs(AuditLogsData.setAuditLogs(auditLogs));
         return getAuditLogsDataResponse;
      }
   }
}
