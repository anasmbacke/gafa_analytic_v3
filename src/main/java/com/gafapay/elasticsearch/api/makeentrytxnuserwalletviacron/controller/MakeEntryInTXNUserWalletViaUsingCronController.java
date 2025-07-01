package com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.controller;

import com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.handler.MakeEntryInTXNUserWalletViaUsingCronResourceHandler;
import com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.model.request.MakeEntryInTxnUserWalletViaCronRequest;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"${app.baseurl}analytics/"})
public class MakeEntryInTXNUserWalletViaUsingCronController {
   @Autowired
   private MakeEntryInTXNUserWalletViaUsingCronResourceHandler makeEntryInTxnUserWalletInEveryMonthViaUsingCron;

   public MakeEntryInTXNUserWalletViaUsingCronController() {
   }

   @PostMapping({"make_entry_in_txn_user_wallet_via_cron"})
   public ResponseEntity<JsonNode> makeEntryInTxnUserWalletInEveryMonthViaUsingCron(@RequestHeader HttpHeaders headers, @ModelAttribute MakeEntryInTxnUserWalletViaCronRequest makeEntryInTxnUserWalletViaCronRequest) {
      return this.makeEntryInTxnUserWalletInEveryMonthViaUsingCron.makeEntryInTxnUserWalletInEveryMonthViaUsingCron(headers, makeEntryInTxnUserWalletViaCronRequest);
   }
}
