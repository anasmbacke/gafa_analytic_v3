package com.gafapay.elasticsearch.api.reportexport.service;

import com.gafapay.elasticsearch.api.commonresponse.CommonAPIDataResponse;
import com.gafapay.elasticsearch.api.reportexport.model.request.DeleteReportExportFilesRequest;
import com.gafapay.elasticsearch.api.reportexport.model.request.GetAllReportExportFilesRequest;
import com.gafapay.elasticsearch.api.reportexport.model.request.GetReportExportFilesRequest;
import com.gafapay.elasticsearch.api.reportexport.model.request.SaveReportExportFilesRequest;
import com.gafapay.elasticsearch.api.reportexport.model.request.UpdateReportExportRequest;
import com.gafapay.elasticsearch.api.reportexport.model.response.GetAllReportExportFilesResponse;
import com.gafapay.elasticsearch.api.reportexport.model.response.GetReportExportFilesResponse;
import com.gafapay.elasticsearch.api.reportexport.model.response.SaveReportExportFilesResponse;

public interface ReportExportFilesRepository {
   SaveReportExportFilesResponse saveReportExportFiles(SaveReportExportFilesRequest saveReportExportFilesRequest);

   CommonAPIDataResponse updateReportExportFiles(UpdateReportExportRequest updateReportExportRequest);

   CommonAPIDataResponse deleteReportExportFiles(DeleteReportExportFilesRequest deleteReportExportFilesRequest);

   GetReportExportFilesResponse getReportExportFiles(GetReportExportFilesRequest getReportExportFIlesRequest);

   GetAllReportExportFilesResponse getAllReportExportFiles(GetAllReportExportFilesRequest getAllReportExportFilesRequest);
}
