package com.gafapay.elasticsearch.api.commonrequest;

public class DateFilter {
   private String date_field_name;
   private Long start_date;
   private Long end_date;
   private Integer filter_type;

   public String getDate_field_name() {
      return this.date_field_name;
   }

   public Long getStart_date() {
      return this.start_date;
   }

   public Long getEnd_date() {
      return this.end_date;
   }

   public Integer getFilter_type() {
      return this.filter_type;
   }

   public void setDate_field_name(final String date_field_name) {
      this.date_field_name = date_field_name;
   }

   public void setStart_date(final Long start_date) {
      this.start_date = start_date;
   }

   public void setEnd_date(final Long end_date) {
      this.end_date = end_date;
   }

   public void setFilter_type(final Integer filter_type) {
      this.filter_type = filter_type;
   }

   public DateFilter() {
   }
}
