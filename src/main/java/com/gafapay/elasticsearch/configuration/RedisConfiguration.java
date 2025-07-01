package com.gafapay.elasticsearch.configuration;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfiguration {
   @Value("${spring.redis.host}")
   private String redisHost;
   @Value("${spring.redis.port}")
   private Integer redisPort;
   @Value("${spring.redis.password}")
   private String redisPass;

   public RedisConfiguration() {
   }

   @Bean
   public RedisConnectionFactory redisConnectionFactory() {
      RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(this.redisHost, this.redisPort);
      if (!StringUtils.isEmpty(this.redisPass)) {
         redisStandaloneConfiguration.setPassword(RedisPassword.of(this.redisPass));
      }

      return new LettuceConnectionFactory(redisStandaloneConfiguration);
   }

   @Bean
   public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory jedisConnectionFactory) {
      RedisTemplate<String, Object> redisTemplate = new RedisTemplate();
      redisTemplate.setConnectionFactory(jedisConnectionFactory);
      return redisTemplate;
   }
}
