package com.gafapay.elasticsearch.api.reportexport.controller;

import com.gafapay.elasticsearch.api.reportexport.handler.ReportExportFilesHandler;
import com.gafapay.elasticsearch.api.reportexport.model.request.DeleteReportExportFilesRequest;
import com.gafapay.elasticsearch.api.reportexport.model.request.GetAllReportExportFilesRequest;
import com.gafapay.elasticsearch.api.reportexport.model.request.GetReportExportFilesRequest;
import com.gafapay.elasticsearch.api.reportexport.model.request.SaveReportExportFilesRequest;
import com.gafapay.elasticsearch.api.reportexport.model.request.UpdateReportExportRequest;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"${app.baseurl}analytics/"})
public class ReportExportFilesController {
   @Autowired
   private ReportExportFilesHandler reportExportFilesHandler;

   public ReportExportFilesController() {
   }

   @PostMapping({"report_export_files"})
   public ResponseEntity<JsonNode> saveReportExportFiles(@RequestHeader HttpHeaders headers, @RequestBody SaveReportExportFilesRequest saveReportExportFilesRequest) {
      return this.reportExportFilesHandler.saveReportExportFiles(headers, saveReportExportFilesRequest);
   }

   @PutMapping({"report_export_files"})
   public ResponseEntity<JsonNode> updateReportExportFiles(@RequestHeader HttpHeaders headers, @RequestBody Map<String, Object> updateEventLogsData, @ModelAttribute UpdateReportExportRequest updateReportExportRequest) {
      updateReportExportRequest.setReportExportFilesMap(updateEventLogsData);
      return this.reportExportFilesHandler.updateReportExportFiles(headers, updateReportExportRequest);
   }

   @DeleteMapping({"report_export_files/{id}"})
   public ResponseEntity<JsonNode> deleteReportExportFiles(@RequestHeader HttpHeaders headers, @PathVariable String id, @ModelAttribute DeleteReportExportFilesRequest deleteReportExportFilesRequest) {
      deleteReportExportFilesRequest.setId(id);
      return this.reportExportFilesHandler.deleteReportExportFiles(headers, deleteReportExportFilesRequest);
   }

   @GetMapping({"report_export_files/{id}"})
   public ResponseEntity<JsonNode> getReportExportFiles(@RequestHeader HttpHeaders headers, @PathVariable String id, @ModelAttribute GetReportExportFilesRequest getReportExportFIlesRequest) {
      getReportExportFIlesRequest.setId(id);
      return this.reportExportFilesHandler.getReportExportFiles(headers, getReportExportFIlesRequest);
   }

   @GetMapping({"report_export_files"})
   public ResponseEntity<JsonNode> getAllReportExportFiles(@RequestHeader HttpHeaders headers, @ModelAttribute GetAllReportExportFilesRequest getAllReportExportFilesRequest) {
      return this.reportExportFilesHandler.getAllReportExportFiles(headers, getAllReportExportFilesRequest);
   }
}
