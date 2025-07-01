package com.gafapay.elasticsearch.api.txnmaster.model;

import com.gafapay.elasticsearch.database.model.ElasticSearchCommonFieldModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(
   indexName = "txn_master"
)
@JsonIgnoreProperties(
   ignoreUnknown = true
)
public class TxnMaster extends ElasticSearchCommonFieldModel {
   @Id
   @Field(
      name = "id"
   )
   public String id;
   @Field(
      name = "txn_number"
   )
   public String txnNumber;
   @Field(
      name = "credit_account_type_id"
   )
   public String creditAccountTypeId;
   @Field(
      name = "credit_account_type"
   )
   public Integer creditAccountType;
   @Field(
      name = "credit_type_id"
   )
   public String creditTypeId;
   @Field(
      name = "credit_type"
   )
   public Integer creditType;
   @Field(
      name = "credit_amount"
   )
   public Double creditAmount;
   @Field(
      name = "credit_currency_id"
   )
   public String creditCurrencyId;
   @Field(
      name = "credit_currency"
   )
   public Map<String, Object> creditCurrency;
   @Field(
      name = "credit_account"
   )
   public Map<String, Object> creditAccount;
   @Field(
      name = "debit_account_type"
   )
   public Integer debitAccountType;
   @Field(
      name = "debit_account_type_id"
   )
   public String debitAccountTypeId;
   @Field(
      name = "debit_type_id"
   )
   public String debitTypeId;
   @Field(
      name = "debit_type"
   )
   public Integer debitType;
   @Field(
      name = "debit_amount"
   )
   public Double debitAmount;
   @Field(
      name = "debit_currency_id"
   )
   public String debitCurrencyId;
   @Field(
      name = "debit_currency"
   )
   public Map<String, Object> debitCurrency;
   @Field(
      name = "debit_account"
   )
   public Map<String, Object> debitAccount;
   @Field(
      name = "transaction_by"
   )
   public Map<String, Object> transactionBy;
   @Field(
      name = "third_party_payment_info"
   )
   public Map<String, Object> thirdPartyPaymentInfo;
   @Field(
      name = "txn_code"
   )
   public String txnCode;
   @Field(
      name = "txn_type"
   )
   public Integer txnType;
   @Field(
      name = "txn_date",
      format = {DateFormat.epoch_millis},
      type = FieldType.Date
   )
   public Long txn_date;
   @Field(
      name = "txn_status"
   )
   public Integer txnStatus;
   @Field(
      name = "txn_amount"
   )
   public Double txnAmount;
   @Field(
      name = "credit_account_balance"
   )
   public Double creditAccountBalance;
   @Field(
      name = "debit_account_balance"
   )
   public Double debitAccountBalance;
   @Field(
      name = "internal_balance"
   )
   public Double internalBalance;
   @Field(
      name = "external_balance"
   )
   public Double externalBalance;
   @Field(
      name = "display_end_user"
   )
   public Boolean displayEndUser;
   @Field(
      name = "note"
   )
   public String note;
   @Field(
      name = "display_text"
   )
   public Map<String, Object> display_text;
   @Field(
      name = "payment_mode"
   )
   public Integer payment_mode;
   @Field(
      name = "metadata"
   )
   public Map<String, Object> metadata;
   @Field(
      name = "txn_charges",
      type = FieldType.Nested
   )
   public List<Map<String, Object>> txnCharges;
   @Field(
      name = "txn_commissions",
      type = FieldType.Nested
   )
   public List<Map<String, Object>> txnCommissions;
   @Field(
      name = "device_info"
   )
   public Map<String, Object> deviceInfo;
   @Field(
      name = "product_info"
   )
   public Map<String, Object> productInfo;
   @Field(
      name = "sub_wallet_info"
   )
   public Map<String, Object> subWalletInfo;
   @Field("bank_info")
   public Map<String, Object> bankInfo;
   @Field(
      name = "merchant_info"
   )
   public Map<String, Object> merchantInfo;
   @Field(
      name = "payment_master"
   )
   public Map<String, Object> paymentMaster;
   @Field(
      name = "payout_master"
   )
   public Map<String, Object> payoutMaster;
   @Field(
      name = "topup_wallet_master"
   )
   public Map<String, Object> topupWalletMaster;
   @Field(
      name = "transfer_master"
   )
   public Map<String, Object> transferMaster;
   @Field(
      name = "reader_info"
   )
   public Map<String, Object> readerInfo;

