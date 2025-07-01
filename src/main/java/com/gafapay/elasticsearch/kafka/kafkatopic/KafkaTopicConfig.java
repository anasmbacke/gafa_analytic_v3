package com.gafapay.elasticsearch.kafka.kafkatopic;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
class KafkaTopicConfig {
   @Value("${kafka.bootstrap.servers}")
   private String bootstrapAddress;

   KafkaTopicConfig() {
   }

   @Bean
   public KafkaAdmin kafkaAdmin() {
      Map<String, Object> configs = new HashMap();
      configs.put("bootstrap.servers", this.bootstrapAddress);
      return new KafkaAdmin(configs);
   }

   @Bean
   NewTopic createElasticSearchActivityLogsTopic() {
      return TopicBuilder.name("dig_topic_elastic_search_activity_logs").partitions(5).build();
   }
}
