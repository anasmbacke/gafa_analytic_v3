package com.gafapay.elasticsearch.api.nodelbanktransaction.service.impl;

import com.gafapay.elasticsearch.api.nodelbanktransaction.dao.NodalBankTransactionDao;
import com.gafapay.elasticsearch.api.nodelbanktransaction.model.request.GetAllNodalBankTransactionRequest;
import com.gafapay.elasticsearch.api.nodelbanktransaction.model.request.GetNodalBankTransactionRequest;
import com.gafapay.elasticsearch.api.nodelbanktransaction.model.response.GetAllNodalBankTransactionResponse;
import com.gafapay.elasticsearch.api.nodelbanktransaction.model.response.GetNodalBankTransactionResponse;
import com.gafapay.elasticsearch.api.nodelbanktransaction.model.response.NodalBankTransactionData;
import com.gafapay.elasticsearch.api.nodelbanktransaction.service.NodalBankTransactionRepository;
import com.gafapay.elasticsearch.database.model.nodalbanktransaction.NodalBankTransaction;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JPANodalBankTransactionRepository implements NodalBankTransactionRepository {
   @Autowired
   private NodalBankTransactionDao nodalBankTransactionDao;

   public JPANodalBankTransactionRepository() {
   }

   public GetNodalBankTransactionResponse getNodalBankTransaction(GetNodalBankTransactionRequest getNodalBankTransactionRequest) {
      GetNodalBankTransactionResponse getNodalBankTransactionResponse = new GetNodalBankTransactionResponse();

      NodalBankTransaction nodalBankTransaction;
      try {
         nodalBankTransaction = this.nodalBankTransactionDao.getNodalBankTransactionById(getNodalBankTransactionRequest);
      } catch (Exception var5) {
         getNodalBankTransactionResponse.setValidationMessage("ELASTIC_SEARCH_NODAL_BANK_TRANSACTION_DATA_NOT_FOUND");
         getNodalBankTransactionResponse.setCheckValidationFailed(true);
         return getNodalBankTransactionResponse;
      }

      if (Objects.isNull(nodalBankTransaction)) {
         getNodalBankTransactionResponse.setValidationMessage("ELASTIC_SEARCH_NODAL_BANK_TRANSACTION_DATA_NOT_FOUND");
         getNodalBankTransactionResponse.setCheckValidationFailed(true);
         return getNodalBankTransactionResponse;
      } else {
         getNodalBankTransactionResponse.setNodalBankTransactionData(NodalBankTransactionData.setNodalBankTransactionDetails(nodalBankTransaction));
         return getNodalBankTransactionResponse;
      }
   }

   public GetAllNodalBankTransactionResponse getAllNodalBankTransaction(GetAllNodalBankTransactionRequest getAllNodalBankTransactionRequest) {
      GetAllNodalBankTransactionResponse getAllNodalBankTransactionResponse = new GetAllNodalBankTransactionResponse();

      List<NodalBankTransaction> nodalBankTransactionList;
      try {
         nodalBankTransactionList = this.nodalBankTransactionDao.getAllNodalBankTransaction(getAllNodalBankTransactionRequest);
      } catch (Exception var5) {
         getAllNodalBankTransactionResponse.setValidationMessage("ELASTIC_SEARCH_NODAL_BANK_TRANSACTION_DATA_NOT_FOUND");
         getAllNodalBankTransactionResponse.setCheckValidationFailed(true);
         return getAllNodalBankTransactionResponse;
      }

      if (!Objects.isNull(nodalBankTransactionList) && !nodalBankTransactionList.isEmpty()) {
         List<NodalBankTransactionData> nodalBankTransactionData = new ArrayList(nodalBankTransactionList.size());
         nodalBankTransactionList.parallelStream().forEachOrdered((nodalBankTransaction) -> nodalBankTransactionData.add(NodalBankTransactionData.setNodalBankTransactionDetails(nodalBankTransaction)));
         getAllNodalBankTransactionResponse.setNodalBankTransactionDataList(nodalBankTransactionData);
         return getAllNodalBankTransactionResponse;
      } else {
         getAllNodalBankTransactionResponse.setValidationMessage("ELASTIC_SEARCH_NODAL_BANK_TRANSACTION_DATA_NOT_FOUND");
         getAllNodalBankTransactionResponse.setCheckValidationFailed(true);
         return getAllNodalBankTransactionResponse;
      }
   }
}
