package com.gafapay.elasticsearch.database.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(
   indexName = "third_party_txn_commission_fees"
)
@JsonIgnoreProperties(
   ignoreUnknown = true
)
public class ThirdPartyTxnCommissionFees extends ElasticSearchCommonFieldModel {
   @Id
   @Field(
      name = "id"
   )
   public String id;
   @Field(
      name = "txn_id"
   )
   public String txnId;
   @Field(
      name = "type"
   )
   public Integer type;
   @Field(
      name = "type_key_code"
   )
   public String typeKeyCode;
   @Field(
      name = "type_key_info"
   )
   public Map<String, Object> typeKeyInfo;
   @Field(
      name = "contract_type"
   )
   public Integer contractType;
   @Field(
      name = "contract_type_amount"
   )
   public Double contractTypeAmount;
   @Field(
      name = "category_key"
   )
   public String categoryKey;
   @Field(
      name = "category_key_info"
   )
   public Map<String, Object> categoryKeyInfo;
   @Field(
      name = "provider_code"
   )
   public String providerCode;
   @Field(
      name = "provider_code_info"
   )
   public Map<String, Object> providerCodeInfo;
   @Field(
      name = "total_tax"
   )
   public Double totalTax;
   @Field(
      name = "tax_info",
      type = FieldType.Nested
   )
   public List<Map<String, Object>> txnInfo;
   @Field(
      name = "net_revenue"
   )
   public Double netRevenue;
   @Field(
      name = "company_share"
   )
   public Double companyShare;
   @Field(
      name = "partner_shares",
      type = FieldType.Nested
   )
   public List<Map<String, Object>> partnerShares;

   protected ThirdPartyTxnCommissionFees(final ThirdPartyTxnCommissionFeesBuilder<?, ?> b) {
      super(b);
      this.id = b.id;
      this.txnId = b.txnId;
      this.type = b.type;
      this.typeKeyCode = b.typeKeyCode;
      this.typeKeyInfo = b.typeKeyInfo;
      this.contractType = b.contractType;
      this.contractTypeAmount = b.contractTypeAmount;
      this.categoryKey = b.categoryKey;
      this.categoryKeyInfo = b.categoryKeyInfo;
      this.providerCode = b.providerCode;
      this.providerCodeInfo = b.providerCodeInfo;
      this.totalTax = b.totalTax;
      this.txnInfo = b.txnInfo;
      this.netRevenue = b.netRevenue;
      this.companyShare = b.companyShare;
      this.partnerShares = b.partnerShares;
   }

   public static ThirdPartyTxnCommissionFeesBuilder<?, ?> builder() {
      return new ThirdPartyTxnCommissionFeesBuilderImpl();
   }

   public String getId() {
      return this.id;
   }

   public String getTxnId() {
      return this.txnId;
   }

   public Integer getType() {
      return this.type;
   }

   public String getTypeKeyCode() {
      return this.typeKeyCode;
   }

   public Map<String, Object> getTypeKeyInfo() {
      return this.typeKeyInfo;
   }

   public Integer getContractType() {
      return this.contractType;
   }

   public Double getContractTypeAmount() {
      return this.contractTypeAmount;
   }

   public String getCategoryKey() {
      return this.categoryKey;
   }

   public Map<String, Object> getCategoryKeyInfo() {
      return this.categoryKeyInfo;
   }

   public String getProviderCode() {
      return this.providerCode;
   }

   public Map<String, Object> getProviderCodeInfo() {
      return this.providerCodeInfo;
   }

   public Double getTotalTax() {
      return this.totalTax;
   }

   public List<Map<String, Object>> getTxnInfo() {
      return this.txnInfo;
   }

   public Double getNetRevenue() {
      return this.netRevenue;
   }

   public Double getCompanyShare() {
      return this.companyShare;
   }

   public List<Map<String, Object>> getPartnerShares() {
      return this.partnerShares;
   }

   public void setId(final String id) {
      this.id = id;
   }

   public void setTxnId(final String txnId) {
      this.txnId = txnId;
   }

   public void setType(final Integer type) {
      this.type = type;
   }

   public void setTypeKeyCode(final String typeKeyCode) {
      this.typeKeyCode = typeKeyCode;
   }

   public void setTypeKeyInfo(final Map<String, Object> typeKeyInfo) {
      this.typeKeyInfo = typeKeyInfo;
   }

   public void setContractType(final Integer contractType) {
      this.contractType = contractType;
   }

   public void setContractTypeAmount(final Double contractTypeAmount) {
      this.contractTypeAmount = contractTypeAmount;
   }

   public void setCategoryKey(final String categoryKey) {
      this.categoryKey = categoryKey;
   }

   public void setCategoryKeyInfo(final Map<String, Object> categoryKeyInfo) {
      this.categoryKeyInfo = categoryKeyInfo;
   }

   public void setProviderCode(final String providerCode) {
      this.providerCode = providerCode;
   }

   public void setProviderCodeInfo(final Map<String, Object> providerCodeInfo) {
      this.providerCodeInfo = providerCodeInfo;
   }

