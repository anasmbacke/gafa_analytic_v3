package com.gafapay.elasticsearch.api.sendreport.controller;

import com.gafapay.elasticsearch.api.sendreport.handler.SendReportRequest;
import com.gafapay.elasticsearch.api.sendreport.handler.SendReportResourceHandler;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping({"${app.baseurl}analytics/"})
public class SendReportController {
   @Autowired
   private SendReportResourceHandler sendReportResourceHandler;

   public SendReportController() {
   }

   @PostMapping(
      value = {"send_report"},
      consumes = {"multipart/form-data"}
   )
   public ResponseEntity<JsonNode> sendReport(@RequestHeader HttpHeaders httpHeaders, @RequestParam(value = "file",required = false) MultipartFile file, @RequestParam(value = "image_url",required = false) String imageURL, @RequestParam(value = "report_type",required = false) Integer reportType, @RequestParam(value = "report_name",required = false) String reportName) throws IOException {
      SendReportRequest sendReportRequest = new SendReportRequest();
      sendReportRequest.setReport_file(file);
      sendReportRequest.setImage_url(imageURL);
      sendReportRequest.setReport_type(reportType);
      sendReportRequest.setReport_name(reportName);
      return this.sendReportResourceHandler.sendReport(httpHeaders, sendReportRequest);
   }
}
