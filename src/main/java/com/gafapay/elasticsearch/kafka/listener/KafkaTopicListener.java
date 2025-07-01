package com.gafapay.elasticsearch.kafka.listener;

import com.amazonaws.services.s3.AmazonS3;
import com.gafapay.elasticsearch.helper.ElasticSearchUtility;
import com.gafapay.elasticsearch.utils.Logger;
import com.gafapay.elasticsearch.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
class KafkaTopicListener {
   @Autowired
   private ElasticSearchUtility elasticSearchUtility;
   @Value("${aws.s3.bucket.name}")
   private String bucketName;
   @Autowired
   private AmazonS3 amazonS3;

   KafkaTopicListener() {
   }

   @KafkaListener(
      topics = {"dig_topic_elastic_search_activity_logs"},
      containerFactory = "elasticSearchDockerkafkaListenerContainerFactory"
   )
   public void consumeActivityLogs(@Payload String message, @Header(name = "kafka_receivedMessageKey",required = false) String requestID) {
      try {
         MDC.put("RequestID", requestID);
         Logger.info("Request ID : " + requestID);
         this.elasticSearchUtility.elasticSearchActivityLogProcessStart(message);
      } catch (Exception var4) {
         this.elasticSearchUtility.insertKafkaLogInDataDog((Map)null, "Error", "Elastic Search");
      }

   }

   @KafkaListener(
      topics = {"dig_topic_elastic_search_transaction_logs"},
      containerFactory = "elasticSearchDockerkafkaListenerContainerFactory"
   )
   public void consumeTxnMaster(@Payload String message, @Header(name = "kafka_receivedMessageKey",required = false) String requestID) {
      try {
         MDC.put("RequestID", requestID);
         Logger.info("Request ID : " + requestID);
         Logger.info("Received Message From Topic dig_topic_elastic_search_transaction_logs" + message + " In Listener ");
         this.elasticSearchUtility.elasticSearchTxnMasterProcessStart(message);
      } catch (Exception ex) {
         ObjectMapper objectMapper = new ObjectMapper();
         Map<String, Object> stringObjectMap = new HashMap();
         stringObjectMap.put("request_id", StringUtils.isEmpty(requestID) ? Instant.now().getEpochSecond() : requestID);
         stringObjectMap.put("kafka_message", objectMapper.valueToTree(Utils.convertJsonStringToHashMap(message)).toPrettyString());
         stringObjectMap.put("error", ExceptionUtils.getStackTrace(ex));
         this.elasticSearchUtility.insertKafkaLogInDataDog(stringObjectMap, "Error", "Elastic Search");
         Logger.error(ExceptionUtils.getStackTrace(ex));
      }

   }

   @KafkaListener(
      topics = {"dig_topic_txn_entry_update_in_elastic_search"},
      containerFactory = "elasticSearchDockerkafkaListenerContainerFactory"
   )
   public void consumeUpdateTxnMaster(@Payload String message, @Header(name = "kafka_receivedMessageKey",required = false) String requestID) {
      try {
         MDC.put("RequestID", requestID);
         Logger.info("Request ID : " + requestID);
         Logger.info("Received Message From Topic dig_topic_txn_entry_update_in_elastic_search" + message + " In Listener ");
         this.elasticSearchUtility.elasticSearchUpdateTxnStatusProcessStart(message, requestID);
      } catch (Exception exception) {
         Logger.error("Error : " + ExceptionUtils.getStackTrace(exception));
      }

   }

   @KafkaListener(
      topics = {"dig_topic_common_api_logs"},
      containerFactory = "elasticSearchDockerkafkaListenerContainerFactory"
   )
   public void consumeCommonAPILogs(@Payload String message, @Header(name = "kafka_receivedMessageKey",required = false) String requestID) {
      MDC.put("RequestID", requestID);
      this.elasticSearchUtility.commonAPILogsProcessStart(message);
   }

   @KafkaListener(
      topics = {"dig_topic_elastic_search_send_report"},
      containerFactory = "elasticSearchDockerkafkaListenerContainerFactory"
   )
   public void consumeSendReport(@Payload String message, @Header(name = "kafka_receivedMessageKey",required = false) String requestID) {
      MDC.put("RequestID", requestID);
      Logger.info("Request ID : " + requestID);
      Logger.info("Received Message From Topic dig_topic_elastic_search_send_report" + message + " In Listener ");
      Logger.info(message);
      Map<String, Object> sendReportObject = Utils.convertJsonStringToHashMap(message);
      File currDir = new File("test");
      int reportType = Integer.valueOf(sendReportObject.get("report_type").toString());

      try {
         if (reportType == 1) {
            File file = ElasticSearchUtility.excelToPDF(currDir);
            this.amazonS3.putObject(Utils.uploadFileToS3Bucket(this.bucketName, currDir));
            byte[] byteForFiles = IOUtils.toByteArray(new FileInputStream(file));
            ElasticSearchUtility.sendEmail((List)sendReportObject.get("to_emails"), sendReportObject.get("subject").toString(), sendReportObject.get("body").toString(), byteForFiles, reportType, sendReportObject.get("report_name").toString());
            if (file.delete()) {
               Logger.info("Delete");
            } else {
               Logger.error("Not Delete");
            }
         } else {
            this.amazonS3.putObject(Utils.uploadFileToS3Bucket(this.bucketName, currDir));
            byte[] byteForFiles = IOUtils.toByteArray(new FileInputStream(currDir));
            ElasticSearchUtility.sendEmail((List)sendReportObject.get("to_emails"), sendReportObject.get("subject").toString(), sendReportObject.get("body").toString(), byteForFiles, reportType, sendReportObject.get("report_name").toString());
            if (currDir.delete()) {
               Logger.info("Delete");
            } else {
               Logger.error("Not Delete");
            }
         }
      } catch (Exception e) {
         Logger.error(e.getMessage());
      }

   }

