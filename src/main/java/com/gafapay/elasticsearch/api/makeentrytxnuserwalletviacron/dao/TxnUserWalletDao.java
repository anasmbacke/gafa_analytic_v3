package com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.dao;

import com.gafapay.elasticsearch.database.model.TxnUserWallet;

public interface TxnUserWalletDao {
   void saveTxnMaster(TxnUserWallet txnUserWallet);

   TxnUserWallet findByTypeAndTypeIdAndCurrencyIdAndIsMasterEntry(Integer type, String TypeId, String currencyId, Boolean isMasterEntry, String companyId);
}
