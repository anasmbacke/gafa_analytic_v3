package com.gafapay.elasticsearch.database.model.nodalbanktransaction;

import com.gafapay.elasticsearch.database.model.ElasticSearchCommonFieldModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(
   indexName = "nodal_bank_transactions"
)
@JsonIgnoreProperties(
   ignoreUnknown = true
)
public class NodalBankTransaction extends ElasticSearchCommonFieldModel {
   @Id
   @Field("id")
   public String id;
   @Field("nodal_bank_id")
   public String nodalBankId;
   @Field("description")
   public String description;
   @Field("amount")
   public Double amount;
   @Field("operation_type")
   public Integer operationType;
   @Field("purpose")
   public Integer purpose;
   @Field("note")
   public String note;
   @Field("txn_date")
   public Long txnDate;
   @Field("txn_number")
   public String txnNumber;
   @Field("txn_status")
   public Integer txnStatus;
   @Field("reference_txn_id")
   public String referenceTxnId;
   @Field("closing_balance")
   public Double closingBalance;

   protected NodalBankTransaction(final NodalBankTransactionBuilder<?, ?> b) {
      super(b);
      this.id = b.id;
      this.nodalBankId = b.nodalBankId;
      this.description = b.description;
      this.amount = b.amount;
      this.operationType = b.operationType;
      this.purpose = b.purpose;
      this.note = b.note;
      this.txnDate = b.txnDate;
      this.txnNumber = b.txnNumber;
      this.txnStatus = b.txnStatus;
      this.referenceTxnId = b.referenceTxnId;
      this.closingBalance = b.closingBalance;
   }

   public static NodalBankTransactionBuilder<?, ?> builder() {
      return new NodalBankTransactionBuilderImpl();
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

   public void setId(final String id) {
      this.id = id;
   }

   public void setNodalBankId(final String nodalBankId) {
      this.nodalBankId = nodalBankId;
   }

   public void setDescription(final String description) {
      this.description = description;
   }

   public void setAmount(final Double amount) {
      this.amount = amount;
   }

   public void setOperationType(final Integer operationType) {
      this.operationType = operationType;
   }

   public void setPurpose(final Integer purpose) {
      this.purpose = purpose;
   }

   public void setNote(final String note) {
      this.note = note;
   }

   public void setTxnDate(final Long txnDate) {
      this.txnDate = txnDate;
   }

   public void setTxnNumber(final String txnNumber) {
      this.txnNumber = txnNumber;
   }

   public void setTxnStatus(final Integer txnStatus) {
      this.txnStatus = txnStatus;
   }

   public void setReferenceTxnId(final String referenceTxnId) {
      this.referenceTxnId = referenceTxnId;
   }

   public void setClosingBalance(final Double closingBalance) {
      this.closingBalance = closingBalance;
   }

   public NodalBankTransaction() {
   }

   public abstract static class NodalBankTransactionBuilder<C extends NodalBankTransaction, B extends NodalBankTransactionBuilder<C, B>> extends ElasticSearchCommonFieldModel.ElasticSearchCommonFieldModelBuilder<C, B> {
      private String id;
      private String nodalBankId;
      private String description;
      private Double amount;
      private Integer operationType;
      private Integer purpose;
      private String note;
      private Long txnDate;
      private String txnNumber;
      private Integer txnStatus;
      private String referenceTxnId;
      private Double closingBalance;

      public NodalBankTransactionBuilder() {
      }

      protected abstract B self();

      public abstract C build();

      public B id(final String id) {
         this.id = id;
         return (B)this.self();
      }

      public B nodalBankId(final String nodalBankId) {
         this.nodalBankId = nodalBankId;
         return (B)this.self();
      }

      public B description(final String description) {
         this.description = description;
         return (B)this.self();
      }

      public B amount(final Double amount) {
         this.amount = amount;
         return (B)this.self();
      }

      public B operationType(final Integer operationType) {
         this.operationType = operationType;
         return (B)this.self();
      }

      public B purpose(final Integer purpose) {
         this.purpose = purpose;
         return (B)this.self();
      }

      public B note(final String note) {
         this.note = note;
         return (B)this.self();
      }

      public B txnDate(final Long txnDate) {
         this.txnDate = txnDate;
         return (B)this.self();
      }

      public B txnNumber(final String txnNumber) {
         this.txnNumber = txnNumber;
         return (B)this.self();
      }

      public B txnStatus(final Integer txnStatus) {
         this.txnStatus = txnStatus;
         return (B)this.self();
      }

      public B referenceTxnId(final String referenceTxnId) {
         this.referenceTxnId = referenceTxnId;
         return (B)this.self();
      }

      public B closingBalance(final Double closingBalance) {
         this.closingBalance = closingBalance;
         return (B)this.self();
      }

      public String toString() {
         String var10000 = super.toString();
         return "NodalBankTransaction.NodalBankTransactionBuilder(super=" + var10000 + ", id=" + this.id + ", nodalBankId=" + this.nodalBankId + ", description=" + this.description + ", amount=" + this.amount + ", operationType=" + this.operationType + ", purpose=" + this.purpose + ", note=" + this.note + ", txnDate=" + this.txnDate + ", txnNumber=" + this.txnNumber + ", txnStatus=" + this.txnStatus + ", referenceTxnId=" + this.referenceTxnId + ", closingBalance=" + this.closingBalance + ")";
      }

      // $FF: synthetic method
      // $FF: bridge method
      public ElasticSearchCommonFieldModel build() {
         return this.build();
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected ElasticSearchCommonFieldModel.ElasticSearchCommonFieldModelBuilder self() {
         return this.self();
      }
   }

   private static final class NodalBankTransactionBuilderImpl extends NodalBankTransactionBuilder<NodalBankTransaction, NodalBankTransactionBuilderImpl> {
      private NodalBankTransactionBuilderImpl() {
      }

      protected NodalBankTransactionBuilderImpl self() {
         return this;
      }

      public NodalBankTransaction build() {
         return new NodalBankTransaction(this);
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected NodalBankTransactionBuilder self() {
         return this.self();
      }

      // $FF: synthetic method
      // $FF: bridge method
      public ElasticSearchCommonFieldModel build() {
         return this.build();
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected ElasticSearchCommonFieldModel.ElasticSearchCommonFieldModelBuilder self() {
         return this.self();
      }
   }
}
