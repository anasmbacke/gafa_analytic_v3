package com.gafapay.elasticsearch.database.model;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("user_auth")
public class UserAuth implements Serializable {
   @Id
   private String user_id;
   private String secret_key;
   private List<String> user_token;
   private Long created_date;
   private Long updated_date;
   private Integer number_of_sessions_active;

   public String getUser_id() {
      return this.user_id;
   }

   public String getSecret_key() {
      return this.secret_key;
   }

   public List<String> getUser_token() {
      return this.user_token;
   }

   public Long getCreated_date() {
      return this.created_date;
   }

   public Long getUpdated_date() {
      return this.updated_date;
   }

   public Integer getNumber_of_sessions_active() {
      return this.number_of_sessions_active;
   }

   public void setUser_id(final String user_id) {
      this.user_id = user_id;
   }

   public void setSecret_key(final String secret_key) {
      this.secret_key = secret_key;
   }

   public void setUser_token(final List<String> user_token) {
      this.user_token = user_token;
   }

   public void setCreated_date(final Long created_date) {
      this.created_date = created_date;
   }

   public void setUpdated_date(final Long updated_date) {
      this.updated_date = updated_date;
   }

   public void setNumber_of_sessions_active(final Integer number_of_sessions_active) {
      this.number_of_sessions_active = number_of_sessions_active;
   }

   public UserAuth() {
   }
}
