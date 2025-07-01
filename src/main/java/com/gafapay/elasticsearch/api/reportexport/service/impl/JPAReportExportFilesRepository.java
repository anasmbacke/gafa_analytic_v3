package com.gafapay.elasticsearch.api.reportexport.service.impl;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.gafapay.elasticsearch.api.reportexport.model.request.DeleteReportExportFilesRequest;
import com.gafapay.elasticsearch.api.reportexport.model.request.GetAllReportExportFilesRequest;
import com.gafapay.elasticsearch.api.reportexport.model.request.GetReportExportFilesRequest;
import com.gafapay.elasticsearch.api.reportexport.model.request.SaveReportExportFilesRequest;
import com.gafapay.elasticsearch.api.reportexport.model.request.UpdateReportExportRequest;
import com.gafapay.elasticsearch.api.reportexport.model.response.GetAllReportExportFilesResponse;
import com.gafapay.elasticsearch.api.reportexport.model.response.GetReportExportFilesResponse;
import com.gafapay.elasticsearch.api.reportexport.model.response.ReportExportFilesDetails;
import com.gafapay.elasticsearch.api.reportexport.model.response.SaveReportExportFilesResponse;
import com.gafapay.elasticsearch.api.reportexport.service.ReportExportFilesRepository;
import com.gafapay.elasticsearch.database.mongodb.ReportExportFiles;
import com.gafapay.elasticsearch.database.repository.mongodb.ReportExportFilesDao;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class JPAReportExportFilesRepository implements ReportExportFilesRepository {
   @Autowired
   private ReportExportFilesDao reportExportFilesDao;

   public JPAReportExportFilesRepository() {
   }

   public SaveReportExportFilesResponse saveReportExportFiles(SaveReportExportFilesRequest saveReportExportFilesRequest) {
      SaveReportExportFilesResponse saveReportExportFilesResponse = new SaveReportExportFilesResponse();
      ReportExportFiles reportExportFiles = ((ReportExportFiles.ReportExportFilesBuilder)((ReportExportFiles.ReportExportFilesBuilder)ReportExportFiles.builder().reportName(saveReportExportFilesRequest.getReportName()).downloadUrl(saveReportExportFilesRequest.getDownloadUrl()).fileFormat(saveReportExportFilesRequest.getFileFormat()).filterApplied(saveReportExportFilesRequest.getFilterApplied()).userId(saveReportExportFilesRequest.getUserId()).userType(saveReportExportFilesRequest.getUserType()).reportDate(saveReportExportFilesRequest.getReportDate()).companyId(saveReportExportFilesRequest.getCompany_id())).createdBy(saveReportExportFilesRequest.getToken_user_id())).build();
      this.reportExportFilesDao.save(reportExportFiles);
      saveReportExportFilesResponse.setId(reportExportFiles.getId());
      saveReportExportFilesResponse.setMessage("REPORT_EXPORT_FILES_SAVED_SUCCESSFULLY");
      return saveReportExportFilesResponse;
   }

   public CommonAPIDataResponse deleteReportExportFiles(DeleteReportExportFilesRequest deleteReportExportFilesRequest) {
      CommonAPIDataResponse commonAPIDataResponse = new CommonAPIDataResponse();
      int isRemove = this.reportExportFilesDao.deleteById(deleteReportExportFilesRequest.getId());
      if (isRemove == 0) {
         commonAPIDataResponse.setValidationMessage("REPORT_EXPORT_FILES_NOT_FOUND");
         commonAPIDataResponse.setCheckValidationFailed(true);
         return commonAPIDataResponse;
      } else {
         commonAPIDataResponse.setMessage("REPORT_EXPORT_FILES_DELETED_SUCCESSFULLY");
         return commonAPIDataResponse;
      }
   }

   public GetReportExportFilesResponse getReportExportFiles(GetReportExportFilesRequest getReportExportFIlesRequest) {
      GetReportExportFilesResponse getReportExportFilesResponse = new GetReportExportFilesResponse();
      ReportExportFiles reportExportFiles = this.reportExportFilesDao.findById(getReportExportFIlesRequest.getId());
      if (Objects.isNull(reportExportFiles)) {
         getReportExportFilesResponse.setValidationMessage("REPORT_EXPORT_FILES_NOT_FOUND");
         getReportExportFilesResponse.setCheckValidationFailed(true);
         return getReportExportFilesResponse;
      } else {
         getReportExportFilesResponse.setCheckForUnAuthorized(false);
         getReportExportFilesResponse.setMessage("REPORT_EXPORT_FILES_NOT_FOUND");
         getReportExportFilesResponse.setReportExportFilesDetails(new ReportExportFilesDetails(reportExportFiles));
         return getReportExportFilesResponse;
      }
   }

   public CommonAPIDataResponse updateReportExportFiles(UpdateReportExportRequest updateReportExportRequest) {
      CommonAPIDataResponse commonAPIDataResponse = new CommonAPIDataResponse();
      String id = updateReportExportRequest.getReportExportFilesMap().containsKey("id") ? updateReportExportRequest.getReportExportFilesMap().get("id").toString() : null;
      Update updateDocument = new Update();

      for(String key : updateReportExportRequest.getReportExportFilesMap().keySet()) {
         if (!key.equalsIgnoreCase("id")) {
            updateDocument.set(key, updateReportExportRequest.getReportExportFilesMap().getOrDefault(key, (Object)null));
         }
      }

      updateDocument.set("updated_by", updateReportExportRequest.getToken_user_id());
      updateDocument.set("updated_date", Instant.now().getEpochSecond());
      long updateStatus = this.reportExportFilesDao.update(id, updateDocument);
      if (updateStatus == 0L) {
         commonAPIDataResponse.setCheckValidationFailed(true);
         commonAPIDataResponse.setValidationMessage("EVENT_LOGS_NOT_FOUND");
         return commonAPIDataResponse;
      } else {
         commonAPIDataResponse.setMessage("EVENT_LOGS_UPDATED_SUCCESSFULLY");
         return commonAPIDataResponse;
      }
   }

   public GetAllReportExportFilesResponse getAllReportExportFiles(GetAllReportExportFilesRequest getAllReportExportFilesRequest) {
      GetAllReportExportFilesResponse getAllReportExportFilesResponse = new GetAllReportExportFilesResponse();
      List<ReportExportFiles> reportExportFilesList = this.reportExportFilesDao.findAllByFilter(getAllReportExportFilesRequest);
      if (!Objects.isNull(reportExportFilesList) && !reportExportFilesList.isEmpty()) {
         List<ReportExportFilesDetails> reportExportFilesDetailsList = (List)reportExportFilesList.stream().map(ReportExportFilesDetails::new).collect(Collectors.toList());
         getAllReportExportFilesResponse.setReportExportFilesDetails(reportExportFilesDetailsList);
         return getAllReportExportFilesResponse;
      } else {
         getAllReportExportFilesResponse.setValidationMessage("REPORT_EXPORT_FILES_DATA_NOT_FOUND");
         getAllReportExportFilesResponse.setCheckValidationFailed(true);
         return getAllReportExportFilesResponse;
      }
   }
}
