package com.gafapay.elasticsearch.api.sendreport.service;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.gafapay.elasticsearch.api.sendreport.handler.SendReportRequest;
import java.io.IOException;

public interface SendReportRepository {
   CommonAPIDataResponse sendStatisticsReport(SendReportRequest sendReportRequest) throws IOException;
}
