package com.gafapay.elasticsearch.database.mongodb;

import com.gafapay.elasticsearch.database.model.CommonFieldModel;
import com.gafapay.elasticsearch.utils.Utils;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(
   collection = "business_type_master"
)
public class BusinessTypeMaster extends CommonFieldModel {
   @Field("_id")
   private String id;
   @Field("name")
   private String name;
   @Field("key")
   private String key;

   private static String $default$id() {
      return Utils.generateUUID();
   }

   protected BusinessTypeMaster(final BusinessTypeMasterBuilder<?, ?> b) {
      super(b);
      if (b.id$set) {
         this.id = b.id$value;
      } else {
         this.id = $default$id();
      }

      this.name = b.name;
      this.key = b.key;
   }

   public static BusinessTypeMasterBuilder<?, ?> builder() {
      return new BusinessTypeMasterBuilderImpl();
   }

   public String getId() {
      return this.id;
   }

   public String getName() {
      return this.name;
   }

   public String getKey() {
      return this.key;
   }

   public void setId(final String id) {
      this.id = id;
   }

   public void setName(final String name) {
      this.name = name;
   }

   public void setKey(final String key) {
      this.key = key;
   }

   public BusinessTypeMaster() {
      this.id = $default$id();
   }

   public abstract static class BusinessTypeMasterBuilder<C extends BusinessTypeMaster, B extends BusinessTypeMasterBuilder<C, B>> extends CommonFieldModel.CommonFieldModelBuilder<C, B> {
      private boolean id$set;
      private String id$value;
      private String name;
      private String key;

      public BusinessTypeMasterBuilder() {
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

      public B key(final String key) {
         this.key = key;
         return (B)this.self();
      }

      public String toString() {
         String var10000 = super.toString();
         return "BusinessTypeMaster.BusinessTypeMasterBuilder(super=" + var10000 + ", id$value=" + this.id$value + ", name=" + this.name + ", key=" + this.key + ")";
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

   private static final class BusinessTypeMasterBuilderImpl extends BusinessTypeMasterBuilder<BusinessTypeMaster, BusinessTypeMasterBuilderImpl> {
      private BusinessTypeMasterBuilderImpl() {
      }

      protected BusinessTypeMasterBuilderImpl self() {
         return this;
      }

      public BusinessTypeMaster build() {
         return new BusinessTypeMaster(this);
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected BusinessTypeMasterBuilder self() {
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
