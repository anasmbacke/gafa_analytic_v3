package com.gafapay.elasticsearch.api.thirdpartytxncommissionfees.service.impl;

import com.gafapay.elasticsearch.api.thirdpartytxncommissionfees.dao.ThirdPartyTxnCommissionFeesDao;
import com.gafapay.elasticsearch.api.thirdpartytxncommissionfees.model.request.GetAllThirdPartyTxnCommissionFeesRequest;
import com.gafapay.elasticsearch.api.thirdpartytxncommissionfees.model.request.GetThirdPartyTxnCommissionFeesRequest;
import com.gafapay.elasticsearch.api.thirdpartytxncommissionfees.model.response.GetAllThirdPartyTxnCommissionFeesResponse;
import com.gafapay.elasticsearch.api.thirdpartytxncommissionfees.model.response.GetThirdPartyTxnCommissionFeesResponse;
import com.gafapay.elasticsearch.api.thirdpartytxncommissionfees.model.response.ThirdPartyTxnCommissionFeesDetail;
import com.gafapay.elasticsearch.api.thirdpartytxncommissionfees.service.ThirdPartyTXNCommissionFeesRepository;
import com.gafapay.elasticsearch.database.model.ThirdPartyTxnCommissionFees;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JPAThirdPartyTXNCommissionFeesRepository implements ThirdPartyTXNCommissionFeesRepository {
   @Autowired
   private ThirdPartyTxnCommissionFeesDao thirdPartyTxnCommissionFeesDao;

   public JPAThirdPartyTXNCommissionFeesRepository() {
   }

   public GetThirdPartyTxnCommissionFeesResponse getThirdPartyTxnCommissionFees(GetThirdPartyTxnCommissionFeesRequest getThirdPartyTxnCommissionFeesRequest) {
      GetThirdPartyTxnCommissionFeesResponse getThirdPartyTxnCommissionFeesResponse = new GetThirdPartyTxnCommissionFeesResponse();

      ThirdPartyTxnCommissionFees thirdPartyTxnCommissionFees;
      try {
         thirdPartyTxnCommissionFees = this.thirdPartyTxnCommissionFeesDao.getById(getThirdPartyTxnCommissionFeesRequest.getId());
      } catch (Exception var5) {
         getThirdPartyTxnCommissionFeesResponse.setValidationMessage("ELASTIC_SEARCH_THIRD_PARTY_TXN_COMMISSION_NOT_FOUND");
         getThirdPartyTxnCommissionFeesResponse.setCheckValidationFailed(true);
         return getThirdPartyTxnCommissionFeesResponse;
      }

      if (Objects.isNull(thirdPartyTxnCommissionFees)) {
         getThirdPartyTxnCommissionFeesResponse.setValidationMessage("ELASTIC_SEARCH_THIRD_PARTY_TXN_COMMISSION_NOT_FOUND");
         getThirdPartyTxnCommissionFeesResponse.setCheckValidationFailed(true);
         return getThirdPartyTxnCommissionFeesResponse;
      } else {
         getThirdPartyTxnCommissionFeesResponse.setThirdPartyTxnCommissionFeesDetail(ThirdPartyTxnCommissionFeesDetail.setThirdPartyTxnCommissionFees(thirdPartyTxnCommissionFees));
         return getThirdPartyTxnCommissionFeesResponse;
      }
   }

   public GetAllThirdPartyTxnCommissionFeesResponse getAllThirdPartyTxnCommissionFees(GetAllThirdPartyTxnCommissionFeesRequest getAllThirdPartyTxnCommissionFeesRequest) {
      GetAllThirdPartyTxnCommissionFeesResponse getAllThirdPartyTxnCommissionFeesResponse = new GetAllThirdPartyTxnCommissionFeesResponse();

      List<ThirdPartyTxnCommissionFees> thirdPartyTxnCommissionFeesList;
      try {
         thirdPartyTxnCommissionFeesList = this.thirdPartyTxnCommissionFeesDao.getAllThirdPartyTxnCommissionFeesByFilters(getAllThirdPartyTxnCommissionFeesRequest);
      } catch (Exception var7) {
         getAllThirdPartyTxnCommissionFeesResponse.setValidationMessage("ELASTIC_SEARCH_THIRD_PARTY_TXN_COMMISSION_NOT_FOUND");
         getAllThirdPartyTxnCommissionFeesResponse.setCheckValidationFailed(true);
         return getAllThirdPartyTxnCommissionFeesResponse;
      }

      if (!Objects.isNull(thirdPartyTxnCommissionFeesList) && !thirdPartyTxnCommissionFeesList.isEmpty()) {
         List<ThirdPartyTxnCommissionFeesDetail> thirdPartyTxnCommissionFeesDetailList = new ArrayList(thirdPartyTxnCommissionFeesList.size());

         for(ThirdPartyTxnCommissionFees thirdPartyTxnCommissionFees : thirdPartyTxnCommissionFeesList) {
            thirdPartyTxnCommissionFeesDetailList.add(ThirdPartyTxnCommissionFeesDetail.setThirdPartyTxnCommissionFees(thirdPartyTxnCommissionFees));
         }

         getAllThirdPartyTxnCommissionFeesResponse.setThirdPartyTxnCommissionFeesDetailList(thirdPartyTxnCommissionFeesDetailList);
         return getAllThirdPartyTxnCommissionFeesResponse;
      } else {
         getAllThirdPartyTxnCommissionFeesResponse.setValidationMessage("ELASTIC_SEARCH_THIRD_PARTY_TXN_COMMISSION_NOT_FOUND");
         getAllThirdPartyTxnCommissionFeesResponse.setCheckValidationFailed(true);
         return getAllThirdPartyTxnCommissionFeesResponse;
      }
   }
}
