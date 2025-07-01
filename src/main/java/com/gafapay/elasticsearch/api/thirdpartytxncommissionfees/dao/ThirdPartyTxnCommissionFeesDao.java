package com.gafapay.elasticsearch.api.thirdpartytxncommissionfees.dao;

import com.gafapay.elasticsearch.api.thirdpartytxncommissionfees.model.request.GetAllThirdPartyTxnCommissionFeesRequest;
import com.gafapay.elasticsearch.database.model.ThirdPartyTxnCommissionFees;
import java.util.List;

public interface ThirdPartyTxnCommissionFeesDao {
   void saveOrUpdate(ThirdPartyTxnCommissionFees thirdPartyTxnCommissionFees);

   List<ThirdPartyTxnCommissionFees> getAllThirdPartyTxnCommissionFeesByFilters(GetAllThirdPartyTxnCommissionFeesRequest getAllThirdPartyTxnCommissionFeesRequest);

   ThirdPartyTxnCommissionFees getById(String id);
}
