package com.gafapay.elasticsearch.database.repository.mongodb;

import com.gafapay.elasticsearch.database.mongodb.UserBanks;

public interface UserBanksQueryDao {
   UserBanks findById(String id);
}
