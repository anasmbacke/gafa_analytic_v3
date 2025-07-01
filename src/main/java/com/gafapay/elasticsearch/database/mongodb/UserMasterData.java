package com.gafapay.elasticsearch.database.mongodb;

import com.gafapay.elasticsearch.database.model.CommonFieldModel;
import com.gafapay.elasticsearch.utils.Utils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Map;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(
   collection = "user_master"
)
@JsonIgnoreProperties(
   ignoreUnknown = true
)
public class UserMasterData extends CommonFieldModel {
   @Field("_id")
   private String id = Utils.generateUUID();
   @Field("first_name")
   private String firstName;
   @Field("last_name")
   private String lastName = null;
   @Field("is_staff")
   private Boolean isStaff = false;
   @Field("is_superuser")
   private Boolean isSuperUser = false;
   @Field("username")
   private String userName;
   @Field("password")
   private String password = null;
   @Field("email")
   private String email = null;
   @Field("dial_code")
   private String dialCode = null;
   @Field("phone_number")
   private String phoneNumber = null;
   @Field("login_pin")
   private String loginPin = null;
   @Field("transaction_pin")
   private String txnPin = null;
   @Field("user_code")
   private String userCode = null;
   @Field("user_type")
   private Integer userType;
   @Field("device_info")
   private Map<String, Object> deviceInfo;
   @Field("role_id")
   private String roleId = null;
   @Field("account_number")
   private String accountNumber = null;
   @Field("verification")
   private Map<String, Object> verification;
   @Field("status_id")
   private Integer statusId;
   @Field("image")
   private String image;
   @Field("address")
   private String address;
   @Field("postal_code")
   private String postalCode;
   @Field("country_id")
   private String countryId;
   @Field("state_id")
   private String stateId;
   @Field("city_id")
   private String cityId;
   @Field("rating")
   private Double rating;
   @Field("kyc_status")
   private Integer kycStatus;
   @Field("kyc_process_status")
   private Integer kycProcessStatus;
   @Field("location_point")
   private Map<String, Object> locationPoint;
   @Field("display_name")
   private String displayName;
   @Field("display_image")
   private String displayImage;

   protected UserMasterData(final UserMasterDataBuilder<?, ?> b) {
      super(b);
      this.id = b.id;
      this.firstName = b.firstName;
      this.lastName = b.lastName;
      this.isStaff = b.isStaff;
      this.isSuperUser = b.isSuperUser;
      this.userName = b.userName;
      this.password = b.password;
      this.email = b.email;
      this.dialCode = b.dialCode;
      this.phoneNumber = b.phoneNumber;
      this.loginPin = b.loginPin;
      this.txnPin = b.txnPin;
      this.userCode = b.userCode;
      this.userType = b.userType;
      this.deviceInfo = b.deviceInfo;
      this.roleId = b.roleId;
      this.accountNumber = b.accountNumber;
      this.verification = b.verification;
      this.statusId = b.statusId;
      this.image = b.image;
      this.address = b.address;
      this.postalCode = b.postalCode;
      this.countryId = b.countryId;
      this.stateId = b.stateId;
      this.cityId = b.cityId;
      this.rating = b.rating;
      this.kycStatus = b.kycStatus;
      this.kycProcessStatus = b.kycProcessStatus;
      this.locationPoint = b.locationPoint;
      this.displayName = b.displayName;
      this.displayImage = b.displayImage;
   }

   public static UserMasterDataBuilder<?, ?> builder() {
      return new UserMasterDataBuilderImpl();
   }

   public String getId() {
      return this.id;
   }

   public String getFirstName() {
      return this.firstName;
   }

   public String getLastName() {
      return this.lastName;
   }

   public Boolean getIsStaff() {
      return this.isStaff;
   }

   public Boolean getIsSuperUser() {
      return this.isSuperUser;
   }

   public String getUserName() {
      return this.userName;
   }

   public String getPassword() {
      return this.password;
   }

   public String getEmail() {
      return this.email;
   }

   public String getDialCode() {
      return this.dialCode;
   }

   public String getPhoneNumber() {
      return this.phoneNumber;
   }

   public String getLoginPin() {
      return this.loginPin;
   }

   public String getTxnPin() {
      return this.txnPin;
   }

   public String getUserCode() {
      return this.userCode;
   }

   public Integer getUserType() {
      return this.userType;
   }

   public Map<String, Object> getDeviceInfo() {
      return this.deviceInfo;
   }

   public String getRoleId() {
      return this.roleId;
   }

   public String getAccountNumber() {
      return this.accountNumber;
   }

   public Map<String, Object> getVerification() {
      return this.verification;
   }

   public Integer getStatusId() {
      return this.statusId;
   }

