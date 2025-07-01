package com.gafapay.elasticsearch.api.spendanalyizer.model.request;

import com.gafapay.elasticsearch.api.commonrequest.CommonAPIDataRequest;
import com.gafapay.elasticsearch.api.commonrequest.DateFilter;
import com.gafapay.elasticsearch.api.commonrequest.DropDownFilter;
import com.gafapay.elasticsearch.api.commonrequest.SearchFilter;
import org.apache.commons.lang3.StringUtils;

public class TransferByUtilityStatisticsRequest extends CommonAPIDataRequest {
   private String debit_account_type_id;
   private DropDownFilter dropdown_filter;
   private SearchFilter search_filter;
   private DateFilter date_filter;

   public boolean checkBadRequest() {
      return StringUtils.isEmpty(this.getCompany_id());
   }

   public String getDebit_account_type_id() {
      return this.debit_account_type_id;
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

   public void setDebit_account_type_id(final String debit_account_type_id) {
      this.debit_account_type_id = debit_account_type_id;
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

   public TransferByUtilityStatisticsRequest() {
   }
}
