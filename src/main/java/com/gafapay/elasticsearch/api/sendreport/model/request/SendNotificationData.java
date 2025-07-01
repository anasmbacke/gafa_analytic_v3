package com.gafapay.elasticsearch.api.sendreport.model.request;

import java.util.Map;

public class SendNotificationData {
   private String company_id;
   private String user_id;
   private String to_user_id;
   private String title;
   private String otp;
   private String txn_code;
   private String txn_id;
   private String txn_number;
   private String txn_status;
   private Double txn_amount;
   private Double to_user_txn_amount;
   private Double charge_amount;
   private String note;
   private String service_type;
   private String transaction_time;
   private String reason;
   private String business_name;
   private String country_id;
   private Integer event_type;
   private String currency_id;
   private String to_currency_id;
   private String wallet_id;
   private String to_user_wallet_id;
   private String reference_number;
   private Integer days;
   private Integer count;
   private Long date;
   private Double net_amount;
   private String dial_code;
   private String phone_number;
   private Integer to_user_type;
   private Integer user_type;
   private String link;
   private Map<String, Object> meta_data;
   private Double balance;
   private String sender_name;
   private String file_attached;

   SendNotificationData(final String company_id, final String user_id, final String to_user_id, final String title, final String otp, final String txn_code, final String txn_id, final String txn_number, final String txn_status, final Double txn_amount, final Double to_user_txn_amount, final Double charge_amount, final String note, final String service_type, final String transaction_time, final String reason, final String business_name, final String country_id, final Integer event_type, final String currency_id, final String to_currency_id, final String wallet_id, final String to_user_wallet_id, final String reference_number, final Integer days, final Integer count, final Long date, final Double net_amount, final String dial_code, final String phone_number, final Integer to_user_type, final Integer user_type, final String link, final Map<String, Object> meta_data, final Double balance, final String sender_name, final String file_attached) {
      this.company_id = company_id;
      this.user_id = user_id;
      this.to_user_id = to_user_id;
      this.title = title;
      this.otp = otp;
      this.txn_code = txn_code;
      this.txn_id = txn_id;
      this.txn_number = txn_number;
      this.txn_status = txn_status;
      this.txn_amount = txn_amount;
      this.to_user_txn_amount = to_user_txn_amount;
      this.charge_amount = charge_amount;
      this.note = note;
      this.service_type = service_type;
      this.transaction_time = transaction_time;
      this.reason = reason;
      this.business_name = business_name;
      this.country_id = country_id;
      this.event_type = event_type;
      this.currency_id = currency_id;
      this.to_currency_id = to_currency_id;
      this.wallet_id = wallet_id;
      this.to_user_wallet_id = to_user_wallet_id;
      this.reference_number = reference_number;
      this.days = days;
      this.count = count;
      this.date = date;
      this.net_amount = net_amount;
      this.dial_code = dial_code;
      this.phone_number = phone_number;
      this.to_user_type = to_user_type;
      this.user_type = user_type;
      this.link = link;
      this.meta_data = meta_data;
      this.balance = balance;
      this.sender_name = sender_name;
      this.file_attached = file_attached;
   }

   public static SendNotificationDataBuilder builder() {
      return new SendNotificationDataBuilder();
   }

   public String getCompany_id() {
      return this.company_id;
   }

   public String getUser_id() {
      return this.user_id;
   }

   public String getTo_user_id() {
      return this.to_user_id;
   }

   public String getTitle() {
      return this.title;
   }

   public String getOtp() {
      return this.otp;
   }

   public String getTxn_code() {
      return this.txn_code;
   }

   public String getTxn_id() {
      return this.txn_id;
   }

   public String getTxn_number() {
      return this.txn_number;
   }

   public String getTxn_status() {
      return this.txn_status;
   }

   public Double getTxn_amount() {
      return this.txn_amount;
   }

   public Double getTo_user_txn_amount() {
      return this.to_user_txn_amount;
   }

   public Double getCharge_amount() {
      return this.charge_amount;
   }

   public String getNote() {
      return this.note;
   }

   public String getService_type() {
      return this.service_type;
   }

   public String getTransaction_time() {
      return this.transaction_time;
   }

