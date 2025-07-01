package com.gafapay.elasticsearch.api.eventlogs.handler;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.gafapay.elasticsearch.api.eventlogs.model.request.DeleteEventLogsRequest;
import com.gafapay.elasticsearch.api.eventlogs.model.request.GetAllEventLogsRequest;
import com.gafapay.elasticsearch.api.eventlogs.model.request.GetEventLogsRequest;
import com.gafapay.elasticsearch.api.eventlogs.model.request.SaveEventLogsRequest;
import com.gafapay.elasticsearch.api.eventlogs.model.request.UpdateEventLogsRequest;
import com.gafapay.elasticsearch.api.eventlogs.model.response.GetAllEventLogsResponse;
import com.gafapay.elasticsearch.api.eventlogs.model.response.GetEventLogsResponse;
import com.gafapay.elasticsearch.api.eventlogs.model.response.SaveEventLogsResponse;
import com.gafapay.elasticsearch.api.eventlogs.service.EventLogsRepository;
import com.gafapay.elasticsearch.helper.HeaderProcessingHelper;
import com.gafapay.elasticsearch.utils.Utils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class EventLogsResourceHandler {
   @Autowired
   private EventLogsRepository eventLogsRepository;

   public EventLogsResourceHandler() {
   }

   public ResponseEntity<JsonNode> saveEvenetlogsData(HttpHeaders headers, SaveEventLogsRequest saveEventLogsRequest) {
      HeaderProcessingHelper.setRequestHeaders(saveEventLogsRequest, headers);
      if (saveEventLogsRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         SaveEventLogsResponse saveEventLogsResponse = this.eventLogsRepository.saveEventLogsData(saveEventLogsRequest);
         return saveEventLogsResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(saveEventLogsResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(saveEventLogsResponse), HttpStatus.OK);
      }
   }

   public ResponseEntity<JsonNode> updateEventLogsData(HttpHeaders headers, UpdateEventLogsRequest updateEventLogsRequest) {
      HeaderProcessingHelper.setRequestHeaders(updateEventLogsRequest, headers);
      if (updateEventLogsRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         CommonAPIDataResponse commonAPIDataResponse = this.eventLogsRepository.updateEventLogsData(updateEventLogsRequest);
         return commonAPIDataResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(commonAPIDataResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(commonAPIDataResponse), HttpStatus.OK);
      }
   }

   public ResponseEntity<JsonNode> deleteEventLogsRequest(HttpHeaders headers, DeleteEventLogsRequest deleteEventLogsRequest) {
      HeaderProcessingHelper.setRequestHeaders(deleteEventLogsRequest, headers);
      if (deleteEventLogsRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         CommonAPIDataResponse commonAPIDataResponse = this.eventLogsRepository.deleteEventLogsData(deleteEventLogsRequest);
         return commonAPIDataResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(commonAPIDataResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(commonAPIDataResponse), HttpStatus.OK);
      }
   }

   public ResponseEntity<JsonNode> geteventLogsData(HttpHeaders headers, GetEventLogsRequest getEventLogsRequest) {
      HeaderProcessingHelper.setRequestHeaders(getEventLogsRequest, headers);
      if (getEventLogsRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         GetEventLogsResponse getEventLogsResponse = this.eventLogsRepository.getEventLogsData(getEventLogsRequest);
         return getEventLogsResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(getEventLogsResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(getEventLogsResponse), HttpStatus.OK);
      }
   }

   public ResponseEntity<JsonNode> getAlleventlogsData(HttpHeaders headers, GetAllEventLogsRequest getAllEventLogsRequest) {
      HeaderProcessingHelper.setRequestHeaders(getAllEventLogsRequest, headers);
      if (getAllEventLogsRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         GetAllEventLogsResponse getAllEventLogsResponse = this.eventLogsRepository.getEventLogsData(getAllEventLogsRequest);
         return getAllEventLogsResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(getAllEventLogsResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(getAllEventLogsResponse), HttpStatus.OK);
      }
   }
}
