package com.gafapay.elasticsearch.kafka.consumer;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
class KafkaConsumerConfig {
   @Autowired
   private KafkaTemplate<String, String> kafkatemplate;
   @Value("${kafka.bootstrap.servers}")
   private String bootstrapAddress;

   KafkaConsumerConfig() {
   }

   @Bean
   public ConsumerFactory<String, String> elasticSearchDockerConsumerFactory() {
      return this.consumerFactoryObject(this.getProps("dig_elastic_search_docker_group"));
   }

   @Bean
   public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> elasticSearchDockerkafkaListenerContainerFactory() {
      return this.getConcurrentMessageListenerContainerKafkaListenerContainerFactory(this.elasticSearchDockerConsumerFactory());
   }

   private Map<String, Object> getProps(String groupId) {
      Map<String, Object> props = new HashMap();
      props.put("bootstrap.servers", this.bootstrapAddress);
      props.put("group.id", groupId);
      props.put("retry.backoff.ms", "100");
      props.put("enable.auto.commit", false);
      props.put("auto.commit.interval.ms", "100");
      props.put("session.timeout.ms", "15000");
      props.put("heartbeat.interval.ms", "1000");
      props.put("key.deserializer", StringDeserializer.class);
      props.put("value.deserializer", StringDeserializer.class);
      props.put("auto.offset.reset", "earliest");
      return props;
   }

   private KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> getConcurrentMessageListenerContainerKafkaListenerContainerFactory(ConsumerFactory<String, String> consumerFactory) {
      ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory();
      factory.setConsumerFactory(consumerFactory);
      factory.setReplyTemplate(this.kafkatemplate);
      return factory;
   }

   private DefaultKafkaConsumerFactory<String, String> consumerFactoryObject(Map<String, Object> props) {
      DefaultKafkaConsumerFactory<String, String> consumerFactoryobj = new DefaultKafkaConsumerFactory(props);
      return consumerFactoryobj;
   }

   private RetryPolicy retryPolicy() {
      return new SimpleRetryPolicy(1);
   }

   private RetryTemplate retryTemplate() {
      RetryTemplate template = new RetryTemplate();
      template.setRetryPolicy(this.retryPolicy());
      return template;
   }
}
