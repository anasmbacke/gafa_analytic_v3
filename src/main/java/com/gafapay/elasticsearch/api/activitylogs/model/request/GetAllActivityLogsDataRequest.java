package com.gafapay.elasticsearch.api.activitylogs.model.request;

import com.gafapay.elasticsearch.api.commonrequest.CommonAPIDataRequest;
import org.apache.commons.lang3.StringUtils;

public class GetAllActivityLogsDataRequest extends CommonAPIDataRequest {
   private String search_keyword;
   private Integer skip;
   private Integer limit;
   private Long start_date;
   private Long end_date;
   private String user_id;
   private String log_type;
   private String sorting;
   private Integer user_type;

   public Boolean checkBadRequest() {
      return StringUtils.isEmpty(this.getCompany_id()) ? true : false;
   }

   public String getSearch_keyword() {
      return this.search_keyword;
   }

   public Integer getSkip() {
      return this.skip;
   }

   public Integer getLimit() {
      return this.limit;
   }

   public Long getStart_date() {
      return this.start_date;
   }

   public Long getEnd_date() {
      return this.end_date;
   }

   public String getUser_id() {
      return this.user_id;
   }

   public String getLog_type() {
      return this.log_type;
   }

   public String getSorting() {
      return this.sorting;
   }

   public Integer getUser_type() {
      return this.user_type;
   }

   public void setSearch_keyword(final String search_keyword) {
      this.search_keyword = search_keyword;
   }

   public void setSkip(final Integer skip) {
      this.skip = skip;
   }

   public void setLimit(final Integer limit) {
      this.limit = limit;
   }

   public void setStart_date(final Long start_date) {
      this.start_date = start_date;
   }

   public void setEnd_date(final Long end_date) {
      this.end_date = end_date;
   }

   public void setUser_id(final String user_id) {
      this.user_id = user_id;
   }

   public void setLog_type(final String log_type) {
      this.log_type = log_type;
   }

   public void setSorting(final String sorting) {
      this.sorting = sorting;
   }

   public void setUser_type(final Integer user_type) {
      this.user_type = user_type;
   }

   public GetAllActivityLogsDataRequest() {
   }
}
