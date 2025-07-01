package com.gafapay.elasticsearch.api.bankreconciliationreport.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class GetBankReconciliationReportResponse extends CommonAPIDataResponse {
   @JsonProperty("bank_reconciliation")
   private List<BankReconciliation> bankReconciliationList = new ArrayList();

   public List<BankReconciliation> getBankReconciliationList() {
      return this.bankReconciliationList;
   }

   @JsonProperty("bank_reconciliation")
   public void setBankReconciliationList(final List<BankReconciliation> bankReconciliationList) {
      this.bankReconciliationList = bankReconciliationList;
   }

   public GetBankReconciliationReportResponse() {
   }

   public static class BankReconciliation {
      @JsonProperty("timestamp")
      private Long timestamp;
      @JsonProperty("credit")
      private Double credit = (double)0.0F;
      @JsonProperty("debit")
      private Double debit = (double)0.0F;
      @JsonProperty("closing_balance")
      private Double closingBalance;

      public Long getTimestamp() {
         return this.timestamp;
      }

      public Double getCredit() {
         return this.credit;
      }

      public Double getDebit() {
         return this.debit;
      }

      public Double getClosingBalance() {
         return this.closingBalance;
      }

      @JsonProperty("timestamp")
      public void setTimestamp(final Long timestamp) {
         this.timestamp = timestamp;
      }

      @JsonProperty("credit")
      public void setCredit(final Double credit) {
         this.credit = credit;
      }

      @JsonProperty("debit")
      public void setDebit(final Double debit) {
         this.debit = debit;
      }

      @JsonProperty("closing_balance")
      public void setClosingBalance(final Double closingBalance) {
         this.closingBalance = closingBalance;
      }

      public BankReconciliation() {
      }
   }
}
