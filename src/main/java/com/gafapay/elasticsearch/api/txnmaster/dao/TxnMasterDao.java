package com.gafapay.elasticsearch.api.txnmaster.dao;

import com.gafapay.elasticsearch.api.txnmaster.model.TxnMaster;
import com.gafapay.elasticsearch.api.txnmaster.model.request.GetAllTransactionsDataRequest;
import java.util.List;

public interface TxnMasterDao {
   List<TxnMaster> getAllTransactions(GetAllTransactionsDataRequest getAllTransactionsDataRequest);

   void saveTxnMaster(TxnMaster txnMaster);

   TxnMaster getTxnMasterById(String txnId);

   List<TxnMaster> getAllTransactionsByCompanyId(String companyId);

   void saveAllTxnMaster(List<TxnMaster> txnMasterList);
}
