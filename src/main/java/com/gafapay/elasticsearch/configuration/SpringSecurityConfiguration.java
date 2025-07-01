package com.gafapay.elasticsearch.configuration;

import com.gafapay.elasticsearch.constant.APIRequestURL;
import com.gafapay.elasticsearch.database.repository.redis.RedisUserSecretDao;
import com.gafapay.elasticsearch.filter.CustomAuthorizationFilterSpring;
import com.gafapay.elasticsearch.helper.ElasticSearchUtility;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter.HeaderValue;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {
   @Autowired
   @Lazy
   private UserDetailsService userDetailsService;
   private final RedisUserSecretDao redisUserSecretDao;
   private final ElasticSearchUtility elasticSearchUtility;

   @Autowired
   SpringSecurityConfiguration(RedisUserSecretDao redisUserSecretDao, ElasticSearchUtility elasticSearchUtility) {
      this.elasticSearchUtility = elasticSearchUtility;
      this.redisUserSecretDao = redisUserSecretDao;
   }

   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http.csrf(AbstractHttpConfigurer::disable);
      http.cors((httpSecurityCorsConfigurer) -> httpSecurityCorsConfigurer.configurationSource((request) -> {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(List.of("*"));
            configuration.setAllowedHeaders(List.of("*"));
            configuration.setAllowedOriginPatterns(List.of("*"));
            configuration.setAllowedMethods(List.of(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name(), HttpMethod.OPTIONS.name()));
            return configuration;
         }));
      http.headers((headers) -> headers.xssProtection((xss) -> xss.headerValue(HeaderValue.ENABLED_MODE_BLOCK)).contentSecurityPolicy((cps) -> cps.policyDirectives("default-src 'self'; script-src 'self' 'unsafe-inline' 'unsafe-eval';")));
      http.sessionManagement((httpSecuritySessionManagementConfigurer) -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.NEVER));
      http.authorizeHttpRequests((authorizationManagerRequestMatcherRegistry) -> ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)authorizationManagerRequestMatcherRegistry.requestMatchers(new String[]{"/digipay/v3/analytics/**"})).authenticated());
      http.addFilterBefore(new CustomAuthorizationFilterSpring(this.redisUserSecretDao, this.elasticSearchUtility), UsernamePasswordAuthenticationFilter.class);
      return (SecurityFilterChain)http.build();
   }

   @Bean
   public WebSecurityCustomizer webSecurityCustomizer() {
      return (web) -> {
         web.ignoring().requestMatchers(HttpMethod.GET, APIRequestURL.getByPassApiEndpoints);
         web.ignoring().requestMatchers(HttpMethod.POST, APIRequestURL.postByPassApiEndpoints);
         web.ignoring().requestMatchers(HttpMethod.PUT, APIRequestURL.putByPassApiEndpoints);
         web.ignoring().requestMatchers(HttpMethod.DELETE, APIRequestURL.deleteByPassApiEndpoints);
      };
   }

   @Bean
   public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
      return configuration.getAuthenticationManager();
   }
}
