package com.gafapay.elasticsearch.api.reportexport.model.request;

import com.gafapay.elasticsearch.api.commonrequest.CommonAllAPIDataRequest;
import io.micrometer.common.util.StringUtils;

public class DeleteReportExportFilesRequest extends CommonAllAPIDataRequest {
   private String id;

   public boolean checkBadRequest() {
      if (StringUtils.isEmpty(this.getCompany_id())) {
         return true;
      } else {
         return StringUtils.isEmpty(this.getId());
      }
   }

   public String getId() {
      return this.id;
   }

   public void setId(final String id) {
      this.id = id;
   }

   public DeleteReportExportFilesRequest() {
   }
}
