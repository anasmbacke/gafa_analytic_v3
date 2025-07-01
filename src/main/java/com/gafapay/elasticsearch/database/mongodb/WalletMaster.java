package com.gafapay.elasticsearch.database.mongodb;

import com.gafapay.elasticsearch.database.model.CommonFieldModel;
import com.gafapay.elasticsearch.utils.Utils;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(
   collection = "wallet_master"
)
public class WalletMaster extends CommonFieldModel {
   @Field("_id")
   public String id = Utils.generateUUID();
   @Field("wallet_name")
   public String walletName;
   @Field("is_default")
   public Boolean isDefault;
   @Field("currency_id")
   public String currencyId;
   @Field("wallet_type")
   public Integer walletType;
   @Field("pool_amount")
   public Double poolAmount;
   @Field("sub_wallet_category_id")
   private String subWalletCategoryId;
   @Field("user_id")
   private String userId;
   @Field("status")
   private Integer status;

   protected WalletMaster(final WalletMasterBuilder<?, ?> b) {
      super(b);
      this.id = b.id;
      this.walletName = b.walletName;
      this.isDefault = b.isDefault;
      this.currencyId = b.currencyId;
      this.walletType = b.walletType;
      this.poolAmount = b.poolAmount;
      this.subWalletCategoryId = b.subWalletCategoryId;
      this.userId = b.userId;
      this.status = b.status;
   }

   public static WalletMasterBuilder<?, ?> builder() {
      return new WalletMasterBuilderImpl();
   }

   public String getId() {
      return this.id;
   }

   public String getWalletName() {
      return this.walletName;
   }

   public Boolean getIsDefault() {
      return this.isDefault;
   }

   public String getCurrencyId() {
      return this.currencyId;
   }

   public Integer getWalletType() {
      return this.walletType;
   }

   public Double getPoolAmount() {
      return this.poolAmount;
   }

   public String getSubWalletCategoryId() {
      return this.subWalletCategoryId;
   }

   public String getUserId() {
      return this.userId;
   }

   public Integer getStatus() {
      return this.status;
   }

   public void setId(final String id) {
      this.id = id;
   }

   public void setWalletName(final String walletName) {
      this.walletName = walletName;
   }

   public void setIsDefault(final Boolean isDefault) {
      this.isDefault = isDefault;
   }

   public void setCurrencyId(final String currencyId) {
      this.currencyId = currencyId;
   }

   public void setWalletType(final Integer walletType) {
      this.walletType = walletType;
   }

   public void setPoolAmount(final Double poolAmount) {
      this.poolAmount = poolAmount;
   }

   public void setSubWalletCategoryId(final String subWalletCategoryId) {
      this.subWalletCategoryId = subWalletCategoryId;
   }

   public void setUserId(final String userId) {
      this.userId = userId;
   }

   public void setStatus(final Integer status) {
      this.status = status;
   }

   public WalletMaster() {
   }

   public abstract static class WalletMasterBuilder<C extends WalletMaster, B extends WalletMasterBuilder<C, B>> extends CommonFieldModel.CommonFieldModelBuilder<C, B> {
      private String id;
      private String walletName;
      private Boolean isDefault;
      private String currencyId;
      private Integer walletType;
      private Double poolAmount;
      private String subWalletCategoryId;
      private String userId;
      private Integer status;

      public WalletMasterBuilder() {
      }

      protected abstract B self();

      public abstract C build();

      public B id(final String id) {
         this.id = id;
         return (B)this.self();
      }

      public B walletName(final String walletName) {
         this.walletName = walletName;
         return (B)this.self();
      }

      public B isDefault(final Boolean isDefault) {
         this.isDefault = isDefault;
         return (B)this.self();
      }

      public B currencyId(final String currencyId) {
         this.currencyId = currencyId;
         return (B)this.self();
      }

      public B walletType(final Integer walletType) {
         this.walletType = walletType;
         return (B)this.self();
      }

      public B poolAmount(final Double poolAmount) {
         this.poolAmount = poolAmount;
         return (B)this.self();
      }

      public B subWalletCategoryId(final String subWalletCategoryId) {
         this.subWalletCategoryId = subWalletCategoryId;
         return (B)this.self();
      }

      public B userId(final String userId) {
         this.userId = userId;
         return (B)this.self();
      }

      public B status(final Integer status) {
         this.status = status;
         return (B)this.self();
      }

      public String toString() {
         String var10000 = super.toString();
         return "WalletMaster.WalletMasterBuilder(super=" + var10000 + ", id=" + this.id + ", walletName=" + this.walletName + ", isDefault=" + this.isDefault + ", currencyId=" + this.currencyId + ", walletType=" + this.walletType + ", poolAmount=" + this.poolAmount + ", subWalletCategoryId=" + this.subWalletCategoryId + ", userId=" + this.userId + ", status=" + this.status + ")";
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

   private static final class WalletMasterBuilderImpl extends WalletMasterBuilder<WalletMaster, WalletMasterBuilderImpl> {
      private WalletMasterBuilderImpl() {
      }

      protected WalletMasterBuilderImpl self() {
         return this;
      }

      public WalletMaster build() {
         return new WalletMaster(this);
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected WalletMasterBuilder self() {
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
