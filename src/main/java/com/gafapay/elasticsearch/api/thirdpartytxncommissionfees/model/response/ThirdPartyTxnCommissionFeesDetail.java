package com.gafapay.elasticsearch.api.thirdpartytxncommissionfees.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.gafapay.elasticsearch.database.model.ThirdPartyTxnCommissionFees;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class ThirdPartyTxnCommissionFeesDetail extends CommonAPIDataResponse {
   @JsonProperty("id")
   private String id;
   @JsonProperty("txn_id")
   private String txnId;
   @JsonProperty("type")
   private Integer type;
   @JsonProperty("type_key_code")
   private String typeKeyCode;
   @JsonProperty("type_key_info")
   private Map<String, Object> typeKeyInfo;
   @JsonProperty("contract_type")
   private Integer contractType;
   @JsonProperty("contract_type_amount")
   private Double contractTypeAmount;
   @JsonProperty("category_key")
   private String categoryKey;
   @JsonProperty("category_key_info")
   private Map<String, Object> categoryKeyInfo;
   @JsonProperty("provider_code")
   private String providerCode;
   @JsonProperty("provider_code_info")
   private Map<String, Object> providerCodeInfo;
   @JsonProperty("total_tax")
   private Double totalTax;
   @JsonProperty("tax_info")
   private List<Map<String, Object>> taxInfo;
   @JsonProperty("net_revenue")
   private Double netRevenue;
   @JsonProperty("company_share")
   private Double companyShare;
   @JsonProperty("partner_shares")
   private List<Map<String, Object>> partnerShares;
   @JsonProperty("is_active")
   private Boolean isActive;
   @JsonProperty("created_by")
   private String createdBy;
   @JsonProperty("created_date")
   private Long createdDate;
   @JsonProperty("updated_by")
   private String updatedBy;
   @JsonProperty("updated_date")
   private Long updatedDate;

   public static ThirdPartyTxnCommissionFeesDetail setThirdPartyTxnCommissionFees(ThirdPartyTxnCommissionFees thirdPartyTxnCommissionFees) {
      ThirdPartyTxnCommissionFeesDetail thirdPartyTxnCommissionFeesDetail = new ThirdPartyTxnCommissionFeesDetail();
      thirdPartyTxnCommissionFeesDetail.setId(thirdPartyTxnCommissionFees.getId());
      thirdPartyTxnCommissionFeesDetail.setTxnId(thirdPartyTxnCommissionFees.getTxnId());
      thirdPartyTxnCommissionFeesDetail.setType(thirdPartyTxnCommissionFees.getType());
      thirdPartyTxnCommissionFeesDetail.setTypeKeyCode(thirdPartyTxnCommissionFees.getTypeKeyCode());
      thirdPartyTxnCommissionFeesDetail.setTypeKeyInfo(thirdPartyTxnCommissionFees.getTypeKeyInfo());
      thirdPartyTxnCommissionFeesDetail.setContractType(thirdPartyTxnCommissionFees.getContractType());
      thirdPartyTxnCommissionFeesDetail.setContractTypeAmount(thirdPartyTxnCommissionFees.getContractTypeAmount());
      thirdPartyTxnCommissionFeesDetail.setCategoryKey(thirdPartyTxnCommissionFees.getCategoryKey());
      thirdPartyTxnCommissionFeesDetail.setCategoryKeyInfo(thirdPartyTxnCommissionFees.getCategoryKeyInfo());
      thirdPartyTxnCommissionFeesDetail.setProviderCode(thirdPartyTxnCommissionFees.getProviderCode());
      thirdPartyTxnCommissionFeesDetail.setProviderCodeInfo(thirdPartyTxnCommissionFees.getProviderCodeInfo());
      thirdPartyTxnCommissionFeesDetail.setTotalTax(thirdPartyTxnCommissionFees.getTotalTax());
      thirdPartyTxnCommissionFeesDetail.setTaxInfo(thirdPartyTxnCommissionFees.getTxnInfo());
      thirdPartyTxnCommissionFeesDetail.setNetRevenue(thirdPartyTxnCommissionFees.getNetRevenue());
      thirdPartyTxnCommissionFeesDetail.setCompanyShare(thirdPartyTxnCommissionFees.getCompanyShare());
      thirdPartyTxnCommissionFeesDetail.setPartnerShares(thirdPartyTxnCommissionFees.getPartnerShares());
      thirdPartyTxnCommissionFeesDetail.setIsActive(thirdPartyTxnCommissionFees.getIsActive());
      thirdPartyTxnCommissionFeesDetail.setCreatedDate(thirdPartyTxnCommissionFees.getCreatedDate());
      thirdPartyTxnCommissionFeesDetail.setCreatedBy(thirdPartyTxnCommissionFees.getCreatedBy());
      thirdPartyTxnCommissionFeesDetail.setUpdatedDate(thirdPartyTxnCommissionFees.getUpdatedDate());
      thirdPartyTxnCommissionFeesDetail.setUpdatedBy(thirdPartyTxnCommissionFees.getUpdatedBy());
      return thirdPartyTxnCommissionFeesDetail;
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

   public List<Map<String, Object>> getTaxInfo() {
      return this.taxInfo;
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

   public Boolean getIsActive() {
      return this.isActive;
   }

   public String getCreatedBy() {
      return this.createdBy;
   }

   public Long getCreatedDate() {
      return this.createdDate;
   }

   public String getUpdatedBy() {
      return this.updatedBy;
   }

   public Long getUpdatedDate() {
      return this.updatedDate;
   }

   @JsonProperty("id")
   public void setId(final String id) {
      this.id = id;
   }

   @JsonProperty("txn_id")
   public void setTxnId(final String txnId) {
      this.txnId = txnId;
   }

   @JsonProperty("type")
   public void setType(final Integer type) {
      this.type = type;
   }

   @JsonProperty("type_key_code")
   public void setTypeKeyCode(final String typeKeyCode) {
      this.typeKeyCode = typeKeyCode;
   }

   @JsonProperty("type_key_info")
   public void setTypeKeyInfo(final Map<String, Object> typeKeyInfo) {
      this.typeKeyInfo = typeKeyInfo;
   }

   @JsonProperty("contract_type")
   public void setContractType(final Integer contractType) {
      this.contractType = contractType;
   }

   @JsonProperty("contract_type_amount")
   public void setContractTypeAmount(final Double contractTypeAmount) {
      this.contractTypeAmount = contractTypeAmount;
   }

   @JsonProperty("category_key")
   public void setCategoryKey(final String categoryKey) {
      this.categoryKey = categoryKey;
   }

   @JsonProperty("category_key_info")
   public void setCategoryKeyInfo(final Map<String, Object> categoryKeyInfo) {
      this.categoryKeyInfo = categoryKeyInfo;
   }

   @JsonProperty("provider_code")
   public void setProviderCode(final String providerCode) {
      this.providerCode = providerCode;
   }

   @JsonProperty("provider_code_info")
   public void setProviderCodeInfo(final Map<String, Object> providerCodeInfo) {
      this.providerCodeInfo = providerCodeInfo;
   }

   @JsonProperty("total_tax")
   public void setTotalTax(final Double totalTax) {
      this.totalTax = totalTax;
   }

   @JsonProperty("tax_info")
   public void setTaxInfo(final List<Map<String, Object>> taxInfo) {
      this.taxInfo = taxInfo;
   }

   @JsonProperty("net_revenue")
   public void setNetRevenue(final Double netRevenue) {
      this.netRevenue = netRevenue;
   }

   @JsonProperty("company_share")
   public void setCompanyShare(final Double companyShare) {
      this.companyShare = companyShare;
   }

   @JsonProperty("partner_shares")
   public void setPartnerShares(final List<Map<String, Object>> partnerShares) {
      this.partnerShares = partnerShares;
   }

   @JsonProperty("is_active")
   public void setIsActive(final Boolean isActive) {
      this.isActive = isActive;
   }

   @JsonProperty("created_by")
   public void setCreatedBy(final String createdBy) {
      this.createdBy = createdBy;
   }

   @JsonProperty("created_date")
   public void setCreatedDate(final Long createdDate) {
      this.createdDate = createdDate;
   }

   @JsonProperty("updated_by")
   public void setUpdatedBy(final String updatedBy) {
      this.updatedBy = updatedBy;
   }

   @JsonProperty("updated_date")
   public void setUpdatedDate(final Long updatedDate) {
      this.updatedDate = updatedDate;
   }

   public ThirdPartyTxnCommissionFeesDetail() {
   }
}
