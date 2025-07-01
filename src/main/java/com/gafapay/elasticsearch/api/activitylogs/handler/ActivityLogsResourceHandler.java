package com.gafapay.elasticsearch.api.activitylogs.handler;

import com.gafapay.elasticsearch.api.activitylogs.model.request.GetAllActivityLogsDataRequest;
import com.gafapay.elasticsearch.api.activitylogs.model.response.GetAllActivityLogsDataResponse;
import com.gafapay.elasticsearch.api.activitylogs.service.ActivityLogsRepository;
import com.gafapay.elasticsearch.helper.HeaderProcessingHelper;
import com.gafapay.elasticsearch.utils.Utils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ActivityLogsResourceHandler {
   private final ActivityLogsRepository activityLogsRepository;

   public ActivityLogsResourceHandler(ActivityLogsRepository activityLogsRepository) {
      this.activityLogsRepository = activityLogsRepository;
   }

   public ResponseEntity<JsonNode> getAllActivityLogs(HttpHeaders httpHeaders, GetAllActivityLogsDataRequest getAllActivityLogsDataRequest) {
      HeaderProcessingHelper.setRequestHeaders(getAllActivityLogsDataRequest, httpHeaders);
      if (getAllActivityLogsDataRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         GetAllActivityLogsDataResponse getAllActivityLogsDataResponse = this.activityLogsRepository.getAllActivityLogs(getAllActivityLogsDataRequest);
         return getAllActivityLogsDataResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(getAllActivityLogsDataResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(getAllActivityLogsDataResponse), HttpStatus.OK);
      }
   }
}
