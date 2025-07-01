package com.gafapay.elasticsearch.database.model;

import org.springframework.data.mongodb.core.mapping.Field;

public class CommonFieldModel {
   @Field("company_id")
   public String companyId = null;
   @Field("is_active")
   public Boolean isActive;
   @Field("created_date")
   public Long createdDate;
   @Field("created_by")
   public String createdBy = null;
   @Field("updated_date")
   public Long updatedDate;
   @Field("updated_by")
   public String updatedBy = null;

   private static Boolean $default$isActive() {
      return true;
   }

   private static Long $default$createdDate() {
      return System.currentTimeMillis() / 1000L;
   }

   private static Long $default$updatedDate() {
      return System.currentTimeMillis() / 1000L;
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

   public boolean equals(final Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CommonFieldModel)) {
         return false;
      } else {
         CommonFieldModel other = (CommonFieldModel)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$isActive = this.getIsActive();
            Object other$isActive = other.getIsActive();
            if (this$isActive == null) {
               if (other$isActive != null) {
                  return false;
               }
            } else if (!this$isActive.equals(other$isActive)) {
               return false;
            }

            Object this$createdDate = this.getCreatedDate();
            Object other$createdDate = other.getCreatedDate();
            if (this$createdDate == null) {
               if (other$createdDate != null) {
                  return false;
               }
            } else if (!this$createdDate.equals(other$createdDate)) {
               return false;
            }

            Object this$updatedDate = this.getUpdatedDate();
            Object other$updatedDate = other.getUpdatedDate();
            if (this$updatedDate == null) {
               if (other$updatedDate != null) {
                  return false;
               }
            } else if (!this$updatedDate.equals(other$updatedDate)) {
               return false;
            }

            Object this$companyId = this.getCompanyId();
            Object other$companyId = other.getCompanyId();
            if (this$companyId == null) {
               if (other$companyId != null) {
                  return false;
               }
            } else if (!this$companyId.equals(other$companyId)) {
               return false;
            }

            Object this$createdBy = this.getCreatedBy();
            Object other$createdBy = other.getCreatedBy();
            if (this$createdBy == null) {
               if (other$createdBy != null) {
                  return false;
               }
            } else if (!this$createdBy.equals(other$createdBy)) {
               return false;
            }

            Object this$updatedBy = this.getUpdatedBy();
            Object other$updatedBy = other.getUpdatedBy();
            if (this$updatedBy == null) {
               if (other$updatedBy != null) {
                  return false;
               }
            } else if (!this$updatedBy.equals(other$updatedBy)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(final Object other) {
      return other instanceof CommonFieldModel;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $isActive = this.getIsActive();
      result = result * 59 + ($isActive == null ? 43 : $isActive.hashCode());
      Object $createdDate = this.getCreatedDate();
      result = result * 59 + ($createdDate == null ? 43 : $createdDate.hashCode());
      Object $updatedDate = this.getUpdatedDate();
      result = result * 59 + ($updatedDate == null ? 43 : $updatedDate.hashCode());
      Object $companyId = this.getCompanyId();
      result = result * 59 + ($companyId == null ? 43 : $companyId.hashCode());
      Object $createdBy = this.getCreatedBy();
      result = result * 59 + ($createdBy == null ? 43 : $createdBy.hashCode());
      Object $updatedBy = this.getUpdatedBy();
      result = result * 59 + ($updatedBy == null ? 43 : $updatedBy.hashCode());
      return result;
   }

   public String toString() {
      String var10000 = this.getCompanyId();
      return "CommonFieldModel(companyId=" + var10000 + ", isActive=" + this.getIsActive() + ", createdDate=" + this.getCreatedDate() + ", createdBy=" + this.getCreatedBy() + ", updatedDate=" + this.getUpdatedDate() + ", updatedBy=" + this.getUpdatedBy() + ")";
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
