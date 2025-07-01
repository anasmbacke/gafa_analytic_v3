package com.gafapay.elasticsearch.api.migrationoftxnmaster.service.impl;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.dao.UserMasterDataQueryDao;
import com.gafapay.elasticsearch.api.migrationoftxnmaster.model.request.TotalRecordRequest;
import com.gafapay.elasticsearch.api.migrationoftxnmaster.model.request.UpdateTxnRecords;
import com.gafapay.elasticsearch.api.migrationoftxnmaster.service.MigrationOfTXNMasterRepository;
import com.gafapay.elasticsearch.api.nodelbanktransaction.model.response.GetAllNodalBankTransactionResponse;
import com.gafapay.elasticsearch.api.txnmaster.dao.TxnMasterDao;
import com.gafapay.elasticsearch.api.txnmaster.model.TxnMaster;
import com.gafapay.elasticsearch.api.txnmaster.model.request.GetAllTransactionsDataRequest;
import com.gafapay.elasticsearch.database.mongodb.UserMasterData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JPAMigrationOfTXNMasterRepository implements MigrationOfTXNMasterRepository {
   @Autowired
   private UserMasterDataQueryDao userMasterDataQueryDao;
   @Autowired
   private TxnMasterDao txnMasterDao;

   public JPAMigrationOfTXNMasterRepository() {
   }

   public GetAllNodalBankTransactionResponse migrationOfTxnMaster(TotalRecordRequest totalRecordRequest) {
      GetAllNodalBankTransactionResponse getAllNodalBankTransactionResponse = new GetAllNodalBankTransactionResponse();
      List<TxnMaster> txnMasterList = this.txnMasterDao.getAllTransactionsByCompanyId(totalRecordRequest.getCompany_id());
      List<TxnMaster> txnMasterListData = new ArrayList();

      for(TxnMaster txnMaster : txnMasterList) {
         if (!txnMaster.getTxnCode().equals("ATC")) {
            if (txnMaster.getTxnAmount() == null) {
               txnMaster.setTxnAmount((double)0.0F);
            }

            if (txnMaster.getCreditAccountBalance() == null) {
               txnMaster.setCreditAccountBalance((double)0.0F);
            }

            if (txnMaster.getDebitAccountBalance() == null) {
               txnMaster.setDebitAccountBalance((double)0.0F);
            }

            if (Objects.isNull(txnMaster.getDisplayEndUser())) {
               txnMaster.setDisplayEndUser(true);
            }
         } else {
            if (txnMaster.getTxnAmount() == null) {
               txnMaster.setTxnAmount((double)0.0F);
            }

            if (txnMaster.getCreditAccountBalance() == null) {
               txnMaster.setCreditAccountBalance((double)0.0F);
            }

            if (txnMaster.getDebitAccountBalance() == null) {
               txnMaster.setDebitAccountBalance((double)0.0F);
            }

            if (Objects.isNull(txnMaster.getDisplayEndUser())) {
               txnMaster.setDisplayEndUser(false);
            }
         }

         txnMasterListData.add(txnMaster);
      }

      this.txnMasterDao.saveAllTxnMaster(txnMasterListData);
      getAllNodalBankTransactionResponse.setMessage("TRANSACTION_TXN_MASTER_MIGRATED_SUCCESSFULLY");
      return getAllNodalBankTransactionResponse;
   }

   public CommonAPIDataResponse updateTxnRecord(UpdateTxnRecords updateTxnRecords) {
      CommonAPIDataResponse commonAPIDataResponse = new CommonAPIDataResponse();
      GetAllTransactionsDataRequest getAllTransactionsDataRequest = new GetAllTransactionsDataRequest();
      getAllTransactionsDataRequest.setStart_date(updateTxnRecords.getStart_date());
      getAllTransactionsDataRequest.setEnd_date(updateTxnRecords.getEnd_date());
      getAllTransactionsDataRequest.setCompany_id(updateTxnRecords.getCompany_id());
      List<TxnMaster> txnMasterList = this.txnMasterDao.getAllTransactions(getAllTransactionsDataRequest);
      if (txnMasterList != null && !txnMasterList.isEmpty()) {
         for(TxnMaster txnMaster : txnMasterList) {
            Map<String, Object> transactionBy = txnMaster.getTransactionBy();
            if (!Objects.isNull(transactionBy) && transactionBy.containsKey("account_id") && !Objects.isNull(transactionBy.get("account_id"))) {
               UserMasterData userMasterData = this.userMasterDataQueryDao.findById(transactionBy.get("account_id").toString());
               transactionBy.put("first_name", Objects.isNull(userMasterData) ? null : userMasterData.getFirstName());
               transactionBy.put("last_name", Objects.isNull(userMasterData) ? null : userMasterData.getLastName());
               transactionBy.put("display_name", Objects.isNull(userMasterData) ? null : userMasterData.getDisplayName());
               txnMaster.setTransactionBy(transactionBy);
               this.txnMasterDao.saveTxnMaster(txnMaster);
            }
         }

         commonAPIDataResponse.setMessage("TRANSACTION_UPDATE_SUCCESSFULLY");
         return commonAPIDataResponse;
      } else {
         commonAPIDataResponse.setCheckValidationFailed(true);
         commonAPIDataResponse.setValidationMessage("NOT_FOUND");
         return commonAPIDataResponse;
      }
   }
}
