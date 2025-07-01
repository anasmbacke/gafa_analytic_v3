package com.gafapay.elasticsearch.api.auditlogs.model.request;

import com.gafapay.elasticsearch.api.commonrequest.CommonAPIDataRequest;
import org.apache.commons.lang3.StringUtils;

public class GetAuditLogsDataRequest extends CommonAPIDataRequest {
   private String id;

   public Boolean checkBadRequest() {
      if (StringUtils.isEmpty(this.getId())) {
         return true;
      } else {
         return StringUtils.isEmpty(this.getCompany_id()) ? true : false;
      }
   }

   public String getId() {
      return this.id;
   }

   public void setId(final String id) {
      this.id = id;
   }

   public GetAuditLogsDataRequest() {
   }
}
