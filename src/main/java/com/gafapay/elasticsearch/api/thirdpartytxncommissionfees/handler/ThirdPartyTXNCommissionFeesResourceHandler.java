package com.gafapay.elasticsearch.api.thirdpartytxncommissionfees.handler;

import com.gafapay.elasticsearch.api.thirdpartytxncommissionfees.model.request.GetAllThirdPartyTxnCommissionFeesRequest;
import com.gafapay.elasticsearch.api.thirdpartytxncommissionfees.model.request.GetThirdPartyTxnCommissionFeesRequest;
import com.gafapay.elasticsearch.api.thirdpartytxncommissionfees.model.response.GetAllThirdPartyTxnCommissionFeesResponse;
import com.gafapay.elasticsearch.api.thirdpartytxncommissionfees.model.response.GetThirdPartyTxnCommissionFeesResponse;
import com.gafapay.elasticsearch.api.thirdpartytxncommissionfees.service.ThirdPartyTXNCommissionFeesRepository;
import com.gafapay.elasticsearch.helper.HeaderProcessingHelper;
import com.gafapay.elasticsearch.utils.Utils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ThirdPartyTXNCommissionFeesResourceHandler {
   private final ThirdPartyTXNCommissionFeesRepository thirdPartyTXNCommissionFeesRepository;

   public ThirdPartyTXNCommissionFeesResourceHandler(ThirdPartyTXNCommissionFeesRepository thirdPartyTXNCommissionFeesRepository) {
      this.thirdPartyTXNCommissionFeesRepository = thirdPartyTXNCommissionFeesRepository;
   }

   public ResponseEntity<JsonNode> getThirdPartyTxnCommissionFees(HttpHeaders headers, GetThirdPartyTxnCommissionFeesRequest getThirdPartyTxnCommissionFeesRequest) {
      HeaderProcessingHelper.setRequestHeaders(getThirdPartyTxnCommissionFeesRequest, headers);
      if (getThirdPartyTxnCommissionFeesRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         GetThirdPartyTxnCommissionFeesResponse getThirdPartyTxnCommissionFeesResponse = this.thirdPartyTXNCommissionFeesRepository.getThirdPartyTxnCommissionFees(getThirdPartyTxnCommissionFeesRequest);
         return getThirdPartyTxnCommissionFeesResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(getThirdPartyTxnCommissionFeesResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(getThirdPartyTxnCommissionFeesResponse), HttpStatus.OK);
      }
   }

   public ResponseEntity<JsonNode> getAllThirdPartyTxnCommissionFees(HttpHeaders headers, GetAllThirdPartyTxnCommissionFeesRequest getAllThirdPartyTxnCommissionFeesRequest) {
      HeaderProcessingHelper.setRequestHeaders(getAllThirdPartyTxnCommissionFeesRequest, headers);
      if (getAllThirdPartyTxnCommissionFeesRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         GetAllThirdPartyTxnCommissionFeesResponse getAllThirdPartyTxnCommissionFeesResponse = this.thirdPartyTXNCommissionFeesRepository.getAllThirdPartyTxnCommissionFees(getAllThirdPartyTxnCommissionFeesRequest);
         return getAllThirdPartyTxnCommissionFeesResponse.isCheckValidationFailed() ? new ResponseEntity(Utils.generateErrorResponse(getAllThirdPartyTxnCommissionFeesResponse.getValidationMessage()), HttpStatus.OK) : new ResponseEntity(Utils.generateSuccessResponse(getAllThirdPartyTxnCommissionFeesResponse), HttpStatus.OK);
      }
   }
}