   @KafkaListener(
      topics = {"dig_topic_audit_logs_listen"},
      containerFactory = "elasticSearchDockerkafkaListenerContainerFactory"
   )
   public void consumeKafkaChangeStreamLogs(@Payload String message, @Header(name = "kafka_receivedMessageKey",required = false) String requestID) {
      MDC.put("RequestID", requestID);
      Logger.info("Request ID : " + requestID);
      Logger.info("Received Message From Topic dig_topic_audit_logs_listen" + message + " In Listener ");
      this.elasticSearchUtility.changeStreamLogFromKafka(message);
   }

   @KafkaListener(
      topics = {"dig_topic_make_entry_in_es_txn_user_wallet"},
      containerFactory = "elasticSearchDockerkafkaListenerContainerFactory"
   )
   public void makeEntryInESTxnUserWallet(@Payload String message, @Header(name = "kafka_receivedMessageKey",required = false) String requestID) {
      try {
         MDC.put("RequestID", requestID);
         Logger.info(" Request ID : " + requestID);
         Logger.info("Received Message From From Topic dig_topic_make_entry_in_es_txn_user_wallet" + message + " In Listener ");
         this.elasticSearchUtility.makeEntryInESTxnUserWallet(message, requestID);
      } catch (Exception ex) {
         Logger.error("Error : " + ExceptionUtils.getStackTrace(ex));
      }

   }

   @KafkaListener(
      topics = {"dig_topic_nodal_bank_transaction_in_elasticsearch"},
      containerFactory = "elasticSearchDockerkafkaListenerContainerFactory"
   )
   public void updateNodalBankTransaction(@Payload String message, @Header(name = "kafka_receivedMessageKey",required = false) String requestID) {
      try {
         MDC.put("RequestID", requestID);
         Logger.info(" Request ID : " + requestID);
         Logger.info("Received Message From From Topic dig_topic_nodal_bank_transaction_in_elasticsearch" + message + " In Listener ");
         this.elasticSearchUtility.updateNodalBankTransaction(message);
      } catch (Exception ex) {
         Logger.error("Error : " + ExceptionUtils.getStackTrace(ex));
      }

   }

   @KafkaListener(
      topics = {"dig_topic_send_logs_to_data_dog_analytics"},
      containerFactory = "elasticSearchDockerkafkaListenerContainerFactory"
   )
   public void sendToDataDog(@Payload String message, @Header(name = "kafka_receivedMessageKey",required = false) String requestID) {
      MDC.put("RequestID", requestID);
      this.elasticSearchUtility.sendDataToDataDog(message);
   }

   @KafkaListener(
      topics = {"dig_topic_send_revenue_calculation_on_txn_charges_to_analytics"},
      containerFactory = "elasticSearchDockerkafkaListenerContainerFactory"
   )
   public void sendThirdPartyTxnCommissionFessToAnaytics(@Payload String message, @Header(name = "kafka_receivedMessageKey",required = false) String requestID) {
      try {
         MDC.put("RequestID", requestID);
         Logger.info(" Request ID : " + requestID);
         Logger.info("Received Message From From Topic dig_topic_send_revenue_calculation_on_txn_charges_to_analytics" + message + " In Listener ");
         this.elasticSearchUtility.sendThirdPartyTxnCommissionFessToAnalyticsProcessStart(message);
      } catch (Exception ex) {
         Logger.error("Error : " + ExceptionUtils.getStackTrace(ex));
      }

   }

   @KafkaListener(
      topics = {"dig_topic_send_delete_audit_logs_to_datadog"},
      containerFactory = "elasticSearchDockerkafkaListenerContainerFactory"
   )
   public void sendDeleteAuditLogsDataToDatadog(@Payload String message, @Header(name = "kafka_receivedMessageKey",required = false) String requestID) {
      try {
         MDC.put("RequestID", requestID);
         Logger.info(" Request ID : " + requestID);
         Logger.info("Received Message From From Topic dig_topic_send_delete_audit_logs_to_datadog" + message + " In Listener ");
         this.elasticSearchUtility.sendDeleteAuditLogsDataToDatadogProcessStart(message);
      } catch (Exception ex) {
         Logger.error("Error : " + ExceptionUtils.getStackTrace(ex));
      }

   }
}
