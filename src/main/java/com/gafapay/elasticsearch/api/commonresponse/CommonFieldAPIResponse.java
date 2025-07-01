package com.gafapay.elasticsearch.api.commonresponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommonFieldAPIResponse {
   @JsonProperty("created_date")
   private Long createdDate;
   @JsonProperty("updated_date")
   private Long updatedDate;
   @JsonProperty("is_active")
   private Boolean isActive;
   @JsonProperty("created_by")
   private String createdBy;
   @JsonProperty("updated_by")
   private String updatedBy;

   public Long getCreatedDate() {
      return this.createdDate;
   }

   public Long getUpdatedDate() {
      return this.updatedDate;
   }

   public Boolean getIsActive() {
      return this.isActive;
   }

   public String getCreatedBy() {
      return this.createdBy;
   }

   public String getUpdatedBy() {
      return this.updatedBy;
   }

   @JsonProperty("created_date")
   public void setCreatedDate(final Long createdDate) {
      this.createdDate = createdDate;
   }

   @JsonProperty("updated_date")
   public void setUpdatedDate(final Long updatedDate) {
      this.updatedDate = updatedDate;
   }

   @JsonProperty("is_active")
   public void setIsActive(final Boolean isActive) {
      this.isActive = isActive;
   }

   @JsonProperty("created_by")
   public void setCreatedBy(final String createdBy) {
      this.createdBy = createdBy;
   }

   @JsonProperty("updated_by")
   public void setUpdatedBy(final String updatedBy) {
      this.updatedBy = updatedBy;
   }

   public CommonFieldAPIResponse() {
   }
}