   public String getReason() {
      return this.reason;
   }

   public String getBusiness_name() {
      return this.business_name;
   }

   public String getCountry_id() {
      return this.country_id;
   }

   public Integer getEvent_type() {
      return this.event_type;
   }

   public String getCurrency_id() {
      return this.currency_id;
   }

   public String getTo_currency_id() {
      return this.to_currency_id;
   }

   public String getWallet_id() {
      return this.wallet_id;
   }

   public String getTo_user_wallet_id() {
      return this.to_user_wallet_id;
   }

   public String getReference_number() {
      return this.reference_number;
   }

   public Integer getDays() {
      return this.days;
   }

   public Integer getCount() {
      return this.count;
   }

   public Long getDate() {
      return this.date;
   }

   public Double getNet_amount() {
      return this.net_amount;
   }

   public String getDial_code() {
      return this.dial_code;
   }

   public String getPhone_number() {
      return this.phone_number;
   }

   public Integer getTo_user_type() {
      return this.to_user_type;
   }

   public Integer getUser_type() {
      return this.user_type;
   }

   public String getLink() {
      return this.link;
   }

   public Map<String, Object> getMeta_data() {
      return this.meta_data;
   }

   public Double getBalance() {
      return this.balance;
   }

   public String getSender_name() {
      return this.sender_name;
   }

   public String getFile_attached() {
      return this.file_attached;
   }

   public void setCompany_id(final String company_id) {
      this.company_id = company_id;
   }

   public void setUser_id(final String user_id) {
      this.user_id = user_id;
   }

   public void setTo_user_id(final String to_user_id) {
      this.to_user_id = to_user_id;
   }

   public void setTitle(final String title) {
      this.title = title;
   }

   public void setOtp(final String otp) {
      this.otp = otp;
   }

   public void setTxn_code(final String txn_code) {
      this.txn_code = txn_code;
   }

   public void setTxn_id(final String txn_id) {
      this.txn_id = txn_id;
   }

   public void setTxn_number(final String txn_number) {
      this.txn_number = txn_number;
   }

   public void setTxn_status(final String txn_status) {
      this.txn_status = txn_status;
   }

   public void setTxn_amount(final Double txn_amount) {
      this.txn_amount = txn_amount;
   }

   public void setTo_user_txn_amount(final Double to_user_txn_amount) {
      this.to_user_txn_amount = to_user_txn_amount;
   }

   public void setCharge_amount(final Double charge_amount) {
      this.charge_amount = charge_amount;
   }

   public void setNote(final String note) {
      this.note = note;
   }

   public void setService_type(final String service_type) {
      this.service_type = service_type;
   }

   public void setTransaction_time(final String transaction_time) {
      this.transaction_time = transaction_time;
   }

   public void setReason(final String reason) {
      this.reason = reason;
   }

   public void setBusiness_name(final String business_name) {
      this.business_name = business_name;
   }

   public void setCountry_id(final String country_id) {
      this.country_id = country_id;
   }

   public void setEvent_type(final Integer event_type) {
      this.event_type = event_type;
   }

   public void setCurrency_id(final String currency_id) {
      this.currency_id = currency_id;
   }

   public void setTo_currency_id(final String to_currency_id) {
      this.to_currency_id = to_currency_id;
   }

   public void setWallet_id(final String wallet_id) {
      this.wallet_id = wallet_id;
   }

   public void setTo_user_wallet_id(final String to_user_wallet_id) {
      this.to_user_wallet_id = to_user_wallet_id;
   }

   public void setReference_number(final String reference_number) {
      this.reference_number = reference_number;
   }

   public void setDays(final Integer days) {
      this.days = days;
   }

   public void setCount(final Integer count) {
      this.count = count;
   }

   public void setDate(final Long date) {
      this.date = date;
   }

   public void setNet_amount(final Double net_amount) {
      this.net_amount = net_amount;
   }

   public void setDial_code(final String dial_code) {
      this.dial_code = dial_code;
   }

   public void setPhone_number(final String phone_number) {
      this.phone_number = phone_number;
   }

   public void setTo_user_type(final Integer to_user_type) {
      this.to_user_type = to_user_type;
   }

