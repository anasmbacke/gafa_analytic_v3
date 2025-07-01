package com.gafapay.elasticsearch.database.repository.mongodb;

import com.gafapay.elasticsearch.database.mongodb.BusinessMaster;

public interface BusinessMasterDataQueryDao {
   BusinessMaster findByCompanyIdAndUserId(String companyId, String userId);
}
