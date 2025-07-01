package com.gafapay.elasticsearch.api.eventlogs.model.request;

import java.time.Instant;
import org.springframework.data.mongodb.core.mapping.Field;

public class CommonFieldModel {
   @Field("company_id")
   private String companyId = null;
   @Field("is_active")
   private Boolean isActive;
   @Field("created_date")
   private Long createdDate;
   @Field("created_by")
   private String createdBy;
   @Field("updated_date")
   private Long updatedDate;
   @Field("updated_by")
   private String updatedBy;

   private static Boolean $default$isActive() {
      return true;
   }

   private static Long $default$createdDate() {
      return Instant.now().getEpochSecond();
   }

   private static Long $default$updatedDate() {
      return Instant.now().getEpochSecond();
   }

   protected CommonFieldModel(final CommonFieldModelBuilder<?, ?> b) {
      this.companyId = b.companyId;
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
      if (b.updatedDate$set) {
         this.updatedDate = b.updatedDate$value;
      } else {
         this.updatedDate = $default$updatedDate();
      }

      this.updatedBy = b.updatedBy;
   }

   public static CommonFieldModelBuilder<?, ?> builder() {
      return new CommonFieldModelBuilderImpl();
   }

   public String getCompanyId() {
      return this.companyId;
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

   public void setCompanyId(final String companyId) {
      this.companyId = companyId;
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

   public CommonFieldModel() {
      this.isActive = $default$isActive();
      this.createdDate = $default$createdDate();
      this.updatedDate = $default$updatedDate();
   }

   public CommonFieldModel(final String companyId, final Boolean isActive, final Long createdDate, final String createdBy, final Long updatedDate, final String updatedBy) {
      this.companyId = companyId;
      this.isActive = isActive;
      this.createdDate = createdDate;
      this.createdBy = createdBy;
      this.updatedDate = updatedDate;
      this.updatedBy = updatedBy;
   }

   public abstract static class CommonFieldModelBuilder<C extends CommonFieldModel, B extends CommonFieldModelBuilder<C, B>> {
      private String companyId;
      private boolean isActive$set;
      private Boolean isActive$value;
      private boolean createdDate$set;
      private Long createdDate$value;
      private String createdBy;
      private boolean updatedDate$set;
      private Long updatedDate$value;
      private String updatedBy;

      public CommonFieldModelBuilder() {
      }

      protected abstract B self();

      public abstract C build();

      public B companyId(final String companyId) {
         this.companyId = companyId;
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
         this.updatedDate$value = updatedDate;
         this.updatedDate$set = true;
         return (B)this.self();
      }

      public B updatedBy(final String updatedBy) {
         this.updatedBy = updatedBy;
         return (B)this.self();
      }

      public String toString() {
         return "CommonFieldModel.CommonFieldModelBuilder(companyId=" + this.companyId + ", isActive$value=" + this.isActive$value + ", createdDate$value=" + this.createdDate$value + ", createdBy=" + this.createdBy + ", updatedDate$value=" + this.updatedDate$value + ", updatedBy=" + this.updatedBy + ")";
      }
   }

   private static final class CommonFieldModelBuilderImpl extends CommonFieldModelBuilder<CommonFieldModel, CommonFieldModelBuilderImpl> {
      private CommonFieldModelBuilderImpl() {
      }

      protected CommonFieldModelBuilderImpl self() {
         return this;
      }

      public CommonFieldModel build() {
         return new CommonFieldModel(this);
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected CommonFieldModelBuilder self() {
         return this.self();
      }
   }
}
