package com.gafapay.elasticsearch.api.sendreport.model.request;

import com.gafapay.elasticsearch.api.commonrequest.CommonAPIDataRequest;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

public class SendReportDataRequest extends CommonAPIDataRequest {
   private int report_type;
   private String report_name;
   private Map<String, Object> report_statistics;
   private String subject;
   private String body;
   private String company_name;
   private String start_date;
   private String end_date;
   private String header_text;
   private String logo_url;

   public boolean checkBadRequest() {
      if (StringUtils.isEmpty(this.company_id)) {
         return true;
      } else if (Objects.isNull(this.report_type)) {
         return true;
      } else {
         return StringUtils.isEmpty(this.report_name);
      }
   }

   public int getReport_type() {
      return this.report_type;
   }

   public String getReport_name() {
      return this.report_name;
   }

   public Map<String, Object> getReport_statistics() {
      return this.report_statistics;
   }

   public String getSubject() {
      return this.subject;
   }

   public String getBody() {
      return this.body;
   }

   public String getCompany_name() {
      return this.company_name;
   }

   public String getStart_date() {
      return this.start_date;
   }

   public String getEnd_date() {
      return this.end_date;
   }

   public String getHeader_text() {
      return this.header_text;
   }

   public String getLogo_url() {
      return this.logo_url;
   }

   public void setReport_type(final int report_type) {
      this.report_type = report_type;
   }

   public void setReport_name(final String report_name) {
      this.report_name = report_name;
   }

   public void setReport_statistics(final Map<String, Object> report_statistics) {
      this.report_statistics = report_statistics;
   }

   public void setSubject(final String subject) {
      this.subject = subject;
   }

   public void setBody(final String body) {
      this.body = body;
   }

   public void setCompany_name(final String company_name) {
      this.company_name = company_name;
   }

   public void setStart_date(final String start_date) {
      this.start_date = start_date;
   }

   public void setEnd_date(final String end_date) {
      this.end_date = end_date;
   }

   public void setHeader_text(final String header_text) {
      this.header_text = header_text;
   }

   public void setLogo_url(final String logo_url) {
      this.logo_url = logo_url;
   }

   public SendReportDataRequest() {
   }
}
