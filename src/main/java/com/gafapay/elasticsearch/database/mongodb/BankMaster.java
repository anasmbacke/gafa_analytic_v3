package com.gafapay.elasticsearch.database.mongodb;

import com.gafapay.elasticsearch.database.model.CommonFieldModel;
import com.gafapay.elasticsearch.utils.Utils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(
   collection = "bank_master"
)
@JsonIgnoreProperties(
   ignoreUnknown = true
)
public class BankMaster extends CommonFieldModel {
   @Field("_id")
   private String id;
   @Field("bank_name")
   private String bankName;
   @Field("bank_image")
   private String bankImage;
   @Field("bank_code")
   private String bankCode;

   private static String $default$id() {
      return Utils.generateUUID();
   }

   protected BankMaster(final BankMasterBuilder<?, ?> b) {
      super(b);
      if (b.id$set) {
         this.id = b.id$value;
      } else {
         this.id = $default$id();
      }

      this.bankName = b.bankName;
      this.bankImage = b.bankImage;
      this.bankCode = b.bankCode;
   }

   public static BankMasterBuilder<?, ?> builder() {
      return new BankMasterBuilderImpl();
   }

   public String getId() {
      return this.id;
   }

   public String getBankName() {
      return this.bankName;
   }

   public String getBankImage() {
      return this.bankImage;
   }

   public String getBankCode() {
      return this.bankCode;
   }

   public void setId(final String id) {
      this.id = id;
   }

   public void setBankName(final String bankName) {
      this.bankName = bankName;
   }

   public void setBankImage(final String bankImage) {
      this.bankImage = bankImage;
   }

   public void setBankCode(final String bankCode) {
      this.bankCode = bankCode;
   }

   public BankMaster() {
      this.id = $default$id();
   }

   public abstract static class BankMasterBuilder<C extends BankMaster, B extends BankMasterBuilder<C, B>> extends CommonFieldModel.CommonFieldModelBuilder<C, B> {
      private boolean id$set;
      private String id$value;
      private String bankName;
      private String bankImage;
      private String bankCode;

      public BankMasterBuilder() {
      }

      protected abstract B self();

      public abstract C build();

      public B id(final String id) {
         this.id$value = id;
         this.id$set = true;
         return (B)this.self();
      }

      public B bankName(final String bankName) {
         this.bankName = bankName;
         return (B)this.self();
      }

      public B bankImage(final String bankImage) {
         this.bankImage = bankImage;
         return (B)this.self();
      }

      public B bankCode(final String bankCode) {
         this.bankCode = bankCode;
         return (B)this.self();
      }

      public String toString() {
         String var10000 = super.toString();
         return "BankMaster.BankMasterBuilder(super=" + var10000 + ", id$value=" + this.id$value + ", bankName=" + this.bankName + ", bankImage=" + this.bankImage + ", bankCode=" + this.bankCode + ")";
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

   private static final class BankMasterBuilderImpl extends BankMasterBuilder<BankMaster, BankMasterBuilderImpl> {
      private BankMasterBuilderImpl() {
      }

      protected BankMasterBuilderImpl self() {
         return this;
      }

      public BankMaster build() {
         return new BankMaster(this);
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected BankMasterBuilder self() {
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
