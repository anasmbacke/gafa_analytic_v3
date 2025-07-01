package com.gafapay.elasticsearch.api.nodalreconciliationreport.service.impl;

import com.gafapay.elasticsearch.api.nodalreconciliationreport.model.request.NodalReconciliationReportRequest;
import com.gafapay.elasticsearch.api.nodalreconciliationreport.model.response.NodalReconciliationReportResponse;
import com.gafapay.elasticsearch.api.nodalreconciliationreport.service.NodalReconciliationReportRepository;
import com.gafapay.elasticsearch.helper.ElasticSearchUtility;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JPANodalReconciliationReportRepository implements NodalReconciliationReportRepository {
   @Autowired
   private ElasticSearchUtility elasticSearchUtility;

   public JPANodalReconciliationReportRepository() {
   }

   public NodalReconciliationReportResponse nodalReconciliationReport(NodalReconciliationReportRequest nodalReconciliationReportRequest) {
      NodalReconciliationReportResponse nodalReconciliationReportResponse = new NodalReconciliationReportResponse();
      List<Map<String, Object>> reportList = this.elasticSearchUtility.getTotalWalletBalances(nodalReconciliationReportRequest);
      List<Map<String, Object>> finalReconciliationReportList = new ArrayList();
      if (reportList == null) {
         nodalReconciliationReportResponse.setCheckValidationFailed(true);
         nodalReconciliationReportResponse.setValidationMessage("TXN_MASTER_DATA_NOT_FOUND");
         return nodalReconciliationReportResponse;
      } else {
         for(Map<String, Object> report : reportList) {
            if (!Objects.isNull(report) && report.containsKey("timestamp") && !Objects.isNull(report.get("timestamp")) && Long.parseLong(report.get("timestamp").toString()) >= nodalReconciliationReportRequest.getDate_filter().getStart_date() && Long.parseLong(report.get("timestamp").toString()) <= nodalReconciliationReportRequest.getDate_filter().getEnd_date()) {
               finalReconciliationReportList.add(report);
            }
         }

         nodalReconciliationReportResponse.setNodalReconciliationReportList(finalReconciliationReportList);
         return nodalReconciliationReportResponse;
      }
   }
}
