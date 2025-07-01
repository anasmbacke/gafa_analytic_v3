package com.gafapay.elasticsearch.api.nodelbanktransaction.dao;

import com.gafapay.elasticsearch.api.nodelbanktransaction.model.request.GetAllNodalBankTransactionRequest;
import com.gafapay.elasticsearch.api.nodelbanktransaction.model.request.GetNodalBankTransactionRequest;
import com.gafapay.elasticsearch.database.model.nodalbanktransaction.NodalBankTransaction;
import java.util.List;

public interface NodalBankTransactionDao {
   void saveOrUpdate(NodalBankTransaction nodalBankTransaction);

   List<NodalBankTransaction> getAllNodalBankTransaction(GetAllNodalBankTransactionRequest getAllNodalBankTransactionRequest);

   NodalBankTransaction getNodalBankTransactionById(GetNodalBankTransactionRequest getNodalBankTransactionRequest);
}
