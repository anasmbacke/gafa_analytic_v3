package com.gafapay.elasticsearch.helper;

import com.gafapay.elasticsearch.utils.Logger;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationPropertiesReader {
   public ApplicationPropertiesReader() {
   }

   public static String getPropertyValueFromApplicationPropertyFile(String key) {
      try {
         Properties properties = new Properties();
         InputStream inputStream = ApplicationPropertiesReader.class.getClassLoader().getResourceAsStream("application.properties");
         properties.load(inputStream);
         return properties.getProperty(key);
      } catch (Exception exception) {
         Logger.error(" No such property found for key " + key);
         Logger.error(exception.getMessage());
         return null;
      }
   }
}
