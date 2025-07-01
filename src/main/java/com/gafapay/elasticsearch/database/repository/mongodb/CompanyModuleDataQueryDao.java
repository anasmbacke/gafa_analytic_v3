package com.gafapay.elasticsearch.database.repository.mongodb;

import com.gafapay.elasticsearch.database.mongodb.CompanyModuleData;

public interface CompanyModuleDataQueryDao {
   CompanyModuleData getCompanyModuleByCompanyIdAndGroupKey(String companyId, String groupType);
}
