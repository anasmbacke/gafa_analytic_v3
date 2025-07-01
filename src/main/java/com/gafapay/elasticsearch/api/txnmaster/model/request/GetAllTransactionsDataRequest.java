package com.gafapay.elasticsearch.api.txnmaster.model.request;

import com.gafapay.elasticsearch.api.commonrequest.CommonAPIDataRequest;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class GetAllTransactionsDataRequest extends CommonAPIDataRequest {
   private Long start_date;
   private Long end_date;
   private Integer skip;
   private Integer limit;
   private String wallet_id;
   private Integer txn_status;
   private String txn_code;
   private String user_id;
   private String user_type;
   private String currency_id;
   private Integer payment_mode;
   private String search_keyword;
   private String sorting;
   private List<String> txn_ids;
   private List<String> txn_codes_ignore;
   private String merchant_category_id;
   private String credit_account_type_id;
   private String debit_account_type_id;
   private String vendor_id;
   private Integer txn_type;
   private String txn_codes;
   private String credit_type_id;
   private String debit_type_id;
   private String payment_type_id;
   private String store_id;
   private String to_user_id;

   public Boolean checkBadRequest() {
      return StringUtils.isEmpty(this.getCompany_id()) ? true : false;
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

   public String getWallet_id() {
      return this.wallet_id;
   }

   public Integer getTxn_status() {
      return this.txn_status;
   }

   public String getTxn_code() {
      return this.txn_code;
   }

   public String getUser_id() {
      return this.user_id;
   }

   public String getUser_type() {
      return this.user_type;
   }

   public String getCurrency_id() {
      return this.currency_id;
   }

   public Integer getPayment_mode() {
      return this.payment_mode;
   }

   public String getSearch_keyword() {
      return this.search_keyword;
   }

   public String getSorting() {
      return this.sorting;
   }

   public List<String> getTxn_ids() {
      return this.txn_ids;
   }

   public List<String> getTxn_codes_ignore() {
      return this.txn_codes_ignore;
   }

   public String getMerchant_category_id() {
      return this.merchant_category_id;
   }

   public String getCredit_account_type_id() {
      return this.credit_account_type_id;
   }

   public String getDebit_account_type_id() {
      return this.debit_account_type_id;
   }

   public String getVendor_id() {
      return this.vendor_id;
   }

   public Integer getTxn_type() {
      return this.txn_type;
   }

   public String getTxn_codes() {
      return this.txn_codes;
   }

   public String getCredit_type_id() {
      return this.credit_type_id;
   }

   public String getDebit_type_id() {
      return this.debit_type_id;
   }

   public String getPayment_type_id() {
      return this.payment_type_id;
   }

   public String getStore_id() {
      return this.store_id;
   }

   public String getTo_user_id() {
      return this.to_user_id;
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

   public void setWallet_id(final String wallet_id) {
      this.wallet_id = wallet_id;
   }

   public void setTxn_status(final Integer txn_status) {
      this.txn_status = txn_status;
   }

   public void setTxn_code(final String txn_code) {
      this.txn_code = txn_code;
   }

   public void setUser_id(final String user_id) {
      this.user_id = user_id;
   }

   public void setUser_type(final String user_type) {
      this.user_type = user_type;
   }

   public void setCurrency_id(final String currency_id) {
      this.currency_id = currency_id;
   }

   public void setPayment_mode(final Integer payment_mode) {
      this.payment_mode = payment_mode;
   }

   public void setSearch_keyword(final String search_keyword) {
      this.search_keyword = search_keyword;
   }

   public void setSorting(final String sorting) {
      this.sorting = sorting;
   }

   public void setTxn_ids(final List<String> txn_ids) {
      this.txn_ids = txn_ids;
   }

   public void setTxn_codes_ignore(final List<String> txn_codes_ignore) {
      this.txn_codes_ignore = txn_codes_ignore;
   }

   public void setMerchant_category_id(final String merchant_category_id) {
      this.merchant_category_id = merchant_category_id;
   }

   public void setCredit_account_type_id(final String credit_account_type_id) {
      this.credit_account_type_id = credit_account_type_id;
   }

   public void setDebit_account_type_id(final String debit_account_type_id) {
      this.debit_account_type_id = debit_account_type_id;
   }

   public void setVendor_id(final String vendor_id) {
      this.vendor_id = vendor_id;
   }

   public void setTxn_type(final Integer txn_type) {
      this.txn_type = txn_type;
   }

   public void setTxn_codes(final String txn_codes) {
      this.txn_codes = txn_codes;
   }

   public void setCredit_type_id(final String credit_type_id) {
      this.credit_type_id = credit_type_id;
   }

   public void setDebit_type_id(final String debit_type_id) {
      this.debit_type_id = debit_type_id;
   }

   public void setPayment_type_id(final String payment_type_id) {
      this.payment_type_id = payment_type_id;
   }

   public void setStore_id(final String store_id) {
      this.store_id = store_id;
   }

   public void setTo_user_id(final String to_user_id) {
      this.to_user_id = to_user_id;
   }

   public GetAllTransactionsDataRequest() {
   }
}
