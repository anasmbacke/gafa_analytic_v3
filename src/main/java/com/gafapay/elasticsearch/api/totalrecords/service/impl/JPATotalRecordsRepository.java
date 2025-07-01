package com.gafapay.elasticsearch.api.totalrecords.service.impl;

import com.gafapay.elasticsearch.api.migrationoftxnmaster.model.request.TotalRecordRequest;
import com.gafapay.elasticsearch.api.totalrecords.model.response.TotalRecordResponse;
import com.gafapay.elasticsearch.api.totalrecords.service.TotalRecordsRepository;
import com.gafapay.elasticsearch.helper.ElasticSearchUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JPATotalRecordsRepository implements TotalRecordsRepository {
   @Autowired
   private ElasticSearchUtility elasticSearchUtility;

   public JPATotalRecordsRepository() {
   }

   public TotalRecordResponse totalRecord(TotalRecordRequest totalRecordRequest) {
      TotalRecordResponse totalRecordResponse = new TotalRecordResponse();
      totalRecordResponse.setTotalCount(this.elasticSearchUtility.countAllRecords(totalRecordRequest.getCompany_id(), totalRecordRequest.getCollection_name(), totalRecordRequest.getDropdown_filter(), totalRecordRequest.getSearch_filter(), totalRecordRequest.getDate_filter(), totalRecordRequest.getTxn_code_ignore(), totalRecordRequest.getMultiSelect_filter()));
      return totalRecordResponse;
   }
}
