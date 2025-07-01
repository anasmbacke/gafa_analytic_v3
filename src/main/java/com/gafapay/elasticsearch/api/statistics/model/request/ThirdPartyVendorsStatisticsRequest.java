package com.gafapay.elasticsearch.api.statistics.model.request;

import com.gafapay.elasticsearch.api.commonrequest.CommonAPIDataRequest;
import com.gafapay.elasticsearch.api.commonrequest.DateFilter;
import com.gafapay.elasticsearch.api.commonrequest.DropDownFilter;
import com.gafapay.elasticsearch.api.commonrequest.SearchFilter;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

public class ThirdPartyVendorsStatisticsRequest extends CommonAPIDataRequest {
   private DropDownFilter dropdown_filter;
   private DateFilter date_filter;
   private SearchFilter search_filter;

   public boolean checkBadRequest() {
      return StringUtils.isEmpty(this.getCompany_id()) || Objects.isNull(this.getDropdown_filter().getType());
   }

   public DropDownFilter getDropdown_filter() {
      return this.dropdown_filter;
   }

   public DateFilter getDate_filter() {
      return this.date_filter;
   }

   public SearchFilter getSearch_filter() {
      return this.search_filter;
   }

   public void setDropdown_filter(final DropDownFilter dropdown_filter) {
      this.dropdown_filter = dropdown_filter;
   }

   public void setDate_filter(final DateFilter date_filter) {
      this.date_filter = date_filter;
   }

   public void setSearch_filter(final SearchFilter search_filter) {
      this.search_filter = search_filter;
   }

   public ThirdPartyVendorsStatisticsRequest() {
   }
}
