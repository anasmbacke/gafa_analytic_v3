package com.gafapay.elasticsearch.api.auditlogs.handler;

import com.gafapay.elasticsearch.api.auditlogs.model.request.GetAllAuditLogsDataRequest;
import com.gafapay.elasticsearch.api.auditlogs.model.request.GetAuditLogsDataRequest;
import com.gafapay.elasticsearch.api.auditlogs.model.response.GetAllAuditLogsDataResponse;
import com.gafapay.elasticsearch.api.auditlogs.model.response.GetAuditLogsDataResponse;
import com.gafapay.elasticsearch.api.auditlogs.service.AuditLogsRepository;
import com.gafapay.elasticsearch.helper.HeaderProcessingHelper;
import com.gafapay.elasticsearch.utils.Utils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AuditLogsResourceHandler {
   private final AuditLogsRepository auditLogsRepository;

   public AuditLogsResourceHandler(AuditLogsRepository auditLogsRepository) {
      this.auditLogsRepository = auditLogsRepository;
   }

   public ResponseEntity<JsonNode> getAllAuditLogs(HttpHeaders httpHeaders, GetAllAuditLogsDataRequest getAllAuditLogsDataRequest) {
      HeaderProcessingHelper.setRequestHeaders(getAllAuditLogsDataRequest, httpHeaders);
      if (getAllAuditLogsDataRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         GetAllAuditLogsDataResponse getAllAuditLogsDataResponse = this.auditLogsRepository.getAllAuditLogs(getAllAuditLogsDataRequest);
         return getAllAuditLogsDataResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(getAllAuditLogsDataResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(getAllAuditLogsDataResponse), HttpStatus.OK);
      }
   }

   public ResponseEntity<JsonNode> getAuditLogs(HttpHeaders httpHeaders, GetAuditLogsDataRequest getAuditLogsDataRequest) {
      HeaderProcessingHelper.setRequestHeaders(getAuditLogsDataRequest, httpHeaders);
      if (getAuditLogsDataRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         GetAuditLogsDataResponse getAuditLogsDataResponse = this.auditLogsRepository.getAuditLogs(getAuditLogsDataRequest);
         return getAuditLogsDataResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(getAuditLogsDataResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(getAuditLogsDataResponse), HttpStatus.OK);
      }
   }
}
