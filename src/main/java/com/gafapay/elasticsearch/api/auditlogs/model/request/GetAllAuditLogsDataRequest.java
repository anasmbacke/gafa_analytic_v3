package com.gafapay.elasticsearch.api.auditlogs.model.request;

import com.gafapay.elasticsearch.api.commonrequest.CommonAPIDataRequest;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class GetAllAuditLogsDataRequest extends CommonAPIDataRequest {
   private String id;
   private Integer action;
   private Long action_date;
   private String audit_log_number;
   private String created_by;
   private Long start_date;
   private Long end_date;
   private Integer skip;
   private Integer limit;
   private String search_keyword;
   private String sorting;
   private String cursor;
   Map<String, Object> filter;
   Map<String, Object> page;
   String after;

   public Boolean checkBadRequest() {
      return StringUtils.isEmpty(this.getCompany_id()) ? true : false;
   }

   public String getId() {
      return this.id;
   }

   public Integer getAction() {
      return this.action;
   }

   public Long getAction_date() {
      return this.action_date;
   }

   public String getAudit_log_number() {
      return this.audit_log_number;
   }

   public String getCreated_by() {
      return this.created_by;
   }

   public Long getStart_date() {
      return this.start_date;
   }

   public Long getEnd_date() {
      return this.end_date;
   }

   public Integer getSkip() {
      return this.skip;
   }

   public Integer getLimit() {
      return this.limit;
   }

   public String getSearch_keyword() {
      return this.search_keyword;
   }

   public String getSorting() {
      return this.sorting;
   }

   public String getCursor() {
      return this.cursor;
   }

   public Map<String, Object> getFilter() {
      return this.filter;
   }

   public Map<String, Object> getPage() {
      return this.page;
   }

   public String getAfter() {
      return this.after;
   }

   public void setId(final String id) {
      this.id = id;
   }

   public void setAction(final Integer action) {
      this.action = action;
   }

   public void setAction_date(final Long action_date) {
      this.action_date = action_date;
   }

   public void setAudit_log_number(final String audit_log_number) {
      this.audit_log_number = audit_log_number;
   }

   public void setCreated_by(final String created_by) {
      this.created_by = created_by;
   }

   public void setStart_date(final Long start_date) {
      this.start_date = start_date;
   }

   public void setEnd_date(final Long end_date) {
      this.end_date = end_date;
   }

   public void setSkip(final Integer skip) {
      this.skip = skip;
   }

   public void setLimit(final Integer limit) {
      this.limit = limit;
   }

   public void setSearch_keyword(final String search_keyword) {
      this.search_keyword = search_keyword;
   }

   public void setSorting(final String sorting) {
      this.sorting = sorting;
   }

   public void setCursor(final String cursor) {
      this.cursor = cursor;
   }

   public void setFilter(final Map<String, Object> filter) {
      this.filter = filter;
   }

   public void setPage(final Map<String, Object> page) {
      this.page = page;
   }

   public void setAfter(final String after) {
      this.after = after;
   }

   public GetAllAuditLogsDataRequest() {
   }
}
