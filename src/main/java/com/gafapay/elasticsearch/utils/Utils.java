package com.gafapay.elasticsearch.utils;

import com.amazonaws.services.s3.model.PutObjectRequest;
import com.datadog.api.client.ApiClient;
import com.datadog.api.client.v2.api.LogsApi;
import com.datadog.api.client.v2.model.ContentEncoding;
import com.datadog.api.client.v2.model.HTTPLogItem;
import com.gafapay.elasticsearch.api.commonresponse.ErrorResponse;
import com.gafapay.elasticsearch.api.commonresponse.SuccessResponse;
import com.gafapay.elasticsearch.helper.ApplicationPropertiesReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.kafka.common.serialization.StringSerializer;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpHeaders;

public class Utils {
   @Value("{$data.dog.log.service.name}")
   private static String dataDogServiceName;
   @Value("{$data.dog.log.source.name}")
   private static String dataDogSourceName;
   @Value("{$data.dog.log.api.key}")
   private String dataDogApiKey;

   public Utils() {
   }

   public static HashMap<String, String> getHeaders(HttpHeaders headers) {
      HashMap<String, String> map = new HashMap();
      String data = null;
      if (headers.containsKey("CompanyID")) {
         List<String> headerOptional = headers.get("CompanyID");
         if (!Objects.isNull(headerOptional) && !headerOptional.isEmpty()) {
            data = (String)headerOptional.get(0);
         }

         Logger.info("Company ID : " + data);
         map.put("CompanyID", data);
      }

      if (headers.containsKey("token_user_id")) {
         List<String> headerOptional = headers.get("token_user_id");
         if (!Objects.isNull(headerOptional) && !headerOptional.isEmpty()) {
            data = (String)headerOptional.get(0);
         }

         Logger.info("Token User ID : " + data);
         map.put("token_user_id", data);
      }

      if (headers.containsKey("Authorization")) {
         List<String> headerOptional = headers.get("Authorization");
         if (!Objects.isNull(headerOptional) && !headerOptional.isEmpty()) {
            data = (String)headerOptional.get(0);
         }

         map.put("Authorization", data);
      }

      if (headers.containsKey("RequestID")) {
         List<String> headerOptional = headers.get("RequestID");
         if (!Objects.isNull(headerOptional) && !headerOptional.isEmpty()) {
            data = (String)headerOptional.get(0);
         }

         Logger.info("Request ID : " + data);
         map.put("RequestID", data);
      }

      if (headers.containsKey("remoteAddress")) {
         List<String> headerOptional = headers.get("remoteAddress");
         if (!Objects.isNull(headerOptional) && !headerOptional.isEmpty()) {
            data = (String)headerOptional.get(0);
         }

         Logger.info("Remote Address : " + data);
         map.put("remoteAddress", data);
      }

      if (headers.containsKey("Language")) {
         List<String> headerOptional = headers.get("Language");
         if (!Objects.isNull(headerOptional) && !headerOptional.isEmpty()) {
            data = (String)headerOptional.get(0);
         }

         Logger.info("Language : " + data);
         map.put("Language", data);
      }

      return map;
   }

   public static JsonNode generateSuccessResponse(Object object) {
      SuccessResponse successResponse = new SuccessResponse();
      successResponse.setSuccess(1);
      successResponse.setData(object);
      successResponse.setError(new ArrayList());
      ObjectMapper mapper = new ObjectMapper();
      mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
      return (JsonNode)mapper.convertValue(successResponse, JsonNode.class);
   }

   public static JsonNode generateErrorResponse(String message) {
      ErrorResponse successResponse = new ErrorResponse();
      successResponse.setSuccess(0);
      successResponse.setData(new Object());
      ArrayList<String> errors = new ArrayList();
      errors.add(message);
      successResponse.setError(errors);
      ObjectMapper mapper = new ObjectMapper();
      mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
      return (JsonNode)mapper.convertValue(successResponse, JsonNode.class);
   }

   public static String generateUUID() {
      UUID uuid = UUID.randomUUID();
      String var10000 = Long.toHexString(uuid.getMostSignificantBits());
      return var10000 + Long.toHexString(uuid.getLeastSignificantBits());
   }

   public static String generateAuditLogsNo() {
      String staticKeyword = "AUD_";
      return staticKeyword.concat(String.valueOf(Instant.now().getEpochSecond()));
   }

   public static String convertObjectToJsonString(Object object, String printMessage) {
      try {
         ObjectMapper objectMapper = new ObjectMapper();
         String rules = objectMapper.writeValueAsString(object);
         Logger.info(printMessage + rules);
         return rules;
      } catch (Exception ex) {
         Logger.error(ex.getMessage());
         return null;
      }
   }