   public void setUser_type(final Integer user_type) {
      this.user_type = user_type;
   }

   public void setLink(final String link) {
      this.link = link;
   }

   public void setMeta_data(final Map<String, Object> meta_data) {
      this.meta_data = meta_data;
   }

   public void setBalance(final Double balance) {
      this.balance = balance;
   }

   public void setSender_name(final String sender_name) {
      this.sender_name = sender_name;
   }

   public void setFile_attached(final String file_attached) {
      this.file_attached = file_attached;
   }

   public static class SendNotificationDataBuilder {
      private String company_id;
      private String user_id;
      private String to_user_id;
      private String title;
      private String otp;
      private String txn_code;
      private String txn_id;
      private String txn_number;
      private String txn_status;
      private Double txn_amount;
      private Double to_user_txn_amount;
      private Double charge_amount;
      private String note;
      private String service_type;
      private String transaction_time;
      private String reason;
      private String business_name;
      private String country_id;
      private Integer event_type;
      private String currency_id;
      private String to_currency_id;
      private String wallet_id;
      private String to_user_wallet_id;
      private String reference_number;
      private Integer days;
      private Integer count;
      private Long date;
      private Double net_amount;
      private String dial_code;
      private String phone_number;
      private Integer to_user_type;
      private Integer user_type;
      private String link;
      private Map<String, Object> meta_data;
      private Double balance;
      private String sender_name;
      private String file_attached;

      SendNotificationDataBuilder() {
      }

      public SendNotificationDataBuilder company_id(final String company_id) {
         this.company_id = company_id;
         return this;
      }

      public SendNotificationDataBuilder user_id(final String user_id) {
         this.user_id = user_id;
         return this;
      }

      public SendNotificationDataBuilder to_user_id(final String to_user_id) {
         this.to_user_id = to_user_id;
         return this;
      }

      public SendNotificationDataBuilder title(final String title) {
         this.title = title;
         return this;
      }

      public SendNotificationDataBuilder otp(final String otp) {
         this.otp = otp;
         return this;
      }

      public SendNotificationDataBuilder txn_code(final String txn_code) {
         this.txn_code = txn_code;
         return this;
      }

      public SendNotificationDataBuilder txn_id(final String txn_id) {
         this.txn_id = txn_id;
         return this;
      }

      public SendNotificationDataBuilder txn_number(final String txn_number) {
         this.txn_number = txn_number;
         return this;
      }

      public SendNotificationDataBuilder txn_status(final String txn_status) {
         this.txn_status = txn_status;
         return this;
      }

      public SendNotificationDataBuilder txn_amount(final Double txn_amount) {
         this.txn_amount = txn_amount;
         return this;
      }

      public SendNotificationDataBuilder to_user_txn_amount(final Double to_user_txn_amount) {
         this.to_user_txn_amount = to_user_txn_amount;
         return this;
      }

      public SendNotificationDataBuilder charge_amount(final Double charge_amount) {
         this.charge_amount = charge_amount;
         return this;
      }

      public SendNotificationDataBuilder note(final String note) {
         this.note = note;
         return this;
      }

      public SendNotificationDataBuilder service_type(final String service_type) {
         this.service_type = service_type;
         return this;
      }

      public SendNotificationDataBuilder transaction_time(final String transaction_time) {
         this.transaction_time = transaction_time;
         return this;
      }

      public SendNotificationDataBuilder reason(final String reason) {
         this.reason = reason;
         return this;
      }

      public SendNotificationDataBuilder business_name(final String business_name) {
         this.business_name = business_name;
         return this;
      }

      public SendNotificationDataBuilder country_id(final String country_id) {
         this.country_id = country_id;
         return this;
      }

      public SendNotificationDataBuilder event_type(final Integer event_type) {
         this.event_type = event_type;
         return this;
      }

      public SendNotificationDataBuilder currency_id(final String currency_id) {
         this.currency_id = currency_id;
         return this;
      }

      public SendNotificationDataBuilder to_currency_id(final String to_currency_id) {
         this.to_currency_id = to_currency_id;
         return this;
      }

