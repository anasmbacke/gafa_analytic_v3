package com.gafapay.elasticsearch.api.commonrequest;

public class CommonAllAPIDataRequest {
   public String company_id;
   public String request_id;
   public String ip_address;
   public String language;
   private String token_user_id;
   private Boolean is_active;
   private Integer skip;
   private Integer limit;
   private Long start_date;
   private Long end_date;
   private String sorting;
   private String search_keyword;

   public String getCompany_id() {
      return this.company_id;
   }

   public String getRequest_id() {
      return this.request_id;
   }

   public String getIp_address() {
      return this.ip_address;
   }

   public String getLanguage() {
      return this.language;
   }

   public String getToken_user_id() {
      return this.token_user_id;
   }

   public Boolean getIs_active() {
      return this.is_active;
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

   public String getSorting() {
      return this.sorting;
   }

   public String getSearch_keyword() {
      return this.search_keyword;
   }

   public void setCompany_id(final String company_id) {
      this.company_id = company_id;
   }

   public void setRequest_id(final String request_id) {
      this.request_id = request_id;
   }

   public void setIp_address(final String ip_address) {
      this.ip_address = ip_address;
   }

   public void setLanguage(final String language) {
      this.language = language;
   }

   public void setToken_user_id(final String token_user_id) {
      this.token_user_id = token_user_id;
   }

   public void setIs_active(final Boolean is_active) {
      this.is_active = is_active;
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

   public void setSorting(final String sorting) {
      this.sorting = sorting;
   }

   public void setSearch_keyword(final String search_keyword) {
      this.search_keyword = search_keyword;
   }

   public CommonAllAPIDataRequest() {
   }
}
