package com.gafapay.elasticsearch.database.mongodb;

import com.gafapay.elasticsearch.constant.AppConstants;
import com.gafapay.elasticsearch.database.model.CommonFieldModel;
import com.gafapay.elasticsearch.utils.Utils;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(
   collection = "company_module"
)
public class CompanyModuleData extends CommonFieldModel {
   @Field("_id")
   public String id;
   @Field("group_type")
   public String groupType;
   @Field("module_key")
   public String moduleKey;
   @Field("category_id")
   public String categoryId;
   @Field("service_type_id")
   public String serviceTypeId;
   @Field("price")
   public Double price;
   @Field("price_type")
   public Integer priceType;
   @Field("start_date")
   public Long startDate;
   @Field("end_date")
   public Long endDate;
   @Field("module_owner")
   public String moduleOwner;
   @Field("module_id")
   public String moduleId;
   @Field("is_configurable")
   public Boolean isConfigurable;
   @Field("parent_module_id")
   public String pModuleId;
   @Field("parent_module_key")
   public String pModuleKey;
   @Field("description")
   public String description;

   private static String $default$id() {
      return Utils.generateUUID();
   }

   protected CompanyModuleData(final CompanyModuleDataBuilder<?, ?> b) {
      super(b);
      this.priceType = AppConstants.PRICE_TYPE_RECURRING;
      if (b.id$set) {
         this.id = b.id$value;
      } else {
         this.id = $default$id();
      }

      this.groupType = b.groupType;
      this.moduleKey = b.moduleKey;
      this.categoryId = b.categoryId;
      this.serviceTypeId = b.serviceTypeId;
      this.price = b.price;
      this.priceType = b.priceType;
      this.startDate = b.startDate;
      this.endDate = b.endDate;
      this.moduleOwner = b.moduleOwner;
      this.moduleId = b.moduleId;
      this.isConfigurable = b.isConfigurable;
      this.pModuleId = b.pModuleId;
      this.pModuleKey = b.pModuleKey;
      this.description = b.description;
   }

   public static CompanyModuleDataBuilder<?, ?> builder() {
      return new CompanyModuleDataBuilderImpl();
   }

   public CompanyModuleData() {
      this.priceType = AppConstants.PRICE_TYPE_RECURRING;
      this.id = $default$id();
   }

   public String getId() {
      return this.id;
   }

   public String getGroupType() {
      return this.groupType;
   }

   public String getModuleKey() {
      return this.moduleKey;
   }

   public String getCategoryId() {
      return this.categoryId;
   }

   public String getServiceTypeId() {
      return this.serviceTypeId;
   }

   public Double getPrice() {
      return this.price;
   }

   public Integer getPriceType() {
      return this.priceType;
   }

   public Long getStartDate() {
      return this.startDate;
   }

   public Long getEndDate() {
      return this.endDate;
   }

   public String getModuleOwner() {
      return this.moduleOwner;
   }

   public String getModuleId() {
      return this.moduleId;
   }

   public Boolean getIsConfigurable() {
      return this.isConfigurable;
   }

   public String getPModuleId() {
      return this.pModuleId;
   }

   public String getPModuleKey() {
      return this.pModuleKey;
   }

   public String getDescription() {
      return this.description;
   }

   public void setId(final String id) {
      this.id = id;
   }

   public void setGroupType(final String groupType) {
      this.groupType = groupType;
   }

   public void setModuleKey(final String moduleKey) {
      this.moduleKey = moduleKey;
   }

   public void setCategoryId(final String categoryId) {
      this.categoryId = categoryId;
   }

   public void setServiceTypeId(final String serviceTypeId) {
      this.serviceTypeId = serviceTypeId;
   }

   public void setPrice(final Double price) {
      this.price = price;
   }

   public void setPriceType(final Integer priceType) {
      this.priceType = priceType;
   }

   public void setStartDate(final Long startDate) {
      this.startDate = startDate;
   }

   public void setEndDate(final Long endDate) {
      this.endDate = endDate;
   }

   public void setModuleOwner(final String moduleOwner) {
      this.moduleOwner = moduleOwner;
   }

