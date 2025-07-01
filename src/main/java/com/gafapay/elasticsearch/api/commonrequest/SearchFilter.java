package com.gafapay.elasticsearch.api.commonrequest;

import java.util.List;

public class SearchFilter {
   private String search_keyword;
   private List<String> fields;

   public String getSearch_keyword() {
      return this.search_keyword;
   }

   public List<String> getFields() {
      return this.fields;
   }

   public void setSearch_keyword(final String search_keyword) {
      this.search_keyword = search_keyword;
   }

   public void setFields(final List<String> fields) {
      this.fields = fields;
   }

   public SearchFilter() {
   }
}
