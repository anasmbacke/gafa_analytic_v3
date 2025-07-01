package com.gafapay.elasticsearch.database.repository.mysql;

import com.gafapay.elasticsearch.database.mysql.PayOutMaster;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MysqlPayOutMasterQueryDao extends CrudRepository<PayOutMaster, String> {
   PayOutMaster findByCompanyIdAndTxnIdAndIsActive(String company_id, String txn_id, boolean is_active);

   @Query(
      value = "SELECT * FROM payout_master payoutMaster WHERE payoutMaster.company_id = :company_id AND payoutMaster.txn_id = :txn_id",
      nativeQuery = true
   )
   List<PayOutMaster> findByTxnId(@Param("company_id") String company_id, @Param("txn_id") String txn_id);
}
