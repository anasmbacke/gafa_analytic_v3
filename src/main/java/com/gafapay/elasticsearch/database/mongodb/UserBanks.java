package com.gafapay.elasticsearch.database.mongodb;

import com.gafapay.elasticsearch.database.model.CommonFieldModel;
import com.gafapay.elasticsearch.utils.Utils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(
   collection = "user_banks"
)
@JsonIgnoreProperties(
   ignoreUnknown = true
)
public class UserBanks extends CommonFieldModel {
   @Field("_id")
   private String id;
   @Field("bank_id")
   private String bankId;
   @Field("account_name")
   private String accountName;
   @Field("account_number")
   private String accountNumber;
   @Field("bank_code")
   private String bankCode;
   @Field("user_id")
   private String userId;
   @Field("third_party_account_number")
   private String thirdPartyAccountNumber;
   @Field("is_default")
   private Boolean isDefault;

   private static String $default$id() {
      return Utils.generateUUID();
   }

   protected UserBanks(final UserBanksBuilder<?, ?> b) {
      super(b);
      if (b.id$set) {
         this.id = b.id$value;
      } else {
         this.id = $default$id();
      }

      this.bankId = b.bankId;
      this.accountName = b.accountName;
      this.accountNumber = b.accountNumber;
      this.bankCode = b.bankCode;
      this.userId = b.userId;
      this.thirdPartyAccountNumber = b.thirdPartyAccountNumber;
      this.isDefault = b.isDefault;
   }

   public static UserBanksBuilder<?, ?> builder() {
      return new UserBanksBuilderImpl();
   }

   public String getId() {
      return this.id;
   }

   public String getBankId() {
      return this.bankId;
   }

   public String getAccountName() {
      return this.accountName;
   }

   public String getAccountNumber() {
      return this.accountNumber;
   }

   public String getBankCode() {
      return this.bankCode;
   }

   public String getUserId() {
      return this.userId;
   }

   public String getThirdPartyAccountNumber() {
      return this.thirdPartyAccountNumber;
   }

   public Boolean getIsDefault() {
      return this.isDefault;
   }

   public void setId(final String id) {
      this.id = id;
   }

   public void setBankId(final String bankId) {
      this.bankId = bankId;
   }

   public void setAccountName(final String accountName) {
      this.accountName = accountName;
   }

   public void setAccountNumber(final String accountNumber) {
      this.accountNumber = accountNumber;
   }

   public void setBankCode(final String bankCode) {
      this.bankCode = bankCode;
   }

   public void setUserId(final String userId) {
      this.userId = userId;
   }

   public void setThirdPartyAccountNumber(final String thirdPartyAccountNumber) {
      this.thirdPartyAccountNumber = thirdPartyAccountNumber;
   }

   public void setIsDefault(final Boolean isDefault) {
      this.isDefault = isDefault;
   }

   public UserBanks() {
      this.id = $default$id();
   }

   public abstract static class UserBanksBuilder<C extends UserBanks, B extends UserBanksBuilder<C, B>> extends CommonFieldModel.CommonFieldModelBuilder<C, B> {
      private boolean id$set;
      private String id$value;
      private String bankId;
      private String accountName;
      private String accountNumber;
      private String bankCode;
      private String userId;
      private String thirdPartyAccountNumber;
      private Boolean isDefault;

      public UserBanksBuilder() {
      }

      protected abstract B self();

      public abstract C build();

      public B id(final String id) {
         this.id$value = id;
         this.id$set = true;
         return (B)this.self();
      }

      public B bankId(final String bankId) {
         this.bankId = bankId;
         return (B)this.self();
      }

      public B accountName(final String accountName) {
         this.accountName = accountName;
         return (B)this.self();
      }

      public B accountNumber(final String accountNumber) {
         this.accountNumber = accountNumber;
         return (B)this.self();
      }

      public B bankCode(final String bankCode) {
         this.bankCode = bankCode;
         return (B)this.self();
      }

      public B userId(final String userId) {
         this.userId = userId;
         return (B)this.self();
      }

      public B thirdPartyAccountNumber(final String thirdPartyAccountNumber) {
         this.thirdPartyAccountNumber = thirdPartyAccountNumber;
         return (B)this.self();
      }

      public B isDefault(final Boolean isDefault) {
         this.isDefault = isDefault;
         return (B)this.self();
      }

      public String toString() {
         String var10000 = super.toString();
         return "UserBanks.UserBanksBuilder(super=" + var10000 + ", id$value=" + this.id$value + ", bankId=" + this.bankId + ", accountName=" + this.accountName + ", accountNumber=" + this.accountNumber + ", bankCode=" + this.bankCode + ", userId=" + this.userId + ", thirdPartyAccountNumber=" + this.thirdPartyAccountNumber + ", isDefault=" + this.isDefault + ")";
      }

      // $FF: synthetic method
      // $FF: bridge method
      public CommonFieldModel build() {
         return this.build();
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected CommonFieldModel.CommonFieldModelBuilder self() {
         return this.self();
      }
   }

   private static final class UserBanksBuilderImpl extends UserBanksBuilder<UserBanks, UserBanksBuilderImpl> {
      private UserBanksBuilderImpl() {
      }

      protected UserBanksBuilderImpl self() {
         return this;
      }

      public UserBanks build() {
         return new UserBanks(this);
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected UserBanksBuilder self() {
         return this.self();
      }

      // $FF: synthetic method
      // $FF: bridge method
      public CommonFieldModel build() {
         return this.build();
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected CommonFieldModel.CommonFieldModelBuilder self() {
         return this.self();
      }
   }
}
