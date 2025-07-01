package com.gafapay.elasticsearch.api.commonresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

public class SuccessResponse {
   @JsonProperty("success")
   private int success;
   @JsonProperty("error")
   private ArrayList<String> error;
   @JsonProperty("data")
   private Object data;

   public SuccessResponse(int success, ArrayList<String> error, Object data) {
      this.success = success;
      this.error = error;
      this.data = data;
   }

   public SuccessResponse() {
   }

   public int getSuccess() {
      return this.success;
   }

   public ArrayList<String> getError() {
      return this.error;
   }

   public Object getData() {
      return this.data;
   }

   @JsonProperty("success")
   public void setSuccess(final int success) {
      this.success = success;
   }

   @JsonProperty("error")
   public void setError(final ArrayList<String> error) {
      this.error = error;
   }

   @JsonProperty("data")
   public void setData(final Object data) {
      this.data = data;
   }

   public boolean equals(final Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SuccessResponse)) {
         return false;
      } else {
         SuccessResponse other = (SuccessResponse)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getSuccess() != other.getSuccess()) {
            return false;
         } else {
            Object this$error = this.getError();
            Object other$error = other.getError();
            if (this$error == null) {
               if (other$error != null) {
                  return false;
               }
            } else if (!this$error.equals(other$error)) {
               return false;
            }

            Object this$data = this.getData();
            Object other$data = other.getData();
            if (this$data == null) {
               if (other$data != null) {
                  return false;
               }
            } else if (!this$data.equals(other$data)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(final Object other) {
      return other instanceof SuccessResponse;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getSuccess();
      Object $error = this.getError();
      result = result * 59 + ($error == null ? 43 : $error.hashCode());
      Object $data = this.getData();
      result = result * 59 + ($data == null ? 43 : $data.hashCode());
      return result;
   }

   public String toString() {
      int var10000 = this.getSuccess();
      return "SuccessResponse(success=" + var10000 + ", error=" + this.getError() + ", data=" + this.getData() + ")";
   }
}
