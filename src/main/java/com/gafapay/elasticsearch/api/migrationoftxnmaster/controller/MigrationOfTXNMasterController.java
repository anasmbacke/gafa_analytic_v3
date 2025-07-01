package com.gafapay.elasticsearch.api.migrationoftxnmaster.controller;

import com.gafapay.elasticsearch.api.migrationoftxnmaster.handler.MigrationOfTXNMasterResourceHandler;
import com.gafapay.elasticsearch.api.migrationoftxnmaster.model.request.TotalRecordRequest;
import com.gafapay.elasticsearch.api.migrationoftxnmaster.model.request.UpdateTxnRecords;
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
public class MigrationOfTXNMasterController {
   @Autowired
   private MigrationOfTXNMasterResourceHandler migrationOfTXNMasterResourceHandler;

   public MigrationOfTXNMasterController() {
   }

   @PostMapping({"migration_of_txn_master"})
   public ResponseEntity<JsonNode> migrationOfTxnMaster(@RequestHeader HttpHeaders httpHeaders, @RequestBody TotalRecordRequest totalRecordRequest) {
      return this.migrationOfTXNMasterResourceHandler.migrationOfTxnMaster(httpHeaders, totalRecordRequest);
   }

   @PostMapping({"update_txn_record"})
   public ResponseEntity<JsonNode> updateTxnRecord(@RequestHeader HttpHeaders httpHeaders, @RequestBody UpdateTxnRecords updateTxnRecords) {
      return this.migrationOfTXNMasterResourceHandler.updateTxnRecord(httpHeaders, updateTxnRecords);
   }
}
