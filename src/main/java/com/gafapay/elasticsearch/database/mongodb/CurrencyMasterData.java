package com.gafapay.elasticsearch.database.mongodb;

import com.gafapay.elasticsearch.database.model.CommonFieldModel;
import com.gafapay.elasticsearch.utils.Utils;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(
   collection = "currency_master"
)
public class CurrencyMasterData extends CommonFieldModel {
   @Field("_id")
   public String id;
   @Field("country_id")
   public String countryId;
   @Field("currency_name")
   public String currencyName;
   @Field("currency_code")
   public String currencyCode;
   @Field("currency_symbol")
   public String currencySymbol;
   @Field("is_primary_currency")
   public Boolean isPrimaryCurrency;

   private static String $default$id() {
      return Utils.generateUUID();
   }

   protected CurrencyMasterData(final CurrencyMasterDataBuilder<?, ?> b) {
      super(b);
      if (b.id$set) {
         this.id = b.id$value;
      } else {
         this.id = $default$id();
      }

      this.countryId = b.countryId;
      this.currencyName = b.currencyName;
      this.currencyCode = b.currencyCode;
      this.currencySymbol = b.currencySymbol;
      this.isPrimaryCurrency = b.isPrimaryCurrency;
   }

   public static CurrencyMasterDataBuilder<?, ?> builder() {
      return new CurrencyMasterDataBuilderImpl();
   }

   public CurrencyMasterData() {
      this.id = $default$id();
   }

   public String getId() {
      return this.id;
   }

   public String getCountryId() {
      return this.countryId;
   }

   public String getCurrencyName() {
      return this.currencyName;
   }

   public String getCurrencyCode() {
      return this.currencyCode;
   }

   public String getCurrencySymbol() {
      return this.currencySymbol;
   }

   public Boolean getIsPrimaryCurrency() {
      return this.isPrimaryCurrency;
   }

   public void setId(final String id) {
      this.id = id;
   }

   public void setCountryId(final String countryId) {
      this.countryId = countryId;
   }

   public void setCurrencyName(final String currencyName) {
      this.currencyName = currencyName;
   }

   public void setCurrencyCode(final String currencyCode) {
      this.currencyCode = currencyCode;
   }

   public void setCurrencySymbol(final String currencySymbol) {
      this.currencySymbol = currencySymbol;
   }

   public void setIsPrimaryCurrency(final Boolean isPrimaryCurrency) {
      this.isPrimaryCurrency = isPrimaryCurrency;
   }

   public abstract static class CurrencyMasterDataBuilder<C extends CurrencyMasterData, B extends CurrencyMasterDataBuilder<C, B>> extends CommonFieldModel.CommonFieldModelBuilder<C, B> {
      private boolean id$set;
      private String id$value;
      private String countryId;
      private String currencyName;
      private String currencyCode;
      private String currencySymbol;
      private Boolean isPrimaryCurrency;

      public CurrencyMasterDataBuilder() {
      }

      protected abstract B self();

      public abstract C build();

      public B id(final String id) {
         this.id$value = id;
         this.id$set = true;
         return (B)this.self();
      }

      public B countryId(final String countryId) {
         this.countryId = countryId;
         return (B)this.self();
      }

      public B currencyName(final String currencyName) {
         this.currencyName = currencyName;
         return (B)this.self();
      }

      public B currencyCode(final String currencyCode) {
         this.currencyCode = currencyCode;
         return (B)this.self();
      }

      public B currencySymbol(final String currencySymbol) {
         this.currencySymbol = currencySymbol;
         return (B)this.self();
      }

      public B isPrimaryCurrency(final Boolean isPrimaryCurrency) {
         this.isPrimaryCurrency = isPrimaryCurrency;
         return (B)this.self();
      }

      public String toString() {
         String var10000 = super.toString();
         return "CurrencyMasterData.CurrencyMasterDataBuilder(super=" + var10000 + ", id$value=" + this.id$value + ", countryId=" + this.countryId + ", currencyName=" + this.currencyName + ", currencyCode=" + this.currencyCode + ", currencySymbol=" + this.currencySymbol + ", isPrimaryCurrency=" + this.isPrimaryCurrency + ")";
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

   private static final class CurrencyMasterDataBuilderImpl extends CurrencyMasterDataBuilder<CurrencyMasterData, CurrencyMasterDataBuilderImpl> {
      private CurrencyMasterDataBuilderImpl() {
      }

      protected CurrencyMasterDataBuilderImpl self() {
         return this;
      }

      public CurrencyMasterData build() {
         return new CurrencyMasterData(this);
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected CurrencyMasterDataBuilder self() {
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
