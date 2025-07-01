package com.gafapay.elasticsearch.database.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(
   indexName = "daily_txn_summary"
)
@JsonIgnoreProperties(
   ignoreUnknown = true
)
public class DailyTxnSummary extends CommonFieldModel {
   @Id
   @Field("id")
   public String id;
   @Field(
      name = "date"
   )
   private Long date;
   @Field(
      name = "customer"
   )
   private Double customer;
   @Field(
      name = "merchant"
   )
   private Double merchant;
   @Field(
      name = "agent"
   )
   private Double agent;
   @Field(
      name = "currency_id"
   )
   private String currencyId;

   protected DailyTxnSummary(final DailyTxnSummaryBuilder<?, ?> b) {
      super(b);
      this.id = b.id;
      this.date = b.date;
      this.customer = b.customer;
      this.merchant = b.merchant;
      this.agent = b.agent;
      this.currencyId = b.currencyId;
   }

   public static DailyTxnSummaryBuilder<?, ?> builder() {
      return new DailyTxnSummaryBuilderImpl();
   }

   public String getId() {
      return this.id;
   }

   public Long getDate() {
      return this.date;
   }

   public Double getCustomer() {
      return this.customer;
   }

   public Double getMerchant() {
      return this.merchant;
   }

   public Double getAgent() {
      return this.agent;
   }

   public String getCurrencyId() {
      return this.currencyId;
   }

   public void setId(final String id) {
      this.id = id;
   }

   public void setDate(final Long date) {
      this.date = date;
   }

   public void setCustomer(final Double customer) {
      this.customer = customer;
   }

   public void setMerchant(final Double merchant) {
      this.merchant = merchant;
   }

   public void setAgent(final Double agent) {
      this.agent = agent;
   }

   public void setCurrencyId(final String currencyId) {
      this.currencyId = currencyId;
   }

   public DailyTxnSummary() {
   }

   public abstract static class DailyTxnSummaryBuilder<C extends DailyTxnSummary, B extends DailyTxnSummaryBuilder<C, B>> extends CommonFieldModel.CommonFieldModelBuilder<C, B> {
      private String id;
      private Long date;
      private Double customer;
      private Double merchant;
      private Double agent;
      private String currencyId;

      public DailyTxnSummaryBuilder() {
      }

      protected abstract B self();

      public abstract C build();

      public B id(final String id) {
         this.id = id;
         return (B)this.self();
      }

      public B date(final Long date) {
         this.date = date;
         return (B)this.self();
      }

      public B customer(final Double customer) {
         this.customer = customer;
         return (B)this.self();
      }

      public B merchant(final Double merchant) {
         this.merchant = merchant;
         return (B)this.self();
      }

      public B agent(final Double agent) {
         this.agent = agent;
         return (B)this.self();
      }

      public B currencyId(final String currencyId) {
         this.currencyId = currencyId;
         return (B)this.self();
      }

      public String toString() {
         String var10000 = super.toString();
         return "DailyTxnSummary.DailyTxnSummaryBuilder(super=" + var10000 + ", id=" + this.id + ", date=" + this.date + ", customer=" + this.customer + ", merchant=" + this.merchant + ", agent=" + this.agent + ", currencyId=" + this.currencyId + ")";
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

   private static final class DailyTxnSummaryBuilderImpl extends DailyTxnSummaryBuilder<DailyTxnSummary, DailyTxnSummaryBuilderImpl> {
      private DailyTxnSummaryBuilderImpl() {
      }

      protected DailyTxnSummaryBuilderImpl self() {
         return this;
      }

      public DailyTxnSummary build() {
         return new DailyTxnSummary(this);
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected DailyTxnSummaryBuilder self() {
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
