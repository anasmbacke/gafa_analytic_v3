package com.gafapay.elasticsearch.api.topmostearneragents.controller;

import com.gafapay.elasticsearch.api.topmostearneragents.handler.TopMostEarnerAgentResourceHandler;
import com.gafapay.elasticsearch.api.topmostearneragents.model.request.TopMostEarnerAgentsRequest;
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
public class TopMostEarnerAgentController {
   @Autowired
   private TopMostEarnerAgentResourceHandler topMostEarnerAgentResourceHandler;

   public TopMostEarnerAgentController() {
   }

   @PostMapping({"agent/top_earner"})
   public ResponseEntity<JsonNode> topMostEarnerAgents(@RequestHeader HttpHeaders httpHeaders, @RequestBody TopMostEarnerAgentsRequest topMostEarnerAgentsRequest) {
      return this.topMostEarnerAgentResourceHandler.topMostEarnerAgents(httpHeaders, topMostEarnerAgentsRequest);
   }
}
