package com.gafapay.elasticsearch.api.totalrecords.controller;

import com.gafapay.elasticsearch.api.migrationoftxnmaster.model.request.TotalRecordRequest;
import com.gafapay.elasticsearch.api.totalrecords.handler.TotalRecordsResourceHandler;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"${app.baseurl}analytics/"})
public class TotalRecordsController {
   @Autowired
   private TotalRecordsResourceHandler totalRecordsResourceHandler;

   public TotalRecordsController() {
   }

   @PostMapping({"total_records"})
   public ResponseEntity<JsonNode> totalRecord(@RequestHeader HttpHeaders httpHeaders, @RequestBody TotalRecordRequest totalRecordRequest) {
      return this.totalRecordsResourceHandler.totalRecord(httpHeaders, totalRecordRequest);
   }
}
