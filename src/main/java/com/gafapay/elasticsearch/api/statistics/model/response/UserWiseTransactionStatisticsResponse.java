package com.gafapay.elasticsearch.api.statistics.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class UserWiseTransactionStatisticsResponse extends CommonAPIDataResponse {
   @JsonProperty("userwise_transaction_statistics")
   List<UserTransactions> userTransactionsList;

   public List<UserTransactions> getUserTransactionsList() {
      return this.userTransactionsList;
   }

   @JsonProperty("userwise_transaction_statistics")
   public void setUserTransactionsList(final List<UserTransactions> userTransactionsList) {
      this.userTransactionsList = userTransactionsList;
   }

   public UserWiseTransactionStatisticsResponse() {
   }

   public class UserTransactions {
      private String user_id;
      private Integer total_count;
      private Double total_amount;

      public String getUser_id() {
         return this.user_id;
      }

      public Integer getTotal_count() {
         return this.total_count;
      }

      public Double getTotal_amount() {
         return this.total_amount;
      }

      public void setUser_id(final String user_id) {
         this.user_id = user_id;
      }

      public void setTotal_count(final Integer total_count) {
         this.total_count = total_count;
      }

      public void setTotal_amount(final Double total_amount) {
         this.total_amount = total_amount;
      }

      public UserTransactions() {
      }
   }
}
