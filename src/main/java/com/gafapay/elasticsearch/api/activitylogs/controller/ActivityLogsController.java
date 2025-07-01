package com.gafapay.elasticsearch.api.activitylogs.controller;

import com.gafapay.elasticsearch.api.activitylogs.handler.ActivityLogsResourceHandler;
import com.gafapay.elasticsearch.api.activitylogs.model.request.GetAllActivityLogsDataRequest;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"${app.baseurl}analytics/"})
public class ActivityLogsController {
   @Autowired
   private ActivityLogsResourceHandler activityLogsResourceHandler;

   public ActivityLogsController() {
   }

   @GetMapping({"activity_logs"})
   public ResponseEntity<JsonNode> getAllActivityLogs(@RequestHeader HttpHeaders httpHeaders, @ModelAttribute GetAllActivityLogsDataRequest getAllActivityLogsDataRequest) {
      return this.activityLogsResourceHandler.getAllActivityLogs(httpHeaders, getAllActivityLogsDataRequest);
   }
}
