package com.gafapay.elasticsearch.database.repository.mongodb;

import com.gafapay.elasticsearch.api.reportexport.model.request.GetAllReportExportFilesRequest;
import com.gafapay.elasticsearch.database.mongodb.ReportExportFiles;
import java.util.List;
import org.springframework.data.mongodb.core.query.Update;

public interface ReportExportFilesDao {
   void save(ReportExportFiles reportExportFiles);

   long update(String id, Update updateDocument);

   int deleteById(String id);

   ReportExportFiles findById(String id);

   List<ReportExportFiles> findAllByFilter(GetAllReportExportFilesRequest getAllReportExportFilesRequest);
}