   protected TxnMaster(final TxnMasterBuilder<?, ?> b) {
      super(b);
      this.id = b.id;
      this.txnNumber = b.txnNumber;
      this.creditAccountTypeId = b.creditAccountTypeId;
      this.creditAccountType = b.creditAccountType;
      this.creditTypeId = b.creditTypeId;
      this.creditType = b.creditType;
      this.creditAmount = b.creditAmount;
      this.creditCurrencyId = b.creditCurrencyId;
      this.creditCurrency = b.creditCurrency;
      this.creditAccount = b.creditAccount;
      this.debitAccountType = b.debitAccountType;
      this.debitAccountTypeId = b.debitAccountTypeId;
      this.debitTypeId = b.debitTypeId;
      this.debitType = b.debitType;
      this.debitAmount = b.debitAmount;
      this.debitCurrencyId = b.debitCurrencyId;
      this.debitCurrency = b.debitCurrency;
      this.debitAccount = b.debitAccount;
      this.transactionBy = b.transactionBy;
      this.thirdPartyPaymentInfo = b.thirdPartyPaymentInfo;
      this.txnCode = b.txnCode;
      this.txnType = b.txnType;
      this.txn_date = b.txn_date;
      this.txnStatus = b.txnStatus;
      this.txnAmount = b.txnAmount;
      this.creditAccountBalance = b.creditAccountBalance;
      this.debitAccountBalance = b.debitAccountBalance;
      this.internalBalance = b.internalBalance;
      this.externalBalance = b.externalBalance;
      this.displayEndUser = b.displayEndUser;
      this.note = b.note;
      this.display_text = b.display_text;
      this.payment_mode = b.payment_mode;
      this.metadata = b.metadata;
      this.txnCharges = b.txnCharges;
      this.txnCommissions = b.txnCommissions;
      this.deviceInfo = b.deviceInfo;
      this.productInfo = b.productInfo;
      this.subWalletInfo = b.subWalletInfo;
      this.bankInfo = b.bankInfo;
      this.merchantInfo = b.merchantInfo;
      this.paymentMaster = b.paymentMaster;
      this.payoutMaster = b.payoutMaster;
      this.topupWalletMaster = b.topupWalletMaster;
      this.transferMaster = b.transferMaster;
      this.readerInfo = b.readerInfo;
   }

   public static TxnMasterBuilder<?, ?> builder() {
      return new TxnMasterBuilderImpl();
   }

   public String getId() {
      return this.id;
   }

