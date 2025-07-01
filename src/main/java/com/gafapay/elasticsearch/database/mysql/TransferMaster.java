package com.gafapay.elasticsearch.database.mysql;

import com.gafapay.elasticsearch.utils.Utils;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Map;
import org.hibernate.annotations.JdbcTypeCode;

@Entity
@Table(
   name = "transfer_master"
)
@Convert(
   attributeName = "json",
   converter = JsonStringType.class
)
public class TransferMaster {
   @Id
   @Column(
      name = "_id"
   )
   public String id;
   @Column(
      name = "txn_id"
   )
   public String txnId;
   @JdbcTypeCode(3001)
   @Column(
      name = "payment_response"
   )
   public Map<String, Object> paymentResponse;
   @JdbcTypeCode(3001)
   @Column(
      name = "card_info"
   )
   public Map<String, Object> cardInfo;
   @Column(
      name = "source_currency_id"
   )
   public String sourceCurrencyId;
   @Column(
      name = "destination_currency_id"
   )
   public String destinationCurrencyId;
   @Column(
      name = "exchange_rate"
   )
   public Double exchangeRate;
   @Column(
      name = "destination_amount"
   )
   public Double destinationAmount;
   @Column(
      name = "source_amount"
   )
   public Double sourceAmount;
   @Column(
      name = "status"
   )
   public Integer status;
   @Column(
      name = "is_active"
   )
   public Boolean isActive;
   @Column(
      name = "created_date"
   )
   public Long createdDate;
   @Column(
      name = "created_by"
   )
   public String createdBy = null;
   @Column(
      name = "updated_date"
   )
   public Long updatedDate = System.currentTimeMillis() / 1000L;
   @Column(
      name = "updated_by"
   )
   public String updatedBy = null;
   @Column(
      name = "company_id"
   )
   public String companyId = null;

   private static String $default$id() {
      return Utils.generateUUID();
   }

   private static Boolean $default$isActive() {
      return true;
   }

   private static Long $default$createdDate() {
      return System.currentTimeMillis() / 1000L;
   }

   protected TransferMaster(final TransferMasterBuilder<?, ?> b) {
      if (b.id$set) {
         this.id = b.id$value;
      } else {
         this.id = $default$id();
      }

      this.txnId = b.txnId;
      this.paymentResponse = b.paymentResponse;
      this.cardInfo = b.cardInfo;
      this.sourceCurrencyId = b.sourceCurrencyId;
      this.destinationCurrencyId = b.destinationCurrencyId;
      this.exchangeRate = b.exchangeRate;
      this.destinationAmount = b.destinationAmount;
      this.sourceAmount = b.sourceAmount;
      this.status = b.status;
      if (b.isActive$set) {
         this.isActive = b.isActive$value;
      } else {
         this.isActive = $default$isActive();
      }

      if (b.createdDate$set) {
         this.createdDate = b.createdDate$value;
      } else {
         this.createdDate = $default$createdDate();
      }

      this.createdBy = b.createdBy;
      this.updatedDate = b.updatedDate;
      this.updatedBy = b.updatedBy;
      this.companyId = b.companyId;
   }

   public static TransferMasterBuilder<?, ?> builder() {
      return new TransferMasterBuilderImpl();
   }

   public TransferMaster(final String id, final String txnId, final Map<String, Object> paymentResponse, final Map<String, Object> cardInfo, final String sourceCurrencyId, final String destinationCurrencyId, final Double exchangeRate, final Double destinationAmount, final Double sourceAmount, final Integer status, final Boolean isActive, final Long createdDate, final String createdBy, final Long updatedDate, final String updatedBy, final String companyId) {
      this.id = id;
      this.txnId = txnId;
      this.paymentResponse = paymentResponse;
      this.cardInfo = cardInfo;
      this.sourceCurrencyId = sourceCurrencyId;
      this.destinationCurrencyId = destinationCurrencyId;
      this.exchangeRate = exchangeRate;
      this.destinationAmount = destinationAmount;
      this.sourceAmount = sourceAmount;
      this.status = status;
      this.isActive = isActive;
      this.createdDate = createdDate;
      this.createdBy = createdBy;
      this.updatedDate = updatedDate;
      this.updatedBy = updatedBy;
      this.companyId = companyId;
   }

   public TransferMaster() {
      this.id = $default$id();
      this.isActive = $default$isActive();
      this.createdDate = $default$createdDate();
   }

   public String getId() {
      return this.id;
   }

   public String getTxnId() {
      return this.txnId;
   }

   public Map<String, Object> getPaymentResponse() {
      return this.paymentResponse;
   }

   public Map<String, Object> getCardInfo() {
      return this.cardInfo;
   }

   public String getSourceCurrencyId() {
      return this.sourceCurrencyId;
   }

   public String getDestinationCurrencyId() {
      return this.destinationCurrencyId;
   }

   public Double getExchangeRate() {
      return this.exchangeRate;
   }

   public Double getDestinationAmount() {
      return this.destinationAmount;
   }

   public Double getSourceAmount() {
      return this.sourceAmount;
   }

   public Integer getStatus() {
      return this.status;
   }

   public Boolean getIsActive() {
      return this.isActive;
   }

   public Long getCreatedDate() {
      return this.createdDate;
   }

   public String getCreatedBy() {
      return this.createdBy;
   }

   public Long getUpdatedDate() {
      return this.updatedDate;
   }

   public String getUpdatedBy() {
      return this.updatedBy;
   }

   public String getCompanyId() {
      return this.companyId;
   }

   public void setId(final String id) {
      this.id = id;
   }

