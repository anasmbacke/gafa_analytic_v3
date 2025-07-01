package com.gafapay.elasticsearch.api.eventlogs.controller;

import com.gafapay.elasticsearch.api.eventlogs.handler.EventLogsResourceHandler;
import com.gafapay.elasticsearch.api.eventlogs.model.request.DeleteEventLogsRequest;
import com.gafapay.elasticsearch.api.eventlogs.model.request.GetAllEventLogsRequest;
import com.gafapay.elasticsearch.api.eventlogs.model.request.GetEventLogsRequest;
import com.gafapay.elasticsearch.api.eventlogs.model.request.SaveEventLogsRequest;
import com.gafapay.elasticsearch.api.eventlogs.model.request.UpdateEventLogsRequest;
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
@RequestMapping({"analytics/"})
public class EventLogsController {
   @Autowired
   private EventLogsResourceHandler eventLogsResourceHandler;

   public EventLogsController() {
   }

   @PostMapping({"event"})
   public ResponseEntity<JsonNode> saveEvenetlogsData(@RequestHeader HttpHeaders headers, @RequestBody SaveEventLogsRequest saveEventLogsRequest) {
      return this.eventLogsResourceHandler.saveEvenetlogsData(headers, saveEventLogsRequest);
   }

   @PutMapping({"event"})
   public ResponseEntity<JsonNode> updateEvenetlogsData(@RequestHeader HttpHeaders headers, @RequestBody Map<String, Object> updateEventLogsData, @ModelAttribute UpdateEventLogsRequest updateEventLogsRequest) {
      updateEventLogsRequest.setUpdateEvnentdataMap(updateEventLogsData);
      return this.eventLogsResourceHandler.updateEventLogsData(headers, updateEventLogsRequest);
   }

   @DeleteMapping({"event/{id}"})
   public ResponseEntity<JsonNode> deleteEvenetlogsData(@RequestHeader HttpHeaders headers, @PathVariable String id, @ModelAttribute DeleteEventLogsRequest deleteEventLogsRequest) {
      deleteEventLogsRequest.setId(id);
      return this.eventLogsResourceHandler.deleteEventLogsRequest(headers, deleteEventLogsRequest);
   }

   @GetMapping({"event/{id}"})
   public ResponseEntity<JsonNode> getEvenetlogsData(@RequestHeader HttpHeaders headers, @PathVariable String id, @ModelAttribute GetEventLogsRequest getEventLogsRequest) {
      getEventLogsRequest.setId(id);
      return this.eventLogsResourceHandler.geteventLogsData(headers, getEventLogsRequest);
   }

   @GetMapping({"event"})
   public ResponseEntity<JsonNode> getAlleventlogsData(@RequestHeader HttpHeaders headers, @ModelAttribute GetAllEventLogsRequest getAllEventLogsRequest) {
      return this.eventLogsResourceHandler.getAlleventlogsData(headers, getAllEventLogsRequest);
   }
}
