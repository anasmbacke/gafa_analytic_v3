package com.gafapay.elasticsearch.database.model;

import com.gafapay.elasticsearch.utils.Utils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(
   indexName = "txn_user_wallet"
)
@JsonIgnoreProperties(
   ignoreUnknown = true
)
public class TxnUserWallet extends ElasticSearchCommonFieldModel {
   @Id
   @Field(
      name = "id"
   )
   public String id = Utils.generateUUID();
   @Field(
      name = "type"
   )
   public Integer type;
   @Field(
      name = "type_id"
   )
   public String typeId;
   @Field(
      name = "previous_wallet_balance"
   )
   public Double previousWalletBalance;
   @Field(
      name = "current_wallet_balance"
   )
   public Double currentWalletBalance;
   @Field(
      name = "txn_amount"
   )
   public Double txnAmount;
   @Field(
      name = "txn_id"
   )
   public String txnId;
   @Field(
      name = "wallet_id"
   )
   public String walletId;
   @Field(
      name = "currency_id"
   )
   public String currencyId;
   @Field(
      name = "wallet_type"
   )
   public Integer walletType;
   @Field(
      name = "is_master_entry"
   )
   public Boolean isMasterEntry;

   protected TxnUserWallet(final TxnUserWalletBuilder<?, ?> b) {
      super(b);
      this.id = b.id;
      this.type = b.type;
      this.typeId = b.typeId;
      this.previousWalletBalance = b.previousWalletBalance;
      this.currentWalletBalance = b.currentWalletBalance;
      this.txnAmount = b.txnAmount;
      this.txnId = b.txnId;
      this.walletId = b.walletId;
      this.currencyId = b.currencyId;
      this.walletType = b.walletType;
      this.isMasterEntry = b.isMasterEntry;
   }

   public static TxnUserWalletBuilder<?, ?> builder() {
      return new TxnUserWalletBuilderImpl();
   }

   public String getId() {
      return this.id;
   }

   public Integer getType() {
      return this.type;
   }

   public String getTypeId() {
      return this.typeId;
   }

   public Double getPreviousWalletBalance() {
      return this.previousWalletBalance;
   }

   public Double getCurrentWalletBalance() {
      return this.currentWalletBalance;
   }

   public Double getTxnAmount() {
      return this.txnAmount;
   }

   public String getTxnId() {
      return this.txnId;
   }

   public String getWalletId() {
      return this.walletId;
   }

   public String getCurrencyId() {
      return this.currencyId;
   }

   public Integer getWalletType() {
      return this.walletType;
   }

   public Boolean getIsMasterEntry() {
      return this.isMasterEntry;
   }

   public void setId(final String id) {
      this.id = id;
   }

   public void setType(final Integer type) {
      this.type = type;
   }

   public void setTypeId(final String typeId) {
      this.typeId = typeId;
   }

   public void setPreviousWalletBalance(final Double previousWalletBalance) {
      this.previousWalletBalance = previousWalletBalance;
   }

   public void setCurrentWalletBalance(final Double currentWalletBalance) {
      this.currentWalletBalance = currentWalletBalance;
   }

   public void setTxnAmount(final Double txnAmount) {
      this.txnAmount = txnAmount;
   }

   public void setTxnId(final String txnId) {
      this.txnId = txnId;
   }

   public void setWalletId(final String walletId) {
      this.walletId = walletId;
   }

   public void setCurrencyId(final String currencyId) {
      this.currencyId = currencyId;
   }

   public void setWalletType(final Integer walletType) {
      this.walletType = walletType;
   }

   public void setIsMasterEntry(final Boolean isMasterEntry) {
      this.isMasterEntry = isMasterEntry;
   }

   public TxnUserWallet() {
   }

   public abstract static class TxnUserWalletBuilder<C extends TxnUserWallet, B extends TxnUserWalletBuilder<C, B>> extends ElasticSearchCommonFieldModel.ElasticSearchCommonFieldModelBuilder<C, B> {
      private String id;
      private Integer type;
      private String typeId;
      private Double previousWalletBalance;
      private Double currentWalletBalance;
      private Double txnAmount;
      private String txnId;
      private String walletId;
      private String currencyId;
      private Integer walletType;
      private Boolean isMasterEntry;

      public TxnUserWalletBuilder() {
      }

      protected abstract B self();

      public abstract C build();

      public B id(final String id) {
         this.id = id;
         return (B)this.self();
      }

      public B type(final Integer type) {
         this.type = type;
         return (B)this.self();
      }

      public B typeId(final String typeId) {
         this.typeId = typeId;
         return (B)this.self();
      }

      public B previousWalletBalance(final Double previousWalletBalance) {
         this.previousWalletBalance = previousWalletBalance;
         return (B)this.self();
      }

      public B currentWalletBalance(final Double currentWalletBalance) {
         this.currentWalletBalance = currentWalletBalance;
         return (B)this.self();
      }

      public B txnAmount(final Double txnAmount) {
         this.txnAmount = txnAmount;
         return (B)this.self();
      }

      public B txnId(final String txnId) {
         this.txnId = txnId;
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

      public B walletType(final Integer walletType) {
         this.walletType = walletType;
         return (B)this.self();
      }

      public B isMasterEntry(final Boolean isMasterEntry) {
         this.isMasterEntry = isMasterEntry;
         return (B)this.self();
      }

      public String toString() {
         String var10000 = super.toString();
         return "TxnUserWallet.TxnUserWalletBuilder(super=" + var10000 + ", id=" + this.id + ", type=" + this.type + ", typeId=" + this.typeId + ", previousWalletBalance=" + this.previousWalletBalance + ", currentWalletBalance=" + this.currentWalletBalance + ", txnAmount=" + this.txnAmount + ", txnId=" + this.txnId + ", walletId=" + this.walletId + ", currencyId=" + this.currencyId + ", walletType=" + this.walletType + ", isMasterEntry=" + this.isMasterEntry + ")";
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

   private static final class TxnUserWalletBuilderImpl extends TxnUserWalletBuilder<TxnUserWallet, TxnUserWalletBuilderImpl> {
      private TxnUserWalletBuilderImpl() {
      }

      protected TxnUserWalletBuilderImpl self() {
         return this;
      }

      public TxnUserWallet build() {
         return new TxnUserWallet(this);
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected TxnUserWalletBuilder self() {
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
