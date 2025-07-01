package com.gafapay.elasticsearch.database.mongodb;

import com.gafapay.elasticsearch.database.model.CommonFieldModel;
import com.gafapay.elasticsearch.utils.Utils;
import java.util.Map;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(
   collection = "report_export_files"
)
public class ReportExportFiles extends CommonFieldModel {
   @Field("_id")
   private String id;
   @Field("report_name")
   private String reportName;
   @Field("download_url")
   private String downloadUrl;
   @Field("file_format")
   private Integer fileFormat;
   @Field("filter_applied")
   private Map<String, Object> filterApplied;
   @Field("user_id")
   private String userId;
   @Field("user_type")
   private Integer userType;
   @Field("report_date")
   private Long reportDate;

   private static String $default$id() {
      return Utils.generateUUID();
   }

   protected ReportExportFiles(final ReportExportFilesBuilder<?, ?> b) {
      super(b);
      if (b.id$set) {
         this.id = b.id$value;
      } else {
         this.id = $default$id();
      }

      this.reportName = b.reportName;
      this.downloadUrl = b.downloadUrl;
      this.fileFormat = b.fileFormat;
      this.filterApplied = b.filterApplied;
      this.userId = b.userId;
      this.userType = b.userType;
      this.reportDate = b.reportDate;
   }

   public static ReportExportFilesBuilder<?, ?> builder() {
      return new ReportExportFilesBuilderImpl();
   }

   public String getId() {
      return this.id;
   }

   public String getReportName() {
      return this.reportName;
   }

   public String getDownloadUrl() {
      return this.downloadUrl;
   }

   public Integer getFileFormat() {
      return this.fileFormat;
   }

   public Map<String, Object> getFilterApplied() {
      return this.filterApplied;
   }

   public String getUserId() {
      return this.userId;
   }

   public Integer getUserType() {
      return this.userType;
   }

   public Long getReportDate() {
      return this.reportDate;
   }

   public void setId(final String id) {
      this.id = id;
   }

   public void setReportName(final String reportName) {
      this.reportName = reportName;
   }

   public void setDownloadUrl(final String downloadUrl) {
      this.downloadUrl = downloadUrl;
   }

   public void setFileFormat(final Integer fileFormat) {
      this.fileFormat = fileFormat;
   }

   public void setFilterApplied(final Map<String, Object> filterApplied) {
      this.filterApplied = filterApplied;
   }

   public void setUserId(final String userId) {
      this.userId = userId;
   }

   public void setUserType(final Integer userType) {
      this.userType = userType;
   }

   public void setReportDate(final Long reportDate) {
      this.reportDate = reportDate;
   }

   public ReportExportFiles() {
      this.id = $default$id();
   }

   public abstract static class ReportExportFilesBuilder<C extends ReportExportFiles, B extends ReportExportFilesBuilder<C, B>> extends CommonFieldModel.CommonFieldModelBuilder<C, B> {
      private boolean id$set;
      private String id$value;
      private String reportName;
      private String downloadUrl;
      private Integer fileFormat;
      private Map<String, Object> filterApplied;
      private String userId;
      private Integer userType;
      private Long reportDate;

      public ReportExportFilesBuilder() {
      }

      protected abstract B self();

      public abstract C build();

      public B id(final String id) {
         this.id$value = id;
         this.id$set = true;
         return (B)this.self();
      }

      public B reportName(final String reportName) {
         this.reportName = reportName;
         return (B)this.self();
      }

      public B downloadUrl(final String downloadUrl) {
         this.downloadUrl = downloadUrl;
         return (B)this.self();
      }

      public B fileFormat(final Integer fileFormat) {
         this.fileFormat = fileFormat;
         return (B)this.self();
      }

      public B filterApplied(final Map<String, Object> filterApplied) {
         this.filterApplied = filterApplied;
         return (B)this.self();
      }

      public B userId(final String userId) {
         this.userId = userId;
         return (B)this.self();
      }

      public B userType(final Integer userType) {
         this.userType = userType;
         return (B)this.self();
      }

      public B reportDate(final Long reportDate) {
         this.reportDate = reportDate;
         return (B)this.self();
      }

      public String toString() {
         String var10000 = super.toString();
         return "ReportExportFiles.ReportExportFilesBuilder(super=" + var10000 + ", id$value=" + this.id$value + ", reportName=" + this.reportName + ", downloadUrl=" + this.downloadUrl + ", fileFormat=" + this.fileFormat + ", filterApplied=" + this.filterApplied + ", userId=" + this.userId + ", userType=" + this.userType + ", reportDate=" + this.reportDate + ")";
      }

      // $FF: synthetic method
      // $FF: bridge method
      public CommonFieldModel build() {
         return this.build();
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected CommonFieldModel.CommonFieldModelBuilder self() {
         return this.self();
      }
   }

   private static final class ReportExportFilesBuilderImpl extends ReportExportFilesBuilder<ReportExportFiles, ReportExportFilesBuilderImpl> {
      private ReportExportFilesBuilderImpl() {
      }

      protected ReportExportFilesBuilderImpl self() {
         return this;
      }

      public ReportExportFiles build() {
         return new ReportExportFiles(this);
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected ReportExportFilesBuilder self() {
         return this.self();
      }

      // $FF: synthetic method
      // $FF: bridge method
      public CommonFieldModel build() {
         return this.build();
      }

      // $FF: synthetic method
      // $FF: bridge method
      protected CommonFieldModel.CommonFieldModelBuilder self() {
         return this.self();
      }
   }
}
