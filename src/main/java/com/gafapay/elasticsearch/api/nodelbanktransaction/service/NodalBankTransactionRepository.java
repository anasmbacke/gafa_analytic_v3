package com.gafapay.elasticsearch.api.nodelbanktransaction.service;

import com.gafapay.elasticsearch.api.nodelbanktransaction.model.request.GetAllNodalBankTransactionRequest;
import com.gafapay.elasticsearch.api.nodelbanktransaction.model.request.GetNodalBankTransactionRequest;
import com.gafapay.elasticsearch.api.nodelbanktransaction.model.response.GetAllNodalBankTransactionResponse;
import com.gafapay.elasticsearch.api.nodelbanktransaction.model.response.GetNodalBankTransactionResponse;

public interface NodalBankTransactionRepository {
   GetNodalBankTransactionResponse getNodalBankTransaction(GetNodalBankTransactionRequest getNodalBankTransactionRequest);

   GetAllNodalBankTransactionResponse getAllNodalBankTransaction(GetAllNodalBankTransactionRequest getAllNodalBankTransactionRequest);
}
