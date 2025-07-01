package com.gafapay.elasticsearch.filter;

import com.gafapay.elasticsearch.helper.ElasticSearchUtility;
import com.gafapay.elasticsearch.utils.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.ThreadContext;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Component
public class ElasticSearchFilter implements Filter {
   @Value("${checksum}")
   private boolean checkSum;
   private final ElasticSearchUtility elasticSearchUtility;

   public ElasticSearchFilter(ElasticSearchUtility baseUtility) {
      this.elasticSearchUtility = baseUtility;
   }

   public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException {
      long startTime = Instant.now().getEpochSecond();
      HttpServletRequest request = (HttpServletRequest)servletRequest;
      HttpServletResponse response = (HttpServletResponse)servletResponse;
      ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
      String requestId = StringUtils.isEmpty(request.getHeader("RequestID")) ? String.valueOf(Instant.now().getEpochSecond()) : request.getHeader("RequestID");
      Map<String, Object> stringObjectHashMap = new HashMap();
      stringObjectHashMap.put("api_endpoint", request.getRequestURI());
      stringObjectHashMap.put("api_method", request.getMethod());
      MDC.put("request_id", requestId);
      MDC.put("api_endpoint", request.getRequestURI());
      MDC.put("api_method", request.getMethod());
      this.setRequestHeaders(stringObjectHashMap, request);

      try {
         if ((!Objects.isNull(request.getQueryString()) || request.getRequestURI().split("/digipay/v3/analytics/")[1].split("/").length == 1) && "GET".equalsIgnoreCase(request.getMethod())) {
            stringObjectHashMap.put("payload", StringUtils.isEmpty(request.getQueryString()) ? request.getQueryString() : URLDecoder.decode(request.getQueryString()));
            HttpServletRequest modifiedRequest = new SomeHttpServletRequest(request);
            if (this.checkSum && this.elasticSearchUtility.verifyCheckSum(stringObjectHashMap.get("payload") == null ? "" : String.valueOf(stringObjectHashMap.get("payload")), request, 2, request.getHeader("signature"))) {
               this.sendErrorResponse(response);
               return;
            }

            filterChain.doFilter(modifiedRequest, responseWrapper);
         } else if (!"POST".equalsIgnoreCase(request.getMethod()) && !"PUT".equalsIgnoreCase(request.getMethod())) {
            String[] restOfUrl = request.getRequestURI().split("/");
            if (restOfUrl.length > 5) {
               stringObjectHashMap.put("payload", restOfUrl[5]);
               if (Objects.equals(request.getMethod(), "GET") && this.checkSum && this.elasticSearchUtility.verifyCheckSum(String.valueOf(stringObjectHashMap.get("payload")), request, request.getMethod().equalsIgnoreCase("GET") ? 3 : 5, request.getHeader("signature"))) {
                  this.sendErrorResponse(response);
                  return;
               }

               filterChain.doFilter(request, response);
            } else {
               stringObjectHashMap.put("payload", request.getQueryString());
               filterChain.doFilter(servletRequest, responseWrapper);
            }
         } else if (request.getContentType() != null && request.getContentType().equals("application/json")) {
            ReadableRequestWrapper requestWrapper = new ReadableRequestWrapper(request);
            stringObjectHashMap.put("payload", requestWrapper.getReader().lines().collect(Collectors.joining()));
            if (Objects.equals(request.getMethod(), "POST") && this.checkSum && this.elasticSearchUtility.verifyCheckSum(String.valueOf(stringObjectHashMap.get("payload")), request, 1, request.getHeader("signature"))) {
               this.sendErrorResponse(response);
               return;
            }

            filterChain.doFilter(requestWrapper, responseWrapper);
         } else {
            ReadableRequestWrapper requestWrapper = new ReadableRequestWrapper(request);
            if (Objects.equals(request.getMethod(), "POST") && this.checkSum && this.elasticSearchUtility.verifyCheckSum((String)requestWrapper.getReader().lines().collect(Collectors.joining()), request, 1, request.getHeader("signature"))) {
               this.sendErrorResponse(response);
               return;
            }

            filterChain.doFilter(servletRequest, responseWrapper);
         }

         String responseBody = this.getStringValue(responseWrapper.getContentAsByteArray(), response.getCharacterEncoding());
         MDC.put("status_code", String.valueOf(response.getStatus()));
         this.sendAPILogToKafka(responseBody, "Info", startTime, stringObjectHashMap, requestId);
         responseWrapper.copyBodyToResponse();
      } catch (Exception var14) {
         Exception ex = var14;
         MDC.put("status_code", String.valueOf(response.getStatus()));
         this.sendAPILogToKafka(ExceptionUtils.getStackTrace(var14), "Error", startTime, stringObjectHashMap, requestId);
         responseWrapper.copyBodyToResponse();

         try {
            throw new Exception(ex);
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }

      ThreadContext.clearMap();
   }

   private void sendAPILogToKafka(String responseBody, String logStatusType, Long startTime, Map<String, Object> stringObjectMap, String requestId) {
      stringObjectMap.put("response", responseBody);
      stringObjectMap.put("log_status_type", logStatusType);
      stringObjectMap.put("duration", Instant.now().getEpochSecond() - startTime);
   }

   private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
      try {
         return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
      } catch (UnsupportedEncodingException e) {
         Logger.error(e.getMessage());
         return "";
      }
   }

