package com.gafapay.elasticsearch.api.nodalreconciliationreport.model.request;

import com.gafapay.elasticsearch.api.commonrequest.CommonAPIDataRequest;
import com.gafapay.elasticsearch.api.commonrequest.DateFilter;
import com.gafapay.elasticsearch.api.commonrequest.DropDownFilter;
import com.gafapay.elasticsearch.api.commonrequest.SearchFilter;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

public class NodalReconciliationReportRequest extends CommonAPIDataRequest {
   private DropDownFilter dropdown_filter;
   private SearchFilter search_filter;
   private DateFilter date_filter;

   public boolean checkBadRequest() {
      return StringUtils.isEmpty(this.getCompany_id()) || StringUtils.isEmpty(this.getDropdown_filter().getCurrency_id()) || Objects.isNull(this.getDate_filter().getStart_date()) || Objects.isNull(this.getDate_filter().getEnd_date()) || Objects.isNull(this.getDate_filter().getFilter_type()) || Objects.isNull(this.getDate_filter().getDate_field_name());
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

   public DropDownFilter getDropdown_filter() {
      return this.dropdown_filter;
   }

   public SearchFilter getSearch_filter() {
      return this.search_filter;
   }

   public DateFilter getDate_filter() {
      return this.date_filter;
   }

   public NodalReconciliationReportRequest() {
   }
}