   public void setTotalTax(final Double totalTax) {
      this.totalTax = totalTax;
   }

   public void setTxnInfo(final List<Map<String, Object>> txnInfo) {
      this.txnInfo = txnInfo;
   }

   public void setNetRevenue(final Double netRevenue) {
      this.netRevenue = netRevenue;
   }

   public void setCompanyShare(final Double companyShare) {
      this.companyShare = companyShare;
   }

   public void setPartnerShares(final List<Map<String, Object>> partnerShares) {
      this.partnerShares = partnerShares;
   }

   public ThirdPartyTxnCommissionFees() {
   }

   public abstract static class ThirdPartyTxnCommissionFeesBuilder<C extends ThirdPartyTxnCommissionFees, B extends ThirdPartyTxnCommissionFeesBuilder<C, B>> extends ElasticSearchCommonFieldModel.ElasticSearchCommonFieldModelBuilder<C, B> {
      private String id;
      private String txnId;
      private Integer type;
      private String typeKeyCode;
      private Map<String, Object> typeKeyInfo;
      private Integer contractType;
      private Double contractTypeAmount;
      private String categoryKey;
      private Map<String, Object> categoryKeyInfo;
      private String providerCode;
      private Map<String, Object> providerCodeInfo;
      private Double totalTax;
      private List<Map<String, Object>> txnInfo;
      private Double netRevenue;
      private Double companyShare;
      private List<Map<String, Object>> partnerShares;

      public ThirdPartyTxnCommissionFeesBuilder() {
      }

      protected abstract B self();

      public abstract C build();

      public B id(final String id) {
         this.id = id;
         return (B)this.self();
      }

      public B txnId(final String txnId) {
         this.txnId = txnId;
         return (B)this.self();
      }

      public B type(final Integer type) {
         this.type = type;
         return (B)this.self();
      }

      public B typeKeyCode(final String typeKeyCode) {
         this.typeKeyCode = typeKeyCode;
         return (B)this.self();
      }

      public B typeKeyInfo(final Map<String, Object> typeKeyInfo) {
         this.typeKeyInfo = typeKeyInfo;
         return (B)this.self();
      }

      public B contractType(final Integer contractType) {
         this.contractType = contractType;
         return (B)this.self();
      }

      public B contractTypeAmount(final Double contractTypeAmount) {
         this.contractTypeAmount = contractTypeAmount;
         return (B)this.self();
      }

      public B categoryKey(final String categoryKey) {
         this.categoryKey = categoryKey;
         return (B)this.self();
      }

      public B categoryKeyInfo(final Map<String, Object> categoryKeyInfo) {
         this.categoryKeyInfo = categoryKeyInfo;
         return (B)this.self();
      }

      public B providerCode(final String providerCode) {
         this.providerCode = providerCode;
         return (B)this.self();
      }

      public B providerCodeInfo(final Map<String, Object> providerCodeInfo) {
         this.providerCodeInfo = providerCodeInfo;
         return (B)this.self();
      }

      public B totalTax(final Double totalTax) {
         this.totalTax = totalTax;
         return (B)this.self();
      }

      public B txnInfo(final List<Map<String, Object>> txnInfo) {
         this.txnInfo = txnInfo;
         return (B)this.self();
      }

      public B netRevenue(final Double netRevenue) {
         this.netRevenue = netRevenue;
         return (B)this.self();
      }

      public B companyShare(final Double companyShare) {
         this.companyShare = companyShare;
         return (B)this.self();
      }

      public B partnerShares(final List<Map<String, Object>> partnerShares) {
         this.partnerShares = partnerShares;
         return (B)this.self();
      }

      public String toString() {
         String var10000 = super.toString();
         return "ThirdPartyTxnCommissionFees.ThirdPartyTxnCommissionFeesBuilder(super=" + var10000 + ", id=" + this.id + ", txnId=" + this.txnId + ", type=" + this.type + ", typeKeyCode=" + this.typeKeyCode + ", typeKeyInfo=" + this.typeKeyInfo + ", contractType=" + this.contractType + ", contractTypeAmount=" + this.contractTypeAmount + ", categoryKey=" + this.categoryKey + ", categoryKeyInfo=" + this.categoryKeyInfo + ", providerCode=" + this.providerCode + ", providerCodeInfo=" + this.providerCodeInfo + ", totalTax=" + this.totalTax + ", txnInfo=" + this.txnInfo + ", netRevenue=" + this.netRevenue + ", companyShare=" + this.companyShare + ", partnerShares=" + this.partnerShares + ")";
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

   private static final class ThirdPartyTxnCommissionFeesBuilderImpl extends ThirdPartyTxnCommissionFeesBuilder<ThirdPartyTxnCommissionFees, ThirdPartyTxnCommissionFeesBuilderImpl> {
      private ThirdPartyTxnCommissionFeesBuilderImpl() {
      }

      protected ThirdPartyTxnCommissionFeesBuilderImpl self() {
         return this;
      }

      public ThirdPartyTxnCommissionFees build() {
         return new ThirdPartyTxnCommissionFees(this);
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected ThirdPartyTxnCommissionFeesBuilder self() {
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
