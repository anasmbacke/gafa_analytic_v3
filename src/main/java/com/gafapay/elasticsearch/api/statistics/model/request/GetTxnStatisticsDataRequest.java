package com.gafapay.elasticsearch.api.statistics.model.request;

import com.gafapay.elasticsearch.api.commonrequest.CommonAPIDataRequest;
import com.gafapay.elasticsearch.api.commonrequest.DateFilter;
import com.gafapay.elasticsearch.api.commonrequest.DropDownFilter;
import com.gafapay.elasticsearch.api.commonrequest.SearchFilter;
import java.time.Instant;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

public class GetTxnStatisticsDataRequest extends CommonAPIDataRequest {
   private DropDownFilter dropdown_filter;
   private SearchFilter search_filter;
   private DateFilter date_filter;

   public boolean checkBadRequest() {
      return StringUtils.isEmpty(this.getCompany_id());
   }

   public DateFilter getDate_filter() {
      if (!Objects.isNull(this.date_filter) && this.hasBodyParamOfDateFilter(this.date_filter)) {
         return this.date_filter;
      } else {
         DateFilter dateFilter = new DateFilter();
         dateFilter.setFilter_type(this.getFilterType(this.date_filter));
         dateFilter.setStart_date(this.getStartDate(this.date_filter));
         dateFilter.setEnd_date(this.getEndDate(this.date_filter));
         dateFilter.setDate_field_name(this.getDateFieldName(this.date_filter));
         this.setDate_filter(dateFilter);
         return this.date_filter;
      }
   }

   private boolean hasBodyParamOfDateFilter(DateFilter dateFilter) {
      return dateFilter.getStart_date() != null && dateFilter.getEnd_date() != null;
   }

   private Integer getFilterType(DateFilter dateFilter) {
      if (Objects.isNull(dateFilter)) {
         return 1;
      } else {
         return Objects.isNull(dateFilter.getFilter_type()) ? 2 : dateFilter.getFilter_type();
      }
   }

   private Long getStartDate(DateFilter dateFilter) {
      if (Objects.isNull(dateFilter)) {
         return 1668450600L;
      } else {
         return Objects.isNull(dateFilter.getStart_date()) ? 1668450600L : dateFilter.getStart_date();
      }
   }

   private Long getEndDate(DateFilter dateFilter) {
      if (Objects.isNull(dateFilter)) {
         return Instant.now().getEpochSecond();
      } else {
         return Objects.isNull(dateFilter.getEnd_date()) ? Instant.now().getEpochSecond() : dateFilter.getEnd_date();
      }
   }

   private String getDateFieldName(DateFilter dateFilter) {
      if (Objects.isNull(dateFilter)) {
         return "txn_date";
      } else {
         return StringUtils.isEmpty(dateFilter.getDate_field_name()) ? "txn_date" : dateFilter.getDate_field_name();
      }
   }

   public DropDownFilter getDropdown_filter() {
      return this.dropdown_filter;
   }

   public SearchFilter getSearch_filter() {
      return this.search_filter;
   }

   public void setDropdown_filter(final DropDownFilter dropdown_filter) {
      this.dropdown_filter = dropdown_filter;
   }

   public void setSearch_filter(final SearchFilter search_filter) {
      this.search_filter = search_filter;
   }

   public void setDate_filter(final DateFilter date_filter) {
      this.date_filter = date_filter;
   }

   public GetTxnStatisticsDataRequest() {
   }
}