   public void destroy() {
   }

   public void sendErrorResponse(HttpServletResponse response) {
      Map<String, Object> mapBodyException = new HashMap();
      mapBodyException.put("status", "error");
      mapBodyException.put("code", HttpStatus.UNAUTHORIZED.value());
      mapBodyException.put("message", HttpStatus.UNAUTHORIZED.name());
      response.setContentType("application/json");
      response.setStatus(HttpStatus.UNAUTHORIZED.value());

      try {
         (new ObjectMapper()).writeValue(response.getOutputStream(), mapBodyException);
      } catch (IOException exception) {
         throw new RuntimeException(exception);
      }
   }

   private void setRequestHeaders(Map<String, Object> stringObjectMap, HttpServletRequest request) {
      Enumeration<String> headerNames = request.getHeaderNames();
      if (!Objects.isNull(headerNames)) {
         Map<String, Object> headers = new HashMap();

         try {
            while(headerNames.hasMoreElements()) {
               String headerName = (String)headerNames.nextElement();
               headers.put(headerName, request.getHeader(headerName));
               if (headerName.equalsIgnoreCase("user_id")) {
                  stringObjectMap.put("user_id", headers.get(headerName));
               }

               if (headerName.equalsIgnoreCase("host")) {
                  stringObjectMap.put("hostname", headers.get(headerName));
               }

               if (headerName.equalsIgnoreCase("ip_address")) {
                  stringObjectMap.put("ip_address", headers.get(headerName));
               }
            }

            stringObjectMap.put("headers", (new ObjectMapper()).valueToTree(headers));
         } catch (Exception ex) {
            System.out.println(" Error : " + ExceptionUtils.getStackTrace(ex));
         }

      }
   }

   public static class ReadableRequestWrapper extends HttpServletRequestWrapper {
      private final Charset encoding;
      private final byte[] rawData;

      private ReadableRequestWrapper(HttpServletRequest request) throws IOException {
         super(request);
         String charEncoding = request.getCharacterEncoding();
         this.encoding = StringUtils.isBlank(charEncoding) ? StandardCharsets.UTF_8 : Charset.forName(charEncoding);
         InputStream is = request.getInputStream();
         ByteArrayOutputStream os = new ByteArrayOutputStream();
         byte[] buffer = new byte[1024];

         int len;
         while((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
         }

         this.rawData = os.toByteArray();
         PrintStream var10000 = System.out;
         String var10001 = request.getMethod();
         var10000.println(var10001 + " Request Parameter" + (String)this.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
      }

      public ServletInputStream getInputStream() {
         final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.rawData);
         return new ServletInputStream() {
            public boolean isFinished() {
               return false;
            }

            public boolean isReady() {
               return false;
            }

            public void setReadListener(ReadListener readListener) {
            }

            public int read() {
               return byteArrayInputStream.read();
            }
         };
      }

      public BufferedReader getReader() {
         return new BufferedReader(new InputStreamReader(this.getInputStream(), this.encoding));
      }
   }

   static class SomeHttpServletRequest extends HttpServletRequestWrapper {
      HttpServletRequest request;

      SomeHttpServletRequest(final HttpServletRequest request) {
         super(request);
         this.request = request;
      }

      public String getQueryString() {
         return this.request.getQueryString();
      }

      public Map<String, String[]> getParameterMap() {
         String queryString = this.getQueryString();
         return this.getParamsFromQueryString(queryString);
      }

      public Enumeration<String> getParameterNames() {
         return Collections.enumeration(this.getParameterMap().keySet());
      }

      public String[] getParameterValues(final String name) {
         return (String[])this.getParameterMap().get(name);
      }

      private Map<String, String[]> getParamsFromQueryString(final String queryString) {
         String[] params = queryString.split("&");
         Map<String, List<String>> collect = (Map)Stream.of(params).map((x) -> x.split("=")).collect(Collectors.groupingBy((x) -> x[0], Collectors.mapping((x) -> x.length > 1 ? x[1] : null, Collectors.toList())));
         return (Map)collect.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, (x) -> (String[])((List)x.getValue()).toArray(new String[0])));
      }
   }
}
