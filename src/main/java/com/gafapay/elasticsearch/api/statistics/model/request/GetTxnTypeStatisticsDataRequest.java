package com.gafapay.elasticsearch.api.statistics.model.request;

import com.gafapay.elasticsearch.api.commonrequest.CommonAPIDataRequest;
import com.gafapay.elasticsearch.api.commonrequest.DateFilter;
import com.gafapay.elasticsearch.api.commonrequest.DropDownFilter;
import com.gafapay.elasticsearch.api.commonrequest.SearchFilter;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

public class GetTxnTypeStatisticsDataRequest extends CommonAPIDataRequest {
   private List<String> product_code;
   private DropDownFilter dropdown_filter;
   private SearchFilter search_filter;
   private DateFilter date_filter;

   public boolean checkBadRequest() {
      if (Objects.isNull(this.getDate_filter())) {
         return true;
      } else if (Objects.isNull(this.getDate_filter().getEnd_date())) {
         return true;
      } else if (Objects.isNull(this.getDate_filter().getStart_date())) {
         return true;
      } else if (StringUtils.isEmpty(this.getDate_filter().getDate_field_name())) {
         return true;
      } else if (Objects.isNull(this.getDate_filter().getFilter_type())) {
         return true;
      } else if (StringUtils.isEmpty(this.getCompany_id())) {
         return true;
      } else {
         return Objects.isNull(this.getProduct_code()) || this.getProduct_code().isEmpty();
      }
   }

   public List<String> getProduct_code() {
      return this.product_code;
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

   public void setProduct_code(final List<String> product_code) {
      this.product_code = product_code;
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

   public GetTxnTypeStatisticsDataRequest() {
   }
}
