package com.gafapay.elasticsearch.api.commonrequest;

import java.util.List;

public class DropDownFilter {
   private String user_id;
   private Integer user_type;
   private String admin_user_id;
   private Integer agent_commission_group_by;
   private String tree_level_id;
   private Integer top_most_earner_agent_count;
   private Integer revenue_summary_type;
   private String currency_id;
   private String wallet_id;
   private String nodal_bank_id;
   private Integer credit_account_type;
   private Integer debit_account_type;
   private String credit_account_type_id;
   private String debit_account_type_id;
   private List<String> txn_code;
   private Integer account_type;
   private Integer type;
   private Boolean is_active;
   private String type_key_code;
   private String corporate_id;

   public String getUser_id() {
      return this.user_id;
   }

   public Integer getUser_type() {
      return this.user_type;
   }

   public String getAdmin_user_id() {
      return this.admin_user_id;
   }

   public Integer getAgent_commission_group_by() {
      return this.agent_commission_group_by;
   }

   public String getTree_level_id() {
      return this.tree_level_id;
   }

   public Integer getTop_most_earner_agent_count() {
      return this.top_most_earner_agent_count;
   }

   public Integer getRevenue_summary_type() {
      return this.revenue_summary_type;
   }

   public String getCurrency_id() {
      return this.currency_id;
   }

   public String getWallet_id() {
      return this.wallet_id;
   }

   public String getNodal_bank_id() {
      return this.nodal_bank_id;
   }

   public Integer getCredit_account_type() {
      return this.credit_account_type;
   }

   public Integer getDebit_account_type() {
      return this.debit_account_type;
   }

   public String getCredit_account_type_id() {
      return this.credit_account_type_id;
   }

   public String getDebit_account_type_id() {
      return this.debit_account_type_id;
   }

   public List<String> getTxn_code() {
      return this.txn_code;
   }

   public Integer getAccount_type() {
      return this.account_type;
   }

   public Integer getType() {
      return this.type;
   }

   public Boolean getIs_active() {
      return this.is_active;
   }

   public String getType_key_code() {
      return this.type_key_code;
   }

   public String getCorporate_id() {
      return this.corporate_id;
   }

   public void setUser_id(final String user_id) {
      this.user_id = user_id;
   }

   public void setUser_type(final Integer user_type) {
      this.user_type = user_type;
   }

   public void setAdmin_user_id(final String admin_user_id) {
      this.admin_user_id = admin_user_id;
   }

   public void setAgent_commission_group_by(final Integer agent_commission_group_by) {
      this.agent_commission_group_by = agent_commission_group_by;
   }

   public void setTree_level_id(final String tree_level_id) {
      this.tree_level_id = tree_level_id;
   }

   public void setTop_most_earner_agent_count(final Integer top_most_earner_agent_count) {
      this.top_most_earner_agent_count = top_most_earner_agent_count;
   }

   public void setRevenue_summary_type(final Integer revenue_summary_type) {
      this.revenue_summary_type = revenue_summary_type;
   }

   public void setCurrency_id(final String currency_id) {
      this.currency_id = currency_id;
   }

   public void setWallet_id(final String wallet_id) {
      this.wallet_id = wallet_id;
   }

   public void setNodal_bank_id(final String nodal_bank_id) {
      this.nodal_bank_id = nodal_bank_id;
   }

   public void setCredit_account_type(final Integer credit_account_type) {
      this.credit_account_type = credit_account_type;
   }

   public void setDebit_account_type(final Integer debit_account_type) {
      this.debit_account_type = debit_account_type;
   }

   public void setCredit_account_type_id(final String credit_account_type_id) {
      this.credit_account_type_id = credit_account_type_id;
   }

   public void setDebit_account_type_id(final String debit_account_type_id) {
      this.debit_account_type_id = debit_account_type_id;
   }

   public void setTxn_code(final List<String> txn_code) {
      this.txn_code = txn_code;
   }

   public void setAccount_type(final Integer account_type) {
      this.account_type = account_type;
   }

   public void setType(final Integer type) {
      this.type = type;
   }

   public void setIs_active(final Boolean is_active) {
      this.is_active = is_active;
   }

   public void setType_key_code(final String type_key_code) {
      this.type_key_code = type_key_code;
   }

   public void setCorporate_id(final String corporate_id) {
      this.corporate_id = corporate_id;
   }

   public DropDownFilter() {
   }
}
