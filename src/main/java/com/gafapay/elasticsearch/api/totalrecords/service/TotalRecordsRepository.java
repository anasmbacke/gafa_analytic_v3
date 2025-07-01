package com.gafapay.elasticsearch.api.totalrecords.service;

import com.gafapay.elasticsearch.api.migrationoftxnmaster.model.request.TotalRecordRequest;
import com.gafapay.elasticsearch.api.totalrecords.model.response.TotalRecordResponse;

public interface TotalRecordsRepository {
   TotalRecordResponse totalRecord(TotalRecordRequest totalRecordRequest);
}
