package com.gafapay.elasticsearch.utils;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.LoggerFactory;

public class Logger {
   public static boolean isLogEnable = true;
   public static final org.slf4j.Logger logger = LoggerFactory.getLogger("application.ElasticSearchService");

   public Logger() {
   }

   public static void info(String message) {
      if (isLogEnable) {
         logger.info(" {} : Class : {}", message, Thread.currentThread().getStackTrace()[2].toString());
      }

   }

   public static void info(JsonNode message) {
      if (isLogEnable) {
         logger.info(" {} : Class : {}", message.toString(), Thread.currentThread().getStackTrace()[2].toString());
      }

   }

   public static void error(String message) {
      if (isLogEnable) {
         logger.error(" {} : Class : {}", message, Thread.currentThread().getStackTrace()[2].toString());
      }

   }

   public static void debug(String message) {
      if (isLogEnable) {
         logger.debug(" {} : Class : {}", message, Thread.currentThread().getStackTrace()[2].toString());
      }

   }
}
