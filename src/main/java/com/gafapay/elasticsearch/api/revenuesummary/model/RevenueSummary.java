package com.gafapay.elasticsearch.api.revenuesummary.model;

import com.gafapay.elasticsearch.database.model.ElasticSearchCommonFieldModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(
   indexName = "revenue_summary"
)
@JsonIgnoreProperties(
   ignoreUnknown = true
)
public class RevenueSummary extends ElasticSearchCommonFieldModel {
   @Id
   @Field("id")
   public String id;
   @Field("summary_date")
   private Long summaryDate;
   @Field("total_revenue")
   private Double totalRevenue;
   @Field("revenue_detail_summary")
   private List<Map<String, Object>> revenueDetailSummary;

   protected RevenueSummary(final RevenueSummaryBuilder<?, ?> b) {
      super(b);
      this.id = b.id;
      this.summaryDate = b.summaryDate;
      this.totalRevenue = b.totalRevenue;
      this.revenueDetailSummary = b.revenueDetailSummary;
   }

   public static RevenueSummaryBuilder<?, ?> builder() {
      return new RevenueSummaryBuilderImpl();
   }

   public String getId() {
      return this.id;
   }

   public Long getSummaryDate() {
      return this.summaryDate;
   }

   public Double getTotalRevenue() {
      return this.totalRevenue;
   }

   public List<Map<String, Object>> getRevenueDetailSummary() {
      return this.revenueDetailSummary;
   }

   public void setId(final String id) {
      this.id = id;
   }

   public void setSummaryDate(final Long summaryDate) {
      this.summaryDate = summaryDate;
   }

   public void setTotalRevenue(final Double totalRevenue) {
      this.totalRevenue = totalRevenue;
   }

   public void setRevenueDetailSummary(final List<Map<String, Object>> revenueDetailSummary) {
      this.revenueDetailSummary = revenueDetailSummary;
   }

   public RevenueSummary() {
   }

   public abstract static class RevenueSummaryBuilder<C extends RevenueSummary, B extends RevenueSummaryBuilder<C, B>> extends ElasticSearchCommonFieldModel.ElasticSearchCommonFieldModelBuilder<C, B> {
      private String id;
      private Long summaryDate;
      private Double totalRevenue;
      private List<Map<String, Object>> revenueDetailSummary;

      public RevenueSummaryBuilder() {
      }

      protected abstract B self();

      public abstract C build();

      public B id(final String id) {
         this.id = id;
         return (B)this.self();
      }

      public B summaryDate(final Long summaryDate) {
         this.summaryDate = summaryDate;
         return (B)this.self();
      }

      public B totalRevenue(final Double totalRevenue) {
         this.totalRevenue = totalRevenue;
         return (B)this.self();
      }

      public B revenueDetailSummary(final List<Map<String, Object>> revenueDetailSummary) {
         this.revenueDetailSummary = revenueDetailSummary;
         return (B)this.self();
      }

      public String toString() {
         String var10000 = super.toString();
         return "RevenueSummary.RevenueSummaryBuilder(super=" + var10000 + ", id=" + this.id + ", summaryDate=" + this.summaryDate + ", totalRevenue=" + this.totalRevenue + ", revenueDetailSummary=" + this.revenueDetailSummary + ")";
      }

      // $FF: synthetic method
      // $FF: bridge method
      public ElasticSearchCommonFieldModel build() {
         return this.build();
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected ElasticSearchCommonFieldModel.ElasticSearchCommonFieldModelBuilder self() {
         return this.self();
      }
   }

   private static final class RevenueSummaryBuilderImpl extends RevenueSummaryBuilder<RevenueSummary, RevenueSummaryBuilderImpl> {
      private RevenueSummaryBuilderImpl() {
      }

      protected RevenueSummaryBuilderImpl self() {
         return this;
      }

      public RevenueSummary build() {
         return new RevenueSummary(this);
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected RevenueSummaryBuilder self() {
         return this.self();
      }

      // $FF: synthetic method
      // $FF: bridge method
      public ElasticSearchCommonFieldModel build() {
         return this.build();
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected ElasticSearchCommonFieldModel.ElasticSearchCommonFieldModelBuilder self() {
         return this.self();
      }
   }
}
