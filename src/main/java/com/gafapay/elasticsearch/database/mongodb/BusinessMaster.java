package com.gafapay.elasticsearch.database.mongodb;

import com.gafapay.elasticsearch.database.model.CommonFieldModel;
import com.gafapay.elasticsearch.utils.Utils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(
   collection = "business_master"
)
@JsonIgnoreProperties(
   ignoreUnknown = true
)
public class BusinessMaster extends CommonFieldModel {
   @Field("_id")
   private String id;
   @Field("business_name")
   private String businessName;
   @Field("business_type")
   private String businessType;
   @Field("business_category_id")
   private String businessCategoryId;
   @Field("industry_type")
   private Integer industryType;
   @Field("monthly_turnover")
   private Integer monthlyTurnover;
   @Field("trade_license_number")
   private String tradeLicenseNumber;
   @Field("user_id")
   private String userId;

   private static String $default$id() {
      return Utils.generateUUID();
   }

   protected BusinessMaster(final BusinessMasterBuilder<?, ?> b) {
      super(b);
      if (b.id$set) {
         this.id = b.id$value;
      } else {
         this.id = $default$id();
      }

      this.businessName = b.businessName;
      this.businessType = b.businessType;
      this.businessCategoryId = b.businessCategoryId;
      this.industryType = b.industryType;
      this.monthlyTurnover = b.monthlyTurnover;
      this.tradeLicenseNumber = b.tradeLicenseNumber;
      this.userId = b.userId;
   }

   public static BusinessMasterBuilder<?, ?> builder() {
      return new BusinessMasterBuilderImpl();
   }

   public String getId() {
      return this.id;
   }

   public String getBusinessName() {
      return this.businessName;
   }

   public String getBusinessType() {
      return this.businessType;
   }

   public String getBusinessCategoryId() {
      return this.businessCategoryId;
   }

   public Integer getIndustryType() {
      return this.industryType;
   }

   public Integer getMonthlyTurnover() {
      return this.monthlyTurnover;
   }

   public String getTradeLicenseNumber() {
      return this.tradeLicenseNumber;
   }

   public String getUserId() {
      return this.userId;
   }

   public void setId(final String id) {
      this.id = id;
   }

   public void setBusinessName(final String businessName) {
      this.businessName = businessName;
   }

   public void setBusinessType(final String businessType) {
      this.businessType = businessType;
   }

   public void setBusinessCategoryId(final String businessCategoryId) {
      this.businessCategoryId = businessCategoryId;
   }

   public void setIndustryType(final Integer industryType) {
      this.industryType = industryType;
   }

   public void setMonthlyTurnover(final Integer monthlyTurnover) {
      this.monthlyTurnover = monthlyTurnover;
   }

   public void setTradeLicenseNumber(final String tradeLicenseNumber) {
      this.tradeLicenseNumber = tradeLicenseNumber;
   }

   public void setUserId(final String userId) {
      this.userId = userId;
   }

   public BusinessMaster() {
      this.id = $default$id();
   }

   public abstract static class BusinessMasterBuilder<C extends BusinessMaster, B extends BusinessMasterBuilder<C, B>> extends CommonFieldModel.CommonFieldModelBuilder<C, B> {
      private boolean id$set;
      private String id$value;
      private String businessName;
      private String businessType;
      private String businessCategoryId;
      private Integer industryType;
      private Integer monthlyTurnover;
      private String tradeLicenseNumber;
      private String userId;

      public BusinessMasterBuilder() {
      }

      protected abstract B self();

      public abstract C build();

      public B id(final String id) {
         this.id$value = id;
         this.id$set = true;
         return (B)this.self();
      }

      public B businessName(final String businessName) {
         this.businessName = businessName;
         return (B)this.self();
      }

      public B businessType(final String businessType) {
         this.businessType = businessType;
         return (B)this.self();
      }

      public B businessCategoryId(final String businessCategoryId) {
         this.businessCategoryId = businessCategoryId;
         return (B)this.self();
      }

      public B industryType(final Integer industryType) {
         this.industryType = industryType;
         return (B)this.self();
      }

      public B monthlyTurnover(final Integer monthlyTurnover) {
         this.monthlyTurnover = monthlyTurnover;
         return (B)this.self();
      }

      public B tradeLicenseNumber(final String tradeLicenseNumber) {
         this.tradeLicenseNumber = tradeLicenseNumber;
         return (B)this.self();
      }

      public B userId(final String userId) {
         this.userId = userId;
         return (B)this.self();
      }

      public String toString() {
         String var10000 = super.toString();
         return "BusinessMaster.BusinessMasterBuilder(super=" + var10000 + ", id$value=" + this.id$value + ", businessName=" + this.businessName + ", businessType=" + this.businessType + ", businessCategoryId=" + this.businessCategoryId + ", industryType=" + this.industryType + ", monthlyTurnover=" + this.monthlyTurnover + ", tradeLicenseNumber=" + this.tradeLicenseNumber + ", userId=" + this.userId + ")";
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

   private static final class BusinessMasterBuilderImpl extends BusinessMasterBuilder<BusinessMaster, BusinessMasterBuilderImpl> {
      private BusinessMasterBuilderImpl() {
      }

      protected BusinessMasterBuilderImpl self() {
         return this;
      }

      public BusinessMaster build() {
         return new BusinessMaster(this);
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected BusinessMasterBuilder self() {
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
