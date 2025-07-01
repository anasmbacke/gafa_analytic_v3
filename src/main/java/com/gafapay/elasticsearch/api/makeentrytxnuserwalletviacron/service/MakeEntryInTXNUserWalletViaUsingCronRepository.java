package com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.service;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.model.request.MakeEntryInTxnUserWalletViaCronRequest;

public interface MakeEntryInTXNUserWalletViaUsingCronRepository {
   CommonAPIDataResponse makeEntryInTxnUserWalletInEveryMonthViaUsingCron(MakeEntryInTxnUserWalletViaCronRequest makeEntryInTxnUserWalletViaCronRequest);
}
