package com.gafapay.elasticsearch.helper;

import com.gafapay.elasticsearch.utils.Logger;
import com.gafapay.elasticsearch.utils.Utils;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;

public class HeaderProcessingHelper {
   public HeaderProcessingHelper() {
   }

   public static <T> void setRequestHeaders(T requestObject, HttpHeaders headers) {
      Map<String, String> mapForHeader = Utils.getHeaders(headers);
      String companyId = (String)mapForHeader.get("CompanyID");
      String requestId = (String)mapForHeader.get("RequestID");
      String ip_address = (String)mapForHeader.get("remoteAddress");
      String tokenUserId = (String)mapForHeader.get("token_user_id");

      try {
         if (mapForHeader.containsKey("CompanyID")) {
            requestObject.getClass().getSuperclass().getDeclaredMethod("setCompany_id", String.class).invoke(requestObject, companyId);
         }

         if (mapForHeader.containsKey("token_user_id")) {
            requestObject.getClass().getSuperclass().getDeclaredMethod("setToken_user_id", String.class).invoke(requestObject, tokenUserId);
         }

         if (mapForHeader.containsKey("RequestID")) {
            requestObject.getClass().getSuperclass().getDeclaredMethod("setRequest_id", String.class).invoke(requestObject, requestId);
         }

         if (mapForHeader.containsKey("remoteAddress")) {
            requestObject.getClass().getSuperclass().getDeclaredMethod("setIp_address", String.class).invoke(requestObject, ip_address);
         }
      } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
         Logger.error("Error : " + ExceptionUtils.getStackTrace(e));
      }

   }
}