   public String getImage() {
      return this.image;
   }

   public String getAddress() {
      return this.address;
   }

   public String getPostalCode() {
      return this.postalCode;
   }

   public String getCountryId() {
      return this.countryId;
   }

   public String getStateId() {
      return this.stateId;
   }

   public String getCityId() {
      return this.cityId;
   }

   public Double getRating() {
      return this.rating;
   }

   public Integer getKycStatus() {
      return this.kycStatus;
   }

   public Integer getKycProcessStatus() {
      return this.kycProcessStatus;
   }

   public Map<String, Object> getLocationPoint() {
      return this.locationPoint;
   }

   public String getDisplayName() {
      return this.displayName;
   }

   public String getDisplayImage() {
      return this.displayImage;
   }

   public void setId(final String id) {
      this.id = id;
   }

   public void setFirstName(final String firstName) {
      this.firstName = firstName;
   }

   public void setLastName(final String lastName) {
      this.lastName = lastName;
   }

   public void setIsStaff(final Boolean isStaff) {
      this.isStaff = isStaff;
   }

   public void setIsSuperUser(final Boolean isSuperUser) {
      this.isSuperUser = isSuperUser;
   }

   public void setUserName(final String userName) {
      this.userName = userName;
   }

   public void setPassword(final String password) {
      this.password = password;
   }

   public void setEmail(final String email) {
      this.email = email;
   }

   public void setDialCode(final String dialCode) {
      this.dialCode = dialCode;
   }

