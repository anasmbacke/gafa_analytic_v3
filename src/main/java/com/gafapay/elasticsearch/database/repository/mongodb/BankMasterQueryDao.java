package com.gafapay.elasticsearch.database.repository.mongodb;

import com.gafapay.elasticsearch.database.mongodb.BankMaster;

public interface BankMasterQueryDao {
   BankMaster findById(String id);
}
