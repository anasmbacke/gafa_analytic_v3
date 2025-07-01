package com.gafapay.elasticsearch;

import com.datadog.api.client.ApiClient;
import com.datadog.api.client.v2.api.LogsApi;
import com.gafapay.elasticsearch.statemachine.StateMachineAccessor;
import com.gafapay.elasticsearch.statemachine.states.AppEvents;
import com.gafapay.elasticsearch.statemachine.states.AppState;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableAsync
@EnableEncryptableProperties
public class ElasticSearchApplication {
   @Value("${data.dog.log.api.key}")
   private String dataDogKey;

   public ElasticSearchApplication() {
   }

   public static void main(String[] args) {
      SpringApplication.run(ElasticSearchApplication.class, args);

      try {
         File fileToDelete = FileUtils.getFile(new String[]{"/app.jar"});
         FileUtils.forceDelete(fileToDelete);
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   @Bean
   public ConfigurableServletWebServerFactory webServerFactory() {
      TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
      factory.addConnectorCustomizers(new TomcatConnectorCustomizer[]{(connector) -> connector.setProperty("relaxedQueryChars", "|{}[]")});
      return factory;
   }

   @Bean
   public static ConfigureRedisAction configureRedisAction() {
      return ConfigureRedisAction.NO_OP;
   }

   @Bean
   public LogsApi getLogsAPI() {
      return new LogsApi(ApiClient.getDefaultApiClient().setApiKey(this.dataDogKey));
   }

   @Component
   class Runner implements ApplicationRunner {
      private final StateMachineAccessor stateMachineAccessor;

      @Autowired
      Runner(StateMachineAccessor stateMachineAccessor) {
         this.stateMachineAccessor = stateMachineAccessor;
      }

      public void run(ApplicationArguments args) {
         StateMachine<AppState, AppEvents> stateMachine = this.stateMachineAccessor.getStateMachine();
         stateMachine.start();
      }
   }
}
