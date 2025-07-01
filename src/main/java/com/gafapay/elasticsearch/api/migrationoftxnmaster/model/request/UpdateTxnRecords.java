package com.gafapay.elasticsearch.api.migrationoftxnmaster.model.request;

import com.gafapay.elasticsearch.api.commonrequest.CommonAPIDataRequest;
import org.apache.commons.lang3.StringUtils;

public class UpdateTxnRecords extends CommonAPIDataRequest {
   private Long start_date;
   private Long end_date;

   public boolean checkBadRequest() {
      return StringUtils.isEmpty(this.getCompany_id());
   }

   public Long getStart_date() {
      return this.start_date;
   }

   public Long getEnd_date() {
      return this.end_date;
   }

   public void setStart_date(final Long start_date) {
      this.start_date = start_date;
   }

   public void setEnd_date(final Long end_date) {
      this.end_date = end_date;
   }

   public UpdateTxnRecords() {
   }
}
