package com.gafapay.elasticsearch.database.mongodb;

import com.gafapay.elasticsearch.database.model.CommonFieldModel;
import com.gafapay.elasticsearch.utils.Utils;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(
   collection = "business_category"
)
public class BusinessCategory extends CommonFieldModel {
   @Field("_id")
   private String id;
   @Field("name")
   private String name;
   @Field("image")
   private String image;

   private static String $default$id() {
      return Utils.generateUUID();
   }

   protected BusinessCategory(final BusinessCategoryBuilder<?, ?> b) {
      super(b);
      if (b.id$set) {
         this.id = b.id$value;
      } else {
         this.id = $default$id();
      }

      this.name = b.name;
      this.image = b.image;
   }

   public static BusinessCategoryBuilder<?, ?> builder() {
      return new BusinessCategoryBuilderImpl();
   }

   public String getId() {
      return this.id;
   }

   public String getName() {
      return this.name;
   }

   public String getImage() {
      return this.image;
   }

   public void setId(final String id) {
      this.id = id;
   }

   public void setName(final String name) {
      this.name = name;
   }

   public void setImage(final String image) {
      this.image = image;
   }

   public BusinessCategory() {
      this.id = $default$id();
   }

   public abstract static class BusinessCategoryBuilder<C extends BusinessCategory, B extends BusinessCategoryBuilder<C, B>> extends CommonFieldModel.CommonFieldModelBuilder<C, B> {
      private boolean id$set;
      private String id$value;
      private String name;
      private String image;

      public BusinessCategoryBuilder() {
      }

      protected abstract B self();

      public abstract C build();

      public B id(final String id) {
         this.id$value = id;
         this.id$set = true;
         return (B)this.self();
      }

      public B name(final String name) {
         this.name = name;
         return (B)this.self();
      }

      public B image(final String image) {
         this.image = image;
         return (B)this.self();
      }

      public String toString() {
         String var10000 = super.toString();
         return "BusinessCategory.BusinessCategoryBuilder(super=" + var10000 + ", id$value=" + this.id$value + ", name=" + this.name + ", image=" + this.image + ")";
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

   private static final class BusinessCategoryBuilderImpl extends BusinessCategoryBuilder<BusinessCategory, BusinessCategoryBuilderImpl> {
      private BusinessCategoryBuilderImpl() {
      }

      protected BusinessCategoryBuilderImpl self() {
         return this;
      }

      public BusinessCategory build() {
         return new BusinessCategory(this);
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected BusinessCategoryBuilder self() {
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
