package com.gafapay.elasticsearch.database.repository.mongodb;

import com.gafapay.elasticsearch.database.mongodb.ProductMaster;

public interface ProductMasterQueryDao {
   ProductMaster findByCompanyIdAndProductId(String companyId, String productId);
}
