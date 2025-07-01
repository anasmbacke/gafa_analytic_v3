package com.gafapay.elasticsearch.api.statistics.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class GetTxnStatisticsDataResponse extends CommonAPIDataResponse {
   @JsonProperty("transaction_statistics")
   private TransactionStatisticsData transactionStatisticsData;
   @JsonProperty("activity")
   private Activity activity;

   public TransactionStatisticsData getTransactionStatisticsData() {
      return this.transactionStatisticsData;
   }

   public Activity getActivity() {
      return this.activity;
   }

   @JsonProperty("transaction_statistics")
   public void setTransactionStatisticsData(final TransactionStatisticsData transactionStatisticsData) {
      this.transactionStatisticsData = transactionStatisticsData;
   }

   @JsonProperty("activity")
   public void setActivity(final Activity activity) {
      this.activity = activity;
   }

   public GetTxnStatisticsDataResponse() {
   }

   public static class Activity {
      @JsonProperty("daily_count_of_active_users")
      private long dailyCountOfActiveUsers;
      @JsonProperty("weekly_count_of_active_users")
      private long weeklyCountOfActiveUsers;
      @JsonProperty("monthly_count_of_active_users")
      private long monthlyCountOfActiveUsers;
      @JsonProperty("yearly_count_of_active_users")
      private long yearlyCountOfActiveUsers;
      @JsonProperty("one_time_usage")
      private long oneTimeUsage;

      public long getDailyCountOfActiveUsers() {
         return this.dailyCountOfActiveUsers;
      }

      public long getWeeklyCountOfActiveUsers() {
         return this.weeklyCountOfActiveUsers;
      }

      public long getMonthlyCountOfActiveUsers() {
         return this.monthlyCountOfActiveUsers;
      }

      public long getYearlyCountOfActiveUsers() {
         return this.yearlyCountOfActiveUsers;
      }

      public long getOneTimeUsage() {
         return this.oneTimeUsage;
      }

      @JsonProperty("daily_count_of_active_users")
      public void setDailyCountOfActiveUsers(final long dailyCountOfActiveUsers) {
         this.dailyCountOfActiveUsers = dailyCountOfActiveUsers;
      }

      @JsonProperty("weekly_count_of_active_users")
      public void setWeeklyCountOfActiveUsers(final long weeklyCountOfActiveUsers) {
         this.weeklyCountOfActiveUsers = weeklyCountOfActiveUsers;
      }

      @JsonProperty("monthly_count_of_active_users")
      public void setMonthlyCountOfActiveUsers(final long monthlyCountOfActiveUsers) {
         this.monthlyCountOfActiveUsers = monthlyCountOfActiveUsers;
      }

      @JsonProperty("yearly_count_of_active_users")
      public void setYearlyCountOfActiveUsers(final long yearlyCountOfActiveUsers) {
         this.yearlyCountOfActiveUsers = yearlyCountOfActiveUsers;
      }

      @JsonProperty("one_time_usage")
      public void setOneTimeUsage(final long oneTimeUsage) {
         this.oneTimeUsage = oneTimeUsage;
      }

      public Activity() {
      }
   }

   public static class TransactionStatisticsData {
      @JsonProperty("total_transactions")
      private long totalTransactions;
      @JsonProperty("status_wise_statistics")
      private List<Map<String, Object>> statusWiseStatistics;
      @JsonProperty("total_charges")
      private Double totalCharges;
      @JsonProperty("total_commission")
      private Double totalCommission;
      @JsonProperty("total_cash_in")
      private Double totalCashIn;
      @JsonProperty("total_cash_out")
      private Double totalCashOut;
      @JsonProperty("total_transaction_amount")
      private Double totalTransactionAmount;
      @JsonProperty("min_transaction_amount")
      private Double minTransactionAmount;
      @JsonProperty("max_transaction_amount")
      private Double maxTransactionAmount;
      @JsonProperty("transactions")
      private List<Map<String, Object>> transactions;

      public long getTotalTransactions() {
         return this.totalTransactions;
      }

      public List<Map<String, Object>> getStatusWiseStatistics() {
         return this.statusWiseStatistics;
      }

      public Double getTotalCharges() {
         return this.totalCharges;
      }

      public Double getTotalCommission() {
         return this.totalCommission;
      }

      public Double getTotalCashIn() {
         return this.totalCashIn;
      }

      public Double getTotalCashOut() {
         return this.totalCashOut;
      }

      public Double getTotalTransactionAmount() {
         return this.totalTransactionAmount;
      }

      public Double getMinTransactionAmount() {
         return this.minTransactionAmount;
      }

      public Double getMaxTransactionAmount() {
         return this.maxTransactionAmount;
      }

      public List<Map<String, Object>> getTransactions() {
         return this.transactions;
      }

      @JsonProperty("total_transactions")
      public void setTotalTransactions(final long totalTransactions) {
         this.totalTransactions = totalTransactions;
      }

      @JsonProperty("status_wise_statistics")
      public void setStatusWiseStatistics(final List<Map<String, Object>> statusWiseStatistics) {
         this.statusWiseStatistics = statusWiseStatistics;
      }

      @JsonProperty("total_charges")
      public void setTotalCharges(final Double totalCharges) {
         this.totalCharges = totalCharges;
      }

      @JsonProperty("total_commission")
      public void setTotalCommission(final Double totalCommission) {
         this.totalCommission = totalCommission;
      }

      @JsonProperty("total_cash_in")
      public void setTotalCashIn(final Double totalCashIn) {
         this.totalCashIn = totalCashIn;
      }

      @JsonProperty("total_cash_out")
      public void setTotalCashOut(final Double totalCashOut) {
         this.totalCashOut = totalCashOut;
      }

      @JsonProperty("total_transaction_amount")
      public void setTotalTransactionAmount(final Double totalTransactionAmount) {
         this.totalTransactionAmount = totalTransactionAmount;
      }

      @JsonProperty("min_transaction_amount")
      public void setMinTransactionAmount(final Double minTransactionAmount) {
         this.minTransactionAmount = minTransactionAmount;
      }

      @JsonProperty("max_transaction_amount")
      public void setMaxTransactionAmount(final Double maxTransactionAmount) {
         this.maxTransactionAmount = maxTransactionAmount;
      }

      @JsonProperty("transactions")
      public void setTransactions(final List<Map<String, Object>> transactions) {
         this.transactions = transactions;
      }

      public TransactionStatisticsData() {
      }
   }
}
