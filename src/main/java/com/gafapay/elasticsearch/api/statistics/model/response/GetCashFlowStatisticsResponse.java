package com.gafapay.elasticsearch.api.statistics.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class GetCashFlowStatisticsResponse extends CommonAPIDataResponse {
   @JsonProperty("transaction_statistics")
   private CashStatics cashStatics;

   @JsonProperty("transaction_statistics")
   public void setCashStatics(final CashStatics cashStatics) {
      this.cashStatics = cashStatics;
   }

   public CashStatics getCashStatics() {
      return this.cashStatics;
   }

   public GetCashFlowStatisticsResponse() {
   }

   public static class CashStatics {
      @JsonProperty("total_cash_in")
      private Double totalCashIn;
      @JsonProperty("total_cash_out")
      private Double totalCashOut;
      @JsonProperty("net_balance")
      private Double netBalance;
      @JsonProperty("cash_flow_data")
      private List<Map<String, Object>> cashFlowData;

      @JsonProperty("total_cash_in")
      public void setTotalCashIn(final Double totalCashIn) {
         this.totalCashIn = totalCashIn;
      }

      @JsonProperty("total_cash_out")
      public void setTotalCashOut(final Double totalCashOut) {
         this.totalCashOut = totalCashOut;
      }

      @JsonProperty("net_balance")
      public void setNetBalance(final Double netBalance) {
         this.netBalance = netBalance;
      }

      @JsonProperty("cash_flow_data")
      public void setCashFlowData(final List<Map<String, Object>> cashFlowData) {
         this.cashFlowData = cashFlowData;
      }

      public Double getTotalCashIn() {
         return this.totalCashIn;
      }

      public Double getTotalCashOut() {
         return this.totalCashOut;
      }

      public Double getNetBalance() {
         return this.netBalance;
      }

      public List<Map<String, Object>> getCashFlowData() {
         return this.cashFlowData;
      }

      public CashStatics() {
      }
   }
}
