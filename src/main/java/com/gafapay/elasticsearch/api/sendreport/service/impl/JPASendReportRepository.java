package com.gafapay.elasticsearch.api.sendreport.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.dao.UserMasterDataQueryDao;
import com.gafapay.elasticsearch.api.sendreport.handler.SendReportRequest;
import com.gafapay.elasticsearch.api.sendreport.model.request.ReportData;
import com.gafapay.elasticsearch.api.sendreport.model.request.SendNotificationData;
import com.gafapay.elasticsearch.api.sendreport.service.SendReportRepository;
import com.gafapay.elasticsearch.database.mongodb.CompanyModuleConfigData;
import com.gafapay.elasticsearch.database.mongodb.CompanyModuleData;
import com.gafapay.elasticsearch.database.mongodb.ReportExportFiles;
import com.gafapay.elasticsearch.database.mongodb.UserMasterData;
import com.gafapay.elasticsearch.database.repository.mongodb.CompanyModuleConfigMasterDataQueryDao;
import com.gafapay.elasticsearch.database.repository.mongodb.CompanyModuleDataQueryDao;
import com.gafapay.elasticsearch.database.repository.mongodb.ReportExportFilesDao;
import com.gafapay.elasticsearch.helper.ElasticSearchUtility;
import com.gafapay.elasticsearch.kafka.producer.KafkaProducerSend;
import com.gafapay.elasticsearch.utils.Logger;
import com.gafapay.elasticsearch.utils.PdfReportGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JPASendReportRepository implements SendReportRepository {
   @Autowired
   private KafkaProducerSend kafkaProducerSend;
   @Autowired
   private AmazonS3 amazonS3;
   @Value("${aws.s3.bucket.name}")
   private String bucketName;
   @Autowired
   private ReportExportFilesDao reportExportFilesDao;
   @Autowired
   private CompanyModuleDataQueryDao companyModuleDataQueryDao;
   @Autowired
   private CompanyModuleConfigMasterDataQueryDao companyModuleConfigMasterDataQueryDao;
   @Autowired
   private UserMasterDataQueryDao userMasterDataQueryDao;
   @Autowired
   private ElasticSearchUtility elasticSearchUtility;

   public JPASendReportRepository() {
   }

   public CommonAPIDataResponse sendStatisticsReport(SendReportRequest sendReportRequest) throws IOException {
      CommonAPIDataResponse commonAPIDataResponse = new CommonAPIDataResponse();
      UserMasterData userMasterData = this.userMasterDataQueryDao.getAdminInfo(sendReportRequest.getCompany_id());
      switch (sendReportRequest.getReport_type()) {
         case 1:
         case 2:
            commonAPIDataResponse = this.createReportPDForExcel(sendReportRequest, userMasterData);
            break;
         case 3:
            ReportExportFiles reportExportFiles = ((ReportExportFiles.ReportExportFilesBuilder)((ReportExportFiles.ReportExportFilesBuilder)ReportExportFiles.builder().reportDate(Instant.now().getEpochSecond()).reportName(sendReportRequest.getReport_name()).downloadUrl(sendReportRequest.getImage_url()).fileFormat(sendReportRequest.getReport_type()).filterApplied((Map)null).userId(sendReportRequest.getToken_user_id()).userType((Integer)null).reportDate((Long)null).companyId(sendReportRequest.getCompany_id())).createdBy(sendReportRequest.getToken_user_id())).build();
            this.reportExportFilesDao.save(reportExportFiles);
            if (!Objects.isNull(userMasterData) && !StringUtils.isEmpty(userMasterData.getEmail())) {
               SendNotificationData sendNotificationData = SendNotificationData.builder().event_type(197).user_id(userMasterData.getId()).company_id(sendReportRequest.getCompany_id()).file_attached(sendReportRequest.getImage_url()).build();
               this.elasticSearchUtility.sendNotificationDataToKafka(sendNotificationData, sendReportRequest.getRequest_id());
            }

            commonAPIDataResponse.setMessage("ELASTIC_SEARCH_REPORT_SENT_SUCCESS");
      }

      return commonAPIDataResponse;
   }

   private CommonAPIDataResponse createReportPDForExcel(SendReportRequest sendReportRequest, UserMasterData userMasterData) throws IOException {
      CommonAPIDataResponse commonAPIDataResponse = new CommonAPIDataResponse();

      ReportData reportData;
      try {
         ObjectMapper objectMapper = new ObjectMapper();
         reportData = (ReportData)objectMapper.readValue(sendReportRequest.getReport_file().getInputStream(), ReportData.class);
      } catch (IOException e) {
         Logger.info("Error File Upload......." + e);
         commonAPIDataResponse.setCheckValidationFailed(true);
         commonAPIDataResponse.setValidationMessage("Error processing the uploaded JSON file");
         return commonAPIDataResponse;
      }

      try {
         int reportType = sendReportRequest.getReport_type();
         ByteArrayOutputStream reportOutputStream;
         String fileExtension;
         String contentType;
         if (Objects.equals(1, reportType)) {
            reportOutputStream = new ByteArrayOutputStream();
            PdfReportGenerator.generatePDF(reportData, reportOutputStream);
            fileExtension = "pdf";
            contentType = "application/pdf";
         } else {
            Workbook workbook = ElasticSearchUtility.generateExcelFileFromJson(reportData);
            reportOutputStream = new ByteArrayOutputStream();
            workbook.write(reportOutputStream);
            fileExtension = "xlsx";
            contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
         }

         CompanyModuleData companyModuleData = this.companyModuleDataQueryDao.getCompanyModuleByCompanyIdAndGroupKey(sendReportRequest.getCompany_id(), "image_upload_settings");
         if (Objects.isNull(companyModuleData)) {
            Logger.info("company module data not found");
            commonAPIDataResponse.setCheckValidationFailed(true);
            commonAPIDataResponse.setValidationMessage("CONFIGURATION_NOT_FOUND");
            return commonAPIDataResponse;
         } else {
            List<CompanyModuleConfigData> companyModuleConfigDataList = this.companyModuleConfigMasterDataQueryDao.findByCompanyIdAndModuleId(sendReportRequest.getCompany_id(), companyModuleData.getId());
            if (!Objects.isNull(companyModuleConfigDataList) && !companyModuleConfigDataList.isEmpty()) {
               String endPoint = "";
               String accessKey = "";
               String secretKey = "";
               String bucketName = "";
               String viewEndPoint = "";
               String region = "";
               String uploadedReportUrl = "";
               if (!companyModuleData.getModuleKey().equalsIgnoreCase("module_minio_io_service")) {
                  if (companyModuleData.getModuleKey().equalsIgnoreCase("module_aws")) {
                     uploadedReportUrl = this.uploadReportToS3(reportOutputStream, reportData.getReportName(), fileExtension, contentType);
                  }
               } else {
                  for(CompanyModuleConfigData configData : companyModuleConfigDataList) {
                     if (configData.getConfigKey().equals("minio_io_end_point")) {
                        endPoint = configData.getConfigValue();
                     } else if (configData.getConfigKey().equals("minio_io_access_key")) {
                        accessKey = configData.getConfigValue();
                     } else if (configData.getConfigKey().equals("minio_io_secret_key")) {
                        secretKey = configData.getConfigValue();
                     } else if (configData.getConfigKey().equals("minio_io_bucket_name")) {
                        bucketName = configData.getConfigValue();
                     } else if (configData.getConfigKey().equals("minio_io_view_end_point")) {
                        viewEndPoint = configData.getConfigValue();
                     }
                  }

                  if (StringUtils.isEmpty(endPoint) || StringUtils.isEmpty(accessKey) || StringUtils.isEmpty(secretKey) || StringUtils.isEmpty(bucketName)) {
                     commonAPIDataResponse.setCheckValidationFailed(true);
                     commonAPIDataResponse.setValidationMessage("BASE_MINIO_IO_SERVICE_CONFIGURATION_NOT_FOUND");
                     return commonAPIDataResponse;
                  }

                  uploadedReportUrl = this.uploadReportToMinIo(reportOutputStream, reportData.getReportName(), fileExtension, contentType, endPoint, accessKey, secretKey, bucketName);
               }

               if (!StringUtils.isEmpty(uploadedReportUrl)) {
                  ReportExportFiles reportExportFiles = ((ReportExportFiles.ReportExportFilesBuilder)((ReportExportFiles.ReportExportFilesBuilder)ReportExportFiles.builder().reportDate(Instant.now().getEpochSecond()).reportName(reportData.getReportName()).downloadUrl(uploadedReportUrl).fileFormat(reportType).filterApplied((Map)null).userId(sendReportRequest.getToken_user_id()).userType((Integer)null).reportDate((Long)null).companyId(sendReportRequest.getCompany_id())).createdBy(sendReportRequest.getToken_user_id())).build();
                  this.reportExportFilesDao.save(reportExportFiles);
                  if (!Objects.isNull(userMasterData) && !StringUtils.isEmpty(userMasterData.getEmail())) {
                     SendNotificationData sendNotificationData = SendNotificationData.builder().event_type(197).user_id(userMasterData.getId()).company_id(sendReportRequest.getCompany_id()).file_attached(uploadedReportUrl).build();
                     this.elasticSearchUtility.sendNotificationDataToKafka(sendNotificationData, sendReportRequest.getRequest_id());
                  }

                  commonAPIDataResponse.setMessage("ELASTIC_SEARCH_REPORT_SENT_SUCCESS");
                  return commonAPIDataResponse;
               } else {
                  Logger.info("Something went wrong while file uploading");
                  commonAPIDataResponse.setCheckValidationFailed(true);
                  commonAPIDataResponse.setValidationMessage("SOMETHING_WENT_WRONG_FILE_UPLOADING");
                  return commonAPIDataResponse;
               }
            } else {
               Logger.info("company module config data not found");
               commonAPIDataResponse.setCheckValidationFailed(true);
               commonAPIDataResponse.setValidationMessage("BASE_MINIO_IO_SERVICE_CONFIGURATION_NOT_FOUND");
               return commonAPIDataResponse;
            }
         }
      } catch (IOException e) {
         Logger.error("Error sending statistics report: " + e.getMessage());
         throw e;
      } catch (Exception e) {
         Logger.error("Unexpected error sending statistics report: " + e.getMessage());
         throw new IOException("Unexpected error", e);
      }
   }

   private String uploadReportToS3(ByteArrayOutputStream reportOutputStream, String reportName, String fileExtension, String contentType) {
      String key = reportName + "." + fileExtension;
      PutObjectRequest reportPutObjectRequest = new PutObjectRequest(this.bucketName, key, new ByteArrayInputStream(reportOutputStream.toByteArray()), new ObjectMetadata());
      reportPutObjectRequest.setMetadata(new ObjectMetadata());
      reportPutObjectRequest.getMetadata().setContentType(contentType);

      try {
         this.amazonS3.putObject(reportPutObjectRequest);
      } catch (Exception exception) {
         exception.printStackTrace();
         return null;
      }

      return this.amazonS3.getUrl(this.bucketName, key).toString();
   }

   private String uploadReportToMinIo(ByteArrayOutputStream reportOutputStream, String reportName, String fileExtension, String contentType, String baseUrl, String accessKey, String secretKey, String bucketName) {
      reportName + "." + fileExtension;
      MinioClient minioClient = (new MinioClient.Builder()).endpoint(baseUrl).credentials(accessKey, secretKey).build();
      long var10000 = Instant.now().getEpochSecond();
      String objectName = var10000 + reportName;
      InputStream inputStream = new ByteArrayInputStream(reportOutputStream.toByteArray());

      try {
         minioClient.putObject((PutObjectArgs)((PutObjectArgs.Builder)((PutObjectArgs.Builder)PutObjectArgs.builder().bucket(bucketName)).object(objectName)).stream(inputStream, -1L, 10485760L).contentType(contentType).build());
      } catch (Exception exception) {
         exception.getMessage();
         return null;
      }

      String downloadURL = null;

      try {
         downloadURL = baseUrl.concat("/").concat(bucketName).concat("/").concat(objectName);
         return downloadURL;
      } catch (Exception exception) {
         exception.getMessage();
         return null;
      }
   }
}
