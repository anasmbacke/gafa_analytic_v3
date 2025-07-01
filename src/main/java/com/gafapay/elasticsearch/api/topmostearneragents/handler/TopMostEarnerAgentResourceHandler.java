package com.gafapay.elasticsearch.api.topmostearneragents.handler;

import com.gafapay.elasticsearch.api.topmostearneragents.model.request.TopMostEarnerAgentsRequest;
import com.gafapay.elasticsearch.api.topmostearneragents.model.response.TopMostEarnerAgentsResponse;
import com.gafapay.elasticsearch.api.topmostearneragents.service.TopMostEarnerAgentRepository;
import com.gafapay.elasticsearch.helper.HeaderProcessingHelper;
import com.gafapay.elasticsearch.utils.Utils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TopMostEarnerAgentResourceHandler {
   private final TopMostEarnerAgentRepository topMostEarnerAgentRepository;

   public TopMostEarnerAgentResourceHandler(TopMostEarnerAgentRepository topMostEarnerAgentRepository) {
      this.topMostEarnerAgentRepository = topMostEarnerAgentRepository;
   }

   public ResponseEntity<JsonNode> topMostEarnerAgents(HttpHeaders httpHeaders, TopMostEarnerAgentsRequest topMostEarnerAgentsRequest) {
      HeaderProcessingHelper.setRequestHeaders(topMostEarnerAgentsRequest, httpHeaders);
      if (topMostEarnerAgentsRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         TopMostEarnerAgentsResponse topMostEarnerAgentsResponse = this.topMostEarnerAgentRepository.topMostEarnerAgents(topMostEarnerAgentsRequest);
         return topMostEarnerAgentsResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(topMostEarnerAgentsResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(topMostEarnerAgentsResponse), HttpStatus.OK);
      }
   }
}
