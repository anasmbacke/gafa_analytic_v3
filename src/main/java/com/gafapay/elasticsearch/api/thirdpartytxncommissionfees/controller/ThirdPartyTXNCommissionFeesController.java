package com.gafapay.elasticsearch.api.thirdpartytxncommissionfees.controller;

import com.gafapay.elasticsearch.api.thirdpartytxncommissionfees.handler.ThirdPartyTXNCommissionFeesResourceHandler;
import com.gafapay.elasticsearch.api.thirdpartytxncommissionfees.model.request.GetAllThirdPartyTxnCommissionFeesRequest;
import com.gafapay.elasticsearch.api.thirdpartytxncommissionfees.model.request.GetThirdPartyTxnCommissionFeesRequest;
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
public class ThirdPartyTXNCommissionFeesController {
   @Autowired
   private ThirdPartyTXNCommissionFeesResourceHandler thirdPartyTXNCommissionFeesResourceHandler;

   public ThirdPartyTXNCommissionFeesController() {
   }

   @GetMapping({"third_party_txn_commission/{id}"})
   public ResponseEntity<JsonNode> getThirdPartyTxnCommissionFees(@RequestHeader HttpHeaders headers, @PathVariable String id, @ModelAttribute GetThirdPartyTxnCommissionFeesRequest getThirdPartyTxnCommissionFeesRequest) {
      getThirdPartyTxnCommissionFeesRequest.setId(id);
      return this.thirdPartyTXNCommissionFeesResourceHandler.getThirdPartyTxnCommissionFees(headers, getThirdPartyTxnCommissionFeesRequest);
   }

   @GetMapping({"third_party_txn_commission"})
   public ResponseEntity<JsonNode> getAllThirdPartyTxnCommissionFees(@RequestHeader HttpHeaders headers, @ModelAttribute GetAllThirdPartyTxnCommissionFeesRequest getAllThirdPartyTxnCommissionFeesRequest) {
      return this.thirdPartyTXNCommissionFeesResourceHandler.getAllThirdPartyTxnCommissionFees(headers, getAllThirdPartyTxnCommissionFeesRequest);
   }
}
