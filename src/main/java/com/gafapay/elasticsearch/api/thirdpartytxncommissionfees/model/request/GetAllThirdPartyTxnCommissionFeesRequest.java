package com.gafapay.elasticsearch.api.thirdpartytxncommissionfees.model.request;

import com.gafapay.elasticsearch.api.commonrequest.CommonAPIDataRequest;
import org.apache.commons.lang3.StringUtils;

public class GetAllThirdPartyTxnCommissionFeesRequest extends CommonAPIDataRequest {
   private Long start_date;
   private Long end_date;
   private Integer skip;
   private Integer limit;
   private String sorting;
   private String txn_id;
   private Integer type;
   private String contract_type;

   public boolean checkBadRequest() {
      return StringUtils.isEmpty(this.getCompany_id());
   }

   public Long getStart_date() {
      return this.start_date;
   }

   public Long getEnd_date() {
      return this.end_date;
   }

   public Integer getSkip() {
      return this.skip;
   }

   public Integer getLimit() {
      return this.limit;
   }

   public String getSorting() {
      return this.sorting;
   }

   public String getTxn_id() {
      return this.txn_id;
   }

   public Integer getType() {
      return this.type;
   }

   public String getContract_type() {
      return this.contract_type;
   }

   public void setStart_date(final Long start_date) {
      this.start_date = start_date;
   }

   public void setEnd_date(final Long end_date) {
      this.end_date = end_date;
   }

   public void setSkip(final Integer skip) {
      this.skip = skip;
   }

   public void setLimit(final Integer limit) {
      this.limit = limit;
   }

   public void setSorting(final String sorting) {
      this.sorting = sorting;
   }

   public void setTxn_id(final String txn_id) {
      this.txn_id = txn_id;
   }

   public void setType(final Integer type) {
      this.type = type;
   }

   public void setContract_type(final String contract_type) {
      this.contract_type = contract_type;
   }

   public GetAllThirdPartyTxnCommissionFeesRequest() {
   }
}
