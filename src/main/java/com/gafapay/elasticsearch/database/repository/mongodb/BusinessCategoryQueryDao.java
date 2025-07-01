package com.gafapay.elasticsearch.database.repository.mongodb;

import com.gafapay.elasticsearch.database.mongodb.BusinessCategory;
import org.springframework.data.mongodb.core.query.Update;

public interface BusinessCategoryQueryDao {
   void save(BusinessCategory businessCategory);

   long update(String id, Update updateDocument);

   int deleteById(String id);

   BusinessCategory findById(String id);

   BusinessCategory findByNameAndCompanyId(String name, String companyId);
}
