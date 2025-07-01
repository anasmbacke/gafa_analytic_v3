package com.gafapay.elasticsearch.database.repository.mongodb.impl;

import com.gafapay.elasticsearch.api.reportexport.model.request.GetAllReportExportFilesRequest;
import com.gafapay.elasticsearch.database.mongodb.ReportExportFiles;
import com.gafapay.elasticsearch.database.repository.mongodb.ReportExportFilesDao;
import com.gafapay.elasticsearch.utils.Utils;
import io.micrometer.common.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class ReportExportFilesDaoImpl implements ReportExportFilesDao {
   @Autowired
   private MongoTemplate mongoTemplate;

   public ReportExportFilesDaoImpl() {
   }

   public void save(ReportExportFiles reportExportFiles) {
      this.mongoTemplate.save(reportExportFiles);
   }

   public long update(String id, Update updateDocument) {
      Criteria criteria = new Criteria();
      criteria.andOperator(new Criteria[]{Criteria.where("_id").is(id)});
      Query query = new Query(criteria);
      return this.mongoTemplate.updateFirst(query, updateDocument, ReportExportFiles.class).getModifiedCount();
   }

   public int deleteById(String id) {
      Criteria criteria = new Criteria();
      criteria.andOperator(new Criteria[]{Criteria.where("_id").is(id)});
      Query query = new Query(criteria);
      return this.mongoTemplate.remove(query, ReportExportFiles.class).getDeletedCount() == 1L ? 1 : 0;
   }

   public ReportExportFiles findById(String id) {
      Criteria criteria = new Criteria();
      criteria.andOperator(new Criteria[]{Criteria.where("_id").is(id)});
      Query query = new Query(criteria);
      return (ReportExportFiles)this.mongoTemplate.findOne(query, ReportExportFiles.class);
   }

   public List<ReportExportFiles> findAllByFilter(GetAllReportExportFilesRequest getAllReportExportFilesRequest) {
      List<Criteria> criteriaArrayList = new ArrayList(0);
      List<Criteria> searchKeyWordArrayList = new ArrayList(1);
      criteriaArrayList.add(Criteria.where("company_id").is(getAllReportExportFilesRequest.getCompany_id()));
      if (!StringUtils.isEmpty(getAllReportExportFilesRequest.getReport_name())) {
         criteriaArrayList.add(Criteria.where("report_name").is(getAllReportExportFilesRequest.getReport_name()));
      }

      if (!StringUtils.isEmpty(getAllReportExportFilesRequest.getDownload_url())) {
         criteriaArrayList.add(Criteria.where("download_url").is(getAllReportExportFilesRequest.getDownload_url()));
      }

      if (!StringUtils.isEmpty(getAllReportExportFilesRequest.getUser_id())) {
         criteriaArrayList.add(Criteria.where("user_id").is(getAllReportExportFilesRequest.getUser_id()));
      }

      if (!Objects.isNull(getAllReportExportFilesRequest.getUser_type())) {
         criteriaArrayList.add(Criteria.where("user_type").is(getAllReportExportFilesRequest.getUser_type()));
      }

      if (!StringUtils.isEmpty(getAllReportExportFilesRequest.getSearch_keyword())) {
         getAllReportExportFilesRequest.setSearch_keyword(Utils.replaceString(getAllReportExportFilesRequest.getSearch_keyword()));
         searchKeyWordArrayList.add(Criteria.where("report_name").regex(getAllReportExportFilesRequest.getSearch_keyword(), "si"));
      }

      criteriaArrayList.add(Utils.setStartDateAndEndDate(getAllReportExportFilesRequest.getStart_date(), getAllReportExportFilesRequest.getEnd_date(), "created_date"));
      Criteria criteria = new Criteria();
      criteria.andOperator(criteriaArrayList);
      Query query = new Query(criteria);
      if (getAllReportExportFilesRequest.getSkip() != null && getAllReportExportFilesRequest.getLimit() != null) {
         query.skip((long)getAllReportExportFilesRequest.getSkip()).limit(getAllReportExportFilesRequest.getLimit());
      }

      Utils.sortByMongo(query, getAllReportExportFilesRequest.getSorting());
      return this.mongoTemplate.find(query, ReportExportFiles.class);
   }
}
