package com.gafapay.elasticsearch.api.reportexport.model.request;

import com.gafapay.elasticsearch.api.commonrequest.CommonAPIDataRequest;
import org.apache.commons.lang3.StringUtils;

public class GetReportExportFilesRequest extends CommonAPIDataRequest {
   private String id;

   public boolean checkBadRequest() {
      if (StringUtils.isEmpty(this.getId())) {
         return true;
      } else {
         return StringUtils.isEmpty(this.getCompany_id());
      }
   }

   public String getId() {
      return this.id;
   }

   public void setId(final String id) {
      this.id = id;
   }

   public GetReportExportFilesRequest() {
   }
}
