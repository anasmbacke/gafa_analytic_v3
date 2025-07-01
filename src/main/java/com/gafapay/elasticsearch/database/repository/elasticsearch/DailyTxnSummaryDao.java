package com.gafapay.elasticsearch.database.repository.elasticsearch;

import com.gafapay.elasticsearch.database.model.DailyTxnSummary;
import java.util.List;

public interface DailyTxnSummaryDao {
   void saveOrUpdateDailyTxnSummary(List<DailyTxnSummary> dailyTxnSummary);
}
