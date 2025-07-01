package com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.model;

import com.gafapay.elasticsearch.utils.Utils;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(
   name = "user_wallet"
)
@Convert(
   attributeName = "json",
   converter = JsonStringType.class
)
public class UserWallet {
   @Id
   @Column(
      name = "_id"
   )
   public String id;
   @Column(
      name = "wallet_id"
   )
   public String walletId;
   @Column(
      name = "currency_id"
   )
   public String currencyId;
   @Column(
      name = "user_id"
   )
   public String userId;
   @Column(
      name = "wallet_balance"
   )
   public Double walletBalance;
   @Column(
      name = "is_default"
   )
   public Boolean isDefault = false;
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
   public Long updatedDate = Instant.now().getEpochSecond();
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
      return Instant.now().getEpochSecond();
   }

   protected UserWallet(final UserWalletBuilder<?, ?> b) {
      if (b.id$set) {
         this.id = b.id$value;
      } else {
         this.id = $default$id();
      }

      this.walletId = b.walletId;
      this.currencyId = b.currencyId;
      this.userId = b.userId;
      this.walletBalance = b.walletBalance;
      this.isDefault = b.isDefault;
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

   public static UserWalletBuilder<?, ?> builder() {
      return new UserWalletBuilderImpl();
   }

   public String getId() {
      return this.id;
   }

   public String getWalletId() {
      return this.walletId;
   }

   public String getCurrencyId() {
      return this.currencyId;
   }

   public String getUserId() {
      return this.userId;
   }

   public Double getWalletBalance() {
      return this.walletBalance;
   }

   public Boolean getIsDefault() {
      return this.isDefault;
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

   public void setWalletId(final String walletId) {
      this.walletId = walletId;
   }

   public void setCurrencyId(final String currencyId) {
      this.currencyId = currencyId;
   }

   public void setUserId(final String userId) {
      this.userId = userId;
   }

   public void setWalletBalance(final Double walletBalance) {
      this.walletBalance = walletBalance;
   }

   public void setIsDefault(final Boolean isDefault) {
      this.isDefault = isDefault;
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

   public UserWallet() {
      this.id = $default$id();
      this.isActive = $default$isActive();
      this.createdDate = $default$createdDate();
   }

   public abstract static class UserWalletBuilder<C extends UserWallet, B extends UserWalletBuilder<C, B>> {
      private boolean id$set;
      private String id$value;
      private String walletId;
      private String currencyId;
      private String userId;
      private Double walletBalance;
      private Boolean isDefault;
      private boolean isActive$set;
      private Boolean isActive$value;
      private boolean createdDate$set;
      private Long createdDate$value;
      private String createdBy;
      private Long updatedDate;
      private String updatedBy;
      private String companyId;

      public UserWalletBuilder() {
      }

      protected abstract B self();

      public abstract C build();

      public B id(final String id) {
         this.id$value = id;
         this.id$set = true;
         return (B)this.self();
      }

      public B walletId(final String walletId) {
         this.walletId = walletId;
         return (B)this.self();
      }

      public B currencyId(final String currencyId) {
         this.currencyId = currencyId;
         return (B)this.self();
      }

      public B userId(final String userId) {
         this.userId = userId;
         return (B)this.self();
      }

      public B walletBalance(final Double walletBalance) {
         this.walletBalance = walletBalance;
         return (B)this.self();
      }

      public B isDefault(final Boolean isDefault) {
         this.isDefault = isDefault;
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
         return "UserWallet.UserWalletBuilder(id$value=" + this.id$value + ", walletId=" + this.walletId + ", currencyId=" + this.currencyId + ", userId=" + this.userId + ", walletBalance=" + this.walletBalance + ", isDefault=" + this.isDefault + ", isActive$value=" + this.isActive$value + ", createdDate$value=" + this.createdDate$value + ", createdBy=" + this.createdBy + ", updatedDate=" + this.updatedDate + ", updatedBy=" + this.updatedBy + ", companyId=" + this.companyId + ")";
      }
   }

   private static final class UserWalletBuilderImpl extends UserWalletBuilder<UserWallet, UserWalletBuilderImpl> {
      private UserWalletBuilderImpl() {
      }

      protected UserWalletBuilderImpl self() {
         return this;
      }

      public UserWallet build() {
         return new UserWallet(this);
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected UserWalletBuilder self() {
         return this.self();
      }
   }
}
