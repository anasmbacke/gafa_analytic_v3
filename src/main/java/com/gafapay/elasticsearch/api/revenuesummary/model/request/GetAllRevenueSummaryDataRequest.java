package com.gafapay.elasticsearch.api.revenuesummary.model.request;

import com.gafapay.elasticsearch.api.commonrequest.CommonAPIDataRequest;
import org.apache.commons.lang3.StringUtils;

public class GetAllRevenueSummaryDataRequest extends CommonAPIDataRequest {
   private Double total_revenue;
   private Long summary_date;
   private Long start_date;
   private Long end_date;
   private Integer skip;
   private Integer limit;
   private String search_keyword;
   private String sorting;

   public Boolean checkBadRequest() {
      return StringUtils.isEmpty(this.getCompany_id()) ? true : false;
   }

   public Double getTotal_revenue() {
      return this.total_revenue;
   }

   public Long getSummary_date() {
      return this.summary_date;
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

   public void setTotal_revenue(final Double total_revenue) {
      this.total_revenue = total_revenue;
   }

   public void setSummary_date(final Long summary_date) {
      this.summary_date = summary_date;
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

   public GetAllRevenueSummaryDataRequest() {
   }
}
