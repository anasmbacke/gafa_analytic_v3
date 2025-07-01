package com.gafapay.elasticsearch.configuration;

import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class SpringWebMvcConfig implements WebMvcConfigurer {
   public SpringWebMvcConfig() {
   }

   public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**").allowedMethods(new String[]{"*"});
   }

   public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
   }
}