   public static String convertHashMapToJsonString(Map<String, Object> objectHashMap) {
      ObjectMapper objectMapper = new ObjectMapper();

      try {
         return objectMapper.writeValueAsString(objectHashMap);
      } catch (Exception ex) {
         Logger.error(" Error At Conversion of HashMap To Json String Method : " + ExceptionUtils.getStackTrace(ex));
         return null;
      }
   }

   public static Map<String, Object> convertJsonStringToHashMap(String jsonString) {
      ObjectMapper objectMapper = new ObjectMapper();

      try {
         Map<String, Object> stringObjectMap = (Map)objectMapper.readValue(jsonString, new TypeReference<HashMap<String, Object>>() {
         });
         return stringObjectMap;
      } catch (JsonProcessingException var4) {
         Logger.error(" Error At Conversion of Json String To Hash Map Method : ");
         return null;
      }
   }

   public static <T> T convertHashMapToClassObject(Map<String, Object> data, T classObject) {
      if (Objects.isNull(classObject)) {
         return null;
      } else {
         try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            return (T)objectMapper.convertValue(data, classObject.getClass());
         } catch (Exception ex) {
            Logger.error(" Error : " + ExceptionUtils.getStackTrace(ex));
            return null;
         }
      }
   }

   public static Object getValueFromMapKey(Map<String, Object> stringObjectMap, String key) {
      if (!Objects.isNull(stringObjectMap) && !stringObjectMap.isEmpty()) {
         if (!stringObjectMap.containsKey(key)) {
            return null;
         } else {
            return Objects.isNull(stringObjectMap.get(key)) ? null : stringObjectMap.get(key).toString();
         }
      } else {
         return null;
      }
   }

   public static Long getStartDateForESFilter(Long startDate) {
      return Objects.isNull(startDate) ? 0L : startDate;
   }

   public static Long getEndDateForESFilter(Long endDate) {
      return Objects.isNull(endDate) ? Instant.now().getEpochSecond() : endDate;
   }

   public static PageRequest getSkipAndLimitForESFilter(Integer skip, Integer limit) {
      if (Objects.isNull(skip) && Objects.isNull(limit)) {
         return PageRequest.of(0, 100);
      } else if (!Objects.isNull(skip) && Objects.isNull(limit)) {
         return PageRequest.of(skip, 100);
      } else {
         return Objects.isNull(skip) && !Objects.isNull(limit) ? PageRequest.of(0, limit) : PageRequest.of(Math.round((float)skip / (float)limit), limit);
      }
   }

   public static NativeSearchQueryBuilder sortBy(NativeSearchQueryBuilder query, String sorting) {
      ObjectMapper objectMapper = new ObjectMapper();
      if (StringUtils.isEmpty(sorting)) {
         return getDefaultSorting(query);
      } else {
         Map<String, Object> queryFilterMap;
         try {
            sorting = URLDecoder.decode(sorting, StandardCharsets.UTF_8.name());
            queryFilterMap = (Map)objectMapper.readValue(sorting, new TypeReference<HashMap<String, Object>>() {
            });
         } catch (Exception e) {
            Logger.error("Error : " + ExceptionUtils.getStackTrace(e));
            return getDefaultSorting(query);
         }

         if (!Objects.isNull(queryFilterMap) && !queryFilterMap.isEmpty()) {
            for(String key : queryFilterMap.keySet()) {
               query.withSorts(new SortBuilder[]{queryFilterMap.get(key).toString().equalsIgnoreCase("asc") ? SortBuilders.fieldSort(key).order(SortOrder.ASC) : SortBuilders.fieldSort(key).order(SortOrder.DESC)}).build();
            }

            return query;
         } else {
            return getDefaultSorting(query);
         }
      }
   }

   private static NativeSearchQueryBuilder getDefaultSorting(NativeSearchQueryBuilder query) {
      query.withSorts(new SortBuilder[]{SortBuilders.fieldSort("created_date").order(SortOrder.DESC)}).build();
      return query;
   }

   public static PutObjectRequest uploadFileToS3Bucket(String bucketName, File file) {
      Logger.info("File uploading in progress");
      return uploadFileToAwsBucket(bucketName, file);
   }

   private static PutObjectRequest uploadFileToAwsBucket(String bucketName, File file) {
      LocalDateTime var10000 = LocalDateTime.now();
      String uniqueFileName = var10000 + "_" + file.getName();
      Logger.info("Uploading file with name =" + uniqueFileName);
      return new PutObjectRequest(bucketName, uniqueFileName, file);
   }

   public static void printLogInDataDog(Map<String, Object> apiLogMap, String apiLogString, String logStatusType) {
      ObjectMapper mapper = new ObjectMapper();
      ApiClient defaultClient = ApiClient.getDefaultApiClient();
      defaultClient.setApiKey("78a9c09d717e5606c0d63c5dc6bf6572");
      LogsApi apiInstance = new LogsApi(defaultClient);
      List<HTTPLogItem> body = Collections.singletonList((new HTTPLogItem()).ddsource(dataDogSourceName).ddtags("env:staging,version:5.1").putAdditionalProperty("status", logStatusType).message(Objects.isNull(apiLogMap) ? apiLogString : mapper.valueToTree(apiLogMap).toPrettyString()).service(dataDogServiceName));

      try {
         apiInstance.submitLogAsync(body, (new LogsApi.SubmitLogOptionalParameters()).contentEncoding(ContentEncoding.DEFLATE));
      } catch (Exception e) {
         System.out.println("Exception : " + ExceptionUtils.getStackTrace(e));
      }

   }

   public static List<String> byPassAPIEndPoints() {
      return List.of("/digipay/v3/auth/user_register");
   }

   public static SearchSourceBuilder sortBySortBuilder(SearchSourceBuilder searchSourceBuilder, String sorting) {
      ObjectMapper objectMapper = new ObjectMapper();
      if (StringUtils.isEmpty(sorting)) {
         return searchSourceBuilder.sort(SortBuilders.fieldSort("created_date").order(SortOrder.DESC));
      } else {
         Map<String, Object> queryFilterMap;
         try {
            sorting = URLDecoder.decode(sorting, StandardCharsets.UTF_8.name());
            queryFilterMap = (Map)objectMapper.readValue(sorting, new TypeReference<HashMap<String, Object>>() {
            });
         } catch (Exception e) {
            Logger.error("Error : " + ExceptionUtils.getStackTrace(e));
            return searchSourceBuilder.sort(SortBuilders.fieldSort("created_date").order(SortOrder.DESC));
         }

         if (!Objects.isNull(queryFilterMap) && !queryFilterMap.isEmpty()) {
            for(String key : queryFilterMap.keySet()) {
               searchSourceBuilder.sort(queryFilterMap.get(key).toString().equalsIgnoreCase("asc") ? SortBuilders.fieldSort(key).order(SortOrder.ASC) : SortBuilders.fieldSort(key).order(SortOrder.DESC));
            }

            return searchSourceBuilder;
         } else {
            return searchSourceBuilder.sort(SortBuilders.fieldSort("created_date").order(SortOrder.DESC));
         }
      }
   }

   public static Properties getKafkaProperties() {
      Properties props = new Properties();
      props.put("bootstrap.servers", ApplicationPropertiesReader.getPropertyValueFromApplicationPropertyFile("kafka.bootstrap.servers"));
      props.put("retries", 0);
      props.put("batch.size", 16384);
      props.put("linger.ms", 1);
      props.put("buffer.memory", 33554432);
      props.put("key.serializer", StringSerializer.class);
      props.put("value.serializer", StringSerializer.class);
      return props;
   }

   public static String replaceString(String keyword) {
      if (keyword.contains("+")) {
         return keyword.replace("+", "\\+");
      } else if (keyword.contains("%22")) {
         return keyword.replace("%22", "\"");
      } else {
         return keyword.contains("%20") ? keyword.replace("%20", " ") : keyword;
      }
   }

   public static Criteria setStartDateAndEndDate(Long startDate, Long endDate, String fieldName) {
      if (!Objects.isNull(startDate) && !Objects.isNull(endDate)) {
         return Criteria.where(fieldName).gte(startDate).lte(endDate);
      } else if (Objects.isNull(startDate) && Objects.isNull(endDate)) {
         return Criteria.where(fieldName).gte(0L).lte(Instant.now().getEpochSecond());
      } else {
         return !Objects.isNull(startDate) ? Criteria.where(fieldName).gte(0L).lte(startDate) : Criteria.where(fieldName).gte(0L).lte(endDate);
      }
   }

   public static void sortByMongo(Query query, String sorting) {
      ObjectMapper objectMapper = new ObjectMapper();
      if (StringUtils.isEmpty(sorting)) {
         getDefaultSortingMongo(query);
      } else {
         Map<String, Object> queryFilterMap;
         try {
            sorting = URLDecoder.decode(sorting, StandardCharsets.UTF_8);
            queryFilterMap = (Map)objectMapper.readValue(sorting, new TypeReference<HashMap<String, Object>>() {
            });
         } catch (Exception e) {
            Logger.error("Error : " + ExceptionUtils.getStackTrace(e));
            getDefaultSortingMongo(query);
            return;
         }

         if (!Objects.isNull(queryFilterMap) && !queryFilterMap.isEmpty()) {
            for(String key : queryFilterMap.keySet()) {
               query.with(queryFilterMap.get(key).toString().equalsIgnoreCase("asc") ? Sort.by(Direction.ASC, new String[]{key}) : Sort.by(Direction.DESC, new String[]{key}));
            }

         } else {
            getDefaultSortingMongo(query);
         }
      }
   }

   private static Query getDefaultSortingMongo(Query query) {
      query.with(Sort.by(Direction.DESC, new String[]{"created_date"}));
      return query;
   }
}
