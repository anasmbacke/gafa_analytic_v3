package com.gafapay.elasticsearch.api.revenuesummary.model.request;

import com.gafapay.elasticsearch.api.commonrequest.CommonAPIDataRequest;
import org.apache.commons.lang3.StringUtils;

public class GetRevenueSummaryDataRequest extends CommonAPIDataRequest {
   private String id;

   public Boolean checkBadRequest() {
      return StringUtils.isEmpty(this.getId()) ? true : false;
   }

   public String getId() {
      return this.id;
   }

   public void setId(final String id) {
      this.id = id;
   }

   public GetRevenueSummaryDataRequest() {
   }
}
