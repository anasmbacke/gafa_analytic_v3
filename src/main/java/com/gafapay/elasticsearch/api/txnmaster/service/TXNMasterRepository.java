package com.gafapay.elasticsearch.api.txnmaster.service;

import com.gafapay.elasticsearch.api.txnmaster.model.request.GetAllTransactionsDataRequest;
import com.gafapay.elasticsearch.api.txnmaster.model.request.GetTransactionDataRequest;
import com.gafapay.elasticsearch.api.txnmaster.model.response.GetAllTransactionsDataResponse;
import com.gafapay.elasticsearch.api.txnmaster.model.response.GetTransactionDataResponse;

public interface TXNMasterRepository {
   GetAllTransactionsDataResponse getAllTransactions(GetAllTransactionsDataRequest getAllTransactionsDataRequest);

   GetTransactionDataResponse getTransactionDetail(GetTransactionDataRequest getTransactionDataRequest);
}
