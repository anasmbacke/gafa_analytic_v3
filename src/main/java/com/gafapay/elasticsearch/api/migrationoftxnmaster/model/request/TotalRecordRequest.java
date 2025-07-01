package com.gafapay.elasticsearch.api.migrationoftxnmaster.model.request;

import com.gafapay.elasticsearch.api.commonrequest.CommonAPIDataRequest;
import com.gafapay.elasticsearch.api.commonrequest.DateFilter;
import com.gafapay.elasticsearch.api.commonrequest.SearchFilter;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class TotalRecordRequest extends CommonAPIDataRequest {
   private String collection_name;
   private Map<String, Object> dropdown_filter;
   private SearchFilter search_filter;
   private DateFilter date_filter;
   private List<String> txn_code_ignore;
   private Map<String, Object> multiSelect_filter;
   private List<String> txn_code;

   public boolean checkBadRequest() {
      if (StringUtils.isEmpty(this.getCollection_name())) {
         return true;
      } else {
         return StringUtils.isEmpty(this.getCompany_id());
      }
   }

   public String getCollection_name() {
      return this.collection_name;
   }

   public Map<String, Object> getDropdown_filter() {
      return this.dropdown_filter;
   }

   public SearchFilter getSearch_filter() {
      return this.search_filter;
   }

   public DateFilter getDate_filter() {
      return this.date_filter;
   }

   public List<String> getTxn_code_ignore() {
      return this.txn_code_ignore;
   }

   public Map<String, Object> getMultiSelect_filter() {
      return this.multiSelect_filter;
   }

   public List<String> getTxn_code() {
      return this.txn_code;
   }

   public void setCollection_name(final String collection_name) {
      this.collection_name = collection_name;
   }

   public void setDropdown_filter(final Map<String, Object> dropdown_filter) {
      this.dropdown_filter = dropdown_filter;
   }

   public void setSearch_filter(final SearchFilter search_filter) {
      this.search_filter = search_filter;
   }

   public void setDate_filter(final DateFilter date_filter) {
      this.date_filter = date_filter;
   }

   public void setTxn_code_ignore(final List<String> txn_code_ignore) {
      this.txn_code_ignore = txn_code_ignore;
   }

   public void setMultiSelect_filter(final Map<String, Object> multiSelect_filter) {
      this.multiSelect_filter = multiSelect_filter;
   }

   public void setTxn_code(final List<String> txn_code) {
      this.txn_code = txn_code;
   }

   public TotalRecordRequest() {
   }
}