   public void setModuleId(final String moduleId) {
      this.moduleId = moduleId;
   }

   public void setIsConfigurable(final Boolean isConfigurable) {
      this.isConfigurable = isConfigurable;
   }

   public void setPModuleId(final String pModuleId) {
      this.pModuleId = pModuleId;
   }

   public void setPModuleKey(final String pModuleKey) {
      this.pModuleKey = pModuleKey;
   }

   public void setDescription(final String description) {
      this.description = description;
   }

   public abstract static class CompanyModuleDataBuilder<C extends CompanyModuleData, B extends CompanyModuleDataBuilder<C, B>> extends CommonFieldModel.CommonFieldModelBuilder<C, B> {
      private boolean id$set;
      private String id$value;
      private String groupType;
      private String moduleKey;
      private String categoryId;
      private String serviceTypeId;
      private Double price;
      private Integer priceType;
      private Long startDate;
      private Long endDate;
      private String moduleOwner;
      private String moduleId;
      private Boolean isConfigurable;
      private String pModuleId;
      private String pModuleKey;
      private String description;

      public CompanyModuleDataBuilder() {
      }

      protected abstract B self();

      public abstract C build();

      public B id(final String id) {
         this.id$value = id;
         this.id$set = true;
         return (B)this.self();
      }

      public B groupType(final String groupType) {
         this.groupType = groupType;
         return (B)this.self();
      }

      public B moduleKey(final String moduleKey) {
         this.moduleKey = moduleKey;
         return (B)this.self();
      }

      public B categoryId(final String categoryId) {
         this.categoryId = categoryId;
         return (B)this.self();
      }

      public B serviceTypeId(final String serviceTypeId) {
         this.serviceTypeId = serviceTypeId;
         return (B)this.self();
      }

      public B price(final Double price) {
         this.price = price;
         return (B)this.self();
      }

      public B priceType(final Integer priceType) {
         this.priceType = priceType;
         return (B)this.self();
      }

      public B startDate(final Long startDate) {
         this.startDate = startDate;
         return (B)this.self();
      }

      public B endDate(final Long endDate) {
         this.endDate = endDate;
         return (B)this.self();
      }

      public B moduleOwner(final String moduleOwner) {
         this.moduleOwner = moduleOwner;
         return (B)this.self();
      }

      public B moduleId(final String moduleId) {
         this.moduleId = moduleId;
         return (B)this.self();
      }

      public B isConfigurable(final Boolean isConfigurable) {
         this.isConfigurable = isConfigurable;
         return (B)this.self();
      }

      public B pModuleId(final String pModuleId) {
         this.pModuleId = pModuleId;
         return (B)this.self();
      }

      public B pModuleKey(final String pModuleKey) {
         this.pModuleKey = pModuleKey;
         return (B)this.self();
      }

      public B description(final String description) {
         this.description = description;
         return (B)this.self();
      }

      public String toString() {
         String var10000 = super.toString();
         return "CompanyModuleData.CompanyModuleDataBuilder(super=" + var10000 + ", id$value=" + this.id$value + ", groupType=" + this.groupType + ", moduleKey=" + this.moduleKey + ", categoryId=" + this.categoryId + ", serviceTypeId=" + this.serviceTypeId + ", price=" + this.price + ", priceType=" + this.priceType + ", startDate=" + this.startDate + ", endDate=" + this.endDate + ", moduleOwner=" + this.moduleOwner + ", moduleId=" + this.moduleId + ", isConfigurable=" + this.isConfigurable + ", pModuleId=" + this.pModuleId + ", pModuleKey=" + this.pModuleKey + ", description=" + this.description + ")";
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

   private static final class CompanyModuleDataBuilderImpl extends CompanyModuleDataBuilder<CompanyModuleData, CompanyModuleDataBuilderImpl> {
      private CompanyModuleDataBuilderImpl() {
      }

      protected CompanyModuleDataBuilderImpl self() {
         return this;
      }

      public CompanyModuleData build() {
         return new CompanyModuleData(this);
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected CompanyModuleDataBuilder self() {
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
