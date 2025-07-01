package com.gafapay.elasticsearch.database.repository.impl;

import com.gafapay.elasticsearch.database.model.DailyTxnSummary;
import com.gafapay.elasticsearch.database.repository.elasticsearch.DailyTxnSummaryDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DailyTxnSummaryIml implements DailyTxnSummaryDao {
   @Autowired
   private ElasticsearchRestTemplate elasticsearchRestTemplate;

   public DailyTxnSummaryIml() {
   }

   public void saveOrUpdateDailyTxnSummary(List<DailyTxnSummary> dailyTxnSummary) {
      this.elasticsearchRestTemplate.save(dailyTxnSummary);
   }
}