   public void setPhoneNumber(final String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public void setLoginPin(final String loginPin) {
      this.loginPin = loginPin;
   }

   public void setTxnPin(final String txnPin) {
      this.txnPin = txnPin;
   }

   public void setUserCode(final String userCode) {
      this.userCode = userCode;
   }

   public void setUserType(final Integer userType) {
      this.userType = userType;
   }

   public void setDeviceInfo(final Map<String, Object> deviceInfo) {
      this.deviceInfo = deviceInfo;
   }

   public void setRoleId(final String roleId) {
      this.roleId = roleId;
   }

   public void setAccountNumber(final String accountNumber) {
      this.accountNumber = accountNumber;
   }

   public void setVerification(final Map<String, Object> verification) {
      this.verification = verification;
   }

   public void setStatusId(final Integer statusId) {
      this.statusId = statusId;
   }

   public void setImage(final String image) {
      this.image = image;
   }

   public void setAddress(final String address) {
      this.address = address;
   }

   public void setPostalCode(final String postalCode) {
      this.postalCode = postalCode;
   }

   public void setCountryId(final String countryId) {
      this.countryId = countryId;
   }

   public void setStateId(final String stateId) {
      this.stateId = stateId;
   }

   public void setCityId(final String cityId) {
      this.cityId = cityId;
   }

   public void setRating(final Double rating) {
      this.rating = rating;
   }

   public void setKycStatus(final Integer kycStatus) {
      this.kycStatus = kycStatus;
   }

   public void setKycProcessStatus(final Integer kycProcessStatus) {
      this.kycProcessStatus = kycProcessStatus;
   }

   public void setLocationPoint(final Map<String, Object> locationPoint) {
      this.locationPoint = locationPoint;
   }

   public void setDisplayName(final String displayName) {
      this.displayName = displayName;
   }

   public void setDisplayImage(final String displayImage) {
      this.displayImage = displayImage;
   }

   public UserMasterData() {
   }

   public abstract static class UserMasterDataBuilder<C extends UserMasterData, B extends UserMasterDataBuilder<C, B>> extends CommonFieldModel.CommonFieldModelBuilder<C, B> {
      private String id;
      private String firstName;
      private String lastName;
      private Boolean isStaff;
      private Boolean isSuperUser;
      private String userName;
      private String password;
      private String email;
      private String dialCode;
      private String phoneNumber;
      private String loginPin;
      private String txnPin;
      private String userCode;
      private Integer userType;
      private Map<String, Object> deviceInfo;
      private String roleId;
      private String accountNumber;
      private Map<String, Object> verification;
      private Integer statusId;
      private String image;
      private String address;
      private String postalCode;
      private String countryId;
      private String stateId;
      private String cityId;
      private Double rating;
      private Integer kycStatus;
      private Integer kycProcessStatus;
      private Map<String, Object> locationPoint;
      private String displayName;
      private String displayImage;

      public UserMasterDataBuilder() {
      }

      protected abstract B self();

      public abstract C build();

      public B id(final String id) {
         this.id = id;
         return (B)this.self();
      }

      public B firstName(final String firstName) {
         this.firstName = firstName;
         return (B)this.self();
      }

      public B lastName(final String lastName) {
         this.lastName = lastName;
         return (B)this.self();
      }

      public B isStaff(final Boolean isStaff) {
         this.isStaff = isStaff;
         return (B)this.self();
      }

      public B isSuperUser(final Boolean isSuperUser) {
         this.isSuperUser = isSuperUser;
         return (B)this.self();
      }

      public B userName(final String userName) {
         this.userName = userName;
         return (B)this.self();
      }

      public B password(final String password) {
         this.password = password;
         return (B)this.self();
      }

      public B email(final String email) {
         this.email = email;
         return (B)this.self();
      }

      public B dialCode(final String dialCode) {
         this.dialCode = dialCode;
         return (B)this.self();
      }

      public B phoneNumber(final String phoneNumber) {
         this.phoneNumber = phoneNumber;
         return (B)this.self();
      }

      public B loginPin(final String loginPin) {
         this.loginPin = loginPin;
         return (B)this.self();
      }

      public B txnPin(final String txnPin) {
         this.txnPin = txnPin;
         return (B)this.self();
      }

      public B userCode(final String userCode) {
         this.userCode = userCode;
         return (B)this.self();
      }

      public B userType(final Integer userType) {
         this.userType = userType;
         return (B)this.self();
      }

      public B deviceInfo(final Map<String, Object> deviceInfo) {
         this.deviceInfo = deviceInfo;
         return (B)this.self();
      }

      public B roleId(final String roleId) {
         this.roleId = roleId;
         return (B)this.self();
      }

      public B accountNumber(final String accountNumber) {
         this.accountNumber = accountNumber;
         return (B)this.self();
      }

      public B verification(final Map<String, Object> verification) {
         this.verification = verification;
         return (B)this.self();
      }

      public B statusId(final Integer statusId) {
         this.statusId = statusId;
         return (B)this.self();
      }

      public B image(final String image) {
         this.image = image;
         return (B)this.self();
      }

      public B address(final String address) {
         this.address = address;
         return (B)this.self();
      }

      public B postalCode(final String postalCode) {
         this.postalCode = postalCode;
         return (B)this.self();
      }

      public B countryId(final String countryId) {
         this.countryId = countryId;
         return (B)this.self();
      }

      public B stateId(final String stateId) {
         this.stateId = stateId;
         return (B)this.self();
      }

      public B cityId(final String cityId) {
         this.cityId = cityId;
         return (B)this.self();
      }

      public B rating(final Double rating) {
         this.rating = rating;
         return (B)this.self();
      }

      public B kycStatus(final Integer kycStatus) {
         this.kycStatus = kycStatus;
         return (B)this.self();
      }

      public B kycProcessStatus(final Integer kycProcessStatus) {
         this.kycProcessStatus = kycProcessStatus;
         return (B)this.self();
      }

      public B locationPoint(final Map<String, Object> locationPoint) {
         this.locationPoint = locationPoint;
         return (B)this.self();
      }

      public B displayName(final String displayName) {
         this.displayName = displayName;
         return (B)this.self();
      }

      public B displayImage(final String displayImage) {
         this.displayImage = displayImage;
         return (B)this.self();
      }

      public String toString() {
         String var10000 = super.toString();
         return "UserMasterData.UserMasterDataBuilder(super=" + var10000 + ", id=" + this.id + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", isStaff=" + this.isStaff + ", isSuperUser=" + this.isSuperUser + ", userName=" + this.userName + ", password=" + this.password + ", email=" + this.email + ", dialCode=" + this.dialCode + ", phoneNumber=" + this.phoneNumber + ", loginPin=" + this.loginPin + ", txnPin=" + this.txnPin + ", userCode=" + this.userCode + ", userType=" + this.userType + ", deviceInfo=" + this.deviceInfo + ", roleId=" + this.roleId + ", accountNumber=" + this.accountNumber + ", verification=" + this.verification + ", statusId=" + this.statusId + ", image=" + this.image + ", address=" + this.address + ", postalCode=" + this.postalCode + ", countryId=" + this.countryId + ", stateId=" + this.stateId + ", cityId=" + this.cityId + ", rating=" + this.rating + ", kycStatus=" + this.kycStatus + ", kycProcessStatus=" + this.kycProcessStatus + ", locationPoint=" + this.locationPoint + ", displayName=" + this.displayName + ", displayImage=" + this.displayImage + ")";
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

   private static final class UserMasterDataBuilderImpl extends UserMasterDataBuilder<UserMasterData, UserMasterDataBuilderImpl> {
      private UserMasterDataBuilderImpl() {
      }

      protected UserMasterDataBuilderImpl self() {
         return this;
      }

      public UserMasterData build() {
         return new UserMasterData(this);
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected UserMasterDataBuilder self() {
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
