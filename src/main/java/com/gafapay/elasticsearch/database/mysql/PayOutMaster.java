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
   name = "payout_master"
)
@Convert(
   attributeName = "json",
   converter = JsonStringType.class
)
public class PayOutMaster {
   @Id
   @Column(
      name = "_id"
   )
   public String id;
   @Column(
      name = "txn_id"
   )
   public String txnId;
   @Column(
      name = "automated"
   )
   public Boolean automated;
   @JdbcTypeCode(3001)
   @Column(
      name = "payment_response"
   )
   public Map<String, Object> paymentResponse;
   @Column(
      name = "currency_id"
   )
   public String currencyId;
   @Column(
      name = "amount"
   )
   public Double amount;
   @Column(
      name = "status"
   )
   public Integer status;
   @JdbcTypeCode(3001)
   @Column(
      name = "user_bank_info"
   )
   public Map<String, Object> userBankInfo;
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

   protected PayOutMaster(final PayOutMasterBuilder<?, ?> b) {
      if (b.id$set) {
         this.id = b.id$value;
      } else {
         this.id = $default$id();
      }

      this.txnId = b.txnId;
      this.automated = b.automated;
      this.paymentResponse = b.paymentResponse;
      this.currencyId = b.currencyId;
      this.amount = b.amount;
      this.status = b.status;
      this.userBankInfo = b.userBankInfo;
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

   public static PayOutMasterBuilder<?, ?> builder() {
      return new PayOutMasterBuilderImpl();
   }

   public PayOutMaster(final String id, final String txnId, final Boolean automated, final Map<String, Object> paymentResponse, final String currencyId, final Double amount, final Integer status, final Map<String, Object> userBankInfo, final Boolean isActive, final Long createdDate, final String createdBy, final Long updatedDate, final String updatedBy, final String companyId) {
      this.id = id;
      this.txnId = txnId;
      this.automated = automated;
      this.paymentResponse = paymentResponse;
      this.currencyId = currencyId;
      this.amount = amount;
      this.status = status;
      this.userBankInfo = userBankInfo;
      this.isActive = isActive;
      this.createdDate = createdDate;
      this.createdBy = createdBy;
      this.updatedDate = updatedDate;
      this.updatedBy = updatedBy;
      this.companyId = companyId;
   }

   public PayOutMaster() {
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

   public Boolean getAutomated() {
      return this.automated;
   }

   public Map<String, Object> getPaymentResponse() {
      return this.paymentResponse;
   }

   public String getCurrencyId() {
      return this.currencyId;
   }

   public Double getAmount() {
      return this.amount;
   }

   public Integer getStatus() {
      return this.status;
   }

   public Map<String, Object> getUserBankInfo() {
      return this.userBankInfo;
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

   public void setAutomated(final Boolean automated) {
      this.automated = automated;
   }

   public void setPaymentResponse(final Map<String, Object> paymentResponse) {
      this.paymentResponse = paymentResponse;
   }

   public void setCurrencyId(final String currencyId) {
      this.currencyId = currencyId;
   }

   public void setAmount(final Double amount) {
      this.amount = amount;
   }

   public void setStatus(final Integer status) {
      this.status = status;
   }

   public void setUserBankInfo(final Map<String, Object> userBankInfo) {
      this.userBankInfo = userBankInfo;
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

   public abstract static class PayOutMasterBuilder<C extends PayOutMaster, B extends PayOutMasterBuilder<C, B>> {
      private boolean id$set;
      private String id$value;
      private String txnId;
      private Boolean automated;
      private Map<String, Object> paymentResponse;
      private String currencyId;
      private Double amount;
      private Integer status;
      private Map<String, Object> userBankInfo;
      private boolean isActive$set;
      private Boolean isActive$value;
      private boolean createdDate$set;
      private Long createdDate$value;
      private String createdBy;
      private Long updatedDate;
      private String updatedBy;
      private String companyId;

      public PayOutMasterBuilder() {
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

      public B automated(final Boolean automated) {
         this.automated = automated;
         return (B)this.self();
      }

      public B paymentResponse(final Map<String, Object> paymentResponse) {
         this.paymentResponse = paymentResponse;
         return (B)this.self();
      }

      public B currencyId(final String currencyId) {
         this.currencyId = currencyId;
         return (B)this.self();
      }

      public B amount(final Double amount) {
         this.amount = amount;
         return (B)this.self();
      }

      public B status(final Integer status) {
         this.status = status;
         return (B)this.self();
      }

      public B userBankInfo(final Map<String, Object> userBankInfo) {
         this.userBankInfo = userBankInfo;
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
         return "PayOutMaster.PayOutMasterBuilder(id$value=" + this.id$value + ", txnId=" + this.txnId + ", automated=" + this.automated + ", paymentResponse=" + this.paymentResponse + ", currencyId=" + this.currencyId + ", amount=" + this.amount + ", status=" + this.status + ", userBankInfo=" + this.userBankInfo + ", isActive$value=" + this.isActive$value + ", createdDate$value=" + this.createdDate$value + ", createdBy=" + this.createdBy + ", updatedDate=" + this.updatedDate + ", updatedBy=" + this.updatedBy + ", companyId=" + this.companyId + ")";
      }
   }

   private static final class PayOutMasterBuilderImpl extends PayOutMasterBuilder<PayOutMaster, PayOutMasterBuilderImpl> {
      private PayOutMasterBuilderImpl() {
      }

      protected PayOutMasterBuilderImpl self() {
         return this;
      }

      public PayOutMaster build() {
         return new PayOutMaster(this);
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected PayOutMasterBuilder self() {
         return this.self();
      }
   }
}
