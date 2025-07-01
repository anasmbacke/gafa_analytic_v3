package com.gafapay.elasticsearch.helper;

import java.util.Collection;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class SpringUsers extends User {
   private String companyId;
   private String email;
   private Map<String, Object> deviceInfo;
   private String firstName;
   private Integer userType;

   public SpringUsers(String username, String password, Collection<? extends GrantedAuthority> authorities) {
      super(username, password, authorities);
   }

   public boolean equals(Object obj) {
      return super.equals(obj);
   }

   public int hashCode() {
      return super.hashCode();
   }

   public String getCompanyId() {
      return this.companyId;
   }

   public String getEmail() {
      return this.email;
   }

   public Map<String, Object> getDeviceInfo() {
      return this.deviceInfo;
   }

   public String getFirstName() {
      return this.firstName;
   }

   public Integer getUserType() {
      return this.userType;
   }

   public void setCompanyId(final String companyId) {
      this.companyId = companyId;
   }

   public void setEmail(final String email) {
      this.email = email;
   }

   public void setDeviceInfo(final Map<String, Object> deviceInfo) {
      this.deviceInfo = deviceInfo;
   }

   public void setFirstName(final String firstName) {
      this.firstName = firstName;
   }

   public void setUserType(final Integer userType) {
      this.userType = userType;
   }
}
