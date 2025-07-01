package com.gafapay.elasticsearch.api.txnmaster.service.impl;

import com.gafapay.elasticsearch.api.txnmaster.dao.TxnMasterDao;
import com.gafapay.elasticsearch.api.txnmaster.model.TxnMaster;
import com.gafapay.elasticsearch.api.txnmaster.model.request.GetAllTransactionsDataRequest;
import com.gafapay.elasticsearch.api.txnmaster.model.request.GetTransactionDataRequest;
import com.gafapay.elasticsearch.api.txnmaster.model.response.GetAllTransactionsDataResponse;
import com.gafapay.elasticsearch.api.txnmaster.model.response.GetTransactionDataResponse;
import com.gafapay.elasticsearch.api.txnmaster.model.response.TxnData;
import com.gafapay.elasticsearch.api.txnmaster.service.TXNMasterRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JPATXNMasterRepository implements TXNMasterRepository {
   @Autowired
   private TxnMasterDao txnMasterDao;

   public JPATXNMasterRepository() {
   }

   public GetAllTransactionsDataResponse getAllTransactions(GetAllTransactionsDataRequest getAllTransactionsDataRequest) {
      GetAllTransactionsDataResponse getAllTransactionsDataResponse = new GetAllTransactionsDataResponse();

      List<TxnMaster> txnMasterList;
      try {
         txnMasterList = this.txnMasterDao.getAllTransactions(getAllTransactionsDataRequest);
      } catch (Exception var7) {
         getAllTransactionsDataResponse.setValidationMessage("ELASTIC_SEARCH_TRANSACTION_NOT_FOUND");
         getAllTransactionsDataResponse.setCheckValidationFailed(true);
         return getAllTransactionsDataResponse;
      }

      if (!Objects.isNull(txnMasterList) && !txnMasterList.isEmpty()) {
         List<TxnData> txnArrayList = new ArrayList(txnMasterList.size());

         for(TxnMaster txnMaster : txnMasterList) {
            txnArrayList.add(TxnData.setTxnMaster(txnMaster));
         }

         getAllTransactionsDataResponse.setTxnMasterList(txnArrayList);
         return getAllTransactionsDataResponse;
      } else {
         getAllTransactionsDataResponse.setValidationMessage("ELASTIC_SEARCH_TRANSACTION_NOT_FOUND");
         getAllTransactionsDataResponse.setCheckValidationFailed(true);
         return getAllTransactionsDataResponse;
      }
   }

   public GetTransactionDataResponse getTransactionDetail(GetTransactionDataRequest getTransactionDataRequest) {
      GetTransactionDataResponse getTransactionDataResponse = new GetTransactionDataResponse();

      TxnMaster txnMaster;
      try {
         txnMaster = this.txnMasterDao.getTxnMasterById(getTransactionDataRequest.getId());
      } catch (Exception var5) {
         getTransactionDataResponse.setValidationMessage("ELASTIC_SEARCH_TRANSACTION_NOT_FOUND");
         getTransactionDataResponse.setCheckValidationFailed(true);
         return getTransactionDataResponse;
      }

      if (Objects.isNull(txnMaster)) {
         getTransactionDataResponse.setValidationMessage("ELASTIC_SEARCH_TRANSACTION_NOT_FOUND");
         getTransactionDataResponse.setCheckValidationFailed(true);
         return getTransactionDataResponse;
      } else {
         getTransactionDataResponse.setTxnData(TxnData.setTxnMaster(txnMaster));
         return getTransactionDataResponse;
      }
   }
}