      public SendNotificationDataBuilder wallet_id(final String wallet_id) {
         this.wallet_id = wallet_id;
         return this;
      }

      public SendNotificationDataBuilder to_user_wallet_id(final String to_user_wallet_id) {
         this.to_user_wallet_id = to_user_wallet_id;
         return this;
      }

      public SendNotificationDataBuilder reference_number(final String reference_number) {
         this.reference_number = reference_number;
         return this;
      }

      public SendNotificationDataBuilder days(final Integer days) {
         this.days = days;
         return this;
      }

      public SendNotificationDataBuilder count(final Integer count) {
         this.count = count;
         return this;
      }

      public SendNotificationDataBuilder date(final Long date) {
         this.date = date;
         return this;
      }

      public SendNotificationDataBuilder net_amount(final Double net_amount) {
         this.net_amount = net_amount;
         return this;
      }

      public SendNotificationDataBuilder dial_code(final String dial_code) {
         this.dial_code = dial_code;
         return this;
      }

      public SendNotificationDataBuilder phone_number(final String phone_number) {
         this.phone_number = phone_number;
         return this;
      }

      public SendNotificationDataBuilder to_user_type(final Integer to_user_type) {
         this.to_user_type = to_user_type;
         return this;
      }

      public SendNotificationDataBuilder user_type(final Integer user_type) {
         this.user_type = user_type;
         return this;
      }

      public SendNotificationDataBuilder link(final String link) {
         this.link = link;
         return this;
      }

      public SendNotificationDataBuilder meta_data(final Map<String, Object> meta_data) {
         this.meta_data = meta_data;
         return this;
      }

      public SendNotificationDataBuilder balance(final Double balance) {
         this.balance = balance;
         return this;
      }

      public SendNotificationDataBuilder sender_name(final String sender_name) {
         this.sender_name = sender_name;
         return this;
      }

      public SendNotificationDataBuilder file_attached(final String file_attached) {
         this.file_attached = file_attached;
         return this;
      }

      public SendNotificationData build() {
         return new SendNotificationData(this.company_id, this.user_id, this.to_user_id, this.title, this.otp, this.txn_code, this.txn_id, this.txn_number, this.txn_status, this.txn_amount, this.to_user_txn_amount, this.charge_amount, this.note, this.service_type, this.transaction_time, this.reason, this.business_name, this.country_id, this.event_type, this.currency_id, this.to_currency_id, this.wallet_id, this.to_user_wallet_id, this.reference_number, this.days, this.count, this.date, this.net_amount, this.dial_code, this.phone_number, this.to_user_type, this.user_type, this.link, this.meta_data, this.balance, this.sender_name, this.file_attached);
      }

      public String toString() {
         return "SendNotificationData.SendNotificationDataBuilder(company_id=" + this.company_id + ", user_id=" + this.user_id + ", to_user_id=" + this.to_user_id + ", title=" + this.title + ", otp=" + this.otp + ", txn_code=" + this.txn_code + ", txn_id=" + this.txn_id + ", txn_number=" + this.txn_number + ", txn_status=" + this.txn_status + ", txn_amount=" + this.txn_amount + ", to_user_txn_amount=" + this.to_user_txn_amount + ", charge_amount=" + this.charge_amount + ", note=" + this.note + ", service_type=" + this.service_type + ", transaction_time=" + this.transaction_time + ", reason=" + this.reason + ", business_name=" + this.business_name + ", country_id=" + this.country_id + ", event_type=" + this.event_type + ", currency_id=" + this.currency_id + ", to_currency_id=" + this.to_currency_id + ", wallet_id=" + this.wallet_id + ", to_user_wallet_id=" + this.to_user_wallet_id + ", reference_number=" + this.reference_number + ", days=" + this.days + ", count=" + this.count + ", date=" + this.date + ", net_amount=" + this.net_amount + ", dial_code=" + this.dial_code + ", phone_number=" + this.phone_number + ", to_user_type=" + this.to_user_type + ", user_type=" + this.user_type + ", link=" + this.link + ", meta_data=" + this.meta_data + ", balance=" + this.balance + ", sender_name=" + this.sender_name + ", file_attached=" + this.file_attached + ")";
      }
   }
}
