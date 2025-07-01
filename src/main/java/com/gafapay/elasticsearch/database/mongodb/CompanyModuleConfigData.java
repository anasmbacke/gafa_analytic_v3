package com.gafapay.elasticsearch.database.mongodb;

import com.gafapay.elasticsearch.database.model.CommonFieldModel;
import com.gafapay.elasticsearch.utils.Utils;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(
   collection = "company_module_config"
)
public class CompanyModuleConfigData extends CommonFieldModel {
   @Field("_id")
   public String id;
   @Field("module_id")
   public String moduleId = null;
   @Field("module_config_id")
   public String moduleConfigId = null;
   @Field("config_key")
   public String configKey = null;
   @Field("config_value")
   public String configValue = null;
   @Field("field_title")
   public String fieldTitle = null;
   @Field("field_type")
   public Integer fieldType = null;
   @Field("field_description")
   public String fieldDescription = null;
   @Field("is_mandatory")
   public Boolean isMandatory = null;

   private static String $default$id() {
      return Utils.generateUUID();
   }

   protected CompanyModuleConfigData(final CompanyModuleConfigDataBuilder<?, ?> b) {
      super(b);
      if (b.id$set) {
         this.id = b.id$value;
      } else {
         this.id = $default$id();
      }

      this.moduleId = b.moduleId;
      this.moduleConfigId = b.moduleConfigId;
      this.configKey = b.configKey;
      this.configValue = b.configValue;
      this.fieldTitle = b.fieldTitle;
      this.fieldType = b.fieldType;
      this.fieldDescription = b.fieldDescription;
      this.isMandatory = b.isMandatory;
   }

   public static CompanyModuleConfigDataBuilder<?, ?> builder() {
      return new CompanyModuleConfigDataBuilderImpl();
   }

   public CompanyModuleConfigData() {
      this.id = $default$id();
   }

   public String getId() {
      return this.id;
   }

   public String getModuleId() {
      return this.moduleId;
   }

   public String getModuleConfigId() {
      return this.moduleConfigId;
   }

   public String getConfigKey() {
      return this.configKey;
   }

   public String getConfigValue() {
      return this.configValue;
   }

   public String getFieldTitle() {
      return this.fieldTitle;
   }

   public Integer getFieldType() {
      return this.fieldType;
   }

   public String getFieldDescription() {
      return this.fieldDescription;
   }

   public Boolean getIsMandatory() {
      return this.isMandatory;
   }

   public void setId(final String id) {
      this.id = id;
   }

   public void setModuleId(final String moduleId) {
      this.moduleId = moduleId;
   }

   public void setModuleConfigId(final String moduleConfigId) {
      this.moduleConfigId = moduleConfigId;
   }

   public void setConfigKey(final String configKey) {
      this.configKey = configKey;
   }

   public void setConfigValue(final String configValue) {
      this.configValue = configValue;
   }

   public void setFieldTitle(final String fieldTitle) {
      this.fieldTitle = fieldTitle;
   }

   public void setFieldType(final Integer fieldType) {
      this.fieldType = fieldType;
   }

   public void setFieldDescription(final String fieldDescription) {
      this.fieldDescription = fieldDescription;
   }

   public void setIsMandatory(final Boolean isMandatory) {
      this.isMandatory = isMandatory;
   }

