package com.gafapay.elasticsearch.api.commonresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties({"validationMessage", "checkValidationFailed", "checkForUnAuthorized"})
public class CommonAPIDataResponse {
   @JsonProperty("validationMessage")
   private String validationMessage;
   @JsonProperty("checkValidationFailed")
   private boolean checkValidationFailed;
   @JsonProperty("checkForUnAuthorized")
   private boolean checkForUnAuthorized;
   @JsonInclude(Include.NON_EMPTY)
   @JsonProperty("message")
   private String message;

   public CommonAPIDataResponse() {
   }

   public String getValidationMessage() {
      return this.validationMessage;
   }

   public boolean isCheckValidationFailed() {
      return this.checkValidationFailed;
   }

   public boolean isCheckForUnAuthorized() {
      return this.checkForUnAuthorized;
   }

   public String getMessage() {
      return this.message;
   }

   @JsonProperty("validationMessage")
   public void setValidationMessage(final String validationMessage) {
      this.validationMessage = validationMessage;
   }

   @JsonProperty("checkValidationFailed")
   public void setCheckValidationFailed(final boolean checkValidationFailed) {
      this.checkValidationFailed = checkValidationFailed;
   }

   @JsonProperty("checkForUnAuthorized")
   public void setCheckForUnAuthorized(final boolean checkForUnAuthorized) {
      this.checkForUnAuthorized = checkForUnAuthorized;
   }

   @JsonProperty("message")
   public void setMessage(final String message) {
      this.message = message;
   }

   public boolean equals(final Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CommonAPIDataResponse)) {
         return false;
      } else {
         CommonAPIDataResponse other = (CommonAPIDataResponse)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.isCheckValidationFailed() != other.isCheckValidationFailed()) {
            return false;
         } else if (this.isCheckForUnAuthorized() != other.isCheckForUnAuthorized()) {
            return false;
         } else {
            Object this$validationMessage = this.getValidationMessage();
            Object other$validationMessage = other.getValidationMessage();
            if (this$validationMessage == null) {
               if (other$validationMessage != null) {
                  return false;
               }
            } else if (!this$validationMessage.equals(other$validationMessage)) {
               return false;
            }

            Object this$message = this.getMessage();
            Object other$message = other.getMessage();
            if (this$message == null) {
               if (other$message != null) {
                  return false;
               }
            } else if (!this$message.equals(other$message)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(final Object other) {
      return other instanceof CommonAPIDataResponse;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.isCheckValidationFailed() ? 79 : 97);
      result = result * 59 + (this.isCheckForUnAuthorized() ? 79 : 97);
      Object $validationMessage = this.getValidationMessage();
      result = result * 59 + ($validationMessage == null ? 43 : $validationMessage.hashCode());
      Object $message = this.getMessage();
      result = result * 59 + ($message == null ? 43 : $message.hashCode());
      return result;
   }

   public String toString() {
      String var10000 = this.getValidationMessage();
      return "CommonAPIDataResponse(validationMessage=" + var10000 + ", checkValidationFailed=" + this.isCheckValidationFailed() + ", checkForUnAuthorized=" + this.isCheckForUnAuthorized() + ", message=" + this.getMessage() + ")";
   }
}
