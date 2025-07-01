package com.gafapay.elasticsearch.database.repository.mongodb;

import com.gafapay.elasticsearch.database.mongodb.BusinessTypeMaster;

public interface BusinessTypeMasterDao {
   BusinessTypeMaster findByCompanyIdAndKey(String companyId, String key);
}
