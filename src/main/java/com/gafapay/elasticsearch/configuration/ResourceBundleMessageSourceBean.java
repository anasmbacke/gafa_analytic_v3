package com.gafapay.elasticsearch.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class ResourceBundleMessageSourceBean {
   public ResourceBundleMessageSourceBean() {
   }

   @Bean
   public ResourceBundleMessageSource messageSource() {
      ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
      rs.setBasenames(new String[]{"responseMessages"});
      rs.setDefaultEncoding("UTF-8");
      rs.setUseCodeAsDefaultMessage(true);
      return rs;
   }
}
