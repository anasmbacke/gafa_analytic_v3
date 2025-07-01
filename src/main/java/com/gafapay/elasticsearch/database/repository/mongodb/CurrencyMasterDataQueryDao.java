package com.gafapay.elasticsearch.database.repository.mongodb;

import com.gafapay.elasticsearch.database.mongodb.CurrencyMasterData;
import java.util.List;

public interface CurrencyMasterDataQueryDao {
   CurrencyMasterData findByCompanyIdAndCurrencyId(String companyId, String currencyId);

   List<CurrencyMasterData> findByCompanyId(String companyId);
}
