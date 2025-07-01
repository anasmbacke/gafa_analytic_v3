package com.gafapay.elasticsearch.api.migrationoftxnmaster.service;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.gafapay.elasticsearch.api.migrationoftxnmaster.model.request.TotalRecordRequest;
import com.gafapay.elasticsearch.api.migrationoftxnmaster.model.request.UpdateTxnRecords;
import com.gafapay.elasticsearch.api.nodelbanktransaction.model.response.GetAllNodalBankTransactionResponse;

public interface MigrationOfTXNMasterRepository {
   GetAllNodalBankTransactionResponse migrationOfTxnMaster(TotalRecordRequest totalRecordRequest);

   CommonAPIDataResponse updateTxnRecord(UpdateTxnRecords updateTxnRecords);
}
