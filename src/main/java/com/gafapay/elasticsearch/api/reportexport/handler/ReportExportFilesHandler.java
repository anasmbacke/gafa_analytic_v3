package com.gafapay.elasticsearch.api.reportexport.handler;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.gafapay.elasticsearch.api.reportexport.model.request.DeleteReportExportFilesRequest;
import com.gafapay.elasticsearch.api.reportexport.model.request.GetAllReportExportFilesRequest;
import com.gafapay.elasticsearch.api.reportexport.model.request.GetReportExportFilesRequest;
import com.gafapay.elasticsearch.api.reportexport.model.request.SaveReportExportFilesRequest;
import com.gafapay.elasticsearch.api.reportexport.model.request.UpdateReportExportRequest;
import com.gafapay.elasticsearch.api.reportexport.model.response.GetAllReportExportFilesResponse;
import com.gafapay.elasticsearch.api.reportexport.model.response.GetReportExportFilesResponse;
import com.gafapay.elasticsearch.api.reportexport.model.response.SaveReportExportFilesResponse;
import com.gafapay.elasticsearch.api.reportexport.service.ReportExportFilesRepository;
import com.gafapay.elasticsearch.helper.HeaderProcessingHelper;
import com.gafapay.elasticsearch.utils.Utils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ReportExportFilesHandler {
   @Autowired
   private ReportExportFilesRepository reportExportFilesRepository;

   public ReportExportFilesHandler() {
   }

   public ResponseEntity<JsonNode> saveReportExportFiles(HttpHeaders headers, SaveReportExportFilesRequest saveReportExportFilesRequest) {
      HeaderProcessingHelper.setRequestHeaders(saveReportExportFilesRequest, headers);
      if (saveReportExportFilesRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         SaveReportExportFilesResponse saveReportExportFilesResponse = this.reportExportFilesRepository.saveReportExportFiles(saveReportExportFilesRequest);
         return saveReportExportFilesResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(saveReportExportFilesResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(saveReportExportFilesResponse), HttpStatus.OK);
      }
   }

   public ResponseEntity<JsonNode> updateReportExportFiles(HttpHeaders headers, UpdateReportExportRequest updateReportExportRequest) {
      HeaderProcessingHelper.setRequestHeaders(updateReportExportRequest, headers);
      if (updateReportExportRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         CommonAPIDataResponse commonAPIDataResponse = this.reportExportFilesRepository.updateReportExportFiles(updateReportExportRequest);
         return commonAPIDataResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(commonAPIDataResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(commonAPIDataResponse), HttpStatus.OK);
      }
   }

   public ResponseEntity<JsonNode> deleteReportExportFiles(HttpHeaders headers, DeleteReportExportFilesRequest deleteReportExportFilesRequest) {
      HeaderProcessingHelper.setRequestHeaders(deleteReportExportFilesRequest, headers);
      if (deleteReportExportFilesRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         CommonAPIDataResponse commonAPIDataResponse = this.reportExportFilesRepository.deleteReportExportFiles(deleteReportExportFilesRequest);
         return commonAPIDataResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(commonAPIDataResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(commonAPIDataResponse), HttpStatus.OK);
      }
   }

   public ResponseEntity<JsonNode> getReportExportFiles(HttpHeaders headers, GetReportExportFilesRequest getReportExportFIlesRequest) {
      HeaderProcessingHelper.setRequestHeaders(getReportExportFIlesRequest, headers);
      if (getReportExportFIlesRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         GetReportExportFilesResponse getReportExportFilesResponse = this.reportExportFilesRepository.getReportExportFiles(getReportExportFIlesRequest);
         return getReportExportFilesResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(getReportExportFilesResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(getReportExportFilesResponse), HttpStatus.OK);
      }
   }

   public ResponseEntity<JsonNode> getAllReportExportFiles(HttpHeaders headers, GetAllReportExportFilesRequest getAllReportExportFilesRequest) {
      HeaderProcessingHelper.setRequestHeaders(getAllReportExportFilesRequest, headers);
      if (getAllReportExportFilesRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         GetAllReportExportFilesResponse getAllReportExportFilesResponse = this.reportExportFilesRepository.getAllReportExportFiles(getAllReportExportFilesRequest);
         return getAllReportExportFilesResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(getAllReportExportFilesResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(getAllReportExportFilesResponse), HttpStatus.OK);
      }
   }
}