   public void setTxnId(final String txnId) {
      this.txnId = txnId;
   }

   public void setPaymentResponse(final Map<String, Object> paymentResponse) {
      this.paymentResponse = paymentResponse;
   }

   public void setCardInfo(final Map<String, Object> cardInfo) {
      this.cardInfo = cardInfo;
   }

   public void setSourceCurrencyId(final String sourceCurrencyId) {
      this.sourceCurrencyId = sourceCurrencyId;
   }

   public void setDestinationCurrencyId(final String destinationCurrencyId) {
      this.destinationCurrencyId = destinationCurrencyId;
   }

   public void setExchangeRate(final Double exchangeRate) {
      this.exchangeRate = exchangeRate;
   }

   public void setDestinationAmount(final Double destinationAmount) {
      this.destinationAmount = destinationAmount;
   }

   public void setSourceAmount(final Double sourceAmount) {
      this.sourceAmount = sourceAmount;
   }

   public void setStatus(final Integer status) {
      this.status = status;
   }

   public void setIsActive(final Boolean isActive) {
      this.isActive = isActive;
   }

   public void setCreatedDate(final Long createdDate) {
      this.createdDate = createdDate;
   }

   public void setCreatedBy(final String createdBy) {
      this.createdBy = createdBy;
   }

   public void setUpdatedDate(final Long updatedDate) {
      this.updatedDate = updatedDate;
   }

   public void setUpdatedBy(final String updatedBy) {
      this.updatedBy = updatedBy;
   }

   public void setCompanyId(final String companyId) {
      this.companyId = companyId;
   }

   public abstract static class TransferMasterBuilder<C extends TransferMaster, B extends TransferMasterBuilder<C, B>> {
      private boolean id$set;
      private String id$value;
      private String txnId;
      private Map<String, Object> paymentResponse;
      private Map<String, Object> cardInfo;
      private String sourceCurrencyId;
      private String destinationCurrencyId;
      private Double exchangeRate;
      private Double destinationAmount;
      private Double sourceAmount;
      private Integer status;
      private boolean isActive$set;
      private Boolean isActive$value;
      private boolean createdDate$set;
      private Long createdDate$value;
      private String createdBy;
      private Long updatedDate;
      private String updatedBy;
      private String companyId;

      public TransferMasterBuilder() {
      }

      protected abstract B self();

      public abstract C build();

      public B id(final String id) {
         this.id$value = id;
         this.id$set = true;
         return (B)this.self();
      }

      public B txnId(final String txnId) {
         this.txnId = txnId;
         return (B)this.self();
      }

      public B paymentResponse(final Map<String, Object> paymentResponse) {
         this.paymentResponse = paymentResponse;
         return (B)this.self();
      }

      public B cardInfo(final Map<String, Object> cardInfo) {
         this.cardInfo = cardInfo;
         return (B)this.self();
      }

      public B sourceCurrencyId(final String sourceCurrencyId) {
         this.sourceCurrencyId = sourceCurrencyId;
         return (B)this.self();
      }

      public B destinationCurrencyId(final String destinationCurrencyId) {
         this.destinationCurrencyId = destinationCurrencyId;
         return (B)this.self();
      }

      public B exchangeRate(final Double exchangeRate) {
         this.exchangeRate = exchangeRate;
         return (B)this.self();
      }

      public B destinationAmount(final Double destinationAmount) {
         this.destinationAmount = destinationAmount;
         return (B)this.self();
      }

      public B sourceAmount(final Double sourceAmount) {
         this.sourceAmount = sourceAmount;
         return (B)this.self();
      }

      public B status(final Integer status) {
         this.status = status;
         return (B)this.self();
      }

      public B isActive(final Boolean isActive) {
         this.isActive$value = isActive;
         this.isActive$set = true;
         return (B)this.self();
      }

      public B createdDate(final Long createdDate) {
         this.createdDate$value = createdDate;
         this.createdDate$set = true;
         return (B)this.self();
      }

      public B createdBy(final String createdBy) {
         this.createdBy = createdBy;
         return (B)this.self();
      }

      public B updatedDate(final Long updatedDate) {
         this.updatedDate = updatedDate;
         return (B)this.self();
      }

      public B updatedBy(final String updatedBy) {
         this.updatedBy = updatedBy;
         return (B)this.self();
      }

      public B companyId(final String companyId) {
         this.companyId = companyId;
         return (B)this.self();
      }

      public String toString() {
         return "TransferMaster.TransferMasterBuilder(id$value=" + this.id$value + ", txnId=" + this.txnId + ", paymentResponse=" + this.paymentResponse + ", cardInfo=" + this.cardInfo + ", sourceCurrencyId=" + this.sourceCurrencyId + ", destinationCurrencyId=" + this.destinationCurrencyId + ", exchangeRate=" + this.exchangeRate + ", destinationAmount=" + this.destinationAmount + ", sourceAmount=" + this.sourceAmount + ", status=" + this.status + ", isActive$value=" + this.isActive$value + ", createdDate$value=" + this.createdDate$value + ", createdBy=" + this.createdBy + ", updatedDate=" + this.updatedDate + ", updatedBy=" + this.updatedBy + ", companyId=" + this.companyId + ")";
      }
   }

   private static final class TransferMasterBuilderImpl extends TransferMasterBuilder<TransferMaster, TransferMasterBuilderImpl> {
      private TransferMasterBuilderImpl() {
      }

      protected TransferMasterBuilderImpl self() {
         return this;
      }

      public TransferMaster build() {
         return new TransferMaster(this);
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected TransferMasterBuilder self() {
         return this.self();
      }
   }
}
