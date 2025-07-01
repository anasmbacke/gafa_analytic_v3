package com.gafapay.elasticsearch.database.repository.mongodb;

import com.gafapay.elasticsearch.database.mongodb.CompanyModuleConfigData;
import java.util.List;

public interface CompanyModuleConfigMasterDataQueryDao {
   List<CompanyModuleConfigData> findByCompanyIdAndModuleId(String companyId, String moduleId);
}
