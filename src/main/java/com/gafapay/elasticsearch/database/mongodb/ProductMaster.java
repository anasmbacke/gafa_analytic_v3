package com.gafapay.elasticsearch.database.mongodb;

import com.gafapay.elasticsearch.database.model.CommonFieldModel;
import com.gafapay.elasticsearch.utils.Utils;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(
   collection = "product_master"
)
public class ProductMaster extends CommonFieldModel {
   @Field("_id")
   private String id;
   @Field("category_id")
   private String categoryId;
   @Field("product_code")
   private String productCode;
   @Field("product_name")
   private String productName;
   @Field("product_description")
   private String productDesc;
   @Field("user_type")
   private List<Integer> userType;
   @Field("status_id")
   private Integer statusId;

   private static String $default$id() {
      return Utils.generateUUID();
   }

   protected ProductMaster(final ProductMasterBuilder<?, ?> b) {
      super(b);
      if (b.id$set) {
         this.id = b.id$value;
      } else {
         this.id = $default$id();
      }

      this.categoryId = b.categoryId;
      this.productCode = b.productCode;
      this.productName = b.productName;
      this.productDesc = b.productDesc;
      this.userType = b.userType;
      this.statusId = b.statusId;
   }

   public static ProductMasterBuilder<?, ?> builder() {
      return new ProductMasterBuilderImpl();
   }

   public String getId() {
      return this.id;
   }

   public String getCategoryId() {
      return this.categoryId;
   }

   public String getProductCode() {
      return this.productCode;
   }

   public String getProductName() {
      return this.productName;
   }

   public String getProductDesc() {
      return this.productDesc;
   }

   public List<Integer> getUserType() {
      return this.userType;
   }

   public Integer getStatusId() {
      return this.statusId;
   }

   public void setId(final String id) {
      this.id = id;
   }

   public void setCategoryId(final String categoryId) {
      this.categoryId = categoryId;
   }

   public void setProductCode(final String productCode) {
      this.productCode = productCode;
   }

   public void setProductName(final String productName) {
      this.productName = productName;
   }

   public void setProductDesc(final String productDesc) {
      this.productDesc = productDesc;
   }

   public void setUserType(final List<Integer> userType) {
      this.userType = userType;
   }

   public void setStatusId(final Integer statusId) {
      this.statusId = statusId;
   }

   public ProductMaster() {
      this.id = $default$id();
   }

   public abstract static class ProductMasterBuilder<C extends ProductMaster, B extends ProductMasterBuilder<C, B>> extends CommonFieldModel.CommonFieldModelBuilder<C, B> {
      private boolean id$set;
      private String id$value;
      private String categoryId;
      private String productCode;
      private String productName;
      private String productDesc;
      private List<Integer> userType;
      private Integer statusId;

      public ProductMasterBuilder() {
      }

      protected abstract B self();

      public abstract C build();

      public B id(final String id) {
         this.id$value = id;
         this.id$set = true;
         return (B)this.self();
      }

      public B categoryId(final String categoryId) {
         this.categoryId = categoryId;
         return (B)this.self();
      }

      public B productCode(final String productCode) {
         this.productCode = productCode;
         return (B)this.self();
      }

      public B productName(final String productName) {
         this.productName = productName;
         return (B)this.self();
      }

      public B productDesc(final String productDesc) {
         this.productDesc = productDesc;
         return (B)this.self();
      }

      public B userType(final List<Integer> userType) {
         this.userType = userType;
         return (B)this.self();
      }

      public B statusId(final Integer statusId) {
         this.statusId = statusId;
         return (B)this.self();
      }

      public String toString() {
         String var10000 = super.toString();
         return "ProductMaster.ProductMasterBuilder(super=" + var10000 + ", id$value=" + this.id$value + ", categoryId=" + this.categoryId + ", productCode=" + this.productCode + ", productName=" + this.productName + ", productDesc=" + this.productDesc + ", userType=" + this.userType + ", statusId=" + this.statusId + ")";
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

   private static final class ProductMasterBuilderImpl extends ProductMasterBuilder<ProductMaster, ProductMasterBuilderImpl> {
      private ProductMasterBuilderImpl() {
      }

      protected ProductMasterBuilderImpl self() {
         return this;
      }

      public ProductMaster build() {
         return new ProductMaster(this);
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected ProductMasterBuilder self() {
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
