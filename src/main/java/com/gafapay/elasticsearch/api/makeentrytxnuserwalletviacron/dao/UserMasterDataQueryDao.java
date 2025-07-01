package com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.dao;

import com.gafapay.elasticsearch.database.mongodb.UserMasterData;
import java.util.List;
import java.util.Map;

public interface UserMasterDataQueryDao {
   UserMasterData findById(String id);

   Integer findUserTypeByUserId(String userId);

   List<String> getAllUserIdsByCompanyIdAndUserType(String companyId, Integer userType);

   String getCompanyAdminIdByCompanyId(String companyId);

   void findByCompanyIdAndDateWiseAllCountOfUser(String companyId, Long startDate, Long endDate, Map<String, Object> stringObjectMap);

   UserMasterData getAdminInfo(String companyId);
}
