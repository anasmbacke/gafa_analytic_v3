package com.gafapay.elasticsearch.database.repository.mysql;

import com.gafapay.elasticsearch.database.mysql.TransferMaster;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MysqlTransferMasterQueryDao extends CrudRepository<TransferMaster, String> {
   TransferMaster findByCompanyIdAndTxnIdAndIsActive(String company_id, String txn_id, boolean is_active);

   @Query(
      value = "SELECT * FROM transfer_master transferMaster WHERE transferMaster.company_id = :company_id AND transferMaster.txn_id = :txn_id",
      nativeQuery = true
   )
   List<TransferMaster> findByTxnId(@Param("company_id") String company_id, @Param("txn_id") String txn_id);
}
