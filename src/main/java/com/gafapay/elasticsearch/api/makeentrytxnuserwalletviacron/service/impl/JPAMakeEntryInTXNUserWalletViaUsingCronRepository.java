package com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.service.impl;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.dao.TxnUserWalletDao;
import com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.dao.UserMasterDataQueryDao;
import com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.dao.WalletMasterQueryDao;
import com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.model.UserWallet;
import com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.model.request.MakeEntryInTxnUserWalletViaCronRequest;
import com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.service.MakeEntryInTXNUserWalletViaUsingCronRepository;
import com.gafapay.elasticsearch.database.model.TxnUserWallet;
import com.gafapay.elasticsearch.database.mongodb.WalletMaster;
import com.gafapay.elasticsearch.database.repository.mysql.MysqlUserWalletQueryDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JPAMakeEntryInTXNUserWalletViaUsingCronRepository implements MakeEntryInTXNUserWalletViaUsingCronRepository {
   @Autowired
   private MysqlUserWalletQueryDao mysqlUserWalletQueryDao;
   @Autowired
   private TxnUserWalletDao txnUserWalletDao;
   @Autowired
   private UserMasterDataQueryDao userMasterDataQueryDao;
   @Autowired
   private WalletMasterQueryDao walletMasterQueryDao;

   public JPAMakeEntryInTXNUserWalletViaUsingCronRepository() {
   }

   public CommonAPIDataResponse makeEntryInTxnUserWalletInEveryMonthViaUsingCron(MakeEntryInTxnUserWalletViaCronRequest makeEntryInTxnUserWalletViaCronRequest) {
      CommonAPIDataResponse commonAPIDataResponse = new CommonAPIDataResponse();
      List<UserWallet> userWalletList = this.mysqlUserWalletQueryDao.findAllByCompanyId(makeEntryInTxnUserWalletViaCronRequest.getCompany_id());
      if (userWalletList == null) {
         commonAPIDataResponse.setValidationMessage("ELASTIC_SEARCH_USER_WALLET_DATA_NOT_FOUND");
         commonAPIDataResponse.setCheckValidationFailed(true);
         return commonAPIDataResponse;
      } else {
         for(UserWallet userWallet : userWalletList) {
            Integer userType = this.userMasterDataQueryDao.findUserTypeByUserId(userWallet.getUserId());
            if (userType != null && userWallet.getUserId() != null && userWallet.getWalletBalance() != null && userWallet.getWalletId() != null && userWallet.getCurrencyId() != null && userWallet.getCompanyId() != null) {
               WalletMaster walletMaster = this.walletMasterQueryDao.findWalletById(userWallet.getCompanyId(), userWallet.getWalletId());
               TxnUserWallet txnUserWallet = this.txnUserWalletDao.findByTypeAndTypeIdAndCurrencyIdAndIsMasterEntry(userType, userWallet.getUserId(), userWallet.getCurrencyId(), true, userWallet.getCompanyId());
               if (txnUserWallet == null) {
                  TxnUserWallet txnUserWalletData = ((TxnUserWallet.TxnUserWalletBuilder)TxnUserWallet.builder().type(userType).typeId(userWallet.getUserId()).previousWalletBalance(userWallet.getWalletBalance()).currentWalletBalance(userWallet.getWalletBalance()).txnAmount((Double)null).txnId((String)null).walletId(userWallet.getWalletId()).currencyId(userWallet.getCurrencyId()).walletType(walletMaster != null && walletMaster.getWalletType() != null ? walletMaster.getWalletType() : null).isMasterEntry(true).companyId(userWallet.getCompanyId())).build();
                  this.txnUserWalletDao.saveTxnMaster(txnUserWalletData);
               }
            }
         }

         commonAPIDataResponse.setMessage("ELASTIC_SEARCH_SUCCESSFULLY_MAKE_ALL_USER_WALLET_ENTRY_IN_TXN_USER_WALLET");
         return commonAPIDataResponse;
      }
   }
}
