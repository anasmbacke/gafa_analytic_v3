package com.gafapay.elasticsearch.api.auditlogs.controller;

import com.gafapay.elasticsearch.api.auditlogs.handler.AuditLogsResourceHandler;
import com.gafapay.elasticsearch.api.auditlogs.model.request.GetAllAuditLogsDataRequest;
import com.gafapay.elasticsearch.api.auditlogs.model.request.GetAuditLogsDataRequest;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"${app.baseurl}analytics/"})
public class AuditLogsController {
   @Autowired
   private AuditLogsResourceHandler auditLogsResourceHandler;

   public AuditLogsController() {
   }

   @GetMapping({"audit_logs"})
   public ResponseEntity<JsonNode> getAllAuditLogs(@RequestHeader HttpHeaders httpHeaders, @ModelAttribute GetAllAuditLogsDataRequest getAllAuditLogsDataRequest) {
      return this.auditLogsResourceHandler.getAllAuditLogs(httpHeaders, getAllAuditLogsDataRequest);
   }

   @GetMapping({"audit_logs/{id}"})
   public ResponseEntity<JsonNode> getAuditLogs(@RequestHeader HttpHeaders httpHeaders, @PathVariable String id, @ModelAttribute GetAuditLogsDataRequest getAuditLogsDataRequest) {
      getAuditLogsDataRequest.setId(id);
      return this.auditLogsResourceHandler.getAuditLogs(httpHeaders, getAuditLogsDataRequest);
   }
}