   public boolean equals(final Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CompanyModuleConfigData)) {
         return false;
      } else {
         CompanyModuleConfigData other = (CompanyModuleConfigData)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$fieldType = this.getFieldType();
            Object other$fieldType = other.getFieldType();
            if (this$fieldType == null) {
               if (other$fieldType != null) {
                  return false;
               }
            } else if (!this$fieldType.equals(other$fieldType)) {
               return false;
            }

            Object this$isMandatory = this.getIsMandatory();
            Object other$isMandatory = other.getIsMandatory();
            if (this$isMandatory == null) {
               if (other$isMandatory != null) {
                  return false;
               }
            } else if (!this$isMandatory.equals(other$isMandatory)) {
               return false;
            }

            Object this$id = this.getId();
            Object other$id = other.getId();
            if (this$id == null) {
               if (other$id != null) {
                  return false;
               }
            } else if (!this$id.equals(other$id)) {
               return false;
            }

            Object this$moduleId = this.getModuleId();
            Object other$moduleId = other.getModuleId();
            if (this$moduleId == null) {
               if (other$moduleId != null) {
                  return false;
               }
            } else if (!this$moduleId.equals(other$moduleId)) {
               return false;
            }

            Object this$moduleConfigId = this.getModuleConfigId();
            Object other$moduleConfigId = other.getModuleConfigId();
            if (this$moduleConfigId == null) {
               if (other$moduleConfigId != null) {
                  return false;
               }
            } else if (!this$moduleConfigId.equals(other$moduleConfigId)) {
               return false;
            }

            Object this$configKey = this.getConfigKey();
            Object other$configKey = other.getConfigKey();
            if (this$configKey == null) {
               if (other$configKey != null) {
                  return false;
               }
            } else if (!this$configKey.equals(other$configKey)) {
               return false;
            }

            Object this$configValue = this.getConfigValue();
            Object other$configValue = other.getConfigValue();
            if (this$configValue == null) {
               if (other$configValue != null) {
                  return false;
               }
            } else if (!this$configValue.equals(other$configValue)) {
               return false;
            }

            Object this$fieldTitle = this.getFieldTitle();
            Object other$fieldTitle = other.getFieldTitle();
            if (this$fieldTitle == null) {
               if (other$fieldTitle != null) {
                  return false;
               }
            } else if (!this$fieldTitle.equals(other$fieldTitle)) {
               return false;
            }

            Object this$fieldDescription = this.getFieldDescription();
            Object other$fieldDescription = other.getFieldDescription();
            if (this$fieldDescription == null) {
               if (other$fieldDescription != null) {
                  return false;
               }
            } else if (!this$fieldDescription.equals(other$fieldDescription)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(final Object other) {
      return other instanceof CompanyModuleConfigData;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $fieldType = this.getFieldType();
      result = result * 59 + ($fieldType == null ? 43 : $fieldType.hashCode());
      Object $isMandatory = this.getIsMandatory();
      result = result * 59 + ($isMandatory == null ? 43 : $isMandatory.hashCode());
      Object $id = this.getId();
      result = result * 59 + ($id == null ? 43 : $id.hashCode());
      Object $moduleId = this.getModuleId();
      result = result * 59 + ($moduleId == null ? 43 : $moduleId.hashCode());
      Object $moduleConfigId = this.getModuleConfigId();
      result = result * 59 + ($moduleConfigId == null ? 43 : $moduleConfigId.hashCode());
      Object $configKey = this.getConfigKey();
      result = result * 59 + ($configKey == null ? 43 : $configKey.hashCode());
      Object $configValue = this.getConfigValue();
      result = result * 59 + ($configValue == null ? 43 : $configValue.hashCode());
      Object $fieldTitle = this.getFieldTitle();
      result = result * 59 + ($fieldTitle == null ? 43 : $fieldTitle.hashCode());
      Object $fieldDescription = this.getFieldDescription();
      result = result * 59 + ($fieldDescription == null ? 43 : $fieldDescription.hashCode());
      return result;
   }

   public String toString() {
      String var10000 = this.getId();
      return "CompanyModuleConfigData(id=" + var10000 + ", moduleId=" + this.getModuleId() + ", moduleConfigId=" + this.getModuleConfigId() + ", configKey=" + this.getConfigKey() + ", configValue=" + this.getConfigValue() + ", fieldTitle=" + this.getFieldTitle() + ", fieldType=" + this.getFieldType() + ", fieldDescription=" + this.getFieldDescription() + ", isMandatory=" + this.getIsMandatory() + ")";
   }

   public abstract static class CompanyModuleConfigDataBuilder<C extends CompanyModuleConfigData, B extends CompanyModuleConfigDataBuilder<C, B>> extends CommonFieldModel.CommonFieldModelBuilder<C, B> {
      private boolean id$set;
      private String id$value;
      private String moduleId;
      private String moduleConfigId;
      private String configKey;
      private String configValue;
      private String fieldTitle;
      private Integer fieldType;
      private String fieldDescription;
      private Boolean isMandatory;

      public CompanyModuleConfigDataBuilder() {
      }

      protected abstract B self();

      public abstract C build();

      public B id(final String id) {
         this.id$value = id;
         this.id$set = true;
         return (B)this.self();
      }

      public B moduleId(final String moduleId) {
         this.moduleId = moduleId;
         return (B)this.self();
      }

      public B moduleConfigId(final String moduleConfigId) {
         this.moduleConfigId = moduleConfigId;
         return (B)this.self();
      }

      public B configKey(final String configKey) {
         this.configKey = configKey;
         return (B)this.self();
      }

      public B configValue(final String configValue) {
         this.configValue = configValue;
         return (B)this.self();
      }

      public B fieldTitle(final String fieldTitle) {
         this.fieldTitle = fieldTitle;
         return (B)this.self();
      }

      public B fieldType(final Integer fieldType) {
         this.fieldType = fieldType;
         return (B)this.self();
      }

      public B fieldDescription(final String fieldDescription) {
         this.fieldDescription = fieldDescription;
         return (B)this.self();
      }

      public B isMandatory(final Boolean isMandatory) {
         this.isMandatory = isMandatory;
         return (B)this.self();
      }

      public String toString() {
         String var10000 = super.toString();
         return "CompanyModuleConfigData.CompanyModuleConfigDataBuilder(super=" + var10000 + ", id$value=" + this.id$value + ", moduleId=" + this.moduleId + ", moduleConfigId=" + this.moduleConfigId + ", configKey=" + this.configKey + ", configValue=" + this.configValue + ", fieldTitle=" + this.fieldTitle + ", fieldType=" + this.fieldType + ", fieldDescription=" + this.fieldDescription + ", isMandatory=" + this.isMandatory + ")";
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

   private static final class CompanyModuleConfigDataBuilderImpl extends CompanyModuleConfigDataBuilder<CompanyModuleConfigData, CompanyModuleConfigDataBuilderImpl> {
      private CompanyModuleConfigDataBuilderImpl() {
      }

      protected CompanyModuleConfigDataBuilderImpl self() {
         return this;
      }

      public CompanyModuleConfigData build() {
         return new CompanyModuleConfigData(this);
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected CompanyModuleConfigDataBuilder self() {
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
