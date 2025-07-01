package com.gafapay.elasticsearch.kafka.producer;

import com.gafapay.elasticsearch.utils.Logger;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerSend {
   @Autowired
   private KafkaTemplate<String, String> kafkaTemplate;

   public KafkaProducerSend() {
   }

   public void sendReport(String message, String requestID) {
      Logger.info("Sending Report Process Message With Key : " + message);
      CompletableFuture<SendResult<String, String>> future = this.kafkaTemplate.send("dig_topic_elastic_search_send_report", requestID, message);
      this.whenComplete(message, future, "dig_topic_elastic_search_send_report");
   }

   private void whenComplete(String message, CompletableFuture<SendResult<String, String>> future, String topicNameLog) {
      future.whenComplete((stringStringSendResult, throwable) -> {
         if (Objects.isNull(throwable)) {
            Logger.info("Sent " + topicNameLog + " Log Message: " + message + " With Offset: " + stringStringSendResult.getRecordMetadata().offset());
         } else {
            Logger.error("Unable To Send " + topicNameLog + " Log Message : " + message + ExceptionUtils.getStackTrace(throwable));
         }

      });
   }

   public void sendToUpdateTxnMasterInElasticSearchMessage(String message, String requestID) {
      Logger.info("Sending Update Txn Master in ElasticSearch Message With Key : " + message);
      CompletableFuture<SendResult<String, String>> future = this.kafkaTemplate.send("dig_topic_txn_entry_update_in_elastic_search", requestID, message);
      this.whenComplete(message, future, "dig_topic_txn_entry_update_in_elastic_search");
   }

   public void sendCommonAPILogMessage(String message, String requestID) {
      Logger.info("Sending Common API Log Message With Key : " + message);
      CompletableFuture<SendResult<String, String>> future = this.kafkaTemplate.send("dig_topic_common_api_logs", requestID, message);
      this.whenComplete(message, future, "dig_topic_common_api_logs");
   }

   public void removeExpiredTokenFromDataBase(String message, String requestID) {
      Logger.info("Sending Common API Log Message With Key : " + message);
      CompletableFuture<SendResult<String, String>> future = this.kafkaTemplate.send("dig_topic_remove_token", requestID, message);
      this.whenComplete(message, future, "dig_topic_remove_token");
   }

   public void sendNotificationMessage(String message, String requestID) {
      Logger.info("Sending Notification Message With Key : " + message);
      CompletableFuture<SendResult<String, String>> future = this.kafkaTemplate.send("dig_topic_send_notification", requestID, message);
      this.whenComplete(message, future, "dig_topic_send_notification");
   }
}
