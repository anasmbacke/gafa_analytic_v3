package com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.handler;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.model.request.MakeEntryInTxnUserWalletViaCronRequest;
import com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.service.MakeEntryInTXNUserWalletViaUsingCronRepository;
import com.gafapay.elasticsearch.helper.HeaderProcessingHelper;
import com.gafapay.elasticsearch.utils.Utils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class MakeEntryInTXNUserWalletViaUsingCronResourceHandler {
   private final MakeEntryInTXNUserWalletViaUsingCronRepository makeEntryInTXNUserWalletViaUsingCronRepository;

   public MakeEntryInTXNUserWalletViaUsingCronResourceHandler(MakeEntryInTXNUserWalletViaUsingCronRepository makeEntryInTXNUserWalletViaUsingCronRepository) {
      this.makeEntryInTXNUserWalletViaUsingCronRepository = makeEntryInTXNUserWalletViaUsingCronRepository;
   }

   public ResponseEntity<JsonNode> makeEntryInTxnUserWalletInEveryMonthViaUsingCron(HttpHeaders headers, MakeEntryInTxnUserWalletViaCronRequest makeEntryInTxnUserWalletViaCronRequest) {
      HeaderProcessingHelper.setRequestHeaders(makeEntryInTxnUserWalletViaCronRequest, headers);
      if (makeEntryInTxnUserWalletViaCronRequest.checkBadRequest()) {
         return new ResponseEntity(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
      } else {
         CommonAPIDataResponse commonAPIDataResponse = this.makeEntryInTXNUserWalletViaUsingCronRepository.makeEntryInTxnUserWalletInEveryMonthViaUsingCron(makeEntryInTxnUserWalletViaCronRequest);
         return new ResponseEntity(Utils.generateSuccessResponse(commonAPIDataResponse), HttpStatus.OK);
      }
   }
}
