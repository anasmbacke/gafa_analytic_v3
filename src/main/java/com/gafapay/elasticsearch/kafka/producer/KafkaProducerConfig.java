package com.gafapay.elasticsearch.kafka.producer;

import com.gafapay.elasticsearch.utils.Logger;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.ProducerListener;

@Configuration
class KafkaProducerConfig {
   @Value("${kafka.bootstrap.servers}")
   private String bootstrapAddress;

   KafkaProducerConfig() {
   }

   @Bean
   public ProducerFactory<String, String> producerFactory() {
      Map<String, Object> props = new HashMap();
      props.put("bootstrap.servers", this.bootstrapAddress);
      props.put("retries", 0);
      props.put("batch.size", 16384);
      props.put("linger.ms", 1);
      props.put("buffer.memory", 33554432);
      props.put("key.serializer", StringSerializer.class);
      props.put("value.serializer", StringSerializer.class);
      return new DefaultKafkaProducerFactory(props);
   }

   @Bean
   public KafkaTemplate<String, String> kafkaTemplate() {
      KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate(this.producerFactory());
      return kafkaTemplate;
   }

   private ProducerListener<String, String> setProducerListener() {
      return new ProducerListener<String, String>() {
         public void onSuccess(ProducerRecord<String, String> producerRecord, RecordMetadata recordMetadata) {
            String var10000 = (String)producerRecord.value();
            Logger.info("ACK from ProducerListener message: {} offset:  {}" + var10000 + recordMetadata.offset());
         }
      };
   }
}
