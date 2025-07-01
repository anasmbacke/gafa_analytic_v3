package com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.model.request;

import com.gafapay.elasticsearch.api.commonrequest.CommonAPIDataRequest;
import org.apache.commons.lang3.StringUtils;

public class MakeEntryInTxnUserWalletViaCronRequest extends CommonAPIDataRequest {
   public boolean checkBadRequest() {
      return StringUtils.isEmpty(this.getCompany_id());
   }

   public MakeEntryInTxnUserWalletViaCronRequest() {
   }
}
