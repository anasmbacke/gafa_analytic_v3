package com.gafapay.elasticsearch.helper;

import com.datadog.api.client.ApiClient;
import com.datadog.api.client.v2.api.LogsApi;
import com.datadog.api.client.v2.model.ContentEncoding;
import com.datadog.api.client.v2.model.HTTPLogItem;
import com.gafapay.elasticsearch.api.activitylogs.dao.ActivityLogsDao;
import com.gafapay.elasticsearch.api.activitylogs.model.ActivityLogs;
import com.gafapay.elasticsearch.api.auditlogs.dao.AuditLogsDao;
import com.gafapay.elasticsearch.api.auditlogs.model.request.GetAllAuditLogsDataRequest;
import com.gafapay.elasticsearch.api.auditlogs.model.response.AuditLogsData;
import com.gafapay.elasticsearch.api.commonrequest.ChangeStream;
import com.gafapay.elasticsearch.api.commonrequest.DateFilter;
import com.gafapay.elasticsearch.api.commonrequest.DropDownFilter;
import com.gafapay.elasticsearch.api.commonrequest.SearchFilter;
import com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.dao.TxnUserWalletDao;
import com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.dao.UserMasterDataQueryDao;
import com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.dao.WalletMasterQueryDao;
import com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.model.UserWallet;
import com.gafapay.elasticsearch.api.nodalreconciliationreport.model.request.NodalReconciliationReportRequest;
import com.gafapay.elasticsearch.api.nodelbanktransaction.dao.NodalBankTransactionDao;
import com.gafapay.elasticsearch.api.sendreport.model.request.ReportData;
import com.gafapay.elasticsearch.api.sendreport.model.request.SendNotificationData;
import com.gafapay.elasticsearch.api.sendreport.model.request.SendReportDataRequest;
import com.gafapay.elasticsearch.api.thirdpartytxncommissionfees.dao.ThirdPartyTxnCommissionFeesDao;
import com.gafapay.elasticsearch.api.txnmaster.dao.TxnMasterDao;
import com.gafapay.elasticsearch.api.txnmaster.model.TxnMaster;
import com.gafapay.elasticsearch.constant.AppConstants;
import com.gafapay.elasticsearch.database.model.AuditLogs;
import com.gafapay.elasticsearch.database.model.ThirdPartyTxnCommissionFees;
import com.gafapay.elasticsearch.database.model.TxnUserWallet;
import com.gafapay.elasticsearch.database.model.UserAuth;
import com.gafapay.elasticsearch.database.model.nodalbanktransaction.NodalBankTransaction;
import com.gafapay.elasticsearch.database.mongodb.BankMaster;
import com.gafapay.elasticsearch.database.mongodb.BusinessCategory;
import com.gafapay.elasticsearch.database.mongodb.BusinessMaster;
import com.gafapay.elasticsearch.database.mongodb.BusinessTypeMaster;
import com.gafapay.elasticsearch.database.mongodb.CurrencyMasterData;
import com.gafapay.elasticsearch.database.mongodb.ProductMaster;
import com.gafapay.elasticsearch.database.mongodb.UserBanks;
import com.gafapay.elasticsearch.database.mongodb.UserMasterData;
import com.gafapay.elasticsearch.database.mongodb.WalletMaster;
import com.gafapay.elasticsearch.database.repository.mongodb.BankMasterQueryDao;
import com.gafapay.elasticsearch.database.repository.mongodb.BusinessCategoryQueryDao;
import com.gafapay.elasticsearch.database.repository.mongodb.BusinessMasterDataQueryDao;
import com.gafapay.elasticsearch.database.repository.mongodb.BusinessTypeMasterDao;
import com.gafapay.elasticsearch.database.repository.mongodb.CurrencyMasterDataQueryDao;
import com.gafapay.elasticsearch.database.repository.mongodb.ProductMasterQueryDao;
import com.gafapay.elasticsearch.database.repository.mongodb.UserBanksQueryDao;
import com.gafapay.elasticsearch.kafka.producer.KafkaProducerSend;
import com.gafapay.elasticsearch.statemachine.StateMachineAccessor;
import com.gafapay.elasticsearch.statemachine.states.AppState;
import com.gafapay.elasticsearch.utils.HeaderFooterPageEvent1;
import com.gafapay.elasticsearch.utils.Logger;
import com.gafapay.elasticsearch.utils.Utils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Attachments;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.elasticsearch.index.IndexNotFoundException;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.filter.FiltersAggregator;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.LongBounds;
import org.elasticsearch.search.aggregations.bucket.histogram.ParsedDateHistogram;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ParsedTopHits;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.NoSuchIndexException;
import org.springframework.data.elasticsearch.core.AggregationsContainer;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ElasticSearchUtility {
   @Autowired
   private ActivityLogsDao activityLogsDao;
   @Autowired
   private TxnMasterDao txnMasterDao;
   @Autowired
   private CurrencyMasterDataQueryDao currencyMasterDataQueryDao;
   @Autowired
   private UserMasterDataQueryDao userMasterDataQueryDao;
   @Autowired
   private BusinessMasterDataQueryDao businessMasterDataQueryDao;
   @Autowired
   private ProductMasterQueryDao productMasterQueryDao;
   @Autowired
   private KafkaProducerSend kafkaProducerSend;
   @Autowired
   private AuditLogsDao auditLogsDao;
   @Value("${data.dog.log.api.key}")
   private String dataDogApiKey;
   @Value("${data.dog.log.application.key}")
   private String dataDogApplicationKey;
   @Autowired
   private BusinessTypeMasterDao businessTypeMasterDao;
   @Autowired
   private BusinessCategoryQueryDao businessCategoryQueryDao;
   @Autowired
   private BankMasterQueryDao bankMasterQueryDao;
   @Autowired
   private UserBanksQueryDao userBanksQueryDao;
   @Autowired
   private TxnUserWalletDao txnUserWalletDao;
   @Autowired
   private WalletMasterQueryDao walletMasterQueryDao;
   @Value("${data.dog.log.source.name}")
   private String dataDogSourceName;
   @Autowired
   private ElasticsearchRestTemplate elasticsearchRestTemplate;
   @Autowired
   private NodalBankTransactionDao nodalBankTransactionDao;
   @Autowired
   private LogsApi logsApi;
   @Value("${dataDogHost}")
   private String dataDogHost;
   @Autowired
   private StateMachineAccessor stateMachineAccessor;
   @Autowired
   private ThirdPartyTxnCommissionFeesDao thirdPartyTxnCommissionFeesDao;

   public ElasticSearchUtility() {
   }

   public static void sendEmail(List<String> toEmail, String subject, String body, byte[] file, int fileType, String fileName) {
      try {
         String apiKey = "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";
         Email from = new Email("dev@digipay.guru");
         Personalization p1 = new Personalization();

         for(String email : toEmail) {
            p1.addTo(new Email(email));
         }

         Content content = new Content("text/html", body);
         Mail mail1 = new Mail();
         mail1.setFrom(from);
         mail1.setSubject(subject);
         mail1.addPersonalization(p1);
         mail1.addContent(content);
         SendGrid sg = new SendGrid(apiKey);
         mail1.addPersonalization(p1);
         Attachments attachments3 = new Attachments();
         Base64 x = new Base64();
         String imageDataString = x.encodeAsString(file);
         attachments3.setContent(imageDataString);
         attachments3.setType(fileType == 1 ? "application/pdf" : "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
         attachments3.setFilename(fileName + "." + (fileType == 1 ? ".pdf" : "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
         attachments3.setDisposition("attachment");
         attachments3.setContentId("Banner");
         mail1.addAttachments(attachments3);
         Request request = new Request();
         request.setMethod(Method.POST);
         request.setEndpoint("mail/send");
         request.setBody(mail1.build());
         Response response = sg.api(request);
         Logger.info("Sendgrid Response: " + response.getBody());
         if (response.getStatusCode() == 202) {
            Logger.info("Document mail sent successfully via sendgrid method in ElasticSearchUtility class");
         } else {
            Logger.error("Failed to send mail from sendgrid sendMail method in userUtility class");
         }
      } catch (IOException e) {
         Logger.info("Failed to send verification mail: " + e);
      }

   }

   public static File excelToPDF(File file) throws Exception {
      File fileOfPdf = null;
      FileInputStream input_document = new FileInputStream(file);

      try {
         XSSFWorkbook my_xls_workbook = new XSSFWorkbook(input_document);

         try {
            XSSFSheet my_worksheet = my_xls_workbook.getSheetAt(0);
            Iterator<Row> rowIterator = my_worksheet.iterator();
            Document iText_xls_2_pdf = new Document();
            fileOfPdf = new File("assa.pdf");
            PdfWriter.getInstance(iText_xls_2_pdf, new FileOutputStream(fileOfPdf));
            iText_xls_2_pdf.open();
            PdfPTable my_table = new PdfPTable(2);

            while(rowIterator.hasNext()) {
               Row row = (Row)rowIterator.next();
               Iterator<Cell> cellIterator = row.cellIterator();

               while(cellIterator.hasNext()) {
                  Cell cell = (Cell)cellIterator.next();
                  switch (cell.getCellType()) {
                     case STRING:
                        PdfPCell table_cell = new PdfPCell(new Phrase(cell.getStringCellValue()));
                        my_table.addCell(table_cell);
                  }
               }
            }

            iText_xls_2_pdf.add(my_table);
            iText_xls_2_pdf.close();
            input_document.close();
            boolean fileStatus = file.delete();
            if (fileStatus) {
               Logger.info("file is deleted");
            } else {
               Logger.info("file is not deleted");
            }
         } catch (Throwable var14) {
            try {
               my_xls_workbook.close();
            } catch (Throwable var13) {
               var14.addSuppressed(var13);
            }

            throw var14;
         }

         my_xls_workbook.close();
      } catch (Throwable var15) {
         try {
            input_document.close();
         } catch (Throwable var12) {
            var15.addSuppressed(var12);
         }

         throw var15;
      }

      input_document.close();
      return fileOfPdf;
   }

   public static Workbook generateExcelFileFromJson(ReportData reportData) {
      Workbook workbook = new XSSFWorkbook();
      Sheet sheet = workbook.createSheet(reportData.getReportName());
      setColumnStyles(workbook, sheet);
      List<String> columnNames = reportData.getHeaders();
      createColumnHeader(sheet, columnNames, workbook);
      List<List<String>> rowValues = reportData.getData();
      createRows(sheet, rowValues, workbook);
      return workbook;
   }

   public void elasticSearchActivityLogProcessStart(String receivedMessage) {
      if (StringUtils.isEmpty(receivedMessage)) {
         Logger.info(" Received Message From Kafka For Elastic Search Activity Log Is Empty ");
      } else {
         Map<String, Object> stringObjectMap = Utils.convertJsonStringToHashMap(receivedMessage);
         if (!Objects.isNull(stringObjectMap) && !stringObjectMap.isEmpty()) {
            if (!stringObjectMap.containsKey("is_api_log")) {
               this.insertActivityLogs(stringObjectMap);
            } else {
               this.insertAPILogInDataDog(stringObjectMap);
            }

         } else {
            Logger.info(" Received Message From Kafka For Elastic Search Activity Log Is Empty HashMap ");
         }
      }
   }

   public void commonAPILogsProcessStart(String receivedMessage) {
      if (StringUtils.isEmpty(receivedMessage)) {
         Logger.info(" Received Message From Kafka For commonAPILogsProcessStart Is Empty ");
      } else {
         Map<String, Object> stringObjectMap = Utils.convertJsonStringToHashMap(receivedMessage);
         if (!Objects.isNull(stringObjectMap) && !stringObjectMap.isEmpty()) {
            this.insertAPILogInDataDog(stringObjectMap);
         } else {
            Logger.info(" Received Message From Kafka For commonAPILogsProcessStart Is Empty HashMap ");
         }
      }
   }

   private void insertActivityLogs(Map<String, Object> stringObjectMap) {
      ActivityLogs activityLogs = (ActivityLogs)Utils.convertHashMapToClassObject(stringObjectMap, new ActivityLogs());
      if (Objects.isNull(activityLogs)) {
         Logger.error("Error : Activity Logs Data NULL ( Conversion Error From Map To Class Object )");
      } else {
         this.activityLogsDao.saveActivityLogs(activityLogs);
      }
   }

   public void elasticSearchTxnMasterProcessStart(String receivedMessage) {
      Logger.info(" Elastic Search Txn Master Process Receive Message : " + receivedMessage);
      if (StringUtils.isEmpty(receivedMessage)) {
         Logger.info(" Received Message From Kafka For Elastic Search Txn Master Is Empty ");
      } else {
         Map<String, Object> stringObjectMap = Utils.convertJsonStringToHashMap(receivedMessage);
         if (!Objects.isNull(stringObjectMap) && !stringObjectMap.isEmpty()) {
            this.saveTxnMaster(stringObjectMap);
         } else {
            Logger.info(" Received Message From Kafka For Elastic Search Txn Master Is Empty HashMap ");
         }
      }
   }

   private void saveTxnMaster(Map<String, Object> stringObjectMap) {
      Map<String, Object> txnMasterMap = (Map)stringObjectMap.get("txn_master");
      Map<String, Object> txnRequestDetailMap = stringObjectMap.containsKey("transaction_request_details") && !Objects.isNull(stringObjectMap.get("transaction_request_details")) ? (Map)stringObjectMap.get("transaction_request_details") : null;
      Map<String, Object> subWalletInfo = stringObjectMap.containsKey("sub_wallet_info") && !Objects.isNull(stringObjectMap.get("sub_wallet_info")) ? (Map)stringObjectMap.get("sub_wallet_info") : null;
      Map<String, Object> merchantInfo = stringObjectMap.containsKey("merchant_info") && !Objects.isNull(stringObjectMap.get("merchant_info")) ? (Map)stringObjectMap.get("merchant_info") : null;
      Map<String, Object> paymentMaster = stringObjectMap.containsKey("payment_master") && !Objects.isNull(stringObjectMap.get("payment_master")) ? (Map)stringObjectMap.get("payment_master") : null;
      Map<String, Object> topupWalletMaster = stringObjectMap.containsKey("topup_wallet_master") && !Objects.isNull(stringObjectMap.get("topup_wallet_master")) ? (Map)stringObjectMap.get("topup_wallet_master") : null;
      Map<String, Object> payoutMaster = stringObjectMap.containsKey("payout_master") && !Objects.isNull(stringObjectMap.get("payout_master")) ? (Map)stringObjectMap.get("payout_master") : null;
      Map<String, Object> transferMaster = stringObjectMap.containsKey("transfer_master") && !Objects.isNull(stringObjectMap.get("transfer_master")) ? (Map)stringObjectMap.get("transfer_master") : null;
      TxnMaster txnMaster = ((TxnMaster.TxnMasterBuilder)((TxnMaster.TxnMasterBuilder)((TxnMaster.TxnMasterBuilder)TxnMaster.builder().id(txnMasterMap.containsKey("id") && !Objects.isNull(txnMasterMap.get("id")) ? txnMasterMap.get("id").toString() : null).companyId(txnMasterMap.containsKey("companyId") && !Objects.isNull(txnMasterMap.get("companyId")) ? txnMasterMap.get("companyId").toString() : null)).txnNumber(txnMasterMap.containsKey("txnNumber") && !Objects.isNull(txnMasterMap.get("txnNumber")) ? txnMasterMap.get("txnNumber").toString() : null).creditAccountTypeId(txnMasterMap.containsKey("creditAccountTypeId") && !Objects.isNull(txnMasterMap.get("creditAccountTypeId")) ? txnMasterMap.get("creditAccountTypeId").toString() : null).creditAccountType(txnMasterMap.containsKey("creditAccountType") && !Objects.isNull(txnMasterMap.get("creditAccountType")) ? Integer.parseInt(txnMasterMap.get("creditAccountType").toString()) : null).creditTypeId(txnMasterMap.containsKey("creditTypeId") && !Objects.isNull(txnMasterMap.get("creditTypeId")) ? txnMasterMap.get("creditTypeId").toString() : null).creditType(txnMasterMap.containsKey("creditType") && !Objects.isNull(txnMasterMap.get("creditType")) ? Integer.parseInt(txnMasterMap.get("creditType").toString()) : null).creditAmount(txnMasterMap.containsKey("creditAmount") && !Objects.isNull(txnMasterMap.get("creditAmount")) ? Double.parseDouble(txnMasterMap.get("creditAmount").toString()) : null).creditCurrencyId(txnMasterMap.containsKey("creditCurrencyId") && !Objects.isNull(txnMasterMap.get("creditCurrencyId")) ? txnMasterMap.get("creditCurrencyId").toString() : null).debitAccountTypeId(txnMasterMap.containsKey("debitAccountTypeId") && !Objects.isNull(txnMasterMap.get("debitAccountTypeId")) ? txnMasterMap.get("debitAccountTypeId").toString() : null).debitAccountType(txnMasterMap.containsKey("debitAccountType") && !Objects.isNull(txnMasterMap.get("debitAccountType")) ? Integer.parseInt(txnMasterMap.get("debitAccountType").toString()) : null).debitTypeId(txnMasterMap.containsKey("debitTypeId") && !Objects.isNull(txnMasterMap.get("debitTypeId")) ? txnMasterMap.get("debitTypeId").toString() : null).debitType(txnMasterMap.containsKey("debitType") && !Objects.isNull(txnMasterMap.get("debitType")) ? Integer.parseInt(txnMasterMap.get("debitType").toString()) : null).debitAmount(txnMasterMap.containsKey("debitAmount") && !Objects.isNull(txnMasterMap.get("debitAmount")) ? Double.parseDouble(txnMasterMap.get("debitAmount").toString()) : null).debitCurrencyId(txnMasterMap.containsKey("debitCurrencyId") && !Objects.isNull(txnMasterMap.get("debitCurrencyId")) ? txnMasterMap.get("debitCurrencyId").toString() : null).transactionBy(txnMasterMap.containsKey("transactionBy") && !Objects.isNull(txnMasterMap.get("transactionBy")) ? this.updateTransactionByMap((Map)txnMasterMap.get("transactionBy")) : null).display_text(txnMasterMap.containsKey("display_text") && !Objects.isNull(txnMasterMap.get("display_text")) ? (Map)txnMasterMap.get("display_text") : null).txnCode(txnMasterMap.containsKey("txnCode") && !Objects.isNull(txnMasterMap.get("txnCode")) ? txnMasterMap.get("txnCode").toString() : null).txnType(txnMasterMap.containsKey("txnType") && !Objects.isNull(txnMasterMap.get("txnType")) ? Integer.parseInt(txnMasterMap.get("txnType").toString()) : null).txnStatus(txnMasterMap.containsKey("txnStatus") && !Objects.isNull(txnMasterMap.get("txnStatus")) ? Integer.parseInt(txnMasterMap.get("txnStatus").toString()) : null).txnAmount(txnMasterMap.containsKey("txnAmount") && !Objects.isNull(txnMasterMap.get("txnAmount")) ? Double.parseDouble(txnMasterMap.get("txnAmount").toString()) : null).debitAccountBalance(txnMasterMap.containsKey("debitAccountBalance") && !Objects.isNull(txnMasterMap.get("debitAccountBalance")) ? Double.parseDouble(txnMasterMap.get("debitAccountBalance").toString()) : null).creditAccountBalance(txnMasterMap.containsKey("creditAccountBalance") && !Objects.isNull(txnMasterMap.get("creditAccountBalance")) ? Double.parseDouble(txnMasterMap.get("creditAccountBalance").toString()) : null).displayEndUser(txnMasterMap.containsKey("displayEndUser") && !Objects.isNull(txnMasterMap.get("displayEndUser")) ? Boolean.parseBoolean(txnMasterMap.get("displayEndUser").toString()) : null).payment_mode(txnMasterMap.containsKey("payment_mode") && !Objects.isNull(txnMasterMap.get("payment_mode")) ? Integer.parseInt(txnMasterMap.get("payment_mode").toString()) : null).txn_date(txnMasterMap.containsKey("txnDate") && !Objects.isNull(txnMasterMap.get("txnDate")) ? Long.parseLong(txnMasterMap.get("txnDate").toString()) : null).note(txnMasterMap.containsKey("note") && !Objects.isNull(txnMasterMap.get("note")) ? txnMasterMap.get("note").toString() : null).isActive(txnMasterMap.containsKey("isActive") && !Objects.isNull(txnMasterMap.get("isActive")) ? Boolean.parseBoolean(txnMasterMap.get("isActive").toString()) : null)).updatedDate(txnMasterMap.containsKey("updatedDate") && !Objects.isNull(txnMasterMap.get("updatedDate")) ? Long.parseLong(txnMasterMap.get("updatedDate").toString()) : null)).metadata(!Objects.isNull(txnRequestDetailMap) && txnRequestDetailMap.containsKey("metaData") && !Objects.isNull(txnRequestDetailMap.get("metaData")) ? (Map)txnRequestDetailMap.get("metaData") : null).thirdPartyPaymentInfo(!Objects.isNull(txnRequestDetailMap) && txnRequestDetailMap.containsKey("paymentResponse") && !Objects.isNull(txnRequestDetailMap.get("paymentResponse")) ? (Map)txnRequestDetailMap.get("paymentResponse") : null).bankInfo(!Objects.isNull(txnRequestDetailMap) && txnRequestDetailMap.containsKey("bankInfo") && txnRequestDetailMap.get("bankInfo") != null ? (Map)txnRequestDetailMap.get("bankInfo") : null).internalBalance(!Objects.isNull(txnRequestDetailMap) && txnRequestDetailMap.containsKey("internalBalance") && !Objects.isNull(txnRequestDetailMap.get("internalBalance")) ? Double.parseDouble(txnRequestDetailMap.get("internalBalance").toString()) : (double)0.0F).externalBalance(!Objects.isNull(txnRequestDetailMap) && txnRequestDetailMap.containsKey("externalBalance") && !Objects.isNull(txnRequestDetailMap.get("externalBalance")) ? Double.parseDouble(txnRequestDetailMap.get("externalBalance").toString()) : (double)0.0F).deviceInfo(!Objects.isNull(txnRequestDetailMap) && txnRequestDetailMap.containsKey("txnDeviceInfo") && !Objects.isNull(txnRequestDetailMap.get("txnDeviceInfo")) ? (Map)txnRequestDetailMap.get("txnDeviceInfo") : null).readerInfo((Map)null).build();
      if (!Objects.isNull(txnMaster.getMetadata())) {
         txnMaster.setReaderInfo(!Objects.isNull(txnMaster.getMetadata().get("reader_info")) && txnMaster.getMetadata().containsKey("reader_info") ? (Map)txnMaster.getMetadata().get("reader_info") : null);
      }

      txnMaster.setCreditCurrency(this.getCurrencyDetails(txnMaster.getCompanyId(), txnMaster.getCreditCurrencyId()));
      txnMaster.setCreditAccount(this.getUserDetails(txnMaster.getCompanyId(), txnMaster.getCreditAccountTypeId(), txnMaster.getCreditAccountType(), txnMaster.getCreditType(), txnMaster.getCreditTypeId()));
      txnMaster.setDebitCurrency(txnMaster.getCreditCurrencyId().equalsIgnoreCase(txnMaster.getDebitCurrencyId()) ? txnMaster.getCreditCurrency() : this.getCurrencyDetails(txnMaster.getCompanyId(), txnMaster.getDebitCurrencyId()));
      txnMaster.setDebitAccount(this.getUserDetails(txnMaster.getCompanyId(), txnMaster.getDebitAccountTypeId(), txnMaster.getDebitAccountType(), txnMaster.getDebitType(), txnMaster.getDebitTypeId()));
      txnMaster.setTxnCharges(this.getChargesDetails(stringObjectMap.containsKey("txn_charges") && !Objects.isNull(stringObjectMap.get("txn_charges")) ? (List)stringObjectMap.get("txn_charges") : null));
      txnMaster.setProductInfo(this.getProductDetails(txnMaster.getCompanyId(), txnMaster.getTxnCode()));
      txnMaster.setSubWalletInfo(subWalletInfo);
      txnMaster.setMerchantInfo(merchantInfo);
      txnMaster.setPaymentMaster(paymentMaster);
      txnMaster.setTopupWalletMaster(topupWalletMaster);
      txnMaster.setPayoutMaster(payoutMaster);
      txnMaster.setTransferMaster(transferMaster);
      Logger.info("Log : " + Utils.convertHashMapToJsonString(stringObjectMap));
      this.txnMasterDao.saveTxnMaster(txnMaster);
   }

   private Map<String, Object> updateTransactionByMap(Map<String, Object> transactionBy) {
      if (transactionBy.containsKey("account_id") && !Objects.isNull(transactionBy.get("account_id"))) {
         UserMasterData userMasterData = this.userMasterDataQueryDao.findById(transactionBy.get("account_id").toString());
         if (!Objects.isNull(userMasterData)) {
            transactionBy.put("first_name", userMasterData.getFirstName());
            transactionBy.put("last_name", userMasterData.getLastName());
            transactionBy.put("display_name", userMasterData.getDisplayName());
         }
      }

      return transactionBy;
   }

   private Map<String, Object> getCurrencyDetails(String companyId, String currencyId) {
      CurrencyMasterData currencyMasterData = this.currencyMasterDataQueryDao.findByCompanyIdAndCurrencyId(companyId, currencyId);
      if (Objects.isNull(currencyMasterData)) {
         return null;
      } else {
         Map<String, Object> stringObjectMap = new HashMap();
         stringObjectMap.put("id", currencyMasterData.getId());
         stringObjectMap.put("currency_code", currencyMasterData.getCurrencyCode());
         stringObjectMap.put("currency_name", currencyMasterData.getCurrencyName());
         stringObjectMap.put("currency_symbol", currencyMasterData.getCurrencySymbol());
         stringObjectMap.put("country_id", currencyMasterData.getCountryId());
         stringObjectMap.put("is_primary_currency", currencyMasterData.getIsPrimaryCurrency());
         return stringObjectMap;
      }
   }

   private Map<String, Object> getUserDetails(String companyId, String id, Integer userType, Integer creditORDebitType, String creditORDebitTypeId) {
      UserMasterData userMasterData = this.userMasterDataQueryDao.findById(id);
      if (Objects.isNull(userMasterData)) {
         return null;
      } else {
         Map<String, Object> stringObjectMap = new HashMap();
         stringObjectMap.put("id", userMasterData.getId());
         stringObjectMap.put("first_name", userMasterData.getFirstName());
         stringObjectMap.put("last_name", userMasterData.getLastName());
         stringObjectMap.put("email", userMasterData.getEmail());
         stringObjectMap.put("dial_code", userMasterData.getDialCode());
         stringObjectMap.put("phone_number", userMasterData.getPhoneNumber());
         stringObjectMap.put("user_type", userMasterData.getUserType());
         stringObjectMap.put("image", userMasterData.getImage());
         stringObjectMap.put("address", userMasterData.getAddress());
         stringObjectMap.put("postal_code", userMasterData.getPostalCode());
         stringObjectMap.put("display_name", userMasterData.getDisplayName());
         stringObjectMap.put("account_number", userMasterData.getAccountNumber());
         if (creditORDebitType.equals(AppConstants.CREDIT_TYPE_BANK)) {
            stringObjectMap.put("bank_info", this.setBankInfo(creditORDebitTypeId));
         }

         stringObjectMap.put("business_info", this.getBusinessDetails(companyId, id));
         return stringObjectMap;
      }
   }

   private Map<String, Object> getBusinessDetails(String companyId, String id) {
      BusinessMaster businessMaster = this.businessMasterDataQueryDao.findByCompanyIdAndUserId(companyId, id);
      if (Objects.isNull(businessMaster)) {
         return null;
      } else {
         Map<String, Object> stringObjectMap = new HashMap();
         stringObjectMap.put("id", businessMaster.getId());
         stringObjectMap.put("business_name", businessMaster.getBusinessName());
         stringObjectMap.put("business_type", businessMaster.getBusinessType());
         stringObjectMap.put("business_category_id", businessMaster.getBusinessCategoryId());
         stringObjectMap.put("industry_type", businessMaster.getIndustryType());
         stringObjectMap.put("monthly_turnover", businessMaster.getMonthlyTurnover());
         stringObjectMap.put("trade_license_number", businessMaster.getTradeLicenseNumber());
         stringObjectMap.put("user_id", businessMaster.getUserId());
         stringObjectMap.put("business_type_info", this.getBusinessTypeInfo(companyId, businessMaster.getBusinessType()));
         stringObjectMap.put("business_category_info", this.getBusinessCategoryInfo(companyId, businessMaster.getBusinessCategoryId()));
         return stringObjectMap;
      }
   }

   private Map<String, Object> getBusinessTypeInfo(String companyId, String businessTypeKey) {
      BusinessTypeMaster businessTypeMaster = this.businessTypeMasterDao.findByCompanyIdAndKey(companyId, businessTypeKey);
      if (Objects.isNull(businessTypeMaster)) {
         return null;
      } else {
         Map<String, Object> objectMap = new HashMap();
         objectMap.put("id", businessTypeMaster.getId());
         objectMap.put("name", businessTypeMaster.getName());
         objectMap.put("key", businessTypeMaster.getKey());
         return objectMap;
      }
   }

   private Map<String, Object> getBusinessCategoryInfo(String companyId, String businessCategoryId) {
      BusinessCategory businessCategory = this.businessCategoryQueryDao.findById(businessCategoryId);
      if (Objects.isNull(businessCategory)) {
         return null;
      } else {
         Map<String, Object> objectMap = new HashMap();
         objectMap.put("id", businessCategory.getId());
         objectMap.put("name", businessCategory.getName());
         objectMap.put("image", businessCategory.getImage());
         return objectMap;
      }
   }

   private Map<String, Object> getProductDetails(String companyId, String productCode) {
      ProductMaster productMaster = this.productMasterQueryDao.findByCompanyIdAndProductId(companyId, productCode);
      if (Objects.isNull(productMaster)) {
         return null;
      } else {
         Map<String, Object> stringObjectMap = new HashMap();
         stringObjectMap.put("id", productMaster.getId());
         stringObjectMap.put("product_name", productMaster.getProductName());
         stringObjectMap.put("product_description", productMaster.getProductDesc());
         stringObjectMap.put("user_type", productMaster.getUserType());
         return stringObjectMap;
      }
   }

   private List<Map<String, Object>> getChargesDetails(List<LinkedHashMap<String, Object>> charges) {
      if (!Objects.isNull(charges) && !charges.isEmpty()) {
         List<Map<String, Object>> stringObjectMap = new ArrayList();

         for(Map<String, Object> objectMap : charges) {
            Map<String, Object> chargeData = new HashMap();
            chargeData.put("id", objectMap.containsKey("id") && !Objects.isNull(objectMap.get("id")) ? objectMap.get("id") : null);
            chargeData.put("txn_id", objectMap.containsKey("txnId") && !Objects.isNull(objectMap.get("txnId")) ? objectMap.get("txnId") : null);
            chargeData.put("charge_id", objectMap.containsKey("charge_id") && !Objects.isNull(objectMap.get("charge_id")) ? objectMap.get("charge_id") : null);
            chargeData.put("charge_name", objectMap.containsKey("chargeName") && !Objects.isNull(objectMap.get("chargeName")) ? objectMap.get("chargeName") : null);
            chargeData.put("charge_type", objectMap.containsKey("chargeType") && !Objects.isNull(objectMap.get("chargeType")) ? objectMap.get("chargeType") : null);
            chargeData.put("charge_value", objectMap.containsKey("chargeValue") && !Objects.isNull(objectMap.get("chargeValue")) ? objectMap.get("chargeValue") : null);
            chargeData.put("charge_value_type", objectMap.containsKey("chargeValueType") && !Objects.isNull(objectMap.get("chargeValueType")) ? objectMap.get("chargeValueType") : null);
            chargeData.put("final_charge", objectMap.containsKey("finalCharge") && !Objects.isNull(objectMap.get("finalCharge")) ? objectMap.get("finalCharge") : null);
            chargeData.put("user_type", objectMap.containsKey("userType") && !Objects.isNull(objectMap.get("userType")) ? objectMap.get("userType") : null);
            chargeData.put("user_id", objectMap.containsKey("userId") && !Objects.isNull(objectMap.get("userId")) ? objectMap.get("userId") : null);
            stringObjectMap.add(chargeData);
         }

         return stringObjectMap;
      } else {
         return null;
      }
   }

   public DateHistogramInterval getFilterType(DateFilter dateFilter) {
      if (Objects.isNull(dateFilter)) {
         return DateHistogramInterval.DAY;
      } else if (Objects.isNull(dateFilter.getFilter_type())) {
         return DateHistogramInterval.DAY;
      } else {
         int filterType = dateFilter.getFilter_type();
         if (filterType == 1) {
            return DateHistogramInterval.HOUR;
         } else if (filterType == 2) {
            return DateHistogramInterval.DAY;
         } else if (filterType == 3) {
            return DateHistogramInterval.MONTH;
         } else {
            return filterType == 4 ? DateHistogramInterval.YEAR : DateHistogramInterval.DAY;
         }
      }
   }

   public void elasticSearchUpdateTxnStatusProcessStart(String receivedMessage, String requestId) {
      Logger.info(" Elastic Search Txn_Status update Process Receive Message : " + receivedMessage);
      if (StringUtils.isEmpty(receivedMessage)) {
         Logger.info(" Received Message From Kafka For Elastic Search TxnStatus message Is Empty ");
      } else {
         Map<String, Object> stringObjectMap = Utils.convertJsonStringToHashMap(receivedMessage);
         if (!Objects.isNull(stringObjectMap) && !stringObjectMap.isEmpty()) {
            int count = stringObjectMap.containsKey("count_for_send_topic") && !Objects.isNull(stringObjectMap.get("count_for_send_topic")) ? Integer.parseInt(stringObjectMap.get("count_for_send_topic").toString()) : 0;
            if (count < 100) {
               String txn_id = stringObjectMap.containsKey("txn_id") && !Objects.isNull(stringObjectMap.get("txn_id")) ? stringObjectMap.get("txn_id").toString() : null;
               Integer txn_status = stringObjectMap.containsKey("txn_status") && !Objects.isNull(stringObjectMap.get("txn_status")) ? Integer.parseInt(stringObjectMap.get("txn_status").toString()) : null;
               if (stringObjectMap.containsKey("company_id") && !Objects.isNull(stringObjectMap.get("company_id"))) {
                  stringObjectMap.get("company_id").toString();
               } else {
                  Object var10000 = null;
               }

               String updated_by = stringObjectMap.containsKey("updated_by") && !Objects.isNull(stringObjectMap.get("updated_by")) ? stringObjectMap.get("updated_by").toString() : null;
               Map<String, Object> paymentMaster = stringObjectMap.containsKey("payment_master") && !Objects.isNull(stringObjectMap.get("payment_master")) ? (Map)stringObjectMap.get("payment_master") : null;
               Map<String, Object> topupWalletMaster = stringObjectMap.containsKey("topup_wallet_master") && !Objects.isNull(stringObjectMap.get("topup_wallet_master")) ? (Map)stringObjectMap.get("topup_wallet_master") : null;
               Map<String, Object> payoutMaster = stringObjectMap.containsKey("payout_master") && !Objects.isNull(stringObjectMap.get("payout_master")) ? (Map)stringObjectMap.get("payout_master") : null;
               Map<String, Object> transferMaster = stringObjectMap.containsKey("transfer_master") && !Objects.isNull(stringObjectMap.get("transfer_master")) ? (Map)stringObjectMap.get("transfer_master") : null;
               List<Map<String, Object>> txnCommission = stringObjectMap.containsKey("txn_commission") && !Objects.isNull(stringObjectMap.get("txn_commission")) ? (List)stringObjectMap.get("txn_commission") : null;
               TxnMaster txnMaster = null;

               try {
                  txnMaster = this.txnMasterDao.getTxnMasterById(txn_id);
                  Utils.convertObjectToJsonString(txnMaster, "before Txn Master Data : ");
                  Utils.convertObjectToJsonString(stringObjectMap, "Update Txn master map from kafka received message:: ");
               } catch (Exception ex) {
                  if (stringObjectMap.containsKey("count_for_send_topic") && !Objects.isNull(stringObjectMap.get("count_for_send_topic"))) {
                     int var18 = Integer.parseInt(stringObjectMap.get("count_for_send_topic").toString()) + 1;
                  } else {
                     boolean var17 = true;
                  }

                  Logger.error("Exception : " + ExceptionUtils.getStackTrace(ex));
                  this.sendTxnMasterUpdateToKafka(txnMaster, stringObjectMap, requestId);
               }

               if (Objects.isNull(txnMaster)) {
                  Logger.info("TxnMaster is not found for update txn master in elasticSearchUpdateTxnStatusProcessStart method");
               } else {
                  txnMaster.setTxnCommissions(!Objects.isNull(txnCommission) && !txnCommission.isEmpty() ? txnCommission : txnMaster.getTxnCommissions());
                  txnMaster.setTxnStatus(!Objects.isNull(txn_status) ? txn_status : txnMaster.getTxnStatus());
                  txnMaster.setUpdatedDate(Instant.now().getEpochSecond());
                  txnMaster.setUpdatedBy(updated_by);
                  txnMaster.setCreditAccount(this.setBankInfoFromTxnMaster(stringObjectMap, "credit_bank_info", txnMaster.getCreditAccount()));
                  txnMaster.setDebitAccount(this.setBankInfoFromTxnMaster(stringObjectMap, "debit_bank_info", txnMaster.getDebitAccount()));
                  txnMaster.setDebitAccountBalance(stringObjectMap.containsKey("debit_account_balance") && !Objects.isNull(stringObjectMap.get("debit_account_balance")) ? Double.valueOf(stringObjectMap.get("debit_account_balance").toString()) : txnMaster.getDebitAccountBalance());
                  txnMaster.setCreditAccountBalance(stringObjectMap.containsKey("credit_account_balance") && !Objects.isNull(stringObjectMap.get("credit_account_balance")) ? Double.valueOf(stringObjectMap.get("credit_account_balance").toString()) : txnMaster.getCreditAccountBalance());
                  txnMaster.setThirdPartyPaymentInfo(this.setThirdPartyPaymentInfo(stringObjectMap, "payment_info", txnMaster.getThirdPartyPaymentInfo()));
                  txnMaster.setPaymentMaster(paymentMaster);
                  txnMaster.setTopupWalletMaster(!Objects.isNull(topupWalletMaster) ? topupWalletMaster : txnMaster.getTopupWalletMaster());
                  txnMaster.setPayoutMaster(!Objects.isNull(payoutMaster) ? payoutMaster : txnMaster.getPayoutMaster());
                  txnMaster.setTransferMaster(!Objects.isNull(transferMaster) ? transferMaster : txnMaster.getTransferMaster());
                  if (stringObjectMap.containsKey("third_party_txn_commission_fees") && Objects.nonNull(stringObjectMap.get("third_party_txn_commission_fees"))) {
                     Map var20 = (Map)stringObjectMap.get("third_party_txn_commission_fees");
                  } else {
                     Object var19 = null;
                  }

                  Utils.convertObjectToJsonString(txnMaster, "after Txn Master Data : ");
                  this.txnMasterDao.saveTxnMaster(txnMaster);
                  Logger.info("Txn Master is updated successfully in ES");
               }
            }
         } else {
            Logger.info(" Received Message From Kafka For Elastic Search Txn_status update Is Empty HashMap ");
         }
      }
   }

   private Map<String, Object> setBankInfoFromTxnMaster(Map<String, Object> stringObjectMap, String keyName, Map<String, Object> accountInfo) {
      if (stringObjectMap.containsKey(keyName) && !Objects.isNull(stringObjectMap.get(keyName))) {
         accountInfo.put("bank_info", stringObjectMap.get(keyName));
      }

      return accountInfo;
   }

   private Map<String, Object> setThirdPartyPaymentInfo(Map<String, Object> stringObjectMap, String keyName, Map<String, Object> paymentInfo) {
      Logger.info("paymentInfo before = " + Utils.convertHashMapToJsonString(paymentInfo));
      Logger.info("stringObjectMap = " + Utils.convertHashMapToJsonString(stringObjectMap));
      if (Objects.isNull(paymentInfo) || paymentInfo.isEmpty()) {
         paymentInfo = new HashMap(2);
      }

      if (stringObjectMap.containsKey(keyName) && !Objects.isNull(stringObjectMap.get(keyName))) {
         if (paymentInfo.containsKey("payment_info") && null != paymentInfo.get("payment_info")) {
            if (paymentInfo.get("payment_info") instanceof Map) {
               Map<String, Object> paymentInfoMap = (Map)paymentInfo.get("payment_info");
               Map<String, Object> newMap = (Map)stringObjectMap.get(keyName);

               for(Map.Entry<String, Object> entry : newMap.entrySet()) {
                  paymentInfoMap.put((String)entry.getKey(), entry.getValue());
               }

               paymentInfo.put("payment_info", paymentInfoMap);
            } else if (paymentInfo.get("payment_info") instanceof List) {
               List<Map<String, Object>> paymentInfoMapList = (List)paymentInfo.get("payment_info");

               for(Map<String, Object> newMap : (List)stringObjectMap.get(keyName)) {
                  paymentInfoMapList.add(newMap);
               }

               paymentInfo.put("payment_info", paymentInfoMapList);
            }
         } else {
            paymentInfo.put("payment_info", stringObjectMap.get(keyName));
         }
      }

      Logger.info("paymentInfo after = " + Utils.convertHashMapToJsonString(paymentInfo));
      return paymentInfo;
   }

   private void sendTxnMasterUpdateToKafka(TxnMaster txnMaster, Map<String, Object> stringObjectMap, String requestId) {
      if (Objects.isNull(txnMaster)) {
         Logger.info("TxnMaster is not found for update txn master in elasticSearchUpdateTxnStatusProcessStart method");
         this.kafkaProducerSend.sendToUpdateTxnMasterInElasticSearchMessage(Utils.convertHashMapToJsonString(stringObjectMap), requestId);
      }

   }

   public FiltersAggregator.KeyedFilter[] convertTxnCodeToKeyedFilter(List<String> productCode) {
      List<FiltersAggregator.KeyedFilter> keyedFilter = new ArrayList();

      for(String txnCode : productCode) {
         keyedFilter.add(new FiltersAggregator.KeyedFilter(txnCode, QueryBuilders.matchQuery("txn_code.keyword", txnCode)));
      }

      return (FiltersAggregator.KeyedFilter[])keyedFilter.toArray((x$0) -> new FiltersAggregator.KeyedFilter[x$0]);
   }

   public BoolQueryBuilder getDefaultFieldBoolQueryBuilder(String companyId, DateFilter dateFilter, DropDownFilter dropDownFilter, SearchFilter searchFilter) {
      BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
      if (Objects.isNull(dateFilter)) {
         dateFilter = new DateFilter();
      }

      boolQueryBuilder.must(QueryBuilders.matchQuery("company_id", companyId));
      if (!Objects.isNull(dropDownFilter) && !StringUtils.isEmpty(dropDownFilter.getUser_id())) {
         boolQueryBuilder.must(QueryBuilders.multiMatchQuery(dropDownFilter.getUser_id(), new String[]{"credit_account_type_id", "debit_account_type_id"}));
      }

      if (!Objects.isNull(dropDownFilter) && !StringUtils.isEmpty(dropDownFilter.getCurrency_id())) {
         boolQueryBuilder.must(QueryBuilders.multiMatchQuery(dropDownFilter.getCurrency_id(), new String[]{"credit_currency_id", "debit_currency_id"}));
      }

      if (!Objects.isNull(dropDownFilter) && !StringUtils.isEmpty(dropDownFilter.getWallet_id())) {
         boolQueryBuilder.must(QueryBuilders.multiMatchQuery(dropDownFilter.getWallet_id(), new String[]{"credit_type_id", "debit_type_id"}));
      }

      if (!Objects.isNull(dropDownFilter) && !StringUtils.isEmpty(dropDownFilter.getAdmin_user_id())) {
         boolQueryBuilder.must(QueryBuilders.multiMatchQuery(dropDownFilter.getAdmin_user_id(), new String[]{"credit_account_type_id", "debit_account_type_id"}));
      }

      if (!Objects.isNull(dropDownFilter) && !Objects.isNull(dropDownFilter.getUser_type())) {
         boolQueryBuilder.must(QueryBuilders.multiMatchQuery(dropDownFilter.getUser_type(), new String[]{"credit_account_type", "debit_account_type"}));
      }

      if (!Objects.isNull(dropDownFilter) && !StringUtils.isEmpty(dropDownFilter.getNodal_bank_id())) {
         boolQueryBuilder.must(QueryBuilders.matchQuery("nodal_bank_id", dropDownFilter.getNodal_bank_id()));
      }

      if (!Objects.isNull(dropDownFilter) && !Objects.isNull(dropDownFilter.getCredit_account_type())) {
         boolQueryBuilder.must(QueryBuilders.matchQuery("credit_account_type", dropDownFilter.getCredit_account_type()));
      }

      if (!Objects.isNull(dropDownFilter) && !Objects.isNull(dropDownFilter.getDebit_account_type())) {
         boolQueryBuilder.must(QueryBuilders.matchQuery("debit_account_type", dropDownFilter.getDebit_account_type()));
      }

      if (!Objects.isNull(dropDownFilter) && !Objects.isNull(dropDownFilter.getAccount_type())) {
         boolQueryBuilder.must(QueryBuilders.matchQuery("transaction_by.account_type", dropDownFilter.getAccount_type()));
      }

      if (!Objects.isNull(dropDownFilter) && !StringUtils.isEmpty(dropDownFilter.getTree_level_id()) && dropDownFilter.getAgent_commission_group_by() == 3) {
         boolQueryBuilder.must(QueryBuilders.nestedQuery("txn_commissions", QueryBuilders.matchQuery("txn_commissions.tree_level_id", dropDownFilter.getTree_level_id()), ScoreMode.None));
      }

      if (!Objects.isNull(dropDownFilter) && !StringUtils.isEmpty(dropDownFilter.getCredit_account_type_id())) {
         boolQueryBuilder.must(QueryBuilders.matchQuery("credit_account_type_id", dropDownFilter.getCredit_account_type_id()));
      }

      if (!Objects.isNull(dropDownFilter) && !StringUtils.isEmpty(dropDownFilter.getDebit_account_type_id())) {
         boolQueryBuilder.must(QueryBuilders.matchQuery("debit_account_type_id", dropDownFilter.getDebit_account_type_id()));
      }

      if (!Objects.isNull(dropDownFilter) && !Objects.isNull(dropDownFilter.getTxn_code())) {
         for(String code : dropDownFilter.getTxn_code()) {
            boolQueryBuilder.should(QueryBuilders.matchQuery("txn_code", code));
         }
      }

      if (!Objects.isNull(dropDownFilter) && !Objects.isNull(dropDownFilter.getType())) {
         boolQueryBuilder.must(QueryBuilders.matchQuery("type", dropDownFilter.getType()));
      }

      if (!Objects.isNull(dropDownFilter) && !Objects.isNull(dropDownFilter.getIs_active())) {
         boolQueryBuilder.must(QueryBuilders.matchQuery("type_key_info.is_active", dropDownFilter.getIs_active()));
      }

      if (!Objects.isNull(dropDownFilter) && !Objects.isNull(dropDownFilter.getType_key_code())) {
         boolQueryBuilder.must(QueryBuilders.matchQuery("type_key_code", dropDownFilter.getType_key_code()));
      }

      if (!Objects.isNull(dropDownFilter) && !Objects.isNull(dropDownFilter.getCorporate_id())) {
         boolQueryBuilder.must(QueryBuilders.matchQuery("debit_account_type_id.keyword", dropDownFilter.getCorporate_id()));
      }

      if (!Objects.isNull(dateFilter) && !Objects.isNull(dateFilter.getDate_field_name()) && !Objects.isNull(dateFilter.getStart_date()) && !Objects.isNull(dateFilter.getEnd_date())) {
         boolQueryBuilder.must(QueryBuilders.rangeQuery(dateFilter.getDate_field_name()).gte(dateFilter.getStart_date()).lte(dateFilter.getEnd_date()));
      }

      if (!Objects.isNull(searchFilter) && !Objects.isNull(searchFilter.getFields()) && !searchFilter.getFields().isEmpty()) {
         boolQueryBuilder.must(QueryBuilders.multiMatchQuery(searchFilter.getSearch_keyword(), (String[])searchFilter.getFields().toArray((x$0) -> new String[x$0])));
      }

      return boolQueryBuilder;
   }

   public DateHistogramAggregationBuilder buildHistogramAggregationBuilder(String dateHistogramFilterName, DateHistogramInterval dateHistogramInterval, DateFilter dateFilter, AggregationBuilder... aggregationBuilder) {
      if (Objects.isNull(dateFilter)) {
         dateFilter = new DateFilter();
      }

      Map<String, Object> aggregationScript = new HashMap();
      aggregationScript.put("lang", "expression");
      aggregationScript.put("source", "doc['" + dateFilter.getDate_field_name() + "'] * 1000");
      DateHistogramAggregationBuilder dateHistogramAggregationBuilder = ((DateHistogramAggregationBuilder)((DateHistogramAggregationBuilder)AggregationBuilders.dateHistogram(dateHistogramFilterName).script(Script.parse(aggregationScript))).field(dateFilter.getDate_field_name())).offset("+30m").calendarInterval(dateHistogramInterval).extendedBounds(new LongBounds(dateFilter.getStart_date() * 1000L, dateFilter.getEnd_date() * 1000L));

      for(AggregationBuilder builder : aggregationBuilder) {
         dateHistogramAggregationBuilder.subAggregation(builder);
      }

      return dateHistogramAggregationBuilder;
   }

   public void insertAPILogInDataDog(Map<String, Object> stringObjectMap) {
      ObjectMapper mapper = new ObjectMapper();
      String logStatusType = stringObjectMap.containsKey("log_status_type") && null != stringObjectMap.get("log_status_type") ? stringObjectMap.get("log_status_type").toString() : "Info";
      String sourceName = stringObjectMap.containsKey("source_name") && null != stringObjectMap.get("source_name") ? stringObjectMap.get("source_name").toString() : "digipay";

      try {
         Map<String, Object> payload = new HashMap();
         if (stringObjectMap.containsKey("payload") && !Objects.isNull(stringObjectMap.get("payload")) && !ObjectUtils.isEmpty(stringObjectMap.get("payload"))) {
            if (!stringObjectMap.get("api_method").toString().equalsIgnoreCase("POST") && !stringObjectMap.get("api_method").toString().equalsIgnoreCase("PUT")) {
               if (!stringObjectMap.get("payload").toString().contains("=")) {
                  payload.put("id", stringObjectMap.get("payload").toString());
               } else {
                  String[] strings = stringObjectMap.get("payload").toString().split("&");

                  for(int i = 0; i < strings.length; ++i) {
                     String[] fields = strings[i].split("=");
                     payload.put(fields[0], fields[1]);
                  }
               }

               stringObjectMap.put("payload", mapper.valueToTree(payload).toPrettyString());
            } else {
               stringObjectMap.put("payload", mapper.valueToTree(mapper.readTree(stringObjectMap.get("payload").toString())));
            }
         }

         if (logStatusType.equalsIgnoreCase("Error")) {
            stringObjectMap.put("response", stringObjectMap.get("response"));
         } else {
            stringObjectMap.put("response", mapper.valueToTree(mapper.readTree(stringObjectMap.get("response").toString()).toPrettyString()));
         }

         stringObjectMap.put("headers", mapper.valueToTree(stringObjectMap.get("headers")).toPrettyString());
         stringObjectMap.put("duration", this.getTimePrefixFromMilliSecond(Long.valueOf(stringObjectMap.get("duration").toString())));
      } catch (Exception e) {
         throw new RuntimeException(e);
      }

      ApiClient defaultClient = ApiClient.getDefaultApiClient();
      defaultClient.setApiKey(this.dataDogApiKey);
      LogsApi apiInstance = new LogsApi(defaultClient);
      List<HTTPLogItem> body = Collections.singletonList((new HTTPLogItem()).ddsource(sourceName).ddtags("env:staging,version:5.1").hostname(this.dataDogHost).putAdditionalProperty("status", logStatusType).message(mapper.valueToTree(stringObjectMap).toPrettyString()).service(stringObjectMap.get("service_name").toString()));

      try {
         apiInstance.submitLog(body, (new LogsApi.SubmitLogOptionalParameters()).contentEncoding(ContentEncoding.DEFLATE));
      } catch (Exception e) {
         Logger.error("Exception : " + ExceptionUtils.getStackTrace(e));
      }

   }

   public void insertKafkaLogInDataDog(Map<String, Object> message, String logStatusType, String serviceName) {
      ObjectMapper mapper = new ObjectMapper();
      ApiClient defaultClient = ApiClient.getDefaultApiClient();
      defaultClient.setApiKey(this.dataDogApiKey);
      LogsApi apiInstance = new LogsApi(defaultClient);
      List<HTTPLogItem> body = Collections.singletonList((new HTTPLogItem()).ddsource("nginx").ddtags("env:staging,version:5.1").hostname(this.dataDogHost).putAdditionalProperty("status", logStatusType).message(mapper.valueToTree(message).toPrettyString()).service(serviceName));

      try {
         apiInstance.submitLog(body, (new LogsApi.SubmitLogOptionalParameters()).contentEncoding(ContentEncoding.DEFLATE));
      } catch (Exception e) {
         Logger.error("Exception : " + ExceptionUtils.getStackTrace(e));
      }

   }

   private String getTimePrefixFromMilliSecond(Long duration) {
      if (duration < 1000L) {
         return duration + " ms";
      } else if (duration < 60000L) {
         return duration + " s";
      } else {
         return duration < 3600000L ? duration + " min" : duration + " hr";
      }
   }

   public void produceCommonAPILogs(Map<String, Object> stringObjectHashMap, String requestId) {
      ObjectMapper objectMapper = new ObjectMapper();
      stringObjectHashMap.put("service_name", "Elastic Search");
      stringObjectHashMap.put("source_name", this.dataDogSourceName);
      JsonNode jsonNode = objectMapper.valueToTree(stringObjectHashMap);
      this.kafkaProducerSend.sendCommonAPILogMessage(jsonNode.toString(), requestId);
   }

   public void sendRemoveExpiredTokenDataToProducer(String encryptedToken, String userId, String requestId, UserAuth userAuth) {
      Map<String, Object> kafkaMap = new HashMap();
      kafkaMap.put("Token", encryptedToken);
      kafkaMap.put("token_user_id", userId);
      kafkaMap.put("user_secret_data", userAuth);
      this.kafkaProducerSend.removeExpiredTokenFromDataBase(Utils.convertHashMapToJsonString(kafkaMap), requestId);
   }

   public void changeStreamLogFromKafka(String receivedMessage) {
      if (StringUtils.isEmpty(receivedMessage)) {
         Logger.info(" Received Message From Kafka Is Empty!");
      } else {
         Logger.info(receivedMessage);
         Map<String, Object> stringObjectMap = Utils.convertJsonStringToHashMap(receivedMessage);
         ChangeStream changeStream = (ChangeStream)Utils.convertHashMapToClassObject(stringObjectMap, new ChangeStream());
         Utils.convertObjectToJsonString(changeStream, "Change stream data transform.");
         if (Objects.isNull(changeStream)) {
            Logger.info(" Received Message From kafka is Empty!");
         } else {
            this.startChangeStreamProcess(changeStream);
         }
      }
   }

   private void startChangeStreamProcess(ChangeStream changeStreamData) {
      if (StringUtils.isEmpty(changeStreamData.getOperationType())) {
         Logger.error("operationtype is null");
      } else {
         switch (changeStreamData.getOperationType()) {
            case "insert":
               this.insertChangeStreamDataProcess(changeStreamData);
               break;
            case "update":
            case "replace":
               this.updateChangeStreamDataProcess(changeStreamData);
               break;
            case "delete":
               this.deleteChangeStreamDataProcess(changeStreamData);
         }

      }
   }

   private void deleteChangeStreamDataProcess(ChangeStream deleteStreamData) {
      if (Objects.isNull(deleteStreamData) && deleteStreamData == null) {
         Logger.error("Stream Data is null");
      } else {
         Map<String, Object> dbAndCollData = deleteStreamData.getNs();
         Map<String, Object> actionTimeData = (Map)deleteStreamData.getClusterTime().get("$timestamp");
         Map<String, Object> oldData = deleteStreamData.getFullDocumentBeforeChange();
         Integer i = (Integer)actionTimeData.get("t");
         Long actionTime = i.longValue();
         String dbName = dbAndCollData.containsKey("db") && !Objects.isNull(dbAndCollData.get("db")) ? dbAndCollData.get("db").toString() : null;
         AuditLogs auditLogs = ((AuditLogs.AuditLogsBuilder)((AuditLogs.AuditLogsBuilder)AuditLogs.builder().id(Utils.generateUUID()).tableName(dbAndCollData.get("coll").toString()).action(AppConstants.ACTION_TYPE_DELETE).actionDate(actionTime).oldData(!Objects.isNull(oldData) ? oldData : null).newData((Map)null).databaseName(dbName).changedData((Map)null).auditLogNumber(Utils.generateAuditLogsNo()).companyId(!Objects.isNull(oldData) && oldData.containsKey("company_id") ? oldData.get("company_id").toString() : null)).createdBy(!Objects.isNull(oldData) && oldData.containsKey("created_by") ? oldData.get("created_by").toString() : null)).build();
         auditLogs.setResourceName(this.getResourceNameBasedOnTableName(auditLogs.getTableName()));
         this.sendAuditLogToDatadog(auditLogs);
      }
   }

   private void updateChangeStreamDataProcess(ChangeStream updateStreamData) {
      if (Objects.isNull(updateStreamData) && updateStreamData == null) {
         Logger.error("Stream data is null");
      } else {
         Map<String, Object> dbAndCollData = updateStreamData.getNs();
         Map<String, Object> actionTimeData = (Map)updateStreamData.getClusterTime().get("$timestamp");
         Integer i = (Integer)actionTimeData.get("t");
         Long actionTime = i.longValue();
         Map<String, Object> oldData = updateStreamData.getFullDocumentBeforeChange();
         Map<String, Object> newDataList = updateStreamData.getFullDocument();

         assert newDataList != null;

         String dbName = dbAndCollData.containsKey("db") && !Objects.isNull(dbAndCollData.get("db")) ? dbAndCollData.get("db").toString() : null;
         Map<String, Object> updated_data = !Objects.isNull(updateStreamData.getUpdateDescription()) ? (updateStreamData.getUpdateDescription().containsKey("updatedFields") && !Objects.isNull(updateStreamData.getUpdateDescription().get("updatedFields")) ? (Map)updateStreamData.getUpdateDescription().get("updatedFields") : null) : null;
         AuditLogs auditLogs = ((AuditLogs.AuditLogsBuilder)((AuditLogs.AuditLogsBuilder)AuditLogs.builder().id(Utils.generateUUID()).tableName(dbAndCollData.get("coll").toString()).action(AppConstants.ACTION_TYPE_UPDATE).actionDate(actionTime).oldData(!Objects.isNull(oldData) ? oldData : null).newData(newDataList).databaseName(dbName).changedData(updated_data).auditLogNumber(Utils.generateAuditLogsNo()).companyId(newDataList.containsKey("company_id") && !Objects.isNull(newDataList.get("company_id")) ? newDataList.get("company_id").toString() : null)).createdBy(newDataList.containsKey("updated_by") && !Objects.isNull(newDataList.get("updated_by")) ? newDataList.get("updated_by").toString() : null)).build();
         auditLogs.setResourceName(this.getResourceNameBasedOnTableName(auditLogs.getTableName()));
         this.sendAuditLogToDatadog(auditLogs);
      }
   }

   Map<String, Object> getUpdatedChangedData(Map<String, Object> upd_data) {
      Map<String, Object> map = new HashMap();

      for(String key : upd_data.keySet()) {
         if (!key.equalsIgnoreCase("updated_date")) {
            map.put(key, upd_data.getOrDefault(key, (Object)null));
         }
      }

      return map;
   }

   private void insertChangeStreamDataProcess(ChangeStream insertStreamData) {
      if (Objects.isNull(insertStreamData)) {
         Logger.error("Stream data is null");
      } else {
         Map<String, Object> dbAndCollData = insertStreamData.getNs();
         Map<String, Object> actionTimeData = (Map)insertStreamData.getClusterTime().get("$timestamp");
         Integer i = (Integer)actionTimeData.get("t");
         Long actionTime = i.longValue();
         Map<String, Object> newData = insertStreamData.getFullDocument();

         assert newData != null;

         Logger.info(Utils.convertHashMapToJsonString(newData));
         String dbName = dbAndCollData.containsKey("db") && !Objects.isNull(dbAndCollData.get("db")) ? dbAndCollData.get("db").toString() : null;
         AuditLogs auditLogs = ((AuditLogs.AuditLogsBuilder)((AuditLogs.AuditLogsBuilder)AuditLogs.builder().id(Utils.generateUUID()).tableName(dbAndCollData.get("coll").toString()).action(AppConstants.ACTION_TYPE_INSERT).actionDate(actionTime).oldData((Map)null).newData(newData).databaseName(dbName).changedData((Map)null).auditLogNumber(Utils.generateAuditLogsNo()).companyId(newData.containsKey("company_id") && !Objects.isNull(newData.get("company_id")) ? newData.get("company_id").toString() : null)).createdBy(newData.containsKey("created_by") && !Objects.isNull(newData.get("created_by")) ? newData.get("created_by").toString() : null)).build();
         auditLogs.setResourceName(this.getResourceNameBasedOnTableName(auditLogs.getTableName()));
         this.sendAuditLogToDatadog(auditLogs);
      }
   }

   public Map<String, Object> setBankInfo(String userBankId) {
      UserBanks userBanks = this.userBanksQueryDao.findById(userBankId);
      if (Objects.isNull(userBanks)) {
         return null;
      } else {
         Map<String, Object> objectMap = new HashMap();
         objectMap.put("account_number", userBanks.getAccountNumber());
         objectMap.put("account_name", userBanks.getAccountName());
         objectMap.put("bank_code", userBanks.getBankCode());
         objectMap.put("third_party_account_number", userBanks.getThirdPartyAccountNumber());
         if (!StringUtils.isEmpty(userBanks.getBankId())) {
            BankMaster bankMaster = this.bankMasterQueryDao.findById(userBanks.getBankId());
            if (!Objects.isNull(bankMaster)) {
               objectMap.put("bank_name", bankMaster.getBankName());
            }
         }

         return objectMap;
      }
   }

   public Map<String, Double> calculatePercentageOfProducts(Map<String, Double> listOfProducts) {
      if (!Objects.isNull(listOfProducts) && !listOfProducts.isEmpty()) {
         Map<String, Double> map = new HashMap();
         double totalSum = listOfProducts.values().stream().mapToDouble(Double::doubleValue).sum();

         for(String product : listOfProducts.keySet()) {
            Double productPercentage = (Double)listOfProducts.get(product) * (double)100.0F / totalSum;
            map.put(product, productPercentage);
         }

         return map;
      } else {
         return null;
      }
   }

   public String getTheMonth(Long startDate) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeInMillis(startDate * 1000L);
      calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
      return calendar.getDisplayName(2, 2, Locale.ENGLISH);
   }

   public Map<String, Long> getPreviousMonthStartDateEndDate() {
      Map<String, Long> previousMonthStartDateEndDate = new HashMap();
      Calendar calendar = Calendar.getInstance();
      calendar.add(2, -1);
      calendar.set(5, 1);
      calendar.set(11, 0);
      calendar.set(12, 0);
      calendar.set(13, 0);
      calendar.set(14, 0);
      Date firstDateOfPreviousMonth = calendar.getTime();
      calendar.set(5, calendar.getActualMaximum(5));
      Date lastDateOfPreviousMonth = calendar.getTime();
      previousMonthStartDateEndDate.put("start_date", firstDateOfPreviousMonth.getTime() / 1000L);
      previousMonthStartDateEndDate.put("end_date", lastDateOfPreviousMonth.getTime() / 1000L);
      return previousMonthStartDateEndDate;
   }

   public void makeEntryInESTxnUserWallet(String receivedMessage, String requestID) {
      Logger.info(" Start Process For makeEntryInESTxnUserWallet Receive Message : " + receivedMessage);
      if (StringUtils.isEmpty(receivedMessage)) {
         Logger.info(" Start Process For makeEntryInESTxnUserWallet Receive Message Is Empty ");
      } else {
         Map<String, Object> stringObjectMap = Utils.convertJsonStringToHashMap(receivedMessage);
         if (stringObjectMap != null && !stringObjectMap.isEmpty()) {
            UserWallet userWallet = stringObjectMap.containsKey("user_wallet") && !Objects.isNull(stringObjectMap.get("user_wallet")) ? (UserWallet)Utils.convertHashMapToClassObject((Map)stringObjectMap.get("user_wallet"), new UserWallet()) : null;
            Double debitAmount = stringObjectMap.containsKey("debit_amount") && !Objects.isNull(stringObjectMap.get("debit_amount")) ? Double.parseDouble(stringObjectMap.get("debit_amount").toString()) : null;
            String debitTypeId = stringObjectMap.containsKey("debit_type_id") && !Objects.isNull(stringObjectMap.get("debit_type_id")) ? stringObjectMap.get("debit_type_id").toString() : null;
            String debitCurrencyId = stringObjectMap.containsKey("debit_currency_id") && !Objects.isNull(stringObjectMap.get("debit_currency_id")) ? stringObjectMap.get("debit_currency_id").toString() : null;
            Integer debitAccountType = stringObjectMap.containsKey("debit_account_type") && !Objects.isNull(stringObjectMap.get("debit_account_type")) ? Integer.valueOf(stringObjectMap.get("debit_account_type").toString()) : null;
            UserWallet toUserWallet = stringObjectMap.containsKey("to_user_wallet") && !Objects.isNull(stringObjectMap.get("to_user_wallet")) ? (UserWallet)Utils.convertHashMapToClassObject((Map)stringObjectMap.get("to_user_wallet"), new UserWallet()) : null;
            Double creditAmount = stringObjectMap.containsKey("credit_amount") && !Objects.isNull(stringObjectMap.get("credit_amount")) ? Double.parseDouble(stringObjectMap.get("credit_amount").toString()) : null;
            String creditTypeId = stringObjectMap.containsKey("credit_type_id") && !Objects.isNull(stringObjectMap.get("credit_type_id")) ? stringObjectMap.get("credit_type_id").toString() : null;
            String creditCurrencyId = stringObjectMap.containsKey("credit_currency_id") && !Objects.isNull(stringObjectMap.get("credit_currency_id")) ? stringObjectMap.get("credit_currency_id").toString() : null;
            Integer creditAccountType = stringObjectMap.containsKey("credit_account_type") && !Objects.isNull(stringObjectMap.get("credit_account_type")) ? Integer.valueOf(stringObjectMap.get("credit_account_type").toString()) : null;
            String txnId = stringObjectMap.containsKey("txn_id") && !Objects.isNull(stringObjectMap.get("txn_id")) ? stringObjectMap.get("txn_id").toString() : null;
            Double txnAmount = stringObjectMap.containsKey("txn_amount") && !Objects.isNull(stringObjectMap.get("txn_amount")) ? Double.parseDouble(stringObjectMap.get("txn_amount").toString()) : null;
            String companyId = userWallet != null && userWallet.getCompanyId() != null ? userWallet.getCompanyId() : null;
            if (companyId != null && debitTypeId != null && debitAmount != null && txnAmount != null && userWallet.getWalletBalance() != null) {
               Double previousWalletBalance = Double.valueOf(AppConstants.decimalFormat.format(userWallet.getWalletBalance() + debitAmount));
               this.saveElasticSearchTxnUserWallet(debitAccountType, userWallet.getUserId(), previousWalletBalance, userWallet.getWalletBalance(), txnAmount, txnId, debitTypeId, debitCurrencyId, companyId);
            }

            if (companyId != null && creditTypeId != null && creditAmount != null && txnAmount != null && toUserWallet != null && toUserWallet.getWalletBalance() != null) {
               Double previousWalletBalance = Double.valueOf(AppConstants.decimalFormat.format(toUserWallet.getWalletBalance() - creditAmount));
               this.saveElasticSearchTxnUserWallet(creditAccountType, toUserWallet.getUserId(), previousWalletBalance, toUserWallet.getWalletBalance(), txnAmount, txnId, creditTypeId, creditCurrencyId, companyId);
            }

         } else {
            Logger.error("There is no data found for makeEntryInESTxnUserWallet");
         }
      }
   }

   public void saveElasticSearchTxnUserWallet(Integer type, String typeId, Double previousWalletBalance, Double currentWalletBalance, Double txnAmount, String txnId, String walletId, String currencyId, String companyId) {
      WalletMaster walletMaster = this.walletMasterQueryDao.findWalletById(companyId, walletId);
      TxnUserWallet txnUserWallet = this.txnUserWalletDao.findByTypeAndTypeIdAndCurrencyIdAndIsMasterEntry(type, typeId, currencyId, true, companyId);
      TxnUserWallet txnUserWalletData = ((TxnUserWallet.TxnUserWalletBuilder)TxnUserWallet.builder().type(type).typeId(typeId).previousWalletBalance(previousWalletBalance).currentWalletBalance(currentWalletBalance).txnAmount(txnAmount).txnId(txnId).walletId(walletId).currencyId(currencyId).walletType(walletMaster != null && walletMaster.getWalletType() != null ? walletMaster.getWalletType() : null).isMasterEntry(txnUserWallet == null).companyId(companyId)).build();
      this.txnUserWalletDao.saveTxnMaster(txnUserWalletData);
   }

   public Long countAllRecords(String company_id, String collection_name, Map<String, Object> dropdown_filter, SearchFilter search_filter, DateFilter date_filter, List<String> txn_code_ignore, Map<String, Object> multiSelect_filter) {
      BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
      boolQueryBuilder.must(QueryBuilders.matchQuery("company_id", company_id));
      Map<String, Set<String>> dropdownFilerMap = this.createAndGetMapFromDropDownFilter(dropdown_filter);
      if (!Objects.isNull(dropdownFilerMap)) {
         for(Map.Entry<String, Set<String>> entry : dropdownFilerMap.entrySet()) {
            boolQueryBuilder.must(QueryBuilders.multiMatchQuery(entry.getKey(), (String[])((Set)entry.getValue()).toArray((x$0) -> new String[x$0])));
         }
      }

      if (!Objects.isNull(txn_code_ignore)) {
         for(String txnCode : txn_code_ignore) {
            boolQueryBuilder.mustNot(QueryBuilders.matchQuery("txn_code", txnCode));
         }
      }

      if (!Objects.isNull(multiSelect_filter)) {
         BoolQueryBuilder bool1 = new BoolQueryBuilder();

         for(String key : multiSelect_filter.keySet()) {
            List<?> keyOfList = (List)multiSelect_filter.get(key);
            if (!Objects.isNull(keyOfList)) {
               for(Object kOfValue : keyOfList) {
                  bool1.should(QueryBuilders.matchQuery(key, kOfValue));
               }
            }
         }

         boolQueryBuilder.must(bool1);
      }

      if (!StringUtils.isEmpty(date_filter.getDate_field_name()) && Objects.nonNull(date_filter.getStart_date()) && Objects.nonNull(date_filter.getEnd_date())) {
         boolQueryBuilder.must(QueryBuilders.rangeQuery(date_filter.getDate_field_name()).gte(date_filter.getStart_date()).lte(date_filter.getEnd_date()));
      }

      if (!Objects.isNull(search_filter) && !Objects.isNull(search_filter.getFields()) && !search_filter.getFields().isEmpty()) {
         boolQueryBuilder.must(QueryBuilders.multiMatchQuery(search_filter.getSearch_keyword(), (String[])search_filter.getFields().toArray((x$0) -> new String[x$0])));
      }

      NativeSearchQuery nativeSearchQuery = (new NativeSearchQueryBuilder()).withFilter(boolQueryBuilder).build();
      long count = 0L;

      try {
         count = this.elasticsearchRestTemplate.search(nativeSearchQuery, Class.forName("com.digipay.elasticsearch.api.txnmaster.model.".concat(collection_name))).getTotalHits();
      } catch (ClassNotFoundException e) {
         Logger.error(e.getMessage());
      }

      return count;
   }

   private Map<String, Set<String>> createAndGetMapFromDropDownFilter(Map<String, Object> dropdown_filter) {
      if (Objects.isNull(dropdown_filter)) {
         return null;
      } else {
         Map<String, Set<String>> stringSetMap = new HashMap();
         Set<String> stringSet = null;

         for(Map.Entry<String, Object> stringObjectEntry : dropdown_filter.entrySet()) {
            if (!stringSetMap.containsKey(dropdown_filter.get(stringObjectEntry.getKey()).toString())) {
               stringSet = new HashSet();
               stringSet.add((String)stringObjectEntry.getKey());
               stringSetMap.put(dropdown_filter.get(stringObjectEntry.getKey()).toString(), stringSet);
            } else {
               Set<String> str = (Set)stringSetMap.get(dropdown_filter.get(stringObjectEntry.getKey()).toString());
               str.add((String)stringObjectEntry.getKey());
               stringSetMap.put(dropdown_filter.get(stringObjectEntry.getKey()).toString(), str);
            }
         }

         return stringSetMap;
      }
   }

   public void updateNodalBankTransaction(String receivedMessage) {
      Logger.info(" Start Process For Nodal bank transaction Receive Message : " + receivedMessage);
      if (StringUtils.isEmpty(receivedMessage)) {
         Logger.info(" Start Process For Nodal bank transaction Receive Message Is Empty ");
      } else {
         Map<String, Object> stringObjectMap = Utils.convertJsonStringToHashMap(receivedMessage);
         if (stringObjectMap != null && !stringObjectMap.isEmpty()) {
            String id = stringObjectMap.containsKey("id") && !Objects.isNull(stringObjectMap.get("id")) ? stringObjectMap.get("id").toString() : null;
            String nodalBankId = stringObjectMap.containsKey("nodal_bank_id") && !Objects.isNull(stringObjectMap.get("nodal_bank_id")) ? stringObjectMap.get("nodal_bank_id").toString() : null;
            String description = stringObjectMap.containsKey("description") && !Objects.isNull(stringObjectMap.get("description")) ? stringObjectMap.get("description").toString() : null;
            Double amount = stringObjectMap.containsKey("amount") && !Objects.isNull(String.valueOf(stringObjectMap.get("amount"))) ? Double.parseDouble(stringObjectMap.get("amount").toString()) : null;
            Integer operationType = stringObjectMap.containsKey("operation_type") && !Objects.isNull(String.valueOf(stringObjectMap.get("operation_type"))) ? Integer.valueOf(stringObjectMap.get("operation_type").toString()) : null;
            Integer purpose = stringObjectMap.containsKey("purpose") && !Objects.isNull(String.valueOf(stringObjectMap.get("purpose"))) ? Integer.valueOf(stringObjectMap.get("purpose").toString()) : null;
            String note = stringObjectMap.containsKey("note") && !Objects.isNull(stringObjectMap.get("note")) ? String.valueOf(stringObjectMap.get("note")) : null;
            Long txnDate = stringObjectMap.containsKey("txn_date") && !Objects.isNull(String.valueOf(stringObjectMap.get("txn_date"))) ? Long.valueOf(stringObjectMap.get("txn_date").toString()) : null;
            String txnNumber = stringObjectMap.containsKey("txn_number") && !Objects.isNull(stringObjectMap.get("txn_number")) ? stringObjectMap.get("txn_number").toString() : null;
            Integer txnStatus = stringObjectMap.containsKey("txn_status") && !Objects.isNull(String.valueOf(stringObjectMap.get("txn_status"))) ? Integer.valueOf(stringObjectMap.get("txn_status").toString()) : null;
            String referenceTxnId = stringObjectMap.containsKey("reference_txn_id") && !Objects.isNull(stringObjectMap.get("reference_txn_id")) ? String.valueOf(stringObjectMap.get("reference_txn_id")) : null;
            Double closingBalance = stringObjectMap.containsKey("closing_balance") && !Objects.isNull(stringObjectMap.get("closing_balance")) ? Double.parseDouble(stringObjectMap.get("closing_balance").toString()) : (double)0.0F;
            String companyId = stringObjectMap.containsKey("company_id") && !Objects.isNull(stringObjectMap.get("company_id")) ? String.valueOf(stringObjectMap.get("company_id")) : null;
            String createdBy = stringObjectMap.containsKey("created_by") && !Objects.isNull(stringObjectMap.get("created_by")) ? stringObjectMap.get("created_by").toString() : null;
            NodalBankTransaction nodalBankTransaction = ((NodalBankTransaction.NodalBankTransactionBuilder)((NodalBankTransaction.NodalBankTransactionBuilder)NodalBankTransaction.builder().id(id).nodalBankId(nodalBankId).description(description).amount(amount).operationType(operationType).purpose(purpose).note(note).txnDate(txnDate).txnNumber(txnNumber).txnStatus(txnStatus).referenceTxnId(referenceTxnId).closingBalance(closingBalance).createdBy(createdBy)).companyId(companyId)).build();
            this.nodalBankTransactionDao.saveOrUpdate(nodalBankTransaction);
         } else {
            Logger.error("There is no data found for update nodal bank transaction");
         }
      }
   }

   public String getResourceNameBasedOnTableName(String tableName) {
      switch (tableName) {
         case "company_config_master" -> {
            return "Company Configuration";
         }
         case "role_master" -> {
            return "Roles";
         }
         case "cms_pages" -> {
            return "CMS Pages";
         }
         case "authorization_settings" -> {
            return "Authorization Settings";
         }
         case "wallet_master" -> {
            return "Nodal Account";
         }
         case "company_module" -> {
            return "Modules";
         }
         case "company_module_config" -> {
            return "Module Config";
         }
         case "company_module_config_option" -> {
            return "Module Config Options";
         }
         case "document_type" -> {
            return "Documents";
         }
         case "user_extrafield" -> {
            return "Extra Fields";
         }
         case "user_extrafield_options" -> {
            return "Extra Fields Options";
         }
         case "user_extrafield_validation" -> {
            return "Extra Fields Validation";
         }
         case "bank_master" -> {
            return "Banks";
         }
         case "notification_rule" -> {
            return "Notification Rules";
         }
         case "charge_detail" -> {
            return "Charge Detail";
         }
         case "charge_type_range" -> {
            return "Range Charges";
         }
         case "charge_type_standard" -> {
            return "Standard Charges";
         }
         case "charges_master" -> {
            return "Charges";
         }
         case "threshold_detail" -> {
            return "Threshold Detail";
         }
         case "threshold_master" -> {
            return "Threshold";
         }
         case "profile_master" -> {
            return "User Profile";
         }
         case "profile_products" -> {
            return "User Profile Products";
         }
         case "tree_commission_config" -> {
            return "Commission Config";
         }
         case "tree_level" -> {
            return "Tree";
         }
         case "tree_master" -> {
            return "Level";
         }
         case "agent_charges_detail" -> {
            return "Agent Charge Detail";
         }
         case "agent_charges_master" -> {
            return "Agent Charges";
         }
         case "agent_profile_master" -> {
            return "Agent Profile";
         }
         case "agent_profile_products" -> {
            return "Agent Profile Products";
         }
         case "agent_threshold_detail" -> {
            return "agent_threshold_detail";
         }
         case "agent_threshold_master" -> {
            return "agent_threshold_master";
         }
         case "company_language_translation" -> {
            return "Comapny Language Translation";
         }
         default -> {
            return null;
         }
      }
   }

   public Map<String, Long> getPreviousDayStartDateAndEndDate() {
      LocalDate yesterday = LocalDate.now().minusDays(1L);
      LocalDateTime start = yesterday.atStartOfDay();
      LocalDateTime end = yesterday.atTime(LocalTime.MAX);
      long startDate = start.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() / 1000L;
      long endDate = end.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() / 1000L;
      return Map.of("start_date", startDate, "end_date", endDate);
   }

   public boolean verifyCheckSum(String payload, HttpServletRequest request, int method, String checkSum) {
      Enumeration<String> headerNames = request.getHeaderNames();
      ArrayList<String> listOfHeader = Collections.list(headerNames);
      StringBuilder builder = new StringBuilder();
      List<String> finalSortPath = new ArrayList();

      try {
         switch (method) {
            case 1:
            case 4:
               Map<String, Object> stringStringMap;
               if (!StringUtils.isEmpty(payload) && !payload.equalsIgnoreCase("null")) {
                  stringStringMap = (Map)(new ObjectMapper()).readValue(payload, new TypeReference<Map<String, Object>>() {
                  });
               } else {
                  stringStringMap = new HashMap();
               }

               ((Stream)listOfHeader.stream().parallel()).forEach((header) -> {
                  if (header.equalsIgnoreCase("RequestID")) {
                     stringStringMap.put("request_id", request.getHeader(header));
                  } else if (header.equalsIgnoreCase("CompanyID")) {
                     stringStringMap.put("company_id", request.getHeader(header));
                  }

               });
               stringStringMap.entrySet().parallelStream().forEach((entry) -> finalSortPath.add((String)entry.getKey()));
               Collections.sort(finalSortPath);

               for(String name : finalSortPath) {
                  builder.append(name).append(String.valueOf(stringStringMap.get(name)).replaceAll("[{}\\[\\]=\\s,]", ""));
               }
               break;
            case 2:
               Map<String, Object> stringStringMap = new HashMap();
               if (!StringUtils.isEmpty(payload)) {
                  String[] queries = payload.split("&");
                  List<String> listOfQueries = Arrays.asList(queries);
                  ((Stream)listOfQueries.stream().parallel()).forEach((list) -> {
                     String[] valueAndKey = list.split("=");
                     stringStringMap.put(valueAndKey[0], valueAndKey[1]);
                     finalSortPath.add(valueAndKey[0]);
                  });
               }

               this.setHeaderListForGetAndDelete(listOfHeader, request, stringStringMap, finalSortPath);
               Collections.sort(finalSortPath);

               for(String queryKey : finalSortPath) {
                  builder.append(queryKey).append(URLDecoder.decode(String.valueOf(stringStringMap.get(queryKey)).replaceAll("[\":{}\\[\\]=]", ""), StandardCharsets.UTF_8));
               }
               break;
            case 3:
            case 5:
               Map<String, Object> stringStringMap = new HashMap();
               this.setHeaderListForGetAndDelete(listOfHeader, request, stringStringMap, finalSortPath);
               finalSortPath.add("id");
               stringStringMap.put("id", payload);
               Collections.sort(finalSortPath);

               for(String key : finalSortPath) {
                  builder.append(key).append(URLDecoder.decode(String.valueOf(stringStringMap.get(key)), StandardCharsets.UTF_8));
               }
               break;
            default:
               return true;
         }

         return !MessageDigest.isEqual(((String)Objects.requireNonNull(generateHmac256((new String(builder)).replaceAll("[\"{}\\s:,]", ""), "secret".getBytes()))).getBytes(StandardCharsets.UTF_8), checkSum.getBytes(StandardCharsets.UTF_8));
      } catch (Exception e) {
         Logger.error(ExceptionUtils.getStackTrace(e));
         return true;
      }
   }

   private void setHeaderListForGetAndDelete(List<String> listOfHeader, HttpServletRequest request, Map<String, Object> stringObjectMap, List<String> finalSortPath) {
      ((Stream)listOfHeader.stream().parallel()).forEach((header) -> {
         if (header.equalsIgnoreCase("RequestID")) {
            stringObjectMap.put("request_id", request.getHeader(header));
            finalSortPath.add("request_id");
         } else if (header.equalsIgnoreCase("CompanyID")) {
            stringObjectMap.put("company_id", request.getHeader(header));
            finalSortPath.add("company_id");
         }

      });
   }

   private static String generateHmac256(String message, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
      byte[] bytes = hmac(key, message.getBytes());
      return java.util.Base64.getEncoder().encodeToString(bytes);
   }

   private static byte[] hmac(byte[] key, byte[] message) throws NoSuchAlgorithmException, InvalidKeyException {
      Mac mac = Mac.getInstance("HmacSHA256");
      mac.init(new SecretKeySpec(key, "HmacSHA256"));
      return mac.doFinal(message);
   }

   @Async
   public void sendDataToDataDog(String message) {
      if (((AppState)this.stateMachineAccessor.getStateMachine().getState().getId()).name().equalsIgnoreCase("ACTIVE")) {
         Map<String, Object> stringObjectMap = Utils.convertJsonStringToHashMap(message);

         try {
            List<HTTPLogItem> body = Collections.singletonList((new HTTPLogItem()).ddsource("trial source").ddtags("env:staging,version:5.1").putAdditionalProperty("status", String.valueOf(stringObjectMap.get("level"))).hostname(InetAddress.getLocalHost().getHostAddress()).message(String.valueOf(stringObjectMap.get("message"))).service("Elastic Search"));
            this.logsApi.submitLog(body, (new LogsApi.SubmitLogOptionalParameters()).contentEncoding(ContentEncoding.DEFLATE));
         } catch (Exception e) {
            Logger.error(e.getMessage());
         }
      }

   }

   public List<Map<String, Object>> getTotalWalletBalances(NodalReconciliationReportRequest nodalReconciliationReportRequest) {
      List<String> custmerUserIdList = this.userMasterDataQueryDao.getAllUserIdsByCompanyIdAndUserType(nodalReconciliationReportRequest.getCompany_id(), 2);
      List<String> merchantUserIdList = this.userMasterDataQueryDao.getAllUserIdsByCompanyIdAndUserType(nodalReconciliationReportRequest.getCompany_id(), 3);
      List<String> agentUserIdList = this.userMasterDataQueryDao.getAllUserIdsByCompanyIdAndUserType(nodalReconciliationReportRequest.getCompany_id(), 5);
      List<String> adminUserIdList = Collections.singletonList(this.userMasterDataQueryDao.getCompanyAdminIdByCompanyId(nodalReconciliationReportRequest.getCompany_id()));
      BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
      boolQueryBuilder.must(QueryBuilders.matchQuery("company_id", nodalReconciliationReportRequest.getCompany_id()));
      boolQueryBuilder.must(QueryBuilders.multiMatchQuery(nodalReconciliationReportRequest.getDropdown_filter().getCurrency_id(), new String[]{"credit_currency_id", "debit_currency_id"}));
      boolQueryBuilder.mustNot(QueryBuilders.matchQuery("txn_code", "ATC"));
      boolQueryBuilder.must(QueryBuilders.rangeQuery("created_date").gte(AppConstants.START_DATE).lte(nodalReconciliationReportRequest.getDate_filter().getEnd_date()));
      DateHistogramInterval intervalType = this.getFilterType(nodalReconciliationReportRequest.getDate_filter());
      Long actualRequestStartDate = nodalReconciliationReportRequest.getDate_filter().getStart_date();
      nodalReconciliationReportRequest.getDate_filter().setStart_date(AppConstants.START_DATE);
      nodalReconciliationReportRequest.getDate_filter().setStart_date(nodalReconciliationReportRequest.getDate_filter().getStart_date() + 19800L);
      nodalReconciliationReportRequest.getDate_filter().setEnd_date(nodalReconciliationReportRequest.getDate_filter().getEnd_date() + 19800L);
      DateHistogramAggregationBuilder dateWiseAggregationBuilder = this.buildHistogramAggregationBuilder("by_txn_date", intervalType, nodalReconciliationReportRequest.getDate_filter(), AggregationBuilders.topHits("top_hits").size(100).sort("created_date", SortOrder.DESC));
      nodalReconciliationReportRequest.getDate_filter().setStart_date(actualRequestStartDate);
      NativeSearchQuery searchQuery = (new NativeSearchQueryBuilder()).withQuery(boolQueryBuilder).withSorts(new SortBuilder[]{SortBuilders.fieldSort("created_date").order(SortOrder.DESC)}).withAggregations(new AbstractAggregationBuilder[]{dateWiseAggregationBuilder}).build();

      SearchHits<TxnMaster> response;
      try {
         response = this.elasticsearchRestTemplate.search(searchQuery, TxnMaster.class);
      } catch (IndexNotFoundException | NoSuchIndexException var15) {
         Logger.error("Index Not Found Exception occur!!");
         return null;
      }

      Map<String, Aggregation> aggregationMap = ((Aggregations)((AggregationsContainer)Objects.requireNonNull(response.getAggregations())).aggregations()).asMap();
      String currencyId = nodalReconciliationReportRequest.getDropdown_filter().getCurrency_id();
      List<Map<String, Object>> reconciliationReportList = new ArrayList();
      ((ParsedDateHistogram)aggregationMap.get("by_txn_date")).getBuckets().forEach((bucket) -> {
         SearchHit[] hits = ((ParsedTopHits)bucket.getAggregations().getAsMap().get("top_hits")).getHits().getHits();
         Map<String, Object> report = new HashMap();
         report.put("timestamp", ZonedDateTime.parse(bucket.getKey().toString()).toEpochSecond());
         Map<String, Object> customer = new HashMap();
         customer.put("wallet_balance", this.getTotalBalanceByUserIdAndSearchHits(currencyId, custmerUserIdList, hits));
         Map<String, Object> merchant = new HashMap();
         merchant.put("wallet_balance", this.getTotalBalanceByUserIdAndSearchHits(currencyId, merchantUserIdList, hits));
         Map<String, Object> agent = new HashMap();
         agent.put("wallet_balance", this.getTotalBalanceByUserIdAndSearchHits(currencyId, agentUserIdList, hits));
         Map<String, Object> admin = new HashMap();
         admin.put("wallet_balance", this.getTotalBalanceByUserIdAndSearchHits(currencyId, adminUserIdList, hits));
         this.getCreditBalanceAndDebitBalance(currencyId, adminUserIdList, hits, admin);
         Map<String, Object> revenue = new HashMap();
         revenue.put("wallet_balance", this.getTotalInternalRevenueBalance(nodalReconciliationReportRequest.getCompany_id(), currencyId, nodalReconciliationReportRequest.getDate_filter().getFilter_type().equals(3) ? ZonedDateTime.parse(bucket.getKey().toString()).toEpochSecond() + 2572199L : ZonedDateTime.parse(bucket.getKey().toString()).toEpochSecond() + 86400L));
         Map<String, Object> poolAmount = new HashMap();
         poolAmount.put("wallet_balance", this.getPoolAmountBalance(nodalReconciliationReportRequest.getCompany_id(), nodalReconciliationReportRequest.getDropdown_filter().getWallet_id(), currencyId, nodalReconciliationReportRequest.getDate_filter().getFilter_type().equals(3) ? ZonedDateTime.parse(bucket.getKey().toString()).toEpochSecond() + 2572199L : ZonedDateTime.parse(bucket.getKey().toString()).toEpochSecond() + 86400L));
         report.put("customer", customer);
         report.put("merchant", merchant);
         report.put("agent", agent);
         report.put("admin", admin);
         report.put("revenue", revenue);
         report.put("pool_amount", poolAmount);
         reconciliationReportList.add(report);
      });
      return reconciliationReportList;
   }

   public Double getTotalBalanceByUserIdAndSearchHits(String currencyId, List<String> userIdList, SearchHit[] searchHitsList) {
      List<String> countedUserIdList = new ArrayList();
      double totalSum = (double)0.0F;
      if (!Objects.isNull(userIdList) && !userIdList.isEmpty()) {
         for(String userId : userIdList) {
            for(SearchHit searchHit : searchHitsList) {
               Map<String, Object> txnMaster = Utils.convertJsonStringToHashMap(searchHit.getSourceAsString());
               String debitAccountTypeId = !Objects.isNull(txnMaster) && txnMaster.containsKey("debit_account_type_id") && !Objects.isNull(txnMaster.get("debit_account_type_id")) ? String.valueOf(txnMaster.get("debit_account_type_id")) : null;
               String creditAccountTypeId = !Objects.isNull(txnMaster) && txnMaster.containsKey("credit_account_type_id") && !Objects.isNull(txnMaster.get("credit_account_type_id")) ? String.valueOf(txnMaster.get("credit_account_type_id")) : null;
               String debitCurrencyId = !Objects.isNull(txnMaster) && txnMaster.containsKey("debit_currency_id") && !Objects.isNull(txnMaster.get("debit_currency_id")) ? String.valueOf(txnMaster.get("debit_currency_id")) : null;
               String creditCurrencyId = !Objects.isNull(txnMaster) && txnMaster.containsKey("credit_currency_id") && !Objects.isNull(txnMaster.get("credit_currency_id")) ? String.valueOf(txnMaster.get("credit_currency_id")) : null;
               double debitAccountBalance = !Objects.isNull(txnMaster) && txnMaster.containsKey("debit_account_balance") && !Objects.isNull(txnMaster.get("debit_account_balance")) ? Double.parseDouble(txnMaster.get("debit_account_balance").toString()) : (double)0.0F;
               double creditAccountBalance = !Objects.isNull(txnMaster) && txnMaster.containsKey("credit_account_balance") && !Objects.isNull(txnMaster.get("credit_account_balance")) ? Double.parseDouble(txnMaster.get("credit_account_balance").toString()) : (double)0.0F;
               if (!StringUtils.isEmpty(debitAccountTypeId) && !StringUtils.isEmpty(userId) && debitAccountTypeId.equals(userId) && !StringUtils.isEmpty(debitCurrencyId) && debitCurrencyId.equals(currencyId)) {
                  if (!countedUserIdList.contains(debitAccountTypeId)) {
                     countedUserIdList.add(userId);
                     totalSum += debitAccountBalance;
                     break;
                  }
               } else if (!StringUtils.isEmpty(creditAccountTypeId) && !StringUtils.isEmpty(userId) && creditAccountTypeId.equals(userId) && !StringUtils.isEmpty(creditCurrencyId) && creditCurrencyId.equals(currencyId) && !countedUserIdList.contains(creditAccountTypeId)) {
                  countedUserIdList.add(userId);
                  totalSum += creditAccountBalance;
                  break;
               }
            }
         }

         return totalSum;
      } else {
         return (double)0.0F;
      }
   }

   public void getCreditBalanceAndDebitBalance(String currencyId, List<String> userIdList, SearchHit[] searchHitsList, Map<String, Object> admin) {
      for(String userId : userIdList) {
         double totalDebitBalance = (double)0.0F;
         double totalCreditBalance = (double)0.0F;

         for(SearchHit searchHit : searchHitsList) {
            Map<String, Object> txnMaster = Utils.convertJsonStringToHashMap(searchHit.getSourceAsString());
            String debitAccountTypeId = !Objects.isNull(txnMaster) && txnMaster.containsKey("debit_account_type_id") && !Objects.isNull(txnMaster.get("debit_account_type_id")) ? String.valueOf(txnMaster.get("debit_account_type_id")) : null;
            String creditAccountTypeId = !Objects.isNull(txnMaster) && txnMaster.containsKey("credit_account_type_id") && !Objects.isNull(txnMaster.get("credit_account_type_id")) ? String.valueOf(txnMaster.get("credit_account_type_id")) : null;
            String debitCurrencyId = !Objects.isNull(txnMaster) && txnMaster.containsKey("debit_currency_id") && !Objects.isNull(txnMaster.get("debit_currency_id")) ? String.valueOf(txnMaster.get("debit_currency_id")) : null;
            String creditCurrencyId = !Objects.isNull(txnMaster) && txnMaster.containsKey("credit_currency_id") && !Objects.isNull(txnMaster.get("credit_currency_id")) ? String.valueOf(txnMaster.get("credit_currency_id")) : null;
            double debitAccountBalance = !Objects.isNull(txnMaster) && txnMaster.containsKey("debit_account_balance") && !Objects.isNull(txnMaster.get("debit_account_balance")) ? Double.parseDouble(txnMaster.get("debit_account_balance").toString()) : (double)0.0F;
            double creditAccountBalance = !Objects.isNull(txnMaster) && txnMaster.containsKey("credit_account_balance") && !Objects.isNull(txnMaster.get("credit_account_balance")) ? Double.parseDouble(txnMaster.get("credit_account_balance").toString()) : (double)0.0F;
            if (!StringUtils.isEmpty(debitAccountTypeId) && !StringUtils.isEmpty(userId) && debitAccountTypeId.equals(userId) && !StringUtils.isEmpty(debitCurrencyId) && debitCurrencyId.equals(currencyId)) {
               totalDebitBalance += debitAccountBalance;
            } else if (!StringUtils.isEmpty(creditAccountTypeId) && !StringUtils.isEmpty(userId) && creditAccountTypeId.equals(userId) && !StringUtils.isEmpty(creditCurrencyId) && creditCurrencyId.equals(currencyId)) {
               totalCreditBalance += creditAccountBalance;
            }
         }

         admin.put("credit_balance", AppConstants.decimalFormat.format(totalCreditBalance));
         admin.put("debit_balance", AppConstants.decimalFormat.format(totalDebitBalance));
      }

   }

   public Double getTotalInternalRevenueBalance(String companyId, String currencyId, Long endDate) {
      BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
      boolQueryBuilder.must(QueryBuilders.matchQuery("company_id", companyId));
      boolQueryBuilder.must(QueryBuilders.multiMatchQuery(currencyId, new String[]{"credit_currency_id", "debit_currency_id"}));
      boolQueryBuilder.must(QueryBuilders.matchQuery("txn_code", "ATC"));
      boolQueryBuilder.must(QueryBuilders.rangeQuery("created_date").gte(AppConstants.START_DATE).lte(endDate));
      NativeSearchQuery searchQuery = (new NativeSearchQueryBuilder()).withQuery(boolQueryBuilder).withSorts(new SortBuilder[]{SortBuilders.fieldSort("created_date").order(SortOrder.DESC)}).build();
      SearchHits<TxnMaster> response = this.elasticsearchRestTemplate.search(searchQuery, TxnMaster.class);
      if (!response.isEmpty() && !response.getSearchHits().isEmpty()) {
         TxnMaster txnMaster = (TxnMaster)((org.springframework.data.elasticsearch.core.SearchHit)response.getSearchHits().get(0)).getContent();
         return txnMaster.getInternalBalance();
      } else {
         return (double)0.0F;
      }
   }

   private double getPoolAmountBalance(String companyId, String walletId, String currencyId, Long endDate) {
      BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
      boolQueryBuilder.must(QueryBuilders.matchQuery("company_id", companyId));
      boolQueryBuilder.must(QueryBuilders.multiMatchQuery(currencyId, new String[]{"credit_currency_id", "debit_currency_id"}));
      boolQueryBuilder.must(QueryBuilders.multiMatchQuery(walletId, new String[]{"credit_type_id", "debit_type_id"}));
      boolQueryBuilder.must(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("txn_code", "ACM")).should(QueryBuilders.matchQuery("txn_code", "AWM")));
      boolQueryBuilder.must(QueryBuilders.rangeQuery("created_date").gte(AppConstants.START_DATE).lte(endDate));
      NativeSearchQuery searchQuery = (new NativeSearchQueryBuilder()).withQuery(boolQueryBuilder).withSorts(new SortBuilder[]{SortBuilders.fieldSort("created_date").order(SortOrder.DESC)}).build();
      SearchHits<TxnMaster> response = this.elasticsearchRestTemplate.search(searchQuery, TxnMaster.class);
      if (!response.isEmpty() && !response.getSearchHits().isEmpty()) {
         Set<TxnMaster> deposit = (Set)response.getSearchHits().stream().map(org.springframework.data.elasticsearch.core.SearchHit::getContent).filter((txnMaster) -> txnMaster.getTxnCode().equals("ACM")).collect(Collectors.toSet());
         Set<TxnMaster> withdraw = (Set)response.getSearchHits().stream().map(org.springframework.data.elasticsearch.core.SearchHit::getContent).filter((txnMaster) -> txnMaster.getTxnCode().equals("AWM")).collect(Collectors.toSet());
         double totalDepositBalance = deposit.stream().mapToDouble(TxnMaster::getTxnAmount).sum();
         double totalWithdrawBalance = withdraw.stream().mapToDouble(TxnMaster::getTxnAmount).sum();
         return totalDepositBalance - totalWithdrawBalance;
      } else {
         return (double)0.0F;
      }
   }

   public void sendThirdPartyTxnCommissionFessToAnalyticsProcessStart(String receivedMessage) {
      if (StringUtils.isEmpty(receivedMessage)) {
         Logger.info(" Received Message From Kafka For sendThirdPartyTxnCommissionFessToAnalyticsProcessStart Is Empty ");
      } else {
         Map<String, Object> stringObjectMap = Utils.convertJsonStringToHashMap(receivedMessage);
         if (!Objects.isNull(stringObjectMap) && !stringObjectMap.isEmpty()) {
            ThirdPartyTxnCommissionFees thirdPartyTxnCommissionFees = (ThirdPartyTxnCommissionFees)Utils.convertHashMapToClassObject(stringObjectMap, new ThirdPartyTxnCommissionFees());
            this.thirdPartyTxnCommissionFeesDao.saveOrUpdate(thirdPartyTxnCommissionFees);
         } else {
            Logger.info(" Received Message From Kafka For sendThirdPartyTxnCommissionFessToAnalyticsProcessStart Is Empty HashMap ");
         }
      }
   }

   public void sendDeleteAuditLogsDataToDatadogProcessStart(String receivedMessage) {
      Logger.info(" sendDeleteAuditLogsDataToDatadogProcessStart Process Receive Message : " + receivedMessage);
      if (StringUtils.isEmpty(receivedMessage)) {
         Logger.info(" Received Message From Kafka For DeleteDataSendToDatadogForAuditLogs Is Empty ");
      } else {
         Map<String, Object> stringObjectMap = Utils.convertJsonStringToHashMap(receivedMessage);
         AuditLogs auditLogs = (AuditLogs)Utils.convertHashMapToClassObject(stringObjectMap, new AuditLogs());
         if (Objects.isNull(auditLogs)) {
            Logger.info(" Received Message From Kafka For DeleteDataSendToDatadogForAuditLogs Is Empty HashMap ");
         } else {
            this.sendAuditLogToDatadog(auditLogs);
         }
      }
   }

   @Async
   public void sendAuditLogToDatadog(AuditLogs auditLogs) {
      try {
         List<HTTPLogItem> body = Collections.singletonList((new HTTPLogItem()).ddsource("trial source").ddtags("env:staging,version:5.1").putAdditionalProperty("id", auditLogs.getId()).putAdditionalProperty("table_name", auditLogs.getTableName()).putAdditionalProperty("action", auditLogs.getAction().toString()).putAdditionalProperty("action_date", auditLogs.getActionDate().toString()).putAdditionalProperty("old_data", Utils.convertHashMapToJsonString(auditLogs.getOldData())).putAdditionalProperty("new_data", Utils.convertHashMapToJsonString(auditLogs.getNewData())).putAdditionalProperty("database_name", auditLogs.getDatabaseName()).putAdditionalProperty("changed_data", Utils.convertHashMapToJsonString(auditLogs.getChangedData())).putAdditionalProperty("audit_log_number", auditLogs.getAuditLogNumber()).putAdditionalProperty("resource_name", auditLogs.getResourceName()).putAdditionalProperty("company_id", auditLogs.getCompanyId()).putAdditionalProperty("created_by", auditLogs.getCreatedBy()).putAdditionalProperty("created_date", String.valueOf(Instant.now().getEpochSecond())).hostname(this.dataDogHost).service("audit_logs"));
         new LogsApi.ListLogsGetOptionalParameters();
         this.logsApi.submitLog(body, (new LogsApi.SubmitLogOptionalParameters()).contentEncoding(ContentEncoding.DEFLATE));
      } catch (Exception e) {
         Logger.error(e.getMessage());
      }

   }

   public List<AuditLogsData> getAllAuditLogsFromDataDog(GetAllAuditLogsDataRequest getAllAuditLogsDataRequest) {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.valueOf("application/json"));
      headers.add("DD_API_KEY", this.dataDogApiKey);
      headers.add("DD_APPLICATION_KEY", this.dataDogApplicationKey);
      Map<String, Object> requestBody = new HashMap();
      requestBody.put("filter", getAllAuditLogsDataRequest.getFilter());
      requestBody.put("page", getAllAuditLogsDataRequest.getPage());
      HttpEntity<Map<String, Object>> dataDogEntity = new HttpEntity(requestBody, headers);
      ResponseEntity<String> response = this.restTempPostAPICall(dataDogEntity, "https://api.datadoghq.com/api/v2/logs/events/search");
      if (response == null && Objects.isNull(response)) {
         Logger.error("Data is null");
         return null;
      } else {
         List<Map<String, Object>> stringObjectMap = null;
         Map<String, Object> metaDataOfPage = null;
         stringObjectMap = (List)Utils.convertJsonStringToHashMap((String)response.getBody()).get("data");
         metaDataOfPage = (Map)Utils.convertJsonStringToHashMap((String)response.getBody()).get("meta");
         String after = metaDataOfPage.containsKey("page") ? ((Map)metaDataOfPage.get("page")).get("after").toString() : null;
         getAllAuditLogsDataRequest.setAfter(!StringUtils.isEmpty(after) ? after : null);
         List<AuditLogsData> auditLogsDataList = new ArrayList();

         for(Map<String, Object> map : stringObjectMap) {
            Map<String, Object> parentAttributes = (Map)map.get("attributes");
            Map<String, Object> finalAttributes = (Map)parentAttributes.get("attributes");
            if (!finalAttributes.isEmpty() && finalAttributes != null) {
               AuditLogsData auditLogsData = new AuditLogsData();
               auditLogsData.setId(!Objects.isNull(finalAttributes.get("id")) && finalAttributes.containsKey("id") ? finalAttributes.get("id").toString() : null);
               auditLogsData.setTableName(!Objects.isNull(finalAttributes.get("table_name")) && finalAttributes.containsKey("table_name") ? finalAttributes.get("table_name").toString() : null);
               auditLogsData.setAction(!Objects.isNull(finalAttributes.get("action")) && finalAttributes.containsKey("action") ? Integer.parseInt(finalAttributes.get("action").toString()) : null);
               auditLogsData.setActionDate(!Objects.isNull(finalAttributes.get("action_date")) && finalAttributes.containsKey("action_date") ? Long.parseLong(finalAttributes.get("action_date").toString()) : null);
               auditLogsData.setOldData(!Objects.isNull(finalAttributes.get("old_data")) && finalAttributes.containsKey("old_data") ? Utils.convertJsonStringToHashMap(finalAttributes.get("old_data").toString()) : null);
               auditLogsData.setNewData(!Objects.isNull(finalAttributes.get("new_data")) && finalAttributes.containsKey("new_data") ? Utils.convertJsonStringToHashMap(finalAttributes.get("new_data").toString()) : null);
               auditLogsData.setDatabaseName(!Objects.isNull(finalAttributes.get("database_name")) && finalAttributes.containsKey("database_name") ? finalAttributes.get("database_name").toString() : null);
               auditLogsData.setChangedData(!Objects.isNull(finalAttributes.get("changed_data")) && finalAttributes.containsKey("changed_data") ? Utils.convertJsonStringToHashMap(finalAttributes.get("changed_data").toString()) : null);
               auditLogsData.setAuditLogNumber(!Objects.isNull(finalAttributes.get("audit_log_number")) && finalAttributes.containsKey("audit_log_number") ? finalAttributes.get("audit_log_number").toString() : null);
               auditLogsData.setResourceName(!Objects.isNull(finalAttributes.get("resource_name")) && finalAttributes.containsKey("resource_name") ? finalAttributes.get("resource_name").toString() : null);
               auditLogsData.setIsActive(!Objects.isNull(finalAttributes.get("is_active")) && finalAttributes.containsKey("is_active") ? Boolean.parseBoolean(finalAttributes.get("is_active").toString()) : null);
               auditLogsData.setCreatedBy(!Objects.isNull(finalAttributes.get("created_by")) && finalAttributes.containsKey("created_by") ? finalAttributes.get("created_by").toString() : null);
               auditLogsData.setCreatedDate(!Objects.isNull(finalAttributes.get("created_date")) && finalAttributes.containsKey("created_date") ? Long.parseLong(finalAttributes.get("created_date").toString()) : null);
               auditLogsDataList.add(auditLogsData);
            }
         }

         if (auditLogsDataList != null) {
            return auditLogsDataList;
         } else {
            return null;
         }
      }
   }

   public ResponseEntity<String> restTempPostAPICall(HttpEntity<Map<String, Object>> entity, String baseURL) {
      Utils.convertObjectToJsonString(entity, " Rest Temp Post API Entity ( Request Payload ) : ");
      ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
      RestTemplate restTemplate = new RestTemplate(requestFactory);

      try {
         return restTemplate.postForEntity(baseURL, entity, String.class, new Object[0]);
      } catch (Exception exception) {
         Logger.error("Error : " + ExceptionUtils.getStackTrace(exception));
         return null;
      }
   }

   public long countNumberOfSingleUseUser(List<TxnMaster> txnMasterList) {
      AtomicInteger countNumberOfSingleUseUser = new AtomicInteger();
      Set<String> crediters = (Set)txnMasterList.parallelStream().map(TxnMaster::getCreditAccountTypeId).collect(Collectors.toSet());
      Set<String> debiters = (Set)txnMasterList.parallelStream().map(TxnMaster::getDebitAccountTypeId).collect(Collectors.toSet());
      Map<String, List<TxnMaster>> collectForCreditedUser = (Map)txnMasterList.stream().collect(Collectors.groupingBy(TxnMaster::getCreditAccountTypeId));
      Map<String, List<TxnMaster>> collectForDebitedUser = (Map)txnMasterList.stream().collect(Collectors.groupingBy(TxnMaster::getCreditAccountTypeId));
      collectForCreditedUser.entrySet().parallelStream().forEach((map) -> {
         if (((List)map.getValue()).size() <= 1 && !debiters.contains(map.getKey())) {
            countNumberOfSingleUseUser.incrementAndGet();
         }

      });
      collectForDebitedUser.entrySet().parallelStream().forEach((map) -> {
         if (((List)map.getValue()).size() <= 1 && !crediters.contains(map.getKey())) {
            countNumberOfSingleUseUser.incrementAndGet();
         }

      });
      return (long)countNumberOfSingleUseUser.get();
   }

   public void fetchTransactionWithUserFirstTime(String companyId, String creditAccountTypeId, List<String> userIds, Map<String, Object> stringObjectMap, Long startDate, Map<String, Object> userWiseMap) {
      BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
      boolQueryBuilder.must(QueryBuilders.matchQuery("company_id", companyId));
      if (!StringUtils.isEmpty(creditAccountTypeId)) {
         boolQueryBuilder.must(QueryBuilders.matchQuery("credit_account_type_id", creditAccountTypeId));
      }

      boolQueryBuilder.must(QueryBuilders.termsQuery("debit_account_type_id.keyword", userIds));
      boolQueryBuilder.must(QueryBuilders.rangeQuery("txn_date").gte(0).lt(startDate));
      TermsAggregationBuilder aggregationBuilders = (TermsAggregationBuilder)((TermsAggregationBuilder)((TermsAggregationBuilder)AggregationBuilders.terms("group_by_sender").field("debit_account_type_id.keyword")).subAggregation(AggregationBuilders.sum("total_amount").field("debit_amount"))).subAggregation(AggregationBuilders.count("total_count").field("debit_amount"));
      NativeSearchQuery searchQuery = (new NativeSearchQueryBuilder()).withQuery(boolQueryBuilder).withAggregations(new AbstractAggregationBuilder[]{aggregationBuilders}).build();
      SearchHits<TxnMaster> response = null;

      try {
         response = this.elasticsearchRestTemplate.search(searchQuery, TxnMaster.class);
      } catch (IndexNotFoundException | NoSuchIndexException var15) {
         return;
      }

      Map<String, Aggregation> aggregationMap = ((Aggregations)((AggregationsContainer)Objects.requireNonNull(response.getAggregations())).aggregations()).asMap();
      Integer noOfUsers = 0;
      Double totalPayments = (double)0.0F;
      if (response != null && !response.isEmpty()) {
         List<String> userListBeforeDate = new ArrayList();
         if (userWiseMap != null || !userWiseMap.isEmpty()) {
            ((ParsedStringTerms)aggregationMap.get("group_by_sender")).getBuckets().forEach((listOfAccounts) -> userListBeforeDate.add(listOfAccounts.getKeyAsString()));
            noOfUsers = userWiseMap.entrySet().stream().filter((entry) -> userListBeforeDate.stream().noneMatch((key) -> key.equalsIgnoreCase((String)entry.getKey()))).mapToInt((entry) -> 1).sum();
            totalPayments = userWiseMap.entrySet().stream().filter((entry) -> userListBeforeDate.stream().noneMatch((key) -> key.equalsIgnoreCase((String)entry.getKey()))).mapToDouble((entry) -> {
               Object value = entry.getValue();
               return value != null ? Double.parseDouble(value.toString()) : (double)0.0F;
            }).sum();
         }
      } else {
         noOfUsers = userWiseMap.entrySet().stream().mapToInt((entry) -> 1).sum();
         totalPayments = userWiseMap.values().stream().mapToDouble((value) -> (Double)value).sum();
      }

      stringObjectMap.put("no_of_new_customers", noOfUsers);
      stringObjectMap.put("new_customer_payments", totalPayments);
   }

   private static List<String> getColumnNames(Map<String, Object> reportFile) {
      Object reportStatisticsObj = reportFile.get("report_statistics");
      if (reportStatisticsObj instanceof Map<String, Object> reportStatistics) {
         Object columnNamesObj = reportStatistics.get("column_names");
         if (columnNamesObj instanceof List) {
            return (List)columnNamesObj;
         }
      }

      throw new IllegalArgumentException("Invalid column names format");
   }

   private static Map<String, List<String>> getRowValues(Map<String, Object> reportFile) {
      Object reportStatisticsObj = reportFile.get("report_statistics");
      if (reportStatisticsObj instanceof Map<String, Object> reportStatistics) {
         Object rowValuesObj = reportStatistics.get("row_values");
         if (rowValuesObj instanceof Map) {
            return (Map)rowValuesObj;
         }
      }

      throw new IllegalArgumentException("Invalid row values format");
   }

   private static void setColumnStyles(Workbook workbook, Sheet sheet) {
      CellStyle style = workbook.createCellStyle();
      Font font = workbook.createFont();
      font.setBold(true);
      font.setFontName("Calibri");
      font.setFontHeightInPoints((short)14);
      style.setFont(font);

      for(Row row : sheet) {
         for(Cell cell : row) {
            cell.setCellStyle(style);
         }
      }

   }

   private static void createColumnHeader(Sheet sheet, List<String> columnNames, Workbook workbook) {
      Row columnHeader = sheet.createRow(0);

      for(int columnCount = 0; columnCount < columnNames.size(); ++columnCount) {
         Cell cell = columnHeader.createCell(columnCount);
         cell.setCellValue((String)columnNames.get(columnCount));
         CellStyle style = workbook.createCellStyle();
         Font font = workbook.createFont();
         font.setBold(true);
         style.setFont(font);
         cell.setCellStyle(style);
         style.setWrapText(true);
         style.setAlignment(HorizontalAlignment.CENTER);
         style.setVerticalAlignment(VerticalAlignment.CENTER);
         style.setBorderTop(BorderStyle.THIN);
         style.setBorderBottom(BorderStyle.THIN);
         style.setBorderLeft(BorderStyle.THIN);
         style.setBorderRight(BorderStyle.THIN);
      }

   }

   private static void createRows(Sheet sheet, List<List<String>> rowValues, Workbook workbook) {
      int count = 1;

      for(List<String> rowData : rowValues) {
         Row row = sheet.createRow(count);
         int columnIndex = 0;

         for(String cellData : rowData) {
            Cell cell = row.createCell(columnIndex++);
            cell.setCellValue(cellData);
         }

         for(int i = 0; i < rowData.size(); ++i) {
            sheet.autoSizeColumn(i);
         }

         CellStyle cellStyle = workbook.createCellStyle();
         cellStyle.setAlignment(HorizontalAlignment.CENTER);
         cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
         cellStyle.setBorderTop(BorderStyle.THIN);
         cellStyle.setBorderBottom(BorderStyle.THIN);
         cellStyle.setBorderLeft(BorderStyle.THIN);
         cellStyle.setBorderRight(BorderStyle.THIN);
         int rowIndex = 0;

         for(Row r : sheet) {
            if (rowIndex != 0) {
               for(Cell cell : r) {
                  cell.setCellStyle(cellStyle);
               }
            }

            ++rowIndex;
         }

         ++count;
      }

   }

   public static void convertExcelToPDF(SendReportDataRequest sendReportDataRequest, Workbook workbook, OutputStream outputStream) throws Exception {
      String headerText = sendReportDataRequest.getHeader_text();
      String logoPath = sendReportDataRequest.getLogo_url();
      Rectangle pageSize = new Rectangle(PageSize.A1);
      Document document = new Document(pageSize);
      PdfWriter writer = PdfWriter.getInstance(document, outputStream);
      String companyName = sendReportDataRequest.getCompany_name();
      HeaderFooterPageEvent1 event = new HeaderFooterPageEvent1(headerText, logoPath, companyName, sendReportDataRequest.getStart_date(), sendReportDataRequest.getEnd_date());
      writer.setPageEvent(event);
      document.open();
      Paragraph headerParagraph = new Paragraph(headerText, FontFactory.getFont("Helvetica-Bold", 12.0F));
      headerParagraph.setAlignment(1);
      document.add(headerParagraph);
      document.add(new Paragraph(" "));

      for(int i = 0; i < workbook.getNumberOfSheets(); ++i) {
         Sheet sheet = workbook.getSheetAt(i);
         int numCells = sheet.getRow(0).getPhysicalNumberOfCells();
         PdfPTable pdfTable = new PdfPTable(numCells);
         BaseColor backgroundColor = new BaseColor(200, 200, 200);
         pdfTable.getDefaultCell().setBackgroundColor(backgroundColor);
         pdfTable.setSpacingBefore(60.0F);
         pdfTable.setWidthPercentage(100.0F);
         pdfTable.getDefaultCell().setPadding(10.0F);

         for(int k = 0; k < numCells; ++k) {
            pdfTable.addCell(new PdfPCell(new Phrase(sheet.getRow(0).getCell(k).toString())));
         }

         for(int j = 1; j < sheet.getPhysicalNumberOfRows(); ++j) {
            Row row = sheet.getRow(j);
            if (row != null) {
               for(int k = 0; k < numCells; ++k) {
                  Cell cell = row.getCell(k, MissingCellPolicy.RETURN_BLANK_AS_NULL);
                  String cellValue = "";
                  if (cell != null) {
                     cellValue = cell.toString();
                  }

                  pdfTable.addCell(new PdfPCell(new Phrase(cellValue)));
               }
            }
         }

         document.add(pdfTable);
         document.newPage();
      }

      document.close();
   }

   public void sendNotificationDataToKafka(SendNotificationData sendNotificationData, String requestID) {
      Map<String, Object> stringObjectMap = new HashMap();
      stringObjectMap.put("company_id", sendNotificationData.getCompany_id());
      stringObjectMap.put("event_type", sendNotificationData.getEvent_type());
      stringObjectMap.put("user_id", sendNotificationData.getUser_id());
      stringObjectMap.put("title", sendNotificationData.getTitle());
      stringObjectMap.put("note", sendNotificationData.getNote());
      stringObjectMap.put("meta_data", sendNotificationData.getMeta_data());
      stringObjectMap.put("file_attached", sendNotificationData.getFile_attached());
      this.kafkaProducerSend.sendNotificationMessage(Utils.convertHashMapToJsonString(stringObjectMap), requestID);
   }
}
