package com.gafapay.elasticsearch.datadog;

import com.gafapay.elasticsearch.utils.Logger;
import com.gafapay.elasticsearch.utils.Utils;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaConnector {
   private final Properties props = Utils.getKafkaProperties();
   KafkaProducer<String, String> producer;

   public KafkaConnector() {
      this.producer = new KafkaProducer(this.props);
   }

   public void sendDataToKafka(String message, String level) {
      Map<String, Object> stringObjectMap = new HashMap();
      stringObjectMap.put("message", message);
      stringObjectMap.put("level", level);

      try {
         ProducerRecord<String, String> record = new ProducerRecord("dig_topic_send_logs_to_data_dog_analytics", Utils.convertHashMapToJsonString(stringObjectMap));
         this.producer.send(record, (metadata, exception) -> {
         });
      } catch (Exception e) {
         Logger.error("Error while producing message: " + e.getMessage());
         this.producer.close();
      }

   }
}
