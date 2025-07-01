package com.gafapay.elasticsearch.api.nodelbanktransaction.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.gafapay.elasticsearch.database.model.nodalbanktransaction.NodalBankTransaction;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NodalBankTransactionData extends CommonAPIDataResponse {
   @JsonProperty("_id")
   private String id;
   @JsonProperty("nodal_bank_id")
   public String nodalBankId;
   @JsonProperty("description")
   public String description;
   @JsonProperty("amount")
   public Double amount;
   @JsonProperty("operation_type")
   public Integer operationType;
   @JsonProperty("purpose")
   public Integer purpose;
   @JsonProperty("note")
   public String note;
   @JsonProperty("txn_date")
   public Long txnDate;
   @JsonProperty("txn_number")
   public String txnNumber;
   @JsonProperty("txn_status")
   public Integer txnStatus;
   @JsonProperty("reference_txn_id")
   public String referenceTxnId;
   @JsonProperty("closing_balance")
   public Double closingBalance;
   @JsonProperty("is_active")
   private Boolean isActive;
   @JsonProperty("created_by")
   private String createdBy;
   @JsonProperty("created_date")
   private Long createdDate;
   @JsonProperty("updated_by")
   private String updatedBy;
   @JsonProperty("updated_date")
   private Long updatedDate;

   public static NodalBankTransactionData setNodalBankTransactionDetails(NodalBankTransaction nodalBankTransaction) {
      NodalBankTransactionData nodalBankTransactionData = new NodalBankTransactionData();
      nodalBankTransactionData.setId(nodalBankTransaction.getId());
      nodalBankTransactionData.setNodalBankId(nodalBankTransaction.getNodalBankId());
      nodalBankTransactionData.setDescription(nodalBankTransaction.getDescription());
      nodalBankTransactionData.setAmount(nodalBankTransaction.getAmount());
      nodalBankTransactionData.setOperationType(nodalBankTransaction.getOperationType());
      nodalBankTransactionData.setPurpose(nodalBankTransaction.getPurpose());
      nodalBankTransactionData.setNote(nodalBankTransaction.getNote());
      nodalBankTransactionData.setTxnDate(nodalBankTransaction.getTxnDate());
      nodalBankTransactionData.setTxnStatus(nodalBankTransaction.getTxnStatus());
      nodalBankTransactionData.setTxnNumber(nodalBankTransaction.getTxnNumber());
      nodalBankTransactionData.setReferenceTxnId(nodalBankTransaction.getReferenceTxnId());
      nodalBankTransactionData.setClosingBalance(nodalBankTransaction.getClosingBalance());
      nodalBankTransactionData.setIsActive(nodalBankTransaction.getIsActive());
      nodalBankTransactionData.setCreatedDate(nodalBankTransaction.getCreatedDate());
      nodalBankTransactionData.setUpdatedDate(nodalBankTransaction.getUpdatedDate());
      nodalBankTransactionData.setUpdatedBy(nodalBankTransaction.getUpdatedBy());
      nodalBankTransactionData.setCreatedBy(nodalBankTransaction.getCreatedBy());
      return nodalBankTransactionData;
   }

   public String getId() {
      return this.id;
   }

   public String getNodalBankId() {
      return this.nodalBankId;
   }

   public String getDescription() {
      return this.description;
   }

   public Double getAmount() {
      return this.amount;
   }

   public Integer getOperationType() {
      return this.operationType;
   }

   public Integer getPurpose() {
      return this.purpose;
   }

   public String getNote() {
      return this.note;
   }

   public Long getTxnDate() {
      return this.txnDate;
   }

   public String getTxnNumber() {
      return this.txnNumber;
   }

   public Integer getTxnStatus() {
      return this.txnStatus;
   }

   public String getReferenceTxnId() {
      return this.referenceTxnId;
   }

   public Double getClosingBalance() {
      return this.closingBalance;
   }

   public Boolean getIsActive() {
      return this.isActive;
   }

   public String getCreatedBy() {
      return this.createdBy;
   }

   public Long getCreatedDate() {
      return this.createdDate;
   }

   public String getUpdatedBy() {
      return this.updatedBy;
   }

   public Long getUpdatedDate() {
      return this.updatedDate;
   }

   @JsonProperty("_id")
   public void setId(final String id) {
      this.id = id;
   }

   @JsonProperty("nodal_bank_id")
   public void setNodalBankId(final String nodalBankId) {
      this.nodalBankId = nodalBankId;
   }

   @JsonProperty("description")
   public void setDescription(final String description) {
      this.description = description;
   }

   @JsonProperty("amount")
   public void setAmount(final Double amount) {
      this.amount = amount;
   }

   @JsonProperty("operation_type")
   public void setOperationType(final Integer operationType) {
      this.operationType = operationType;
   }

   @JsonProperty("purpose")
   public void setPurpose(final Integer purpose) {
      this.purpose = purpose;
   }

   @JsonProperty("note")
   public void setNote(final String note) {
      this.note = note;
   }

   @JsonProperty("txn_date")
   public void setTxnDate(final Long txnDate) {
      this.txnDate = txnDate;
   }

   @JsonProperty("txn_number")
   public void setTxnNumber(final String txnNumber) {
      this.txnNumber = txnNumber;
   }

   @JsonProperty("txn_status")
   public void setTxnStatus(final Integer txnStatus) {
      this.txnStatus = txnStatus;
   }

   @JsonProperty("reference_txn_id")
   public void setReferenceTxnId(final String referenceTxnId) {
      this.referenceTxnId = referenceTxnId;
   }

   @JsonProperty("closing_balance")
   public void setClosingBalance(final Double closingBalance) {
      this.closingBalance = closingBalance;
   }

   @JsonProperty("is_active")
   public void setIsActive(final Boolean isActive) {
      this.isActive = isActive;
   }

   @JsonProperty("created_by")
   public void setCreatedBy(final String createdBy) {
      this.createdBy = createdBy;
   }

   @JsonProperty("created_date")
   public void setCreatedDate(final Long createdDate) {
      this.createdDate = createdDate;
   }

   @JsonProperty("updated_by")
   public void setUpdatedBy(final String updatedBy) {
      this.updatedBy = updatedBy;
   }

   @JsonProperty("updated_date")
   public void setUpdatedDate(final Long updatedDate) {
      this.updatedDate = updatedDate;
   }

   public NodalBankTransactionData() {
   }
}
