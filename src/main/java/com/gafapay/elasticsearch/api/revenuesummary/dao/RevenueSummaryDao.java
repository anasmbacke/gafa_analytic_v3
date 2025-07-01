package com.gafapay.elasticsearch.api.revenuesummary.dao;

import com.gafapay.elasticsearch.api.revenuesummary.model.RevenueSummary;
import com.gafapay.elasticsearch.api.revenuesummary.model.request.GetAllRevenueSummaryDataRequest;
import com.gafapay.elasticsearch.api.revenuesummary.model.request.GetRevenueSummaryDataRequest;
import java.util.List;

public interface RevenueSummaryDao {
   void saveOrUpdate(RevenueSummary revenueSummary);

   List<RevenueSummary> getAllRevenueSummary(GetAllRevenueSummaryDataRequest getAllRevenueSummaryDataRequest);

   RevenueSummary getRevenueSummaryById(GetRevenueSummaryDataRequest getRevenueSummaryDataRequest);
}
