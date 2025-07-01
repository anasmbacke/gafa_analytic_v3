package com.gafapay.elasticsearch.api.txnmaster.model.response;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.gafapay.elasticsearch.api.txnmaster.model.TxnMaster;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class TxnData extends CommonAPIDataResponse {
   @JsonProperty("_id")
   private String id;
   @JsonProperty("txn_number")
   private String txnNumber;
   @JsonProperty("txn_code")
   private String txnCode;
   @JsonProperty("txn_type")
   private Integer txnType;
   @JsonProperty("txn_date")
   private Long txnDate;
   @JsonProperty("txn_status")
   private Integer txnStatus;
   @JsonProperty("note")
   private String note;
   @JsonProperty("display_text")
   private Map<String, Object> displayText;
   @JsonProperty("payment_mode")
   private Integer paymentMode;
   @JsonProperty("credit_account_type_id")
   public String creditAccountTypeId;
   @JsonProperty("credit_account_type")
   public Integer creditAccountType;
   @JsonProperty("credit_type_id")
   public String creditTypeId;
   @JsonProperty("credit_type")
   public Integer creditType;
   @JsonProperty("credit_amount")
   public Double creditAmount;
   @JsonProperty("credit_currency_id")
   public String creditCurrencyId;
   @JsonProperty("credit_currency")
   public Map<String, Object> creditCurrency;
   @JsonProperty("credit_account")
   public Map<String, Object> creditAccount;
   @JsonProperty("debit_account_type")
   public Integer debitAccountType;
   @JsonProperty("debit_account_type_id")
   public String debitAccountTypeId;
   @JsonProperty("debit_type_id")
   public String debitTypeId;
   @JsonProperty("debit_type")
   public Integer debitType;
   @JsonProperty("debit_amount")
   public Double debitAmount;
   @JsonProperty("debit_currency_id")
   public String debitCurrencyId;
   @JsonProperty("debit_currency")
   public Map<String, Object> debitCurrency;
   @JsonProperty("debit_account")
   public Map<String, Object> debitAccount;
   @JsonProperty("transaction_by")
   public Map<String, Object> transactionBy;
   @JsonProperty("metadata")
   public Map<String, Object> metadata;
   @JsonProperty("txn_charges")
   public List<Map<String, Object>> txnCharges;
   @JsonProperty("txn_commission")
   public List<Map<String, Object>> txnCommission;
   @JsonProperty("device_info")
   public Map<String, Object> deviceInfo;
   @JsonProperty("product_info")
   public Map<String, Object> productInfo;
   @JsonProperty("third_party_payment_info")
   public Map<String, Object> thirdPartyPaymentInfo;
   @JsonProperty("txn_amount")
   public Double txnAmount;
   @JsonProperty("sub_wallet_info")
   public Map<String, Object> subWalletInfo;
   @JsonProperty("bank_info")
   public Map<String, Object> bankInfo;
   @JsonProperty("debit_account_balance")
   public Double debitAccountBalance;
   @JsonProperty("credit_account_balance")
   public Double creditAccountBalance;
   @JsonProperty("reader_info")
   public Map<String, Object> readerInfo;
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

   public static TxnData setTxnMaster(TxnMaster txnMaster) {
      TxnData txnData = new TxnData();
      txnData.setId(txnMaster.getId());
      txnData.setTxnNumber(txnMaster.getTxnNumber());
      txnData.setTxnCode(txnMaster.getTxnCode());
      txnData.setTxnType(txnMaster.getTxnType());
      txnData.setTxnDate(txnMaster.getTxn_date());
      txnData.setTxnStatus(txnMaster.getTxnStatus());
      txnData.setNote(txnMaster.getNote());
      txnData.setDisplayText(txnMaster.getDisplay_text());
      txnData.setPaymentMode(txnMaster.getPayment_mode());
      txnData.setCreditAccountTypeId(txnMaster.getCreditAccountTypeId());
      txnData.setCreditAccountType(txnMaster.getCreditAccountType());
      txnData.setCreditTypeId(txnMaster.getCreditTypeId());
      txnData.setCreditType(txnMaster.getCreditType());
      txnData.setCreditAmount(txnMaster.getCreditAmount());
      txnData.setCreditAccount(txnMaster.getCreditAccount());
      txnData.setCreditCurrencyId(txnMaster.getCreditCurrencyId());
      txnData.setCreditCurrency(txnMaster.getCreditCurrency());
      txnData.setDebitAccountTypeId(txnMaster.getDebitAccountTypeId());
      txnData.setDebitAccountType(txnMaster.getDebitAccountType());
      txnData.setDebitTypeId(txnMaster.getDebitTypeId());
      txnData.setDebitType(txnMaster.getDebitType());
      txnData.setDebitAmount(txnMaster.getDebitAmount());
      txnData.setDebitCurrencyId(txnMaster.getDebitCurrencyId());
      txnData.setDebitCurrency(txnMaster.getDebitCurrency());
      txnData.setDebitAccount(txnMaster.getDebitAccount());
      txnData.setTransactionBy(txnMaster.getTransactionBy());
      txnData.setMetadata(txnMaster.getMetadata());
      txnData.setTxnCharges(txnMaster.getTxnCharges());
      txnData.setTxnCommission(txnMaster.getTxnCommissions());
      txnData.setDeviceInfo(txnMaster.getDeviceInfo());
      txnData.setProductInfo(txnMaster.getProductInfo());
      txnData.setThirdPartyPaymentInfo(txnMaster.getThirdPartyPaymentInfo());
      txnData.setTxnAmount(txnMaster.getTxnAmount());
      txnData.setSubWalletInfo(txnMaster.getSubWalletInfo());
      txnData.setBankInfo(txnMaster.getBankInfo());
      txnData.setDebitAccountBalance(txnMaster.getDebitAccountBalance());
      txnData.setCreditAccountBalance(txnMaster.getCreditAccountBalance());
      txnData.setReaderInfo(txnMaster.getReaderInfo());
      txnData.setIsActive(txnMaster.getIsActive());
      txnData.setCreatedDate(txnMaster.getCreatedDate());
      txnData.setCreatedBy(txnMaster.getCreatedBy());
      txnData.setUpdatedDate(txnMaster.getUpdatedDate());
      txnData.setUpdatedBy(txnMaster.getUpdatedBy());
      return txnData;
   }

   public String getId() {
      return this.id;
   }

   public String getTxnNumber() {
      return this.txnNumber;
   }

   public String getTxnCode() {
      return this.txnCode;
   }

   public Integer getTxnType() {
      return this.txnType;
   }

   public Long getTxnDate() {
      return this.txnDate;
   }

   public Integer getTxnStatus() {
      return this.txnStatus;
   }

   public String getNote() {
      return this.note;
   }

   public Map<String, Object> getDisplayText() {
      return this.displayText;
   }

   public Integer getPaymentMode() {
      return this.paymentMode;
   }

   public String getCreditAccountTypeId() {
      return this.creditAccountTypeId;
   }

   public Integer getCreditAccountType() {
      return this.creditAccountType;
   }

   public String getCreditTypeId() {
      return this.creditTypeId;
   }

   public Integer getCreditType() {
      return this.creditType;
   }

   public Double getCreditAmount() {
      return this.creditAmount;
   }

   public String getCreditCurrencyId() {
      return this.creditCurrencyId;
   }

   public Map<String, Object> getCreditCurrency() {
      return this.creditCurrency;
   }

   public Map<String, Object> getCreditAccount() {
      return this.creditAccount;
   }

   public Integer getDebitAccountType() {
      return this.debitAccountType;
   }

   public String getDebitAccountTypeId() {
      return this.debitAccountTypeId;
   }

   public String getDebitTypeId() {
      return this.debitTypeId;
   }

   public Integer getDebitType() {
      return this.debitType;
   }

   public Double getDebitAmount() {
      return this.debitAmount;
   }

   public String getDebitCurrencyId() {
      return this.debitCurrencyId;
   }

   public Map<String, Object> getDebitCurrency() {
      return this.debitCurrency;
   }

   public Map<String, Object> getDebitAccount() {
      return this.debitAccount;
   }

   public Map<String, Object> getTransactionBy() {
      return this.transactionBy;
   }

   public Map<String, Object> getMetadata() {
      return this.metadata;
   }

   public List<Map<String, Object>> getTxnCharges() {
      return this.txnCharges;
   }

   public List<Map<String, Object>> getTxnCommission() {
      return this.txnCommission;
   }

   public Map<String, Object> getDeviceInfo() {
      return this.deviceInfo;
   }

   public Map<String, Object> getProductInfo() {
      return this.productInfo;
   }

   public Map<String, Object> getThirdPartyPaymentInfo() {
      return this.thirdPartyPaymentInfo;
   }

   public Double getTxnAmount() {
      return this.txnAmount;
   }

   public Map<String, Object> getSubWalletInfo() {
      return this.subWalletInfo;
   }

   public Map<String, Object> getBankInfo() {
      return this.bankInfo;
   }

   public Double getDebitAccountBalance() {
      return this.debitAccountBalance;
   }

   public Double getCreditAccountBalance() {
      return this.creditAccountBalance;
   }

   public Map<String, Object> getReaderInfo() {
      return this.readerInfo;
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

   @JsonProperty("_id")
   public void setId(final String id) {
      this.id = id;
   }

   @JsonProperty("txn_number")
   public void setTxnNumber(final String txnNumber) {
      this.txnNumber = txnNumber;
   }

   @JsonProperty("txn_code")
   public void setTxnCode(final String txnCode) {
      this.txnCode = txnCode;
   }

   @JsonProperty("txn_type")
   public void setTxnType(final Integer txnType) {
      this.txnType = txnType;
   }

   @JsonProperty("txn_date")
   public void setTxnDate(final Long txnDate) {
      this.txnDate = txnDate;
   }

   @JsonProperty("txn_status")
   public void setTxnStatus(final Integer txnStatus) {
      this.txnStatus = txnStatus;
   }

   @JsonProperty("note")
   public void setNote(final String note) {
      this.note = note;
   }

   @JsonProperty("display_text")
   public void setDisplayText(final Map<String, Object> displayText) {
      this.displayText = displayText;
   }

   @JsonProperty("payment_mode")
   public void setPaymentMode(final Integer paymentMode) {
      this.paymentMode = paymentMode;
   }

   @JsonProperty("credit_account_type_id")
   public void setCreditAccountTypeId(final String creditAccountTypeId) {
      this.creditAccountTypeId = creditAccountTypeId;
   }

   @JsonProperty("credit_account_type")
   public void setCreditAccountType(final Integer creditAccountType) {
      this.creditAccountType = creditAccountType;
   }

   @JsonProperty("credit_type_id")
   public void setCreditTypeId(final String creditTypeId) {
      this.creditTypeId = creditTypeId;
   }

   @JsonProperty("credit_type")
   public void setCreditType(final Integer creditType) {
      this.creditType = creditType;
   }

   @JsonProperty("credit_amount")
   public void setCreditAmount(final Double creditAmount) {
      this.creditAmount = creditAmount;
   }

   @JsonProperty("credit_currency_id")
   public void setCreditCurrencyId(final String creditCurrencyId) {
      this.creditCurrencyId = creditCurrencyId;
   }

   @JsonProperty("credit_currency")
   public void setCreditCurrency(final Map<String, Object> creditCurrency) {
      this.creditCurrency = creditCurrency;
   }

   @JsonProperty("credit_account")
   public void setCreditAccount(final Map<String, Object> creditAccount) {
      this.creditAccount = creditAccount;
   }

   @JsonProperty("debit_account_type")
   public void setDebitAccountType(final Integer debitAccountType) {
      this.debitAccountType = debitAccountType;
   }

   @JsonProperty("debit_account_type_id")
   public void setDebitAccountTypeId(final String debitAccountTypeId) {
      this.debitAccountTypeId = debitAccountTypeId;
   }

   @JsonProperty("debit_type_id")
   public void setDebitTypeId(final String debitTypeId) {
      this.debitTypeId = debitTypeId;
   }

   @JsonProperty("debit_type")
   public void setDebitType(final Integer debitType) {
      this.debitType = debitType;
   }

   @JsonProperty("debit_amount")
   public void setDebitAmount(final Double debitAmount) {
      this.debitAmount = debitAmount;
   }

   @JsonProperty("debit_currency_id")
   public void setDebitCurrencyId(final String debitCurrencyId) {
      this.debitCurrencyId = debitCurrencyId;
   }

   @JsonProperty("debit_currency")
   public void setDebitCurrency(final Map<String, Object> debitCurrency) {
      this.debitCurrency = debitCurrency;
   }

   @JsonProperty("debit_account")
   public void setDebitAccount(final Map<String, Object> debitAccount) {
      this.debitAccount = debitAccount;
   }

   @JsonProperty("transaction_by")
   public void setTransactionBy(final Map<String, Object> transactionBy) {
      this.transactionBy = transactionBy;
   }

   @JsonProperty("metadata")
   public void setMetadata(final Map<String, Object> metadata) {
      this.metadata = metadata;
   }

   @JsonProperty("txn_charges")
   public void setTxnCharges(final List<Map<String, Object>> txnCharges) {
      this.txnCharges = txnCharges;
   }

   @JsonProperty("txn_commission")
   public void setTxnCommission(final List<Map<String, Object>> txnCommission) {
      this.txnCommission = txnCommission;
   }

   @JsonProperty("device_info")
   public void setDeviceInfo(final Map<String, Object> deviceInfo) {
      this.deviceInfo = deviceInfo;
   }

   @JsonProperty("product_info")
   public void setProductInfo(final Map<String, Object> productInfo) {
      this.productInfo = productInfo;
   }

   @JsonProperty("third_party_payment_info")
   public void setThirdPartyPaymentInfo(final Map<String, Object> thirdPartyPaymentInfo) {
      this.thirdPartyPaymentInfo = thirdPartyPaymentInfo;
   }

   @JsonProperty("txn_amount")
   public void setTxnAmount(final Double txnAmount) {
      this.txnAmount = txnAmount;
   }

   @JsonProperty("sub_wallet_info")
   public void setSubWalletInfo(final Map<String, Object> subWalletInfo) {
      this.subWalletInfo = subWalletInfo;
   }

   @JsonProperty("bank_info")
   public void setBankInfo(final Map<String, Object> bankInfo) {
      this.bankInfo = bankInfo;
   }

   @JsonProperty("debit_account_balance")
   public void setDebitAccountBalance(final Double debitAccountBalance) {
      this.debitAccountBalance = debitAccountBalance;
   }

   @JsonProperty("credit_account_balance")
   public void setCreditAccountBalance(final Double creditAccountBalance) {
      this.creditAccountBalance = creditAccountBalance;
   }

   @JsonProperty("reader_info")
   public void setReaderInfo(final Map<String, Object> readerInfo) {
      this.readerInfo = readerInfo;
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

   public TxnData() {
   }
}
