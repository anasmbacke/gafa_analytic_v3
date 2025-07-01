package com.gafapay.elasticsearch.api.statistics.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class GetRevenueStatisticsResponse extends CommonAPIDataResponse {
   @JsonProperty("total_revenue")
   private Double totalRevenue;
   @JsonProperty("overall_revenue_statistics")
   private List<OverAllRevenueStatistics> overAllRevenueStatisticsList;
   @JsonProperty("product_wise_revenue_statistics")
   private List<ProductWiseRevenueStatistics> productWiseRevenueStatisticsList;
   @JsonProperty("user_wise_revenue_statistics")
   private List<UserWiseRevenueStatistics> userWiseRevenueStatisticsList;

   public Double getTotalRevenue() {
      return this.totalRevenue;
   }

   public List<OverAllRevenueStatistics> getOverAllRevenueStatisticsList() {
      return this.overAllRevenueStatisticsList;
   }

   public List<ProductWiseRevenueStatistics> getProductWiseRevenueStatisticsList() {
      return this.productWiseRevenueStatisticsList;
   }

   public List<UserWiseRevenueStatistics> getUserWiseRevenueStatisticsList() {
      return this.userWiseRevenueStatisticsList;
   }

   @JsonProperty("total_revenue")
   public void setTotalRevenue(final Double totalRevenue) {
      this.totalRevenue = totalRevenue;
   }

   @JsonProperty("overall_revenue_statistics")
   public void setOverAllRevenueStatisticsList(final List<OverAllRevenueStatistics> overAllRevenueStatisticsList) {
      this.overAllRevenueStatisticsList = overAllRevenueStatisticsList;
   }

   @JsonProperty("product_wise_revenue_statistics")
   public void setProductWiseRevenueStatisticsList(final List<ProductWiseRevenueStatistics> productWiseRevenueStatisticsList) {
      this.productWiseRevenueStatisticsList = productWiseRevenueStatisticsList;
   }

   @JsonProperty("user_wise_revenue_statistics")
   public void setUserWiseRevenueStatisticsList(final List<UserWiseRevenueStatistics> userWiseRevenueStatisticsList) {
      this.userWiseRevenueStatisticsList = userWiseRevenueStatisticsList;
   }

   public GetRevenueStatisticsResponse() {
   }

   public static class OverAllRevenueStatistics {
      @JsonProperty("timestamp")
      private Long timeStamp;
      @JsonProperty("month")
      private String month;
      @JsonProperty("total_count")
      private Integer totalCount;
      @JsonProperty("total_amount")
      private Double totalAmount;

      @JsonProperty("timestamp")
      public void setTimeStamp(final Long timeStamp) {
         this.timeStamp = timeStamp;
      }

      @JsonProperty("month")
      public void setMonth(final String month) {
         this.month = month;
      }

      @JsonProperty("total_count")
      public void setTotalCount(final Integer totalCount) {
         this.totalCount = totalCount;
      }

      @JsonProperty("total_amount")
      public void setTotalAmount(final Double totalAmount) {
         this.totalAmount = totalAmount;
      }

      public Long getTimeStamp() {
         return this.timeStamp;
      }

      public String getMonth() {
         return this.month;
      }

      public Integer getTotalCount() {
         return this.totalCount;
      }

      public Double getTotalAmount() {
         return this.totalAmount;
      }

      public OverAllRevenueStatistics() {
      }
   }

   public static class ProductWiseRevenueStatistics {
      @JsonProperty("product_code")
      private String productCode;
      @JsonProperty("overall")
      private Double overall;
      @JsonProperty("total_amount")
      private Double totalAmount;

      @JsonProperty("product_code")
      public void setProductCode(final String productCode) {
         this.productCode = productCode;
      }

      @JsonProperty("overall")
      public void setOverall(final Double overall) {
         this.overall = overall;
      }

      @JsonProperty("total_amount")
      public void setTotalAmount(final Double totalAmount) {
         this.totalAmount = totalAmount;
      }

      public String getProductCode() {
         return this.productCode;
      }

      public Double getOverall() {
         return this.overall;
      }

      public Double getTotalAmount() {
         return this.totalAmount;
      }

      public ProductWiseRevenueStatistics() {
      }
   }

   public static class UserWiseRevenueStatistics {
      @JsonProperty("user_type")
      private Integer userType;
      @JsonProperty("overall")
      private Double overall;
      @JsonProperty("total_amount")
      private Double totalAmount;

      @JsonProperty("user_type")
      public void setUserType(final Integer userType) {
         this.userType = userType;
      }

      @JsonProperty("overall")
      public void setOverall(final Double overall) {
         this.overall = overall;
      }

      @JsonProperty("total_amount")
      public void setTotalAmount(final Double totalAmount) {
         this.totalAmount = totalAmount;
      }

      public Integer getUserType() {
         return this.userType;
      }

      public Double getOverall() {
         return this.overall;
      }

      public Double getTotalAmount() {
         return this.totalAmount;
      }

      public UserWiseRevenueStatistics() {
      }
   }
}
