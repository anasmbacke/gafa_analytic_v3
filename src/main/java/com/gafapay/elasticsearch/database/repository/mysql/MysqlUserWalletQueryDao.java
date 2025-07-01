package com.gafapay.elasticsearch.database.repository.mysql;

import com.gafapay.elasticsearch.api.makeentrytxnuserwalletviacron.model.UserWallet;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MysqlUserWalletQueryDao extends CrudRepository<UserWallet, String> {
   List<UserWallet> findAllByCompanyId(String company_id);
}
