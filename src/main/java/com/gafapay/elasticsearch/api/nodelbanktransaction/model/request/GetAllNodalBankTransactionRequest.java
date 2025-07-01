package com.gafapay.elasticsearch.api.nodelbanktransaction.model.request;

import com.gafapay.elasticsearch.api.commonrequest.CommonAPIDataRequest;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class GetAllNodalBankTransactionRequest extends CommonAPIDataRequest {
   private String nodal_bank_id;
   private Integer operation_type;
   private Integer purpose;
   private String txn_number;
   private Long txn_date;
   private Integer txn_status;
   private List<String> reference_txn_id;
   private Long start_date;
   private Long end_date;
   private Integer skip;
   private Integer limit;
   private String search_keyword;
   private String sorting;

   public Boolean checkBadRequest() {
      return StringUtils.isEmpty(this.getCompany_id()) ? true : false;
   }

   public String getNodal_bank_id() {
      return this.nodal_bank_id;
   }

   public Integer getOperation_type() {
      return this.operation_type;
   }

   public Integer getPurpose() {
      return this.purpose;
   }

   public String getTxn_number() {
      return this.txn_number;
   }

   public Long getTxn_date() {
      return this.txn_date;
   }

   public Integer getTxn_status() {
      return this.txn_status;
   }

   public List<String> getReference_txn_id() {
      return this.reference_txn_id;
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

   public String getSearch_keyword() {
      return this.search_keyword;
   }

   public String getSorting() {
      return this.sorting;
   }

   public void setNodal_bank_id(final String nodal_bank_id) {
      this.nodal_bank_id = nodal_bank_id;
   }

   public void setOperation_type(final Integer operation_type) {
      this.operation_type = operation_type;
   }

   public void setPurpose(final Integer purpose) {
      this.purpose = purpose;
   }

   public void setTxn_number(final String txn_number) {
      this.txn_number = txn_number;
   }

   public void setTxn_date(final Long txn_date) {
      this.txn_date = txn_date;
   }

   public void setTxn_status(final Integer txn_status) {
      this.txn_status = txn_status;
   }

   public void setReference_txn_id(final List<String> reference_txn_id) {
      this.reference_txn_id = reference_txn_id;
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

   public void setSearch_keyword(final String search_keyword) {
      this.search_keyword = search_keyword;
   }

   public void setSorting(final String sorting) {
      this.sorting = sorting;
   }

   public GetAllNodalBankTransactionRequest() {
   }
}
