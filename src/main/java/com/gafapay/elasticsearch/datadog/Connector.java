package com.gafapay.elasticsearch.datadog;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.StackTraceElementProxy;

public class Connector {
   private final KafkaConnector kafkaConnector;

   public Connector(KafkaConnector kafkaConnector) {
      this.kafkaConnector = kafkaConnector;
   }

   public void connector(ILoggingEvent loggingEventVO) {
      if (null != loggingEventVO.getThrowableProxy()) {
         StringBuilder stackTraceString = new StringBuilder();
         StackTraceElementProxy[] stackTraceElements = loggingEventVO.getThrowableProxy().getStackTraceElementProxyArray();
         stackTraceString.append(loggingEventVO.getThrowableProxy().getMessage());
         stackTraceString.append(".\n");

         for(StackTraceElementProxy stackTraceElementProxy : stackTraceElements) {
            stackTraceString.append(stackTraceElementProxy.toString());
            stackTraceString.append(".\n");
         }

         this.kafkaConnector.sendDataToKafka(stackTraceString.toString(), "ERROR");
      } else {
         this.kafkaConnector.sendDataToKafka(loggingEventVO.getFormattedMessage(), loggingEventVO.getLevel().toString());
      }

   }
}
