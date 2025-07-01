package com.gafapay.elasticsearch.api.commonrequest;

public class CommonAPIDataRequest {
   public String company_id;
   public String request_id;
   public String ip_address;
   public String language;
   private String token_user_id;

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

   public CommonAPIDataRequest() {
   }
}