   public String getTxnNumber() {
      return this.txnNumber;
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

   public Map<String, Object> getThirdPartyPaymentInfo() {
      return this.thirdPartyPaymentInfo;
   }

   public String getTxnCode() {
      return this.txnCode;
   }

   public Integer getTxnType() {
      return this.txnType;
   }

   public Long getTxn_date() {
      return this.txn_date;
   }

   public Integer getTxnStatus() {
      return this.txnStatus;
   }

   public Double getTxnAmount() {
      return this.txnAmount;
   }

   public Double getCreditAccountBalance() {
      return this.creditAccountBalance;
   }

   public Double getDebitAccountBalance() {
      return this.debitAccountBalance;
   }

   public Double getInternalBalance() {
      return this.internalBalance;
   }

   public Double getExternalBalance() {
      return this.externalBalance;
   }

   public Boolean getDisplayEndUser() {
      return this.displayEndUser;
   }

   public String getNote() {
      return this.note;
   }

   public Map<String, Object> getDisplay_text() {
      return this.display_text;
   }

   public Integer getPayment_mode() {
      return this.payment_mode;
   }

   public Map<String, Object> getMetadata() {
      return this.metadata;
   }

   public List<Map<String, Object>> getTxnCharges() {
      return this.txnCharges;
   }

   public List<Map<String, Object>> getTxnCommissions() {
      return this.txnCommissions;
   }

   public Map<String, Object> getDeviceInfo() {
      return this.deviceInfo;
   }

   public Map<String, Object> getProductInfo() {
      return this.productInfo;
   }

   public Map<String, Object> getSubWalletInfo() {
      return this.subWalletInfo;
   }

   public Map<String, Object> getBankInfo() {
      return this.bankInfo;
   }

   public Map<String, Object> getMerchantInfo() {
      return this.merchantInfo;
   }

   public Map<String, Object> getPaymentMaster() {
      return this.paymentMaster;
   }

   public Map<String, Object> getPayoutMaster() {
      return this.payoutMaster;
   }

   public Map<String, Object> getTopupWalletMaster() {
      return this.topupWalletMaster;
   }

   public Map<String, Object> getTransferMaster() {
      return this.transferMaster;
   }

   public Map<String, Object> getReaderInfo() {
      return this.readerInfo;
   }

   public void setId(final String id) {
      this.id = id;
   }

   public void setTxnNumber(final String txnNumber) {
      this.txnNumber = txnNumber;
   }

   public void setCreditAccountTypeId(final String creditAccountTypeId) {
      this.creditAccountTypeId = creditAccountTypeId;
   }

   public void setCreditAccountType(final Integer creditAccountType) {
      this.creditAccountType = creditAccountType;
   }

   public void setCreditTypeId(final String creditTypeId) {
      this.creditTypeId = creditTypeId;
   }

   public void setCreditType(final Integer creditType) {
      this.creditType = creditType;
   }

   public void setCreditAmount(final Double creditAmount) {
      this.creditAmount = creditAmount;
   }

   public void setCreditCurrencyId(final String creditCurrencyId) {
      this.creditCurrencyId = creditCurrencyId;
   }

   public void setCreditCurrency(final Map<String, Object> creditCurrency) {
      this.creditCurrency = creditCurrency;
   }

   public void setCreditAccount(final Map<String, Object> creditAccount) {
      this.creditAccount = creditAccount;
   }

   public void setDebitAccountType(final Integer debitAccountType) {
      this.debitAccountType = debitAccountType;
   }

   public void setDebitAccountTypeId(final String debitAccountTypeId) {
      this.debitAccountTypeId = debitAccountTypeId;
   }

   public void setDebitTypeId(final String debitTypeId) {
      this.debitTypeId = debitTypeId;
   }

   public void setDebitType(final Integer debitType) {
      this.debitType = debitType;
   }

   public void setDebitAmount(final Double debitAmount) {
      this.debitAmount = debitAmount;
   }

   public void setDebitCurrencyId(final String debitCurrencyId) {
      this.debitCurrencyId = debitCurrencyId;
   }

   public void setDebitCurrency(final Map<String, Object> debitCurrency) {
      this.debitCurrency = debitCurrency;
   }

   public void setDebitAccount(final Map<String, Object> debitAccount) {
      this.debitAccount = debitAccount;
   }

   public void setTransactionBy(final Map<String, Object> transactionBy) {
      this.transactionBy = transactionBy;
   }

   public void setThirdPartyPaymentInfo(final Map<String, Object> thirdPartyPaymentInfo) {
      this.thirdPartyPaymentInfo = thirdPartyPaymentInfo;
   }

   public void setTxnCode(final String txnCode) {
      this.txnCode = txnCode;
   }

   public void setTxnType(final Integer txnType) {
      this.txnType = txnType;
   }

   public void setTxn_date(final Long txn_date) {
      this.txn_date = txn_date;
   }

   public void setTxnStatus(final Integer txnStatus) {
      this.txnStatus = txnStatus;
   }

   public void setTxnAmount(final Double txnAmount) {
      this.txnAmount = txnAmount;
   }

   public void setCreditAccountBalance(final Double creditAccountBalance) {
      this.creditAccountBalance = creditAccountBalance;
   }

   public void setDebitAccountBalance(final Double debitAccountBalance) {
      this.debitAccountBalance = debitAccountBalance;
   }

   public void setInternalBalance(final Double internalBalance) {
      this.internalBalance = internalBalance;
   }

   public void setExternalBalance(final Double externalBalance) {
      this.externalBalance = externalBalance;
   }

   public void setDisplayEndUser(final Boolean displayEndUser) {
      this.displayEndUser = displayEndUser;
   }

   public void setNote(final String note) {
      this.note = note;
   }

   public void setDisplay_text(final Map<String, Object> display_text) {
      this.display_text = display_text;
   }

   public void setPayment_mode(final Integer payment_mode) {
      this.payment_mode = payment_mode;
   }

   public void setMetadata(final Map<String, Object> metadata) {
      this.metadata = metadata;
   }

   public void setTxnCharges(final List<Map<String, Object>> txnCharges) {
      this.txnCharges = txnCharges;
   }

   public void setTxnCommissions(final List<Map<String, Object>> txnCommissions) {
      this.txnCommissions = txnCommissions;
   }

   public void setDeviceInfo(final Map<String, Object> deviceInfo) {
      this.deviceInfo = deviceInfo;
   }

   public void setProductInfo(final Map<String, Object> productInfo) {
      this.productInfo = productInfo;
   }

   public void setSubWalletInfo(final Map<String, Object> subWalletInfo) {
      this.subWalletInfo = subWalletInfo;
   }

   public void setBankInfo(final Map<String, Object> bankInfo) {
      this.bankInfo = bankInfo;
   }

   public void setMerchantInfo(final Map<String, Object> merchantInfo) {
      this.merchantInfo = merchantInfo;
   }

   public void setPaymentMaster(final Map<String, Object> paymentMaster) {
      this.paymentMaster = paymentMaster;
   }

   public void setPayoutMaster(final Map<String, Object> payoutMaster) {
      this.payoutMaster = payoutMaster;
   }

   public void setTopupWalletMaster(final Map<String, Object> topupWalletMaster) {
      this.topupWalletMaster = topupWalletMaster;
   }

   public void setTransferMaster(final Map<String, Object> transferMaster) {
      this.transferMaster = transferMaster;
   }

   public void setReaderInfo(final Map<String, Object> readerInfo) {
      this.readerInfo = readerInfo;
   }

   public TxnMaster() {
   }

   public abstract static class TxnMasterBuilder<C extends TxnMaster, B extends TxnMasterBuilder<C, B>> extends ElasticSearchCommonFieldModel.ElasticSearchCommonFieldModelBuilder<C, B> {
      private String id;
      private String txnNumber;
      private String creditAccountTypeId;
      private Integer creditAccountType;
      private String creditTypeId;
      private Integer creditType;
      private Double creditAmount;
      private String creditCurrencyId;
      private Map<String, Object> creditCurrency;
      private Map<String, Object> creditAccount;
      private Integer debitAccountType;
      private String debitAccountTypeId;
      private String debitTypeId;
      private Integer debitType;
      private Double debitAmount;
      private String debitCurrencyId;
      private Map<String, Object> debitCurrency;
      private Map<String, Object> debitAccount;
      private Map<String, Object> transactionBy;
      private Map<String, Object> thirdPartyPaymentInfo;
      private String txnCode;
      private Integer txnType;
      private Long txn_date;
      private Integer txnStatus;
      private Double txnAmount;
      private Double creditAccountBalance;
      private Double debitAccountBalance;
      private Double internalBalance;
      private Double externalBalance;
      private Boolean displayEndUser;
      private String note;
      private Map<String, Object> display_text;
      private Integer payment_mode;
      private Map<String, Object> metadata;
      private List<Map<String, Object>> txnCharges;
      private List<Map<String, Object>> txnCommissions;
      private Map<String, Object> deviceInfo;
      private Map<String, Object> productInfo;
      private Map<String, Object> subWalletInfo;
      private Map<String, Object> bankInfo;
      private Map<String, Object> merchantInfo;
      private Map<String, Object> paymentMaster;
      private Map<String, Object> payoutMaster;
      private Map<String, Object> topupWalletMaster;
      private Map<String, Object> transferMaster;
      private Map<String, Object> readerInfo;

      public TxnMasterBuilder() {
      }

      protected abstract B self();

      public abstract C build();

      public B id(final String id) {
         this.id = id;
         return (B)this.self();
      }

      public B txnNumber(final String txnNumber) {
         this.txnNumber = txnNumber;
         return (B)this.self();
      }

      public B creditAccountTypeId(final String creditAccountTypeId) {
         this.creditAccountTypeId = creditAccountTypeId;
         return (B)this.self();
      }

      public B creditAccountType(final Integer creditAccountType) {
         this.creditAccountType = creditAccountType;
         return (B)this.self();
      }

      public B creditTypeId(final String creditTypeId) {
         this.creditTypeId = creditTypeId;
         return (B)this.self();
      }

      public B creditType(final Integer creditType) {
         this.creditType = creditType;
         return (B)this.self();
      }

      public B creditAmount(final Double creditAmount) {
         this.creditAmount = creditAmount;
         return (B)this.self();
      }

      public B creditCurrencyId(final String creditCurrencyId) {
         this.creditCurrencyId = creditCurrencyId;
         return (B)this.self();
      }

      public B creditCurrency(final Map<String, Object> creditCurrency) {
         this.creditCurrency = creditCurrency;
         return (B)this.self();
      }

      public B creditAccount(final Map<String, Object> creditAccount) {
         this.creditAccount = creditAccount;
         return (B)this.self();
      }

      public B debitAccountType(final Integer debitAccountType) {
         this.debitAccountType = debitAccountType;
         return (B)this.self();
      }

      public B debitAccountTypeId(final String debitAccountTypeId) {
         this.debitAccountTypeId = debitAccountTypeId;
         return (B)this.self();
      }

      public B debitTypeId(final String debitTypeId) {
         this.debitTypeId = debitTypeId;
         return (B)this.self();
      }

      public B debitType(final Integer debitType) {
         this.debitType = debitType;
         return (B)this.self();
      }

      public B debitAmount(final Double debitAmount) {
         this.debitAmount = debitAmount;
         return (B)this.self();
      }

      public B debitCurrencyId(final String debitCurrencyId) {
         this.debitCurrencyId = debitCurrencyId;
         return (B)this.self();
      }

      public B debitCurrency(final Map<String, Object> debitCurrency) {
         this.debitCurrency = debitCurrency;
         return (B)this.self();
      }

      public B debitAccount(final Map<String, Object> debitAccount) {
         this.debitAccount = debitAccount;
         return (B)this.self();
      }

      public B transactionBy(final Map<String, Object> transactionBy) {
         this.transactionBy = transactionBy;
         return (B)this.self();
      }

      public B thirdPartyPaymentInfo(final Map<String, Object> thirdPartyPaymentInfo) {
         this.thirdPartyPaymentInfo = thirdPartyPaymentInfo;
         return (B)this.self();
      }

      public B txnCode(final String txnCode) {
         this.txnCode = txnCode;
         return (B)this.self();
      }

      public B txnType(final Integer txnType) {
         this.txnType = txnType;
         return (B)this.self();
      }

      public B txn_date(final Long txn_date) {
         this.txn_date = txn_date;
         return (B)this.self();
      }

      public B txnStatus(final Integer txnStatus) {
         this.txnStatus = txnStatus;
         return (B)this.self();
      }

      public B txnAmount(final Double txnAmount) {
         this.txnAmount = txnAmount;
         return (B)this.self();
      }

      public B creditAccountBalance(final Double creditAccountBalance) {
         this.creditAccountBalance = creditAccountBalance;
         return (B)this.self();
      }

      public B debitAccountBalance(final Double debitAccountBalance) {
         this.debitAccountBalance = debitAccountBalance;
         return (B)this.self();
      }

      public B internalBalance(final Double internalBalance) {
         this.internalBalance = internalBalance;
         return (B)this.self();
      }

      public B externalBalance(final Double externalBalance) {
         this.externalBalance = externalBalance;
         return (B)this.self();
      }

      public B displayEndUser(final Boolean displayEndUser) {
         this.displayEndUser = displayEndUser;
         return (B)this.self();
      }

      public B note(final String note) {
         this.note = note;
         return (B)this.self();
      }

      public B display_text(final Map<String, Object> display_text) {
         this.display_text = display_text;
         return (B)this.self();
      }

      public B payment_mode(final Integer payment_mode) {
         this.payment_mode = payment_mode;
         return (B)this.self();
      }

      public B metadata(final Map<String, Object> metadata) {
         this.metadata = metadata;
         return (B)this.self();
      }

      public B txnCharges(final List<Map<String, Object>> txnCharges) {
         this.txnCharges = txnCharges;
         return (B)this.self();
      }

      public B txnCommissions(final List<Map<String, Object>> txnCommissions) {
         this.txnCommissions = txnCommissions;
         return (B)this.self();
      }

      public B deviceInfo(final Map<String, Object> deviceInfo) {
         this.deviceInfo = deviceInfo;
         return (B)this.self();
      }

      public B productInfo(final Map<String, Object> productInfo) {
         this.productInfo = productInfo;
         return (B)this.self();
      }

      public B subWalletInfo(final Map<String, Object> subWalletInfo) {
         this.subWalletInfo = subWalletInfo;
         return (B)this.self();
      }

      public B bankInfo(final Map<String, Object> bankInfo) {
         this.bankInfo = bankInfo;
         return (B)this.self();
      }

      public B merchantInfo(final Map<String, Object> merchantInfo) {
         this.merchantInfo = merchantInfo;
         return (B)this.self();
      }

      public B paymentMaster(final Map<String, Object> paymentMaster) {
         this.paymentMaster = paymentMaster;
         return (B)this.self();
      }

      public B payoutMaster(final Map<String, Object> payoutMaster) {
         this.payoutMaster = payoutMaster;
         return (B)this.self();
      }

      public B topupWalletMaster(final Map<String, Object> topupWalletMaster) {
         this.topupWalletMaster = topupWalletMaster;
         return (B)this.self();
      }

      public B transferMaster(final Map<String, Object> transferMaster) {
         this.transferMaster = transferMaster;
         return (B)this.self();
      }

      public B readerInfo(final Map<String, Object> readerInfo) {
         this.readerInfo = readerInfo;
         return (B)this.self();
      }

      public String toString() {
         String var10000 = super.toString();
         return "TxnMaster.TxnMasterBuilder(super=" + var10000 + ", id=" + this.id + ", txnNumber=" + this.txnNumber + ", creditAccountTypeId=" + this.creditAccountTypeId + ", creditAccountType=" + this.creditAccountType + ", creditTypeId=" + this.creditTypeId + ", creditType=" + this.creditType + ", creditAmount=" + this.creditAmount + ", creditCurrencyId=" + this.creditCurrencyId + ", creditCurrency=" + this.creditCurrency + ", creditAccount=" + this.creditAccount + ", debitAccountType=" + this.debitAccountType + ", debitAccountTypeId=" + this.debitAccountTypeId + ", debitTypeId=" + this.debitTypeId + ", debitType=" + this.debitType + ", debitAmount=" + this.debitAmount + ", debitCurrencyId=" + this.debitCurrencyId + ", debitCurrency=" + this.debitCurrency + ", debitAccount=" + this.debitAccount + ", transactionBy=" + this.transactionBy + ", thirdPartyPaymentInfo=" + this.thirdPartyPaymentInfo + ", txnCode=" + this.txnCode + ", txnType=" + this.txnType + ", txn_date=" + this.txn_date + ", txnStatus=" + this.txnStatus + ", txnAmount=" + this.txnAmount + ", creditAccountBalance=" + this.creditAccountBalance + ", debitAccountBalance=" + this.debitAccountBalance + ", internalBalance=" + this.internalBalance + ", externalBalance=" + this.externalBalance + ", displayEndUser=" + this.displayEndUser + ", note=" + this.note + ", display_text=" + this.display_text + ", payment_mode=" + this.payment_mode + ", metadata=" + this.metadata + ", txnCharges=" + this.txnCharges + ", txnCommissions=" + this.txnCommissions + ", deviceInfo=" + this.deviceInfo + ", productInfo=" + this.productInfo + ", subWalletInfo=" + this.subWalletInfo + ", bankInfo=" + this.bankInfo + ", merchantInfo=" + this.merchantInfo + ", paymentMaster=" + this.paymentMaster + ", payoutMaster=" + this.payoutMaster + ", topupWalletMaster=" + this.topupWalletMaster + ", transferMaster=" + this.transferMaster + ", readerInfo=" + this.readerInfo + ")";
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

   private static final class TxnMasterBuilderImpl extends TxnMasterBuilder<TxnMaster, TxnMasterBuilderImpl> {
      private TxnMasterBuilderImpl() {
      }

      protected TxnMasterBuilderImpl self() {
         return this;
      }

      public TxnMaster build() {
         return new TxnMaster(this);
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected TxnMasterBuilder self() {
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
