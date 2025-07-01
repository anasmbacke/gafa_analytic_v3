package com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.dao;

import com.gafapay.elasticsearch.database.mongodb.WalletMaster;

public interface WalletMasterQueryDao {
   WalletMaster findWalletById(String companyId, String walletId);
}
